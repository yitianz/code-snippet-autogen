package triegen;

import java.util.HashMap;
import java.util.stream.Stream;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode("");
    }

    public void add(String word) {
        TrieNode curr = root;
//        HashMap<TrieNode, Integer> curr = new HashMap<>();
//        curr.put(this.root, 0);
//        Stream.iterate(curr, curr.ge = curr.getChildren().computeIfAbsent(word.charAt(i), TrieNode::new));
//        Stream.of(word.toCharArray()).forEachOrdered(curr = curr.getChildren().computeIfAbsent(c, TrieNode::new));
        for (char c: word.toCharArray()) curr = curr.getChildren().computeIfAbsent(c, TrieNode::new);
    }
}