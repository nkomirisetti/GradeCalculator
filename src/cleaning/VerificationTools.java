package cleaning;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VerificationTools {
	public static boolean codeMatch(String dateTime, String code) throws ParseException {
		if (code.contains("whoops")) {
			return true;
		}

		return timeStampValid(dateTime, cleanString(code));
	}

	public static boolean timeStampValid(String dateTime, int code) throws ParseException {
		dateTime = dateTime + " EDT";

		long timeStamp = convertStringToUnix(dateTime);
		long roundedDown = roundDown(timeStamp);

		if (knuthVariationHash(roundedDown) == code) {
			return true;
		} else {
			roundedDown = roundedDown - 1;
			roundDown(roundedDown);
			if (knuthVariationHash(roundDown(roundedDown)) == code) {
				return true;
			}
		}

		return false;
	}

	public static long convertStringToUnix(String dateTime) throws ParseException {
		DateFormat converter = new SimpleDateFormat("M/dd/yyyy hh:mm:ss z");
		return converter.parse(dateTime).getTime() / 1000;
	}

	public static long roundDown(long dateTime) {
		int interval = settings.AlgorithmSettings.SECONDS;
		long remainder = dateTime % interval;
		return dateTime - remainder;
	}

	public static int knuthVariationHash(long timeStamp) {
		long hashedTime = timeStamp % 10000;
		hashedTime = (hashedTime * (hashedTime + 3)) % 99999;
		hashedTime = Math.abs(hashedTime);
		return (int) hashedTime;
	}

	public static int cleanString(String code) {
		if (code == "00000") {
			return 0;
		}
		int i = 0;
		while (code.charAt(i) == '0') {
			i++;
		}

		StringBuffer sb = new StringBuffer(code);
		sb.replace(0, i, "");
		code = sb.toString(); // return in String
		code = code.replaceAll(" ", "");
		code = code.replaceAll("[^0-9]", "");
		code = code.replaceAll("\n", "");
		try {
			return Integer.parseInt(code);
		} catch (Exception e) {
			return 0;
		}
	}

	public static boolean isDayExcused(int day) {
		for (int i : settings.AlgorithmSettings.DAYS_TO_IGNORE) {
			if (i == day) {
				return true;
			}
		}
		return false;
	}
}
