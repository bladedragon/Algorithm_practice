package dataStruct.LinkedList;


import dataStruct.LRU.Node;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 *
 *
 */
public class LinkedListAlg {


    /**
     * 链表反转  ——递归法（精髓）
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        if (head == null || head.next == null) return head;
        Node p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    /**
     * 单链表反转
     * @param list
     * @return
     */
    public static  Node reserve(Node list){
        Node cur = list;
        //存储链表的上一个节点，遍历完之后变成头节点
        Node pre = null;
        while(cur != null){
            //暂存指向下一个节点的引用
            Node next = cur.next;
            //反转，将存储的下一个节点指向上一个节点
            cur.next = pre;
            //下面两部将链表向下移动
            pre = cur;
            cur = next;
        }
        //遍历完之后pre变成头节点
        return pre;
    }

    //使用快慢指针法。追及问题，只要慢指针追上快指针，说明存在环路
    public static boolean checkCircle(Node list){
        if(list== null)
        {
            return false;
        }

        Node fast = list.next;
        Node slow = list;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }

        return false;
    }


    public static Node mergeSortList(Node la,Node lb){
             Node  p = la;
             Node q  =lb;
             Node head ;

             //处理头部的特殊情况
        if(p.data<q.data){
                 head = p;
                 p = p.next;

             }else{
                 head = q;
                 q = q.next;

             }

             Node r = head;

         while(q != null && p != null) {
             if (q.data < p.data) {
                 r.next = q;
                 q = q.next;
             } else {
                 r.next = p;
                 p = p.next;
             }
         }
         if(p.next == null){
             r.next = q;
         }

         if(q.next == null){
             r.next =p;
         }

         return r;

    }

    /**
     * 有序合并（哨兵机制优化）
     * @param la
     * @param lb
     * @return
     */

    public static Node mergeTwoListsA(Node la,Node lb){
        Node soldier =  new Node(0,null);
        Node p = soldier;


        //正常的合并逻辑

        return p;

    }

    /**
     * 递归合并链表
     * @param l1
     * @param l2
     * @return
     */
        public Node mergeTwoLists(Node l1, Node l2) {
            if (l1 == null) {
                return l2;
            }
            else if (l2 == null) {
                return l1;
            }
            else if (l1.data < l2.data) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

        }



    //快指针比慢指针前k个节点，然后快指针遍历到尾部的时候慢指针刚刚好到删除节点，注意保存删除节点的上一个节点
    private  void deleteLastKth(Node list,int index){
        Node fast = list;
        //注意这里的i！！！！
        int i = 1;

        while(fast != null && i<index){
            fast = fast.next;
            i++;
        }
        if(fast == null){
            return ;
        }

        Node slow = list;
        Node prev = null;
        //遍历到最后fast指针指向最后一个节点，注意快慢指针之间的间隔
        //因为选择使用prev作为删除节点，所以next !=null
        while(fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if(prev != null){
            prev.next = prev.next.next;
        }else{
            list = list.next;
        }
        return ;
    }

    private static Node fndMiddleNode(Node list ){
        Node fast = list;
        Node slow = list;

        //第一个条件是防止head为null，第二个是为了使得fast指针遍历到最后一个节点
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }



    public static void main(String[] args) {
            Node node = new Node(1,new Node(2,new Node(3,new Node(4,new Node(5,null)))));
            LinkedListAlg linkedListAlg = new LinkedListAlg();
            linkedListAlg.deleteLastKth(node,2);
            while(node != null){
                System.out.println(node.data);
                node = node.next;
            }

    }




}
