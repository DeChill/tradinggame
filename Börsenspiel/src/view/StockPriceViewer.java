package view;

import java.util.Locale;
import java.util.TimerTask;
import java.awt.Font;
import java.text.NumberFormat;
import javax.swing.*;

import core.Share;
import core.StockPriceInfo;
import core.UpdateTimer;

public class StockPriceViewer extends JFrame {

	private UpdateTimer updateTimer = UpdateTimer.getInstance();

	public void startUpdate(StockPriceInfo info) {
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

	}

	public void update(StockPriceInfo info) {
		Share[] shareSnapShot = info.getAllSharesAsSnapShot();

		StringBuilder buff = new StringBuilder();
		buff.append("<html><table>");
		for (int i = 0; i < shareSnapShot.length; i++) {
			long doublePrice = shareSnapShot[i].getPrice();
			NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY);
			String s = n.format(doublePrice / 100.0);

			buff.append(String.format(
					"<tr><td align='right'>%s</td><td></td><td>%s</td></tr>",
					shareSnapShot[i].getName(), s));
		}

		buff.append("</table></html>");

		Label.setText(buff.toString());
	}

}