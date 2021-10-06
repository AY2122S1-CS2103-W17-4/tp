package seedu.unify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;

/**
 * Represents a storage for {@link UniFy}.
 */
public interface UniFyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyUniFy}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUniFy> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyUniFy> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUniFy} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyUniFy addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyUniFy)
     */
    void saveAddressBook(ReadOnlyUniFy addressBook, Path filePath) throws IOException;

}
