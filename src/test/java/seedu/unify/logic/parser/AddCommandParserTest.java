package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.unify.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.unify.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.unify.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.unify.testutil.TypicalPersons.AMY;
import static seedu.unify.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.AddCommand;
import seedu.unify.model.tag.Tag;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Email;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Person;
import seedu.unify.model.task.Phone;
import seedu.unify.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple dates - last date accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_DESC_AMY
                + DATE_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + DATE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + DATE_DESC_BOB,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_DATE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_DATE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + DATE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DATE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + DATE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_DATE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + DATE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
