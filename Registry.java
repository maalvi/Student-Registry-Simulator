import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Registry {
	private TreeMap<String, Student> students = new TreeMap<String, Student>();
	private TreeMap<String, ActiveCourse> courses = new TreeMap<String, ActiveCourse>();

	public Registry() throws FileNotFoundException {
		
		// Reading the students from a file:
		String sName, sId;
		File listOfStudents = new File("Assignment2\\students.txt");
		Scanner studentScanner = null;

		studentScanner = new Scanner(listOfStudents);

		while (studentScanner.hasNext()) {
			sName = studentScanner.next();
			sId = studentScanner.next();
			students.put(sId, new Student(sName, sId));
		}

		studentScanner.close();

		// Masterlist of students used in initialization
		ArrayList<Student> masterList = new ArrayList<Student>();
		
		for (String Id : students.keySet()) {
			masterList.add(students.get(Id));
		}
		
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(masterList.get(1));
		list.add(masterList.get(2));
		list.add(masterList.get(3));
		
		// Add some active courses with students
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		
		// Add course to student list of credit courses
		for (Student student : list) {
			student.addCourse(courseName, courseCode, descr, format, "W2020", 0);
		}


		// CPS511
		list.clear();
		list.add(masterList.get(0));
		list.add(masterList.get(4));
		list.add(masterList.get(5));
		
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "F2020", list));
		
		// Add course to student list of credit courses
		for (Student student : list) {
			student.addCourse(courseName, courseCode, descr, format, "W2020", 0);
		}

		
		// CPS643
		list.clear();
		list.add(masterList.get(0));
		list.add(masterList.get(1));
		list.add(masterList.get(3));
		list.add(masterList.get(5));
		
		courseName = "Virtual Reality";
		courseCode = "CPS643";
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";

		courses.put(courseCode, new ActiveCourse(courseName, courseCode, descr, format, "W2020", list));
		
		// Add course to student list of credit courses
		for (Student student : list) {
			student.addCourse(courseName, courseCode, descr, format, "W2020", 0);
		}

	}

	public boolean addNewStudent(String name, String id) {
		if (findStudent(id) != null)
			return false;

		students.put(id, new Student(name, id));
		return true;
	}

	public boolean removeStudent(String studentId) {
		for (String id : students.keySet()) {
			if (id.equals(studentId)) {
				students.remove(id);
				return true;
			}
		}
		return false;
	}

	public void printAllStudents() {
		for (String id : students.keySet()) {
			System.out.println("ID: " + id + "Name: " + students.get(id));			
		}
	}

	private Student findStudent(String id) {
		for (String sId : students.keySet()) {
			if (sId.equals(id)) {
				return students.get(sId);
			}
		}
		return null;
	}

	private ActiveCourse findCourse(String code) {
		for (String cCode : courses.keySet()) {
			if (cCode.equals(code)) {
				return courses.get(cCode);
			}
		}
		return null;
	}

	public void addCourse(String studentId, String courseCode) {
		Student s = findStudent(studentId);
		if (s == null)
			return;

		if (s.takenCourse(courseCode))
			return;

		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		if (ac.enrolled(studentId))
			return;

		ac.students.add(s);
		s.addCourse(ac.getName(), ac.getCode(), ac.getCourseDescription(), ac.getFormat(), ac.getSemester(), 0);
	}

	public void dropCourse(String studentId, String courseCode) {
		Student s = findStudent(studentId);
		if (s == null)
			return;

		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		ac.remove(studentId);
		s.removeActiveCourse(courseCode);
	}

	public void printActiveCourses() {
		for (String code : students.keySet()) {
			System.out.println(courses.get(code).getDescription());			
		}
	}

	public void printClassList(String courseCode) {
		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		ac.printClassList();
	}

	public void sortCourseByName(String courseCode) {
		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		ac.sortByName();
	}

	public void sortCourseById(String courseCode) {
		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		ac.sortById();
	}

	public void printGrades(String courseCode) {
		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;

		ac.printGrades();
	}

	public void printStudentCourses(String studentId) {
		Student s = findStudent(studentId);
		if (s == null)
			return;

		s.printActiveCourses();
	}

	public void printStudentTranscript(String studentId) {
		Student s = findStudent(studentId);
		if (s == null)
			return;

		s.printTranscript();
	}

	public void setFinalGrade(String courseCode, String studentId, double grade) {
		Student s = findStudent(studentId);
		if (s == null)
			return;
		s.setGrade(courseCode, grade);

		ActiveCourse ac = findCourse(courseCode);
		if (ac == null)
			return;
		ac.remove(studentId);
	}
}
