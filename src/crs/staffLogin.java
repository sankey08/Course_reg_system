package crs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class staffLogin {

	private JFrame frame;
	private JTextField textField_name;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staffLogin window = new staffLogin();
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
	public staffLogin() {
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
		
		JLabel lblLogin = new JLabel("STAFF LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setBounds(163, 11, 112, 26);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblEnterUsername = new JLabel("Enter ID : ");
		lblEnterUsername.setBounds(106, 79, 93, 14);
		frame.getContentPane().add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("Enter Password : ");
		lblEnterPassword.setBounds(106, 114, 93, 14);
		frame.getContentPane().add(lblEnterPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 50, 414, 2);
		frame.getContentPane().add(separator);
		
		textField_name = new JTextField();
		textField_name.setBounds(209, 76, 86, 20);
		frame.getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(209, 111, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s1 =textField_name.getText();
				String s2 = passwordField.getText();
				
				try {
				String query = "select r_ssid,r_sspwd from staff where r_ssid='"+textField_name.getText()+"'";
				
				 Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
						
				 PreparedStatement pst = myConn.prepareStatement(query);
				 ResultSet rs= pst.executeQuery();
				 int count = 0;
					
				 if(!rs.next())
				 {}

					
				 if(s2.equals(rs.getString("r_sspwd")))
				 {
						JOptionPane.showMessageDialog(btnConfirm,"Login Successful");
						//COURSE PAGE
						Staff2 main = new Staff2();
						main.main(null);
				 }
				 else 
				 {
						JOptionPane.showMessageDialog(btnConfirm, "invaild details , please try again ");
						textField_name.setText(null);
						passwordField.setText(null);
				 }
					
				 } 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnConfirm.setBounds(106, 181, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_name.setText(null);
				passwordField.setText(null);
			}
		});
		btnClear.setBounds(206, 181, 89, 23);
		frame.getContentPane().add(btnClear);
	}

}
