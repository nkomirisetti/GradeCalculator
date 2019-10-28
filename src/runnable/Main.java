package runnable;

public class Main {

	public static void main(String[] args) {
		model.Class comp401 = read.RosterMaker.buildRoster();
		comp401 = read.AddAttendance.add(comp401);
		comp401.printDailyMisses();
		comp401 = read.DropAttendance.excuses(comp401);
		output.GradePrinter.printToSpreadsheet(comp401);
		output.StatsPrinter.printToSpreadsheet(comp401);
	}
}