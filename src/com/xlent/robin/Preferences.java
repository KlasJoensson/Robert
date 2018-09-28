package com.xlent.robin;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class is meant to read data from files. 
 * For now its task will be to translate names of apps.
 * 
 * @author Klas Jönsson
 *
 */
public class Preferences {

	private Map<String, String> appNames;
	private final String APP_NAME_FILE_PATH = "./resources/appNames.dat";
			
	public Preferences() {
		populateMaps();
	}
	
	private void populateMaps() {
		appNames = new HashMap<String, String>();
		
		try (Stream<String> stream = Files.lines(Paths.get(APP_NAME_FILE_PATH))) {
			stream.forEach( nextLine -> { addToNameMap(nextLine); } );

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addToNameMap(String line) {
		int atLine = appNames.size() + 1;
		if (!line.startsWith("#")) {
			String[] name = line.split("=");
			appNames.put(name[0], name[1]);
		} else {
			appNames.put("comment@" + atLine, line);
		}
	}
	
	public String getTranslatedAppName(String name) {
		return appNames.get(name);
	}
	
	public void addTranslationForApp(String realName, String translatedName) {
		appNames.put(realName, translatedName);
		
		//TODO Something smart to keep the comments in the file (maybe also empty lines?)
		StringBuilder sb = new StringBuilder();
		for(String key:appNames.keySet()) {
			sb.append(key);
			sb.append("=");
			sb.append(appNames.get(key));
			sb.append("\n");
		}
		
		Path path = Paths.get(APP_NAME_FILE_PATH);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
		    writer.write( sb.toString() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public Map<String, String> getMap() {
		return appNames;
	}
}
