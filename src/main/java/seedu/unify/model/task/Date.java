package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * Represents a Task's date in the Uni-fy app.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date should only contain numbers in the format of YYYY-MM-DD,\n"
            + "where YYYY is from 2021 to 2022, "
            + "MM is from 01 to 12, "
            + "and DD is from 01 to 31\n"
            + "and should be of a valid date.";

    /**
     * The date should follow the format YYYY-MM-DD.
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final String YEAR_REGEX = "(202[12])";
    private static final String MONTH_REGEX = "(0[1-9]|1[012])";
    private static final String DAY_REGEX = "(0[1-9]|[12][0-9]|3[01])";
    public static final String VALIDATION_REGEX = YEAR_REGEX + "-" + MONTH_REGEX + "-" + DAY_REGEX;

    public final String value;
    public final LocalDate localDate;
    public final Integer week;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
        localDate = LocalDate.parse(this.value, LOCAL_DATE_FORMAT);
        week = localDate
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            SIMPLE_DATE_FORMAT.setLenient(false);
            SIMPLE_DATE_FORMAT.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Parses date from string format into LocalDate format and returns it.
     *
     * @return The LocalDate representing the date.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    public Integer getWeek() {
        return week;
    }
}
