package model;

import java.util.Random;

public class Class {
	private Student[] roster;

	public Class(Student[] roster) {
		this.roster = roster;
	}

	public void printEnrollment() {
		log.Logger.info("The class has " + roster.length + " students");
	}

	public void printRandomStudentInfo() {
		Random r = new Random();
		Student randomStudent = roster[r.nextInt(roster.length)];

		log.Logger.info("Random Student " + randomStudent.getFirstName() + " " + randomStudent.getLastName()
				+ " with PID: " + randomStudent.getPID() + " and Onyen: " + randomStudent.getOnyen());
	}

	public boolean addAttendance(int dayNumber, String onyen, String fullName, int PID, int attendanceStatus) {
		for (Student s : roster) {
			if (s.getOnyen().equals(onyen) || s.getPID() == PID) {
				s.addAttendance(dayNumber, attendanceStatus);
				return true;
			}
		}
		
		// worst case check name
//		for (Student s: roster) {
//			if (cleaning.NameTools.compare(fullName, s)) {
//				s.addAttendance(dayNumber, attendanceStatus);
//				return true;
//			}
//		}
	
		return false;
	}

	public void printDailyMisses() {
		for (int i = 0; i < settings.AlgorithmSettings.DAYS_OF_CLASS; i++) {
			int absences = 0;
			int misses = 0;
			int matches = 0;
			for (Student s : roster) {
				switch (s.getAttendanceOnDay(i)) {
				case 0:
					absences++;
					break;
				case 1:
					misses++;
					break;
				case 2:
					matches++;
					break;
				}
			}

			log.Logger.dayStats(i, absences, misses, matches);

		}
	}

	public boolean addExcuse(String name, String onyen, int PID) {
		for (Student s : roster) {
			if (s.getOnyen().equals(onyen) || PID == s.getPID() || cleaning.NameTools.compare(name, s)) {
				s.addExcuse();
				return true;
			}
		}
		return false;
	}

	public void printExcuses() {
		for (Student s : roster) {
			if (s.getDaysExcused() > 0) {
				log.Logger.info(cleaning.NameTools.simpleName(s) + " has " + s.getDaysExcused() + " excuses");
			}
		}
	}

	public Student[] getStudents() {
		return roster;
	}

	public int attendanceOnDay(int day, int type) {
		int attended = 0;
		for (Student s : roster) {
			if (s.getAttendanceOnDay(day) == type) {
				attended++;
			}
		}
		return attended;
	}
}
