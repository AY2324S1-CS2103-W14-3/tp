package swe.context.logic;

/**
 * Holds message strings used by the logic for display to the user.
 *
 * Public messages do not need formatting and can be used directly after import.
 *
 * Private messages need formatting and have the prefix {@code UNFORMATTED_}.
 * These contain raw format specifiers (e.g. {@code %s}) that should be
 * populated by calling their associated methods.
 */
public final class Messages {
    // Generic commands
    public static final String DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued parameters(s): ";
    public static final String COMMAND_DUPLICATE_CONTACT =
            "There is an existing contact with the same name.";
    private static final String UNFORMATTED_COMMAND_INVALID_FORMAT = "Invalid command format.%n%s";
    private static final String UNFORMATTED_COMMAND_UNKNOWN = "Unknown command.%n%s";
    private static final String UNFORMATTED_CONTACTS_LISTED_OVERVIEW = "%d contacts listed.";
    private static final String UNFORMATTED_ADD_COMMAND_SUCCESS = "New contact added: %s";
    private static final String UNFORMATTED_DELETE_COMMAND_SUCCESS = "Deleted contact(s):%n%s";
    private static final String UNFORMATTED_DELETE_DUPLICATE_COMMAND_SUCCESS =
            "Note that the duplicate indices just provided only count once.\n Deleted contact(s):%n%s";
    private static final String UNFORMATTED_EDIT_COMMAND_SUCCESS = "Edited contact: %s";

    // Specific commands
    public static final String COMMAND_EDIT_NO_PARAM =
            "At least one optional parameter to edit must be provided.";
    public static final String COMMAND_CLEAR_SUCCESS = "Removed all contacts!";
    public static final String COMMAND_LIST_SUCCESS = "Listed all contacts.";
    public static final String COMMAND_HELP_SUCCESS = "Opened help window.";
    public static final String COMMAND_EXIT_SUCCESS = "Exiting app...";

    public static final String INVALID_DELETE_INDEX =
            "One or more of the contact indices provided are invalid.";
    public static final String INVALID_EDIT_INDEX = "The contact index provided is invalid.";

    // Validation messages for command parameter constraints
    public static final String NAME_INVALID = "Names must be alphanumeric (spaces allowed).";
    public static final String PHONE_INVALID = "Phone numbers must start with at least 3 digits.";
    public static final String EMAIL_INVALID =
            "Emails must roughly be of the form \"example_email@foo-domain.sg.\"";
    private static final String UNFORMATTED_TAG_INVALID =
            "\"%s\" is not a valid tag. Tags must be alphanumeric (spaces allowed).";
    private static final String UNFORMATTED_ALTERNATECONTACT_INVALID =
            "\"%s\" is not a valid alternate contact. "
            + "Alternate contacts must roughly be of the form \"SocialMedia: Username\"";

    // JSON
    public static final String CONVERT_CONTACTS_DUPLICATE =
            "Encountered duplicate while converting contacts.";

    // Exceptions
    public static final String DUPLICATE_CONTACT_EXCEPTION =
            "Operation would result in duplicate contacts";
    private static final String UNFORMATTED_FILE_OPS_ERROR_FORMAT =
            "Could not save data due to the following error: %s";
    private static final String UNFORMATTED_FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private static final String UNFORMATTED_FIELD_MISSING = "Contact's %s field is missing.";

    /**
     * Returns a formatted message about the command format being invalid, with
     * the specified help text.
     */
    public static String commandInvalidFormat(String helpText) {
        return String.format(
            Messages.UNFORMATTED_COMMAND_INVALID_FORMAT,
            helpText
        );
    }

    /**
     * Returns a formatted message about the command being unknown, with the
     * specified help text.
     */
    public static String commandUnknown(String helpText) {
        return String.format(
            Messages.UNFORMATTED_COMMAND_UNKNOWN,
            helpText
        );
    }

    /**
     * Returns a formatted message about the specified tag name being invalid.
     */
    public static String tagInvalid(String invalidName) {
        return String.format(
            Messages.UNFORMATTED_TAG_INVALID,
            invalidName
        );
    }

    /**
     * Returns a formatted message showing the number of contacts listed.
     */
    public static String contactsListedOverview(int count) {
        return String.format(UNFORMATTED_CONTACTS_LISTED_OVERVIEW, count);
    }

    /**
     * Returns a formatted message indicating a successful addition of a contact.
     */
    public static String addCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_ADD_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating successful deletion of contact(s).
     */
    public static String deleteCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_DELETE_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating successful editing of a contact.
     */
    public static String editCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_EDIT_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating successful deletion that contains duplicate.
     */
    public static String deleteDuplicateCommandSuccess(String contactDetails) {
        return String.format(UNFORMATTED_DELETE_DUPLICATE_COMMAND_SUCCESS, contactDetails);
    }

    /**
     * Returns a formatted message indicating a file operation error.
     */
    public static String fileOpsErrorFormat(String errorDetails) {
        return String.format(UNFORMATTED_FILE_OPS_ERROR_FORMAT, errorDetails);
    }

    /**
     * Returns a formatted message indicating a file permission error.
     */
    public static String fileOpsPermissionErrorFormat(String filePath) {
        return String.format(UNFORMATTED_FILE_OPS_PERMISSION_ERROR_FORMAT, filePath);
    }

    /**
     * Returns a formatted message indicating a missing field in a contact.
     */
    public static String fieldMissing(String fieldName) {
        return String.format(UNFORMATTED_FIELD_MISSING, fieldName);
    }

    /**
     * Returns a formatted message about the specified alternate contact being invalid.
     */
    public static String alternateContactInvalid(String invalid) {
        return String.format(
                Messages.UNFORMATTED_ALTERNATECONTACT_INVALID, invalid);
    }

    private Messages() {
        // No instantiation
    }
}
