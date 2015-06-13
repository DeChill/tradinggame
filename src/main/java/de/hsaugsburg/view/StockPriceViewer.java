package de.hsaugsburg.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.*;

import de.hsaugsburg.core.Share;
import de.hsaugsburg.core.StockPriceInfo;
import de.hsaugsburg.core.UpdateTimer;

public class StockPriceViewer extends JFrame {

	private UpdateTimer updateTimer = UpdateTimer.getInstance();
	private Locale currentLocale;

	public void startUpdate(final StockPriceInfo info) {
		updateTimer.getTimer().scheduleAtFixedRate(new TimerTask() {

			public void run() {
				update(info);
			}

		}, 2000, 1000);
	}

	public Share[] getShareRates(StockPriceInfo info) {
		return (info.getAllSharesAsSnapShot());
	}

	private JLabel Label;

	public StockPriceViewer() {
		Label = new JLabel("Loading...", JLabel.CENTER);
		Label.setFont(new Font("Arial", Font.BOLD, 33));
		add(Label);
		Label.setVerticalAlignment(JLabel.TOP);
		Label.setHorizontalTextPosition(JLabel.LEFT);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setVisible(true);
		
		this.currentLocale = new Locale("en", "us");

	}

	public void update(StockPriceInfo info) {
		
		List<Share> shareList = Arrays.asList(info.getAllSharesAsSnapShot());

		StringBuilder buff = new StringBuilder();
		buff.append("<html><table>");
		
		
		NumberFormat n = NumberFormat.getCurrencyInstance(currentLocale);
		
		
		shareList.forEach(share ->  buff.append(String.format(
				"<tr><td align='right'>%s</td><td></td><td>%s</td></tr>",
				share.getName(), n.format(share.getPrice() / 100.0))));
		


		buff.append("</table></html>");

		Label.setText(buff.toString());
	}

}