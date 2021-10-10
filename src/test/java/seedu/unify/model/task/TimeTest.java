package seedu.unify.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid phone numbers
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("91")); // less than 3 numbers
        assertFalse(Time.isValidTime("phone")); // non-numeric
        assertFalse(Time.isValidTime("9011p041")); // alphabets within digits
        assertFalse(Time.isValidTime("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Time.isValidTime("911")); // exactly 3 numbers
        assertTrue(Time.isValidTime("93121534"));
        assertTrue(Time.isValidTime("124293842033123")); // long phone numbers

        /*
        updated test

        // null time
        assertThrows(NullPointerException.class, () -> Time2.isValidTime(null));

        // invalid times
        assertFalse(Time2.isValidTime("")); // empty string
        assertFalse(Time2.isValidTime(" ")); // spaces only
        assertFalse(Time2.isValidTime("1:30")); // hour not in HH
        assertFalse(Time2.isValidTime("time")); // non-numeric
        assertFalse(Time2.isValidTime("1215")); // missing colon
        assertFalse(Time2.isValidTime("24:30")); // hour not in range
        assertFalse(Time2.isValidTime("23:60")); // minute not in range

        // valid times
        assertTrue(Time2.isValidTime("00:00")); // first minute of day
        assertTrue(Time2.isValidTime("23:59")); // last minute of day
        assertTrue(Time2.isValidTime("09:30"));
         */

    }
}
