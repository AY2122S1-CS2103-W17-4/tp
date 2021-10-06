package seedu.unify.logic.parser.exceptions;

import seedu.unify.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
