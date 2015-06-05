package de.hsaugsburg.GUI;




import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.*;
import javafx.event.*;



public class GUI extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        Label label = new Label("Action:");
        gridPane.add(label, 1, 1);
        final TextField textField = new TextField("");
        gridPane.add(textField,2,1);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        final Button button = new Button("DO IT");
        hBox.getChildren().add(button);
        gridPane.add(hBox,2,2);
        
        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();  
        
        scene.addEventHandler(Event.ANY, new EventHandler() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				
			}   
        	
        }
	}
	
	public void launch(){
		Application.launch();
	}

}
