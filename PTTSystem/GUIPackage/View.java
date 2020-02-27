package GUIPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;




public class View extends JFrame  implements ActionListener{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,createClassBN,classListBN,teacherList,requestList,createCourse,createClassOKBN,createClassCBN;
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color blue = new java.awt.Color(135,206,250);
	public JPanel barPanel, loginPanel, semesterPanel, framePanel, centerPanel, createClassPanel, 
					classListPanel, rootPanel, classDetailPanel, selectTeacherPanel;
	public JTable classListTable, staffListTable;
	public JFrame selectTeacherWindow;
	public JTextArea requirementTA;
	public Semester semester;
	public Login login;
	public Bar bar;
	public Main main;
	public Frame frame;
	private CardLayout page;
	private CardLayout centerPage;	
	public View(){
		//Set Frame title, size, the location where execution occurs, and action to close a window;
	
	}
	public void initialise() {
		this.getContentPane().removeAll();

		this.setTitle("PTT Manage System");
		this.setSize(800,550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50,50);
		semester = new Semester();
		login = new Login();
		bar = new Bar();
		main = new Main();
		frame = new Frame();
		loginPanel=null;
		barPanel=null;
		rootPanel = new JPanel();
		page = new CardLayout();
		rootPanel.setLayout(page);
		this.add(rootPanel);
		this.setVisible(true);
		refresh();
		
		System.out.println("initilase");
	}
	//clean all registered classDetailPanel
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==main.selectTeacherPage.selectTeacherSubmitBN) {
			main.selectTeacherPage.setSelectedTeacher();
			centerPage.show(centerPanel, "classDetailPanel");
		}
		
	}
	
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	
	
	public class Login{
		public void buildLoginPanel() {
				loginPanel = new JPanel(new GridLayout(4,1,0,12));
				loginPanel.setBackground(Color.white);
				loginPanel.setFocusable(true);
				loginPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
				
				JLabel title = new JLabel("Login", SwingConstants.CENTER);
				Font f = new Font("TimesRoman",Font.PLAIN,23);
				title.setFont(f);
				
				usernameTF = new JTextField(16);
				usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, "username"));
				usernameTF.setBorder(BorderFactory.createLineBorder(blue)); 
				
				passwordTF = new JTextField(16);
				passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, "password"));
				passwordTF.setBorder(BorderFactory.createLineBorder(blue));
				
				loginBN = new JButton("ENTER");
				loginBN.setBackground(blue);
				loginBN.setFocusPainted(false);
		
				loginPanel.add(title);
				loginPanel.add(usernameTF);
				loginPanel.add(passwordTF);
				loginPanel.add(loginBN);
				View.this.rootPanel.add(loginPanel,"LoginPage");
		}
		
		public JPanel getLoginPanel() {
			return loginPanel;
		}
		
		//get login input
		public String[] getAccount() {
			String[] s = new String[2];
			s[0]= usernameTF.getText();
			s[1]= passwordTF.getText();	
			return s;
		}
		
		//when user type wrong
		public void cleanLogin() {
			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, "username"));
			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, "password"));
		}
		
		public int logOutCheck() {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "wanring",JOptionPane.YES_NO_OPTION);
			return n;
		}
		
		public void displayLoginPanel() {
			View.this.page.show(rootPanel, "LoginPage");
			refresh();
		}
	}
	
	public class Semester{
		public void buildSemesterPanel() {
				semesterPanel = new JPanel(new GridLayout(4,1,0,12));
				semesterPanel.setBackground(Color.white);
				semesterPanel.setFocusable(true);
				semesterPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
				
				JLabel title = new JLabel("Select a semester to access.", SwingConstants.CENTER);
				Font f = new Font("TimesRoman",Font.PLAIN,12);
				title.setFont(f);
				
				semesterTF = new JTextField(4);
		
				semesterTF.setBorder(BorderFactory.createLineBorder(blue)); 
				
				
				semesterBN = new JButton("Select");
				semesterBN.setBackground(blue);
				semesterBN.setFocusPainted(false);
		
				semesterPanel.add(title);
				semesterPanel.add(semesterTF);
				semesterPanel.add(semesterBN);
				View.this.rootPanel.add(semesterPanel,"SemesterPage");
		}
		
		public void displayLatestSemester(int num) {
			semesterTF.addFocusListener(new JTextFieldHintListener(semesterTF, ""+num));
		}
		
		public int getSelecetedSemester() {
			return Integer.parseInt(semesterTF.getText());
		}
		
		public void displaySemesterPanel() {
			View.this.page.show(rootPanel, "SemesterPage");
			View.this.refresh();
		}
		
	}
	
	
	public class Frame{
		public void buildFramePanel(JPanel bar) {
			framePanel = new JPanel(new BorderLayout());
			framePanel.add(bar, BorderLayout.WEST);
			framePanel.add(View.this.centerPanel, BorderLayout.CENTER);
			rootPanel.add(framePanel, "mainPage");
			
		}
		public void displayFramePanel() {
			page.show(rootPanel, "mainPage");
			View.this.refresh();
		}
	}
	
	public class Main{
		String defaultClassName = "  Please enter a class name, max length 20.";
		String defaultClassRequirement = "  Please enter a requirement, max length 200 words.";
		public CourseDetailPage courseDetailPage;
		public SelectTeacherPage selectTeacherPage;
	
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
			courseDetailPage = new CourseDetailPage();
			selectTeacherPage = new SelectTeacherPage();
		}
		
		
		public void buildCreateClassPanel() {
			JPanel centerP = new JPanel(null);
			centerP.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
			JPanel buttonPanel = new JPanel(new FlowLayout());
			JPanel textAreaPanel = new JPanel(new BorderLayout());
			centerP.setBackground(Color.white);
			buttonPanel.setBackground(Color.white);
			textAreaPanel.setBackground(Color.white);
			
			JLabel createClassTitleL = new JLabel("Create a new class", SwingConstants.CENTER);
			createClassTitleL.setFont(new Font("Arial", Font.PLAIN, 23));
			createClassTitleL.setBounds(220, 30, 200, 62);
			centerP.add(createClassTitleL);
			
			
			textAreaPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
			JLabel classNameL = new JLabel("Class name:     ");
			classNameL.setForeground(new Color(114, 114, 114));
			classNameL.setFont(new Font("Arial", Font.PLAIN, 14));
			classNameL.setBounds(168, 104, 112, 21);
			centerP.add(classNameL);
			JLabel reqLTitle1 = new JLabel("Requirement:  ");
			
			reqLTitle1.setForeground(new Color(114, 114, 114));
			reqLTitle1.setFont(new Font("Arial", Font.PLAIN, 14));
			reqLTitle1.setBounds(168, 184, 112, 21);
			centerP.add(reqLTitle1);
			
			
			
			courseNameTF = new JTextField();
		
			courseNameTF.setFont(new Font("Arial", Font.PLAIN, 12));
			courseNameTF.setForeground(new Color(20,20,20));
			courseNameTF.setBounds(164, 125, 319, 36);

			courseNameTF.setColumns(10);
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
			
			centerP.add(courseNameTF);
			
			
			
			
			int TA_row = 10;
			int TA_col = 30;
			
			requirementTA= new JTextArea(TA_row, TA_col);
			requirementTA.setFont(new Font("Arial", Font.PLAIN, 12));
			requirementTA.setMaximumSize(new Dimension(5, 30));
			requirementTA.setForeground(new Color(20,20,20));

			requirementTA.setWrapStyleWord(true);
			requirementTA.setLineWrap(true);
			
			requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));
			
			JScrollPane reqScrollPanel = new JScrollPane( requirementTA );
			reqScrollPanel.setBounds(164, 208, 319, 160);

