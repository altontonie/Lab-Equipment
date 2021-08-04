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

public class AllReservationsInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int User_Id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllReservationsInterface frame = new AllReservationsInterface();
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
	public AllReservationsInterface() {
		User_Id = LoginInterface.User_Id;
		
		setTitle("All Reservation");
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
		scrollPane.setBounds(29, 63, 625, 221);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select First_Name, Last_Name, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date, Comment from reservations ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			//conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		JButton cancelBtn_1 = new JButton("Cancel Reservation");
		cancelBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		cancelBtn_1.setBounds(36, 312, 148, 36);
		panel_1.add(cancelBtn_1);
		
		JLabel lblNewLabel_1 = new JLabel("List of Equipments Reservations");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(275, 11, 298, 50);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ManagerInterface user = new ManagerInterface();
				user.setVisible(true);
			}
		});
		cancelBtn.setBounds(531, 11, 116, 23);
		panel_2.add(cancelBtn);
	}
}
