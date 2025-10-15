package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsAnyKeywordsPredicate firstPredicate = 
            new NameContainsAnyKeywordsPredicate(firstPredicateKeywordList);
        NameContainsAnyKeywordsPredicate secondPredicate = 
            new NameContainsAnyKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsAnyKeywordsPredicate firstPredicateCopy = 
            new NameContainsAnyKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsAnyKeywordsPredicate predicate = new NameContainsAnyKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsAnyKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsAnyKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsAnyKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsAnyKeywordsPredicate predicate = new NameContainsAnyKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsAnyKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = 
            new NameContainsAnyKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
            assertFalse(predicate.test(new PatientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsAnyKeywordsPredicate predicate = new NameContainsAnyKeywordsPredicate(keywords);

        String expected = NameContainsAnyKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
