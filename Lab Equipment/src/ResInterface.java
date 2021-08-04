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

public class ResInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int User_Id;
	private JTextField resIdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResInterface frame = new ResInterface();
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
			String sql = "Select reservation_Id, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date, Comment from reservations where User_Id="+User_Id+" ";
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
	public ResInterface() {
		User_Id = LoginInterface.User_Id;
		
		setTitle("Reservations");
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
			String sql = "Select reservation_Id, Equip_Name, Room_Name, Room_Place, Room_Number, Reservation_date, Comment from reservations where User_Id="+User_Id+" ";
			ResultSet rs= stmt.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			conn.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
		JButton cancelBtn_1 = new JButton("Cancel Reservation");
		cancelBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(resIdField.getText().length()!=0) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
						Statement stmt = conn.createStatement();
						String sql = "DELETE FROM reservations WHERE reservation_Id="+resIdField.getText()+"";
						stmt.executeUpdate(sql);
						conn.close();
						refreshTable();
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}else
					JOptionPane.showMessageDialog(null, "Reservation ID is required");
				
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
		lblNewLabel_2.setBounds(122, 321, 91, 14);
		panel_1.add(lblNewLabel_2);
		
		resIdField = new JTextField();
		resIdField.setBounds(268, 318, 117, 20);
		panel_1.add(resIdField);
		resIdField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton cancelBtn = new JButton("Back");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInterface user = new UserInterface();
				user.setVisible(true);
				dispose();
			}
		});
		cancelBtn.setBounds(531, 11, 116, 23);
		panel_2.add(cancelBtn);
	}
}
