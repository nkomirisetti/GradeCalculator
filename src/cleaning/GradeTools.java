package cleaning;

import model.Student;

public class GradeTools {

	public static int integerCurve(Student s) {
		int total = s.getTotalAttendance();
		total += settings.AlgorithmSettings.DAYS_TO_DROP;
		if (total > settings.AlgorithmSettings.DAYS_OF_CLASS) {
			return settings.AlgorithmSettings.DAYS_OF_CLASS;
		} else {
			return total;
		}
	}

	public static double percentageCurve(Student s) {
		return ((double)integerCurve(s) / (double)settings.AlgorithmSettings.DAYS_OF_CLASS);
	}

}
