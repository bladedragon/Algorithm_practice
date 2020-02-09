package dataStruct.hashTable;

import java.util.HashMap;

/**
 * @Description 散列表的实现
 */
public class HashTable<K,V> {
    /**
     * 默认长度
     */
    private static final int DEFAUlT_INITIAL_CAPACITY = 8;
    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75f;
    /**
     * 初始化散列表数组
     */
    private Entry<K,V>[] table;
    /**
     * 实际长度
     */
    private int size = 0;
    /**
     * 散列表索引数量
     */
    private int use = 0;

    public HashTable(){
        table=  (Entry<K,V>[]) new Entry[DEFAUlT_INITIAL_CAPACITY];
    }

    static class Entry<K,V>{
        K key;
        V value;
        Entry<K,V> next;
        Entry(K key, V value,Entry<K,V> entry){
            this.key = key;
            this.value = value;
            this.next = entry;
        }
    }

    HashMap hashMap = new HashMap();

    /**
     * 新增
     * @param key
     * @param value
     */
    public void put(K key,V value){
        //获取hash值
        int index = hash(key);

        //如果不存在hash索引
        if(table[index] == null){
            //添加哨兵
            table[index] = new Entry<>(null,null,null);
        }

        Entry<K,V> tmp = table[index];

        if(tmp.next == null){
            tmp.next = new Entry<>(key,value,null);
            //实际元素数量
            size++;
            use++;
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
            //说明存在hash冲突
        }else{
            while(tmp.next != null){
                if(key == tmp.key){
                    tmp.value = value;
                    return;
                }
                tmp = tmp.next;
            }
            //把元素放到链表头部
            Entry<K,V> temp = table[index].next;
            table[index].next = new Entry<>(key,value,temp);
            size++;
        }
    }

    /**
     * 扩容
     */
    private void resize() {
        Entry<K,V>[] oldTable = table;
        table  = (Entry<K,V>[]) new Entry[table.length*2];
        use = 0;
        for(int i =0;i<oldTable.length;i++){
            if(oldTable[i] ==null || oldTable[i].next ==null){
                continue;
            }
            Entry<K,V> entry = oldTable[i];
            while(entry.next != null){
                entry = entry.next;
                int index = hash(entry.next);
                //如果不存在hash映射
                if(table[index] ==null){
                    use++;
                    //添加哨兵节点
                    table[index] = new Entry<>(null,null,null);
                }
                //添加节点到链表的头部，哨兵的next节点指向新节点，妙！
                table[index].next = new Entry<>(entry.key,entry.value,table[index].next);
            }
        }
    }

    public void remove(K key){
        int index = hash(key);
        Entry entry = table[index];
        //如果不存在该hash索引
        if(entry == null || entry.next == null){
            return ;
        }


        //必须通过前驱节点来删除链表的值
        Entry pre;
        Entry<K,V> headNode = table[index];

        do{
            pre = entry;
            entry = entry.next;
            if(entry.key == key){
                pre.next = entry.next;
                size --;
                //如果散列表里只有一个元素，存疑，不应该放在while循环里面
                if(headNode.next == null){
                    use--;
                }
                return;
            }
            //遍历链表
        }while(entry.next != null);

    }


    public V get(K key){
        int index = hash(key);
        //前提是头节点是哨兵节点，因此next == null 也算条件之一
        if(table[index] == null || table[index].next == null){
            return null;
        }
        Entry<K,V> entry = table[index];
        while(entry.next != null){
            entry = entry.next;
            if(entry.key == key){
                return entry.value;
            }
        }
        return null;
    }
    private int hash(Object key){
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;

    }

}
