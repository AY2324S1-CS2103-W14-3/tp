package swe.context.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.Assert.assertThrows;
import static swe.context.testutil.TestData.IndexContact.FIRST_CONTACT;
import static swe.context.testutil.TestData.IndexContact.SECOND_CONTACT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.logic.commands.AddCommand;
import swe.context.logic.commands.ClearCommand;
import swe.context.logic.commands.DeleteCommand;
import swe.context.logic.commands.EditCommand;
import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.logic.commands.ExitCommand;
import swe.context.logic.commands.FilterCommand;
import swe.context.logic.commands.FindCommand;
import swe.context.logic.commands.HelpCommand;
import swe.context.logic.commands.ListCommand;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.contact.Contact;
import swe.context.model.contact.ContainsTagPredicate;
import swe.context.model.contact.NameContainsKeywordsPredicate;
import swe.context.testutil.CommandUtil;
import swe.context.testutil.ContactBuilder;
import swe.context.testutil.EditContactDescriptorBuilder;


public class InputParserTest {

    @Test
    public void parseCommand_add() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddCommand command = (AddCommand) InputParser.parseCommand(CommandUtil.getAddCommand(contact));
        assertEquals(new AddCommand(contact), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(InputParser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(InputParser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) InputParser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(List.of(FIRST_CONTACT, Index.fromZeroBased(0))), command);
    }

    @Test
    public void parseCommand_deleteMassDelete() throws Exception {
        DeleteCommand command = (DeleteCommand) InputParser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + FIRST_CONTACT.getOneBased()
                + " " + SECOND_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(List.of(FIRST_CONTACT, SECOND_CONTACT,
                Index.fromZeroBased(0))), command);
    }

    @Test
    public void parseCommand_deleteDuplicateIndices() throws Exception {
        DeleteCommand command = (DeleteCommand) InputParser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + FIRST_CONTACT.getOneBased()
                + " " + FIRST_CONTACT.getOneBased()
                + " " + FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteCommand(List.of(FIRST_CONTACT, Index.fromZeroBased(1))), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditCommand command = (EditCommand) InputParser.parseCommand(EditCommand.COMMAND_WORD + " "
                + FIRST_CONTACT.getOneBased() + " " + CommandUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditCommand(FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(InputParser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(InputParser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        String keyword = "foobar";
        FilterCommand command = (FilterCommand) InputParser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + keyword);
        assertEquals(new FilterCommand(new ContainsTagPredicate(keyword)), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) InputParser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(InputParser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(InputParser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(InputParser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(InputParser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
            ParseException.class,
            Messages.commandInvalidFormat(HelpCommand.MESSAGE_USAGE),
            () -> InputParser.parseCommand("")
        );
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
            ParseException.class,
            Messages.commandUnknown(HelpCommand.MESSAGE_USAGE),
            () -> InputParser.parseCommand("unknownCommand")
        );
    }
}
