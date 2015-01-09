import java.util.ArrayList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {

        List<Integer> matches = new ArrayList<Integer>();

        if (pattern == null || pattern.length() == 0 || text == null) {

            throw new IllegalArgumentException();

        }

        if (text.length() == 0 || pattern.length() > text.length()) {

            return matches;

        }

        int[] last = buildLastTable(pattern);
        int i = pattern.length() - 1;
        int j = pattern.length() - 1;
        char textCharacter;

        while (i < text.length()) {
            //RELEASE THE SEARCH HOUNDS

            textCharacter = text.charAt(i);

            if (textCharacter == pattern.charAt(j)) {

                if (j == 0) {

                    matches.add(i);
                    i += pattern.length() - Math.min(j,
                            1 + last[textCharacter]);
                    j = pattern.length() - 1;


                } else {

                    i--;
                    j--;

                }

            } else {

                i += pattern.length() - Math.min(j, 1 + last[textCharacter]);
                j = pattern.length() - 1;

            }

        }

        return matches;

    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {

        if (pattern == null) {

            throw new IllegalArgumentException();

        }

        int[] lastTable = new int[Character.MAX_VALUE + 1];

        for (int i = 0; i < lastTable.length; i++) {

            lastTable[i] = -1;

        }

        for (int j = 0; j < pattern.length(); j++) {

            lastTable[pattern.charAt(j)] = j;

        }

        return lastTable;

    }

    @Override
    public int generateHash(CharSequence current, int length) {

        if (current == null || length < 0 || length > current.length()) {

            throw new IllegalArgumentException();

        }

        int hash = 0;

        for (int i = 0; i < length; i++) {

            hash += current.charAt(i) * power(BASE, (length - 1 - i));

        }

        return hash;

    }

    /**
     *
     * Private method to do exponentiation since Math.pow() overflows
     *
     * @param base number that is the base
     * @param exponent number that is the exponent
     * @return calculated value
     */
    private int power(int base, int exponent) {

        int value = base;

        if (exponent == 0) {

            return 1;

        }

        int i = 1;

        while (i < exponent) {

            value *= base;
            i++;

        }

        return value;

    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {

        int hash = oldHash; //hash created
        hash -= oldChar * power(BASE, length - 1); //oldChar hash removed
        hash *= BASE; //multiply by BASE
        hash += newChar * power(BASE, 0); //add newChar

        return hash;

    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {

        List<Integer> matches = new ArrayList<Integer>();

        if (pattern == null || pattern.length() == 0 || text == null) {

            throw new IllegalArgumentException();

        }

        if (pattern.length() == 0 || text.length() == 0
                || pattern.length() > text.length()) {

            return matches;

        }

        int needleHash = generateHash(pattern, pattern.length());
        int haystackHash = 0;
        int haystackIndex;
        int needleIndex;
        int matchIndex;

        for (int i = 0; i < pattern.length(); i++) { //create initial hash

            haystackHash += text.charAt(i)
                    * power(BASE, (pattern.length() - 1 - i));

        }

        for (int i = 0; i < text.length() - (pattern.length() - 1); i++) {
            //RELEASE THE SEARCH HOUNDS!

            if (needleHash == haystackHash) {

                boolean match = true;
                needleIndex = 0;
                haystackIndex = i;
                matchIndex = i;

                while (match && needleIndex < pattern.length()) {

                    if (pattern.charAt(needleIndex)
                            == text.charAt(needleIndex + haystackIndex)) {

                        match = true;
                        needleIndex++;

                    } else {

                        match = false;

                    }

                }

                if (match) {

                    matches.add(matchIndex);

                }

            }

            if (i + 1 < text.length() - pattern.length() + 1) {

                haystackHash = updateHash(haystackHash,
                        pattern.length(), text.charAt(i),
                        text.charAt(i + pattern.length()));

            }

        }

        return matches;

    }

}
