import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.JOptionPane;


public class ServerConnection{
	private static int User_Id;
	private String User_Role;
	String Username;
	String Email;
	LocalDateTime Date = LocalDateTime.now();	
	DateTimeFormatter newDate = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
	
	public ServerConnection() {
		
		User_Id = LoginInterface.User_Id;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt = conn.createStatement();
			String sql = "Select Username, Email, Role from logindb where User_Id="+User_Id+"";
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				Username = rs.getString("Username");
				Email = rs.getString("Email");
				User_Role=rs.getString("Role");
			}
			
			sql=" INSERT INTO logs(User_ID, Username, Email, Role, Date) VALUES ('"+User_Id+"','"+Username+"','"+Email+"','"+User_Role+"', '"+newDate.format(Date)+"') ";
			stmt.executeUpdate(sql);
			
			if(Objects.equals(User_Role, new String("admin"))) {
				JOptionPane.showMessageDialog(null, "Logged in as Admin User");
				AdminInterface admin = new AdminInterface();
				admin.setVisible(true);
				conn.close();
			}else if(Objects.equals(User_Role, new String("lab manager")) ){
				JOptionPane.showMessageDialog(null, "Logged in as Lab Manager User");
				ManagerInterface lab = new ManagerInterface();
				lab.setVisible(true);
				conn.close();
			}
			else {
				JOptionPane.showMessageDialog(null, "Logged in as Normal User");
				UserInterface userN = new UserInterface();
				userN.setVisible(true);
				conn.close();
			}
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		
	}
}
