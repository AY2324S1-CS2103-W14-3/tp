package seedu.address.logic;



//TODO we could avoid static imports, and instead refer to strings as
// Messages.FOO. Then we can remove the MESSAGE_ prefix for everything
//TODO prefix messages containing format specifiers with UNFORMATTED_
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
    public static final String INVALID_CONTACT_DISPLAYED_INDEX = "The contact index provided is invalid";
    public static final String CONTACTS_LISTED_OVERVIEW = "%d contacts listed!";
    public static final String DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String EMPTY_WORD_PARAMETER = "Word parameter cannot be empty";
    public static final String SINGLE_WORD_EXPECTED = "Word parameter should be a single word";

    // Messages associated with various Commands
    public static final String ADD_COMMAND_SUCCESS = "New contact added: %1$s";
    public static final String COMMAND_DUPLICATE_CONTACT = "This contact is already in your contact list.";
    public static final String DELETE_COMMAND_SUCCESS = "Deleted Contact: %1$s";
    public static final String EDIT_COMMAND_SUCCESS = "Edited Contact: %1$s";
    public static final String EDIT_COMMAND_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String HELP_COMMAND_SHOW_HELP = "Opened help window.";
    public static final String LIST_COMMAND_SUCCESS = "Listed all contacts";

    // Exception message
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";
    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";
    public static final String DUPLICATE_CONTACT_EXCEPTION = "Operation would result in duplicate contacts";

    // Messages associated with Attributes constraints
    public static final String NAME_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String PHONE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";

    // Messages associated with Storage
    public static final String FIELD_MISSING = "Contact's %s field is missing.";

    //TODO refine the messages above this line

    // Generic commands
    public static final String COMMAND_UNKNOWN = "Unknown command.";
    private static final String UNFORMATTED_COMMAND_INVALID_FORMAT =
            "Invalid command format.\n%s";

    // Specific commands
    public static final String COMMAND_CLEAR_SUCCESS = "All contacts have been removed!";
    public static final String COMMAND_EXIT_SUCCESS = "Exiting app...";

    // Contacts
    private static final String UNFORMATTED_TAG_INVALID =
            "\"%s\" is not a valid tag. Tags must be alphanumeric (spaces allowed).";

    public static final String EMAIL_INVALID =
            "Emails must roughly be of the form \"example_email@foo-domain.sg.\"";

    // JSON
    public static final String CONVERT_CONTACTS_DUPLICATE = "Encountered duplicate while converting contacts.";

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
     * Returns a formatted message about the specified tag name being invalid.
     */
    public static String tagInvalid(String invalidName) {
        return String.format(
            Messages.UNFORMATTED_TAG_INVALID,
            invalidName
        );
    }

    private Messages() {
        // No instantiation
    }
}
