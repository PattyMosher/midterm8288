package midtermCST8288;

public class Student {
	private long ID;
	private String fName;
	private String lName;

	public Student(String fName, String lName, long ID) {
		this.fName = fName;
		this.lName = lName;
		this.ID = ID;
	}
	public void updateName(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}
	public String getFName() { return fName; }
	public String getLName() { return lName; }
	public long getID() { return ID; }
}
