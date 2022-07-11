package AccessSoftware;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Arrays;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTextField tID;
	private JTextField tName;
	private JTextField tHome;
	private JTextField tCheck;
	private JTextField tID2;
	private JTextField tName2;
	private JTextField tHome2;
	private JTextField tCheck2;
	private JTextField tGen2;
	private JTextField tID3;
	private JTextField tName3;
	private JTextField tHome3;
	private JTextField tCheck3;
	private JTextField tContent;
	private JTextField tContent2;
	private JTextField tHomeID;
	private JTextField tHomeCheck;
	private JTextField tHomeAddress;
	private JTextField tHomeID2;
	private JTextField tHomeCheck2;
	private JTextField tHomeAddress2;
	private JTextField tHomeID3;
	private JTextField tHomeCheck3;
	private JTextField tHomeAddress3;
	private JTextField tContent3;
	private JTextField tDeviceAddID;
	private JTextField tDeviceAddName;
	private JTextField tDeviceAddManu;
	private JTextField tDeviceDeleteID;
	private JTextField tDeviceDeleteName;
	private JTextField tDeviceDeleteManu;
	private JTextField tDeviceDelete;
	private JTextField tDeviceModifyID;
	private JTextField tDeviceModifyName;
	private JTextField tDeviceModifyManu;
	private JTextField tDeviceModify;
	private JTextField tCurrentType;
	private JTextField tDeviceDeleteType;
	
	
	private String[] typeBox = new String[20];
	private String[] typeIDBox = new String[20];
	int typeNum = 0;
	private JTextField tTypeAddID;
	private JTextField tTypeAddName;
	private JTextField tTypeDeleteID;
	private JTextField tTypeModifyID;
	private JTextField tTypeModifyName;
	private JTextField tDataSearchDeviceID;
	private JTextField tDataSearchDeviceName;
	private JTextField tDataSearchDeviceManu;
	private JTextField tDataSearchDeviceType;
	private JTextField tDataSearchTypeID;
	private JTextField tDataSearchTypeName;
	private JTextField tDataSearchHomeID;
	private JTable tabDataSearch;
	private JTextField tDataStatisticsID;
	private JTextField tDataStatisticsName;
	private JTextField tDataStatisticsManu;
	private JTextField tDataStatisticsType;
	private JTextField tDataStatisticsValue;
	private JTextField tDataDisID;
	private JTextField tDataDisName;
	private JTextField tDataDisManu;
	private JTextField tDataDisType;
	private JTextField tDataHomeSet;
	private JTable tabDataHome;
	private JTextField tDataManageDeleteID;
	private JTextField tDataManageDeleteTime;
	private JTextField tDataManageDeleteDeviceID;
	private JTextField tDataManageDeleteType;
	private JTextField tDataManageDeleteName;
	private JTextField tDataManageDeleteValue;
	private JTextField tDataManageModifyID;
	private JTextField tDataManageModifyTime;
	private JTextField tDataManageModifyDeviceID;
	private JTextField tDataManageModifyName;
	private JTextField tDataManageModifyType;
	private JTextField tDataManageModifyValue;
	private JTable tabMem;
	private Color color1 = new Color(244,244,244);
	private Color color2 = new Color(228,228,228);
	private Color color3 = new Color(202,202,202);
	
	private Connection conn = null;
	private JTable tabHome;
	private JTextField tDeviceAdd;
	private JTextField tDataSearchHomeAddress;
	private JTable tabDevice;
	private JTextField tDeviceModifyHome;
	private JTextField tDeviceDeleteHome;
	private JTextField tDeviceAddHome;
	private JTable tabType;
	private JTextField tDataManageDeleteCheck;
	private JTextField tDataManageModifyCheck;
	
	@SuppressWarnings("unchecked")

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Admin frame = new Admin();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Admin() {
		//Get Typebox Logic
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
			System.out.println("Connection established!");
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from Type where typeDel = 0");
			int i = 0;
			while(myRs.next()) {
				typeBox[i] = myRs.getString("TypeName");
				typeIDBox[i] = myRs.getString("TypeID");
				i++;
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("Exception:" + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}
		//get type number
		while(typeBox[typeNum] != null) { typeNum++; }
		String[] typeBoxCut = new String[typeNum];
		String[] typeIDBoxCut = new String[typeNum];
		for(int i = 0; i < typeNum; i++) {
			typeBoxCut[i] = typeBox[i];
			typeIDBoxCut[i] = typeIDBox[i];
		}
		//Add "All"
		String[] typeBoxCutPlus = new String[typeNum + 1];
		typeBoxCutPlus[0] = "All";
		for(int i = 0; i < typeNum; i++) {
			typeBoxCutPlus[i + 1] = typeBoxCut[i];
		}
		
		
		setResizable(false);
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000,700);
		contentPane = new JPanel();
		contentPane.setBackground(color1);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//UIManager Set
		UIManager.put("TabbedPane.selected", color2);
		UIManager.put("TabbedPane.background", Color.WHITE);
		UIManager.put("Panel.background", color1);
		UIManager.put("TitledBorder.border", new LineBorder(Color.lightGray));
		UIManager.put("TitledBorder.font", new Font("Arial", Font.BOLD | Font.ITALIC, 13)); 
		UIManager.put("TitledBorder.titleColor", Color.darkGray);
		
		//Icons Adding
		ImageIcon iAdd = new ImageIcon("icons\\iconAdd.png");
		iAdd.setImage(iAdd.getImage().getScaledInstance(30,30, 0));
		ImageIcon iDelete = new ImageIcon("icons\\iconDelete.png");
		iDelete.setImage(iDelete.getImage().getScaledInstance(30,30, 0));
		ImageIcon iModify = new ImageIcon("icons\\iconModify.png");
		iModify.setImage(iModify.getImage().getScaledInstance(30,30, 0));
		ImageIcon iDraw = new ImageIcon("icons\\iconDraw.png");
		iDraw.setImage(iDraw.getImage().getScaledInstance(30,30, 0));
		ImageIcon iMember = new ImageIcon("icons\\iconMember.png");
		iMember.setImage(iMember.getImage().getScaledInstance(45,45, 0));
		ImageIcon iHome = new ImageIcon("icons\\iconHome.png");
		iHome.setImage(iHome.getImage().getScaledInstance(45,45, 0));
		ImageIcon iDevice = new ImageIcon("icons\\iconDevice.png");
		iDevice.setImage(iDevice.getImage().getScaledInstance(45,45, 0));
		ImageIcon iType = new ImageIcon("icons\\iconType.png");
		iType.setImage(iType.getImage().getScaledInstance(45,45, 0));
		ImageIcon iData = new ImageIcon("icons\\iconData.png");
		iData.setImage(iData.getImage().getScaledInstance(45,45, 0));
		ImageIcon iSearch = new ImageIcon("icons\\iconSearch.png");
		iSearch.setImage(iSearch.getImage().getScaledInstance(15,15, 0));
		ImageIcon iSearch2 = new ImageIcon("icons\\iconSearch2.png");
		iSearch2.setImage(iSearch2.getImage().getScaledInstance(30,30, 0));
		ImageIcon iStatistics = new ImageIcon("icons\\iconStatistics.png");
		iStatistics.setImage(iStatistics.getImage().getScaledInstance(30,30, 0));
		ImageIcon iDistribution = new ImageIcon("icons\\iconDistribution.png");
		iDistribution.setImage(iDistribution.getImage().getScaledInstance(30,30, 0));
		ImageIcon iHomeSet = new ImageIcon("icons\\iconHomeset.png");
		iHomeSet.setImage(iHomeSet.getImage().getScaledInstance(30,30, 0));
		ImageIcon iManage = new ImageIcon("icons\\iconManage.png");
		iManage.setImage(iManage.getImage().getScaledInstance(30,30, 0));
		ImageIcon iDevice2 = new ImageIcon("icons\\iconDevice.png");
		iDevice2.setImage(iDevice2.getImage().getScaledInstance(27,27, 0));
		ImageIcon iType2 = new ImageIcon("icons\\iconType.png");
		iType2.setImage(iType2.getImage().getScaledInstance(27,27, 0));
		ImageIcon iHome2 = new ImageIcon("icons\\iconHome.png");
		iHome2.setImage(iHome2.getImage().getScaledInstance(27,27, 0));
		
////////////////////////////////////////////////////////////////////////////Start Member Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		JPanel pExit = new JPanel();
		pExit.setBackground(color1);
		pExit.setLayout(null);
		pExit.setBounds(0, 566, 204, 106);
		contentPane.add(pExit);
		
		JButton bExit = new JButton();
		ImageIcon iOut = new ImageIcon("icons\\iconOut.png");
		iOut.setImage(iOut.getImage().getScaledInstance(25,25, 0));
		bExit.setIcon(iOut);
		bExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				setVisible(false);
			}
		});
		bExit.setForeground(Color.DARK_GRAY);
		bExit.setFont(new Font("Arial", Font.PLAIN, 20));
		bExit.setBackground(Color.WHITE);
		bExit.setBounds(75, 10, 108, 50);
		pExit.add(bExit);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 986, 663);
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 19));
		contentPane.add(tabbedPane);
		
		JPanel pMember = new JPanel();
		pMember.setBackground(color1);
		pMember.setToolTipText("");
		tabbedPane.addTab("Member Management", null, pMember, null);
		tabbedPane.setIconAt(0, iMember);
		pMember.setLayout(null);
		
		JTabbedPane tabbedPane_Member = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Member.setForeground(Color.DARK_GRAY);
		tabbedPane_Member.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane_Member.setFont(new Font("Arial", Font.PLAIN, 20));
		tabbedPane_Member.setBounds(-1, 520, 720, 138);
		pMember.add(tabbedPane_Member);
		
		JPanel pMemberAdd = new JPanel();
		pMemberAdd.setFont(new Font("Arial", Font.PLAIN, 10));
		tabbedPane_Member.addTab("", null, pMemberAdd, null);
		tabbedPane_Member.setIconAt(0, iAdd);
		pMemberAdd.setLayout(null);
		
		tID = new JTextField();
		tID.setFont(new Font("Arial", Font.PLAIN, 15));
		tID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tID.setBounds(36, 10, 100, 50);
		pMemberAdd.add(tID);
		tID.setColumns(10);
		
		tName = new JTextField();
		tName.setFont(new Font("Arial", Font.PLAIN, 15));
		tName.setColumns(10);
		tName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tName.setBounds(172, 10, 145, 50);
		pMemberAdd.add(tName);
		
		JComboBox cGen = new JComboBox();
		cGen.setForeground(Color.DARK_GRAY);
		cGen.setBackground(Color.WHITE);
		cGen.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cGen.setSelectedIndex(0);
		cGen.setMaximumRowCount(3);
		cGen.setFont(new Font("Arial", Font.PLAIN, 15));
		cGen.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cGen.setBounds(353, 10, 100, 50);
		pMemberAdd.add(cGen);
		
		tHome = new JTextField();
		tHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tHome.setColumns(10);
		tHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHome.setBounds(489, 10, 100, 50);
		pMemberAdd.add(tHome);
		
		JButton bAdd = new JButton("Apply");
		bAdd.setForeground(Color.DARK_GRAY);
		bAdd.setBackground(Color.WHITE);
		bAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		bAdd.setBounds(534, 70, 85, 27);
		pMemberAdd.add(bAdd);
		
		JButton bCheck = new JButton("Check");
		bCheck.setForeground(Color.DARK_GRAY);
		bCheck.setBackground(Color.WHITE);
		bCheck.setBounds(46, 59, 80, 21);
		pMemberAdd.add(bCheck);
		
		tCheck = new JTextField();
		tCheck.setHorizontalAlignment(SwingConstants.CENTER);
		tCheck.setBorder(null);
		tCheck.setEditable(false);
		tCheck.setBackground(color1);
		tCheck.setBounds(46, 82, 80, 20);
		pMemberAdd.add(tCheck);
		tCheck.setColumns(10);
		
		JPanel pMemberDelete = new JPanel();
		tabbedPane_Member.addTab("", null, pMemberDelete, null);
		tabbedPane_Member.setIconAt(1, iDelete);
		pMemberDelete.setLayout(null);
		
		tID2 = new JTextField();
		tID2.setFont(new Font("Arial", Font.PLAIN, 15));
		tID2.setColumns(10);
		tID2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tID2.setBounds(36, 10, 100, 50);
		pMemberDelete.add(tID2);
		
		tName2 = new JTextField();
		tName2.setFont(new Font("Arial", Font.PLAIN, 15));
		tName2.setEditable(false);
		tName2.setColumns(10);
		tName2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tName2.setBounds(172, 10, 145, 50);
		pMemberDelete.add(tName2);
		
		tHome2 = new JTextField();
		tHome2.setFont(new Font("Arial", Font.PLAIN, 15));
		tHome2.setEditable(false);
		tHome2.setColumns(10);
		tHome2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHome2.setBounds(489, 10, 100, 50);
		pMemberDelete.add(tHome2);
		
		JButton bDelete = new JButton("Delete");
		bDelete.setForeground(Color.DARK_GRAY);
		bDelete.setBackground(Color.WHITE);
		bDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bDelete.setBounds(534, 70, 85, 27);
		pMemberDelete.add(bDelete);
		
		JButton bCheck2 = new JButton("Check");
		bCheck2.setForeground(Color.DARK_GRAY);
		bCheck2.setBackground(Color.WHITE);
		bCheck2.setBounds(46, 59, 80, 21);
		pMemberDelete.add(bCheck2);
		
		tCheck2 = new JTextField();
		tCheck2.setHorizontalAlignment(SwingConstants.CENTER);
		tCheck2.setEditable(false);
		tCheck2.setColumns(10);
		tCheck2.setBorder(null);
		tCheck2.setBackground(color1);
		tCheck2.setBounds(46, 82, 80, 20);
		pMemberDelete.add(tCheck2);
		
		tGen2 = new JTextField();
		tGen2.setFont(new Font("Arial", Font.PLAIN, 15));
		tGen2.setEditable(false);
		tGen2.setColumns(10);
		tGen2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tGen2.setBounds(353, 10, 100, 50);
		pMemberDelete.add(tGen2);
		
		JPanel pMemberModify = new JPanel();
		tabbedPane_Member.addTab("", null, pMemberModify, null);
		tabbedPane_Member.setIconAt(2, iModify);
		pMemberModify.setLayout(null);
		
		tID3 = new JTextField();
		tID3.setFont(new Font("Arial", Font.PLAIN, 15));
		tID3.setColumns(10);
		tID3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tID3.setBounds(36, 10, 100, 50);
		pMemberModify.add(tID3);
		
		tName3 = new JTextField();
		tName3.setFont(new Font("Arial", Font.PLAIN, 15));
		tName3.setColumns(10);
		tName3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Member Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tName3.setBounds(172, 10, 145, 50);
		pMemberModify.add(tName3);
		
		JComboBox cGen3 = new JComboBox();
		cGen3.setForeground(Color.DARK_GRAY);
		cGen3.setBackground(Color.WHITE);
		cGen3.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		cGen3.setMaximumRowCount(3);
		cGen3.setFont(new Font("Arial", Font.PLAIN, 15));
		cGen3.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cGen3.setBounds(353, 10, 100, 50);
		pMemberModify.add(cGen3);
		
		tHome3 = new JTextField();
		tHome3.setFont(new Font("Arial", Font.PLAIN, 15));
		tHome3.setColumns(10);
		tHome3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHome3.setBounds(489, 10, 100, 50);
		pMemberModify.add(tHome3);
		
		JButton bModify = new JButton("Save");
		bModify.setForeground(Color.DARK_GRAY);
		bModify.setBackground(Color.WHITE);
		bModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bModify.setBounds(534, 70, 85, 27);
		pMemberModify.add(bModify);
		
		JButton bCheck3 = new JButton("Check");
		bCheck3.setForeground(Color.DARK_GRAY);
		bCheck3.setBackground(Color.WHITE);
		bCheck3.setBounds(46, 59, 80, 21);
		pMemberModify.add(bCheck3);
		
		tCheck3 = new JTextField();
		tCheck3.setHorizontalAlignment(SwingConstants.CENTER);
		tCheck3.setEditable(false);
		tCheck3.setColumns(10);
		tCheck3.setBorder(null);
		tCheck3.setBackground(color1);
		tCheck3.setBounds(46, 82, 80, 20);
		pMemberModify.add(tCheck3);
		
		JRadioButton rReset = new JRadioButton("Reset Password");
		rReset.setForeground(Color.DARK_GRAY);
		rReset.setFont(new Font("Arial", Font.PLAIN, 18));
		rReset.setBounds(344, 67, 161, 32);
		pMemberModify.add(rReset);
		
		JPanel pMemberFilter = new JPanel();
		pMemberFilter.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMemberFilter.setBounds(-1, 335, 344, 88);
		pMember.add(pMemberFilter);
		pMemberFilter.setLayout(null);
		
		JComboBox cGender = new JComboBox();
		cGender.setForeground(Color.DARK_GRAY);
		cGender.setBackground(Color.WHITE);
		cGender.setModel(new DefaultComboBoxModel(new String[] {"All", "Male", "Female"}));
		cGender.setSelectedIndex(0);
		cGender.setBounds(127, 17, 89, 54);
		cGender.setMaximumRowCount(3);
		cGender.setFont(new Font("Arial", Font.PLAIN, 15));
		cGender.setBorder(new TitledBorder(null, "Gender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMemberFilter.add(cGender);
		
		JPanel pMemberSorting = new JPanel();
		pMemberSorting.setBorder(new TitledBorder(null, "Sorting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMemberSorting.setBounds(345, 335, 374, 88);
		pMember.add(pMemberSorting);
		pMemberSorting.setLayout(null);
		
		JComboBox cSort = new JComboBox();
		cSort.setForeground(Color.DARK_GRAY);
		cSort.setBackground(Color.WHITE);
		cSort.setModel(new DefaultComboBoxModel(new String[] {"Member ID", "Member Name", "Registration Year", "Home ID"}));
		cSort.setSelectedIndex(0);
		cSort.setBounds(89, 29, 146, 30);
		cSort.setMaximumRowCount(10);
		cSort.setFont(new Font("Arial", Font.PLAIN, 15));
		pMemberSorting.add(cSort);
		
		JPanel pMemberSearch = new JPanel();
		pMemberSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pMemberSearch.setBounds(-1, 422, 720, 101);
		pMember.add(pMemberSearch);
		pMemberSearch.setLayout(null);
		
		tContent = new JTextField();
		tContent.setFont(new Font("Arial", Font.PLAIN, 17));
		tContent.setBounds(114, 29, 146, 42);
		pMemberSearch.add(tContent);
		tContent.setColumns(10);
		
		JComboBox cSearchType = new JComboBox();
		cSearchType.setForeground(Color.DARK_GRAY);
		cSearchType.setBackground(Color.WHITE);
		cSearchType.setFont(new Font("Arial", Font.PLAIN, 15));
		cSearchType.setModel(new DefaultComboBoxModel(new String[] {"Member ID", "Member Name"}));
		cSearchType.setSelectedIndex(0);
		cSearchType.setBounds(260, 29, 130, 42);
		pMemberSearch.add(cSearchType);
		
		JButton bSearch = new JButton("Search");
		bSearch.setIcon(iSearch);
		bSearch.setForeground(Color.DARK_GRAY);
		bSearch.setBackground(Color.WHITE);
		bSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		bSearch.setBounds(463, 34, 105, 33);
		pMemberSearch.add(bSearch);
			
		JScrollPane scrollPane_Member = new JScrollPane();
		scrollPane_Member.setBorder(new TitledBorder(null, "Member Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Member.setBounds(-1, 0, 720, 334);
		pMember.add(scrollPane_Member);
		
		tabMem = new JTable();
		tabMem.setGridColor(SystemColor.controlShadow);
		tabMem.setForeground(Color.DARK_GRAY);
		tabMem.setEnabled(false);
		tabMem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabMem.setBorder(new LineBorder(new Color(0, 0, 0)));
		DefaultTableModel modelMember = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Member ID", "Member Name", "Gender", "Register Year", "Home ID"
			}
		);
		tabMem.setModel(modelMember);
		tabMem.getColumnModel().getColumn(0).setPreferredWidth(61);
		tabMem.getColumnModel().getColumn(1).setPreferredWidth(79);
		tabMem.getColumnModel().getColumn(4).setPreferredWidth(63);
		scrollPane_Member.setViewportView(tabMem);
		
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0");
			modelMember.setRowCount(0);
			while(myRs.next()) {
				modelMember.addRow(new String[] {myRs.getString("MemID"), myRs.getString("MemName"), myRs.getString("MemGender"), myRs.getString("MemRegistYear"), myRs.getString("HomeID")});
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		String[] filter1 = {"is not null", "='Male'", "='Female'"};
		String[] sorting1 = {"MemID", "MemName", "MemRegistYear", "HomeID"};
		
		//Member Filter
		cGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					if (!tContent.getText().equals("")) {
						if (cSearchType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemID = '"
														+ tContent.getText() + "'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemName LIKE '%"
														+ tContent.getText() + "%'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						
					}
					else {
						myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()]
													+ " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
					}
					modelMember.setRowCount(0);
					while(myRs.next()) {
						modelMember.addRow(new String[] {myRs.getString("MemID"), myRs.getString("MemName"), myRs.getString("MemGender"), myRs.getString("MemRegistYear"), myRs.getString("HomeID")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		//Member Sorting
		cSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					if (!tContent.getText().equals("")) {
						if (cSearchType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemID = '"
														+ tContent.getText() + "'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemName LIKE '%"
														+ tContent.getText() + "%'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						
					}
					else {
						myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()]
													+ " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
					}
					modelMember.setRowCount(0);
					while(myRs.next()) {
						modelMember.addRow(new String[] {myRs.getString("MemID"), myRs.getString("MemName"), myRs.getString("MemGender"), myRs.getString("MemRegistYear"), myRs.getString("HomeID")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		//Member Search
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					if (!tContent.getText().equals("")) {
						if (cSearchType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemID = '"
														+ tContent.getText() + "'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()] + " AND MemName LIKE '%"
														+ tContent.getText() + "%'" + " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
						}
						
					}
					else {
						myRs = myStmt.executeQuery("SELECT * FROM Member WHERE MemDel = 0 AND Memgender " + filter1[cGender.getSelectedIndex()]
													+ " ORDER BY " + sorting1[cSort.getSelectedIndex()]);
					}
					modelMember.setRowCount(0);
					while(myRs.next()) {
						modelMember.addRow(new String[] {myRs.getString("MemID"), myRs.getString("MemName"), myRs.getString("MemGender"), myRs.getString("MemRegistYear"), myRs.getString("HomeID")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		cSearchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tContent.setText("");
			}
		});
		
		///////Start Member Management Add Logic////////////
		
		//Member Add Check Logic
		bCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if (tID.getText().equals("")) {
					tCheck.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemID = '" + tID.getText() + "'");
						if (myRs.next()) {
							tCheck.setText("ID exist");
						}
						else {
							tCheck.setText("Pass");
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Member Add Logic
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tCheck.setText("");
				//ID is null
				if (tID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Member ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemID = '" + tID.getText() + "'");
						//ID not pass
						if (myRs.next()) {
							JOptionPane.showMessageDialog(null, "Member ID already exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//ID pass
						else {
							//Name is null
							if (tName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Member name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt1 = conn.createStatement();
								ResultSet myRs1 = myStmt1.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tHome.getText() + "'");
								//HomeID not pass
								if (!myRs1.next()) {
									JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
								}
								//HomeID pass
								else {
									Statement myStmt2 = conn.createStatement();
									ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) FROM Member WHERE HomeID = '" + tHome.getText() + "'");
									myRs2.next();
								 	//Number not pass
									if (myRs2.getInt("COUNT(*)") >= 10) {
										JOptionPane.showMessageDialog(null, "Home Members more than 10!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Number pass, all pass!
									else {
										Calendar cal = Calendar.getInstance();
										int year = cal.get(Calendar.YEAR);
										//System.out.println("INSERT INTO Member VALUES ('" + tID.getText() + "','" + tHome.getText() + "','" + MD5.encode(tID.getText()) + "'," + year + ",'" + tName.getText() + "','" + cGen.getSelectedItem() + "',0);");
										Statement myStmt3 = conn.createStatement();
										myStmt3.executeUpdate("INSERT INTO Member VALUES ('" + tID.getText() + "','" + tHome.getText() + "','" + MD5.encode(tID.getText()) + "'," + year + ",'" + tName.getText() + "','" + cGen.getSelectedItem() + "',0);");
										JOptionPane.showMessageDialog(null, "Successfully added!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										cGender.setSelectedIndex(0);
										cSort.setSelectedIndex(0);
										tContent.setText("");
										cSearchType.setSelectedIndex(0);
										tID.setText("");
										tName.setText("");
										cGen.setSelectedIndex(0);
										tHome.setText("");
										//Refresh JTable
										Statement myStmt4 = conn.createStatement();
										ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Member WHERE MemDel = 0");
										modelMember.setRowCount(0);
										while(myRs4.next()) {
											modelMember.addRow(new String[] {myRs4.getString("MemID"), myRs4.getString("MemName"), myRs4.getString("MemGender"), myRs4.getString("MemRegistYear"), myRs4.getString("HomeID")});
										}
									}
								}
							}
							
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		///////Start Member Management Delete Logic////////////		
		
		//Check Button
		bCheck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tName2.setText("");
				tGen2.setText("");
				tHome2.setText("");
				if (tID2.getText().equals("")) {
					tCheck2.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemDel = 0 AND MemID = '" + tID2.getText() + "'");
						if (!myRs.next()) {
							tCheck2.setText("ID not exist");
						}
						else {
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Member WHERE MemID = '" + tID2.getText() + "'");
							myRs1.next();
							tName2.setText(myRs1.getString("MemName"));
							tGen2.setText(myRs1.getString("MemGender"));
							tHome2.setText(myRs1.getString("HomeID"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Delete Button
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Member ID is null
				if (tID2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Member ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Member ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemDel = 0 AND MemID = '" + tID2.getText() + "'");
						//Member ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Member not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Deletable
						else {
							Statement myStmt1 = conn.createStatement();
							myStmt1.executeUpdate("UPDATE Member SET MemDel = 1 WHERE MemID = '" + tID2.getText() + "'");
							JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",JOptionPane.INFORMATION_MESSAGE);
							//Refresh Component
							cGender.setSelectedIndex(0);
							cSort.setSelectedIndex(0);
							tContent.setText("");
							cSearchType.setSelectedIndex(0);
							tID2.setText("");
							tName2.setText("");
							tGen2.setText("");
							tHome2.setText("");
							tCheck2.setText("");
							//Refresh JTable
							Statement myStmt4 = conn.createStatement();
							ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Member WHERE MemDel = 0");
							modelMember.setRowCount(0);
							while(myRs4.next()) {
								modelMember.addRow(new String[] {myRs4.getString("MemID"), myRs4.getString("MemName"), myRs4.getString("MemGender"), myRs4.getString("MemRegistYear"), myRs4.getString("HomeID")});
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		
		///////Start Member Management Modify Logic////////////	
		
		//Check Button
		bCheck3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Refresh Component
				tName3.setText("");
				cGen3.setSelectedIndex(0);
				tHome3.setText("");
				//rReset.setSelected(false);
				if (tID3.getText().equals("")) {
					tCheck3.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemDel = 0 AND MemID = '" + tID3.getText() + "'");
						if (!myRs.next()) {
							tCheck3.setText("ID not exist");
						}
						else {
							//Editable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Member WHERE MemID = '" + tID3.getText() + "'");
							myRs1.next();
							tName3.setText(myRs1.getString("MemName"));
							if (myRs1.getString("MemGender").equals("Male")) { cGen3.setSelectedIndex(0); }
							else { cGen3.setSelectedIndex(1); }
							tHome3.setText(myRs1.getString("HomeID"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Modify Button
		bModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Member ID is null
				if (tID3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Member ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Member ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT MemID FROM Member WHERE MemDel = 0 AND MemID = '" + tID3.getText() + "'");
						//Member ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Member not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Member ID exist
						else {
							//Name is null
							if (tName3.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Member name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt1 = conn.createStatement();
								ResultSet myRs1 = myStmt1.executeQuery("SELECT HomeID FROM Member WHERE HomeID = '" + tHome3.getText() + "'");
								//HomeID not pass
								if (!myRs1.next()) {
									JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
								}
								//HomeID pass
								else {
									Statement myStmt2 = conn.createStatement();
									ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) FROM Member WHERE HomeID = '" + tHome3.getText() + "'");
									myRs2.next();
									//Number not pass
									if (myRs2.getInt("COUNT(*)") >= 10) {
										JOptionPane.showMessageDialog(null, "Home Members more than 10!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Editable
									else {
										Statement myStmt5 = conn.createStatement();
										myStmt5.executeUpdate("UPDATE Member SET MemName = '" + tName3.getText() +"', MemGender = '" + cGen3.getSelectedItem() + "', HomeID = '" + tHome3.getText() + "' WHERE MemID = '" + tID3.getText() + "'");
										//Reset Password
										if (rReset.isSelected()) {
											Statement myStmt3 = conn.createStatement();
											myStmt3.executeUpdate("UPDATE Member SET MemPassword = '" + MD5.encode(tID3.getText()) + "' WHERE MemID = '" + tID3.getText() + "'");
										}
										JOptionPane.showMessageDialog(null, "Successfully modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tID3.setText("");
										tName3.setText("");
										cGen3.setSelectedIndex(0);
										cGender.setSelectedIndex(0);
										cSort.setSelectedIndex(0);
										cSearchType.setSelectedIndex(0);
										tHome3.setText("");
										tCheck3.setText("");
										rReset.setSelected(false);
										//Refresh JTable
										Statement myStmt4 = conn.createStatement();
										ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Member WHERE MemDel = 0");
										modelMember.setRowCount(0);
										while(myRs4.next()) {
											modelMember.addRow(new String[] {myRs4.getString("MemID"), myRs4.getString("MemName"), myRs4.getString("MemGender"), myRs4.getString("MemRegistYear"), myRs4.getString("HomeID")});
										}
									}
								}
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		
		
////////////////////////////////////////////////////////////////////////////Start Home Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		JPanel pHome = new JPanel();
		tabbedPane.addTab("Home Management   ", null, pHome, null);
		tabbedPane.setIconAt(1, iHome);
		pHome.setLayout(null);
		pHome.setBackground(color1);
		
		JScrollPane scrollPane_Home = new JScrollPane();
		scrollPane_Home.setBorder(new TitledBorder(null, "Home Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Home.setBounds(0, 0, 719, 420);
		pHome.add(scrollPane_Home);
		
		tabHome = new JTable();
		tabHome.setEnabled(false);
		DefaultTableModel modelHome = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Home ID", "Home Address"
			}
		);
		tabHome.setModel(modelHome);
		tabHome.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabHome.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane_Home.setViewportView(tabHome);
		
		JTabbedPane tabbedPane_Home = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Home.setForeground(Color.DARK_GRAY);
		tabbedPane_Home.setFont(new Font("Arial", Font.PLAIN, 20));
		tabbedPane_Home.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane_Home.setBounds(0, 520, 719, 138);
		pHome.add(tabbedPane_Home);
		
		JPanel pHomeAdd = new JPanel();
		tabbedPane_Home.addTab("", null, pHomeAdd, null);
		tabbedPane_Home.setIconAt(0, iAdd);
		pHomeAdd.setLayout(null);
		
		tHomeID = new JTextField();
		tHomeID.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeID.setColumns(10);
		tHomeID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeID.setBounds(40, 10, 100, 50);
		pHomeAdd.add(tHomeID);
		
		JButton bHomeCheck = new JButton("Check");
		bHomeCheck.setForeground(Color.DARK_GRAY);
		bHomeCheck.setBackground(Color.WHITE);
		bHomeCheck.setBounds(49, 59, 80, 21);
		pHomeAdd.add(bHomeCheck);
		
		tHomeCheck = new JTextField();
		tHomeCheck.setHorizontalAlignment(SwingConstants.CENTER);
		tHomeCheck.setEditable(false);
		tHomeCheck.setColumns(10);
		tHomeCheck.setBorder(null);
		tHomeCheck.setBackground(color1);
		tHomeCheck.setBounds(49, 82, 80, 20);
		pHomeAdd.add(tHomeCheck);
		
		tHomeAddress = new JTextField();
		tHomeAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeAddress.setColumns(10);
		tHomeAddress.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeAddress.setBounds(176, 10, 381, 50);
		pHomeAdd.add(tHomeAddress);
		
		JButton bHomeAdd = new JButton("Apply");
		bHomeAdd.setForeground(Color.DARK_GRAY);
		bHomeAdd.setBackground(Color.WHITE);
		bHomeAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		bHomeAdd.setBounds(534, 70, 85, 27);
		pHomeAdd.add(bHomeAdd);
		
		JPanel pHomeDelete = new JPanel();
		tabbedPane_Home.addTab("", null, pHomeDelete, null);
		tabbedPane_Home.setIconAt(1, iDelete);
		pHomeDelete.setLayout(null);
		
		tHomeID2 = new JTextField();
		tHomeID2.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeID2.setColumns(10);
		tHomeID2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeID2.setBounds(40, 10, 100, 50);
		pHomeDelete.add(tHomeID2);
		
		JButton bHomeCheck2 = new JButton("Check");
		bHomeCheck2.setForeground(Color.DARK_GRAY);
		bHomeCheck2.setBackground(Color.WHITE);
		bHomeCheck2.setBounds(49, 59, 80, 21);
		pHomeDelete.add(bHomeCheck2);
		
		tHomeCheck2 = new JTextField();
		tHomeCheck2.setHorizontalAlignment(SwingConstants.CENTER);
		tHomeCheck2.setEditable(false);
		tHomeCheck2.setColumns(10);
		tHomeCheck2.setBorder(null);
		tHomeCheck2.setBackground(color1);
		tHomeCheck2.setBounds(49, 82, 80, 20);
		pHomeDelete.add(tHomeCheck2);
		
		tHomeAddress2 = new JTextField();
		tHomeAddress2.setEditable(false);
		tHomeAddress2.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeAddress2.setColumns(10);
		tHomeAddress2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeAddress2.setBounds(176, 10, 381, 50);
		pHomeDelete.add(tHomeAddress2);
		
		JButton bHomeDelete = new JButton("Delete");
		bHomeDelete.setForeground(Color.DARK_GRAY);
		bHomeDelete.setBackground(Color.WHITE);
		bHomeDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bHomeDelete.setBounds(534, 70, 85, 27);
		pHomeDelete.add(bHomeDelete);
		
		JPanel pHomeModify = new JPanel();
		tabbedPane_Home.addTab("", null, pHomeModify, null);
		tabbedPane_Home.setIconAt(2, iModify);
		pHomeModify.setLayout(null);
		
		tHomeID3 = new JTextField();
		tHomeID3.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeID3.setColumns(10);
		tHomeID3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeID3.setBounds(40, 10, 100, 50);
		pHomeModify.add(tHomeID3);
		
		JButton bHomeCheck3 = new JButton("Check");
		bHomeCheck3.setForeground(Color.DARK_GRAY);
		bHomeCheck3.setBackground(Color.WHITE);
		bHomeCheck3.setBounds(49, 59, 80, 21);
		pHomeModify.add(bHomeCheck3);
		
		tHomeCheck3 = new JTextField();
		tHomeCheck3.setHorizontalAlignment(SwingConstants.CENTER);
		tHomeCheck3.setEditable(false);
		tHomeCheck3.setColumns(10);
		tHomeCheck3.setBorder(null);
		tHomeCheck3.setBackground(color1);
		tHomeCheck3.setBounds(49, 82, 80, 20);
		pHomeModify.add(tHomeCheck3);
		
		tHomeAddress3 = new JTextField();
		tHomeAddress3.setFont(new Font("Arial", Font.PLAIN, 15));
		tHomeAddress3.setColumns(10);
		tHomeAddress3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tHomeAddress3.setBounds(176, 10, 381, 50);
		pHomeModify.add(tHomeAddress3);
		
		JButton bHomeModify = new JButton("Save");
		bHomeModify.setForeground(Color.DARK_GRAY);
		bHomeModify.setBackground(Color.WHITE);
		bHomeModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bHomeModify.setBounds(534, 70, 85, 27);
		pHomeModify.add(bHomeModify);
		
		JPanel pSearch_1 = new JPanel();
		pSearch_1.setLayout(null);
		pSearch_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pSearch_1.setBounds(0, 422, 719, 101);
		pHome.add(pSearch_1);
		
		tContent2 = new JTextField();
		tContent2.setFont(new Font("Arial", Font.PLAIN, 17));
		tContent2.setColumns(10);
		tContent2.setBounds(203, 29, 266, 42);
		pSearch_1.add(tContent2);
		
		JButton bSearch2 = new JButton("Search");
		bSearch2.setIcon(iSearch);
		bSearch2.setForeground(Color.DARK_GRAY);
		bSearch2.setBackground(Color.WHITE);
		bSearch2.setFont(new Font("Arial", Font.PLAIN, 15));
		bSearch2.setBounds(522, 34, 105, 33);
		pSearch_1.add(bSearch2);
		
		JLabel lblNewLabel = new JLabel("Home ID:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(105, 29, 88, 42);
		pSearch_1.add(lblNewLabel);
		
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Home WHERE HomeDel = 0");
			modelHome.setRowCount(0);
			while(myRs.next()) {
				modelHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("Address")});
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		//Home Search
		bSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					if (!tContent2.getText().equals("")) {
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Home WHERE HomeDel = 0 AND HomeID = '" + tContent2.getText() + "'");
						modelHome.setRowCount(0);
						while(myRs1.next()) {
							modelHome.addRow(new String[] {myRs1.getString("HomeID"), myRs1.getString("Address")});
						}
					}
					else {
						Statement myStmt2 = conn.createStatement();
						ResultSet myRs2 = myStmt2.executeQuery("SELECT * FROM Home WHERE HomeDel = 0");
						modelHome.setRowCount(0);
						while(myRs2.next()) {
							modelHome.addRow(new String[] {myRs2.getString("HomeID"), myRs2.getString("Address")});
						}
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		///////Start Home Management Add Logic////////////
	
		//Home Add Check Logic
		bHomeCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if (tHomeID.getText().equals("")) {
					tHomeCheck.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeID = '" + tHomeID.getText() + "'");
						if (myRs.next()) {
							tHomeCheck.setText("ID exist");
						}
						else {
							tHomeCheck.setText("Pass");
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Home Add Logic
		bHomeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tHomeCheck.setText("");
				//ID is null
				if (tHomeID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Home ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeID = '" + tHomeID.getText() + "'");
						//ID not pass
						if (myRs.next()) {
							JOptionPane.showMessageDialog(null, "Home ID already exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//ID pass
						else {
							//Address is null
							if (tHomeAddress.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Home address can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Address pass
							else {
								Statement myStmt3 = conn.createStatement();
								myStmt3.executeUpdate("INSERT INTO Home VALUES ('" + tHomeID.getText() + "','" + tHomeAddress.getText() + "',0);");
								JOptionPane.showMessageDialog(null, "Successfully added!", "Success",JOptionPane.INFORMATION_MESSAGE);
								//Refresh Component
								tContent2.setText("");
								tHomeID.setText("");
								tHomeAddress.setText("");
								//Refresh JTable
								Statement myStmt4 = conn.createStatement();
								ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Home WHERE HomeDel = 0");
								modelHome.setRowCount(0);
								while(myRs4.next()) {
									modelHome.addRow(new String[] {myRs4.getString("HomeID"), myRs4.getString("Address")});
								}
							}
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		///////Start Home Management Delete Logic////////////		
		
		//Check Button
		bHomeCheck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tHomeAddress2.setText("");
				if (tHomeID2.getText().equals("")) {
					tHomeCheck2.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tHomeID2.getText() + "'");
						if (!myRs.next()) {
							tHomeCheck2.setText("ID not exist");
						}
						else {
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT Address FROM Home WHERE HomeID = '" + tHomeID2.getText() + "'");
							myRs1.next();
							tHomeAddress2.setText(myRs1.getString("Address"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Delete Button
		bHomeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Home ID is null
				if (tHomeID2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Home ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Home ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tHomeID2.getText() + "'");
						//Home ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Deletable
						else {
							Statement myStmt1 = conn.createStatement();
							myStmt1.executeUpdate("UPDATE Member SET MemDel = 1 WHERE HomeID = '" + tHomeID2.getText() + "'");
							myStmt1.executeUpdate("UPDATE Data SET DataDel = 1 WHERE DeviceID IN (SELECT DeviceID FROM Device WHERE HomeID = '" + tHomeID2.getText() + "')");
							myStmt1.executeUpdate("UPDATE Device SET DeviceDel = 1 WHERE HomeID = '" + tHomeID2.getText() + "'");
							myStmt1.executeUpdate("UPDATE Home SET HomeDel = 1 WHERE HomeID = '" + tHomeID2.getText() + "'");
							JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",JOptionPane.INFORMATION_MESSAGE);
							//Refresh Component
							tHomeID2.setText("");
							tHomeAddress2.setText("");
							tHomeCheck2.setText("");
							tContent2.setText("");
							//Refresh JTable
							Statement myStmt4 = conn.createStatement();
							ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Home WHERE HomeDel = 0");
							modelHome.setRowCount(0);
							while(myRs4.next()) {
								modelHome.addRow(new String[] {myRs4.getString("HomeID"), myRs4.getString("Address")});
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		///////Start Home Management Modify Logic////////////	
		
		//Check Button
		bHomeCheck3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Refresh Component
				tHomeAddress3.setText("");
				if (tHomeID3.getText().equals("")) {
					tHomeCheck3.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tHomeID3.getText() + "'");
						if (!myRs.next()) {
							tHomeCheck3.setText("ID not exist");
						}
						else {
							//Editable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Home WHERE HomeID = '" + tHomeID3.getText() + "'");
							myRs1.next();
							tHomeAddress3.setText(myRs1.getString("Address"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Modify Button
		bHomeModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Home ID is null
				if (tHomeID3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Home ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Home ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tHomeID3.getText() + "'");
						//Home ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Home ID exist
						else {
							//Address is null
							if (tHomeAddress3.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Home address can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Address pass
							else {
								Statement myStmt1 = conn.createStatement();
								myStmt1.executeUpdate("UPDATE Home SET Address = '" + tHomeAddress3.getText() + "' WHERE HomeID = '" + tHomeID3.getText() + "'");
								JOptionPane.showMessageDialog(null, "Successfully modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
								//Refresh Component
								tContent2.setText("");
								tHomeID3.setText("");
								tHomeAddress3.setText("");
								//Refresh JTable
								Statement myStmt4 = conn.createStatement();
								ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Home WHERE HomeDel = 0");
								modelHome.setRowCount(0);
								while(myRs4.next()) {
									modelHome.addRow(new String[] {myRs4.getString("HomeID"), myRs4.getString("Address")});
								}
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		
////////////////////////////////////////////////////////////////////////////Start Device Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		JPanel pDevice = new JPanel();
		tabbedPane.addTab("Device Management  ", null, pDevice, null);
		tabbedPane.setIconAt(2, iDevice);
		pDevice.setLayout(null);
		pDevice.setBackground(color1);
		
		JScrollPane scrollPane_Device = new JScrollPane();
		scrollPane_Device.setBorder(new TitledBorder(null, "Device Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Device.setBounds(0, 10, 720, 262);
		pDevice.add(scrollPane_Device);
		
		tabDevice = new JTable();
		tabDevice.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabDevice.setEnabled(false);
		tabDevice.setFillsViewportHeight(true);
		tabDevice.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabDevice.setFont(new Font("Arial", Font.PLAIN, 15));
		DefaultTableModel modelDevice = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Device ID", "Device Name", "Home ID", "Manufacturer ID", "Type ID", "Device Intro"
			}
		);
		tabDevice.setModel(modelDevice);
		tabDevice.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabDevice.getColumnModel().getColumn(3).setPreferredWidth(90);
		tabDevice.getColumnModel().getColumn(5).setPreferredWidth(500);
		scrollPane_Device.setViewportView(tabDevice);
		
		JPanel pDeviceSearch = new JPanel();
		pDeviceSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceSearch.setBounds(0, 347, 719, 88);
		pDevice.add(pDeviceSearch);
		pDeviceSearch.setLayout(null);
		
		tContent3 = new JTextField();
		tContent3.setFont(new Font("Arial", Font.PLAIN, 17));
		tContent3.setColumns(10);
		tContent3.setBounds(93, 23, 202, 42);
		pDeviceSearch.add(tContent3);
		
		JComboBox cSearchType2 = new JComboBox();
		cSearchType2.setForeground(Color.DARK_GRAY);
		cSearchType2.setBackground(Color.WHITE);
		cSearchType2.setModel(new DefaultComboBoxModel(new String[] {"Device ID", "Device Name"}));
		cSearchType2.setSelectedIndex(0);
		cSearchType2.setFont(new Font("Arial", Font.PLAIN, 15));
		cSearchType2.setBounds(295, 23, 171, 42);
		pDeviceSearch.add(cSearchType2);
		
		JButton bSearch3 = new JButton("Search");
		bSearch3.setIcon(iSearch);
		bSearch3.setForeground(Color.DARK_GRAY);
		bSearch3.setBackground(Color.WHITE);
		bSearch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bSearch3.setFont(new Font("Arial", Font.PLAIN, 15));
		bSearch3.setBounds(527, 27, 105, 33);
		pDeviceSearch.add(bSearch3);
		
		JPanel pDeviceSorting = new JPanel();
		pDeviceSorting.setLayout(null);
		pDeviceSorting.setBorder(new TitledBorder(null, "Sorting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceSorting.setBounds(359, 271, 360, 77);
		pDevice.add(pDeviceSorting);
		
		JComboBox cDeviceSort = new JComboBox();
		cDeviceSort.setForeground(Color.DARK_GRAY);
		cDeviceSort.setBackground(Color.WHITE);
		cDeviceSort.setModel(new DefaultComboBoxModel(new String[] {"Device ID", "Device Name", "Home ID", "Manu ID", "Type ID"}));
		cDeviceSort.setSelectedIndex(0);
		cDeviceSort.setMaximumRowCount(10);
		cDeviceSort.setFont(new Font("Arial", Font.PLAIN, 15));
		cDeviceSort.setBounds(119, 23, 121, 30);
		pDeviceSorting.add(cDeviceSort);
		
		JPanel pDeviceFilter = new JPanel();
		pDeviceFilter.setLayout(null);
		pDeviceFilter.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceFilter.setBounds(0, 271, 360, 77);
		pDevice.add(pDeviceFilter);
		
		JComboBox cType = new JComboBox(typeBoxCutPlus);
		cType.setForeground(Color.DARK_GRAY);
		cType.setBackground(Color.WHITE);
		cType.setSelectedIndex(0);
		cType.setMaximumRowCount(10);
		cType.setFont(new Font("Arial", Font.PLAIN, 15));
		cType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cType.setBounds(112, 11, 136, 54);
		pDeviceFilter.add(cType);
		
		JTabbedPane tabbedPane_Device = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Device.setForeground(Color.DARK_GRAY);
		tabbedPane_Device.setFont(new Font("Arial", Font.PLAIN, 20));
		tabbedPane_Device.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane_Device.setBounds(0, 433, 720, 225);
		pDevice.add(tabbedPane_Device);
		
		JPanel pDeviceAdd = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceAdd, null);
		tabbedPane_Device.setIconAt(0, iAdd);
		pDeviceAdd.setLayout(null);
		
		tDeviceAddID = new JTextField();
		tDeviceAddID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceAddID.setBounds(10, 10, 78, 40);
		pDeviceAdd.add(tDeviceAddID);
		tDeviceAddID.setColumns(10);
		
		tDeviceAddName = new JTextField();
		tDeviceAddName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddName.setColumns(10);
		tDeviceAddName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceAddName.setBounds(98, 10, 101, 40);
		pDeviceAdd.add(tDeviceAddName);
		
		tDeviceAddManu = new JTextField();
		tDeviceAddManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddManu.setColumns(10);
		tDeviceAddManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manufacturer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceAddManu.setBounds(297, 10, 105, 40);
		pDeviceAdd.add(tDeviceAddManu);
		
		JTextArea tDeviceAddIntro = new JTextArea();
		tDeviceAddIntro.setLineWrap(true);
		tDeviceAddIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceAddIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceAddIntro.setBounds(10, 57, 514, 132);
		pDeviceAdd.add(tDeviceAddIntro);
		
		JButton bDeviceAdd = new JButton("Apply");
		bDeviceAdd.setForeground(Color.DARK_GRAY);
		bDeviceAdd.setBackground(Color.WHITE);
		bDeviceAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceAdd.setBounds(534, 162, 85, 27);
		pDeviceAdd.add(bDeviceAdd);
		
		JComboBox cDeviceAddType = new JComboBox(typeBoxCut);
		cDeviceAddType.setForeground(Color.DARK_GRAY);
		cDeviceAddType.setBackground(Color.WHITE);
		cDeviceAddType.setBorder(new TitledBorder(null, "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDeviceAddType.setBounds(412, 0, 112, 50);
		pDeviceAdd.add(cDeviceAddType);
		
		JButton bDeviceAddCheck = new JButton("Check");
		bDeviceAddCheck.setForeground(Color.DARK_GRAY);
		bDeviceAddCheck.setBackground(Color.WHITE);
		bDeviceAddCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceAddCheck.setBounds(545, 10, 74, 22);
		pDeviceAdd.add(bDeviceAddCheck);
		
		tDeviceAdd = new JTextField();
		tDeviceAdd.setEditable(false);
		tDeviceAdd.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceAdd.setColumns(10);
		tDeviceAdd.setBorder(null);
		tDeviceAdd.setBackground(color1);
		tDeviceAdd.setBounds(538, 42, 90, 23);
		pDeviceAdd.add(tDeviceAdd);
		
		tDeviceAddHome = new JTextField();
		tDeviceAddHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddHome.setColumns(10);
		tDeviceAddHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceAddHome.setBounds(209, 10, 78, 40);
		pDeviceAdd.add(tDeviceAddHome);
		
		JPanel pDeviceDelete = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceDelete, null);
		tabbedPane_Device.setIconAt(1, iDelete);
		pDeviceDelete.setLayout(null);
		
		tDeviceDeleteID = new JTextField();
		tDeviceDeleteID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteID.setColumns(10);
		tDeviceDeleteID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceDeleteID.setBounds(10, 10, 78, 40);
		pDeviceDelete.add(tDeviceDeleteID);
		
		tDeviceDeleteName = new JTextField();
		tDeviceDeleteName.setEditable(false);
		tDeviceDeleteName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteName.setColumns(10);
		tDeviceDeleteName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteName.setBounds(98, 10, 101, 40);
		pDeviceDelete.add(tDeviceDeleteName);
		
		tDeviceDeleteManu = new JTextField();
		tDeviceDeleteManu.setEditable(false);
		tDeviceDeleteManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteManu.setColumns(10);
		tDeviceDeleteManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manufacturer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteManu.setBounds(295, 10, 105, 40);
		pDeviceDelete.add(tDeviceDeleteManu);
		
		JTextArea tDeviceDeleteIntro = new JTextArea();
		tDeviceDeleteIntro.setBackground(UIManager.getColor("Button.background"));
		tDeviceDeleteIntro.setEditable(false);
		tDeviceDeleteIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceDeleteIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceDeleteIntro.setBounds(10, 57, 514, 132);
		pDeviceDelete.add(tDeviceDeleteIntro);
		
		JButton bDeviceDelete = new JButton("Delete");
		bDeviceDelete.setForeground(Color.DARK_GRAY);
		bDeviceDelete.setBackground(Color.WHITE);
		bDeviceDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceDelete.setBounds(534, 162, 85, 27);
		pDeviceDelete.add(bDeviceDelete);
		
		JButton bDeviceDeleteCheck = new JButton("Check");
		bDeviceDeleteCheck.setForeground(Color.DARK_GRAY);
		bDeviceDeleteCheck.setBackground(Color.WHITE);
		bDeviceDeleteCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceDeleteCheck.setBounds(545, 10, 74, 22);
		pDeviceDelete.add(bDeviceDeleteCheck);
		
		tDeviceDelete = new JTextField();
		tDeviceDelete.setEditable(false);
		tDeviceDelete.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceDelete.setBackground(color1);
		tDeviceDelete.setBorder(null);
		tDeviceDelete.setBounds(538, 42, 90, 23);
		pDeviceDelete.add(tDeviceDelete);
		tDeviceDelete.setColumns(10);
		
		tDeviceDeleteType = new JTextField();
		tDeviceDeleteType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteType.setEditable(false);
		tDeviceDeleteType.setColumns(10);
		tDeviceDeleteType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteType.setBounds(410, 10, 114, 40);
		pDeviceDelete.add(tDeviceDeleteType);
		
		tDeviceDeleteHome = new JTextField();
		tDeviceDeleteHome.setEditable(false);
		tDeviceDeleteHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteHome.setColumns(10);
		tDeviceDeleteHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteHome.setBounds(209, 10, 78, 40);
		pDeviceDelete.add(tDeviceDeleteHome);
		
		JPanel pDeviceModify = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceModify, null);
		tabbedPane_Device.setIconAt(2, iModify);
		pDeviceModify.setLayout(null);
		
		tDeviceModifyID = new JTextField();
		tDeviceModifyID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyID.setColumns(10);
		tDeviceModifyID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceModifyID.setBounds(10, 10, 78, 40);
		pDeviceModify.add(tDeviceModifyID);
		
		tDeviceModifyName = new JTextField();
		tDeviceModifyName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyName.setColumns(10);
		tDeviceModifyName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceModifyName.setBounds(98, 10, 101, 40);
		pDeviceModify.add(tDeviceModifyName);
		
		JTextArea tDeviceModifyIntro = new JTextArea();
		tDeviceModifyIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceModifyIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceModifyIntro.setBounds(10, 57, 514, 132);
		pDeviceModify.add(tDeviceModifyIntro);
		
		tDeviceModifyManu = new JTextField();
		tDeviceModifyManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyManu.setColumns(10);
		tDeviceModifyManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manufacturer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceModifyManu.setBounds(297, 10, 105, 40);
		pDeviceModify.add(tDeviceModifyManu);
		
		JButton bDeviceModifyCheck = new JButton("Check");
		bDeviceModifyCheck.setForeground(Color.DARK_GRAY);
		bDeviceModifyCheck.setBackground(Color.WHITE);
		bDeviceModifyCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceModifyCheck.setBounds(545, 10, 74, 22);
		pDeviceModify.add(bDeviceModifyCheck);
		
		JButton bDeviceModify = new JButton("Save");
		bDeviceModify.setForeground(Color.DARK_GRAY);
		bDeviceModify.setBackground(Color.WHITE);
		bDeviceModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceModify.setBounds(534, 162, 85, 27);
		pDeviceModify.add(bDeviceModify);
		
		tDeviceModify = new JTextField();
		tDeviceModify.setEditable(false);
		tDeviceModify.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceModify.setColumns(10);
		tDeviceModify.setBorder(null);
		tDeviceModify.setBackground(color1);
		tDeviceModify.setBounds(538, 42, 90, 23);
		pDeviceModify.add(tDeviceModify);
		
		JComboBox cDeviceModifyType = new JComboBox(typeBoxCut);
		cDeviceModifyType.setForeground(Color.DARK_GRAY);
		cDeviceModifyType.setBackground(Color.WHITE);
		cDeviceModifyType.setBorder(new TitledBorder(null, "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDeviceModifyType.setBounds(412, 0, 112, 50);
		pDeviceModify.add(cDeviceModifyType);
		
		tDeviceModifyHome = new JTextField();
		tDeviceModifyHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyHome.setColumns(10);
		tDeviceModifyHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceModifyHome.setBounds(209, 10, 78, 40);
		pDeviceModify.add(tDeviceModifyHome);
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0");
			modelDevice.setRowCount(0);
			while(myRs.next()) {
				modelDevice.addRow(new String[] {myRs.getString("DeviceID"), myRs.getString("DeviceName"), myRs.getString("HomeID"), myRs.getString("ManuID"), myRs.getString("TypeID"), myRs.getString("DeviceIntro")});
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		String[] sortingDevice = {"DeviceID", "DeviceName", "HomeID", "ManuID", "TypeID"};
		
		//Device Filter
		cType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					//Content is not null
					if (!tContent3.getText().equals("")) {
						//Search by ID
						if (cSearchType2.getSelectedIndex() == 0) {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '"
														+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
					}
					modelDevice.setRowCount(0);
					while(myRs.next()) {
						modelDevice.addRow(new String[] {myRs.getString("DeviceID"), myRs.getString("DeviceName"), myRs.getString("HomeID"), myRs.getString("ManuID"), myRs.getString("TypeID"), myRs.getString("DeviceIntro")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		//Device Sorting
		cDeviceSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					//Content is not null
					if (!tContent3.getText().equals("")) {
						//Search by ID
						if (cSearchType2.getSelectedIndex() == 0) {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '"
														+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
					}
					modelDevice.setRowCount(0);
					while(myRs.next()) {
						modelDevice.addRow(new String[] {myRs.getString("DeviceID"), myRs.getString("DeviceName"), myRs.getString("HomeID"), myRs.getString("ManuID"), myRs.getString("TypeID"), myRs.getString("DeviceIntro")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		//Device Search
		bSearch3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					//Content is not null
					if (!tContent3.getText().equals("")) {
						//Search by ID
						if (cSearchType2.getSelectedIndex() == 0) {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '"
														+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tContent3.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tContent3.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND TypeID = '" + typeIDBox[cType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
					}
					modelDevice.setRowCount(0);
					while(myRs.next()) {
						modelDevice.addRow(new String[] {myRs.getString("DeviceID"), myRs.getString("DeviceName"), myRs.getString("HomeID"), myRs.getString("ManuID"), myRs.getString("TypeID"), myRs.getString("DeviceIntro")});
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
				
				
		///////Start Device Management Add Logic////////////
		
		//Device Add Check Logic
		bDeviceAddCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if (tDeviceAddID.getText().equals("")) {
					tDeviceAdd.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceID = '" + tDeviceAddID.getText() + "'");
						//Device ID exist
						if (myRs.next()) {
							tDeviceAdd.setText("ID exist");
						}
						//Device ID pass
						else {
							tDeviceAdd.setText("Pass");
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Device Add Logic
		bDeviceAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDeviceAdd.setText("");
				//Device ID is null
				if (tDeviceAddID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Device ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Device ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceID = '" + tDeviceAddID.getText() + "'");
						//ID not pass
						if (myRs.next()) {
							JOptionPane.showMessageDialog(null, "Device ID already exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//ID pass
						else {
							//Name is null
							if (tDeviceAddName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Device name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt1 = conn.createStatement();
								ResultSet myRs1 = myStmt1.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tDeviceAddHome.getText() + "'");
								//Home ID not pass
								if (!myRs1.next()) {
									JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
								}
								//Home ID pass
								else {
									Statement myStmt2 = conn.createStatement();
									ResultSet myRs2 = myStmt2.executeQuery("SELECT ManuID FROM Manufacturer WHERE ManuDel = 0 AND ManuID = '" + tDeviceAddManu.getText() + "'");
									//Manu ID not pass
									if(!myRs2.next()) {
										JOptionPane.showMessageDialog(null, "Manufacturer not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Manu ID pass
									else {
										//Intro null
										if (tDeviceAddIntro.getText().equals("")) {
											JOptionPane.showMessageDialog(null, "Device intro can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
										}
										//Intro pass, all pass!
										else {
											
											//Get Typebox Logic
											for(int i = 0; i < 19; i++) {
												typeBox[i] = null;
												typeIDBox[i] = null;
											}
											try {
												Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
												conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
												System.out.println("Connection established!");
												Statement myStmt6 = conn.createStatement();
												ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
												int i = 0;
												while(myRs6.next()) {
													typeBox[i] = myRs6.getString("TypeName");
													typeIDBox[i] = myRs6.getString("TypeID");
													i++;
												}
											}
											catch (ClassNotFoundException e) {
												System.out.println("Exception:" + e.getMessage());
											}
											catch (SQLException e) {
												System.out.println("SQLException:" + e.getMessage());
											}
											//get type number
											typeNum = 0;
											while(typeBox[typeNum] != null) { typeNum++; }
											String[] typeBoxCut = new String[typeNum];
											String[] typeIDBoxCut = new String[typeNum];
											for(int i = 0; i < typeNum; i++) {
												typeBoxCut[i] = typeBox[i];
												typeIDBoxCut[i] = typeIDBox[i];
											}
											//Add "All"
											String[] typeBoxCutPlus = new String[typeNum + 1];
											typeBoxCutPlus[0] = "All";
											for(int i = 0; i < typeNum; i++) {
												typeBoxCutPlus[i + 1] = typeBoxCut[i];
											}
											cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
											cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
											cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
											
											Statement myStmt3 = conn.createStatement();
											myStmt3.executeUpdate("INSERT INTO Device VALUES ('" + tDeviceAddID.getText() + "','" + tDeviceAddHome.getText() + "','" + tDeviceAddManu.getText() + "','" + typeIDBoxCut[cDeviceAddType.getSelectedIndex()] + "','" + tDeviceAddName.getText() + "','" + tDeviceAddIntro.getText() + "',0);");
											JOptionPane.showMessageDialog(null, "Successfully added!", "Success",JOptionPane.INFORMATION_MESSAGE);
											//Refresh Component
											cType.setSelectedIndex(0);
											cDeviceSort.setSelectedIndex(0);
											tContent3.setText("");
											cSearchType2.setSelectedIndex(0);
											tDeviceAddID.setText("");
											tDeviceAddName.setText("");
											tDeviceAddHome.setText("");
											tDeviceAddManu.setText("");
											cDeviceAddType.setSelectedIndex(0);
											tDeviceAddIntro.setText("");
											//Refresh JTable
											Statement myStmt5 = conn.createStatement();
											ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0");
											modelDevice.setRowCount(0);
											while(myRs5.next()) {
												modelDevice.addRow(new String[] {myRs5.getString("DeviceID"), myRs5.getString("DeviceName"), myRs5.getString("HomeID"), myRs5.getString("ManuID"), myRs5.getString("TypeID"), myRs5.getString("DeviceIntro")});
											}
										}
									}
								}
							}
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		///////Start Device Management Delete Logic////////////		
		
		//Check Button
		bDeviceDeleteCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDeviceDeleteName.setText("");
				tDeviceDeleteHome.setText("");
				tDeviceDeleteManu.setText("");
				tDeviceDeleteType.setText("");
				tDeviceDeleteIntro.setText("");
				if (tDeviceDeleteID.getText().equals("")) {
					tDeviceDelete.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDeviceDeleteID.getText() + "'");
						if (!myRs.next()) {
							tDeviceDelete.setText("ID not exist");
						}
						else {
							
							//Get Typebox Logic
							for(int i = 0; i < 19; i++) {
								typeBox[i] = null;
								typeIDBox[i] = null;
							}
							try {
								Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
								conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
								System.out.println("Connection established!");
								Statement myStmt6 = conn.createStatement();
								ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
								int i = 0;
								while(myRs6.next()) {
									typeBox[i] = myRs6.getString("TypeName");
									typeIDBox[i] = myRs6.getString("TypeID");
									i++;
								}
							}
							catch (ClassNotFoundException e) {
								System.out.println("Exception:" + e.getMessage());
							}
							catch (SQLException e) {
								System.out.println("SQLException:" + e.getMessage());
							}
							//get type number
							typeNum = 0;
							while(typeBox[typeNum] != null) { typeNum++; }
							String[] typeBoxCut = new String[typeNum];
							String[] typeIDBoxCut = new String[typeNum];
							for(int i = 0; i < typeNum; i++) {
								typeBoxCut[i] = typeBox[i];
								typeIDBoxCut[i] = typeIDBox[i];
							}
							//Add "All"
							String[] typeBoxCutPlus = new String[typeNum + 1];
							typeBoxCutPlus[0] = "All";
							for(int i = 0; i < typeNum; i++) {
								typeBoxCutPlus[i + 1] = typeBoxCut[i];
							}
							cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
							cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
							cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
							
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = '" + tDeviceDeleteID.getText() + "'");
							myRs1.next();
							tDeviceDeleteName.setText(myRs1.getString("DeviceName"));
							tDeviceDeleteHome.setText(myRs1.getString("HomeID"));
							tDeviceDeleteManu.setText(myRs1.getString("ManuID"));
							tDeviceDeleteType.setText(typeBoxCut[Arrays.asList(typeIDBoxCut).indexOf(myRs1.getString("TypeID"))]);
							tDeviceDeleteIntro.setText(myRs1.getString("DeviceIntro"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Delete Button
		bDeviceDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Device ID is null
				if (tDeviceDeleteID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Device ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Device ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDeviceDeleteID.getText() + "'");
						//Device ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Device not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Deletable
						else {
							Statement myStmt1 = conn.createStatement();
							myStmt1.executeUpdate("UPDATE Data SET DataDel = 1 WHERE DeviceID = '" + tDeviceDeleteID.getText() + "'");
							myStmt1.executeUpdate("UPDATE Device SET DeviceDel = 1 WHERE DeviceID = '" + tDeviceDeleteID.getText() + "'");
							JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",JOptionPane.INFORMATION_MESSAGE);
							//Refresh Component
							cType.setSelectedIndex(0);
							cDeviceSort.setSelectedIndex(0);
							tContent3.setText("");
							cSearchType2.setSelectedIndex(0);
							tDeviceDeleteID.setText("");
							tDeviceDeleteName.setText("");
							tDeviceDeleteHome.setText("");
							tDeviceDeleteManu.setText("");
							tDeviceDeleteType.setText("");
							tDeviceDeleteIntro.setText("");
							//Refresh JTable
							Statement myStmt5 = conn.createStatement();
							ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0");
							modelDevice.setRowCount(0);
							while(myRs5.next()) {
								modelDevice.addRow(new String[] {myRs5.getString("DeviceID"), myRs5.getString("DeviceName"), myRs5.getString("HomeID"), myRs5.getString("ManuID"), myRs5.getString("TypeID"), myRs5.getString("DeviceIntro")});
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});	
		
		
		///////Start Device Management Modify Logic////////////	
		
		//Check Button
		bDeviceModifyCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Refresh Component
				tDeviceModifyName.setText("");
				tDeviceModifyHome.setText("");
				tDeviceModifyManu.setText("");
				cDeviceModifyType.setSelectedIndex(0);
				tDeviceModifyIntro.setText("");
				if (tDeviceModifyID.getText().equals("")) {
					tDeviceModify.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDeviceModifyID.getText() + "'");
						if (!myRs.next()) {
							tDeviceModify.setText("ID not exist");
						}
						else {
							
							//Get Typebox Logic
							for(int i = 0; i < 19; i++) {
								typeBox[i] = null;
								typeIDBox[i] = null;
							}
							try {
								Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
								conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
								System.out.println("Connection established!");
								Statement myStmt6 = conn.createStatement();
								ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
								int i = 0;
								while(myRs6.next()) {
									typeBox[i] = myRs6.getString("TypeName");
									typeIDBox[i] = myRs6.getString("TypeID");
									i++;
								}
							}
							catch (ClassNotFoundException e) {
								System.out.println("Exception:" + e.getMessage());
							}
							catch (SQLException e) {
								System.out.println("SQLException:" + e.getMessage());
							}
							//get type number
							typeNum = 0;
							while(typeBox[typeNum] != null) { typeNum++; }
							String[] typeBoxCut = new String[typeNum];
							String[] typeIDBoxCut = new String[typeNum];
							for(int i = 0; i < typeNum; i++) {
								typeBoxCut[i] = typeBox[i];
								typeIDBoxCut[i] = typeIDBox[i];
							}
							//Add "All"
							String[] typeBoxCutPlus = new String[typeNum + 1];
							typeBoxCutPlus[0] = "All";
							for(int i = 0; i < typeNum; i++) {
								typeBoxCutPlus[i + 1] = typeBoxCut[i];
							}
							cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
							cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
							cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
							
							//Editable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = '" + tDeviceModifyID.getText() + "'");
							myRs1.next();
							tDeviceModifyName.setText(myRs1.getString("DeviceName"));
							tDeviceModifyHome.setText(myRs1.getString("HomeID"));
							tDeviceModifyManu.setText(myRs1.getString("ManuID"));
							cDeviceModifyType.setSelectedIndex(Arrays.asList(typeIDBoxCut).indexOf(myRs1.getString("TypeID")));
							tDeviceModifyIntro.setText(myRs1.getString("DeviceIntro"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Modify Button
		bDeviceModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Device ID is null
				if (tDeviceModifyID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Device ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Device ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceID = '" + tDeviceModifyID.getText() + "'");
						//ID not pass
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Device ID not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//ID pass
						else {
							//Name is null
							if (tDeviceModifyName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Device name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt1 = conn.createStatement();
								ResultSet myRs1 = myStmt1.executeQuery("SELECT HomeID FROM Home WHERE HomeDel = 0 AND HomeID = '" + tDeviceModifyHome.getText() + "'");
								//Home ID not pass
								if (!myRs1.next()) {
									JOptionPane.showMessageDialog(null, "Home not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
								}
								//Home ID pass
								else {
									Statement myStmt2 = conn.createStatement();
									ResultSet myRs2 = myStmt2.executeQuery("SELECT ManuID FROM Manufacturer WHERE ManuDel = 0 AND ManuID = '" + tDeviceModifyManu.getText() + "'");
									//Manu ID not pass
									if(!myRs2.next()) {
										JOptionPane.showMessageDialog(null, "Manufacturer not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Manu ID pass
									else {
										//Intro null
										if (tDeviceModifyIntro.getText().equals("")) {
											JOptionPane.showMessageDialog(null, "Device intro can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
										}
										//Intro pass, all pass!
										else {
											
											//Get Typebox Logic
											for(int i = 0; i < 19; i++) {
												typeBox[i] = null;
												typeIDBox[i] = null;
											}
											try {
												Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
												conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
												System.out.println("Connection established!");
												Statement myStmt6 = conn.createStatement();
												ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
												int i = 0;
												while(myRs6.next()) {
													typeBox[i] = myRs6.getString("TypeName");
													typeIDBox[i] = myRs6.getString("TypeID");
													i++;
												}
											}
											catch (ClassNotFoundException e) {
												System.out.println("Exception:" + e.getMessage());
											}
											catch (SQLException e) {
												System.out.println("SQLException:" + e.getMessage());
											}
											//get type number
											typeNum = 0;
											while(typeBox[typeNum] != null) { typeNum++; }
											String[] typeBoxCut = new String[typeNum];
											String[] typeIDBoxCut = new String[typeNum];
											for(int i = 0; i < typeNum; i++) {
												typeBoxCut[i] = typeBox[i];
												typeIDBoxCut[i] = typeIDBox[i];
											}
											//Add "All"
											String[] typeBoxCutPlus = new String[typeNum + 1];
											typeBoxCutPlus[0] = "All";
											for(int i = 0; i < typeNum; i++) {
												typeBoxCutPlus[i + 1] = typeBoxCut[i];
											}
											cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
											cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
											cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
											
											Statement myStmt3 = conn.createStatement();
											myStmt3.executeUpdate("UPDATE Device SET DeviceName = '" + tDeviceModifyName.getText() +"', HomeID = '" + tDeviceModifyHome.getText() + "', ManuID = '" + tDeviceModifyManu.getText() + "', TypeID = '" + typeIDBoxCut[cDeviceModifyType.getSelectedIndex()] + "', DeviceIntro = '" + tDeviceModifyIntro.getText() + "' WHERE DeviceID = '" + tDeviceModifyID.getText() + "'");
											JOptionPane.showMessageDialog(null, "Successfully Modifyed!", "Success",JOptionPane.INFORMATION_MESSAGE);
											//Refresh Component
											cType.setSelectedIndex(0);
											cDeviceSort.setSelectedIndex(0);
											tContent3.setText("");
											cSearchType2.setSelectedIndex(0);
											tDeviceModifyID.setText("");
											tDeviceModifyName.setText("");
											tDeviceModifyHome.setText("");
											tDeviceModifyManu.setText("");
											cDeviceModifyType.setSelectedIndex(0);
											tDeviceModifyIntro.setText("");
											//Refresh JTable
											Statement myStmt5 = conn.createStatement();
											ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0");
											modelDevice.setRowCount(0);
											while(myRs5.next()) {
												modelDevice.addRow(new String[] {myRs5.getString("DeviceID"), myRs5.getString("DeviceName"), myRs5.getString("HomeID"), myRs5.getString("ManuID"), myRs5.getString("TypeID"), myRs5.getString("DeviceIntro")});
											}
										}
									}
								}
							}
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		
////////////////////////////////////////////////////////////////////////////Start Type Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		JPanel pType = new JPanel();
		tabbedPane.addTab("Type Management    ", null, pType, null);
		tabbedPane.setIconAt(3, iType);
		pType.setLayout(null);
		
		JScrollPane scrollPane_Type = new JScrollPane();
		scrollPane_Type.setBorder(new TitledBorder(null, "Type Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Type.setBounds(0, 0, 719, 510);
		pType.add(scrollPane_Type);
		
		tabType = new JTable();
		tabType.setEnabled(false);
		tabType.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabType.setFont(new Font("Ebrima", Font.ITALIC, 20));
		tabType.setRowHeight(35);
		DefaultTableModel modelType = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Type ID", "Type Name"
			}
		);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		tabType.setDefaultRenderer(Object.class, tcr);
		tabHome.setDefaultRenderer(Object.class, tcr);
		tabType.setModel(modelType);
		scrollPane_Type.setViewportView(tabType);
		
		JTabbedPane tabbedPane_Type = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Type.setForeground(Color.DARK_GRAY);
		tabbedPane_Type.setFont(new Font("Arial", Font.PLAIN, 20));
		tabbedPane_Type.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane_Type.setBounds(0, 515, 720, 143);
		pType.add(tabbedPane_Type);
		
		JPanel pTypeAdd = new JPanel();
		tabbedPane_Type.addTab("", null, pTypeAdd, null);
		tabbedPane_Type.setIconAt(0, iAdd);
		pTypeAdd.setLayout(null);
		
		tTypeAddID = new JTextField();
		tTypeAddID.setFont(new Font("Arial", Font.PLAIN, 15));
		tTypeAddID.setBorder(new TitledBorder(null, "Type ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tTypeAddID.setBounds(59, 27, 170, 40);
		pTypeAdd.add(tTypeAddID);
		tTypeAddID.setColumns(10);
		
		tTypeAddName = new JTextField();
		tTypeAddName.setFont(new Font("Arial", Font.PLAIN, 15));
		tTypeAddName.setColumns(10);
		tTypeAddName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tTypeAddName.setBounds(325, 27, 170, 40);
		pTypeAdd.add(tTypeAddName);
		
		JButton bTypeAdd = new JButton("Apply");
		bTypeAdd.setForeground(Color.DARK_GRAY);
		bTypeAdd.setBackground(Color.WHITE);
		bTypeAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		bTypeAdd.setBounds(534, 80, 85, 27);
		pTypeAdd.add(bTypeAdd);
		
		JPanel pTypeDelete = new JPanel();
		tabbedPane_Type.addTab("", null, pTypeDelete, null);
		tabbedPane_Type.setIconAt(1, iDelete);
		pTypeDelete.setLayout(null);
		
		tTypeDeleteID = new JTextField();
		tTypeDeleteID.setFont(new Font("Arial", Font.PLAIN, 15));
		tTypeDeleteID.setColumns(10);
		tTypeDeleteID.setBorder(new TitledBorder(null, "Type ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tTypeDeleteID.setBounds(207, 25, 170, 40);
		pTypeDelete.add(tTypeDeleteID);
		
		JButton bTypeDelete = new JButton("Delete");
		bTypeDelete.setForeground(Color.DARK_GRAY);
		bTypeDelete.setBackground(Color.WHITE);
		bTypeDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bTypeDelete.setBounds(534, 80, 85, 27);
		pTypeDelete.add(bTypeDelete);
		
		JPanel pTypeModify = new JPanel();
		tabbedPane_Type.addTab("", null, pTypeModify, null);
		tabbedPane_Type.setIconAt(2, iModify);
		pTypeModify.setLayout(null);
		
		tTypeModifyID = new JTextField();
		tTypeModifyID.setFont(new Font("Arial", Font.PLAIN, 15));
		tTypeModifyID.setColumns(10);
		tTypeModifyID.setBorder(new TitledBorder(null, "Type ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tTypeModifyID.setBounds(59, 27, 170, 40);
		pTypeModify.add(tTypeModifyID);
		
		tTypeModifyName = new JTextField();
		tTypeModifyName.setFont(new Font("Arial", Font.PLAIN, 15));
		tTypeModifyName.setColumns(10);
		tTypeModifyName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tTypeModifyName.setBounds(325, 27, 170, 40);
		pTypeModify.add(tTypeModifyName);
		
		JButton bTypeModify = new JButton("Apply");
		bTypeModify.setForeground(Color.DARK_GRAY);
		bTypeModify.setBackground(Color.WHITE);
		bTypeModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bTypeModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bTypeModify.setBounds(534, 80, 85, 27);
		pTypeModify.add(bTypeModify);
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Type WHERE TypeDel = 0");
			modelType.setRowCount(0);
			while(myRs.next()) {
				modelType.addRow(new String[] {myRs.getString("TypeID"), myRs.getString("TypeName")});
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		
		
		///////Start Type Management Add Logic////////////
		//Type Add Logic
		bTypeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//ID is null
				if (tTypeAddID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Type ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT TypeID FROM Type WHERE TypeID = '" + tTypeAddID.getText() + "'");
						//ID not pass
						if (myRs.next()) {
							JOptionPane.showMessageDialog(null, "Type ID already exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//ID pass
						else {
							//Name is null
							if (tTypeAddName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Type name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt3 = conn.createStatement();
								myStmt3.executeUpdate("INSERT INTO Type VALUES ('" + tTypeAddID.getText() + "','" + tTypeAddName.getText() + "',0);");
								
								//Get Typebox Logic
								for(int i = 0; i < 19; i++) {
									typeBox[i] = null;
									typeIDBox[i] = null;
								}
								try {
									Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
									System.out.println("Connection established!");
									Statement myStmt6 = conn.createStatement();
									ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
									int i = 0;
									while(myRs6.next()) {
										typeBox[i] = myRs6.getString("TypeName");
										typeIDBox[i] = myRs6.getString("TypeID");
										i++;
									}
								}
								catch (ClassNotFoundException e) {
									System.out.println("Exception:" + e.getMessage());
								}
								catch (SQLException e) {
									System.out.println("SQLException:" + e.getMessage());
								}
								//get type number
								typeNum = 0;
								while(typeBox[typeNum] != null) { typeNum++; }
								String[] typeBoxCut = new String[typeNum];
								String[] typeIDBoxCut = new String[typeNum];
								for(int i = 0; i < typeNum; i++) {
									typeBoxCut[i] = typeBox[i];
									typeIDBoxCut[i] = typeIDBox[i];
								}
								//Add "All"
								String[] typeBoxCutPlus = new String[typeNum + 1];
								typeBoxCutPlus[0] = "All";
								for(int i = 0; i < typeNum; i++) {
									typeBoxCutPlus[i + 1] = typeBoxCut[i];
								}
								cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
								cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
								cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
								
								JOptionPane.showMessageDialog(null, "Successfully added!", "Success",JOptionPane.INFORMATION_MESSAGE);
								//Refresh Component
								tTypeAddID.setText("");
								tTypeAddName.setText("");
								//Refresh JTable
								Statement myStmt4 = conn.createStatement();
								ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Type WHERE TypeDel = 0");
								modelType.setRowCount(0);
								while(myRs4.next()) {
									modelType.addRow(new String[] {myRs4.getString("TypeID"), myRs4.getString("TypeName")});
								}
							}
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		
		///////Start Type Management Delete Logic////////////
		//Delete Button
		bTypeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Type ID is null
				if (tTypeDeleteID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Type ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Type ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT TypeID FROM Type WHERE TypeDel = 0 AND TypeID = '" + tTypeDeleteID.getText() + "'");
						//Type ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Type not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Deletable
						else {
							Statement myStmt1 = conn.createStatement();
							myStmt1.executeUpdate("UPDATE Data SET DataDel = 1 WHERE DeviceID IN (SELECT DeviceID FROM Device WHERE TypeID = '" + tTypeDeleteID.getText() + "')");
							myStmt1.executeUpdate("UPDATE Device SET DeviceDel = 1 WHERE TypeID = '" + tTypeDeleteID.getText() + "'");
							myStmt1.executeUpdate("UPDATE Type SET TypeDel = 1 WHERE TypeID = '" + tTypeDeleteID.getText() + "'");
							
							//Get Typebox Logic
							for(int i = 0; i < 19; i++) {
								typeBox[i] = null;
								typeIDBox[i] = null;
							}
							try {
								Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
								conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
								System.out.println("Connection established!");
								Statement myStmt6 = conn.createStatement();
								ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
								int i = 0;
								while(myRs6.next()) {
									typeBox[i] = myRs6.getString("TypeName");
									typeIDBox[i] = myRs6.getString("TypeID");
									i++;
								}
							}
							catch (ClassNotFoundException e) {
								System.out.println("Exception:" + e.getMessage());
							}
							catch (SQLException e) {
								System.out.println("SQLException:" + e.getMessage());
							}
							//get type number
							typeNum = 0;
							while(typeBox[typeNum] != null) { typeNum++; }
							String[] typeBoxCut = new String[typeNum];
							String[] typeIDBoxCut = new String[typeNum];
							for(int i = 0; i < typeNum; i++) {
								typeBoxCut[i] = typeBox[i];
								typeIDBoxCut[i] = typeIDBox[i];
							}
							//Add "All"
							String[] typeBoxCutPlus = new String[typeNum + 1];
							typeBoxCutPlus[0] = "All";
							for(int i = 0; i < typeNum; i++) {
								typeBoxCutPlus[i + 1] = typeBoxCut[i];
							}
							cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
							cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
							cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
							
							JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",JOptionPane.INFORMATION_MESSAGE);
							//Refresh Component
							tTypeDeleteID.setText("");
							//Refresh JTable
							Statement myStmt4 = conn.createStatement();
							ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Type WHERE TypeDel = 0");
							modelType.setRowCount(0);
							while(myRs4.next()) {
								modelType.addRow(new String[] {myRs4.getString("TypeID"), myRs4.getString("TypeName")});
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});

		
		///////Start Type Management Modify Logic////////////	
		//Modify Button
		bTypeModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Type ID is null
				if (tTypeModifyID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Type ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Type ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT TypeID FROM Type WHERE TypeDel = 0 AND TypeID = '" + tTypeModifyID.getText() + "'");
						//Type ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Type not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Type ID exist
						else {
							//Name is null
							if (tTypeModifyName.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Type name can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//Name pass
							else {
								Statement myStmt1 = conn.createStatement();
								myStmt1.executeUpdate("UPDATE Type SET TypeName = '" + tTypeModifyName.getText() + "' WHERE TypeID = '" + tTypeModifyID.getText() + "'");
								
								//Get Typebox Logic
								for(int i = 0; i < 19; i++) {
									typeBox[i] = null;
									typeIDBox[i] = null;
								}
								try {
									Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
									conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
									System.out.println("Connection established!");
									Statement myStmt6 = conn.createStatement();
									ResultSet myRs6 = myStmt6.executeQuery("select * from Type where typeDel = 0");
									int i = 0;
									while(myRs6.next()) {
										typeBox[i] = myRs6.getString("TypeName");
										typeIDBox[i] = myRs6.getString("TypeID");
										i++;
									}
								}
								catch (ClassNotFoundException e) {
									System.out.println("Exception:" + e.getMessage());
								}
								catch (SQLException e) {
									System.out.println("SQLException:" + e.getMessage());
								}
								//get type number
								typeNum = 0;
								while(typeBox[typeNum] != null) { typeNum++; }
								String[] typeBoxCut = new String[typeNum];
								String[] typeIDBoxCut = new String[typeNum];
								for(int i = 0; i < typeNum; i++) {
									typeBoxCut[i] = typeBox[i];
									typeIDBoxCut[i] = typeIDBox[i];
								}
								//Add "All"
								String[] typeBoxCutPlus = new String[typeNum + 1];
								typeBoxCutPlus[0] = "All";
								for(int i = 0; i < typeNum; i++) {
									typeBoxCutPlus[i + 1] = typeBoxCut[i];
								}
								cType.setModel(new DefaultComboBoxModel(typeBoxCutPlus));
								cDeviceAddType.setModel(new DefaultComboBoxModel(typeBoxCut));
								cDeviceModifyType.setModel(new DefaultComboBoxModel(typeBoxCut));
								
								JOptionPane.showMessageDialog(null, "Successfully modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
								//Refresh Component
								tTypeModifyID.setText("");
								tTypeModifyName.setText("");
								//Refresh JTable
								Statement myStmt4 = conn.createStatement();
								ResultSet myRs4 = myStmt4.executeQuery("SELECT * FROM Type WHERE TypeDel = 0");
								modelType.setRowCount(0);
								while(myRs4.next()) {
									modelType.addRow(new String[] {myRs4.getString("TypeID"), myRs4.getString("TypeName")});
								}
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
				

////////////////////////////////////////////////////////////////////////////Start Data Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		JPanel pData = new JPanel();
		tabbedPane.addTab("Data Management     ", null, pData, null);
		tabbedPane.setIconAt(4, iData);
		pData.setLayout(null);
		
		JTabbedPane tabbedPane_Data = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Data.setForeground(Color.DARK_GRAY);
		tabbedPane_Data.setBackground(Color.WHITE);
		tabbedPane_Data.setFont(new Font("Arial", Font.PLAIN, 15));
		tabbedPane_Data.setBounds(0, 0, 719, 513);
		pData.add(tabbedPane_Data);
		
		JPanel pDataSearch = new JPanel();
		tabbedPane_Data.addTab("Search      ", null, pDataSearch, null);
		tabbedPane_Data.setIconAt(0, iSearch2);
		pDataSearch.setLayout(null);
		
		JTabbedPane pDataSearchWays = new JTabbedPane(JTabbedPane.LEFT);
		pDataSearchWays.setForeground(Color.DARK_GRAY);
		pDataSearchWays.setFont(new Font("Arial", Font.PLAIN, 10));
		pDataSearchWays.setBounds(0, 0, 576, 202);
		pDataSearch.add(pDataSearchWays);
		
		JPanel pDataSearchDevice = new JPanel();
		pDataSearchWays.addTab("", null, pDataSearchDevice, null);
		pDataSearchWays.setIconAt(0, iDevice2);
		pDataSearchDevice.setLayout(null);
		
		tDataSearchDeviceID = new JTextField();
		tDataSearchDeviceID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataSearchDeviceID.setColumns(10);
		tDataSearchDeviceID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataSearchDeviceID.setBounds(10, 10, 110, 40);
		pDataSearchDevice.add(tDataSearchDeviceID);
		
		tDataSearchDeviceName = new JTextField();
		tDataSearchDeviceName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataSearchDeviceName.setEditable(false);
		tDataSearchDeviceName.setColumns(10);
		tDataSearchDeviceName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchDeviceName.setBounds(130, 10, 136, 40);
		pDataSearchDevice.add(tDataSearchDeviceName);
		
		tDataSearchDeviceManu = new JTextField();
		tDataSearchDeviceManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataSearchDeviceManu.setEditable(false);
		tDataSearchDeviceManu.setColumns(10);
		tDataSearchDeviceManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Manufacturer ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchDeviceManu.setBounds(270, 10, 120, 40);
		pDataSearchDevice.add(tDataSearchDeviceManu);
		
		tDataSearchDeviceType = new JTextField();
		tDataSearchDeviceType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataSearchDeviceType.setEditable(false);
		tDataSearchDeviceType.setColumns(10);
		tDataSearchDeviceType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchDeviceType.setBounds(394, 10, 110, 40);
		pDataSearchDevice.add(tDataSearchDeviceType);
		
		JTextArea tDataSearchDeviceIntro = new JTextArea();
		tDataSearchDeviceIntro.setLineWrap(true);
		tDataSearchDeviceIntro.setBackground(UIManager.getColor("Button.background"));
		tDataSearchDeviceIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDataSearchDeviceIntro.setEditable(false);
		tDataSearchDeviceIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataSearchDeviceIntro.setBounds(128, 57, 376, 130);
		pDataSearchDevice.add(tDataSearchDeviceIntro);
		
		JButton bDataSearchDevice = new JButton("Search");
		bDataSearchDevice.setIcon(iSearch);
		bDataSearchDevice.setForeground(Color.DARK_GRAY);
		bDataSearchDevice.setBackground(Color.WHITE);
		bDataSearchDevice.setFont(new Font("Arial", Font.PLAIN, 16));
		bDataSearchDevice.setBounds(10, 60, 110, 63);
		pDataSearchDevice.add(bDataSearchDevice);
		
		JPanel pDataSearchType = new JPanel();
		pDataSearchWays.addTab("", null, pDataSearchType, null);
		pDataSearchWays.setIconAt(1, iType2);
		pDataSearchType.setLayout(null);
		
		tDataSearchTypeID = new JTextField();
		tDataSearchTypeID.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataSearchTypeID.setColumns(10);
		tDataSearchTypeID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchTypeID.setBounds(68, 26, 135, 62);
		pDataSearchType.add(tDataSearchTypeID);
		
		tDataSearchTypeName = new JTextField();
		tDataSearchTypeName.setEditable(false);
		tDataSearchTypeName.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataSearchTypeName.setColumns(10);
		tDataSearchTypeName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchTypeName.setBounds(271, 26, 159, 62);
		pDataSearchType.add(tDataSearchTypeName);
		
		JButton bDataSearchType = new JButton("Search");
		bDataSearchType.setIcon(iSearch);
		bDataSearchType.setForeground(Color.DARK_GRAY);
		bDataSearchType.setBackground(Color.WHITE);
		bDataSearchType.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataSearchType.setBounds(82, 110, 107, 48);
		pDataSearchType.add(bDataSearchType);
		
		JPanel pDataSearchHome = new JPanel();
		pDataSearchWays.addTab("", null, pDataSearchHome, null);
		pDataSearchWays.setIconAt(2, iHome2);
		pDataSearchHome.setLayout(null);
		
		tDataSearchHomeID = new JTextField();
		tDataSearchHomeID.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataSearchHomeID.setColumns(10);
		tDataSearchHomeID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchHomeID.setBounds(68, 26, 135, 62);
		pDataSearchHome.add(tDataSearchHomeID);
		
		JButton bDataSearchHome = new JButton("Search");
		bDataSearchHome.setIcon(iSearch);
		bDataSearchHome.setForeground(Color.DARK_GRAY);
		bDataSearchHome.setBackground(Color.WHITE);
		bDataSearchHome.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataSearchHome.setBounds(82, 110, 107, 48);
		pDataSearchHome.add(bDataSearchHome);
		
		tDataSearchHomeAddress = new JTextField();
		tDataSearchHomeAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataSearchHomeAddress.setEditable(false);
		tDataSearchHomeAddress.setColumns(10);
		tDataSearchHomeAddress.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Address", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataSearchHomeAddress.setBounds(251, 26, 192, 62);
		pDataSearchHome.add(tDataSearchHomeAddress);
		
		JScrollPane scrollPane_DataSearch = new JScrollPane();
		scrollPane_DataSearch.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_DataSearch.setBounds(0, 200, 576, 298);
		pDataSearch.add(scrollPane_DataSearch);
		
		tabDataSearch = new JTable();
		tabDataSearch.setEnabled(false);
		tabDataSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		DefaultTableModel modelDataSearch = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data ID", "Device ID", "Data Time", "Value"
			}
		);
		tabDataSearch.setModel(modelDataSearch);
		scrollPane_DataSearch.setViewportView(tabDataSearch);
		
		JPanel pDataStatistics = new JPanel();
		tabbedPane_Data.addTab("Statistics   ", null, pDataStatistics, null);
		tabbedPane_Data.setIconAt(1, iStatistics);
		pDataStatistics.setLayout(null);
		
		tDataStatisticsID = new JTextField();
		tDataStatisticsID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsID.setColumns(10);
		tDataStatisticsID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataStatisticsID.setBounds(10, 10, 108, 40);
		pDataStatistics.add(tDataStatisticsID);
		
		JButton bDataStatistics = new JButton("");
		bDataStatistics.setIcon(iDraw);
		bDataStatistics.setForeground(Color.DARK_GRAY);
		bDataStatistics.setBackground(Color.WHITE);
		bDataStatistics.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataStatistics.setBounds(10, 110, 108, 75);
		pDataStatistics.add(bDataStatistics);
		
		JTextArea tDataStatisticsIntro = new JTextArea();
		tDataStatisticsIntro.setLineWrap(true);
		tDataStatisticsIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDataStatisticsIntro.setEditable(false);
		tDataStatisticsIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataStatisticsIntro.setBackground(SystemColor.menu);
		tDataStatisticsIntro.setBounds(128, 57, 448, 130);
		pDataStatistics.add(tDataStatisticsIntro);
		
		tDataStatisticsName = new JTextField();
		tDataStatisticsName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsName.setEditable(false);
		tDataStatisticsName.setColumns(10);
		tDataStatisticsName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataStatisticsName.setBounds(128, 10, 142, 40);
		pDataStatistics.add(tDataStatisticsName);
		
		tDataStatisticsManu = new JTextField();
		tDataStatisticsManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsManu.setEditable(false);
		tDataStatisticsManu.setColumns(10);
		tDataStatisticsManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Manufacturer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataStatisticsManu.setBounds(280, 10, 149, 40);
		pDataStatistics.add(tDataStatisticsManu);
		
		tDataStatisticsType = new JTextField();
		tDataStatisticsType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsType.setEditable(false);
		tDataStatisticsType.setColumns(10);
		tDataStatisticsType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataStatisticsType.setBounds(439, 10, 137, 40);
		pDataStatistics.add(tDataStatisticsType);
		
		tDataStatisticsValue = new JTextField();
		tDataStatisticsValue.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsValue.setColumns(10);
		tDataStatisticsValue.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Value", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataStatisticsValue.setBounds(10, 60, 108, 40);
		pDataStatistics.add(tDataStatisticsValue);
		
		JPanel pChart1 = new JPanel();
		pChart1.setBounds(10, 196, 563, 300);
		pDataStatistics.add(pChart1);
		pChart1.setLayout(new BoxLayout(pChart1, BoxLayout.X_AXIS));
		
		JPanel pDataDistribution = new JPanel();
		tabbedPane_Data.addTab("Distribution", null, pDataDistribution, null);
		tabbedPane_Data.setIconAt(2, iDistribution);
		pDataDistribution.setLayout(null);
		
		JButton bDataDis = new JButton("");
		bDataDis.setIcon(iDraw);
		bDataDis.setForeground(Color.DARK_GRAY);
		bDataDis.setBackground(Color.WHITE);
		bDataDis.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataDis.setBounds(10, 122, 108, 63);
		pDataDistribution.add(bDataDis);
		
		JTextArea tDataDisIntro = new JTextArea();
		tDataDisIntro.setLineWrap(true);
		tDataDisIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDataDisIntro.setEditable(false);
		tDataDisIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataDisIntro.setBackground(SystemColor.menu);
		tDataDisIntro.setBounds(128, 57, 448, 130);
		pDataDistribution.add(tDataDisIntro);
		
		tDataDisID = new JTextField();
		tDataDisID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataDisID.setColumns(10);
		tDataDisID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataDisID.setBounds(10, 10, 108, 40);
		pDataDistribution.add(tDataDisID);
		
		tDataDisName = new JTextField();
		tDataDisName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataDisName.setEditable(false);
		tDataDisName.setColumns(10);
		tDataDisName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataDisName.setBounds(128, 10, 142, 40);
		pDataDistribution.add(tDataDisName);
		
		tDataDisManu = new JTextField();
		tDataDisManu.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataDisManu.setEditable(false);
		tDataDisManu.setColumns(10);
		tDataDisManu.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Manufacturer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataDisManu.setBounds(280, 10, 149, 40);
		pDataDistribution.add(tDataDisManu);
		
		tDataDisType = new JTextField();
		tDataDisType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataDisType.setEditable(false);
		tDataDisType.setColumns(10);
		tDataDisType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataDisType.setBounds(439, 10, 137, 40);
		pDataDistribution.add(tDataDisType);
		
		JComboBox cDataDis = new JComboBox();
		cDataDis.setForeground(Color.DARK_GRAY);
		cDataDis.setBackground(Color.WHITE);
		cDataDis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cDataDis.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5"}));
		cDataDis.setSelectedIndex(1);
		cDataDis.setBorder(new TitledBorder(null, "Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDataDis.setBounds(10, 57, 108, 55);
		pDataDistribution.add(cDataDis);
		
		JPanel pChart2 = new JPanel();
		pChart2.setBounds(10, 195, 563, 303);
		pDataDistribution.add(pChart2);
		pChart2.setLayout(new BoxLayout(pChart2, BoxLayout.X_AXIS));
		
		JPanel pDataHomeSet = new JPanel();
		tabbedPane_Data.addTab("Home Set  ", null, pDataHomeSet, null);
		tabbedPane_Data.setIconAt(3, iHomeSet);
		pDataHomeSet.setLayout(null);
		
		tDataHomeSet = new JTextField();
		tDataHomeSet.setBorder(new TitledBorder(null, "Home IDs, Seperate with comma", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataHomeSet.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataHomeSet.setBounds(10, 10, 458, 46);
		pDataHomeSet.add(tDataHomeSet);
		tDataHomeSet.setColumns(10);
		
		JScrollPane scrollPane_DataHome = new JScrollPane();
		scrollPane_DataHome.setBorder(new TitledBorder(null, "Data Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_DataHome.setBounds(10, 66, 566, 432);
		pDataHomeSet.add(scrollPane_DataHome);
		
		tabDataHome = new JTable();
		tabDataHome.setFont(new Font("Arial", Font.PLAIN, 12));
		tabDataHome.setEnabled(false);
		tabDataHome.setBorder(new LineBorder(new Color(0, 0, 0)));
		DefaultTableModel modelDataHome = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Home ID", "Data ID", "Device ID", "Data Time", "Value"
			}
		);
		tabDataHome.setModel(modelDataHome);
		tabDataHome.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabDataHome.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabDataHome.getColumnModel().getColumn(2).setPreferredWidth(40);
		scrollPane_DataHome.setViewportView(tabDataHome);
		
		JButton bDataHomeSet = new JButton("Search");
		bDataHomeSet.setIcon(iSearch);
		bDataHomeSet.setForeground(Color.DARK_GRAY);
		bDataHomeSet.setFont(new Font("Arial", Font.PLAIN, 15));
		bDataHomeSet.setBackground(Color.WHITE);
		bDataHomeSet.setBounds(470, 18, 106, 36);
		pDataHomeSet.add(bDataHomeSet);
		
		JPanel pDataManagement = new JPanel();
		tabbedPane_Data.addTab("Manage     ", null, pDataManagement, null);
		tabbedPane_Data.setIconAt(4, iManage);
		pDataManagement.setLayout(null);
		
		JTabbedPane tabbedPane_DataManage = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_DataManage.setFont(new Font("Arial", Font.PLAIN, 19));
		tabbedPane_DataManage.setBounds(1, 1, 575, 497);
		pDataManagement.add(tabbedPane_DataManage);
		
		JPanel pDataManageDelete = new JPanel();
		pDataManageDelete.setToolTipText("");
		tabbedPane_DataManage.addTab("", null, pDataManageDelete, null);
		tabbedPane_DataManage.setIconAt(0, iDelete);
		pDataManageDelete.setLayout(null);
		
		tDataManageDeleteID = new JTextField();
		tDataManageDeleteID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageDeleteID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageDeleteID.setBounds(10, 10, 140, 47);
		pDataManageDelete.add(tDataManageDeleteID);
		tDataManageDeleteID.setColumns(10);
		
		JButton bDataManageDeleteCheck = new JButton("Check");
		bDataManageDeleteCheck.setForeground(Color.DARK_GRAY);
		bDataManageDeleteCheck.setBackground(Color.WHITE);
		bDataManageDeleteCheck.setFont(new Font("Arial", Font.PLAIN, 15));
		bDataManageDeleteCheck.setBounds(160, 10, 103, 47);
		pDataManageDelete.add(bDataManageDeleteCheck);
		
		tDataManageDeleteTime = new JTextField();
		tDataManageDeleteTime.setEditable(false);
		tDataManageDeleteTime.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageDeleteTime.setColumns(10);
		tDataManageDeleteTime.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageDeleteTime.setBounds(10, 67, 140, 47);
		pDataManageDelete.add(tDataManageDeleteTime);
		
		tDataManageDeleteDeviceID = new JTextField();
		tDataManageDeleteDeviceID.setEditable(false);
		tDataManageDeleteDeviceID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageDeleteDeviceID.setColumns(10);
		tDataManageDeleteDeviceID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageDeleteDeviceID.setBounds(10, 124, 140, 47);
		pDataManageDelete.add(tDataManageDeleteDeviceID);
		
		tDataManageDeleteType = new JTextField();
		tDataManageDeleteType.setEditable(false);
		tDataManageDeleteType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageDeleteType.setColumns(10);
		tDataManageDeleteType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageDeleteType.setBounds(348, 124, 156, 47);
		pDataManageDelete.add(tDataManageDeleteType);
		
		tDataManageDeleteName = new JTextField();
		tDataManageDeleteName.setEditable(false);
		tDataManageDeleteName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageDeleteName.setColumns(10);
		tDataManageDeleteName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageDeleteName.setBounds(160, 124, 178, 47);
		pDataManageDelete.add(tDataManageDeleteName);
		
		JTextArea tDataManageDeleteIntro = new JTextArea();
		tDataManageDeleteIntro.setLineWrap(true);
		tDataManageDeleteIntro.setBackground(UIManager.getColor("Button.background"));
		tDataManageDeleteIntro.setEditable(false);
		tDataManageDeleteIntro.setBorder(new TitledBorder(null, "Device Intro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataManageDeleteIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDataManageDeleteIntro.setBounds(10, 181, 494, 161);
		pDataManageDelete.add(tDataManageDeleteIntro);
		
		tDataManageDeleteValue = new JTextField();
		tDataManageDeleteValue.setHorizontalAlignment(SwingConstants.CENTER);
		tDataManageDeleteValue.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataManageDeleteValue.setEditable(false);
		tDataManageDeleteValue.setBorder(new TitledBorder(null, "Value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataManageDeleteValue.setBounds(187, 352, 140, 62);
		pDataManageDelete.add(tDataManageDeleteValue);
		tDataManageDeleteValue.setColumns(10);
		
		JButton bDataManageDelete = new JButton("Delete");
		bDataManageDelete.setForeground(Color.DARK_GRAY);
		bDataManageDelete.setBackground(Color.WHITE);
		bDataManageDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bDataManageDelete.setBounds(401, 435, 103, 47);
		pDataManageDelete.add(bDataManageDelete);
		
		tDataManageDeleteCheck = new JTextField();
		tDataManageDeleteCheck.setHorizontalAlignment(SwingConstants.CENTER);
		tDataManageDeleteCheck.setEditable(false);
		tDataManageDeleteCheck.setColumns(10);
		tDataManageDeleteCheck.setBorder(null);
		tDataManageDeleteCheck.setBackground(new Color(244, 244, 244));
		tDataManageDeleteCheck.setBounds(265, 10, 90, 47);
		pDataManageDelete.add(tDataManageDeleteCheck);
		
		JPanel pDataManageModify = new JPanel();
		tabbedPane_DataManage.addTab("", null, pDataManageModify, null);
		tabbedPane_DataManage.setIconAt(1, iModify);
		pDataManageModify.setLayout(null);
		
		tDataManageModifyID = new JTextField();
		tDataManageModifyID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageModifyID.setColumns(10);
		tDataManageModifyID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageModifyID.setBounds(10, 10, 140, 47);
		pDataManageModify.add(tDataManageModifyID);
		
		JButton bDataManageModifyCheck = new JButton("Check");
		bDataManageModifyCheck.setForeground(Color.DARK_GRAY);
		bDataManageModifyCheck.setBackground(Color.WHITE);
		bDataManageModifyCheck.setFont(new Font("Arial", Font.PLAIN, 15));
		bDataManageModifyCheck.setBounds(160, 10, 103, 47);
		pDataManageModify.add(bDataManageModifyCheck);
		
		tDataManageModifyTime = new JTextField();
		tDataManageModifyTime.setEditable(false);
		tDataManageModifyTime.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageModifyTime.setColumns(10);
		tDataManageModifyTime.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageModifyTime.setBounds(10, 67, 140, 47);
		pDataManageModify.add(tDataManageModifyTime);
		
		tDataManageModifyDeviceID = new JTextField();
		tDataManageModifyDeviceID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageModifyDeviceID.setEditable(false);
		tDataManageModifyDeviceID.setColumns(10);
		tDataManageModifyDeviceID.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageModifyDeviceID.setBounds(10, 124, 140, 47);
		pDataManageModify.add(tDataManageModifyDeviceID);
		
		tDataManageModifyName = new JTextField();
		tDataManageModifyName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageModifyName.setEditable(false);
		tDataManageModifyName.setColumns(10);
		tDataManageModifyName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageModifyName.setBounds(160, 124, 178, 47);
		pDataManageModify.add(tDataManageModifyName);
		
		tDataManageModifyType = new JTextField();
		tDataManageModifyType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataManageModifyType.setEditable(false);
		tDataManageModifyType.setColumns(10);
		tDataManageModifyType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataManageModifyType.setBounds(348, 124, 140, 47);
		pDataManageModify.add(tDataManageModifyType);
		
		JTextArea tDataManageModifyIntro = new JTextArea();
		tDataManageModifyIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDataManageModifyIntro.setEditable(false);
		tDataManageModifyIntro.setBorder(new TitledBorder(null, "Device Intro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataManageModifyIntro.setBackground(SystemColor.menu);
		tDataManageModifyIntro.setBounds(10, 181, 478, 161);
		pDataManageModify.add(tDataManageModifyIntro);
		
		tDataManageModifyValue = new JTextField();
		tDataManageModifyValue.setHorizontalAlignment(SwingConstants.CENTER);
		tDataManageModifyValue.setFont(new Font("Arial", Font.PLAIN, 20));
		tDataManageModifyValue.setColumns(10);
		tDataManageModifyValue.setBorder(new TitledBorder(null, "Value", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataManageModifyValue.setBounds(179, 352, 140, 62);
		pDataManageModify.add(tDataManageModifyValue);
		
		JButton bDataManageModify = new JButton("Save");
		bDataManageModify.setForeground(Color.DARK_GRAY);
		bDataManageModify.setBackground(Color.WHITE);
		bDataManageModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bDataManageModify.setBounds(385, 435, 103, 47);
		pDataManageModify.add(bDataManageModify);
		
		tDataManageModifyCheck = new JTextField();
		tDataManageModifyCheck.setHorizontalAlignment(SwingConstants.CENTER);
		tDataManageModifyCheck.setEditable(false);
		tDataManageModifyCheck.setColumns(10);
		tDataManageModifyCheck.setBorder(null);
		tDataManageModifyCheck.setBackground(new Color(244, 244, 244));
		tDataManageModifyCheck.setBounds(265, 10, 90, 47);
		pDataManageModify.add(tDataManageModifyCheck);
		
		JPanel pTimeRange = new JPanel();
		pTimeRange.setBorder(new TitledBorder(null, "Time Range", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.setBounds(0, 512, 719, 146);
		pData.add(pTimeRange);
		pTimeRange.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pTimeStart = new JPanel();
		pTimeStart.setBorder(new TitledBorder(null, "Start", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.add(pTimeStart);
		pTimeStart.setLayout(null);
		
		JComboBox cTimeStartYear = new JComboBox();
		cTimeStartYear.setForeground(Color.DARK_GRAY);
		cTimeStartYear.setBackground(Color.WHITE);
		cTimeStartYear.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeStartYear.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011"}));
		cTimeStartYear.setSelectedIndex(10);
		cTimeStartYear.setBounds(80, 23, 63, 39);
		pTimeStart.add(cTimeStartYear);
		
		JComboBox cTimeStartMonth = new JComboBox();
		cTimeStartMonth.setForeground(Color.DARK_GRAY);
		cTimeStartMonth.setBackground(Color.WHITE);
		cTimeStartMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cTimeStartMonth.setSelectedIndex(0);
		cTimeStartMonth.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeStartMonth.setBounds(153, 23, 47, 39);
		pTimeStart.add(cTimeStartMonth);
		
		JComboBox cTimeStartDay = new JComboBox();
		cTimeStartDay.setForeground(Color.DARK_GRAY);
		cTimeStartDay.setBackground(Color.WHITE);
		cTimeStartDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cTimeStartDay.setSelectedIndex(0);
		cTimeStartDay.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeStartDay.setBounds(210, 23, 47, 39);
		pTimeStart.add(cTimeStartDay);
		
		JLabel lTimeStartC1 = new JLabel(":");
		lTimeStartC1.setFont(new Font("Arial", Font.BOLD, 20));
		lTimeStartC1.setBounds(144, 72, 7, 32);
		pTimeStart.add(lTimeStartC1);
		
		JLabel lTimeStartC2 = new JLabel(":");
		lTimeStartC2.setFont(new Font("Arial", Font.BOLD, 20));
		lTimeStartC2.setBounds(201, 72, 7, 32);
		pTimeStart.add(lTimeStartC2);
		
		JComboBox cTimeStartHour = new JComboBox();
		cTimeStartHour.setForeground(Color.DARK_GRAY);
		cTimeStartHour.setBackground(Color.WHITE);
		cTimeStartHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeStartHour.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cTimeStartHour.setSelectedIndex(0);
		cTimeStartHour.setBounds(96, 70, 47, 39);
		pTimeStart.add(cTimeStartHour);
		
		JComboBox cTimeStartMinu = new JComboBox();
		cTimeStartMinu.setForeground(Color.DARK_GRAY);
		cTimeStartMinu.setBackground(Color.WHITE);
		cTimeStartMinu.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cTimeStartMinu.setSelectedIndex(0);
		cTimeStartMinu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeStartMinu.setBounds(153, 70, 47, 39);
		pTimeStart.add(cTimeStartMinu);
		
		JComboBox cTimeStartSec = new JComboBox();
		cTimeStartSec.setForeground(Color.DARK_GRAY);
		cTimeStartSec.setBackground(Color.WHITE);
		cTimeStartSec.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cTimeStartSec.setSelectedIndex(0);
		cTimeStartSec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeStartSec.setBounds(210, 70, 47, 39);
		pTimeStart.add(cTimeStartSec);
		
		JPanel pTimeEnd = new JPanel();
		pTimeEnd.setBorder(new TitledBorder(null, "End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.add(pTimeEnd);
		pTimeEnd.setLayout(null);
		
		JComboBox cTimeEndYear = new JComboBox();
		cTimeEndYear.setForeground(Color.DARK_GRAY);
		cTimeEndYear.setBackground(Color.WHITE);
		cTimeEndYear.setModel(new DefaultComboBoxModel(new String[] {"2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011"}));
		cTimeEndYear.setSelectedIndex(0);
		cTimeEndYear.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeEndYear.setBounds(80, 23, 63, 39);
		pTimeEnd.add(cTimeEndYear);
		
		JComboBox cTimeEndMonth = new JComboBox();
		cTimeEndMonth.setForeground(Color.DARK_GRAY);
		cTimeEndMonth.setBackground(Color.WHITE);
		cTimeEndMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cTimeEndMonth.setSelectedIndex(11);
		cTimeEndMonth.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeEndMonth.setBounds(153, 23, 47, 39);
		pTimeEnd.add(cTimeEndMonth);
		
		JComboBox cTimeEndDay = new JComboBox();
		cTimeEndDay.setForeground(Color.DARK_GRAY);
		cTimeEndDay.setBackground(Color.WHITE);
		cTimeEndDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cTimeEndDay.setSelectedIndex(30);
		cTimeEndDay.setFont(new Font("Arial", Font.PLAIN, 15));
		cTimeEndDay.setBounds(210, 23, 47, 39);
		pTimeEnd.add(cTimeEndDay);
		
		JLabel lTimeStartC2_1 = new JLabel(":");
		lTimeStartC2_1.setFont(new Font("Arial", Font.BOLD, 20));
		lTimeStartC2_1.setBounds(201, 72, 7, 32);
		pTimeEnd.add(lTimeStartC2_1);
		
		JLabel lTimeStartC1_1 = new JLabel(":");
		lTimeStartC1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lTimeStartC1_1.setBounds(144, 72, 7, 32);
		pTimeEnd.add(lTimeStartC1_1);
		
		JComboBox cTimeEndHour = new JComboBox();
		cTimeEndHour.setForeground(Color.DARK_GRAY);
		cTimeEndHour.setBackground(Color.WHITE);
		cTimeEndHour.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		cTimeEndHour.setSelectedIndex(23);
		cTimeEndHour.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeEndHour.setBounds(96, 70, 47, 39);
		pTimeEnd.add(cTimeEndHour);
		
		JComboBox cTimeEndMinu = new JComboBox();
		cTimeEndMinu.setForeground(Color.DARK_GRAY);
		cTimeEndMinu.setBackground(Color.WHITE);
		cTimeEndMinu.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cTimeEndMinu.setSelectedIndex(59);
		cTimeEndMinu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeEndMinu.setBounds(153, 70, 47, 39);
		pTimeEnd.add(cTimeEndMinu);
		
		JComboBox cTimeEndSec = new JComboBox();
		cTimeEndSec.setForeground(Color.DARK_GRAY);
		cTimeEndSec.setBackground(Color.WHITE);
		cTimeEndSec.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cTimeEndSec.setSelectedIndex(59);
		cTimeEndSec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cTimeEndSec.setBounds(210, 70, 47, 39);
		pTimeEnd.add(cTimeEndSec);
		
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Data WHERE DataDel = 0");
			modelDataSearch.setRowCount(0);
			while(myRs.next()) {
				if(!(myRs.getString("Humidity") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
				}
				else if(!(myRs.getString("Temperature") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
				}
				else if(!(myRs.getString("Light") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
				}
				else if(!(myRs.getString("Doors") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
				}
				else if(!(myRs.getString("Alarm") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
				}
				else if(!(myRs.getString("Windows") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
				}
				else if(!(myRs.getString("Lamp") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
				}
				else if(!(myRs.getString("LampRGB") == null)) {
					modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
				}
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		
		///////Start Data Management Search Device Logic////////////	
		
		bDataSearchDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDataSearchDeviceName.setText("");
				tDataSearchDeviceManu.setText("");
				tDataSearchDeviceType.setText("");
				tDataSearchDeviceIntro.setText("");
				String ifID = ("");
				//Search Data with Device ID
				if (!tDataSearchDeviceID.getText().equals("")) {
					ifID = ("AND DeviceID = '" + tDataSearchDeviceID.getText() + "'");
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDataSearchDeviceID.getText() + "'");
						if (myRs1.next()) {
							tDataSearchDeviceName.setText(myRs1.getString("DeviceName"));
							tDataSearchDeviceManu.setText(myRs1.getString("ManuID"));
							tDataSearchDeviceType.setText(myRs1.getString("TypeID"));
							tDataSearchDeviceIntro.setText(myRs1.getString("DeviceIntro"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
				try {
					String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
					 			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT * FROM Data WHERE DataDel = 0 " + timeRange + ifID);
					modelDataSearch.setRowCount(0);
					while(myRs.next()) {
						if(!(myRs.getString("Humidity") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
						}
						else if(!(myRs.getString("Temperature") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
						}
						else if(!(myRs.getString("Light") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
						}
						else if(!(myRs.getString("Doors") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
						}
						else if(!(myRs.getString("Alarm") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
						}
						else if(!(myRs.getString("Windows") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
						}
						else if(!(myRs.getString("Lamp") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
						}
						else if(!(myRs.getString("LampRGB") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
						}
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		
		///////Start Data Management Search Type Logic////////////	
		
		bDataSearchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDataSearchTypeName.setText("");
				String ifID = ("");
				//Search Data with Type ID
				if (!tDataSearchTypeID.getText().equals("")) {
					ifID = ("AND TypeID = '" + tDataSearchTypeID.getText() + "'");
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT TypeName FROM Type WHERE TypeDel = 0 AND TypeID = '" + tDataSearchTypeID.getText() + "'");
						if (myRs1.next()) {
							tDataSearchTypeName.setText(myRs1.getString("TypeName"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
				try {
					String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
					 			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
					Statement myStmt = conn.createStatement();
					//System.out.println("SELECT DataID, da.DeviceID, DataTime, Humidity, Temperature, Light, Doors, Alarm, Windows, Lamp, LampRGB FROM Data da, Device  de WHERE da.DeviceID = de.DeviceID AND DataDel = 0" + timeRange + ifID);
					ResultSet myRs = myStmt.executeQuery("SELECT DataID, da.DeviceID, DataTime, Humidity, Temperature, Light, Doors, Alarm, Windows, Lamp, LampRGB FROM Data da, Device  de WHERE da.DeviceID = de.DeviceID AND DataDel = 0 " + timeRange + ifID);
					modelDataSearch.setRowCount(0);
					while(myRs.next()) {
						if(!(myRs.getString("Humidity") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
						}
						else if(!(myRs.getString("Temperature") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
						}
						else if(!(myRs.getString("Light") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
						}
						else if(!(myRs.getString("Doors") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
						}
						else if(!(myRs.getString("Alarm") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
						}
						else if(!(myRs.getString("Windows") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
						}
						else if(!(myRs.getString("Lamp") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
						}
						else if(!(myRs.getString("LampRGB") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
						}
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		
		///////Start Data Management Search Home Logic////////////
		
		bDataSearchHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDataSearchHomeAddress.setText("");
				String ifID = ("");
				//Search Data with Home ID
				if (!tDataSearchHomeID.getText().equals("")) {
					ifID = ("AND HomeID = '" + tDataSearchHomeID.getText() + "'");
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT Address FROM Home WHERE HomeDel = 0 AND HomeID = '" + tDataSearchHomeID.getText() + "'");
						if (myRs1.next()) {
							tDataSearchHomeAddress.setText(myRs1.getString("Address"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
				try {
					String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
					 			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT DataID, da.DeviceID, DataTime, Humidity, Temperature, Light, Doors, Alarm, Windows, Lamp, LampRGB FROM Data da, Device  de WHERE da.DeviceID = de.DeviceID AND DataDel = 0 " + timeRange + ifID);
					modelDataSearch.setRowCount(0);
					while(myRs.next()) {
						if(!(myRs.getString("Humidity") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
						}
						else if(!(myRs.getString("Temperature") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
						}
						else if(!(myRs.getString("Light") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
						}
						else if(!(myRs.getString("Doors") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
						}
						else if(!(myRs.getString("Alarm") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
						}
						else if(!(myRs.getString("Windows") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
						}
						else if(!(myRs.getString("Lamp") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
						}
						else if(!(myRs.getString("LampRGB") == null)) {
							modelDataSearch.addRow(new String[] {myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
						}
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		
		///////Start Data Management Statistics Logic////////////
		
		bDataStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
	 			   			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
				tDataStatisticsName.setText("");
				tDataStatisticsManu.setText("");
				tDataStatisticsType.setText("");
				tDataStatisticsIntro.setText("");
				pChart1.removeAll();
				pChart1.repaint();
				//Device ID not null
				if (!tDataStatisticsID.getText().equals("")) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "'");
						//Device ID Exist
						if (myRs1.next()) {
							tDataStatisticsName.setText(myRs1.getString("DeviceName"));
							tDataStatisticsManu.setText(myRs1.getString("ManuID"));
							tDataStatisticsType.setText(myRs1.getString("TypeID"));
							tDataStatisticsIntro.setText(myRs1.getString("DeviceIntro"));
							//Value null
							if (tDataStatisticsValue.getText().equals("")) {}
							//Value not null
							else {
								//Judge If Device Data Is Boolean
								Statement myStmt = conn.createStatement();
								ResultSet myRs = myStmt.executeQuery("SELECT * FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "'" + timeRange);
								//This Device Has No Data
								if (!myRs.next()) {
									JOptionPane.showMessageDialog(null, "This device has no data!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
								}
								//This Device Has Data
								else {
									//This Device Can't Generate A Chart
									if((myRs.getString("Light") != null) || (myRs.getString("Doors") != null) || (myRs.getString("Alarm") != null) || (myRs.getString("Windows") != null)) {
										JOptionPane.showMessageDialog(null, "Chart can't be built because data type is Boolean!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//This Device Can Generate A Chart
									else {
										//Value Is Not a Number
										if (!IsNumber.checkNumber(tDataStatisticsValue.getText())) {
											JOptionPane.showMessageDialog(null, "Value must be a number!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
										}
										//Value Is a Number, All Pass!
										else {
											int a = 0, b = 0, c = 0;
											float x = (float)Integer.parseInt(tDataStatisticsValue.getText());
											//This Device Data is Humidity
											if(!(myRs.getString("Humidity") == null)) {
												Statement myStmt2 = conn.createStatement();
												ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Low FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Humidity < " + x + timeRange);
												myRs2.next();
												a = myRs2.getInt("Low");
												
												Statement myStmt3 = conn.createStatement();
												ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Equal FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Humidity = " + x + timeRange);
												myRs3.next();
												b = myRs3.getInt("Equal");
												
												Statement myStmt4 = conn.createStatement();
												ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS High FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Humidity > " + x + timeRange);
												myRs4.next();
												c = myRs4.getInt("High");
											}
											//This Device Data is Temperature
											else if(!(myRs.getString("Temperature") == null)) {
												Statement myStmt2 = conn.createStatement();
												ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Low FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Temperature < " + x + timeRange);
												myRs2.next();
												a = myRs2.getInt("Low");
												
												Statement myStmt3 = conn.createStatement();
												ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Equal FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Temperature = " + x + timeRange);
												myRs3.next();
												b = myRs3.getInt("Equal");
												
												Statement myStmt4 = conn.createStatement();
												ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS High FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Temperature > " + x + timeRange);
												myRs4.next();
												c = myRs4.getInt("High");
											}
											//This Device Data is Lamp
											else if(!(myRs.getString("Lamp") == null)) {
												Statement myStmt2 = conn.createStatement();
												ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Low FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Lamp < " + x + timeRange);
												myRs2.next();
												a = myRs2.getInt("Low");
												
												Statement myStmt3 = conn.createStatement();
												ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Equal FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Lamp = " + x + timeRange);
												myRs3.next();
												b = myRs3.getInt("Equal");
												
												Statement myStmt4 = conn.createStatement();
												ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS High FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND Lamp > " + x + timeRange);
												myRs4.next();
												c = myRs4.getInt("High");
											}
											//This Device Data is LampRGB
											else if(!(myRs.getString("LampRGB") == null)) {
												Statement myStmt2 = conn.createStatement();
												ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Low FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND LampRGB < " + x + timeRange);
												myRs2.next();
												a = myRs2.getInt("Low");
												
												Statement myStmt3 = conn.createStatement();
												ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Equal FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND LampRGB = " + x + timeRange);
												myRs3.next();
												b = myRs3.getInt("Equal");
												
												Statement myStmt4 = conn.createStatement();
												ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS High FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataStatisticsID.getText() + "' AND LampRGB > " + x + timeRange);
												myRs4.next();
												c = myRs4.getInt("High");
											}
											//Generate The Chart!
											pChart1.add(new StatisticsChart(a, b, c, x).getChartPanel());
											pChart1.repaint();
											pChart1.validate();
										}
									}
								}
							}
						}
						//Device ID Not Exist
						else {
							JOptionPane.showMessageDialog(null, "Device ID not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
				//Device ID null
				else {
					JOptionPane.showMessageDialog(null, "Device ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		///////Start Device Management Distribution Logic////////////
		
		bDataDis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
							+ "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
				tDataDisName.setText("");
				tDataDisManu.setText("");
				tDataDisType.setText("");
				tDataDisIntro.setText("");
				pChart2.removeAll();
				pChart2.repaint();
				//Device ID not null
				if (!tDataDisID.getText().equals("")) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt1 = conn.createStatement();
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND DeviceID = '" + tDataDisID.getText() + "'");
						//Device ID Exist
						if (myRs1.next()) {
							tDataDisName.setText(myRs1.getString("DeviceName"));
							tDataDisManu.setText(myRs1.getString("ManuID"));
							tDataDisType.setText(myRs1.getString("TypeID"));
							tDataDisIntro.setText(myRs1.getString("DeviceIntro"));
							Statement myStmt = conn.createStatement();
							ResultSet myRs = myStmt.executeQuery("SELECT * FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "'" + timeRange);
							//This Device Has No Data
							if (!myRs.next()) {
								JOptionPane.showMessageDialog(null, "This device has no data!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							//This Device Has Data
							else {
								
								//"2" Distribution
								if (cDataDis.getSelectedIndex() == 0) {
									//This device data is Boolean, PASS!
									if((myRs.getString("Light") != null) || (myRs.getString("Doors") != null) || (myRs.getString("Alarm") != null) || (myRs.getString("Windows") != null)) {
										int a = 0, b = 0;
										//This Device Data is Light
										if(!(myRs.getString("Light") == null)) {
											Statement myStmt2 = conn.createStatement();
											ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Off FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Light = 0 " + timeRange);
											myRs2.next();
											a = myRs2.getInt("Off");
											
											Statement myStmt3 = conn.createStatement();
											ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS OnOn FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Light = 1 " + timeRange);
											myRs3.next();
											b = myRs3.getInt("OnOn");
										}
										//This Device Data is Doors
										else if(!(myRs.getString("Doors") == null)) {
											Statement myStmt2 = conn.createStatement();
											ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Off FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Doors = 0 " + timeRange);
											myRs2.next();
											a = myRs2.getInt("Off");
											
											Statement myStmt3 = conn.createStatement();
											ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS OnOn FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Doors = 1 " + timeRange);
											myRs3.next();
											b = myRs3.getInt("OnOn");
										}
										//This Device Data is Alarm
										else if(!(myRs.getString("Alarm") == null)) {
											Statement myStmt2 = conn.createStatement();
											ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Off FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Alarm = 0 " + timeRange);
											myRs2.next();
											a = myRs2.getInt("Off");
											
											Statement myStmt3 = conn.createStatement();
											ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS OnOn FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Alarm = 1 " + timeRange);
											myRs3.next();
											b = myRs3.getInt("OnOn");
										}
										//This Device Data is Windows
										else if(!(myRs.getString("Windows") == null)) {
											Statement myStmt2 = conn.createStatement();
											ResultSet myRs2 = myStmt2.executeQuery("SELECT COUNT(*) AS Off FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Windows = 0 " + timeRange);
											myRs2.next();
											a = myRs2.getInt("Off");
											
											Statement myStmt3 = conn.createStatement();
											ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS OnOn FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND Windows = 1 " + timeRange);
											myRs3.next();
											b = myRs3.getInt("OnOn");
										}
										//Generate The Chart!
										pChart2.add(new DistributionChart2(a, b).getChartPanel());
										pChart2.repaint();
										pChart2.validate();
									}
									//This device data is not Boolean, can't generate "2" distribution
									else {
										cDataDis.setSelectedIndex(1);
										JOptionPane.showMessageDialog(null, "Chart can't be built because data type is not Boolean!\nDistribution automatically changed", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								
								//"3" Distribution
								else if (cDataDis.getSelectedIndex() == 1) {
									//This device data is Boolean, can't generate "3" distribution
									if((myRs.getString("Light") != null) || (myRs.getString("Doors") != null) || (myRs.getString("Alarm") != null) || (myRs.getString("Windows") != null)) {
										cDataDis.setSelectedIndex(0);
										JOptionPane.showMessageDialog(null, "Chart can't be built because data type is Boolean!\nDistribution automatically changed", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//This device data is not Boolean, PASS!
									else {
										int a = 0, b = 0, c = 0;
										float min = 0f, sap1 = 0f, sap2 = 0f, max = 0f;
										String d3 = "";
										//This Device Data is Humidity
										if(!(myRs.getString("Humidity") == null)) {
											d3 = "Humidity";
										}
										//This Device Data is Temperature
										else if(!(myRs.getString("Temperature") == null)) {
											d3 = "Temperature";
										}
										//This Device Data is Lamp
										else if(!(myRs.getString("Lamp") == null)) {
											d3 = "Lamp";
										}
										//This Device Data is LampRGB
										else if(!(myRs.getString("LampRGB") == null)) {
											d3 = "LampRGB";
										}
										Statement myStmt2 = conn.createStatement();
										ResultSet myRs2 = myStmt2.executeQuery("SELECT MIN(" + d3 + ") AS Min, MAX(" + d3 + ") AS Max FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' " + timeRange);
										myRs2.next();
										min = myRs2.getFloat("Min");
										max = myRs2.getFloat("Max");
										
										sap1 = min + ((max - min) / 3);
										sap2 = sap1 + ((max - min) / 3);
										
										Statement myStmt3 = conn.createStatement();
										ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Aa FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d3 + " BETWEEN " + min + " AND " + (sap1-0.001) + timeRange);
										myRs3.next();
										a = myRs3.getInt("Aa");
										
										Statement myStmt4 = conn.createStatement();
										ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS Bb FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d3 + " BETWEEN " + sap1 + " AND " + (sap2-0.001) + timeRange);
										myRs4.next();
										b = myRs4.getInt("Bb");
										
										Statement myStmt5 = conn.createStatement();
										ResultSet myRs5 = myStmt5.executeQuery("SELECT COUNT(*) AS Cc FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d3 + " BETWEEN " + sap2 + " AND " + max + timeRange);
										myRs5.next();
										c = myRs5.getInt("Cc");
										
										//Generate The Chart!
										pChart2.add(new DistributionChart3(a, b, c, min, max).getChartPanel());
										pChart2.repaint();
										pChart2.validate();
									}
								}
								
								//"4" Distribution
								else if (cDataDis.getSelectedIndex() == 2) {
									//This device data is Boolean, can't generate "4" distribution
									if((myRs.getString("Light") != null) || (myRs.getString("Doors") != null) || (myRs.getString("Alarm") != null) || (myRs.getString("Windows") != null)) {
										cDataDis.setSelectedIndex(0);
										JOptionPane.showMessageDialog(null, "Chart can't be built because data type is Boolean!\nDistribution automatically changed", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//This device data is not Boolean, PASS!
									else {
										int a = 0, b = 0, c = 0, d = 0;
										float min = 0f, sap1 = 0f, sap2 = 0f, sap3 = 0f, max = 0f;
										String d4 = "";
										//This Device Data is Humidity
										if(!(myRs.getString("Humidity") == null)) {
											d4 = "Humidity";
										}
										//This Device Data is Temperature
										else if(!(myRs.getString("Temperature") == null)) {
											d4 = "Temperature";
										}
										//This Device Data is Lamp
										else if(!(myRs.getString("Lamp") == null)) {
											d4 = "Lamp";
										}
										//This Device Data is LampRGB
										else if(!(myRs.getString("LampRGB") == null)) {
											d4 = "LampRGB";
										}
										Statement myStmt2 = conn.createStatement();
										ResultSet myRs2 = myStmt2.executeQuery("SELECT MIN(" + d4 + ") AS Min, MAX(" + d4 + ") AS Max FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' " + timeRange);
										myRs2.next();
										min = myRs2.getFloat("Min");
										max = myRs2.getFloat("Max");
										
										sap1 = min + ((max - min) / 4);
										sap2 = sap1 + ((max - min) / 4);
										sap3 = sap2 + ((max - min) / 4);
										
										Statement myStmt3 = conn.createStatement();
										ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Aa FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d4 + " BETWEEN " + min + " AND " + (sap1-0.001) + timeRange);
										myRs3.next();
										a = myRs3.getInt("Aa");
										
										Statement myStmt4 = conn.createStatement();
										ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS Bb FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d4 + " BETWEEN " + sap1 + " AND " + (sap2-0.001) + timeRange);
										myRs4.next();
										b = myRs4.getInt("Bb");
										
										Statement myStmt5 = conn.createStatement();
										ResultSet myRs5 = myStmt5.executeQuery("SELECT COUNT(*) AS Cc FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d4 + " BETWEEN " + sap2 + " AND " + (sap3-0.001) + timeRange);
										myRs5.next();
										c = myRs5.getInt("Cc");
										
										Statement myStmt6 = conn.createStatement();
										ResultSet myRs6 = myStmt6.executeQuery("SELECT COUNT(*) AS Dd FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d4 + " BETWEEN " + sap3 + " AND " + max + timeRange);
										myRs6.next();
										d = myRs6.getInt("Dd");
										
										//Generate The Chart!
										pChart2.add(new DistributionChart4(a, b, c, d, min, max).getChartPanel());
										pChart2.repaint();
										pChart2.validate();
									}
								}
								
								//"5" Distribution
								else if (cDataDis.getSelectedIndex() == 3) {
									//This device data is Boolean, can't generate "5" distribution
									if((myRs.getString("Light") != null) || (myRs.getString("Doors") != null) || (myRs.getString("Alarm") != null) || (myRs.getString("Windows") != null)) {
										cDataDis.setSelectedIndex(0);
										JOptionPane.showMessageDialog(null, "Chart can't be built because data type is Boolean!\nDistribution automatically changed", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//This device data is not Boolean, PASS!
									else {
										int a = 0, b = 0, c = 0, d = 0, e = 0;
										float min = 0f, sap1 = 0f, sap2 = 0f, sap3 = 0f, sap4 = 0f, max = 0f;
										String d5 = "";
										//This Device Data is Humidity
										if(!(myRs.getString("Humidity") == null)) {
											d5 = "Humidity";
										}
										//This Device Data is Temperature
										else if(!(myRs.getString("Temperature") == null)) {
											d5 = "Temperature";
										}
										//This Device Data is Lamp
										else if(!(myRs.getString("Lamp") == null)) {
											d5 = "Lamp";
										}
										//This Device Data is LampRGB
										else if(!(myRs.getString("LampRGB") == null)) {
											d5 = "LampRGB";
										}
										Statement myStmt2 = conn.createStatement();
										ResultSet myRs2 = myStmt2.executeQuery("SELECT MIN(" + d5 + ") AS Min, MAX(" + d5 + ") AS Max FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' " + timeRange);
										myRs2.next();
										min = myRs2.getFloat("Min");
										max = myRs2.getFloat("Max");
										
										sap1 = min + ((max - min) / 5);
										sap2 = sap1 + ((max - min) / 5);
										sap3 = sap2 + ((max - min) / 5);
										sap4 = sap3 + ((max - min) / 5);
										
										Statement myStmt3 = conn.createStatement();
										ResultSet myRs3 = myStmt3.executeQuery("SELECT COUNT(*) AS Aa FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d5 + " BETWEEN " + min + " AND " + (sap1-0.001) + timeRange);
										myRs3.next();
										a = myRs3.getInt("Aa");
										
										Statement myStmt4 = conn.createStatement();
										ResultSet myRs4 = myStmt4.executeQuery("SELECT COUNT(*) AS Bb FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d5 + " BETWEEN " + sap1 + " AND " + (sap2-0.001) + timeRange);
										myRs4.next();
										b = myRs4.getInt("Bb");
										
										Statement myStmt5 = conn.createStatement();
										ResultSet myRs5 = myStmt5.executeQuery("SELECT COUNT(*) AS Cc FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d5 + " BETWEEN " + sap2 + " AND " + (sap3-0.001) + timeRange);
										myRs5.next();
										c = myRs5.getInt("Cc");
										
										Statement myStmt6 = conn.createStatement();
										ResultSet myRs6 = myStmt6.executeQuery("SELECT COUNT(*) AS Dd FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d5 + " BETWEEN " + sap3 + " AND " + (sap4-0.001) + timeRange);
										myRs6.next();
										d = myRs6.getInt("Dd");
										
										Statement myStmt7 = conn.createStatement();
										ResultSet myRs7 = myStmt7.executeQuery("SELECT COUNT(*) AS Ee FROM Data WHERE DataDel = 0 AND DeviceID = '" + tDataDisID.getText() + "' AND " + d5 + " BETWEEN " + sap4 + " AND " + max + timeRange);
										myRs7.next();
										e = myRs7.getInt("Ee");
										
										//Generate The Chart!
										pChart2.add(new DistributionChart5(a, b, c, d, e, min, max).getChartPanel());
										pChart2.repaint();
										pChart2.validate();
									}
								}
								
							}
						}
						//Device ID Not Exist
						else {
							JOptionPane.showMessageDialog(null, "Device ID not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
				//Device ID null
				else {
					JOptionPane.showMessageDialog(null, "Device ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		///////Start Data Management Home Set Logic////////////
		//Table Initialize
		try {
			String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
			 			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT HomeID, DataID, da.DeviceID, DataTime, Humidity, Temperature, Light, Doors, Alarm, Windows, Lamp, LampRGB FROM Data da, Device  de WHERE da.DeviceID = de.DeviceID AND DataDel = 0 " + timeRange + " ORDER BY HomeID");
			modelDataHome.setRowCount(0);
			while(myRs.next()) {
				if(!(myRs.getString("Humidity") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
				}
				else if(!(myRs.getString("Temperature") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
				}
				else if(!(myRs.getString("Light") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
				}
				else if(!(myRs.getString("Doors") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
				}
				else if(!(myRs.getString("Alarm") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
				}
				else if(!(myRs.getString("Windows") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
				}
				else if(!(myRs.getString("Lamp") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
				}
				else if(!(myRs.getString("LampRGB") == null)) {
					modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
				}
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		//Search Logic
		bDataHomeSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				String[] homeSet = tDataHomeSet.getText().split(",");
				String set = "";
				if (!tDataHomeSet.getText().equals("")) {
					int i = 0;
					set += " AND (HomeID = '" + homeSet[i++] + "'";
					while (i != homeSet.length) {
						set += " OR HomeID = '" + homeSet[i++] + "'";
					}
					set += ")";
				}
				try {
					String timeRange = "AND DataTime BETWEEN '" + cTimeStartYear.getSelectedItem() + "-" + cTimeStartMonth.getSelectedItem() + "-" + cTimeStartDay.getSelectedItem() + " " + cTimeStartHour.getSelectedItem() + ":" + cTimeStartMinu.getSelectedItem() + ":" + cTimeStartSec.getSelectedItem()
					 			   + "' AND '" + cTimeEndYear.getSelectedItem() + "-" + cTimeEndMonth.getSelectedItem() + "-" + cTimeEndDay.getSelectedItem() + " " + cTimeEndHour.getSelectedItem() + ":" + cTimeEndMinu.getSelectedItem() + ":" + cTimeEndSec.getSelectedItem() + "'";
					Statement myStmt = conn.createStatement();
					ResultSet myRs = myStmt.executeQuery("SELECT HomeID, DataID, da.DeviceID, DataTime, Humidity, Temperature, Light, Doors, Alarm, Windows, Lamp, LampRGB FROM Data da, Device  de WHERE da.DeviceID = de.DeviceID AND DataDel = 0 " + timeRange + set + " ORDER BY HomeID");
					modelDataHome.setRowCount(0);
					while(myRs.next()) {
						if(!(myRs.getString("Humidity") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Humidity") + "  (Humidity)"});
						}
						else if(!(myRs.getString("Temperature") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Temperature") + "  (Temperature)"});
						}
						else if(!(myRs.getString("Light") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Light") + "  (Light)"});
						}
						else if(!(myRs.getString("Doors") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Doors") + "  (Doors)"});
						}
						else if(!(myRs.getString("Alarm") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Alarm") + "  (Alarm)"});
						}
						else if(!(myRs.getString("Windows") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Windows") + "  (Windows)"});
						}
						else if(!(myRs.getString("Lamp") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("Lamp") + "  (Lamp)"});
						}
						else if(!(myRs.getString("LampRGB") == null)) {
							modelDataHome.addRow(new String[] {myRs.getString("HomeID"), myRs.getString("DataID"), myRs.getString("DeviceID"), myRs.getString("DataTime"), myRs.getString("LampRGB") + "  (LampRGB)"});
						}
					}
				}
				catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
			}
		});
		
		
		///////Start Data Management Manage Delete Logic////////////		
		
		//Check Button
		bDataManageDeleteCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDataManageDeleteTime.setText("");
				tDataManageDeleteCheck.setText("");
				tDataManageDeleteDeviceID.setText("");
				tDataManageDeleteName.setText("");
				tDataManageDeleteType.setText("");
				tDataManageDeleteIntro.setText("");
				tDataManageDeleteValue.setText("");
				if (tDataManageDeleteID.getText().equals("")) {
					tDataManageDeleteCheck.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DataID FROM Data WHERE DataDel = 0 AND DataID = '" + tDataManageDeleteID.getText() + "'");
						if (!myRs.next()) {
							tDataManageDeleteCheck.setText("ID not exist");
						}
						else {
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Data WHERE DataID = '" + tDataManageDeleteID.getText() + "'");
							myRs1.next();
							tDataManageDeleteTime.setText(myRs1.getString("DataTime"));
							tDataManageDeleteDeviceID.setText(myRs1.getString("DeviceID"));
							if(!(myRs1.getString("Humidity") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Humidity") + "%");
							}
							else if(!(myRs1.getString("Temperature") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Temperature") + "¡ãC");
							}
							else if(!(myRs1.getString("Light") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Light"));
							}
							else if(!(myRs1.getString("Doors") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Doors"));
							}
							else if(!(myRs1.getString("Alarm") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Alarm"));
							}
							else if(!(myRs1.getString("Windows") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Windows"));
							}
							else if(!(myRs1.getString("Lamp") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("Lamp"));
							}
							else if(!(myRs1.getString("LampRGB") == null)) {
								tDataManageDeleteValue.setText(myRs1.getString("lampRGB"));
							}
							
							myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = (SELECT DeviceID FROM Data WHERE DataID = '" + tDataManageDeleteID.getText() + "')");
							myRs1.next();
							tDataManageDeleteName.setText(myRs1.getString("DeviceName"));
							tDataManageDeleteType.setText(myRs1.getString("typeID"));
							tDataManageDeleteIntro.setText(myRs1.getString("DeviceIntro"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Delete Button
		bDataManageDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Device ID is null
				if (tDataManageDeleteID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Data ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Device ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DataID FROM Data WHERE DataDel = 0 AND DataID = '" + tDataManageDeleteID.getText() + "'");
						//Data ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Data not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Deletable
						else {
							Statement myStmt1 = conn.createStatement();
							myStmt1.executeUpdate("UPDATE Data SET DataDel = 1 WHERE DataID = '" + tDataManageDeleteID.getText() + "'");
							JOptionPane.showMessageDialog(null, "Successfully deleted!", "Success",JOptionPane.INFORMATION_MESSAGE);
							//Refresh Component
							tDataManageDeleteID.setText("");
							tDataManageDeleteTime.setText("");
							tDataManageDeleteCheck.setText("");
							tDataManageDeleteDeviceID.setText("");
							tDataManageDeleteName.setText("");
							tDataManageDeleteType.setText("");
							tDataManageDeleteIntro.setText("");
							tDataManageDeleteValue.setText("");
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});	
		
		
		///////Start Data Management Manage Modify Logic////////////		
		
		//Check Button
		bDataManageModifyCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				tDataManageModifyTime.setText("");
				tDataManageModifyCheck.setText("");
				tDataManageModifyDeviceID.setText("");
				tDataManageModifyName.setText("");
				tDataManageModifyType.setText("");
				tDataManageModifyIntro.setText("");
				tDataManageModifyValue.setText("");
				if (tDataManageModifyID.getText().equals("")) {
					tDataManageModifyCheck.setText("Empty");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DataID FROM Data WHERE DataDel = 0 AND DataID = '" + tDataManageModifyID.getText() + "'");
						if (!myRs.next()) {
							tDataManageModifyCheck.setText("ID not exist");
						}
						else {
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Data WHERE DataID = '" + tDataManageModifyID.getText() + "'");
							myRs1.next();
							tDataManageModifyTime.setText(myRs1.getString("DataTime"));
							tDataManageModifyDeviceID.setText(myRs1.getString("DeviceID"));
							if(!(myRs1.getString("Humidity") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Humidity"));
							}
							else if(!(myRs1.getString("Temperature") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Temperature"));
							}
							else if(!(myRs1.getString("Light") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Light"));
							}
							else if(!(myRs1.getString("Doors") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Doors"));
							}
							else if(!(myRs1.getString("Alarm") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Alarm"));
							}
							else if(!(myRs1.getString("Windows") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Windows"));
							}
							else if(!(myRs1.getString("Lamp") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("Lamp"));
							}
							else if(!(myRs1.getString("LampRGB") == null)) {
								tDataManageModifyValue.setText(myRs1.getString("lampRGB"));
							}
							myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = (SELECT DeviceID FROM Data WHERE DataID = '" + tDataManageModifyID.getText() + "')");
							myRs1.next();
							tDataManageModifyName.setText(myRs1.getString("DeviceName"));
							tDataManageModifyType.setText(myRs1.getString("typeID"));
							tDataManageModifyIntro.setText(myRs1.getString("DeviceIntro"));
						}
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});
		
		//Modify Button
		bDataManageModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				//Device ID is null
				if (tDataManageModifyID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Data ID can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
				}
				//Device ID not null
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver"); System.out.println("Driver loaded");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthome", "root", "Jerry2001");
						System.out.println("Connection established!");
						Statement myStmt = conn.createStatement();
						ResultSet myRs = myStmt.executeQuery("SELECT DataID FROM Data WHERE DataDel = 0 AND DataID = '" + tDataManageModifyID.getText() + "'");
						//Data ID not exist
						if (!myRs.next()) {
							JOptionPane.showMessageDialog(null, "Data not exist!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
						}
						//Modifiable
						else {
							if (tDataManageModifyValue.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Value can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
							}
							else {
								Statement myStmt1 = conn.createStatement();
								ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Data WHERE DataID = '" + tDataManageModifyID.getText() + "'");
								myRs1.next();
								if(!(myRs1.getString("Humidity") == null)) {
									if(IsNumber.checkNumber(tDataManageModifyValue.getText())) {
										if(Double.parseDouble(tDataManageModifyValue.getText()) > 0 && Double.parseDouble(tDataManageModifyValue.getText()) < 100) {
											Statement myStmt2 = conn.createStatement();
											myStmt2.executeUpdate("UPDATE Data SET Humidity = " + Double.parseDouble(tDataManageModifyValue.getText()) + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
											JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
											//Refresh Component
											tDataManageModifyID.setText("");
											tDataManageModifyTime.setText("");
											tDataManageModifyCheck.setText("");
											tDataManageModifyDeviceID.setText("");
											tDataManageModifyName.setText("");
											tDataManageModifyType.setText("");
											tDataManageModifyIntro.setText("");
											tDataManageModifyValue.setText("");
										}
										else {
											JOptionPane.showMessageDialog(null, "humidity should between 0 and 100", "Something Wrong", JOptionPane.WARNING_MESSAGE);
										}
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except a number for humidity", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Temperature") == null)) {
									if(IsNumber.checkNumber(tDataManageModifyValue.getText())) {
										if(Double.parseDouble(tDataManageModifyValue.getText()) > -30 && Double.parseDouble(tDataManageModifyValue.getText()) < 70) {
											Statement myStmt2 = conn.createStatement();
											myStmt2.executeUpdate("UPDATE Data SET Temperature = " + Double.parseDouble(tDataManageModifyValue.getText()) + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
											JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
											//Refresh Component
											tDataManageModifyID.setText("");
											tDataManageModifyTime.setText("");
											tDataManageModifyCheck.setText("");
											tDataManageModifyDeviceID.setText("");
											tDataManageModifyName.setText("");
											tDataManageModifyType.setText("");
											tDataManageModifyIntro.setText("");
											tDataManageModifyValue.setText("");
										}
										else {
											JOptionPane.showMessageDialog(null, "temperature should between -30 and 70", "Something Wrong", JOptionPane.WARNING_MESSAGE);
										}
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except a number for temperature", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Light") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET Light = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0 or 1 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Doors") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET Doors = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0 or 1 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Alarm") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET Alarm = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0 or 1 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Windows") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET Windows = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0 or 1 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("Lamp") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1") || tDataManageModifyValue.getText().equals("2")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET Lamp = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0, 1 or 2 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(!(myRs1.getString("LampRGB") == null)) {
									if(tDataManageModifyValue.getText().equals("0") || tDataManageModifyValue.getText().equals("1") || tDataManageModifyValue.getText().equals("2")) {
										Statement myStmt2 = conn.createStatement();
										myStmt2.executeUpdate("UPDATE Data SET LampRGB = " + tDataManageModifyValue.getText() + " WHERE DataID = '" + tDataManageModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modified!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										tDataManageModifyID.setText("");
										tDataManageModifyTime.setText("");
										tDataManageModifyCheck.setText("");
										tDataManageModifyDeviceID.setText("");
										tDataManageModifyName.setText("");
										tDataManageModifyType.setText("");
										tDataManageModifyIntro.setText("");
										tDataManageModifyValue.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, "Only except 0, 1 or 2 for this value", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
								}
							}
						}
		
					}
					catch (ClassNotFoundException e) {
						System.out.println("Exception:" + e.getMessage());
					}
					catch (SQLException e) {
						System.out.println("SQLException:" + e.getMessage());
					}
				}
			}
		});			
		
	}
}
