package de.hsaugsburg.GUI;




import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import de.hsaugsburg.core.AccountManager;
import de.hsaugsburg.core.Player;
import de.hsaugsburg.scanner.CommandScanner;
import de.hsaugsburg.scanner.StockGameCommandProcessor;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
	
	private static StockGameCommandProcessor cp;
	private static AccountManager am;


	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        Label result = new Label("RESULT DUMMY");
        gridPane.add(result, 1, 3);
        
        
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
        primaryStage.setTitle("Börsenspiel");
        primaryStage.show();  
        
        
        
       
        	   
        
        
        button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				String s = textField.getText();
				System.out.println("("+s+")");
				System.out.println(cp);
				result.setText(cp.process(s));
//				gridPane.add((addPlayerSection(s.substring(4))), 0, i++);
				textField.setText("");
			}

			
        	
        });
        
        
        
        
        

    
        
	}
	
//	protected GridPane addPlayerSection(String s) {
//		GridPane gridPane = new GridPane();
//		Button playerButton = new Button(s);
////		System.out.println("("+s+")");
//		gridPane.add(playerButton, 0, 0);
//		
//		playerButton.setOnAction(evt -> playerchoice());
//		return gridPane;
//	}
//
//	private void playerchoice() {
//		Stage stage = new Stage();
//		   HBox hBox = new HBox();
//		   Label label = new Label(msg);
//		   hBox.getChildren().add(label);
//		   hBox.setPadding(new Insets(25, 25, 25, 25));
//		   stage.setScene(new Scene(hBox));
//		   stage.setTitle("My modal window");
//		   stage.initModality(Modality.WINDOW_MODAL);
//		   stage.initOwner(primaryStage.getScene().getWindow());
//		   stage.show();  
//		
//	}

	public static void setUp(StockGameCommandProcessor cp, AccountManager am){
		GUI.cp = cp;
		GUI.am = am;
		Application.launch();
	}
	


	
	
	

	
//	public void launch(){
//		Application.launch();
//		
//	}

}
