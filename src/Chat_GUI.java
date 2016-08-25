import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chat_GUI implements Runnable {

	private JFrame frame;
	private JTextField msgField;
	private JTextArea msgArea;
	private JButton sendButton;
	private JScrollPane scrollPane;
	private String message;


	public void run() {
			Chat_GUI window = new Chat_GUI();
			window.frame.setVisible(true);
	
	}

	/**
	 * Create the application.
	 */
	public Chat_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		EventHandler handler = new EventHandler();
		frame = new JFrame(getName());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//send button
		sendButton = new JButton("Send");
		sendButton.setBackground(new Color(0, 255, 0));
		sendButton.setBounds(323, 215, 97, 25);
		sendButton.addActionListener(handler);
		frame.getContentPane().add(sendButton);
		
		//user msg input field
		msgField = new JTextField();
		msgField.setBounds(12, 216, 299, 24);
		frame.getContentPane().add(msgField);
		msgField.setColumns(10);
		
		//messaging area
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 299, 182);
		frame.getContentPane().add(scrollPane);
		
		msgArea= new JTextArea();
		scrollPane.setViewportView(msgArea);
		msgArea.setEditable(false);
		msgArea.setLineWrap(true);
		
		
	}
	public class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==sendButton){
				
			}
			
		}
		
	}
	public String getMessage(){
		return message;
	}
	private void displayMessage(){
		
	}
	public String getName(){
		return null;
	}

}
