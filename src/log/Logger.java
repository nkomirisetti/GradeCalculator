package log;

public class Logger {
	public static void error(String error) {
		System.out.println("ERROR: " + error);
	}

	public static void info(String info) {
		System.out.println("INFO: " + info);
	}

	public static void missingStudent(int i, boolean verified, String fullName, int intPID, String onyen) {
		System.out.println("MISSING STUDENT: There is a missing student on week " + i + " with a " + verified
				+ " code.  Name: " + fullName + " Onyen: " + onyen + " PID: " + intPID);
	}

	public static void dayStats(int i, int absences, int misses, int matches) {
		System.out.println("DAY STATS: On day " + i + ", there are " + matches + " matches, " + misses
				+ " incorrect codes, and " + absences + " absences");
	}

	public static void missingExcuse(String onyen, String stringPID, String fullName) {
		System.out.println("MISSING EXCUSE: " + fullName + " with Onyen: " + onyen + " and PID: " + stringPID
				+ " was unable to be excused properly");
	}
}
