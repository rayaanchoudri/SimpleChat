import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectedClient {
	private Socket socket;
	private String name;
	private OutputStream out;
	public ConnectedClient(Socket socket, String name) throws IOException {
		this.name=name;
		this.socket=socket;
		this.out = socket.getOutputStream();
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	public String getName(){
		return this.name;
	}
	
	public OutputStream getOutStream(){
		return out;
	}
}
