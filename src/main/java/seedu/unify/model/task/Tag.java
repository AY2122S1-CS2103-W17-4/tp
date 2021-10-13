package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the UniFy app.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagTaskName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names are cases sensitive and can only be chosen "
            + "as 'Important' or 'Urgent' or 'Medium or 'Low'";

    public final String tagTaskName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagTaskName A valid tag task name.
     */
    public Tag(String tagTaskName) {
        requireNonNull(tagTaskName);
        checkArgument(isValidTagTaskName(tagTaskName), MESSAGE_CONSTRAINTS);
        this.tagTaskName = tagTaskName;
    }

    /**
     * Returns true if a given string is a valid tag task name.
     */
    public static boolean isValidTagTaskName(String test) {
        if (test.equals("Important") || test.equals("Urgent") || test.equals("Low") || test.equals("Medium")) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagTaskName.equals(((Tag) other).tagTaskName)); // state check
    }

    @Override
    public int hashCode() {
        return tagTaskName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagTaskName;
    }

}
