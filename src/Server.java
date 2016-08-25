import java.awt.List;
import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;
import java.util.HashMap;

public class Server implements Runnable{

	/**
	 * Class variables
	 */
		private ServerSocket serversocket;
		
		private static ArrayList<Socket> clients; 
		private static ArrayList<String> client_names;
		
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
			
			Thread host = new Thread(new Server(17));
			
			host.start();
			
		}
	public Server(int port) throws UnknownHostException, IOException {
		this.serversocket = new ServerSocket(port);
		this.clients = new ArrayList<Socket>();
		this.client_names = new ArrayList<String>();
		
	}
	public void run(){
	
		while(true){//wait and accepts client
			try {
				Socket socket = serversocket.accept();
				Scanner INPUT = new Scanner(socket.getInputStream());
				String name = INPUT.nextLine();
		        client_names.add(name);
				clients.add(socket);
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
	public static void updateClients() throws IOException
    {
        
        
        for(int i = 0; i < Server.clients.size(); i++)
        {
        	OutputStream o = clients.get(i).getOutputStream();
            PrintWriter OUT = new PrintWriter(o);
            OUT.println("NEWCLIENTSIG!");
            OUT.flush();
            ObjectOutputStream objectOutput = new ObjectOutputStream(o);
            objectOutput.writeObject(client_names);
          
        }
    }
	public static Socket getClient(int i){
		return clients.get(i);
		
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
