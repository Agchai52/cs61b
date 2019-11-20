public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dequeChar = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dequeChar.addLast(word.charAt(i));
        }
        return dequeChar;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int size = word.length();
        if (size == 0 || size == 1) {
            return true;
        }
        for (int i = 0; i < size / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(size-i-1))) {
                return false;
            }
        }
        return true;

    }

    public boolean isPalindrome(String ss) {
        Deque<Character> deque = wordToDeque(ss);
        int sizeOfSs = deque.size();
        if (sizeOfSs == 0 || sizeOfSs == 1) {
            return true;
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        /*for (int i = 0; i < sizeOfSs / 2; i++) {
            if (ss.charAt(i) != ss.charAt(sizeOfSs-i-1)) {
                return false;
            }
        }*/
        return true;
    }
}
