import java.net.Socket;
import java.util.ArrayList;

public class ServerChatManager implements Runnable {
	private ArrayList<Socket> chats;
	public ServerChatManager(Socket manager) {
		chats = new ArrayList();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