//			requirment.addFocusListener(new JTextFieldHintListener(requirment, defaultClassRequirements));
//			requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, defaultClassRequirements));
			

			reqScrollPanel.setBorder(BorderFactory.createLineBorder(new Color(114, 114, 114)));
			
			centerP.add(reqScrollPanel);
			
//			requirment2TF.setBorder(BorderFactory.createLineBorder(blue));
//			add = new JButton("add a new requirement");
			
			createClassOKBN = new JButton("Ok");
			createClassOKBN.setBounds(415, 391, 70, 33);
			createClassOKBN.setForeground(Color.white);
			createClassOKBN.setFont(new Font("Arial", Font.PLAIN,12));	
			createClassOKBN.setBackground(new Color(56, 151, 240));
			createClassOKBN.setFocusPainted(false);
			centerP.add(createClassOKBN);
			
			
			
			createClassCBN = createBlackButton("Cancel");
			createClassCBN.setBounds(166, 391, 80, 33);
			
			centerP.add(createClassCBN);
			
			createClassPanel = centerP;
			centerPanel.add(createClassPanel, "createClassPanel");
			
		}
		
		public void displayCreateClassPanel() {
			centerPage.show(centerPanel, "createClassPanel");
			cleanCreateClassText();
			View.this.refresh();
		}
		
		
		
		
		public String getCreateClassString() {
			String s = "";
			if(courseNameTF.getText().equals(defaultClassName)|| courseNameTF.getText().equals("")) {
				return null;
			}
			s += encodeString(courseNameTF.getText());
			String req ="";
			if(requirementTA.getText().equals(" ")|| requirementTA.getText().equals("")) {
				return null;
			}
			req += requirementTA.getText();
			
			s += encodeString(req);
	
			return s;
		}
		
		public void cleanCreateClassText() {
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
			courseNameTF.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));

		}
		
	
	
		
		
		
		public void buildClassListPanel(String[] header, String[][] list) {
			classListPanel = new JPanel(new BorderLayout());
			JPanel classListSubP = new JPanel(new BorderLayout());
			JLabel space = new JLabel("");
			createClassBN = new JButton ("Create a class");
			createClassBN.setVisible(false);
			createClassBN.setBackground(blue);
			createClassBN.addActionListener(View.this);
			classListSubP.setBackground(Color.white);
			classListPanel.setBackground(Color.white);
			classListSubP.add(space, BorderLayout.CENTER);
			classListSubP.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
			classListSubP.add(createClassBN, BorderLayout.EAST);
			classListPanel.add(classListSubP, BorderLayout.NORTH);
			classListTable = buildModelListTable(header, list);
			enableTableHoverEffect(classListTable);
			JScrollPane sp=new JScrollPane();
	        sp.setViewportView(classListTable);
	        sp.getViewport().setBackground(Color.WHITE);
	        sp.setBackground(Color.white);
			
			classListPanel.add(sp, BorderLayout.CENTER);
			classListPanel.setVisible(true);
			centerPanel.add(classListPanel, "classListPanel");
		}
		
		
		
		public class ForcedListSelectionModel extends DefaultListSelectionModel {

		    public ForcedListSelectionModel () {
		        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    }

		    @Override
		    public void clearSelection() {
		    }

		    @Override
		    public void removeSelectionInterval(int index0, int index1) {
		    }

		}
		
		
		public void displayClassListPanel(String[] header, String[][] list) {	
			TableModel m = new DefaultTableModel(list, header) ;
			View.this.classListTable.setModel(m);
			centerPage.show(centerPanel, "classListPanel");
			refresh();
		}
		
		
		public class CourseDetailPage {
			JLabel classNameL;
			JLabel classIDTitleL;
			JLabel directorNameTitleL;
			JLabel directorIDTitleL;
			JLabel classIDL;
			JLabel directorNameL;
			JLabel directorIDL;
			JLabel requirementTitleL;
			JLabel staffTitleL;
			JLabel staffIDL;
			JLabel staffNameL;
			JLabel statusTitleL;
			JLabel statusL;
			JLabel trainingTitleL;
			JTextArea requirementTA;
			JTextArea trainingTA;
			public JButton submitTeachingRequestBN;
			public JButton approveTeachingRequestBN;
			public JButton submitTeacherBN;
			public JButton assignTeacherBN;
			CardLayout submitButtonsLayout = new CardLayout();
			JPanel submitButtonsPanel = new JPanel(submitButtonsLayout);
			Color titleFontColor = new Color(114,114,114);
			private int statusIndex = -1;
			private List<JLabel> labelList = new ArrayList<JLabel>();
			List<JTextArea> TAList = new ArrayList<JTextArea>();
			
			
			
			
			
			public void buildClassDetailPanel() {
				classDetailPanel = new JPanel();
				classDetailPanel.setBackground(Color.WHITE);

				classDetailPanel.setLayout(null);
				
				classNameL = new JLabel("");
				classNameL.setFont(new Font("Arial", Font.PLAIN, 20 ));
				classNameL.setBounds(65, 30, 370, 62);
				classDetailPanel.add(classNameL);
				labelList.add(classNameL);
			
				classIDTitleL = new JLabel("Course ID");
				classIDTitleL.setForeground(titleFontColor);
				classIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				classIDTitleL.setBounds(65, 92, 91, 29);
				classDetailPanel.add(classIDTitleL);
				
				
				
				directorIDTitleL = new JLabel("CDID");
				directorIDTitleL.setForeground(titleFontColor);
				directorIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorIDTitleL.setBounds(184, 91, 53, 29);
				classDetailPanel.add(directorIDTitleL);
				
				directorNameTitleL = new JLabel("Director");
				directorNameTitleL.setForeground(titleFontColor);
				directorNameTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorNameTitleL.setBounds(293, 90, 116, 29);
				classDetailPanel.add(directorNameTitleL);

				
				classIDL= new JLabel();
				classIDL.setForeground(Color.BLACK);
				classIDL.setFont(new Font("Arial", Font.PLAIN, 12));
				classIDL.setBounds(65, 112, 53, 29);
				classDetailPanel.add(classIDL);
				labelList.add(classIDL);
				
				
				
				directorIDL = new JLabel();
				directorIDL.setForeground(Color.BLACK);
				directorIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorIDL.setBounds(184, 112, 97, 29);
				classDetailPanel.add(directorIDL);
				labelList.add(directorIDL);
				
				
				directorNameL = new JLabel();
				directorNameL.setForeground(Color.BLACK);
				directorNameL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorNameL.setBounds(292, 111, 82, 29);
				classDetailPanel.add(directorNameL);
				labelList.add(directorNameL);
				
				
				statusTitleL = new JLabel("Status: ");
				statusTitleL .setBounds(65, 284, 82, 29);
				statusTitleL.setForeground(titleFontColor);
				statusTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				classDetailPanel.add(statusTitleL);
				
				
				
				
				statusL = new JLabel("Teacher has not been assigned.");
				statusL .setBounds(115, 288, 400, 21);
				statusL.setForeground(Color.BLACK);
				statusL.setFont(new Font("Arial", Font.PLAIN, 14));
				classDetailPanel.add(statusL);
				labelList.add(statusL);
				
				requirementTitleL = new JLabel("Requirement");
				requirementTitleL.setForeground(titleFontColor);
				requirementTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				requirementTitleL.setBounds(65, 145, 89, 21);
				classDetailPanel.add(requirementTitleL);
				
				
				JPanel operateP = new JPanel();
				operateP.setBorder(new LineBorder(Color.LIGHT_GRAY));
				operateP.setBackground(Color.WHITE);
				operateP.setBounds(65, 312, 495, 160);
				classDetailPanel.add(operateP);
				operateP.setLayout(null);
				
				staffTitleL = new JLabel("Teacher");
				staffTitleL.setBounds(16, 13, 76, 14);
				staffTitleL.setForeground(titleFontColor);
				staffTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				operateP.add(staffTitleL);
				
				staffIDL = new JLabel();
				staffIDL.setForeground(Color.BLACK);
				staffIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				staffIDL.setBounds(16, 36, 97, 21);
				operateP.add(staffIDL);
				labelList.add(staffIDL);
				
				staffNameL = new JLabel();
				staffNameL.setForeground(Color.BLACK);
				staffNameL.setFont(new Font("Arial", Font.PLAIN, 14));
				staffNameL.setBounds(16, 58, 124, 21);
				operateP.add(staffNameL);
				labelList.add(staffNameL);
				
				
				
				trainingTitleL = new JLabel("Organized training");
				trainingTitleL.setForeground(titleFontColor);
				trainingTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				trainingTitleL.setBounds(158, 11, 147, 18);
				operateP.add(trainingTitleL);
				
				
				JScrollPane requirementSP = new JScrollPane();
				requirementSP.setBounds(65, 170, 495, 106);
				classDetailPanel.add(requirementSP);
				
				requirementTA = new JTextArea();
				requirementTA.setFont(new Font("Arial", Font.PLAIN, 14));
				requirementTA.setEditable(false);
				requirementTA.setWrapStyleWord(true);
				requirementTA.setLineWrap(true);
				requirementTA.setText("");
				requirementSP.setViewportView(requirementTA);
				TAList.add(requirementTA);
				
				JScrollPane trainingSP = new JScrollPane();
				trainingSP.setToolTipText("");
				trainingSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				trainingSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				trainingSP.setBounds(158, 36, 319, 70);
				operateP.add(trainingSP);
				trainingTA  = new JTextArea();
				trainingTA.setWrapStyleWord(true);
				trainingTA.setLineWrap(true);
				trainingTA.setEditable(false);
				trainingTA.setText("");
				trainingSP.setViewportView(trainingTA);
				TAList.add(trainingTA);
				

				submitButtonsPanel.setBounds(365, 113, 100, 30);
				
				submitTeacherBN = createBlackButton("Submit");
				submitButtonsPanel.add(submitTeacherBN, "submitTeacherBN");
				approveTeachingRequestBN = createBlackButton("Approve");
				submitButtonsPanel.add(approveTeachingRequestBN, "approveTeachingRequestBN");
				submitTeachingRequestBN = createBlackButton("Submit");
				submitButtonsPanel.add(submitTeachingRequestBN, "submitTeachingRequestBN");
				JPanel emptyP = new JPanel ();
				emptyP.setBackground(Color.WHITE);
				submitButtonsPanel.add(emptyP, "emptyP");		
				operateP.add(submitButtonsPanel);
				
				assignTeacherBN = createBlackButton("Assign");
				assignTeacherBN.setBounds(75, 13, 40, 16);
				assignTeacherBN.setFont(new Font("Arial", Font.BOLD, 8));
				assignTeacherBN.setVisible(false);
				assignTeacherBN.setEnabled(false);
				assignTeacherBN.addActionListener(View.this);
				operateP.add(assignTeacherBN);
				
			
				
				centerPanel.add(classDetailPanel, "classDetailPanel");
			}
			
			
			
			public String[] getAssignTeacher() {
				String [] s = new String [3];
				s[0] =	classIDL.getText();
				s[1] = staffIDL.getText();
				s[2] = trainingTA.getText();			
				return s;
			}
			
			

			public void updateData(String[] data) {
				centerPage.show(centerPanel, "classDetailPanel");
				int i = 1;	//start with class id #2 element
				String [] refinedData = new String[data.length];
				
				
				refinedData[0] = data[0]; //semester
				refinedData[1] = data[2]; //classname
				refinedData[2] = data[1]; //classid
				refinedData[3] = data[5]; //directorid
				refinedData[4] = data[6]; //directorname
				refinedData[5] = data[4]; //status
				refinedData[6] = data[7]; //teacherid
				refinedData[7] = data[8]; //teachername
				refinedData[8] = data[3]; //requirement
				refinedData[9] = data[9]; //training				
				
				if(refinedData[5].equals("Pending")) {
					statusIndex = 1;
					refinedData[5] = "Require the assignment of a teacher.";
				}
				else if(refinedData[5].equals("Assigned")) {
					statusIndex = 2;
					refinedData[5] = "Require the submission of the teaching request.";
				}
				else if(refinedData[5].equals("Submitted")) {
					statusIndex = 3;
					refinedData[5] = "Require the approvement of the teaching request.";
				}
				else if(refinedData[5].equals("Approved")) {
					statusIndex = 4;
					refinedData[5] = "Teaching request had been approved";
				}else {
					refinedData[5] = "Error" + refinedData[5];
				}
				
				
				System.out.println();
				
				for(JLabel l : labelList) {
					if(i<refinedData.length) {
						System.out.print(refinedData[i]);
						l.setText(refinedData[i]);
						i++;
					}
				}
				for(JTextArea l : TAList) {
					System.out.print(l.getName());
					if(i<refinedData.length) {
						System.out.print(refinedData[i]);
						System.out.print(l.getName());
						l.setText(refinedData[i]);
						i++;
					}
				}

				View.this.refresh();
			}
			
			
			
			
			
			public void displayAdminsMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
	
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "submitTeacherBN");
					
					trainingTA.setEditable(true);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(true);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(true);
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");	
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}
				
			}
			
			public void displayDCMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "submitTeachingRequestBN");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				
			}
			
			public void displayPDMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "approveTeachingRequestBN");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				
			}
			public void displayNormalMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				
			}
			
			
		}
		public class  SelectTeacherPage{
			String staffID = "";
			String staffName = "";
			public JButton selectTeacherSubmitBN;
			JLabel semesterlabel;
			public void buildSelectTeacherPanel() {
//				selectTeacherWindow= new JFrame();
//
//				selectTeacherWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				selectTeacherWindow.setVisible(true);
//				selectTeacherWindow.setBounds(100, 100, 500, 500);
				selectTeacherPanel = new JPanel();
				selectTeacherPanel.setBackground(Color.white);
//				selectTeacherPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//				selectTeacherWindow.setContentPane(contentPane);
				GridBagLayout gbl_contentPane = new GridBagLayout();
				gbl_contentPane.columnWidths = new int[]{79, 334, 0};
				gbl_contentPane.rowHeights = new int[]{31, 22, 14, 323, 23, 0};
				gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				selectTeacherPanel.setLayout(gbl_contentPane);
				
				JLabel lblNewLabel = new JLabel("Staff List");
				lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				selectTeacherPanel.add(lblNewLabel, gbc_lblNewLabel);
				
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.gridx = 1;
				gbc_panel.gridy = 2;
				selectTeacherPanel.add(panel, gbc_panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				panel.setBackground(Color.white);
				JLabel lblSemester = new JLabel("Semester: ");
				lblSemester.setFont(new Font("Arial", Font.PLAIN, 12));
				panel.add(lblSemester);
				
				semesterlabel = new JLabel("");
				semesterlabel.setFont(new Font("Arial", Font.PLAIN, 12));
				panel.add(semesterlabel);
				
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 3;
				selectTeacherPanel.add(scrollPane, gbc_scrollPane);
				
				staffListTable = buildModelListTable(null, null);	
				
				staffListTable.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int selectedRow = staffListTable.rowAtPoint(evt.getPoint());
						if (selectedRow >= 0&& selectedRow < staffListTable.getRowCount()) {
						staffID = staffListTable.getValueAt(selectedRow, 0).toString();
						staffName = staffListTable.getValueAt(selectedRow, 2).toString();
						}
					}
				});

				scrollPane.setViewportView(staffListTable);			
				selectTeacherSubmitBN = new JButton("Select");
				selectTeacherSubmitBN.setBorder(BorderFactory.createEmptyBorder(10,8,8,10));
				selectTeacherSubmitBN.setFont(new Font("Arial", Font.PLAIN, 12));
				selectTeacherSubmitBN.setBackground(new Color(56, 151, 240));
				selectTeacherSubmitBN.setForeground(Color.white);
				selectTeacherSubmitBN.addActionListener(View.this);
				
				
				
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
				gbc_btnNewButton.gridx = 1;
				gbc_btnNewButton.gridy = 4;
				selectTeacherPanel.add(selectTeacherSubmitBN, gbc_btnNewButton);
				centerPanel.add(selectTeacherPanel, "selectTeacherPanel");
			}
			
			public void displaySelectTeacherPanel(String []staffTableHeader,String [][] staffList, String semester) {
				TableModel m = new DefaultTableModel(staffList, staffTableHeader) ;
				View.this.staffListTable.setModel(m);
				semesterlabel.setText(semester);
				centerPage.show(centerPanel, "selectTeacherPanel");
				refresh();
			}
			
			public void setSelectedTeacher() {

				View.this.main.courseDetailPage.staffIDL.setText(staffID);
				View.this.main.courseDetailPage.staffNameL.setText(staffName);
				refresh();
			}
			
			
				
			
		}
	}
	
	
	public void centerRefresh() {
		View.this.centerPanel.revalidate();
		View.this.centerPanel.repaint();
	}
		
	public String encodeString(String s) {
		if(s!=null) {
			return (!s.equals("")) ? "\""+ s + "\"" : "";
		}
		return null;
	}
		
	public JTable buildModelListTable(String[] header, String [][] list) {	
		if(header==null || list == null) {
			String [] tem  =  {""};
			String [][] tem2  =  {{""}};
			header= tem;
			list =tem2;
		}
		
		JTable modelTable = new JTable(list, header){
			public boolean isCellEditable(int row, int column) {                
		           return false;    
			}
		};
			     
		modelTable.setBackground(Color.WHITE);
		modelTable.getTableHeader().setBackground(Color.WHITE);
		modelTable.getTableHeader().setReorderingAllowed(false);
		modelTable.setFillsViewportHeight(true);
		return modelTable;		
	}
	
	
	
	public void enableTableHoverEffect(JTable modelTable) {
		
		modelTable.addMouseMotionListener(new MouseMotionListener() {
			int hoveredRow = -1, hoveredColumn = -1;
			@Override
			public void mouseMoved(MouseEvent e) {
				java.awt.Point p = e.getPoint();
			    hoveredRow = modelTable.rowAtPoint(p);
			    hoveredColumn = modelTable.columnAtPoint(p);
			    if(hoveredRow<0||hoveredRow>modelTable.getRowCount()) {}
			    	else {
			    		modelTable.setRowSelectionInterval(hoveredRow, hoveredRow);
			    		modelTable.repaint();  
			        }
			     }
			@Override
			public void mouseDragged(MouseEvent e) {
				hoveredRow = hoveredColumn = -1;
			    modelTable.repaint();
			}
		});
	}
	
	
	
		
	// Course Director 
	public class Bar {
		public void addSelfListener() {
			classListBN.addActionListener(View.this);
		}
		public void buildCDBar(String ID, String name) {
			JPanel westPanel = new JPanel(new BorderLayout());
			JPanel westNorth = new JPanel();
			JPanel westCenter = new JPanel(new GridLayout(2,1));
			JPanel westSouth = new JPanel(new GridLayout(3,1,0,20));
			JPanel list = new JPanel(new GridLayout(3,1,0,5));
			
			westNorth.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
			westNorth.setBackground(blue);
			westCenter.setBackground(blue);
			westSouth.setBackground(blue);
			list.setBackground(blue);
			
			JLabel courseDirector = new JLabel("Course Dirctor");
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Class List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel = westPanel;
		}
		
		public void buildABar(String ID, String name) {
			JPanel westPanel = new JPanel(new BorderLayout());
			JPanel westNorth = new JPanel();
			JPanel westCenter = new JPanel(new GridLayout(2,1));
			JPanel westSouth = new JPanel(new GridLayout(3,1,0,20));
			JPanel list = new JPanel(new GridLayout(3,1,0,5));
			
			westNorth.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
			westNorth.setBackground(blue);
			westCenter.setBackground(blue);
			westSouth.setBackground(blue);
			list.setBackground(blue);
			
			JLabel courseDirector = new JLabel("Administrator");
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Course List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel =  westPanel;

		}
		
		
		public void buildPDBar(String ID, String name) {
			JPanel westPanel = new JPanel(new BorderLayout());
			JPanel westNorth = new JPanel();
			JPanel westCenter = new JPanel(new GridLayout(2,1));
			JPanel westSouth = new JPanel(new GridLayout(3,1,0,20));
			JPanel list = new JPanel(new GridLayout(3,1,0,5));
			
			westNorth.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
			westNorth.setBackground(blue);
			westCenter.setBackground(blue);
			westSouth.setBackground(blue);
			list.setBackground(blue);
			
			JLabel courseDirector = new JLabel("PTT Dirctor");
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Course List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel =  westPanel;
		}
	}
	
	
	private JButton createBlackButton(String name) {
		JButton btn = new JButton(name);
		btn.setBackground(new Color(20,20,20));
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		return btn;
	}
	
	
