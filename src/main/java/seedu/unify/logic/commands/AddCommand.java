package seedu.unify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unify.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.unify.logic.commands.exceptions.CommandException;
import seedu.unify.model.Model;
import seedu.unify.model.task.Task;

/**
 * Adds a task to the Uni-Fy app.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the Uni-Fy app. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_DATE + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the Uni-Fy app";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
