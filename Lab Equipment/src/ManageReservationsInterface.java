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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ManageReservationsInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int User_Id;
	private JTextField resIdField;
	private String comment1 = "approved";
	private String comment2 = "disapproved";
	private String selected;
	private int ID;
	private JTextField roomNumField;
	private JTextField roomPlaceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageReservationsInterface frame = new ManageReservationsInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select reservation_Id, First_Name, Last_Name, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date, Comment from reservations ";
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
	public ManageReservationsInterface() {
		User_Id = LoginInterface.User_Id;
		
		setTitle("Make Reservation");
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
		
		JLabel lblNewLabel = new JLabel("Lab Management Reservation System");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 28));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 44, 684, 374);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 664, 221);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select reservation_Id, First_Name, Last_Name, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date, Comment from reservations ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		JButton cancelBtn_1 = new JButton("Submit");
		cancelBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(resIdField.getText().length()!=0) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "UPDATE reservations set Comment='"+selected+"' where reservation_Id="+resIdField.getText()+"";
						stmt.executeUpdate(sql);
						conn.close();
						refreshTable();
						resIdField.setText("");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Enter all required Fields");
				
			}
		});
		cancelBtn_1.setBounds(526, 327, 148, 36);
		panel_1.add(cancelBtn_1);
		
		JLabel lblNewLabel_1 = new JLabel("List of Equipments Reservations");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(184, 2, 298, 50);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Reservation ID");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 345, 91, 14);
		panel_1.add(lblNewLabel_2);
		
		resIdField = new JTextField();
		resIdField.setBounds(92, 342, 117, 20);
		panel_1.add(resIdField);
		resIdField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Comment");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(262, 345, 63, 14);
		panel_1.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected= (String)comboBox.getSelectedItem();
			}
		});
		comboBox.setBounds(350, 341, 117, 22);
		comboBox.addItem(comment1);
		comboBox.addItem(comment2);
		
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Room Number");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(10, 296, 81, 14);
		panel_1.add(lblNewLabel_4);
		
		roomNumField = new JTextField();
		roomNumField.setBounds(92, 293, 86, 20);
		panel_1.add(roomNumField);
		roomNumField.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("Room Place");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setBounds(205, 296, 93, 14);
		panel_1.add(lblNewLabel_4_1);
		
		roomPlaceField = new JTextField();
		roomPlaceField.setColumns(10);
		roomPlaceField.setBounds(297, 293, 86, 20);
		panel_1.add(roomPlaceField);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = "Select reservation_Id, First_Name, Last_Name, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date, Comment from reservations where Room_Number='"+roomNumField.getText()+"' OR Room_Place='"+roomPlaceField.getText()+"'";
				if(roomNumField.getText().length()==0 && roomPlaceField.getText().length()==0)
					text = "Select reservation_Id, First_Name, Last_Name, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date, Comment from reservations ";
				//JOptionPane.showMessageDialog(null, text);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = text;
					ResultSet rs= stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
					roomNumField.setText("");
					roomPlaceField.setText("");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnNewButton.setBounds(549, 293, 89, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerInterface user = new ManagerInterface();
				user.setVisible(true);
				dispose();
			}
		});
		cancelBtn.setBounds(531, 11, 116, 23);
		panel_2.add(cancelBtn);
	}
}
