package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class StatsPrinter {
	public static void printToSpreadsheet(model.Class input) {
		File output = new File("spreadsheets/stats.csv");
		try {
			output.createNewFile();

		} catch (IOException e) {
			log.Logger.error("Output file already exists");

		}
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			log.Logger.error("Print writer failed");
			return;
		}

		writeDailyStats(writer, input);

		writer.close();
	}

	private static void writeDailyStats(PrintWriter pw, model.Class input) {
		writeLine(pw, "Day");
		writeLine(pw, "Total Attended");
		writeLine(pw, "Correct Code");
		writeLine(pw, "Incorrect Code");
		writeLine(pw, "Absent");
		writeLine(pw, "Percent Attendance");
		newLine(pw);

		for (int i = 0; i < settings.AlgorithmSettings.DAYS_OF_CLASS; i++) {
			writeLine(pw, i + 1);
			writeLine(pw, input.attendanceOnDay(i, 1) + input.attendanceOnDay(i, 2));
			writeLine(pw, input.attendanceOnDay(i, 2));
			writeLine(pw, input.attendanceOnDay(i, 1));
			writeLine(pw, input.attendanceOnDay(i, 0));
			writeLine(pw, (double) input.attendanceOnDay(i, 2) / (double) input.getStudents().length);
			newLine(pw);
		}

	}

	private static void writeLine(PrintWriter writer, String s) {
		writer.write(s);
		writer.write(",");
	}

	private static void writeLine(PrintWriter writer, double d) {
		writeLine(writer, Double.toString(d));
	}

	private static void writeLine(PrintWriter writer, int n) {
		writeLine(writer, Integer.toString(n));
	}

	private static void newLine(PrintWriter writer) {
		writer.write("\n");
	}

}
