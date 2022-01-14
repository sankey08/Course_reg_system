package crs;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Registration {

	private JFrame frame;
	private JTextField textField_id;
	private JTextField textField_Name;
	private JPasswordField passwordField;
	private JPasswordField passwordField_con;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registration() {
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
		
		JLabel lblRegistration = new JLabel("Registration");
		lblRegistration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegistration.setBounds(171, 11, 116, 22);
		frame.getContentPane().add(lblRegistration);
		
		JLabel lblEnterDetails = new JLabel("Enter your details ");
		lblEnterDetails.setBounds(38, 44, 149, 14);
		frame.getContentPane().add(lblEnterDetails);
		
		JLabel lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setBounds(38, 70, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterUserName = new JLabel("Student Name");
		lblEnterUserName.setBounds(38, 95, 116, 14);
		frame.getContentPane().add(lblEnterUserName);
		
		JLabel lblEnterPassword = new JLabel("Password");
		lblEnterPassword.setBounds(38, 120, 99, 14);
		frame.getContentPane().add(lblEnterPassword);
		
		JLabel lblEnterConfirmPassword = new JLabel("Confirm Password");
		lblEnterConfirmPassword.setBounds(38, 149, 131, 14);
		frame.getContentPane().add(lblEnterConfirmPassword);
		
		textField_id = new JTextField();
		textField_id.setBounds(148, 67, 155, 20);
		frame.getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(148, 92, 155, 20);
		frame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int x=0;
				String s1 = textField_id.getText();
				String s2 = textField_Name.getText();
				char[] s3 = passwordField.getPassword();
				char[] s4 = passwordField_con.getPassword();
				String s5 = new String(s3);
				String s6 = new String(s4);
			    String s7 = textField.getText();
		//	    String s8 = textField_1.getText();
				
				if (s5.equals(s6))
				{
					try {
						


						Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
						PreparedStatement ps=myConn.prepareStatement("insert into student (s_id,s_fname,s_dept,s_pass) values(?,?,?,?)");
						
						ps.setString(1, s1);
						ps.setString(2, s2);
						ps.setString(4, s6);
						ps.setString(3, s7);
				//	ps.setString(5, s8);
						
						int rs= ps.executeUpdate();
						x++;
						if(x>0)
						{
							JOptionPane.showMessageDialog(btnConfirm, " User Added successfully ");
						}
						
						}
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(btnConfirm, "Re-enter Password ");
					passwordField.setText(null);
					passwordField_con.setText(null);
				}
				
			}
		});
		btnConfirm.setBounds(26, 227, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_id.setText(null);
				textField_Name.setText(null);
				passwordField.setText(null);
				passwordField_con.setText(null);
				textField.setText(null);
			//	textField_1.setText(null);
			}
		});
		btnClear.setBounds(183, 227, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(322, 227, 89, 23);
		frame.getContentPane().add(btnExit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 120, 155, 20);
		frame.getContentPane().add(passwordField);
		
		passwordField_con = new JPasswordField();
		passwordField_con.setBounds(148, 145, 155, 20);
		frame.getContentPane().add(passwordField_con);
		
		JLabel lblDept = new JLabel("Dept");
		lblDept.setBounds(38, 174, 77, 18);
		frame.getContentPane().add(lblDept);
		
		textField = new JTextField();
		textField.setBounds(148, 173, 155, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
