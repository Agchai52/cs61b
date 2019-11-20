import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("n"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("ab"));
    }

    @Test
    public void testIsPalindromeOffOne() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertFalse(palindrome.isPalindrome("ac", offByOne));
    }

    @Test
    public void testIsPalindromeOffN() {
        CharacterComparator offByN = new OffByN(5);
        assertTrue(palindrome.isPalindrome("af", offByN));
        assertFalse(palindrome.isPalindrome("hf", offByN));

    }
}
//Uncomment this class once you've created your Palindrome class. */