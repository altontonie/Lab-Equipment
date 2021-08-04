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

public class deleteUserInterface extends JFrame {

	private JPanel contentPane;
	
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
				
				deleteUserInterface frame = new deleteUserInterface();
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
	public deleteUserInterface() {
		
		setTitle("Delete User");
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
		
		IDField = new JTextField();
		IDField.setText((String) null);
		IDField.setColumns(10);
		IDField.setBounds(77, 59, 140, 20);
		panel_1.add(IDField);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(10, 65, 65, 14);
		panel_1.add(lblUserId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 11, 447, 323);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
		
		JButton updateBtn = new JButton("Delete User");
		updateBtn.setBounds(48, 115, 133, 23);
		panel_1.add(updateBtn);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(IDField.getText().length()!=0) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "DELETE from logindb where User_Id="+IDField.getText()+"";
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Information Updated");
						conn.close();
						refreshTable();
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "User ID is required");
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
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
