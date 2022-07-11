package AccessSoftware;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JPasswordField pf1;
	private Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
			System.out.println("Connection established!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Exception:" + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 725);
		setSize(1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244,244,244));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lLable = new JLabel("Welcome!");
		lLable.setBounds(331, 66, 342, 72);
		lLable.setHorizontalAlignment(SwingConstants.CENTER);
		lLable.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 50));
		contentPane.add(lLable);
		
		JLabel lUserName = new JLabel("User ID:");
		lUserName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		lUserName.setBounds(280, 312, 114, 57);
		contentPane.add(lUserName);
		
		JLabel lPassword = new JLabel("Password:");
		lPassword.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		lPassword.setBounds(248, 408, 146, 57);
		contentPane.add(lPassword);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		tf1.setBounds(416, 320, 259, 45);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		pf1 = new JPasswordField();
		pf1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		pf1.setBounds(416, 414, 259, 45);
		contentPane.add(pf1);
		
		JComboBox cIdentity = new JComboBox();
		cIdentity.setForeground(Color.DARK_GRAY);
		cIdentity.setBackground(Color.WHITE);
		cIdentity.setModel(new DefaultComboBoxModel(new String[] {"System Administrator", "Device Manufacturer"}));
		cIdentity.setSelectedIndex(0);
		cIdentity.setMaximumRowCount(2);
		cIdentity.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		cIdentity.setBounds(416, 229, 259, 45);
		contentPane.add(cIdentity);
		
		JLabel lIdentity = new JLabel("Identity:");
		lIdentity.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
		lIdentity.setBounds(276, 219, 118, 57);
		contentPane.add(lIdentity);
		
		JButton bLogin = new JButton("Login");
		bLogin.setBackground(Color.WHITE);
		bLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ee) {
				//Identity: System Admin
				if (cIdentity.getSelectedIndex() == 0) {
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT AdPassword FROM Administration WHERE AdDel = 0 AND AdID = '" + tf1.getText() + "'");
						if(!myRs.next() || !myRs.getString("AdPassword").equals(MD5.encode(pf1.getText()))) {
							JOptionPane.showMessageDialog(null, "Wrong ID or password!", "Sorry", JOptionPane.WARNING_MESSAGE);
						}
						else {
							Admin myAdmin = new Admin();
							myAdmin.setVisible(true);
							setVisible(false);
						}
					}
					catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
				}
				//Identity: Device Manu
				else {
					try {
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT ManuPassword FROM Manufacturer WHERE ManuDel = 0 AND ManuID = '" + tf1.getText() + "'");
						if(!myRs.next() || !myRs.getString("ManuPassword").equals(MD5.encode(pf1.getText()))) {
							JOptionPane.showMessageDialog(null, "Wrong ID or password!", "Sorry", JOptionPane.WARNING_MESSAGE);
						}
						else {
							Manufacturer myManu = new Manufacturer(tf1.getText());
							myManu.setVisible(true);
							setVisible(false);
						}
					}
					catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
				}
			}
		});
		bLogin.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 25));
		bLogin.setBounds(425, 510, 146, 50);
		ImageIcon iIn = new ImageIcon("icons\\iconIn.png");
		iIn.setImage(iIn.getImage().getScaledInstance(25,25, 0));
		bLogin.setIcon(iIn);
		contentPane.add(bLogin);
		
		JLabel lInfo = new JLabel("Smart home administration terminal");
		lInfo.setForeground(Color.LIGHT_GRAY);
		lInfo.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lInfo.setBounds(800, 636, 176, 27);
		contentPane.add(lInfo);
	}
}
