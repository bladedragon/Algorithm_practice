package dataStruct.LinkedList;

import dataStruct.LRU.Node;

public class isPalindrome {

    public boolean isPalindrome(Node list){
        Node slow = list;
        Node fast = list;
        Node prev = null;


        if(list == null){
            return false;
        }

        while(fast != null && fast.next != null){

            fast = fast.next.next;
            Node next = slow.next;

            slow.next = prev;
            prev = slow;
            //慢指针后移
            slow = next;
        }

        //跨过中点，使得prev和slow同步，这里存在节点数是奇数还是偶数的情况
        if(fast != null){
            slow = slow.next;
        }

        if(slow != null){
            if(slow.data != prev.data){
                return false;
            }

            slow = slow.next;
            prev = prev.next;
        }

        return true;

    }


}
