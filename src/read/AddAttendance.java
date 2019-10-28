package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class AddAttendance {
	public static model.Class add(model.Class inputClass) {
		for (int i = 0; i < settings.AlgorithmSettings.DAYS_OF_CLASS; i++) {
			if (i == 12) {
				i = 12;
			}
			Scanner dayScan;
			try {
				dayScan = new Scanner(new File("spreadsheets/day" + (i + 1) + ".csv"));
			} catch (FileNotFoundException e) {
				log.Logger.error("Attendance spreadsheet for week " + (i + 1) + " not found");
				return inputClass;
			}
			dayScan.useDelimiter(",|\\n");

			// skip over column headings
			dayScan.next();
			dayScan.next();
			dayScan.next();
			dayScan.next();
			dayScan.next();

			while (dayScan.hasNext()) {
				String timeStamp = dayScan.next();
				String fullName = dayScan.next();
				String onyen = dayScan.next();
				String stringPID = dayScan.next();
				String code = dayScan.next();

				boolean verified;
				try {
					verified = cleaning.VerificationTools.codeMatch(timeStamp, code);
				} catch (ParseException e) {
					log.Logger.error(
							"the timestamp: '" + timeStamp + "' and the code '" + code + "' resulted in an error");
					verified = false;
				}
				onyen = cleaning.OnyenTools.clean(onyen);
				int intPID = cleaning.PIDTools.clean(stringPID);

				int attendanceStatus;
				if (verified || cleaning.VerificationTools.isDayExcused(i)) {
					attendanceStatus = 2;
				} else {
					attendanceStatus = 1;
				}

				if (!inputClass.addAttendance(i, onyen, fullName, intPID, attendanceStatus)) {
					if (!inputClass.addAttendance(i, cleaning.OnyenTools.clean(stringPID), fullName,
							cleaning.PIDTools.clean(onyen), attendanceStatus)) {
						log.Logger.missingStudent(i, verified, fullName, intPID, onyen);
					}
				}
			}

			dayScan.close();
		}
		return inputClass;
	}
}
