package seedu.address.model.tag;

import seedu.address.model.contact.Contact;



/**
 * Represents a {@link Contact}'s Tag.
 *
 * A Tag must be an alphanumeric string but may contain spaces.
 */
public class Tag {
    public final String name;

    /**
     * Returns whether the specified name is valid for constructing a Tag.
     *
     * @param name Name to check.
     */
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\d ]+");
    }

    /**
     * Constructs a Tag with the specified name.
     *
     * The provided name must already be valid.
     *
     * @param _name Valid name.
     */
    public Tag(String _name) {
        this.name = _name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        // instanceof also handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }
        Tag otherTag = (Tag)other;

        if (otherTag == this) {
            return true;
        }

        return this.name.equals(otherTag.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
