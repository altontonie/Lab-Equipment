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

public class ResModifyInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int User_Id;
	private JTextField equipField;
	private JTextField roomNameField;
	private JTextField roomPlaceField;
	private JTextField roomNumberField;
	private JTextField dateField;
	private static String first_Name;
	private static String last_Name;
	private JTextField reservationIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResModifyInterface frame = new ResModifyInterface();
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
			String sql = "Select Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date from reservations where User_Id="+User_Id+" and NOT Comment='approved' ";
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
	public ResModifyInterface() {
		
		User_Id = LoginInterface.User_Id;
		
		setTitle("Modify Reservation");
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
		scrollPane.setBounds(226, 42, 448, 221);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton searchBtn = new JButton("Show reservations");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
					Statement stmt = conn.createStatement();
					String sql = "Select reservation_Id, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date as Date from reservations where User_Id="+User_Id+" and NOT Comment='approved' ";
					ResultSet rs= stmt.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					conn.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		searchBtn.setBounds(33, 65, 148, 36);
		panel_1.add(searchBtn);
		
		JButton reserveBtn = new JButton("Modify");
		reserveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(reservationIDField.getText().length()!=0 && equipField.getText().length()!=0 && roomNameField.getText().length()!=0 && roomPlaceField.getText().length()!=0 && roomNumberField.getText().length()!=0 && dateField.getText().length()!=0) {
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
												
						sql = "UPDATE reservations SET Equip_Name='"+equipField.getText()+"' ,Room_Name='"+roomNameField.getText()+"',Room_Place='"+roomPlaceField.getText()+"',Room_Number='"+roomNumberField.getText()+"',User_Id="+User_Id+",First_Name='"+first_Name+"',Last_Name='"+last_Name+"',Reservation_Date='"+dateField.getText()+"',Comment='"+new String("pending")+"' WHERE reservation_Id="+reservationIDField.getText()+"";
						stmt.executeUpdate(sql);
						conn.close();
						refreshTable();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Please Enter all the required Fields");
				
			}
		});
		reserveBtn.setBounds(548, 324, 109, 27);
		panel_1.add(reserveBtn);
		
		JLabel lblNewLabel_1 = new JLabel("List of Reservations");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(276, 11, 298, 27);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Equipment name");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(36, 291, 97, 14);
		panel_1.add(lblNewLabel_2);
		
		equipField = new JTextField();
		equipField.setColumns(10);
		equipField.setBounds(133, 288, 102, 20);
		panel_1.add(equipField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Room Name");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setBounds(262, 291, 72, 14);
		panel_1.add(lblNewLabel_2_1);
		
		roomNameField = new JTextField();
		roomNameField.setColumns(10);
		roomNameField.setBounds(344, 288, 102, 20);
		panel_1.add(roomNameField);
		
		JLabel lblNewLabel_2_2 = new JLabel("Room Place");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setBounds(481, 291, 72, 14);
		panel_1.add(lblNewLabel_2_2);
		
		roomPlaceField = new JTextField();
		roomPlaceField.setColumns(10);
		roomPlaceField.setBounds(555, 288, 102, 20);
		panel_1.add(roomPlaceField);
		
		JLabel lblNewLabel_2_3 = new JLabel("Room Number");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setBounds(36, 330, 97, 14);
		panel_1.add(lblNewLabel_2_3);
		
		roomNumberField = new JTextField();
		roomNumberField.setColumns(10);
		roomNumberField.setBounds(133, 327, 102, 20);
		panel_1.add(roomNumberField);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(414, 330, 102, 20);
		panel_1.add(dateField);
		
		JLabel lblNewLabel_3 = new JLabel("New Date");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(314, 330, 90, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Reservation ID");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(10, 247, 90, 14);
		panel_1.add(lblNewLabel_4);
		
		reservationIDField = new JTextField();
		reservationIDField.setBounds(110, 244, 106, 20);
		panel_1.add(reservationIDField);
		reservationIDField.setColumns(10);
		
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
