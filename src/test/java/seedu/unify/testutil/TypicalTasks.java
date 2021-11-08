package seedu.unify.testutil;

import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_QUIZ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.unify.model.UniFy;
import seedu.unify.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task CS1234_QUIZ = new TaskBuilder().withName("Quiz 4")
            .withDate("2021-12-11")
            .withTime("12:45")
            .withTags("CS1234").build();
    public static final Task MATH_ASSIGNMENT = new TaskBuilder().withName("Math Assignment 1")
            .withDate("2021-11-23")
            .withTime("23:41")
            .withTags("Important")
            .withState("TODO").build();
    public static final Task GYM_TRAINING = new TaskBuilder().withName("Gym Training").withTime("23:41")
            .withDate("2021-10-25").build();
    public static final Task GE1234_LAB = new TaskBuilder().withName("Lab 1").withTime("08:35")
            .withDate("2021-12-11").withTags("GE1234").build();
    public static final Task CS4321_GROUP_MEETING = new TaskBuilder().withName("Group Meeting").withTime("12:33")
            .withDate("2021-12-25").withTags("CS4321").build();
    public static final Task GYM_TRAINING_2 = new TaskBuilder().withName("Gym Training 2").withTime("14:40")
            .withDate("2021-12-23").build();
    public static final Task INTERNSHIP_SEMINAR = new TaskBuilder().withName("Internship Seminar").withTime("13:44")
            .withDate("2021-10-25").build();
    public static final Task CS2101_DEMO_DONE = new TaskBuilder().withName("DEMO").withTime("12:59")
            .withDate("2022-01-15").withTags("CS2101").withState("DONE").build();

    // Manually added
    public static final Task BUY_GROCERIES = new TaskBuilder().withName("Buy Groceries").withTime("11:22")
            .withDate("2021-10-25").build();
    public static final Task CS1234_PRESENTATION = new TaskBuilder().withName("Presentation").withTime("12:59")
            .withDate("2022-01-15").withTags("CS1234").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task ASSIGNMENT = new TaskBuilder().withName(VALID_NAME_ASSIGNMENT)
            .withTime(VALID_TIME_ASSIGNMENT).withDate(VALID_DATE_ASSIGNMENT).withTags(VALID_TAG_MODULE).build();
    public static final Task QUIZ = new TaskBuilder().withName(VALID_NAME_QUIZ).withTime(VALID_TIME_QUIZ)
            .withDate(VALID_DATE_QUIZ).withTags(VALID_TAG_MODULE).build();

    public static final String KEYWORD_MATCHING_QUIZ = "quiz"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code DateBook} with all the typical tasks.
     */
    public static UniFy getTypicalUniFy() {
        UniFy ab = new UniFy();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(
                Arrays.asList(CS1234_QUIZ,
                        MATH_ASSIGNMENT,
                        GYM_TRAINING,
                        GE1234_LAB,
                        CS4321_GROUP_MEETING,
                        GYM_TRAINING_2,
                        CS2101_DEMO_DONE,
                        INTERNSHIP_SEMINAR));
    }
}
