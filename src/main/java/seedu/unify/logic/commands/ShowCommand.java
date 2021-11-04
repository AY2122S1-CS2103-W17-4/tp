package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Date;

/**
 * Adds a task to the Uni-Fy app.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows task for the week \n"
            + "Parameters: "
            + "week number"
            + "Example: " + COMMAND_WORD + " "
            + "1";

    public static final String MESSAGE_SUCCESS = "Week %d is shown";
    public static final String MESSAGE_DUPLICATE_TASK = "This Week is already being shown";

    private final int weekNumber;

    /**
     * Creates a ShowCommand to show {@code Task} in the given Week Number
     */
    public ShowCommand(int number) {
        requireNonNull(number);
        weekNumber = number;
    }

    /**
     * Creates a ShowCommand to show {@code Task} in the given Week Number
     */
    public ShowCommand(Date date) {
        requireNonNull(date);
        weekNumber = dateToWeekNum(date);
    }

    private static int dateToWeekNum(Date date) {
        return 1;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateWeeklyTaskList(weekNumber);
        return new CommandResult(String.format(MESSAGE_SUCCESS, weekNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && weekNumber == ((ShowCommand) other).weekNumber);
    }
}

