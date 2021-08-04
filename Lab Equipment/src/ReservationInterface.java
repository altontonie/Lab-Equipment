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

public class ReservationInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int User_Id;
	private JTextField equipField;
	private JTextField roomNameField;
	private JTextField roomPlaceField;
	private JTextField roomNumberField;
	private JTextField resDateField;
	private static String first_Name;
	private static String last_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationInterface frame = new ReservationInterface();
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
	public ReservationInterface() {
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
		scrollPane.setBounds(206, 36, 448, 221);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton showBtn = new JButton("Show equipments");
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "Select * from labequipments ";
					ResultSet rs= stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		showBtn.setBounds(33, 38, 148, 36);
		panel_1.add(showBtn);
		
		JButton reserveBtn = new JButton("Make Reservation");
		reserveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(equipField.getText().length()!=0 && roomNameField.getText().length()!=0 && roomPlaceField.getText().length()!=0 && roomNumberField.getText().length()!=0) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						
						String sql = "Select First_Name, Last_Name from logindb where User_Id="+User_Id+" ";
						ResultSet rs= stmt.executeQuery(sql);
						while(rs.next()) {
							first_Name = rs.getString("First_Name");
							last_Name = rs.getString("Last_Name");
						}
						
						sql = "INSERT INTO reservations(Equip_Name, Room_Name, Room_Place, Room_Number, User_Id, First_Name, Last_Name, Reservation_Date, Comment) VALUES ('"+equipField.getText()+"','"+roomNameField.getText()+"','"+roomPlaceField.getText()+"','"+roomNumberField.getText()+"',"+User_Id+",'"+first_Name+"','"+last_Name+"','"+resDateField.getText()+"', '"+new String("pending")+"')";
						stmt.executeUpdate(sql);
						
						conn.close();
						JOptionPane.showMessageDialog(null, "Reservation Submitted");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Enter all the required Fields");				
			
			}
		});
		reserveBtn.setBounds(506, 325, 148, 36);
		panel_1.add(reserveBtn);
		
		JLabel lblNewLabel_1 = new JLabel("List of available Equipments");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(275, 0, 298, 36);
		panel_1.add(lblNewLabel_1);
		
		JButton approvedBtn = new JButton("Approved");
		approvedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "Select Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_Date as Date from reservations where User_Id="+User_Id+" and Comment='approved'";
					ResultSet rs= stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		approvedBtn.setBounds(33, 221, 148, 36);
		panel_1.add(approvedBtn);
		
		equipField = new JTextField();
		equipField.setBounds(130, 286, 102, 20);
		panel_1.add(equipField);
		equipField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Equipment name");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(33, 289, 97, 14);
		panel_1.add(lblNewLabel_2);
		
		roomNameField = new JTextField();
		roomNameField.setColumns(10);
		roomNameField.setBounds(341, 286, 102, 20);
		panel_1.add(roomNameField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Room Name");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(259, 289, 72, 14);
		panel_1.add(lblNewLabel_2_1);
		
		roomPlaceField = new JTextField();
		roomPlaceField.setColumns(10);
		roomPlaceField.setBounds(552, 286, 102, 20);
		panel_1.add(roomPlaceField);
		
		JLabel lblNewLabel_2_2 = new JLabel("Room Place");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setBounds(478, 289, 72, 14);
		panel_1.add(lblNewLabel_2_2);
		
		roomNumberField = new JTextField();
		roomNumberField.setColumns(10);
		roomNumberField.setBounds(130, 325, 102, 20);
		panel_1.add(roomNumberField);
		
		JLabel lblNewLabel_2_3 = new JLabel("Room Number");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setBounds(33, 328, 97, 14);
		panel_1.add(lblNewLabel_2_3);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "Select * from labequipments where Equip_Name='"+equipField.getText().toString()+"' OR Room_Name='"+roomNameField.getText()+"' OR Room_Number='"+roomNumberField.getText()+"' OR Room_Place='"+roomPlaceField.getText()+"' ";
					ResultSet rs= stmt.executeQuery(sql);
					if(!rs.next())
						JOptionPane.showMessageDialog(null, "Equipment Not Found");
					else {
						sql = "Select * from labequipments where Equip_Name='"+equipField.getText().toString()+"' OR Room_Name='"+roomNameField.getText()+"' OR Room_Number='"+roomNumberField.getText()+"' OR Room_Place='"+roomPlaceField.getText()+"' ";
						rs= stmt.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					
									
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		searchBtn.setBounds(381, 324, 102, 39);
		panel_1.add(searchBtn);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(285, 325, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		resDateField = new JTextField();
		resDateField.setBounds(259, 341, 102, 20);
		panel_1.add(resDateField);
		resDateField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserInterface user = new UserInterface();
				user.setVisible(true);
			}
		});
		cancelBtn.setBounds(531, 11, 116, 23);
		panel_2.add(cancelBtn);
	}
}
