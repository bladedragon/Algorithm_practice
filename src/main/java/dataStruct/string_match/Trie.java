package dataStruct.string_match;

public class Trie {
    private TrieNode root;


    public void insert(char[] text){
        TrieNode p = root;
        for(int i=0;i<text.length;i++){
            int index = text[i] - 'a';
            if(p.children[index] == null){
                p.children[index] = new TrieNode(text[i]);
                p.children[index].isEndingChar = true;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
}

    public boolean find(char[] patten){
        TrieNode p = root;
        for(int i=0;i<patten.length;i++){
            int index = patten[i] - 'a';
            if(p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        if(p.isEndingChar){
            return true;
        }else{
            return false;
        }

    }


    class TrieNode{
        char data;
        TrieNode[] children ;
        boolean isEndingChar = false;

        TrieNode(char data){
            this.data = data;
            this.children = new TrieNode[26];
        }

    }


}
