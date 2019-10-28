package cleaning;

public class OnyenTools {
	public static String clean(String onyen) {
		onyen = onyen.toLowerCase();
		onyen = onyen.replace("\r", "");
		onyen = onyen.replaceAll("\"", "");

		StringBuffer fixedOnyen = new StringBuffer(onyen);
		int emailIndex = fixedOnyen.indexOf("@");
		if (emailIndex == -1) {
			return fixedOnyen.toString();
		} else {
			return fixedOnyen.subSequence(0, emailIndex).toString();
		}
	}

}
