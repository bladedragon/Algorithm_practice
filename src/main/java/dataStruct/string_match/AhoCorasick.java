package dataStruct.string_match;

import java.util.LinkedList;
import java.util.Queue;

public class AhoCorasick{
    private final static char CHARA = 'a';

    private final static int CHAR_LENGTH = 26;

    public class AcNode{
        public char data;
        public AcNode[] children = new AcNode[CHAR_LENGTH];
        public boolean isEndingChar;
        public int length;
        public AcNode fail;
        public AcNode(char data){
            this.data = data;
        }

        @Override
        public String toString(){
            final StringBuilder sb = new StringBuilder("AcNode{");
            sb.append("data=").append(data);
            sb.append("}");
            return  sb.toString();
        }
    }

    public AcNode root;


    /**
     * 添加节点
     * @param value
     */
    public void add(String value){
        char[] chars = value.toCharArray();

        AcNode p = root;

        for(int i =0; i < chars.length; i ++){
            int index = chars[i] - CHARA;

            if (p.children[index] ==null) {
                AcNode newNode = new AcNode(chars[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        //记录模式串的长度
        p.length = chars.length;
        p.isEndingChar = true;
    }



    public void BuildFailurePointer(){
        //Queue存储的是失败指针？
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while(!queue.isEmpty()){
            AcNode p = queue.remove();
            //遍历同一层的所有节点
            for(int i =0;i<26;i++){
                AcNode pc = p.children[i];
                if(pc == null){
                    continue;
                }
                if(pc == root){
                    pc.fail = root;
                }else{
                    AcNode q = p.fail;
                    while(q != null){
                        //判断p的子节点是不是等于q的子节点，如果是，说明还存在更大的最大公共子串
                        //如果不是，则就要向上面寻求次最大公共子串
                        AcNode qc = q.children[pc.data-'a'];
                        if(qc != null){
                            pc.fail = qc;
                            break;
                        }else{
                            //递归
                            q = q.fail;
                        }
                    }
                    //如果不存在最大公共子串，失败指针就指向root
                    if(q == null){
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }


    public void match(char[] text){
        int n = text.length;
        AcNode p = root;
        for(int i = 0; i < n; ++i){
            int idx = text[i] - 'a';
            while(p.children[idx] == null && p != root){
                //如果不相等就跳到失败指针位置
                p = p.fail;
            }
            p = p.children[idx];
            //如果没有匹配从root重新开始匹配
            if(p == null){
                p = root;
            }
            AcNode tmp = p;
            //打印可以匹配的模式串
            while(tmp != root){
                if(tmp.isEndingChar == true){
                    int pos = i -tmp.length+1;
                    System.out.println("匹配起始下标"+pos + ";长度"+tmp.length);
                }
                tmp = tmp.fail;
            }
        }

    }

    public void printFailNode(){
        AcNode node = root;
        Queue<AcNode> queue = new LinkedList<>();
        queue.add(root);
        AcNode nodeItem = null;
        while( !queue.isEmpty()){
            nodeItem = queue.remove();
            if(nodeItem != null){
                AcNode[] childrens =  nodeItem.children;
                for(int i =0;i < childrens.length; i++){
                    if(childrens[i] != null){
                        queue.add(childrens[i]);
                    }
                }
            }
            System.out.println("当前节点是"+nodeItem.data+".失败节点是"+nodeItem.fail);
        }
    }
}
