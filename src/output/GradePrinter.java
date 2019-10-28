package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import model.Class;
import model.Student;

public class GradePrinter {
	public static void printToSpreadsheet(model.Class input) {
		File output = new File("spreadsheets/summary.csv");
		try {
			output.createNewFile();

		} catch (IOException e) {
			output.delete();
			try {
				output.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			log.Logger.error("Print writer failed");
			return;
		}

		switch (settings.AlgorithmSettings.OUTPUT) {
		case BASIC:
			basicPrint(writer, input);
			break;
		case PERCENTAGE:
			percentagePrint(writer, input);
			break;
		case FULL:
			fullPrint(writer, input);
			break;
		case SAKAI:
			sakaiPrint(writer, input);
		default:
			break;
		}

		writer.close();
	}

	private static void basicPrint(PrintWriter pw, Class input) {
		writeLine(pw, "Student ID");
		writeLine(pw, "PID");
		writeLine(pw, "Name");
		writeLine(pw, "Attendanded Days");
		newLine(pw);

		for (Student s : input.getStudents()) {
			writeLine(pw, s.getOnyen());
			writeLine(pw, s.getPID());
			writeLine(pw, cleaning.NameTools.simpleName(s));
			writeLine(pw, cleaning.GradeTools.integerCurve(s));
			newLine(pw);
		}
	}

	private static void sakaiPrint(PrintWriter pw, Class input) {
		writeLine(pw, "Student ID");
		writeLine(pw, "PID");
		writeLine(pw, "Student Name");
		newLine(pw, "Days Attended[" + settings.AlgorithmSettings.DAYS_OF_CLASS + "]");

		for (Student s : input.getStudents()) {
			writeLine(pw, s.getOnyen());
			writeLine(pw, s.getPID());
			writeLine(pw, cleaning.NameTools.simpleName(s));
			newLine(pw, s.getTotalAttendance());
		}
	}

	private static void percentagePrint(PrintWriter pw, Class input) {
		writeLine(pw, "Student ID");
		writeLine(pw, "PID");
		writeLine(pw, "Student Name");
		writeLine(pw, "Attendanded Percentage");
		newLine(pw);

		for (Student s : input.getStudents()) {
			writeLine(pw, s.getOnyen());
			writeLine(pw, s.getPID());
			writeLine(pw, cleaning.NameTools.simpleName(s));
			writeLine(pw, cleaning.GradeTools.percentageCurve(s));
			newLine(pw);
		}
	}

	private static void fullPrint(PrintWriter pw, Class input) {
		writeLine(pw, "Student ID");
		writeLine(pw, "PID");
		writeLine(pw, "Student Name");
		for (int i = 1; i <= settings.AlgorithmSettings.DAYS_OF_CLASS; i++) {
			writeLine(pw, "Day " + i);
		}
		writeLine(pw, "Excuses");
		writeLine(pw, "Attendanded Percentage");
		writeLine(pw, "Total Days Attended");
		newLine(pw);
		
		System.out.println(input.getStudents().length);
		for (Student s : input.getStudents()) {
			writeLine(pw, s.getOnyen());
			writeLine(pw, s.getPID());
			writeLine(pw, cleaning.NameTools.simpleName(s));
			for (int day = 0; day < settings.AlgorithmSettings.DAYS_OF_CLASS; day++) {
				writeLine(pw, s.getAttendanceOnDay(day));
			}
			writeLine(pw, s.getDaysExcused());
			writeLine(pw, cleaning.GradeTools.percentageCurve(s));
			writeLine(pw, cleaning.GradeTools.integerCurve(s));

			newLine(pw);
		}
	}

	private static void writeLine(PrintWriter writer, String s) {
		writer.write(s);
		writer.write(",");
	}

	private static void writeLine(PrintWriter writer, double d) {
		writeLine(writer, String.format("%.2f", d));
	}

	private static void writeLine(PrintWriter writer, int n) {
		writeLine(writer, Integer.toString(n));
	}

	private static void newLine(PrintWriter writer) {
		writer.write("\n");
	}
	
	private static void newLine(PrintWriter writer, String s) {
		writer.write(s);
		writer.write("\n");
	}
	
	
	private static void newLine(PrintWriter pw, int i) {
		newLine(pw, Integer.toString(i));
	}
}
