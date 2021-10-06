package seedu.unify.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.unify.commons.core.LogsCenter;
import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.commons.util.FileUtil;
import seedu.unify.commons.util.JsonUtil;
import seedu.unify.model.ReadOnlyUniFy;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonUniFyStorage implements UniFyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniFyStorage.class);

    private Path filePath;

    public JsonUniFyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniFy> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUniFy> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyUniFy addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyUniFy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyUniFy addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

}