//	
//	
//	
//	public JPanel centerPanel() {
//		JPanel centerPanel = new JPanel(new BorderLayout());
//		JPanel centerTop = new JPanel(new BorderLayout());
//		centerPanel.setBackground(Color.white);
//		centerTop.setBackground(Color.white);
//		
//		createCourse = new JButton("create new course");
//		createCourse.setFocusPainted(false);
//	
//		centerTop.setBorder(BorderFactory.createEmptyBorder(20,10,10,50));
//		centerTop.add(createCourse,BorderLayout.EAST);
//		centerPanel.add(centerTop,BorderLayout.NORTH);
//		return centerPanel;
//	}
//	
//	// Create course
//	public JPanel CreateCoursePanel() {
//		JPanel center = new JPanel(new GridLayout(5,1,0,10));
//		center.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
//		JPanel buttonPanel = new JPanel(new FlowLayout());
//		
//		center.setBackground(Color.white);
//		buttonPanel.setBackground(Color.white);
//		
//		JLabel label = new JLabel("create new course", SwingConstants.CENTER);
//		Font f = new Font("TimesRoman",Font.PLAIN,23);
//		label.setFont(f);
//		
//		courseNameTF = new JTextField();
//		requirment1TF= new JTextField();
//		requirment2TF= new JTextField();
//		
//		courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, "class name"));
//		requirment1TF.addFocusListener(new JTextFieldHintListener(requirment1TF, "requirment 1"));
//		requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, "requirment 2"));
//		
//		courseNameTF.setBorder(BorderFactory.createLineBorder(blue));
//		requirment1TF.setBorder(BorderFactory.createLineBorder(blue));
//		requirment2TF.setBorder(BorderFactory.createLineBorder(blue));
//		
////		add = new JButton("add a new requirement");
//		ok = new JButton("Ok");
//		cancel = new JButton("Cancel");
//		
//		ok.setBackground(blue);
//		ok.setFocusPainted(false);
//	
//		
//		cancel.setBackground(blue);
//		cancel.setFocusPainted(false);
//
//		
//		buttonPanel.add(ok);
//		buttonPanel.add(cancel);
//		
//		center.add(label);
//		center.add(courseNameTF);
//		center.add(requirment1TF);
//		center.add(requirment2TF);
////		center.add(add);
//		center.add(buttonPanel);
//		return center;
//	}
//	
//	
	
	
	
}
