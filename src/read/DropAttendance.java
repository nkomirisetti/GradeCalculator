package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Class;

public class DropAttendance {

	public static Class excuses(Class input) {
		Scanner dropScan;
		try {
			dropScan = new Scanner(new File("spreadsheets/excuses.csv"));
		} catch (FileNotFoundException e) {
			log.Logger.error("Excuses spreadsheet not found, returning orignal spreadsheet");
			return input;
		}
		dropScan.useDelimiter(",|\\n");

		// skip over column headings
		dropScan.next();
		dropScan.next();
		dropScan.next();

		while (dropScan.hasNext()) {
			String onyen = dropScan.next();
			String stringPID = dropScan.next();
			String fullName = dropScan.next();

			onyen = cleaning.OnyenTools.clean(onyen);
			int intPID = cleaning.PIDTools.clean(stringPID);

			if (!input.addExcuse(fullName, onyen, intPID)) {
				if (!input.addExcuse(fullName, cleaning.OnyenTools.clean(stringPID), cleaning.PIDTools.clean(onyen))) {
					log.Logger.missingExcuse(onyen, stringPID, fullName);
				}
			}

		}
		dropScan.close();
		return input;
	}

}
