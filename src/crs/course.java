package crs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.mysql.cj.xdevapi.Statement;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class course {

	private JFrame frame;
	private JComboBox comboBox;
	Connection myConn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					course window = new course();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//METHOD TO DISPLAY COURSES
	private void courseDropdown()
	{
		try {
			String query ="select * from course";
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
			 PreparedStatement pst = myConn.prepareStatement(query);
			 ResultSet rs=   pst.executeQuery(query);
			 
			 while(rs.next())
				{
				 comboBox.addItem(rs.getString("c_name"));
				}
	}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/**
	 * Create the application.
	 */
	public course() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCourses = new JLabel("COURSES");
		lblCourses.setBounds(190, 11, 61, 32);
		frame.getContentPane().add(lblCourses);
		
		JLabel lblSelectcourse = new JLabel("SELECT COURSE");
		lblSelectcourse.setBounds(98, 97, 112, 14);
		frame.getContentPane().add(lblSelectcourse);
		
		comboBox = new JComboBox();
		comboBox.setBounds(247, 94, 75, 20);
		frame.getContentPane().add(comboBox);
		
		courseDropdown();
		
		JButton btnNewButton = new JButton("CONFIRM");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				try {
//					String query ="insert into student (s_course) values(?)";
//						
//					 Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
//					 //PreparedStatement pst = myConn.prepareStatement(query);
//					// ResultSet rs=  ((java.sql.Statement) pst).executeQuery(query);
//					
//					String value=comboBox.getSelectedItem().toString();
//				 Statement ps=(Statement) myConn.createStatement();
//					System.out.print(comboBox.getSelectedItem().toString());
//					ResultSet rs = (ResultSet) ps.execute();
//					while(rs.next())
//					{
//					rs.updateString(1, value);
//					
//					}
//					JOptionPane.showMessageDialog(null,"Courses Added Successfully");
//					
//			}
				try {
					
					
					 String value=(String) comboBox.getSelectedItem();
					 System.out.print(value);
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
					String query ="UPDATE student SET s_course=(?) where s_id=(?)"; 
					PreparedStatement pst = myConn.prepareStatement(query);
					
					//while(true)
					//{
					pst.setString(1,value);
					pst.setString(2, Login.s1);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Courses Added Successfully");
					//}
			}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(174, 174, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnShowDetails = new JButton("Show Details");
		btnShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					courseDetail main = new courseDetail();
					main.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnShowDetails.setBounds(136, 227, 163, 23);
		frame.getContentPane().add(btnShowDetails);
		
		
	}
}
