package seedu.unify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.ReadOnlyUserPrefs;
import seedu.unify.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UniFyStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyUniFy> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyUniFy addressBook) throws IOException;

}
