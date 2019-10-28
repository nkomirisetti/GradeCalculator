package cleaning;

import model.Student;

public class NameTools {
	public static String clean(String name) {
		String input = name;

		input = input.replace("/r", ""); // regex for carriage return
		input = input.replaceAll("\\s", ""); // regex for whitespace
		input = input.replaceAll("\"", "");
		return input;
	}

	public static String formatName(String firstName, String lastName) {
		return lastName + ", " + firstName;
	}

	public static String simpleName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}

	public static boolean compare(String fullName, Student s) {
		String firstName = s.getFirstName().toLowerCase();
		String lastName = s.getLastName().toLowerCase();

		if (fullName.toLowerCase().contains(firstName) && fullName.toLowerCase().contains(lastName)) {
			return true;
		} else {
			return false;
		}
	}

	public static String simpleName(Student s) {
		return simpleName(s.getFirstName(), s.getLastName());
	}
	
	public static String formatName(Student s) {
		return formatName(s.getFirstName(), s.getLastName());
	}
}
