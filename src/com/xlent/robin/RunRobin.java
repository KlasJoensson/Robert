package com.xlent.robin;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xlent.robin.commands.Command;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is for making use of the little robot Robin.
 * 
 * @author Klas Jönsson
 */
public class RunRobin extends Application {

	private TreeView<TreeItem<String>> commandTreeView;
	private ListView<Command> commandListView;
	private List<Command> commandList = new ArrayList<>();
	private CommandFactory commandFactory = new CommandFactory();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Robert - your RPA friend");
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));
		pane.setBottom(getButtonPane());
		
		commandListView = new ListView<Command>();
		commandTreeView = getCommandView();
		HBox paneCenter = new HBox();
		paneCenter.setSpacing(5);
		pane.setCenter(paneCenter);
		paneCenter.getChildren().add(commandTreeView);
		paneCenter.getChildren().add(getMoveBtnPane());
		paneCenter.getChildren().add(commandListView);
		
		Scene scene = new Scene(pane, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox getMoveBtnPane() {
		VBox btnBox = new VBox();
		Button addBtn = new Button(">>");
		addBtn.setPrefSize(50, 30);
		addBtn.setOnAction(addBtnEventHandler);
		btnBox.getChildren().add(addBtn);

		Button removeBtn = new Button("<<");
		removeBtn.setPrefSize(50, 30);
		removeBtn.setOnAction(removeBtnEventHandler);
		btnBox.getChildren().add(removeBtn);
		
		// TODO Move to its panel when it exist
		Button editBtn = new Button("Edit");
		editBtn.setPrefSize(50, 30);
		editBtn.setOnAction(editBtnEventHandler);
		btnBox.getChildren().add(editBtn);
		
		return btnBox;
	}
	
	private HBox getButtonPane() {
		Button saveBtn = new Button("Save");
		saveBtn.setOnAction(saveBtnEventHandler);
		Button openBtn = new Button("Open");
		openBtn.setOnAction(openBtnEventHandler);
		Button runBtn = new Button("Run");
		runBtn.setOnAction(runBtnEventHandler);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(15, 12, 15, 12));
	    hBox.setSpacing(10);
	    hBox.setStyle("-fx-background-color: #336699;");
		hBox.getChildren().addAll(saveBtn, openBtn, runBtn);
		
		return hBox;
	}
	
	private TreeView<TreeItem<String>> getCommandView() {
		TreeView commandView = new TreeView();
		TreeItem rootItem = new TreeItem("Commands");
		
		HashMap<String, ArrayList<String>> commands = Robin.getCommandsAsTree();
		ArrayList<TreeItem> commandTree = new ArrayList<>();
		TreeItem headerItem;
		for(String header:commands.keySet()) {
			headerItem = new TreeItem(header);
			headerItem.getChildren().addAll(getItemFromList(commands.get(header)));
			rootItem.getChildren().add(headerItem);
		}
		
		commandView.setRoot(rootItem);
		
		return commandView;
	}
	
	private List<TreeItem> getItemFromList(List<String> list) {
		List<TreeItem> commandList = new ArrayList<>();
		list.stream().forEach(name -> commandList.add(new TreeItem(name)));
		
		return commandList;
	}
	
	private EventHandler<ActionEvent> saveBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not implemnetd");
			alert.setHeaderText("Save button");
			alert.setContentText("This button hasn't been implemneted yet");
			alert.showAndWait();
		}
	};
	
	private EventHandler<ActionEvent> openBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not implemnetd");
			alert.setHeaderText("Open button");
			alert.setContentText("This button hasn't been implemneted yet");
			alert.showAndWait();
		}
	};
	
	private EventHandler<ActionEvent> runBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Not implemnetd");
			alert.setHeaderText("Run button");
			alert.setContentText("This button hasn't been implemneted yet");
			alert.showAndWait();
		}
	};
	
	private EventHandler<ActionEvent> addBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			TreeItem selected = commandTreeView.getSelectionModel().getSelectedItem();
			if (selected != null && selected.getChildren().isEmpty()) {
				String commandName = (String) selected.getValue();
				try {
					Command command = commandFactory.createCommand(commandName);
					commandListView.getItems().add(command);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	private EventHandler<ActionEvent> removeBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			int index = commandListView.getSelectionModel().getSelectedIndex();
			if (index >= 0)
				commandListView.getItems().remove(index);
		}
	};
	
	private EventHandler<ActionEvent> editBtnEventHandler = new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			Command command = commandListView.getSelectionModel().getSelectedItem();
			if (command != null) {
				Stage editWindow = new EditStage(command);
				editWindow.show();		
			}
				
		}
	};
	
	public static void main(String[] args) {
		launch(args);
	}
}
