package triegen;

import java.util.Arrays;

public class TrieGen {
    public static Trie generateTrie(String[] initialWords) {
        Trie characterTrie = new Trie();
        Arrays.stream(initialWords).sequential().forEach(characterTrie::add);
        return characterTrie;
    }
}