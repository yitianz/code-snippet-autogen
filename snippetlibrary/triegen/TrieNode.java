package triegen;

import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private String content;
    private boolean isWord;

    public TrieNode(String content) {
        this.content = content;
    }

    public TrieNode(Character content) {
        this.content = String.valueOf(content);
    }

    public TrieNode(HashMap<Character, TrieNode> children, String content, boolean isWord) {
        this.children = children;
        this.content = content;
        this.isWord = isWord;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}