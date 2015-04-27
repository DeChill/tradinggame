import java.io.*;
import java.net.*;

class client_timo_wilhelm {
	public static void main(String argv[]) throws Exception {

		int calc = 0;
		String modifiedSentence = null;
		String[] split = null;
		String res = null;
		String header = "This is STUPID/1.0\r\nName: Timo Wilhelm\r\n\r\n";

		Socket clientSocket = new Socket("141.82.57.23", 12810);

		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		outToServer.writeBytes(header);

		while (!(modifiedSentence = inFromServer.readLine()).equals("")) {

			System.out.println(modifiedSentence);
			if (modifiedSentence.contains("Number:")) {
				split = modifiedSentence.split(" ");
			}
		}
		
		calc = Integer.parseInt(split[1]) * 42;
		
		
		res = "This is STUPID/1.0\r\nResult: " + calc + "\r\n\r\n";
		
		outToServer.writeBytes(res);

		while (!(modifiedSentence = inFromServer.readLine()).equals("")) {

			System.out.println(modifiedSentence);
		}
		
		clientSocket.close();
	}
}