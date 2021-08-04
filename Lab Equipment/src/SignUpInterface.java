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
import javax.swing.JComboBox;

public class SignUpInterface extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField lnameField;
	private JTextField fnameField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpInterface frame = new SignUpInterface();
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
	public SignUpInterface() {
		setTitle("User Creation");
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
		lblUsername.setBounds(219, 171, 89, 14);
		panel_1.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(318, 168, 173, 20);
		panel_1.add(usernameField);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(219, 202, 89, 14);
		panel_1.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(318, 230, 173, 20);
		panel_1.add(passwordField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(318, 137, 173, 20);
		panel_1.add(lnameField);
		
		fnameField = new JTextField();
		fnameField.setColumns(10);
		fnameField.setBounds(318, 106, 173, 20);
		panel_1.add(fnameField);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(219, 140, 89, 14);
		panel_1.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(219, 109, 89, 14);
		panel_1.add(lblFirstName);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(318, 199, 173, 20);
		panel_1.add(emailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(219, 233, 89, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton createBtn = new JButton("Create User");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passwordField.getText().length()!=0 && usernameField.getText().length()!=0 && +emailField.getText().length()!=0 && lnameField.getText().length()!=0 && fnameField.getText().length()!=0) {
					
					try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO logindb(Username, First_Name, Last_Name, Email, Password, Role) VALUES ('"+usernameField.getText()+"','"+fnameField.getText()+"','"+lnameField.getText()+"','"+emailField.getText()+"','"+passwordField.getText()+"','"+new String("user")+"')";
					stmt.executeUpdate(sql);
					conn.close();
					AdminInterface admin = new AdminInterface();
					admin.setVisible(true);
					dispose();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
						
				}else 
					JOptionPane.showMessageDialog(null, "Please enter all required information");			
			}
		});
		createBtn.setBounds(270, 11, 133, 23);
		panel_2.add(createBtn);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminInterface ad = new AdminInterface();
				ad.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(23, 11, 89, 23);
		panel_2.add(btnNewButton);
	}
}
