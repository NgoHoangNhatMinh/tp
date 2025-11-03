package seedu.hospital.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.hospital.commons.util.StringUtil;
import seedu.hospital.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsAnyKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public NameContainsAnyKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsAnyKeywordsPredicate)) {
            return false;
        }

        NameContainsAnyKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsAnyKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
