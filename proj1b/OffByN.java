public class OffByN implements CharacterComparator {
    private int offSet;

    public OffByN(int N) {
        offSet = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == -offSet || diff == offSet;
    }
}
