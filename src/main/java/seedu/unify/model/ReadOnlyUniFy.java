package seedu.unify.model;

import javafx.collections.ObservableList;
import seedu.unify.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyUniFy {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getPersonList();

}
