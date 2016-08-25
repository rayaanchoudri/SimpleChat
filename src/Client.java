import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Client implements Runnable {

	private JFrame frame;
	private JList list;
	private JScrollPane scrollPane;
	public static ArrayList<String> UsersOnline = new ArrayList<String>();
	private static int client_count = 0;
	private JButton chatButton;
	private int client_ID;
	private String name;
	private Socket socket;
	private Scanner IN;
	private PrintWriter OUT;
	
	
	/**
	 * Create the application.
	 */
	public Client(String host, int port, String name) throws UnknownHostException, IOException {
		
		this.socket = new Socket(host, port);
		//this.socket = new Socket(IP, port); must conver to inetaddress, do later
		this.name = name;
		//UsersOnline.add(name); //could do this but wanna control from server
		client_count++;
		this.client_ID=client_count;
		IN = new Scanner(socket.getInputStream());
        OUT = new PrintWriter(socket.getOutputStream());
        OUT.println(name);
        OUT.flush();
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame(name);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 265, 227);
		frame.getContentPane().add(scrollPane);
		list= new JList();
		scrollPane.setViewportView(list);
		//list.setListData(UsersOnline);
		chatButton = new JButton("Start Chat");
		chatButton.setBackground(new Color(0, 255, 0));
		chatButton.setBounds(305, 215, 97, 25);
		frame.getContentPane().add(chatButton);
		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = list.getSelectedIndex();
				startChat(i);			}
		});
		frame.setVisible(true);
		
	}
	
	public void updateClientList() throws IOException, ClassNotFoundException{
		ObjectInputStream o = new ObjectInputStream(socket.getInputStream());
		Object temp = o.readObject();
		UsersOnline = (ArrayList<String>)temp;
		for(int i =0;i<UsersOnline.size();i++)
			System.out.println(UsersOnline.get(i));
		
		String [] NAMES = new String[UsersOnline.size()];
		NAMES = UsersOnline.toArray(NAMES);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(list);
		list.setListData(NAMES);
	}
	@Override
	public void run() {
		while(true){
			try {
				InputStream in = socket.getInputStream();
				IN = new Scanner(in);
				OutputStream out = socket.getOutputStream();
		        OUT = new PrintWriter(out);
		        OUT.flush();
				String [] NAMES = new String[UsersOnline.size()];
				NAMES = UsersOnline.toArray(NAMES);
				frame.getContentPane().add(scrollPane);
				scrollPane.setViewportView(list);
				list.setListData(NAMES);
		        if(IN.hasNext())
		        {
		            String MESSAGE = IN.nextLine();
		            IN.nextLine();
		
		            if(MESSAGE.contains("NEWCLIENTSIG!"))
		            {
		            	System.out.println(MESSAGE);
		                updateClientList();
		            }
		          
		        }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void startChat(int client_ID){
		
	}

}
