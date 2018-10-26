package com.xlent.robin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xlent.robin.commands.Command;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Map<String, Object> args = command.getArgunments();
		List<Object> values = new ArrayList<>();
		HBox pane;
		VBox argPane = new VBox();
		for (String arg:args.keySet()) {
			pane = new HBox();
			pane.getChildren().add(new Label(arg));
			TextField field = new TextField();
			field.setText(args.get(arg).toString());
			pane.getChildren().add(field);
			values.add(field);
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

		Scene editScene = new Scene(editLayout, 230, 100);

		setTitle("Edit " + command.getName() );
		setScene(editScene);
	}
}
