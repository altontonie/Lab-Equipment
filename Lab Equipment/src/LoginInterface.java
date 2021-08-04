import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants; 

public class LoginInterface extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public static int User_Id;
	public String User_Role;
	private String currentDate;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInterface frame = new LoginInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginInterface() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date(); 
	    currentDate = formatter.format(date);
		
		setTitle("Lab Management System - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 684, 44);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Lab Management System");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 28));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(283, 43, 401, 374);
		contentPane.add(panel_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(81, 136, 89, 14);
		panel_1.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(180, 133, 173, 20);
		panel_1.add(usernameField);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(81, 167, 89, 14);
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 164, 173, 20);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Pinket");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBounds(638, 360, 36, 14);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(Color.PINK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setBackground(Color.LIGHT_GRAY);
			}
		});
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usernameField.getText().length()!=0 && passwordField.getText().length()!=0) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "Select * from logindb where Username='"+usernameField.getText()+"' and Password='"+passwordField.getText().toString()+"'";
						ResultSet rs= stmt.executeQuery(sql);
						
						while(rs.next()) {
							User_Id = rs.getInt("User_Id");
						}
						if(User_Id==0) {
							JOptionPane.showMessageDialog(null, "Invalid user account");
						}else {
							sql = "UPDATE logindb SET Logged_In='"+currentDate+"' where User_Id="+User_Id+" ";
							stmt.executeUpdate(sql);
							ServerConnection server = new ServerConnection();
							dispose();
												
							conn.close();
						}
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Username and Password cannot be empty");
								
			}
		});
		loginBtn.setBounds(283, 11, 116, 23);
		panel_2.add(loginBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 43, 283, 374);
		contentPane.add(panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 7));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Alton-laptop\\Desktop\\java\\Projects\\Lab Equipment\\src\\183-mro-developing-a-sourcing-strategy-for-lab-consumables (2).jpg"));
		lblNewLabel_3.setBounds(0, 0, 283, 374);
		panel_3.add(lblNewLabel_3);
	}
}
