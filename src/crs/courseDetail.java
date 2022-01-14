package crs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class courseDetail {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					courseDetail window = new courseDetail();
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
	public courseDetail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 687, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnShowData = new JButton("SHOW DATA");
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					String query = "select * from course";
					
					 Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/dbproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root" , "");
						
						
						PreparedStatement pst = myConn.prepareStatement(query);
						ResultSet rs= pst.executeQuery(query);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//DefaultTableModel tm = (DefaultTableModel)table.getModel();
						//tm.setRowCount(0);
						
//						/*while(rs.next())
//						{
//							Object o[] ={rs.getInt("s_id"),rs.getString("s_fname"),rs.getString("s_dept"),rs.getString("s_pass"),rs.getString("s_course")};
//							tm.addRow(o);
//						}*/
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(btnShowData, e1);
						e1.printStackTrace();
					}
				
				
			}
		});
		btnShowData.setBounds(222, 276, 89, 23);
		frame.getContentPane().add(btnShowData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(199, 66, 275, 163);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit ");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnExit.setBounds(417, 276, 89, 23);
		frame.getContentPane().add(btnExit);
	}
}
