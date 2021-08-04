import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ModifyUserInterface extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField lnameField;
	private JTextField fnameField;
	private JTextField emailField;
	
	private static String first_name;
	private static String last_name;
	private static String mail;
	private static String pwd;
	private static String user_name;
	public static int User_Id;
	private JTextField IDField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ModifyUserInterface frame = new ModifyUserInterface();
				frame.setVisible(true);
			}
		});
	}
	
	public void refreshTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select User_Id, First_Name, Last_Name, Username, Email, Password from logindb ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	/**
	 * Create the frame.
	 */
	public ModifyUserInterface() {

		setTitle("Update Information");
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
		panel_1.setBounds(0, 44, 684, 374);
		contentPane.add(panel_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(10, 158, 65, 14);
		panel_1.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(77, 152, 102, 20);
		usernameField.setText(user_name);
		panel_1.add(usernameField);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 189, 65, 14);
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(77, 214, 102, 20);
		passwordField.setText(pwd);
		panel_1.add(passwordField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(77, 121, 102, 20);
		lnameField.setText(last_name);
		panel_1.add(lnameField);
		
		fnameField = new JTextField();
		fnameField.setColumns(10);
		fnameField.setBounds(77, 90, 102, 20);
		fnameField.setText(first_name);
		panel_1.add(fnameField);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(10, 127, 65, 14);
		panel_1.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(10, 96, 65, 14);
		panel_1.add(lblFirstName);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(77, 183, 102, 20);
		emailField.setText(mail);
		panel_1.add(emailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(10, 220, 65, 14);
		panel_1.add(lblNewLabel_1_1);
		
		IDField = new JTextField();
		IDField.setText((String) null);
		IDField.setColumns(10);
		IDField.setBounds(77, 59, 102, 20);
		panel_1.add(IDField);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(10, 65, 65, 14);
		panel_1.add(lblUserId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 11, 485, 323);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select User_Id as ID, First_Name, Last_Name, Username, Email, Password, Logged_In as Online from logindb ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IDField.getText().length()!=0 && passwordField.getText().length()!=0 && usernameField.getText().length()!=0 && +emailField.getText().length()!=0 && lnameField.getText().length()!=0 && fnameField.getText().length()!=0) {
					int temp_user=0;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "Select User_Id from logindb where User_Id="+IDField.getText()+"";
						ResultSet rs= stmt.executeQuery(sql);
						while(rs.next()) {
							temp_user = rs.getInt("User_Id");
						}
						if(temp_user!= 0 && temp_user== Integer.parseInt(IDField.getText())) {
							sql = "UPDATE logindb SET First_Name='"+fnameField.getText()+"',Last_Name='"+lnameField.getText()+"',"
									+ " Email='"+emailField.getText()+"' , Username='"+usernameField.getText()+"', Password='"+passwordField.getText().toString()+"' WHERE User_Id='"+IDField.getText()+"'";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Information Updated");
							conn.close();
							refreshTable();
						}else {
							JOptionPane.showMessageDialog(null, "Invalid User");
							conn.close();
						}
					
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Please Enter all required Fields");
				
				
			}
		});
		updateBtn.setBounds(270, 11, 133, 23);
		panel_2.add(updateBtn);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminInterface ad = new AdminInterface();
				ad.setVisible(true);
				dispose();
			}
		});
		cancelBtn.setBounds(10, 11, 89, 23);
		panel_2.add(cancelBtn);
	}
}
