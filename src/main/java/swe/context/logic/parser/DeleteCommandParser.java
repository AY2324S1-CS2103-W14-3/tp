package swe.context.logic.parser;

import java.util.ArrayList;
import java.util.List;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.logic.commands.DeleteCommand;
import swe.context.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Returns a {@link DeleteCommand} from parsing the specified arguments.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");
            List<Index> indices = new ArrayList<>();
            boolean hasDuplicate = false;

            // Parse all the index arguments and add to the list if it doesn't already contain it
            for (String arg : splitArgs) {
                Index currentIndex = ParserUtil.parseIndex(arg);
                if (indices.contains(currentIndex)) {
                    hasDuplicate = true;
                    continue;
                }
                indices.add(currentIndex);
            }

            return new DeleteCommand(indices, hasDuplicate);

        } catch (ParseException e) {
            throw new ParseException(
                    Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE), e);
        }
    }
}
