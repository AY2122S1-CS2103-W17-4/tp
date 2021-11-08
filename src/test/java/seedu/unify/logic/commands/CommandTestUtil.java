package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.unify.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.unify.commons.core.index.Index;
import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.UniFy;
import seedu.unify.model.task.NameContainsKeywordsPredicate;
import seedu.unify.model.task.Task;
import seedu.unify.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ASSIGNMENT = "Assignment 2";
    public static final String VALID_NAME_QUIZ = "Quiz 1";
    public static final String VALID_TIME_ASSIGNMENT = "16:40";
    public static final String VALID_TIME_QUIZ = "11:35";
    public static final String VALID_DATE_ASSIGNMENT = "2021-12-11";
    public static final String VALID_DATE_QUIZ = "2022-02-21";
    public static final String VALID_TAG_MODULE = "CS1234";
    public static final String VALID_TAG_CCA = "CCA";
    public static final String DUPLICATE_DATE_QUIZ = "2021-12-11";
    public static final String DUPLICATE_TAG_MODULE = "CS1234";

    public static final String NAME_DESC_ASSIGNMENT = " " + PREFIX_NAME + VALID_NAME_ASSIGNMENT;
    public static final String NAME_DESC_QUIZ = " " + PREFIX_NAME + VALID_NAME_QUIZ;
    public static final String TIME_DESC_ASSIGNMENT = " " + PREFIX_TIME + VALID_TIME_ASSIGNMENT;
    public static final String TIME_DESC_QUIZ = " " + PREFIX_TIME + VALID_TIME_QUIZ;
    public static final String DATE_DESC_ASSIGNMENT = " " + PREFIX_DATE + VALID_DATE_ASSIGNMENT;
    public static final String DATE_DESC_QUIZ = " " + PREFIX_DATE + VALID_DATE_QUIZ;
    public static final String TAG_DESC_CCA = " " + PREFIX_TAG + VALID_TAG_CCA;
    public static final String TAG_DESC_MODULE = " " + PREFIX_TAG + VALID_TAG_MODULE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Quiz &"; // '&' not allowed in names
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "15:13a"; // 'a' not allowed in time
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE; // empty string not allowed for dates
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "secret*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_ASSIGNMENT;
    public static final EditCommand.EditTaskDescriptor DESC_QUIZ;

    static {
        DESC_ASSIGNMENT = new EditTaskDescriptorBuilder().withName(VALID_NAME_ASSIGNMENT)
                .withTime(VALID_TIME_ASSIGNMENT).withDate(VALID_DATE_ASSIGNMENT)
                .withTags(VALID_TAG_MODULE).build();
        DESC_QUIZ = new EditTaskDescriptorBuilder().withName(VALID_NAME_QUIZ)
                .withTime(VALID_TIME_QUIZ).withDate(VALID_DATE_QUIZ)
                .withTags(VALID_TAG_MODULE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UniFy expectedUniFy = new UniFy(actualModel.getUniFy());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedUniFy, actualModel.getUniFy());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().taskName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
