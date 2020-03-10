package seedu.address.model.profile.course.module.personal;

/**
 * A wrapper class to contain attributes of a Module specific to a particular student.
 */
public class Personal {

    private Status status;
    private Grade grade;
    private TaskList taskList;

    public Personal() {
        status = new Status();
        grade = new Grade();
        taskList = new TaskList();
    }

    // Currently unusable because all the fields are private.
    // TODO: Link the parsed input to the input of Personal, then call the respective functions within each class.
}
