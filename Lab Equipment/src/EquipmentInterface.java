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
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EquipmentInterface extends JFrame {

	private JPanel contentPane;
	private JTextField roomNumberField;
	private JTextField roomPlaceField;
	private JTextField roomNameField;
	
	private static String first_name;
	private static String last_name;
	private static String mail;
	private static String pwd;
	private static String user_name;
	public static int User_Id;
	private JTextField equipField;
	private JTable table;
	private JTextField equipIdField;
	private JTextField equipIdField2;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				EquipmentInterface frame = new EquipmentInterface();
				frame.setVisible(true);
			}
		});
	}
	
	public void refreshTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM labequipments ";
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
	public EquipmentInterface() {

		setTitle("Equipment Information");
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
		
		JLabel lblUsername = new JLabel("Room #");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(10, 127, 65, 14);
		panel_1.add(lblUsername);
		
		roomNumberField = new JTextField();
		roomNumberField.setColumns(10);
		roomNumberField.setBounds(85, 121, 132, 20);
		roomNumberField.setText(user_name);
		panel_1.add(roomNumberField);
		
		JLabel lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 158, 65, 14);
		panel_1.add(lblNewLabel_1);
		
		roomPlaceField = new JTextField();
		roomPlaceField.setColumns(10);
		roomPlaceField.setBounds(85, 90, 132, 20);
		roomPlaceField.setText(last_name);
		panel_1.add(roomPlaceField);
		
		roomNameField = new JTextField();
		roomNameField.setColumns(10);
		roomNameField.setBounds(85, 59, 132, 20);
		roomNameField.setText(first_name);
		panel_1.add(roomNameField);
		
		JLabel lblLastName = new JLabel("Room Place");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setBounds(10, 96, 75, 14);
		panel_1.add(lblLastName);
		
		JLabel lblFirstName = new JLabel("Room Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setBounds(10, 65, 75, 14);
		panel_1.add(lblFirstName);
		
		equipField = new JTextField();
		equipField.setText((String) null);
		equipField.setColumns(10);
		equipField.setBounds(85, 28, 132, 20);
		panel_1.add(equipField);
		
		JLabel lblUserId = new JLabel("Equipment");
		lblUserId.setForeground(Color.WHITE);
		lblUserId.setBounds(10, 34, 75, 14);
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
			String sql = "SELECT * FROM labequipments ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));		
			conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		equipIdField = new JTextField();
		equipIdField.setBounds(113, 314, 86, 20);
		panel_1.add(equipIdField);
		equipIdField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Equipment ID");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 317, 93, 14);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "DELETE FROM labequipments where Equip_Id="+equipIdField.getText()+"";
					stmt.executeUpdate(sql);
					conn.close();
					equipIdField.setText("");
					refreshTable();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setBounds(62, 340, 89, 23);
		panel_1.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"available", "not available"}));
		comboBox.setBounds(99, 154, 118, 22);
		panel_1.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("Add Equipment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(equipField.getText().length()!=0 && roomNameField.getText().length()!=0 && roomPlaceField.getText().length()!=0 && +roomNumberField.getText().length()!=0 ) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "INSERT INTO `labequipments`(`Equip_Name`, `Room_Name`, `Room_Place`, `Room_Number`, `Status`) VALUES ('"+equipField.getText()+"','"+roomNameField.getText()+"','"+roomPlaceField.getText()+"','"+roomNumberField.getText()+"','"+comboBox.getSelectedItem()+"') ";
						stmt.executeUpdate(sql);
						conn.close();
						equipField.setText("");
						roomNameField.setText("");
						roomPlaceField.setText("");
						roomNumberField.setText("");
						refreshTable();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Please Enter all required Fields");
				
			}
		});
		btnNewButton_2.setBounds(99, 183, 118, 23);
		panel_1.add(btnNewButton_2);
			
		JLabel lblNewLabel_2_1 = new JLabel("Equipment ID");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(10, 224, 93, 14);
		panel_1.add(lblNewLabel_2_1);
		
		equipIdField2 = new JTextField();
		equipIdField2.setColumns(10);
		equipIdField2.setBounds(113, 221, 86, 20);
		panel_1.add(equipIdField2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Status");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setBounds(10, 252, 93, 14);
		panel_1.add(lblNewLabel_2_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"available", "not available"}));
		comboBox_1.setBounds(113, 252, 104, 22);
		panel_1.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("Modify");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "UPDATE `labequipments` SET Status='"+comboBox_1.getSelectedItem()+"' where Equip_Id='"+equipIdField2.getText()+"'";
					stmt.executeUpdate(sql);
					conn.close();
					equipIdField2.setText("");
					refreshTable();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_1.setBounds(62, 280, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test="SELECT * FROM labequipments";
				if(equipField.getText().length()!=0) {
					test="SELECT * FROM labequipments where Equip_Name='"+equipField.getText()+"'";
				}else if(roomNameField.getText().length()!=0) {
					test="SELECT * FROM labequipments where Room_Name='"+roomNameField.getText()+"'";
				}else if(roomPlaceField.getText().length()!=0) {
					test="SELECT * FROM labequipments where Room_Place='"+roomPlaceField.getText()+"'";
				}else if(roomNumberField.getText().length()!=0) {
					test="SELECT * FROM labequipments where Room_Number='"+roomNumberField.getText()+"'";
				}
				//JOptionPane.showMessageDialog(null, test);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = test;
					ResultSet rs = stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
					equipField.setText("");
					roomNameField.setText("");
					roomPlaceField.setText("");
					roomNumberField.setText("");
					
					//refreshTable();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton_3.setBounds(14, 183, 75, 23);
		panel_1.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerConnection server = new ServerConnection();
				
				dispose();
			}
		});
		cancelBtn.setBounds(10, 11, 89, 23);
		panel_2.add(cancelBtn);
	}
}
