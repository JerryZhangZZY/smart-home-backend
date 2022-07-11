package AccessSoftware;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Rectangle;

public class Manufacturer extends JFrame {

	private String[] typeBox = new String[20];
	private String[] typeIDBox = new String[20];
	
	private JPanel contentPane;
	private Color color1 = new Color(244,244,244);
	private Color color2 = new Color(228,228,228);
	private Color color3 = new Color(202,202,202);
	private JTextField tDeviceSearch;
	private JTable tabDevice;
	private JTextField tDeviceAddID;
	private JTextField tDeviceAddName;
	private Connection conn = null;
	private JTextField tDeviceDeleteID;
	private JTextField tDeviceDeleteName;
	private JTextField tDeviceDeleteType;
	private JTextField tDeviceAdd;
	private JTextField tDeviceDelete;
	private JTextField tDeviceModifyID;
	private JTextField tDeviceModifyName;
	private JTextField tDeviceModify;
	private JTextField tDeviceModifyHome;
	private JTextField tDeviceDeleteHome;
	private JTextField tDeviceAddHome;
	private JTextField tDataStatisticsID;
	private JTextField tDataStatisticsValue;
	private JTextField tDataStatisticsName;
	private JTextField tDataStatisticsManu;
	private JTextField tDataStatisticsType;
	private JTextField tDataDisID;
	private JTextField tDataDisName;
	private JTextField tDataDisManu;
	private JTextField tDataDisType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Manufacturer frame = new Manufacturer("M0001");
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Manufacturer(String thisID) {
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
		int typeNum = 0;
		while(typeBox[typeNum] != null) { typeNum++; }
		String[] typeBoxCut = new String[typeNum];
		String[] typeIDBoxCut = new String[typeNum];
		for(int i = 0; i < typeNum; i++) {
			typeBoxCut[i] = typeBox[i];
			typeIDBoxCut[i] = typeIDBox[i];
		}
		
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
		ImageIcon iDevice = new ImageIcon("icons\\iconDevice.png");
		iDevice.setImage(iDevice.getImage().getScaledInstance(45,45, 0));
		ImageIcon iData = new ImageIcon("icons\\iconData.png");
		iData.setImage(iData.getImage().getScaledInstance(45,45, 0));
		ImageIcon iSearch = new ImageIcon("icons\\iconSearch.png");
		iSearch.setImage(iSearch.getImage().getScaledInstance(15,15, 0));
		ImageIcon iStatistics = new ImageIcon("icons\\iconStatistics.png");
		iStatistics.setImage(iStatistics.getImage().getScaledInstance(30,30, 0));
		ImageIcon iDistribution = new ImageIcon("icons\\iconDistribution.png");
		iDistribution.setImage(iDistribution.getImage().getScaledInstance(30,30, 0));
		
		setTitle("Manufacturturer");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 601);
		setSize(1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(color1);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
////////////////////////////////////////////////////////////////////////////Start Device Management Panel/////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		JPanel pExit = new JPanel();
		pExit.setLayout(null);
		pExit.setBackground(color1);
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
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 19));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 986, 663);
		contentPane.add(tabbedPane);
		
		JPanel pDevice = new JPanel();
		tabbedPane.addTab("Device Management  ", null, pDevice, null);
		tabbedPane.setIconAt(0, iDevice);
		pDevice.setLayout(null);
		
		JScrollPane scrollPane_Device = new JScrollPane();
		scrollPane_Device.setBorder(new TitledBorder(null, "Device Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Device.setBounds(0, 0, 733, 269);
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
		pDeviceSearch.setLayout(null);
		pDeviceSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceSearch.setBounds(0, 347, 732, 88);
		pDevice.add(pDeviceSearch);
		
		tDeviceSearch = new JTextField();
		tDeviceSearch.setFont(new Font("Arial", Font.PLAIN, 17));
		tDeviceSearch.setColumns(10);
		tDeviceSearch.setBounds(93, 23, 202, 42);
		pDeviceSearch.add(tDeviceSearch);
		
		JComboBox cDeviceSearchType = new JComboBox();
		cDeviceSearchType.setBackground(Color.WHITE);
		cDeviceSearchType.setModel(new DefaultComboBoxModel(new String[] {"Device ID", "Device Name"}));
		cDeviceSearchType.setFont(new Font("Arial", Font.PLAIN, 15));
		cDeviceSearchType.setBounds(295, 23, 171, 42);
		pDeviceSearch.add(cDeviceSearchType);
		
		JButton bDeviceSearch = new JButton("Search");
		bDeviceSearch.setIcon(iSearch);
		bDeviceSearch.setBackground(Color.WHITE);
		bDeviceSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceSearch.setBounds(527, 27, 105, 33);
		pDeviceSearch.add(bDeviceSearch);
		
		JPanel pDeviceSorting = new JPanel();
		pDeviceSorting.setLayout(null);
		pDeviceSorting.setBorder(new TitledBorder(null, "Sorting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceSorting.setBounds(372, 271, 360, 77);
		pDevice.add(pDeviceSorting);
		
		JComboBox cDeviceSort = new JComboBox();
		cDeviceSort.setForeground(Color.DARK_GRAY);
		cDeviceSort.setBackground(Color.WHITE);
		cDeviceSort.setModel(new DefaultComboBoxModel(new String[] {"Device ID", "Device Name", "Home ID", "Type ID"}));
		cDeviceSort.setSelectedIndex(0);
		cDeviceSort.setMaximumRowCount(10);
		cDeviceSort.setFont(new Font("Arial", Font.PLAIN, 15));
		cDeviceSort.setBounds(119, 23, 121, 30);
		pDeviceSorting.add(cDeviceSort);
		
		JPanel pDeviceFilter = new JPanel();
		pDeviceFilter.setLayout(null);
		pDeviceFilter.setBorder(new TitledBorder(null, "Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDeviceFilter.setBounds(0, 271, 373, 77);
		pDevice.add(pDeviceFilter);
		
		//Add "All"
		String[] typeBoxCutPlus = new String[typeNum + 1];
		typeBoxCutPlus[0] = "All";
		for(int i = 0; i < typeNum; i++) {
			typeBoxCutPlus[i + 1] = typeBoxCut[i];
		}
		JComboBox cDeviceType = new JComboBox(typeBoxCutPlus);
		cDeviceType.setBackground(Color.WHITE);
		cDeviceType.setSelectedIndex(0);
		cDeviceType.setMaximumRowCount(10);
		cDeviceType.setFont(new Font("Arial", Font.PLAIN, 15));
		cDeviceType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		cDeviceType.setBounds(112, 11, 136, 54);
		pDeviceFilter.add(cDeviceType);
		
		JTabbedPane tabbedPane_Device = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Device.setFont(new Font("Arial", Font.PLAIN, 20));
		tabbedPane_Device.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane_Device.setBounds(0, 433, 733, 225);
		pDevice.add(tabbedPane_Device);
		
		JPanel pDeviceAdd = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceAdd, null);
		tabbedPane_Device.setIconAt(0, iAdd);
		pDeviceAdd.setLayout(null);
		tDeviceAddID = new JTextField();
		tDeviceAddID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddID.setColumns(10);
		tDeviceAddID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceAddID.setBounds(10, 10, 124, 40);
		pDeviceAdd.add(tDeviceAddID);
		
		tDeviceAddName = new JTextField();
		tDeviceAddName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddName.setColumns(10);
		tDeviceAddName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceAddName.setBounds(144, 10, 133, 40);
		pDeviceAdd.add(tDeviceAddName);
		
		JTextArea tDeviceAddIntro = new JTextArea();
		tDeviceAddIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceAddIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceAddIntro.setBounds(10, 57, 516, 132);
		pDeviceAdd.add(tDeviceAddIntro);
		
		JComboBox cDeviceAddType = new JComboBox(typeBoxCut);
		cDeviceAddType.setBackground(Color.WHITE);
		cDeviceAddType.setBorder(new TitledBorder(null, "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDeviceAddType.setBounds(393, 0, 133, 50);
		pDeviceAdd.add(cDeviceAddType);
		
		JButton bDeviceAdd = new JButton("Apply");
		bDeviceAdd.setBackground(Color.WHITE);
		bDeviceAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceAdd.setBounds(546, 162, 85, 27);
		pDeviceAdd.add(bDeviceAdd);
		
		JButton bDeviceAddCheck = new JButton("Check");
		bDeviceAddCheck.setBackground(Color.WHITE);
		bDeviceAddCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceAddCheck.setBounds(557, 10, 74, 22);
		pDeviceAdd.add(bDeviceAddCheck);
		
		tDeviceAdd = new JTextField();
		tDeviceAdd.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceAdd.setColumns(10);
		tDeviceAdd.setBorder(null);
		tDeviceAdd.setBackground(color1);
		tDeviceAdd.setBounds(557, 42, 74, 23);
		pDeviceAdd.add(tDeviceAdd);
		
		tDeviceAddHome = new JTextField();
		tDeviceAddHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceAddHome.setColumns(10);
		tDeviceAddHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceAddHome.setBounds(287, 10, 96, 40);
		pDeviceAdd.add(tDeviceAddHome);
		
		JPanel pDeviceDelete = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceDelete, null);
		tabbedPane_Device.setIconAt(1, iDelete);
		pDeviceDelete.setLayout(null);
		
		tDeviceDeleteID = new JTextField();
		tDeviceDeleteID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteID.setColumns(10);
		tDeviceDeleteID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceDeleteID.setBounds(10, 10, 124, 40);
		pDeviceDelete.add(tDeviceDeleteID);
		
		JTextArea tDeviceDeleteIntro = new JTextArea();
		tDeviceDeleteIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceDeleteIntro.setEditable(false);
		tDeviceDeleteIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceDeleteIntro.setBackground(SystemColor.menu);
		tDeviceDeleteIntro.setBounds(10, 57, 516, 132);
		pDeviceDelete.add(tDeviceDeleteIntro);
		
		tDeviceDeleteName = new JTextField();
		tDeviceDeleteName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteName.setEditable(false);
		tDeviceDeleteName.setColumns(10);
		tDeviceDeleteName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteName.setBounds(144, 10, 133, 40);
		pDeviceDelete.add(tDeviceDeleteName);
		
		tDeviceDeleteType = new JTextField();
		tDeviceDeleteType.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteType.setEditable(false);
		tDeviceDeleteType.setColumns(10);
		tDeviceDeleteType.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteType.setBounds(393, 10, 133, 40);
		pDeviceDelete.add(tDeviceDeleteType);
		
		JButton bDeviceDeleteCheck = new JButton("Check");
		bDeviceDeleteCheck.setBackground(Color.WHITE);
		bDeviceDeleteCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceDeleteCheck.setBounds(557, 10, 74, 22);
		pDeviceDelete.add(bDeviceDeleteCheck);
		
		JButton bDeviceDelete = new JButton("Delete");
		bDeviceDelete.setBackground(Color.WHITE);
		bDeviceDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceDelete.setBounds(546, 162, 85, 27);
		pDeviceDelete.add(bDeviceDelete);
		
		tDeviceDelete = new JTextField();
		tDeviceDelete.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceDelete.setColumns(10);
		tDeviceDelete.setBorder(null);
		tDeviceDelete.setBackground(color1);
		tDeviceDelete.setBounds(557, 42, 74, 23);
		pDeviceDelete.add(tDeviceDelete);
		
		tDeviceDeleteHome = new JTextField();
		tDeviceDeleteHome.setEditable(false);
		tDeviceDeleteHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceDeleteHome.setColumns(10);
		tDeviceDeleteHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceDeleteHome.setBounds(287, 10, 96, 40);
		pDeviceDelete.add(tDeviceDeleteHome);
		
		JPanel pDeviceModify = new JPanel();
		tabbedPane_Device.addTab("", null, pDeviceModify, null);
		tabbedPane_Device.setIconAt(2, iModify);
		pDeviceModify.setLayout(null);
		
		tDeviceModifyID = new JTextField();
		tDeviceModifyID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyID.setColumns(10);
		tDeviceModifyID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceModifyID.setBounds(10, 10, 124, 40);
		pDeviceModify.add(tDeviceModifyID);
		
		JTextArea tDeviceModifyIntro = new JTextArea();
		tDeviceModifyIntro.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tDeviceModifyIntro.setBorder(new TitledBorder(null, "Device Introduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDeviceModifyIntro.setBounds(10, 57, 516, 132);
		pDeviceModify.add(tDeviceModifyIntro);
		
		tDeviceModifyName = new JTextField();
		tDeviceModifyName.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyName.setColumns(10);
		tDeviceModifyName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceModifyName.setBounds(144, 10, 133, 40);
		pDeviceModify.add(tDeviceModifyName);
		
		JComboBox cDeviceModifyType = new JComboBox(typeBoxCut);
		cDeviceModifyType.setBackground(Color.WHITE);
		cDeviceModifyType.setBorder(new TitledBorder(null, "Device Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDeviceModifyType.setBounds(393, 0, 133, 50);
		pDeviceModify.add(cDeviceModifyType);
		
		JButton bDeviceModify = new JButton("Save");
		bDeviceModify.setBackground(Color.WHITE);
		bDeviceModify.setFont(new Font("Arial", Font.PLAIN, 15));
		bDeviceModify.setBounds(546, 162, 85, 27);
		pDeviceModify.add(bDeviceModify);
		
		JButton bDeviceModifyCheck = new JButton("Check");
		bDeviceModifyCheck.setBackground(Color.WHITE);
		bDeviceModifyCheck.setFont(new Font("Arial", Font.PLAIN, 13));
		bDeviceModifyCheck.setBounds(557, 10, 74, 22);
		pDeviceModify.add(bDeviceModifyCheck);
		
		tDeviceModify = new JTextField();
		tDeviceModify.setHorizontalAlignment(SwingConstants.CENTER);
		tDeviceModify.setColumns(10);
		tDeviceModify.setBorder(null);
		tDeviceModify.setBackground(color1);
		tDeviceModify.setBounds(557, 42, 74, 23);
		pDeviceModify.add(tDeviceModify);
		
		tDeviceModifyHome = new JTextField();
		tDeviceModifyHome.setFont(new Font("Arial", Font.PLAIN, 15));
		tDeviceModifyHome.setColumns(10);
		tDeviceModifyHome.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Home", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDeviceModifyHome.setBounds(287, 10, 96, 40);
		pDeviceModify.add(tDeviceModifyHome);
		
		JPanel pData = new JPanel();
		tabbedPane.addTab("Data Management   ", null, pData, null);
		tabbedPane.setIconAt(1, iData);
		pData.setLayout(null);
		
		JPanel pTimeRange = new JPanel();
		pTimeRange.setBorder(new TitledBorder(null, "Time Range", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.setBounds(0, 512, 719, 146);
		pData.add(pTimeRange);
		pTimeRange.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pTimeStart = new JPanel();
		pTimeStart.setLayout(null);
		pTimeStart.setBorder(new TitledBorder(null, "Start", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.add(pTimeStart);
		
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
		pTimeEnd.setLayout(null);
		pTimeEnd.setBorder(new TitledBorder(null, "End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pTimeRange.add(pTimeEnd);
		
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
		
		JTabbedPane tabbedPane_Data = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_Data.setForeground(Color.DARK_GRAY);
		tabbedPane_Data.setFont(new Font("Arial", Font.PLAIN, 15));
		tabbedPane_Data.setBackground(Color.WHITE);
		tabbedPane_Data.setBounds(0, 0, 719, 513);
		pData.add(tabbedPane_Data);
		
		JPanel pDataStatistics = new JPanel();
		tabbedPane_Data.addTab("Statistics   ", null, pDataStatistics, null);
		tabbedPane_Data.setIconAt(0, iStatistics);
		pDataStatistics.setLayout(null);
		
		tDataStatisticsID = new JTextField();
		tDataStatisticsID.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsID.setColumns(10);
		tDataStatisticsID.setBorder(new TitledBorder(null, "Device ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tDataStatisticsID.setBounds(10, 10, 108, 40);
		pDataStatistics.add(tDataStatisticsID);
		
		tDataStatisticsValue = new JTextField();
		tDataStatisticsValue.setFont(new Font("Arial", Font.PLAIN, 15));
		tDataStatisticsValue.setColumns(10);
		tDataStatisticsValue.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Value", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		tDataStatisticsValue.setBounds(10, 60, 108, 40);
		pDataStatistics.add(tDataStatisticsValue);
		
		JButton bDataStatistics = new JButton();
		bDataStatistics.setIcon(iDraw);
		bDataStatistics.setForeground(Color.DARK_GRAY);
		bDataStatistics.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataStatistics.setBackground(Color.WHITE);
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
		
		JPanel pChart1 = new JPanel();
		pChart1.setBounds(10, 195, 563, 300);
		pDataStatistics.add(pChart1);
		pChart1.setLayout(new BoxLayout(pChart1, BoxLayout.X_AXIS));
		
		JPanel pDataDistribution = new JPanel();
		tabbedPane_Data.addTab("Distribution", null, pDataDistribution, null);
		tabbedPane_Data.setIconAt(1, iDistribution);
		pDataDistribution.setLayout(null);
		
		JButton bDataDis = new JButton();
		bDataDis.setIcon(iDraw);
		bDataDis.setForeground(Color.DARK_GRAY);
		bDataDis.setFont(new Font("Arial", Font.PLAIN, 17));
		bDataDis.setBackground(Color.WHITE);
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
		
		JComboBox cDataDis = new JComboBox();
		cDataDis.setForeground(Color.DARK_GRAY);
		cDataDis.setBackground(Color.WHITE);
		cDataDis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cDataDis.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5"}));
		cDataDis.setSelectedIndex(1);
		cDataDis.setBorder(new TitledBorder(null, "Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cDataDis.setBounds(10, 57, 108, 55);
		pDataDistribution.add(cDataDis);
		
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
		
		JPanel pChart2 = new JPanel();
		pChart2.setBounds(10, 195, 563, 303);
		pDataDistribution.add(pChart2);
		pChart2.setLayout(new BoxLayout(pChart2, BoxLayout.X_AXIS));
		
		//JTable Initialize
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'");
			modelDevice.setRowCount(0);
			while(myRs.next()) {
				modelDevice.addRow(new String[] {myRs.getString("DeviceID"), myRs.getString("DeviceName"), myRs.getString("HomeID"), myRs.getString("ManuID"), myRs.getString("TypeID"), myRs.getString("DeviceIntro")});
			}
		}
		catch (SQLException e) { System.out.println("SQLException:" + e.getMessage()); }
		
		String[] sortingDevice = {"DeviceID", "DeviceName", "HomeID", "TypeID"};
		
		//Device Filter
		cDeviceType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					//Content is not null
					if (!tDeviceSearch.getText().equals("")) {
						//Search by ID
						if (cDeviceSearchType.getSelectedIndex() == 0) {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceID = '"
														+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND AND ManuID = '" + thisID + "' TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cDeviceType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
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
					if (!tDeviceSearch.getText().equals("")) {
						//Search by ID
						if (cDeviceSearchType.getSelectedIndex() == 0) {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceID = '"
														+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND AND ManuID = '" + thisID + "' TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cDeviceType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
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
		bDeviceSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				try {
					Statement myStmt = conn.createStatement();
					ResultSet myRs = null;
					//Content is not null
					if (!tDeviceSearch.getText().equals("")) {
						//Search by ID
						if (cDeviceSearchType.getSelectedIndex() == 0) {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceID = '"
														+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceID = '"
										+ tDeviceSearch.getText() + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							
						}
						//Search by Name
						else {
							//All type
							if (cDeviceType.getSelectedIndex() == 0) {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
							//Specific type
							else {
								myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND AND ManuID = '" + thisID + "' TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' AND DeviceName LIKE '%"
										+ tDeviceSearch.getText() + "%' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
							}
						}
						
					}
					//Content is null
					else {
						//All type
						if (cDeviceType.getSelectedIndex() == 0) {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
						}
						//Specific type
						else {
							myRs = myStmt.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND TypeID = '" + typeIDBox[cDeviceType.getSelectedIndex() - 1] + "' ORDER BY " + sortingDevice[cDeviceSort.getSelectedIndex()]);
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
									//Intro null
									if (tDeviceAddIntro.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Device intro can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Intro pass, all pass!
									else {
										Statement myStmt3 = conn.createStatement();
										myStmt3.executeUpdate("INSERT INTO Device VALUES ('" + tDeviceAddID.getText() + "','" + tDeviceAddHome.getText() + "','" + thisID + "','" + typeIDBoxCut[cDeviceAddType.getSelectedIndex()] + "','" + tDeviceAddName.getText() + "','" + tDeviceAddIntro.getText() + "',0);");
										JOptionPane.showMessageDialog(null, "Successfully added!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										cDeviceType.setSelectedIndex(0);
										cDeviceSort.setSelectedIndex(0);
										tDeviceSearch.setText("");
										cDeviceSearchType.setSelectedIndex(0);
										tDeviceAddID.setText("");
										tDeviceAddName.setText("");
										tDeviceAddHome.setText("");
										cDeviceAddType.setSelectedIndex(0);
										tDeviceAddIntro.setText("");
										//Refresh JTable
										Statement myStmt5 = conn.createStatement();
										ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'");
										modelDevice.setRowCount(0);
										while(myRs5.next()) {
											modelDevice.addRow(new String[] {myRs5.getString("DeviceID"), myRs5.getString("DeviceName"), myRs5.getString("HomeID"), myRs5.getString("ManuID"), myRs5.getString("TypeID"), myRs5.getString("DeviceIntro")});
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
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'  AND DeviceID = '" + tDeviceDeleteID.getText() + "'");
						if (!myRs.next()) {
							tDeviceDelete.setText("ID not exist");
						}
						else {
							//Deletable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = '" + tDeviceDeleteID.getText() + "'");
							myRs1.next();
							tDeviceDeleteName.setText(myRs1.getString("DeviceName"));
							tDeviceDeleteHome.setText(myRs1.getString("HomeID"));
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
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'  AND DeviceID = '" + tDeviceDeleteID.getText() + "'");
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
							cDeviceType.setSelectedIndex(0);
							cDeviceSort.setSelectedIndex(0);
							tDeviceSearch.setText("");
							cDeviceSearchType.setSelectedIndex(0);
							tDeviceDeleteID.setText("");
							tDeviceDeleteName.setText("");
							tDeviceDeleteHome.setText("");
							tDeviceDeleteType.setText("");
							tDeviceDeleteIntro.setText("");
							//Refresh JTable
							Statement myStmt5 = conn.createStatement();
							ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'");
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
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'  AND DeviceID = '" + tDeviceModifyID.getText() + "'");
						if (!myRs.next()) {
							tDeviceModify.setText("ID not exist");
						}
						else {
							//Editable
							Statement myStmt1 = conn.createStatement();
							ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceID = '" + tDeviceModifyID.getText() + "'");
							myRs1.next();
							tDeviceModifyName.setText(myRs1.getString("DeviceName"));
							tDeviceModifyHome.setText(myRs1.getString("HomeID"));
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
						ResultSet myRs = myStmt.executeQuery("SELECT DeviceID FROM Device WHERE ManuID = '" + thisID + "' AND DeviceID = '" + tDeviceModifyID.getText() + "'");
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
									
									//Intro null
									if (tDeviceModifyIntro.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "Device intro can't be null!", "Something Wrong", JOptionPane.WARNING_MESSAGE);
									}
									//Intro pass, all pass!
									else {
										Statement myStmt3 = conn.createStatement();
										myStmt3.executeUpdate("UPDATE Device SET DeviceName = '" + tDeviceModifyName.getText() +"', HomeID = '" + tDeviceModifyHome.getText() + "', ManuID = '" + thisID + "', TypeID = '" + typeIDBoxCut[cDeviceModifyType.getSelectedIndex()] + "', DeviceIntro = '" + tDeviceModifyIntro.getText() + "' WHERE DeviceID = '" + tDeviceModifyID.getText() + "'");
										JOptionPane.showMessageDialog(null, "Successfully Modifyed!", "Success",JOptionPane.INFORMATION_MESSAGE);
										//Refresh Component
										cDeviceType.setSelectedIndex(0);
										cDeviceSort.setSelectedIndex(0);
										tDeviceSearch.setText("");
										cDeviceSearchType.setSelectedIndex(0);
										tDeviceModifyID.setText("");
										tDeviceModifyName.setText("");
										tDeviceModifyHome.setText("");
										cDeviceModifyType.setSelectedIndex(0);
										tDeviceModifyIntro.setText("");
										//Refresh JTable
										Statement myStmt5 = conn.createStatement();
										ResultSet myRs5 = myStmt5.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "'");
										modelDevice.setRowCount(0);
										while(myRs5.next()) {
											modelDevice.addRow(new String[] {myRs5.getString("DeviceID"), myRs5.getString("DeviceName"), myRs5.getString("HomeID"), myRs5.getString("ManuID"), myRs5.getString("TypeID"), myRs5.getString("DeviceIntro")});
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
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceID = '" + tDataStatisticsID.getText() + "'");
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
						ResultSet myRs1 = myStmt1.executeQuery("SELECT * FROM Device WHERE DeviceDel = 0 AND ManuID = '" + thisID + "' AND DeviceID = '" + tDataDisID.getText() + "'");
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
	}
}
