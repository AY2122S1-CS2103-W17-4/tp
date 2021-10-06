---
layout: page
title: User Guide
---

Uni-Fy is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Uni-Fy can get your tasks to display faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Uni-Fy.jar` from [here](https://github.com/AY2122S1-CS2103T-W17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your app.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.
   * Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Italicised words represent parameters that are meant to be supplied by the user
  * e.g. in add *n/task_name d/date*, the *task_name* keyword represents a task and the *date* keyword represents a date, both to be inputted by the user

* Items inside round brackets are optional
  * e.g. in delete *n/task_name (d/date)*, a user can choose to include the date for the task if there are multiples of it

* In user inputted commands:
  * Items with n/ before them represent the exact name of the task
  * Items with tg/ before them represent the tag of the task
  * Items with d/ before them represent the date of the task
  * Items with t/ before them represent the time of the task
    * Date is represented in YYYY-MM-DD format
    * Time is represented in HH:MM format
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a task: `add` (coming soon)

Add a task to the task list.

Format:
- <code> add <i>n/task_name d/date (t/time) </i> </code>

Interpretation
* Adds task with the name <code><i>n/task_name</i></code>
* The task is added for that date only at the given time
  * Useful if it is an event or for marking dates for exams
* If <code><i>t/time</i></code> is provided:
  * The task will be scheduled to <strong>end</strong> at that specified time
* If <code><i>t/time</i></code> is not provided:
  * The task will be schedule to <strong>end</strong> at 23:59 of the date provided


Examples:
* `add n/CS2103 test d/2021-12-01 t/16:30` adds the `CS2103 test` task on `2021-12-01`, scheduled to end at `16:30`

### Locating a task by name: `find` (coming soon)

Find tasks with matching keywords and show them in the task list.

Format: `find keyword (more_keywords) (d/date)`

Interpretation:
* The search is case-insensitive.
* The order of the keywords does not matter.
* Only the name of the task is searched.
* Incomplete keywords will be matched e.g. Qu will match Quiz
* Only tasks containing all keywords will be shown
* if `d/date` field is provided:
  * Only tasks due on that date is searched.

Examples:
* `find quiz` returns CS2103 Quiz, GEQ1000 Quiz 1, GEQ1000 Quiz 2
* `find Quiz GEQ1000` returns GEQ1000 Quiz 1,  GEQ1000 Quiz 2
* `find Quiz GEQ1000 d/2021-10-10` returns all the GEQ1000 Quiz that is due by 2021-10-10


### Showing Tasks: `show` (coming soon)

Show all tasks that have been added to the app in a specific week.

Format:
- <code> show <i>week_number</i> </code>
- <code> show <i>d/date</i> </code>

Interpretation
* If <code><i>week_number</i></code> field is provided:
  * Searches for the task with the given date and show it on the GUI
  * Useful in cases where user wants to show all tasks in the given week
* If <code><i>d/date</i></code> field is provided:
  * Show tasks matching the given the date

Examples:
* `show 1` displays tasks in week `1`
* `show d/2021-10-10` displays the tasks in the week of `2021-10-10`


### Setting Priority Level for Tasks: `tag` (coming soon)

Set a task's priority.

Format:
- <code> tag n/task_name (d/date) tg/level </i> </code>
- <code> tag task_id (d/date) tg/level </i> </code>

Interpretation
* If <code><i>n/task_name</i></code> field is provided:
  * Depending on the <code><i>tg/level </i></code> provided it sets priority of the task with the name exactly matching the <code><i>n/task_name </i></code> or <code><i>task_id</i></code> to
    * Urgent
    * Important
    * Medium
    * Low
  * The <code><i>tg/level</i></code> field is case-insensitive
  * If <code><i>date</i></code> field is provided, it sets priority for the task in the given date
    * Useful in cases when there are multiple copies of the same task , recurring tasks, etc.
  * If <code><i>d/date</i></code> field is not provided, it sets priority for every occurrence of tasks matching the given name

* If <code><i>task_id</i></code> is provided:


Examples:
* `tag 5 tg/Urgent` sets the priority of task 5 in the task list to `Urgent`
* `tag n/quiz d/2021-10-10 tg/Important` sets priority of the quiz task on 2021-10-10 to `Important`


### Deleting tasks : `delete` (coming soon)

Delete a specific task that has been added to your app.

Format:
- <code> delete <i>task_id</i> </code>
- <code> delete <i>n/task_name (d/date)</i> </code>

Interpretation
* If <code><i>task_id</i></code> field is provided:
  * Deletes the task corresponding to the <code><i>task_id</i></code>
* If <code><i>n/task_name</i></code> field is provided:
  * Deletes the task with name **exactly matching** that given in the <code><i>n/task_name</i></code> field
  * If <code><i>d/date</code></i> field is provided, search for the task in the given date to delete
    * Useful in cases where there are multiple copies of the same task, recurring tasks, etc.
  * If <code><i>d/date</code></i> field is not provided, deletes **every occurence** of tasks matching the given name

Examples:
* `delete 3` removes the third item in the task list
* `delete n/quiz` removes every `quiz` task in the app
* `delete n/quiz d/2021-10-10` deletes only the `quiz` task on `2021-10-10`

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

**Uni-Fy** data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

**Uni-Fy** data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Uni-Fy will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Tasks home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | <code> add <i>n/task_name d/date (t/time) </i> </code> <br>e.g.  `add n/CS2103 test d/2021-12-01 t/16:30`
**Delete** | <code>delete <i>task_id</i></code> <br> <code>delete <i>n/task_name (d/date)</i> </code> <br> e.g. <code>delete n/quiz d/2021-10-10 </code>
**Show** | `show`
**Find** | <code>find <i>keyword (more_keywords) (d/date)</i></code> <br> e.g. `find Quiz GEQ1000 d/2021-10-10`
**Tag** | <code> tag n/task_name (d/date) tg/level </i> </code> <br>e.g.  `tag n/quiz d/2021-10-10 tg/Important`
**Help** | `help`
**Clear** | `clear`
