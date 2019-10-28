package settings;

public class AlgorithmSettings {
	public static final int DAYS_OF_CLASS = 8;
	public static final int[] DAYS_TO_IGNORE = { 0, 1, 2, 3, 4,  5,  6, 7};
	public static final String ROSTER_LOCATION = "spreadsheets/roster.csv";
	public static final int SECONDS = 20;
	public static final int DAYS_TO_DROP = 5;
	public static final boolean IS_CURVED = false;

	public static enum OUTPUT_TYPE {
		BASIC, PERCENTAGE, FULL, SAKAI
	}

	public static final OUTPUT_TYPE OUTPUT = OUTPUT_TYPE.SAKAI;
}
