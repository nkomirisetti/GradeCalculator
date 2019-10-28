package cleaning;

public class PIDTools {
	public static int clean(String input) {
		try {
			input.replaceAll("-", "");
			input.replaceAll(" ", "");
			input = input.replaceAll("[^0-9]", "");
			return Integer.parseInt(input);
		} catch (Exception e) {
			//log.Logger.error("The PID " + input + " was unparseable");
			return 0;
		}
	}
}
