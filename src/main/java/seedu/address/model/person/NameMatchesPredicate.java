package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Name} matches the entire keyword given.
 */
public class NameMatchesPredicate implements Predicate<Patient>{
    private final String keyword;

    public NameMatchesPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Patient patient) {
        return StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameMatchesPredicate)) {
            return false;
        }

        NameMatchesPredicate otherNameContainsKeywordsPredicate = (NameMatchesPredicate) other;
        return keyword.equals(otherNameContainsKeywordsPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
