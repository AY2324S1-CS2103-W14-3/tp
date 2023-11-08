package swe.context.logic.parser;

import static swe.context.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.logic.commands.DeleteCommand;
import swe.context.testutil.TestData;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand1() {
        assertParseSuccess(parser, "1",
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT), false));
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand2() {
        assertParseSuccess(parser, "1 1",
                new DeleteCommand(List.of(TestData.IndexContact.FIRST_CONTACT), true));
    }

    @Test
    public void parse_invalidArgs1_throwsParseException() {
        assertParseFailure(
            parser,
            "a",
            Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE)
        );
    }

    @Test
    public void parse_invalidArgs2_throwsParseException() {
        assertParseFailure(
            parser,
            "",
            Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE)
        );
    }
}
