package com.xlent.robin;

import java.io.IOException;
import java.nio.file.Files;
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
	
	public Preferences() {
		populateMaps();
	}
	
	private void populateMaps() {
		appNames = new HashMap<String, String>();
		String appNamesFile = "./resources/appNames.dat";
		
		try (Stream<String> stream = Files.lines(Paths.get(appNamesFile))) {
			
			stream.forEach( nextLine -> addToNameMap(nextLine) );

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addToNameMap(String line) {
		if (!line.startsWith("#")) {
			String[] name = line.split("=");
			appNames.put(name[0], name[1]);
		}
	}
	
	public String getTranslatedAppName(String name) {
		return appNames.get(name);
	}
	
	public void addTranslationForApp(String realName, String translatedName) {
		appNames.put(realName, translatedName);
		// TODO save it to the file as well!
	}
	
	public Map<String, String> getMap() {
		return appNames;
	}
}
