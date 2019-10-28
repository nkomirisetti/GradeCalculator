package model;

public class Student {
	int[] daysAttended; // this array stores 0 if student didn't attend, 1 if they put in the wrong
						// code, and 2 if they attended w the right code
	private int daysExcused, PID;
	private String firstName, lastName, onyen;

	public Student(String firstName, String lastName, String onyen, int PID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.onyen = onyen;
		this.PID = PID;

		daysAttended = new int[settings.AlgorithmSettings.DAYS_OF_CLASS];
		daysExcused = 0;
	}

	public int addExcuse() {
		daysExcused++;
		return daysExcused;
	}

	public void addAttendance(int dayNumber, int attendanceStatus) {
		if (daysAttended[dayNumber] == 2) {
			return;
		} else {
			daysAttended[dayNumber] = attendanceStatus;
		}
	}

	// GETTERS AND SETTERS
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getOnyen() {
		return onyen;
	}

	public int getDaysExcused() {
		return daysExcused;
	}

	public int getPID() {
		return PID;
	}

	public int getAttendanceOnDay(int day) {
		return daysAttended[day];
	}

	public int getTotalAttendance() {
		int attendance = daysExcused;
		for (int i : daysAttended) {
			if (i == 2) {
				attendance++;
			}
		}
		if (settings.AlgorithmSettings.DAYS_OF_CLASS < attendance) {
			return settings.AlgorithmSettings.DAYS_OF_CLASS;
		}
		return attendance;
	}

	public double getPercentage() {
		return (double) getTotalAttendance() / (double) settings.AlgorithmSettings.DAYS_OF_CLASS;
	}
}
