import javax.swing.JOptionPane;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {

	private int UserIP;
	public Client() {
		
		
	}
	private void askUserIP(){
		
		String f = JOptionPane.showInputDialog("What is your IP address");
		this.UserIP = Integer.parseInt(f);
	}
	
	public void runClient() throws UnknownHostException, IOException{
		
		//establish connection
		Socket sock1 = new Socket("localhost", 222);
		
		//write to server
		PrintStream toServer = new PrintStream(sock1.getOutputStream());
		Scanner s = new Scanner(System.in);
		String message = s.nextLine();
		toServer.println(message);
		
		//read from server and print
		InputStreamReader serverIn = new InputStreamReader(sock1.getInputStream());
		BufferedReader br = new BufferedReader(serverIn);
		String echomessage = br.readLine();
		System.out.println(echomessage);
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Client client1 = new Client(); 
		client1.runClient();
	}
	
}