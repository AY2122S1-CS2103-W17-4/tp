package seedu.unify.testutil;

import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class UniFyBuilder {

    private UniFy addressBook;

    public UniFyBuilder() {
        addressBook = new UniFy();
    }

    public UniFyBuilder(UniFy addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public UniFyBuilder withPerson(Task task) {
        addressBook.addPerson(task);
        return this;
    }

    public UniFy build() {
        return addressBook;
    }
}
