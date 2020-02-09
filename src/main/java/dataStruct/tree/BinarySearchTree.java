package dataStruct.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 */
public class BinarySearchTree {
    private Node tree;

    public Node find(int data){

        Node p = tree;
        while(p != null){
            if(p.data < data){
                p = p.right;
            }else if(p.data > data){
                p = p.left;

            }else{
                return p;
            }
        }
        return null;
    }

    /**
     * 插入操作，暂不支持重复数据的插入操作
     * 如果要支持重复数据的插入，只要在null判断的最后在写进一步的逻辑就行
     * @param data
     */
    public void insert(int data){
        if(tree == null){
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while(tree != null){
            if(data > p.data){
                if(p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else if(data < p.data){
                if(p.left == null){
                    p.left = new Node(data);
                    return ;
                }
                p = p.left;
            }
        }
        return ;
    }

    /**
     * 删除操作
     *
     * @param data
     * @return
     */
    public Node delete(int data){
        Node p = tree;
        Node pp = null;
        if(p == null){
            return null;
        }
        //首先遍历找到对应节点
        while(p != null){

            if(p.data > data){
                pp = p;
                p = p.left;
            }else if(p.data < data){
                pp = p;
                p = p.right;
            }else{
                //如果加入循环条件，那么pp的赋值就可以抽离出来
                break;
            }
        }
        //判断是否存在双子节点
        if(p.left != null && p.right != null){

            Node minP = p.right;
            Node minPP = p;
            while(minP != null){
                if(minP.left != null){
                    minPP = minP;
                    minP = minP.left;
                }
                //p点节点转换成右子树的最小值
                p.data = minP.data;
                //将问题转换成删除右子树的最左节点（因为出现了重复，，所以要把重复的节点删除）
                // （即将双节点都存在问题转换成单节点存在问题）
                p = minP;
                pp = minPP;
            }
        }

        //获取更新的节点
        //判断存在单节点和无节点的处理
        Node child;
        if(p.left != null){
            child = p.left;
        }else if(p.right !=null){
            child= p.right;
        }else{
            child = null;
        }

        //通过父节点来删除该节点
        if(pp == null){
            tree = child;
        }else if(pp.left == p){
                pp.left = child;
        }else{
            pp.right = child;
        }
        return p;
    }

    /**
     * 查找最小值
     * @return
     */
    public Node findMin(){
        Node p = tree;
        if(tree == null){
            return null;
        }
        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    /**
     * 求二叉树的宽度
     * 采用队列法
     * @return
     */
    public int widthOfBinaryTree(){
        if(tree == null){
            return 0;

        }
        //通过linkedList实现的queue
        Queue<Node> queue = new LinkedList<>();
        queue.offer(tree);
        //存储当前队列里的元素个数，相当于队列长度
        int qlength = 1;
        //存储最大的宽度
        int maxWidth = 1;
        /*
        二层while 时间复杂度太高
         */
//        while(!queue.isEmpty()){
//            while(qlength-- != 0){
//                Node node = queue.poll();
//
//                if(node.left != null){
//                    queue.offer(node.left);
//
//                }else if(node.right != null){
//                    queue.offer(node.right);
//
//                }
//            }
//        qlength = queue.size();
//            maxWidth = Math.max(qlength,maxWidth);
//        }

        while(!queue.isEmpty()){
            //减掉上一层的节点，同时也是遍历生成新节点
            qlength--;
            //队列清空，说明一层的节点都已经遍历完成
            if(qlength == 0){
                //重新设置该层的节点数
                qlength = queue.size();
                //当前节点数和往期节点的最宽值
                maxWidth = Math.max(qlength,maxWidth);
            }
            //必须放在设置最大值的后面
            Node node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){
                queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    /**
     * 树节点
     */
    public static class Node{
        private int data;
        private Node left;
        private Node right;

        Node(int data){
            this.data = data;
        }
    }


}
