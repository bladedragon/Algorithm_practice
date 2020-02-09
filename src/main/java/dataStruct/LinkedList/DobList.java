package dataStruct.LinkedList;

public class DobList {
    private DuoNode head;
    private DuoNode tail;
    private int count;

    DobList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    private void insertToHead(int data){
        DuoNode node = new DuoNode(data);
        DuoNode prev = head;
        if(count == 0){
            head = node;
            tail = node;
        }else{
            prev.prev = node;
            node.next = prev;
            head = node;
        }
        ++count;
    }


    private void insertToTail(int data){
        DuoNode node = new DuoNode(data);
        DuoNode last = tail;
        if(count == 0){
            head = node;
            tail = node;

        }else{
            last.next = node;
            node.prev = last;
            tail = node;
        }
        ++count;
    }

    private void insertToIndex(int index,int data){
        DuoNode node = new DuoNode(data);

        if(index > count){
            return;
        }else{
            DuoNode resNode = findIndex(index);
            if(resNode != null) {
                DuoNode prev = resNode.prev;
                prev.next = node;
                node.next = resNode;
                node.prev = prev;
                resNode.prev = node;
            }
            ++count;
        }
    }

    private DuoNode deleteHead(){
        DuoNode h = head;
        if(count >0){
            //两句话最好不要反着来
            head = h.next;
            head.prev = null;
            --count;
        return h;
        }
        return null;
    }

    private DuoNode deleteTail() {
        DuoNode q = tail;
        if (count > 0) {
            tail = tail.prev;
            tail.next = null;
            --count;
        }
        return q;
    }

    private DuoNode deleteToIndex(int index){
        if(index >count){
            return null;
        }

        if(index ==0){
            deleteHead();
        }else if(index == count-1){
            deleteTail();
        }else{
            DuoNode resNode = findIndex(index);
            if(resNode !=null){
                DuoNode prev = resNode.prev;
                DuoNode next = resNode.next;
                prev.next = resNode.next;
                next.prev = prev;
                resNode.prev = null;
                resNode.next = null;
            }
            --count;
            return resNode;

        }
            return null;
    }

    private DuoNode findIndex(int index) {
        if(index >count){
            return null;
        }
        DuoNode node = head;
        int num = 0;
        while(num < index) {
            node = node.next;
            ++num;
        }
            return node;
    }

    private void printAll() {
        DuoNode duoNode= head;
        while (null != duoNode) {
            System.out.print(duoNode.data + "->");
            duoNode = duoNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DobList dulLinkList = new DobList();
        dulLinkList.insertToHead(3);
        dulLinkList.insertToHead(2);
        dulLinkList.insertToHead(1);
        dulLinkList.insertToTail(5);
        dulLinkList.insertToTail(6);
        dulLinkList.insertToTail(7);
        dulLinkList.insertToTail(8);
        dulLinkList.insertToTail(9);
        dulLinkList.printAll();
        System.out.println(dulLinkList.count);
        System.out.println("删除头部,并返回");
        DuoNode resHead = dulLinkList.deleteHead();
        System.out.println("返回值：" + resHead.data);
        dulLinkList.insertToIndex(3, 4);
        dulLinkList.printAll();
        System.out.println("删除尾部,并返回");
        DuoNode resTail = dulLinkList.deleteTail();
        System.out.println("返回值：" + resTail.data);
        dulLinkList.printAll();
        int data = 5;
//        int res = dulLinkList.indexOf(data);
//        System.out.println("数据" + data + "的下标 " + (res != -1 ? res : "不存在"));
        int delData = 5;
        dulLinkList.deleteToIndex(delData);
        dulLinkList.printAll();
    }

}
