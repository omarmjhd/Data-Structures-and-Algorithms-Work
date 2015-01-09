import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Iterator;

public class Huffman {

    // Do NOT add any variables (instance or static)

    /**
     * Builds a frequency map of characters for the given string.
     *
     * This should just be the count of each character.
     * No character not in the input string should be in the frequency map.
     *
     * @param s the string to map
     * @return the character -> Integer frequency map
     */
    public static Map<Character, Integer> buildFrequencyMap(String s) {

        if (s == null) {

            throw new IllegalArgumentException();

        }

        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        char[] characters = s.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            if (freqMap.get(characters[i]) == null) {

                freqMap.put(characters[i], 1);

            } else {

                int value = freqMap.get(characters[i]);

                freqMap.put(characters[i], value + 1);

            }

        }

        return freqMap;
    }

    /**
     * Build the Huffman tree using the frequencies given.
     *
     * Add the nodes to the tree based on their natural ordering (the order
     * given by the compareTo method).
     * The frequency map will not necessarily come from the
     * {@code buildFrequencyMap()} method. Every entry in the map should be in
     * the tree.
     *
     * @param freq the frequency map to represent
     * @return the root of the Huffman Tree
     */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {

        if (freq == null) {

            throw new IllegalArgumentException();

        }

        PriorityQueue<HuffmanNode> pQueue = new PriorityQueue<HuffmanNode>();

        Set<Character> keySet = freq.keySet();

        for (Character i: keySet) { //pq made with each huffman node

            Integer value = freq.get(i);

            HuffmanNode node = new HuffmanNode(i, value);

            pQueue.add(node);

        }

        while (pQueue.size() > 1) {

            HuffmanNode newHuffmanNode =
                    new HuffmanNode(pQueue.poll(), pQueue.poll());

            pQueue.add(newHuffmanNode);

        }

        return pQueue.poll();

    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree.
     *
     * The tree provided will be a valid huffman tree but may not come from the
     * {@code buildHuffmanTree()} method.
     *
     * @param tree the root of the Huffman Tree
     * @return the map of each character to the encoding string it represents
     */
    public static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree) {

        if (tree == null) {

            throw new IllegalArgumentException();

        }

        /*if (tree.getLeft() == null && tree.getRight() == null) {

            return null; //THIS SHOULDNT RETURN NULL, COULD BE A ONE NODE TREE

        }*/

        HashMap<Character, EncodedString> encMap =
                new HashMap<Character, EncodedString>();

        buildEncodingMapRecursive(tree, new EncodedString(), encMap);

        return encMap;

    }

    /**
     *
     * Private helper method to recursively build the encoding map
     *
     * @param node the root node of the map being created
     * @param encodedString the initial encoded string
     * @param encodedStringMap the map of the encoded strings
     */
    private static void buildEncodingMapRecursive(HuffmanNode node,
        EncodedString encodedString, Map<Character,
            EncodedString> encodedStringMap) {


        if (node.getLeft() == null && node.getRight() == null
                && node.getCharacter() == 0) {

            System.out.println("ZERO");
            encodedString.zero(); //need a zero if only one character
            encodedStringMap.put(node.getCharacter(), encodedString);

        }

        if (node.getLeft() == null && node.getRight() == null) {

            encodedStringMap.put(node.getCharacter(), encodedString);

        }

        if (node.getLeft() != null) {

            EncodedString newEncodedString = new EncodedString();

            newEncodedString.concat(encodedString);
            newEncodedString.zero();

            buildEncodingMapRecursive(node.getLeft(),
                    newEncodedString, encodedStringMap);

        }

        if (node.getRight() != null) {

            EncodedString newEncodedString = new EncodedString();

            newEncodedString.concat(encodedString);
            newEncodedString.one();

            buildEncodingMapRecursive(node.getRight(),
                    newEncodedString, encodedStringMap);

        }

    }



    /**
     * Encode each character in the string using the map provided.
     *
     * If a character in the string doesn't exist in the map ignore it.
     *
     * The encoding map may not necessarily come from the
     * {@code buildEncodingMap()} method, but will be correct for the tree given
     * to {@code decode()} when decoding this method's output.
     *
     * @param encodingMap the map of each character to the encoding string it
     * represents
     * @param s the string to encode
     * @return the encoded string
     */
    public static EncodedString encode(Map<Character, EncodedString>
            encodingMap, String s) {

        if (encodingMap == null || s == null) {

            throw new IllegalArgumentException();

        }

        EncodedString encodedString = new EncodedString();

        for (char key : s.toCharArray()) {

            EncodedString forThisChar = encodingMap.get(key);

            if (forThisChar != null) {

                encodedString.concat(forThisChar);

            }

        }

        return encodedString;
    }

    /**
     * Decode the encoded string using the tree provided.
     *
     * The encoded string may not necessarily come from {@code encode()}, but
     * will be a valid string for the given tree.
     *
     * (tip: use StringBuilder to make this method faster -- concatenating
     * strings is SLOW.)
     *
     * @param tree the tree to use to decode the string
     * @param es the encoded string
     * @return the decoded string
     */
    public static String decode(HuffmanNode tree, EncodedString es) {

        if (tree == null || es == null) {

            throw new IllegalArgumentException();

        }

        StringBuilder decodedString = new StringBuilder();

        HuffmanNode currentNode = tree;

        Iterator<Byte> iterator = es.iterator();

        while (iterator.hasNext()) {

            Byte next = iterator.next();

            if (next == 0) {

                if (currentNode.getCharacter() != 0) {

                    decodedString.append(currentNode.getCharacter());
                    currentNode = tree;

                }

                currentNode = currentNode.getLeft();

            } else {

                currentNode = currentNode.getRight();

            }

            if (currentNode.getLeft() == null
                    && currentNode.getRight() == null) {

                decodedString.append(currentNode.getCharacter());

                currentNode = tree;

            }

        }

        return decodedString.toString();

    }


}
