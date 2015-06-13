package de.hsaugsburg.GUI;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hsaugsburg.core.AccountManager;
import de.hsaugsburg.core.Player;
import de.hsaugsburg.core.Share;
import de.hsaugsburg.core.UpdateTimer;
import de.hsaugsburg.scanner.StockGameCommandProcessor;
import de.hsaugsburg.view.PlayerViewer;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.*;

public class PlayerGUI extends Stage {

	private static Player player;
	private static AccountManager am;
	private String selectedShare;
	private UpdateTimer updateTimer = UpdateTimer.getInstance();

	public PlayerGUI(Player player, AccountManager am) {
		this.player = player;
		this.am = am;
		GridPane gridPane = new GridPane();

		ToggleGroup toggleGroup = new ToggleGroup();

		List<Share> shareList = Arrays.asList(am.getAllShares());

		VBox shareSelectionBox = new VBox();
		shareList.forEach(share -> {
			RadioButton radioButton = new RadioButton(share.getName());
			radioButton.setUserData(share.getName());
			shareSelectionBox.getChildren().add(radioButton);
			radioButton.setToggleGroup(toggleGroup);

		});

		toggleGroup.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {
					public void changed(ObservableValue<? extends Toggle> ov,
							Toggle toggle, Toggle new_toggle) {
						if (new_toggle != null)
							selectedShare = (String) toggleGroup
									.getSelectedToggle().getUserData();
					}
				});

		TextField amount = new TextField("");
		gridPane.add(amount, 2, 3);
		Label label1 = new Label("amount:");
		gridPane.add(label1, 1, 3);

		Button buyShares = new Button("Buy");
		buyShares.setOnAction(evt -> {
			try {
				am.buyShares(player.getName(), selectedShare,
						Integer.parseInt(amount.getText()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		gridPane.add(buyShares, 3, 3);

		Button sellShares = new Button("Sell");
		sellShares.setOnAction(evt -> {
			try {
				am.sellShares(player.getName(), selectedShare,
						Integer.parseInt(amount.getText()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		gridPane.add(sellShares, 3, 4);

		gridPane.add(shareSelectionBox, 0, 0);

		PlayerViewer pv = new PlayerViewer(player);
		gridPane.add(pv.getLabel(), 1, 5);
		updatePlayerViewer(pv);
		

		

		Scene scene = new Scene(gridPane, 600, 500);
		this.setScene(scene);
		this.setTitle(player.getName());
		this.show();

	}

	// public static void setUp(Player player, AccountManager am){
	// PlayerGUI.player = player;
	// PlayerGUI.am = am;
	// PlayerGUI();
	//
	// }
	public void	updatePlayerViewer(PlayerViewer pv){
	 Timer timer = updateTimer.getTimer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        
	        public void run() {
	            Platform.runLater(() -> {
	                pv.update();
	                
	            });
	        }
	    }, 1000, 1000);
	}

}
