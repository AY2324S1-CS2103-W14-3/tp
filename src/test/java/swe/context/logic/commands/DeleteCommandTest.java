package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.logic.commands.CommandTestUtil.assertCommandFailure;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;

import java.util.List;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand =
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT, Index.fromZeroBased(0)));

        String expectedMessage = Messages.deleteCommandSuccess(Contact.format(contactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex, Index.fromZeroBased(0)));

        assertCommandFailure(deleteCommand, model, Messages.INVALID_DELETE_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);
        Contact contactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT,
                Index.fromZeroBased(0)));

        String expectedMessage = Messages.deleteCommandSuccess(Contact.format(contactToDelete));

        Model expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);
        showNoContact(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);

        Index outOfBoundIndex = TestData.IndexContact.SECOND_CONTACT;
        // Before removal, ensure the index is still in the bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContacts().getUnmodifiableList().size());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex, Index.fromZeroBased(0)));

        assertCommandFailure(deleteCommand, model, Messages.INVALID_DELETE_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand =
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT));
        DeleteCommand deleteSecondCommand =
                new DeleteCommand(List.of(TestData.IndexContact.SECOND_CONTACT));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy =
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        List<Index> targetIndices = List.of(Index.fromOneBased(1), Index.fromOneBased(2));
        DeleteCommand deleteCommand = new DeleteCommand(targetIndices);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndices=" + targetIndices + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.setContactsFilter(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Contact firstContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        Contact secondContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.SECOND_CONTACT.getZeroBased());
        DeleteCommand deleteCommand =
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT,
                        TestData.IndexContact.SECOND_CONTACT, Index.fromZeroBased(0)));

        String expectedMessage = Messages.deleteCommandSuccess(
                Contact.format(firstContactToDelete) + ",\n" + Contact.format(secondContactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(firstContactToDelete);
        expectedModel.removeContact(secondContactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesUnfilteredList_successWithDuplicate() {
        Contact firstContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        Contact secondContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.SECOND_CONTACT.getZeroBased());
        DeleteCommand deleteCommand =
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT,
                        TestData.IndexContact.SECOND_CONTACT, Index.fromZeroBased(1)));

        String expectedMessage = Messages.deleteDuplicateCommandSuccess(
                Contact.format(firstContactToDelete) + ",\n" + Contact.format(secondContactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(firstContactToDelete);
        expectedModel.removeContact(secondContactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unorderedIndicesUnfilteredList_success() {
        Contact firstContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        Contact secondContactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.SECOND_CONTACT.getZeroBased());

        // Providing the SECOND_CONTACT before the FIRST_CONTACT in the list.
        List<Index> unorderedIndices =
                List.of(TestData.IndexContact.SECOND_CONTACT,
                        TestData.IndexContact.FIRST_CONTACT, Index.fromZeroBased(0));
        DeleteCommand deleteCommand = new DeleteCommand(unorderedIndices);

        String expectedMessage = Messages.deleteCommandSuccess(
                Contact.format(secondContactToDelete) + ",\n" + Contact.format(firstContactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(firstContactToDelete);
        expectedModel.removeContact(secondContactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someInvalidIndicesUnfilteredList_throwsCommandException() {
        Index validIndex = TestData.IndexContact.FIRST_CONTACT;
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);

        // Providing a valid index followed by an invalid one.
        List<Index> indices = List.of(validIndex, outOfBoundIndex, Index.fromZeroBased(0));
        DeleteCommand deleteCommand = new DeleteCommand(indices);

        assertCommandFailure(deleteCommand, model, Messages.INVALID_DELETE_INDEX);
    }
}
