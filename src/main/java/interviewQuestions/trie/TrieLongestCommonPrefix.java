package interviewQuestions.trie;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        this.children = new TrieNode[TrieLongestCommonPrefix.noOfChars];

        for(int i = 0; i < TrieLongestCommonPrefix.noOfChars; ++i) {
            this.children[i] = null;
        }

        this.isEndOfWord = false;
    }
}

public class TrieLongestCommonPrefix {
    static int noOfChars = 26;
    static TrieNode root;
    static int prefixIndex;

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    static void insertIntoTrie(String str) {
        TrieNode head = root;

        for(int i = 0; i < str.length(); ++i) {
            int index = str.charAt(i) - 97;
            if (head.children[index] == null) {
                head.children[index] = new TrieNode();
            }

            head = head.children[index];
        }

        head.isEndOfWord = true;
    }

    static int countNumberOfChild(TrieNode currNode) {
        int count = 0;

        for(int i = 0; i < noOfChars; ++i) {
            if (currNode.children[i] != null) {
                ++count;
                prefixIndex = i;
            }
        }

        return count;
    }

    static String findPrefix() {
        TrieNode head = root;

        String prefixStr;
        for(prefixStr = ""; countNumberOfChild(head) == 1 && !head.isEndOfWord; head = head.children[prefixIndex]) {
            prefixStr = prefixStr + (char)(97 + prefixIndex);
        }

        return prefixStr;
    }

    static String longestCommonPrefix(String[] strs) {
        root = new TrieNode();
        for (String str : strs) {
            System.out.print(str + " ");
            insertIntoTrie(str);
        }

        System.out.println();
        return findPrefix();
    }

}

