package view;

import java.util.Locale;
import java.util.TimerTask;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.*;

import core.Player;
import core.UpdateTimer;

public class PlayerViewer extends JFrame {
	
	private Player player;
	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	private JLabel Label;
	
	public PlayerViewer(Player player){
		this.player = player;
		
		Label = new JLabel("Loading...", JLabel.CENTER);
		Label.setFont(new Font("Arial", Font.BOLD, 20));
		add(Label);
		Label.setVerticalAlignment(JLabel.TOP);
		Label.setHorizontalTextPosition(JLabel.RIGHT);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setVisible(true);
	}
	
	

	public void startUpdate() {
		updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {

			public void run() {
				update();
			}

		}, 2000, 1000);
	}
	
	public void update() {

		StringBuilder buff = new StringBuilder();
		buff.append("<html>");
		for (int i = 0; i < player.getShareDespositAccount().getShareItems().length; i++) {
		buff.append(player.getShareDespositAccount().getShareItems()[i].toString());
		buff.append("<br>");
	
		}
		buff.append("<br>");
		buff.append("Kontostand: " + player.getPlayerCash().toString());
		buff.append("<br>");
		buff.append("<br>");
		buff.append("Gesammtwert: " + (player.getPlayerCash().getValue() + player.getPlayerShares().getValue()));
		buff.append("<br>");
		buff.append("</html>");

		Label.setText(buff.toString());
	}

}
