import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminInterface extends JFrame {

	private JPanel contentPane;
	public static int User_Id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInterface frame = new AdminInterface();
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
	public AdminInterface() {
		setTitle("Lab Management System - Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User_Id = LoginInterface.User_Id;
		
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
		panel_1.setBounds(0, 43, 684, 374);
		contentPane.add(panel_1);
		
		JButton createBtn = new JButton("Create User");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpInterface signup = new SignUpInterface();
				signup.setVisible(true);
				dispose();
			}
		});
		createBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createBtn.setBounds(264, 62, 164, 42);
		panel_1.add(createBtn);
		
		JButton btnModify = new JButton("Modify User");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyUserInterface mod = new ModifyUserInterface();
				mod.setVisible(true);
				dispose();
			}
		});
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModify.setBounds(264, 115, 164, 42);
		panel_1.add(btnModify);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserInterface del = new deleteUserInterface();
				del.setVisible(true);
			}
		});
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteUser.setBounds(264, 168, 164, 42);
		panel_1.add(btnDeleteUser);
		
		JButton btnNewButton = new JButton("Equipments");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EquipmentInterface equip = new EquipmentInterface();
				equip.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(264, 221, 164, 42);
		panel_1.add(btnNewButton);
		
		JButton btnLogs = new JButton("LOGS");
		btnLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInterface mod = new LogInterface();
				mod.setVisible(true);
				dispose();
			}
		});
		btnLogs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogs.setBounds(264, 274, 164, 42);
		panel_1.add(btnLogs);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton loginBtn = new JButton("Log Out");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginInterface.User_Id = 0;
				LoginInterface log = new LoginInterface();
				
				log.setVisible(true);
				dispose();
			}
		});
		loginBtn.setBounds(531, 11, 116, 23);
		panel_2.add(loginBtn);
	}
}
