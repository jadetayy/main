package seedu.address.model.profile.course;

import java.util.List;

import seedu.address.model.profile.course.module.ModularCredits;
import seedu.address.model.profile.course.module.ModuleCode;



/**
 * Represents a Course's requirement.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CourseRequirement {

    private final String requirementName;
    private final List<ModuleCode> modules;
    private final ModularCredits modularCredits;

    /**
     * Every field must be present and not null.
     */
    public CourseRequirement(String requirementName, List<ModuleCode> modules, ModularCredits modularCredits) {
        this.requirementName = requirementName;
        this.modules = modules;
        this.modularCredits = modularCredits;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(requirementName);
        for (ModuleCode moduleCode : modules) {
            output.append("\n");
            output.append(moduleCode);
        }
        output.append("\n");
        return output.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CourseRequirement // instanceof handles nulls
                && requirementName.equals(((CourseRequirement) other).requirementName)); // state check
    }
}
