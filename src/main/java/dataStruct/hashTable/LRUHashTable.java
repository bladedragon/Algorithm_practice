package dataStruct.hashTable;

import java.util.HashMap;

/**
 * 基于散列表的LRU算法
 * @param <K>
 * @param <V>
 */
public class LRUHashTable<K,V> {

    /**
     * 默认长度
     */
    private final static Integer DEFAULT_INITIAL_CAPACITY = 10;
    /**
     * 头节点
     */
    private DNode<K,V> headNode;
    /**
     * 尾节点
     */
    private DNode<K,V> tailNode;
    /**
     * 链表长度
     */
    private Integer length;
    /**
     * 链表最大容量
     */
    private Integer capacity;
    /**
     * 散列表存Key
     */
    private HashMap<K,DNode<K,V>> table;

    public LRUHashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public LRUHashTable(int capacity){
        this.capacity = capacity;
        this.length = 0;

        headNode = new DNode<>();
        tailNode = new DNode<>();

        //为什么只要两条？而不是四条？感觉是因为只需要将链表连接起来就行
        headNode.next = tailNode;
        tailNode.prev = headNode;
        table = new HashMap<>();
    }

    public void add(K key,V value){

        //先查找hash索引
        DNode<K,V> node = table.get(key);
        if(null == node){
            DNode newNode = new DNode(key,value);
            table.put(key,newNode);
            addNode(newNode);

            //LRU缓存淘汰的精髓
            if(length++ > capacity){
                DNode tail = popTail();
                table.remove(tail.key);
                length--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    public V get(K key){
        DNode<K,V> node = table.get(key);
        if(node == null){
            return null;
        }
        moveToHead(node);
        return node.value;
    }


    //将节点移动到头部
    private void moveToHead(DNode<K,V> node) {
        removeNode(node);
        addNode(node);
    }

    private DNode popTail() {
        DNode<K,V> node = tailNode.prev;
        removeNode(node);
        return node;

    }

    private void removeNode(DNode<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //将newNode放在headNodd的后面
    private void addNode(DNode newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    public void remove(K key){
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        length--;
        table.remove(node.key);
    }


    class DNode<K, V> {

        private K key;
        private V value;
        private DNode prev;
        private DNode next;

        DNode(){}

        DNode(K key,V value){
            this.key = key;
            this.value = value;
        }
    }
}
