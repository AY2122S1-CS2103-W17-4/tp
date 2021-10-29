package seedu.unify.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import seedu.unify.logic.commands.SortCommand;
import seedu.unify.logic.parser.exceptions.ParseException;
import seedu.unify.model.task.Task;

/**
 *
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * sfssa
     *
     * @param args sfsf
     * @return sfsfsaf
     * @throws ParseException safest
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
            args, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_SORT_ORDER
        );

        BinaryOperator<Integer> op = (x, y) -> x - y;
        if (argumentMultimap.getValue(CliSyntax.PREFIX_SORT_ORDER).isPresent()) {
            String sortOrder = argumentMultimap.getValue(CliSyntax.PREFIX_SORT_ORDER).get();
            if (sortOrder.equalsIgnoreCase("desc")) {
                op = (x, y) -> y - x;
            } else if (!sortOrder.equalsIgnoreCase("asc")) {
                // throw error
            }
        }


        Function<Task, Integer> func = x -> x.getTime().getTimeInMinutesFromStartOfDay();

        if (argumentMultimap.getValue(CliSyntax.PREFIX_TYPE).isPresent()) {
            String sortType = argumentMultimap.getValue(CliSyntax.PREFIX_TYPE).get();
            if (sortType.equalsIgnoreCase("priority")) {
                func = Task::getPriority;
            } else if (!sortType.equalsIgnoreCase("time")) {
                // throw error
            }
        }

        BiFunction<Function<Task, Integer>, BinaryOperator<Integer>, BiFunction<Task, Task, Integer>> finalFunc = (
            compVar, order) -> (Task x, Task y) ->
                order.apply(compVar.apply(x), compVar.apply((y)));


        return new SortCommand(finalFunc.apply(func, op));
    }

}
