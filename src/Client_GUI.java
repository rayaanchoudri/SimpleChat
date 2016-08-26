import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Client_GUI{

	private JFrame frame;
	private JTextField ipField;
	private JTextField portField;
	private static JButton connectButton;
	private JLabel port;
	private JTextField nameField;
	private JLabel Name;
	private JLabel IpAddress;
	/**
	 * Launch the application.
	 */
					
	public static void main(String[] args) {
		
		Client_GUI window = new Client_GUI();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Client_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		EventHandler eventHandler = new EventHandler();
		frame = new JFrame("Connect to server:");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		IpAddress = new JLabel("IP Address: ");
		IpAddress.setBounds(47, 63, 70, 16);
		frame.getContentPane().add(IpAddress);
		
		ipField = new JTextField();
		ipField.setBounds(117, 60, 116, 22);
		frame.getContentPane().add(ipField);
		ipField.setColumns(10);
		
		port = new JLabel("Port:");
		port.setBounds(78, 98, 28, 16);
		frame.getContentPane().add(port);
		
		portField = new JTextField();
		portField.setBounds(117, 95, 116, 22);
		frame.getContentPane().add(portField);
		portField.setColumns(10);
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(eventHandler);
		connectButton.setBackground(new Color(127, 255, 0));
		connectButton.setForeground(new Color(255, 250, 250));
		connectButton.setBounds(269, 129, 97, 25);
		frame.getContentPane().add(connectButton);
		
		nameField = new JTextField();
		nameField.setBounds(117, 130, 116, 22);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		Name = new JLabel("Name:");
		Name.setBounds(66, 133, 44, 16);
		frame.getContentPane().add(Name);
	}
	
	private class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==connectButton){
				//if(ipField.getText()=="localhost"){
					int portnum = Integer.parseInt(portField.getText());
					String name = nameField.getText();
					try {
						Client client = new Client("localhost", portnum, name);
						PrintWriter OUT = new PrintWriter(client.getOUT());
				        OUT.println(name);
				        OUT.flush();
						Thread newclient = new Thread(client);
						newclient.start();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				//}
				/*else{
					try {
			
						InetAddress ip = InetAddress.getByName(ipField.getText());
						int portnum = Integer.parseInt(portField.getText());
						String name = nameField.getText();
						try {
							//Thread client = new Thread(new Client(ip, portnum, name));
							//client.start();
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
				}*/
			}
		}
		
	}

}
