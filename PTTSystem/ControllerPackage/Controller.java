package ControllerPackage;
import ViewPackage.View;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModelPackage.Model;
public class Controller implements ActionListener{
	protected View view;
	protected Model model;
	protected LoginController logC;

	
	public Controller(Model model, View view, LoginController logC) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.view = view;
		this.logC = logC;
		selectSemesterPage();
		
	}
	
	public void selectSemesterPage() {
		view.semester.buildSemesterPanel(model.getlatestSem());
		view.semesterBN.addActionListener(this);
		view.semester.displayLatestSemester();
		view.semester.displaySemesterPanel();
	}
	

	public void initialisePage() {

		
		//setup all available pages
		view.main.courseDetailPage.buildClassDetailPanel();
		view.main.buildClassListPanel(model.getClassListTableHeader(), model.getClassListTable());

		view.classListTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow = view.classListTable.rowAtPoint(evt.getPoint());
		//		    int selectedCol = view.classListTable.columnAtPoint(evt.getPoint());
				    if (selectedRow >= 0) {
				       String classId = view.classListTable.getValueAt(selectedRow, 1).toString();
				       System.out.println(selectedRow);
				       selectedCourseStage(classId);

				    }
				}
		});
		
		view.main.courseDetailPage.normalPageBBN.addActionListener(this);
		view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
	}
	
	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayNormalMode(model.getClass(classId));
	}
	
	public void displayCourseListPage() {
		view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
		
	}
	
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.semesterBN) {
			int semester = view.semester.getSelecetedSemester();
			if(model.selectSemester(semester)) {
				this.initialisePage();
			}else {
				view.semester.displayLatestSemester();
			}
		}
		
		if(e.getSource() == view.classListBN) {
			displayCourseListPage();
		}
		
		if(e.getSource() == view.main.courseDetailPage.normalPageBBN) {
			displayCourseListPage();
		}
	
	}

	
	
}
