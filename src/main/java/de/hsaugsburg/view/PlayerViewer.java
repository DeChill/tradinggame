package de.hsaugsburg.view;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimerTask;

import java.text.NumberFormat;

import javafx.*;
import javafx.application.Platform;
import de.hsaugsburg.core.Player;
import de.hsaugsburg.core.UpdateTimer;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class PlayerViewer {
	
	private Player player;
	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	private Label label;
	private ResourceBundle language;
	
	public PlayerViewer(Player player){
		this.player = player;
		this.language = player.getLanguage();
		
		label = new Label("Loading...");
		



	}
	
	

	
	
	public void update() {

		StringBuilder buff = new StringBuilder();
		
		for (int i = 0; i < player.getShareDespositAccount().getShareItems().length; i++) {
		buff.append(player.getShareDespositAccount().getShareItems()[i].toString());
		buff.append("\r\n");
	
		}
		buff.append("\r\n");
		String balance = language.getString("balance");
		buff.append(balance + player.getPlayerCash().toString());
		buff.append("\r\n");
		buff.append("\r\n");
		String total = language.getString("total");
		buff.append(total + (player.getPlayerCash().getValue() + player.getPlayerShares().getValue()));
		buff.append("\r\n");
		

		label.setText(buff.toString());
		
	}
	
//	public void StartUpdate(){
//		Platform.runLater(() -> {
//	
//	        updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {
//
//	            @Override
//	            public void run() {
//	                update();
//
//	            }
//	        }, 1000, 1000);
//	    });
//	}
	
	public Label getLabel(){
		return label;
	}
	
	

}
