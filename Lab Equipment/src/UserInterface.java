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

public class UserInterface extends JFrame {

	private JPanel contentPane;
	public static int User_Id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
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
	public UserInterface() {
		setTitle("Lab Management System");
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
		panel_1.setBounds(0, 44, 684, 374);
		contentPane.add(panel_1);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReservationInterface user = new ReservationInterface();
				user.setVisible(true);
				dispose();
			}
		});
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createButton.setBounds(282, 116, 119, 42);
		panel_1.add(createButton);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResModifyInterface res = new ResModifyInterface();
				res.setVisible(true);
				dispose();
			}
		});
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModify.setBounds(282, 169, 119, 42);
		panel_1.add(btnModify);
		
		JButton btnStatus = new JButton("Status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResInterface status = new ResInterface();
				status.setVisible(true);
				dispose();
			}
		});
		btnStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStatus.setBounds(282, 222, 119, 42);
		panel_1.add(btnStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 417, 684, 44);
		contentPane.add(panel_2);
		
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginInterface.User_Id = 0;
				LoginInterface log = new LoginInterface();
				
				log.setVisible(true);
				dispose();
			}
		});
		logoutBtn.setBounds(531, 11, 116, 23);
		panel_2.add(logoutBtn);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateInterface updateI = new UpdateInterface();
				updateI.setVisible(true);
				dispose();
			}
		});
		btnUpdate.setBounds(32, 11, 116, 23);
		panel_2.add(btnUpdate);
	}

}
