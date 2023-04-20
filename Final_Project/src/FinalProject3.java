/*
- COP 3330 Final Project
- Alaa Aljundi,Jessenia Argueta (Section 0V03),Lakshmi (Section 0002)
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//------------------------------------------------------------------------------------------------------
// Lab Class
class Lab {
	// Fields
	private int crn;
	private String roomNumber;

	// Constructor
	public Lab(int crn, String roomNumber) {
		this.crn = crn;
		this.roomNumber = roomNumber;
	}

	// Getters
	public int getCRN() {
		return crn;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	@Override
	public String toString() {
		return String.format("%d, %s", crn, roomNumber);
	}
}

//------------------------------------------------------------------------------------------------------
// Lecture Class
class Lecture {
	// Fields
	private int crn;
	private String prefix;
	private String title;
	private String type;
	private String buildingCode;
	private String roomNumber;
	private boolean isOnline;
	private boolean hasLabs;
	private List<Lab> labs;

	// Constructor
	// Lecture Is Online Online
	public Lecture(int crn, String prefix, String title, String type) {
		this(crn, prefix, title, type, null, null, false);
		this.isOnline = true;
	}

	// Master Constructor
	public Lecture(int crn, String prefix, String title, String type, String buildingCode, String roomNumber,
			boolean hasLabs) {
		this.crn = crn;
		this.prefix = prefix;
		this.title = title;
		this.type = type;
		this.buildingCode = buildingCode;
		this.roomNumber = roomNumber;
		this.hasLabs = hasLabs;
		// If Lecture Has Labs
		if (this.hasLabs) {
			labs = new ArrayList<Lab>();
		}
	}

	// Add A Lab
	public void addLab(Lab lab) {
		labs.add(lab);
	}

	// Returns If The Lecture Is Online
	public boolean isOnline() {
		return isOnline;
	}

	// Returns If Lecture Has Labs
	public boolean hasLabs() {
		return hasLabs;
	}

	// Returns Room Number
	public String getRoomNumber() {
		return roomNumber;
	}

	// Returns All Labs
	public List<Lab> getLabs() {
		return labs;
	}

	// Returns CRN
	public int getCRN() {
		return crn;
	}

	public String getTitle() {
		return title;
	}

	public String getPrefix() {
		return prefix;
	}

	// To String
	@Override
	public String toString() {
		if (isOnline()) {
			return String.format("%d, %s, %s, %s, Online", crn, prefix, title, type);
		} else {
			return String.format("%d, %s, %s, %s, %s, %s, %s", crn, prefix, title, type, buildingCode, roomNumber,
					hasLabs() ? "Yes" : "No");
		}
	}
}

//------------------------------------------------------------------------------------------------------
// Person Abstract Class
abstract class Person {
	private Integer ucfId;
	private String name;

	public Person() {
	}

	public Person(Integer ucfId, String name) {
		this.ucfId = ucfId;
		this.name = name;
	}

	public Integer getUcfId() {
		return ucfId;
	}

	public void setUcfId(Integer ucfId) {
		this.ucfId = ucfId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

//------------------------------------------------------------------------------------------------------
//Faculty
class Faculty extends Person {

	private String rank;
	private String officeLocation;
	private ArrayList<Lecture> lecturesTaught = new ArrayList<Lecture>();

	public Faculty() {
		super();
	}

	public Faculty(int ucfId, String name, String rank, String officeLocation, ArrayList<Lecture> lecturesTaught) {
		super(ucfId, name); // call super class parameterized constructor
		this.rank = rank;
		this.officeLocation = officeLocation;
		this.lecturesTaught = lecturesTaught;
	}

	public Faculty(String name) {
		setName(name);
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public ArrayList<Lecture> getLecturesTaught() {
		return lecturesTaught;
	}

	public void setLecturesTaught(ArrayList<Lecture> lecturesTaught) {
		this.lecturesTaught = lecturesTaught;
	}

	public void addLecture(Lecture lecture) {
		this.lecturesTaught.add(lecture);
	}
}

//------------------------------------------------------------------------------------------------------
//Student
class Student extends Person {
	protected boolean graduate;
	protected ArrayList<Lecture> lecturesTaken;

	public Student() {
		super();
		this.lecturesTaken = new ArrayList<Lecture>();
	}

	public Student(int ucfId, String name, ArrayList<Lecture> lecturesTaken) {
		super(ucfId, name); // call super class parameterized constructor..
		this.lecturesTaken = lecturesTaken;
	}

	public Student(int ucfId, String name, boolean graduate, ArrayList<Lecture> lecturesTaken) {
		super(ucfId, name); // call super class parameterized constructor..
		this.graduate = graduate;
		this.lecturesTaken = lecturesTaken;
	}

	public boolean isGraduate() {
		return graduate;
	}

	public void setGraduate(boolean graduate) {
		this.graduate = graduate;
	}

	public ArrayList<Lecture> getLecturesTaken() {
		return lecturesTaken;
	}

	public void setLecturesTaken(ArrayList<Lecture> lecturesTaken) {
		this.lecturesTaken = lecturesTaken;
	}

	public void addLecture(Lecture lecture) {
		this.lecturesTaken.add(lecture);
	}
}

//------------------------------------------------------------------------------------------------------
//TeachingAssistant
class TeachingAssistant extends Student {
	private HashMap<Integer, ArrayList<Lab>> labsSupervised = new LinkedHashMap<>();
	private Faculty advisor;
	private String expectedDegree;

	public TeachingAssistant() {
		super();
	}

	public TeachingAssistant(HashMap<Integer, ArrayList<Lab>> labsSupervised, String expectedDegree) {
		super();
		this.labsSupervised = labsSupervised;
		this.expectedDegree = expectedDegree;
	}

	public TeachingAssistant(HashMap<Integer, ArrayList<Lab>> labsSupervised, Faculty advisor, String expectedDegree) {
		super();
		this.labsSupervised = labsSupervised;
		this.advisor = advisor;
		this.expectedDegree = expectedDegree;
	}

	public HashMap<Integer, ArrayList<Lab>> getLabsSupervised() {
		return labsSupervised;
	}

	public void setLabsSupervised(HashMap<Integer, ArrayList<Lab>> labsSupervised) {
		this.labsSupervised = labsSupervised;
	}

	public Faculty getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Faculty advisor) {
		this.advisor = advisor;
	}

	public String getExpectedDegree() {
		return expectedDegree;
	}

	public void setExpectedDegree(String expectedDegree) {
		this.expectedDegree = expectedDegree;
	}

	public void addLab(int crn, Lab lab) {

		if (labsSupervised.containsKey(crn)) {

			this.labsSupervised.get(crn).add(lab);
		} else {

			ArrayList<Lab> labs = new ArrayList<Lab>();
			labs.add(lab);
			this.labsSupervised.put(crn, labs);
		}
	}
}

//------------------------------------------------------------------------------------------------------
// IdException
class IdException extends Throwable {
	public IdException(String message) {
		super(message);
	}
}

//------------------------------------------------------------------------------------------------------
//Schedule
class Schedule {

	Map<Integer, Lecture> lecturesMap = null;
	Map<Integer, Person> personsMap = new HashMap<Integer, Person>();
	Scanner sc = new Scanner(System.in);


	//Start of Methods
	//----------------------------------------------------------------------------
	public void addFacultyToSchedule() { //Adding Faculty to Schedule Method

		int ucfId = 0;
		String name = null;
		String officeLocation = null;
		int lectureCount = 0;
		TeachingAssistant ta = null;
		Faculty faculty = null;
		String rank = null;

		System.out.print("\nEnter UCF faculty ID: ");
		ucfId = sc.nextInt();
		validateUcfId(ucfId);

		if (personsMap.containsKey(ucfId)) {
			faculty = (Faculty) personsMap.get(ucfId);
		} else {
			faculty = new Faculty();
			System.out.print("\nEnter name: ");

			sc.nextLine();
			name = sc.nextLine();

			System.out.print("\nEnter rank: ");
			rank = sc.next();
			System.out.print("\nEnter office location: ");
			officeLocation = sc.next();
			faculty.setUcfId(ucfId);
			faculty.setName(name);
			faculty.setRank(rank);
			faculty.setOfficeLocation(officeLocation);
		}

		System.out.print("\nEnter how many lectures: ");

		lectureCount = sc.nextInt();

		System.out.print("\nEnter the crns of the lectures: ");
		sc.nextLine();
		String str = sc.nextLine();

		
		String crns[] = str.split(" ");
		for (String c : crns) {

			if (lecturesMap.containsKey(Integer.parseInt(c))) {
				Lecture l = lecturesMap.get(Integer.parseInt(c));

				faculty.addLecture(l);

				
				if (l.hasLabs()) {

					System.out
							.println("\n[" + l.getCRN() + "/" + l.getPrefix() + "/" + l.getTitle() + "] has these labs:");

					for (Lab lb : l.getLabs())
						System.out.println(lb.getCRN() + "," + lb.getRoomNumber());

					for (Lab lb1 : l.getLabs()) {

						System.out.print("\nEnter the TA UCF ID for " + lb1.getCRN() + ": ");
						ucfId = sc.nextInt();

						if (!personsMap.containsKey(ucfId)) {

							System.out.print("\nName of TA: ");
							sc.nextLine();
							name = sc.nextLine();

							System.out.print("\nName of TA supervisor: ");
							String advisor = sc.nextLine();

							System.out.print("\nDegree Seeking: ");
							String expectedDegree = sc.next();

							ta = new TeachingAssistant();
							ta.setUcfId(ucfId);
							ta.setName(name);
							ta.setExpectedDegree(expectedDegree);
							ta.setAdvisor(new Faculty(advisor));

							personsMap.put(ta.getUcfId(), ta);
							

						} else {
							TeachingAssistant ta1 = (TeachingAssistant) personsMap.get(ucfId);
							System.out.println("\nTA found as a student: " + ta1.getName());
						}
						ta.addLab(Integer.parseInt(c), lb1);

					} // for lab

				} // if
				personsMap.put(faculty.getUcfId(), faculty);

			System.out.println("\n[" + l.getCRN() + "/" + l.getPrefix() + "/" + l.getTitle() + "] Added!!");
			} // for crn
			else
				System.out.println("CRN [" + c + "] Not found");
		} // for

	} // addFacultyToSchedule

	//----------------------------------------------------------------------------
	public void enrollStudentToLecture() { //Enrolls Students to Lectures Method
		int ucfId = 0;
		int crn;

		System.out.print("\nEnter UCF student ID: ");
		ucfId = sc.nextInt();
		validateUcfId(ucfId);

		if (personsMap.containsKey(ucfId)) {
			Person person = personsMap.get(ucfId);
			Student student = (Student) person;
			System.out.println("Record found/Name: " + student.getName());
			System.out.printf("Which lecture to enroll [" + student.getName() + "] in? Enter crn of the lecture: ");
			crn = sc.nextInt();

			if (lecturesMap.containsKey(crn)) {

				Lecture lecture = lecturesMap.get(crn);

				if (lecture.hasLabs()) {
					System.out.println("[" + lecture.getPrefix() + "/" + lecture.getTitle() + "] has these labs");
					List<Lab> labs = lecture.getLabs();

					for (Lab lab : labs)
						System.out.println(lab.getCRN() + ", " + lab.getRoomNumber());

					System.out.printf("\n[" + student.getName() + "] has been added to the lab."
							+ labs.get(new Random().nextInt(labs.size())).getCRN());

				} else
					System.out.println("[" + lecture.getPrefix() + "/" + lecture.getTitle() + "] has no labs");

				student.addLecture(lecture);

				System.out.println("\nStudent enrolled!");

			} else
				System.out.println("CRN  [" + crn + "] not found");

		} else
			System.out.println("UCF ID [" + ucfId + "] not found");

	}// enrollStudentToLecture

	//----------------------------------------------------------------------------
	public void printScheduleOfFaculty(int ucfId) { //Prints faculty schedule method

		validateUcfId(ucfId);
		if (personsMap.containsKey(ucfId)) {
			Person person = personsMap.get(ucfId);
			Faculty faculty = (Faculty) person;

			System.out.println(faculty.getName() + " is teaching the following lectures:\n");

			for (Lecture lecture : faculty.getLecturesTaught()) {
				if (lecture.isOnline())
					System.out.println("[" + lecture.getCRN() + "/" + lecture.getPrefix() + "/" + lecture.getTitle()
							+ "][Online]");

				else if (lecture.hasLabs()) {
					System.out.println("[" + lecture.getCRN() + "/" + lecture.getPrefix() + "/" + lecture.getTitle()
							+ "] with Labs:");
					for (Lab l : lecture.getLabs())
						System.out.println("[" + l.getCRN() + "/" + l.getRoomNumber() + "]");

				} else
					System.out.println(
							"[" + lecture.getCRN() + "/" + lecture.getPrefix() + "/" + lecture.getTitle() + "]");
			}
		} else
			System.out.println("UCF ID [" + ucfId + "] not found");
	}// printScheduleOfFaculty

	//----------------------------------------------------------------------------
	public void printScheduleOfStudent(int ucfId) { //Prints student schedule method

		validateUcfId(ucfId);

		if (personsMap.containsKey(ucfId)) {
			Person person = personsMap.get(ucfId);
			Student student = (Student) person;

			System.out.println("");
			System.out.println("Record Found: ");
			System.out.println("        " + student.getName());
			System.out.println("        Enrolled for following Lectures\n");
			for (Lecture lecture : student.getLecturesTaken()) {
				if (lecture.hasLabs()) {
					List<Lab> labs = lecture.getLabs();

					System.out.println("[" + lecture.getPrefix() + "/" + lecture.getTitle() + "]/[Lab: "
							+ labs.get(new Random().nextInt(labs.size())).getCRN() + "]");
				} else
					System.out.println("[" + lecture.getPrefix() + "/" + lecture.getTitle() + "]/ [No Labs]");

			}

		} else
			System.out.println("UCF ID [" + ucfId + "] not found");

	}// printScheduleOfStudent

	//----------------------------------------------------------------------------
	public void printScheduleOfTeachingAssistant(int ucfId) { //Prints TA schedule method

		validateUcfId(ucfId);
		if (personsMap.containsKey(ucfId)) {
			Person person = personsMap.get(ucfId);
			TeachingAssistant ta = (TeachingAssistant) person;

			System.out.println(ta.getName() + " supervised below labs:\n");

			Map<Integer, ArrayList<Lab>> map = ta.getLabsSupervised();
			Set<Integer> keySet = map.keySet();

			for (Integer key : keySet) {

				for (Lab l : map.get(key))
					System.out.println("[" + l.getCRN() + "," + l.getRoomNumber() + "]");
			}

		} else
			System.out.println("\nSorry, no TA found.");
	}//printScheduleOfTeachingAssistant

	//----------------------------------------------------------------------------
	public void deleteLecture(int crn) { //Deletes lectures method

		if (lecturesMap.containsKey(crn)) {
			Lecture lecture = lecturesMap.get(crn);
			lecturesMap.remove(crn);
			System.out.println(
					"[" + lecture.getCRN() + "/" + lecture.getPrefix() + "/" + lecture.getTitle() + "] Deleted");

			updateFile(lecturesMap.values());

			Collection<Person> persons = personsMap.values();

			for (Person p : persons) {

				if (p instanceof Faculty) {

					Faculty f = (Faculty) p;

					ArrayList<Lecture> lecturesTaught = f.getLecturesTaught();

					for (int i = 0; i < lecturesTaught.size(); i++) {
						Lecture l = lecturesTaught.get(i);
						if (l.getCRN() == crn)
							lecturesTaught.remove(i);
					}

					f.setLecturesTaught(lecturesTaught);
					personsMap.put(f.getUcfId(), f);

				} // Faculty

				if (p instanceof Student) {

					TeachingAssistant s = (TeachingAssistant) p;

					ArrayList<Lecture> lecturesTaken = s.getLecturesTaken();

					for (int i = 0; i < lecturesTaken.size(); i++) {
						Lecture l = lecturesTaken.get(i);
						if (l.getCRN() == crn)
							lecturesTaken.remove(i);
					}

					s.setLecturesTaken(lecturesTaken);
					personsMap.put(s.getUcfId(), s);
				}

				if (p instanceof TeachingAssistant) {

					TeachingAssistant s = (TeachingAssistant) p;

					s.getLabsSupervised().remove(crn);
				}

			} // for

		} else
			System.out.println("Lecture with crn [" + crn + "] not found");

	}// deleteLecture

	public void exit(String filePath) {
		System.out.println("You have made a deletion of at least one lecture.\n");
		System.out.println("Would you like to print the copy of lec.txt?");

		while (true) {
			System.out.print("\nEnter y/Y for Yes or n/N for No: ");

			String input = sc.next();
			if (input.equals("y") || input.equals("Y")) {
				displayLecturesFromFile(filePath);
				return;
			} else if (input.equals("n") || input.equals("N")) {
				System.out.println("Bye!");
				return;

			} else {
				System.out.print("\nIs that a yes or no?");
				continue;
			}
		}//while loop
	} //exit method

	// read Lectures from lec.txt
	public Map<Integer, Lecture> readLectures(File inputFile) {
	lecturesMap = new LinkedHashMap<Integer, Lecture>();

		// ArrayList To Hold Lecture
		ArrayList<Lecture> lectures = new ArrayList<Lecture>();

		boolean readLabs = false;
		// Open The File For Reading
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			// Read File Line By Line
			while ((line = br.readLine()) != null) {
				// Split The Line
				String[] fields = line.trim().split(",");

				if (readLabs) {
					if (fields.length != 2) {
						readLabs = false;
					} else {
						// Get Lab CRN and Room Number
						int crn = Integer.parseInt(fields[0].trim());
						String roomNumber = fields[1].trim();
						// Add Lab To Last Lecture
						lectures.get(lectures.size() - 1).addLab(new Lab(crn, roomNumber));
						continue;
					}
				}
				// Get CRN, Prefix, Title, Type
				int crn = Integer.parseInt(fields[0].trim());
				String prefix = fields[1].trim();
				String title = fields[2].trim();
				String type = fields[3].trim();
				// Check If Lecture is Online
				if (fields[4].trim().equalsIgnoreCase("ONLINE")) {
					// Create A New Online Lecture

					lectures.add(new Lecture(crn, prefix, title, type));

				} else {
					// Lecture is Not Online
					// Get Building Number

					String buildingCode = fields[4].trim();
					String roomNumber = fields[5].trim();
					boolean hasLabs = false;

					// Check If Lecture Has Labs
					if (fields[6].trim().equalsIgnoreCase("YES")) {
						// Has Labs
						hasLabs = true;
						readLabs = true;
					}
					// Add Lecture
					lectures.add(new Lecture(crn, prefix, title, type, buildingCode, roomNumber, hasLabs));
				}

			}

			for (Lecture lecture : lectures)
				lecturesMap.put(lecture.getCRN(), lecture);

		} catch (IOException | IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}

		return lecturesMap;

	}// readLectures

	// validate ucfId
	private void validateUcfId(Integer ucfId) {
		try {
			if (ucfId.toString().length() != 7)
				throw new IdException(">>>>>>>>>>>>>>>> Sorry incorrect format. (Id's are 7 digits)");
		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}

	}

	// update File
	public void updateFile(Collection<Lecture> lectures) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("lec.txt");

			for (Lecture lecture : lectures) {
				writer.write(lecture.toString() + "\n");
				if (lecture.hasLabs()) {
					for (Lab l : lecture.getLabs())
						writer.write(l.getCRN() + "," + l.getRoomNumber() + "\n");
				}

			}

			writer.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	// Display lectures from file
	public void displayLecturesFromFile(String filePath) {

		Collection<Lecture> lectures = readLectures(new File(filePath)).values();

		System.out.println("\n\n Lectures From File");
		System.out.println("---------------------------------------");
		for (Lecture lecture : lectures) {
			System.out.println(lecture);
			if (lecture.hasLabs())
				for (Lab lab : lecture.getLabs())
					System.out.println(lab);

		}

	}

}

// Final Project
public class FinalProject3 {

	// Main
	public static void main(String[] args) {

		Schedule schedule = new Schedule();
		Map<Integer, Person> personsMap = new HashMap<Integer, Person>();

		File file = null;
	    String filePath=null;
		Scanner sc =null;
		int choice = 0;
	  
		
	try {

		// Scanner For User Input
	    sc= new Scanner(System.in);

	

		System.out.print("Enter the absolute path of the file: ");
		

		while (true) {
			filePath = sc.next();
			file = new File(filePath);

			if (file.exists()) {
				schedule.readLectures(file);
				System.out.println("\nFile Found! Let us proceed.");

				while (choice < 8) {

					System.out.println("\n\n\n*****************************************");
					System.out.println("\nChoose one of these options:\n");
					System.out.println("1- Add a new Faculty to the schedule");
					System.out.println("2- Enroll a Student to a Lecture");
					System.out.println("3- Print the schedule of a Faculty");
					System.out.println("4- Print the schedule of an TA");
					System.out.println("5- Print the schedule of a Student");
					System.out.println("6- Delete a Lecture");
					System.out.println("7- Exit");

					System.out.println("");

					System.out.print("Enter your choice: ");
					choice = sc.nextInt();

					switch (choice) {
					case 1:

						schedule.addFacultyToSchedule();

						break;

					case 2:

						schedule.enrollStudentToLecture();

						break;

					case 3:
						System.out.print("Enter UCF ID: ");
						schedule.printScheduleOfFaculty(sc.nextInt());

						break;
					case 4:
						System.out.print("\nEnter the TA UCF ID: ");
						schedule.printScheduleOfTeachingAssistant(sc.nextInt());

						break;

					case 5:
						System.out.print("Enter UCF ID: ");
						schedule.printScheduleOfStudent(sc.nextInt());
						break;

					case 6:
						System.out.print("Enter the crn of the lecture to delete: ");
						schedule.deleteLecture(sc.nextInt());

						break;
					case 7:
						schedule.exit(filePath);
						System.exit(0);
						break;

					default:
						return;
					}// switch

				} // while

			} else {
				System.out.println("\nSorry no such file.");
				System.out.print("\nTry again: ");
				continue;
			} // else
		} // while
		
	}
	catch (Exception e) {
		System.out.println(e);
	}
	}// main
}// FinalProject