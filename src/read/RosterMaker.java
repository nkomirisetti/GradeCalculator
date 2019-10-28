package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Student;

public class RosterMaker {
	public static model.Class buildRoster() {
		Scanner rScan;
		try {
			rScan = new Scanner(new File(settings.AlgorithmSettings.ROSTER_LOCATION));
			rScan.useDelimiter(",|\\n");
			
		} catch (FileNotFoundException e) {
			log.Logger.error("Roster spreadsheet could not be found");
			e.printStackTrace();
			return null;
		}

		rScan.next(); // skips over column headings
		rScan.next();
		rScan.next();

		List<Student> studentsList = new ArrayList<>();

		while (rScan.hasNext()) {
			// raw inputs
			String onyen = rScan.next();
			String stringPID = rScan.next();
			String lastName = rScan.next();
			String firstName = rScan.next();

			// formatted and cleaned
			firstName = cleaning.NameTools.clean(firstName);
			lastName = cleaning.NameTools.clean(lastName);
			int intPID = cleaning.PIDTools.clean(stringPID);
			onyen = cleaning.OnyenTools.clean(onyen);

			Student s = new Student(firstName, lastName, onyen, intPID);
			studentsList.add(s);
		}
		rScan.close();
		
		Student[] studentsArray = studentsList.toArray(new Student[0]);
		
		return new model.Class(studentsArray);
	}
}
