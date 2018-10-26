package com.xlent.robin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xlent.robin.Robin.ModifierKey;
import com.xlent.robin.commands.Command;
import com.xlent.robin.commands.PressArrowKey;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditStage extends Stage {

	private Command command;

	public EditStage(Command command) {
		this.command = command;

		BorderPane editLayout = new BorderPane(); 
		editLayout.setPadding(new Insets(10));
		Map<String, Object> args = command.getArgunments();
		List<Object> values = new ArrayList<>();
		HBox pane;
		VBox argPane = new VBox();
		argPane.setPadding(new Insets(5));
		for (String arg:args.keySet()) {
			pane = new HBox();
			pane.setPadding(new Insets(2));
			Object argObj = args.get(arg);
			if(argObj instanceof String || argObj instanceof Integer || argObj instanceof Character) {
				TextField field = new TextField();
				field.setText(argObj.toString());
				pane.getChildren().add(new Label(arg));
				pane.getChildren().add(field);
				values.add(field);
			} else if (argObj instanceof ModifierKey) {
				ComboBox<String> modifiers = new ComboBox<>();
				Map<String, ModifierKey> modifierKeys = Robin.getModifierKeys();
				modifiers.getItems().addAll( modifierKeys.keySet() );
				for(String key:modifierKeys.keySet()) {
					if(modifierKeys.get(key).equals(argObj)) {
						modifiers.setValue(key);
					}
				}
				pane.getChildren().add(new Label("Modifier Key"));
				pane.getChildren().add(modifiers);
				values.add(modifiers);
			} else if (argObj instanceof PressArrowKey.ArrowKey) {
				ComboBox<String> modifiers = new ComboBox<>();
				Map<String, Object> argAlt = command.getArgumentAlternetivs();
				modifiers.getItems().addAll( argAlt.keySet() );
				for(String key:argAlt.keySet()) {
					if(argAlt.get(key).equals(argObj)) {
						modifiers.setValue(key);
					}
				}
				pane.getChildren().add(new Label("Arrow Key"));
				pane.getChildren().add(modifiers);
				values.add(modifiers);
			} else {
				pane.getChildren().add(new Label(arg));
				pane.getChildren().add(new Label(argObj!=null?argObj.toString():"n/a"));
			}
			
			
			argPane.getChildren().add(pane);
		}
		editLayout.setCenter(argPane);

		HBox controlPane = new HBox();
		Button editSave = new Button("Save");
		editSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Map<String, Object> args = new HashMap<>();
				String name = "";
				String value = "";
				for(Node row:argPane.getChildren()) {
					if(row instanceof HBox) {					
						for(Node cell:((HBox) row).getChildren()) {
							if(cell instanceof Label) {
								name = ((Label)cell).getText();
							} else if (cell instanceof TextField) {
								value = ((TextField)cell).getText();
							} else if (cell instanceof ComboBox) {
								value = ((ComboBox)cell).getValue().toString();							
							} else {
								System.out.println("Save not imlemented for type: " + cell.getClass() );
								name = "";
								value = "";
							}
							args.put(name, value);
						}
					}
				}
				command.changeParameters(args);
				close();
			}
		});
		controlPane.getChildren().add(editSave);
		Button editCancel = new Button("Cancel");
		editCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});
		controlPane.getChildren().add(editCancel);

		editLayout.setBottom(controlPane);

		Scene editScene = new Scene(editLayout, 250, 200);
		
		setTitle("Edit " + command.getName() );
		setScene(editScene);
	}
}
