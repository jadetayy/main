package seedu.address.model.profile.course.module.personal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.profile.Profile;

import java.util.ArrayList;

/**
 * DeadlineList contains the list of deadlines and methods for the list.
 */
public class DeadlineList {
    private final ObservableList<Deadline> deadlineList = FXCollections.observableArrayList();

    public DeadlineList() {
    }

//    public DeadlineList(ArrayList<Deadline> list) {
//        this.list = list;
//    }

    public ObservableList<Deadline> getDeadlineList() {
        return deadlineList;
    }

    /**
     * Returns specific task required.
     *
     * @param index Index of task in the list.
     * @return Required task.
     */
    public Deadline getDeadline(int index) {
        return deadlineList.get(index);
    }

    /**
     * Returns number of tasks in list.
     *
     * @return size of list.
     */
    public int getListSize() {
        return deadlineList.size();
    }

    public void addDeadline(Deadline deadline) {
        deadlineList.add(deadline);
    }

    public void deleteDeadline(Deadline deadline) {
        deadlineList.removeIf(dl->dl.getDescription().equals(deadline.getDescription()));
    }
}
