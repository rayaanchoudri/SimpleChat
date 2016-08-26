import java.awt.List;
import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

import javax.xml.stream.events.Namespace;

import java.util.HashMap;

public class Server implements Runnable{

	/**
	 * Class variables
	 */
		private ServerSocket serversocket;
		
		private ArrayList<ConnectedClient> connectedClients;
		
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
			
			Thread host = new Thread(new Server(17));
			host.start();
			
		}
	public Server(int port) throws UnknownHostException, IOException {
		this.serversocket = new ServerSocket(port);
		this.connectedClients = new ArrayList<ConnectedClient>();
		
		
	}
	public void run(){
	
		while(true){//wait and accepts client
			try {
				Socket socket = serversocket.accept();
				Scanner INPUT = new Scanner(socket.getInputStream());
				String name = INPUT.nextLine();
		        this.connectedClients.add(new ConnectedClient(socket, name));
				System.out.println("Client " + name  +" is connected.");
				updateClients();
				
				//ServerChatManager manager = new ServerChatManager(socket);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			

				//send();
				//receive();
		
			
		}
	
	}
	public void updateClients() throws IOException
    {
        
        for(int i = 0; i < connectedClients.size(); i++)
        {
        	PrintWriter client_out = new PrintWriter(connectedClients.get(i).getOutStream());
        	String names = "";
        	for(int j = 0; j<connectedClients.size(); j++){
        		names = names.concat(connectedClients.get(j).getName()+",");
        		
        	}
        	System.out.println(names);
        	client_out.println("NEWCLIENTSIG!" + names);
        	client_out.flush();
        }
    }
	public ConnectedClient getClient(int i){
		return connectedClients.get(i);
		
	}
	/**
	 * Sends message to server
	 * @throws IOException 
	 */
		/*private void send() throws IOException{ 
			Scanner s = new Scanner(System.in);
			PrintStream toClient = new PrintStream(socket.getOutputStream());
			if(s.hasNext()){
				String message = s.nextLine();
				if(message!=null)
					toClient.println("Server says: " + message + ".");
			}
		}*/
		
	/**
	 * Reads message from client 
	 * @throws IOException 
	 */
		/*private void receive() throws IOException{
			InputStreamReader ir = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(ir);
			String message = br.readLine();
			if(message!=null)
				System.out.println(message);
		}*/

	
}
