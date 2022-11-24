package midtermCST8288;

import java.util.LinkedList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentManager {
	private Random rand;
	private ObservableList<Student> students;
	
	public StudentManager() {
		rand = new Random();
		students = FXCollections.observableList(new LinkedList<>());
	}
	public ObservableList<Student> getStudents(){
		return students;
	}
	public void addStudent(String fName, String lName) {
		students.add(new Student(fName, lName, rand.nextLong()));
	}
}

