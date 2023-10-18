package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.Messages;
import seedu.address.model.ContactsManager;
import seedu.address.testutil.TestData;

public class JsonSerializableContactsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableContactsTest");
    private static final Path TYPICAL_CONTACTS_FILE = TEST_DATA_FOLDER.resolve("typicalContactsConText.json");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactConText.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve("duplicateContactConText.json");

    @Test
    public void toModelType_typicalContactsFile_success() throws Exception {
        JsonSerializableContacts dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTACTS_FILE,
                JsonSerializableContacts.class).get();
        ContactsManager conTextFromFile = dataFromFile.toModelType();
        ContactsManager typicalContactsContactsManager = TestData.getTypicalContactsManager();
        assertEquals(conTextFromFile, typicalContactsContactsManager);
    }

    @Test
    public void toModelType_invalidContactFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContacts dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
                JsonSerializableContacts.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableContacts dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableContacts.class).get();
        assertThrows(IllegalValueException.class, Messages.MESSAGE_CONTAIN_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

}