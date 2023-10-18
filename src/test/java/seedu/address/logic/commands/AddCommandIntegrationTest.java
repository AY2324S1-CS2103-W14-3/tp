package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestData.Valid.Contact.getTypicalContactsManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsManager(), new UserPrefs());
    }

    @Test
    public void execute_newContact_success() {
        Contact validContact = new ContactBuilder().build();

        Model expectedModel = new ModelManager(model.getContactList(), new UserPrefs());
        expectedModel.addContact(validContact);

        assertCommandSuccess(new AddCommand(validContact), model,
                String.format(Messages.MESSAGE_ADD_COMMAND_SUCCESS, Contact.format(validContact)),
                expectedModel);
    }

    @Test
    public void execute_duplicateContact_throwsCommandException() {
        Contact contactInList = model.getContactList().getContactList().get(0);
        assertCommandFailure(new AddCommand(contactInList), model,
                Messages.MESSAGE_COMMAND_DUPLICATE_CONTACT);
    }

}
