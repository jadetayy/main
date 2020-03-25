package seedu.address.model.profile;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.profile.course.CourseName;
import seedu.address.model.profile.course.module.Module;
import seedu.address.model.profile.course.module.ModuleCode;
import seedu.address.model.profile.course.module.personal.Deadline;
import seedu.address.model.profile.course.module.personal.DeadlineList;

/**
 * Represents a Profile in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Profile {

    // Identity fields
    private static HashMap<Integer, ArrayList<Module>> moduleHash;
    private static int currentSemester = 0;
    private static String specialisation;
    private static Name name;
    private static CourseName courseName;
    private final DeadlineList deadlines;
    private FilteredList<Deadline> filteredDeadlines;


    /**
     * Every field must be present and not null.
     */
    public Profile(Name name, CourseName courseName, int currentSemester, String specialisation) {
        requireAllNonNull(name);
        requireAllNonNull(courseName);
        requireAllNonNull(currentSemester);
        Profile.name = name;
        Profile.courseName = courseName;
        Profile.currentSemester = currentSemester;
        Profile.specialisation = specialisation;
        Profile.moduleHash = new HashMap<Integer, ArrayList<Module>>();
        deadlines = new DeadlineList();
//        filteredDeadlines = new FilteredList<>(deadlines.getDeadlineList());
    }

    /**
     * Adds a module to the hashmap with the key being the semester
     */
    public static void addModule(Integer semester, Module module) {
        if (!moduleHash.isEmpty() && moduleHash.containsKey(semester)) {
            moduleHash.get(semester).add(module);
        } else {
            ArrayList<Module> semesterList = new ArrayList<Module>();
            semesterList.add(module);
            moduleHash.put(semester, semesterList);
        }
    }

    public Name getName() {
        return name;
    }

    public static Name getStaticName() {
        return name;
    }

    public CourseName getCourseName() {
        return courseName;
    }

    public static int getCurrentSemester() {
        return currentSemester;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public static void setName(Name name) {
        Profile.name = name;
    }

    public static void setCourse(CourseName courseName) {
        Profile.courseName = courseName;
    }

    public static void setCurrentSemester(int currentSemester) {
        Profile.currentSemester = currentSemester;
    }

    public static void setSpecialisation(String specialisation) {
        Profile.specialisation = specialisation;
    }

    public static ArrayList<Module> getModules(Integer semester) {
        return moduleHash.get(semester);
    }

    public static HashMap<Integer, ArrayList<Module>> getAllModules() {
        return moduleHash;
    }

    public Set<Map.Entry<Integer, ArrayList<Module>>> getMappings() {
        return moduleHash.entrySet();
    }

    public static HashMap<Integer, ArrayList<Module>> getHashMap() {
        return moduleHash;
    }

    public ObservableList<Deadline> getDeadlines() {
        List<Module> modules = moduleHash.get(currentSemester); // Deadlines should only be from the current semester
//        List<Deadline> deadlineList = new ArrayList<>();
        ObservableList<Deadline> temp = FXCollections.observableArrayList();
            System.out.println(modules.get(0).getPersonal().getObservableDeadlines());
        for (Module module: modules) {
            module.getPersonal().getDeadlines();
            temp.addAll(module.getPersonal().getObservableDeadlines());
        }
        filteredDeadlines = new FilteredList<>(temp);
        System.out.println("---->" + deadlines);
        return filteredDeadlines;
    }

    public ObservableList<Deadline> getFilteredDeadlines() {
        return filteredDeadlines;
    }

//    public ObservableList<Deadline> getAllDeadlines() {
//        List<Module> modules = moduleHash.get(currentSemester); // Deadlines should only be from the current semester
//        for (Module module: modules) {
//            deadlines.addAll(module.getPersonal().getObservableDeadlines());
//        }
//
//        System.out.println("---->" + deadlines);
//        return deadlines;
//    }

    public List<Module> getModulesInSem(int sem) {
        return moduleHash.get(sem);
    }

    public int getModuleSemester(ModuleCode moduleCode) {
        return moduleHash.entrySet()
                .stream()
                .filter(entry->entry.getValue().stream().anyMatch(mod->mod.getModuleCode().equals(moduleCode)))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    public Module getModule(ModuleCode moduleCode) {
        return moduleHash.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(x->x.getModuleCode().equals(moduleCode))
                .findFirst().get();
    }

    /**
     * Returns true if a module with module code {@code moduleCode} exists in {@code moduleHash}.
     */
    public boolean hasModule(ModuleCode moduleCode) {
        return moduleHash.values()
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(x->x.getModuleCode().equals(moduleCode));
    }

    /**
     * Deletes a module with module code {@code moduleCode}.
     */
    public void deleteModule(ModuleCode moduleCode) {
        if (hasModule(moduleCode)) {
            int semester = getModuleSemester(moduleCode);
            Module modToDelete = getModule(moduleCode);
            moduleHash.get(semester).remove(modToDelete);
            return;
        }
        throw new NoSuchElementException();
    }


    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameProfile(Profile otherProfile) {
        if (otherProfile == this) {
            return true;
        }

        return otherProfile != null
                && otherProfile.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Profile)) {
            return false;
        }

        Profile otherProfile = (Profile) other;
        return otherProfile.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}
