package swe.context.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import swe.context.commons.core.index.Index;
import swe.context.commons.util.ToStringBuilder;
import swe.context.logic.Messages;
import swe.context.logic.commands.exceptions.CommandException;
import swe.context.model.Model;
import swe.context.model.contact.Contact;



/**
 * Deletes one or more {@link Contact}s based on their displayed indices in the UI list.
 * Duplicate indices are considered only once.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = String.format(
        "%s: Deletes contact(s)."
                + "%nParameters: INDEX..."
                + "%nExample: %s 1 3 5",
        DeleteCommand.COMMAND_WORD,
        DeleteCommand.COMMAND_WORD
    );

    private final List<Index> targetIndices;
    private final boolean hasDuplicate;

    /**
     * Constructs a new {@code DeleteCommand} with the specified indices and duplication status.
     *
     * @param targetIndices the list of indices of the items to be deleted.
     * @param hasDuplicate a boolean value indicating if there are duplicate indices in the list.
     */
    public DeleteCommand(List<Index> targetIndices, boolean hasDuplicate) {
        this.targetIndices = targetIndices;
        this.hasDuplicate = hasDuplicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> currentContactList = model.getFilteredContactList();
        List<Contact> contactsToDelete = new ArrayList<>();

        // Collect contacts to delete
        for (int i = 0; i < targetIndices.size(); i++) {
            Index index = targetIndices.get(i);
            if (index.getZeroBased() >= currentContactList.size()) {
                throw new CommandException(Messages.INVALID_DELETE_INDEX);
            }
            contactsToDelete.add(currentContactList.get(index.getZeroBased()));
        }

        // Delete the contacts
        for (Contact contact : contactsToDelete) {
            model.removeContact(contact);
        }

        // Format the deleted contacts for the message
        String formattedContacts = contactsToDelete.stream()
                .map(Contact::format)
                .collect(Collectors.joining(",\n"));

        if (hasDuplicate) {
            return new CommandResult(Messages.deleteDuplicateCommandSuccess(formattedContacts));
        }
        return new CommandResult(Messages.deleteCommandSuccess(formattedContacts));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndices.equals(otherDeleteCommand.targetIndices)
                && this.hasDuplicate == otherDeleteCommand.hasDuplicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndices", targetIndices)
                .toString();
    }
}
