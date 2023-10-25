package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.TestData.Valid.EMAIL_BOB;
import static swe.context.testutil.TestData.Valid.NAME_BOB;
import static swe.context.testutil.TestData.Valid.NOTE_BOB;
import static swe.context.testutil.TestData.Valid.PHONE_BOB;

import org.junit.jupiter.api.Test;

import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.testutil.EditContactDescriptorBuilder;
import swe.context.testutil.TestData;



public class EditContactDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditContactDescriptor descriptorWithSameValues = new EditContactDescriptor(TestData.Valid.EditDescriptor.AMY);
        assertTrue(TestData.Valid.EditDescriptor.AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(TestData.Valid.EditDescriptor.AMY.equals(TestData.Valid.EditDescriptor.AMY));

        // null -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(null));

        // different types -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(5));

        // different values -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(TestData.Valid.EditDescriptor.BOB));

        // different name -> returns false
        EditContactDescriptor editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withName(NAME_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withPhone(PHONE_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withEmail(EMAIL_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withNote(NOTE_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        String expected = EditContactDescriptor.class.getCanonicalName() + "{name="
                + editContactDescriptor.getName().orElse(null) + ", phone="
                + editContactDescriptor.getPhone().orElse(null) + ", email="
                + editContactDescriptor.getEmail().orElse(null) + ", address="
                + editContactDescriptor.getNote().orElse(null) + ", tags="
                + editContactDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editContactDescriptor.toString());
    }
}
