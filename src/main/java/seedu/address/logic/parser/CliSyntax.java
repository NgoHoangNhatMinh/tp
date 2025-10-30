package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_APPT_PATIENT = new Prefix("n/");
    public static final Prefix PREFIX_APPT_DOCTOR = new Prefix("d/");
    public static final Prefix PREFIX_APPT_TIME = new Prefix("t/");
    public static final Prefix PREFIX_APPT_NOTE = new Prefix("note/");

    // Custom prefixes for prescription
    public static final Prefix PREFIX_PATIENT = new Prefix("n/");
    public static final Prefix PREFIX_MEDICATION = new Prefix("m/");
    public static final Prefix PREFIX_DOSAGE = new Prefix("d/");
    public static final Prefix PREFIX_FREQUENCY = new Prefix("f/");
    public static final Prefix PREFIX_STARTDATE = new Prefix("s/");
    public static final Prefix PREFIX_DURATION = new Prefix("dur/");
    public static final Prefix PREFIX_NOTE = new Prefix("note/");

    // Other custom prefixes for patient information

    public static final Prefix PREFIX_DOB = new Prefix("dob/"); // For birthday
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_EMERGENCY = new Prefix("em/"); // Suitable?
    public static final Prefix PREFIX_ID = new Prefix("id/");
    public static final Prefix PREFIX_LANG = new Prefix("lang/");
}
