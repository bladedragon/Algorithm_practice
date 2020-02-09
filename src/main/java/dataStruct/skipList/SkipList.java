package dataStruct.skipList;

import java.util.Random;

public class SkipList {

    final static int MAX_LEVEL = 16;
    final static float SKILLIST_P = 0.5f;

    private int levelCount = 1;
    private Node head = new Node(MAX_LEVEL);
    private Random r =new Random();

    public  Node find(int value){
        Node p = head;
        for(int i =0;i<levelCount;i++){
            while(p.forwards[i] != null && p.forwards[i].data<value){
                p = p.forwards[i];
            }
        }

        if(p.forwards[0] != null && p.forwards[0].data == value){
            return p;
        }
        return null;
    }

    public  void insert(int value){
        int level = randomLevel();
        Node newNode = new Node(level);
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];

        if(level > levelCount){
            levelCount = level;
        }

        for(int i =0;i<level;i++){
            update[i] = head;
        }

        //获取节点的每一层次的下一节点
        Node p = head;
        //注意这里的遍历顺序,必须是从最上面一层开始遍历，才能起到索引作用
        for(int i =level-1 ;i>= 0;i--){
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //插入节点，类似于链表操作(多层次的插入操作)
        //注意这里是针对一个节点，可以直接level次遍历
        for(int i = 0;i<level;i++){
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
    }

    public void insert2(int value){
        int level = head.forwards[0] == null? 1: randomLevel();

        //更新levelCount和level 只更新一层
        //减少随机性带来的额外维护
        if(level > levelCount){
            level = ++levelCount;
        }

        Node newNode = new Node(level);
        newNode.data = value;
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        Node p =head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }
            // level应该还是等于levelCount
            //TODO：level为什么会大于levelCount
            if (level > i) {
                update[i] = p;
            }
        }

        //和原来一样
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }
    }

    public void insert3(int value){

        int level =head.forwards[0] == null? 1: randomLevel();
        if (level > levelCount) {
            level = ++levelCount;
        }

        Node newNode = new Node(level);
        newNode.data = value;
        Node p = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                // 找到前一节点
                p = p.forwards[i];
            }

            // 这里和之前不一样，实际上还是原来的插入逻辑，只是多增加了边缘条件
            if (level > i) {
                if (p.forwards[i] == null) {
                    p.forwards[i] = newNode;
                } else {
                    Node next = p.forwards[i];
                    p.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }


    public void delete(int value){
        //保存节点搜索的路径
        Node update[] = new Node[levelCount];
        Node p = head;

        for(int i = levelCount-1;i>=0;i--){
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        //如果匹配到节点就进行删除操作
        //删除操作涉及到前后节点，因此遍历需要从上到下遍历
        if(p.forwards[0] != null && p.forwards[0].data == value){
            for(int i =levelCount-1 ;i>=0;i--){
                while(update[i].forwards[i] != null && update[i].forwards[i].data < value){
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
        while(levelCount >1 && head.forwards[levelCount] == null){
            levelCount--;
        }
    }

    //    private int randomLevel() {
//        int level = 1;
//        while (Math.random() < SKILLIST_P && level < MAX_LEVEL) {
//            level += 1;
//        }
//        return level;
//    }
    /**
     * 随机函数，防止伪随机
     * 遇到奇数层则+1
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }


    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }
    /**
     * 跳表节点
     */
   public class Node{
        private int data = -1;

        //表示每个层次的下一个节点
        private Node forwards[];

        //表示节点的最大层次
        private int maxLevel = 0;

        Node(int level){
            this.forwards = new Node[level];
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(8);
        list.insert(7);
        list.printAll_beautiful();
        list.printAll();
    }
}
