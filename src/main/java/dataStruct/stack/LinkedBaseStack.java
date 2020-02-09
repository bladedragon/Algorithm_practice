package dataStruct.stack;



public class LinkedBaseStack {
    private int size;
    private Node top;

    public Node createNode(String value,Node next){
        return new Node(value,next);
    }

    public void clear(){
        this.top = null;
        this.size = 0;
    }
    public void push(String value){
        Node newNode = createNode(value,this.top);
        this.top = newNode;
        size++;

    }

    public String pop(){
        Node popNode = this.top;
        if(popNode == null){
            System.out.println("Stack is empty");
            return null;
        }

        this.top = top.next;
        if(this.size >0){
            this.size--;
        }

        return popNode.data;

    }

    public String getTopData(){
        if(this.top == null){
            return null;
        }
        return this.top.data;
    }

    public int size(){
        return this.size;
    }

    public void printAll(){
        System.out.println("Print Stack:");
        Node currNode =this.top;
        while(currNode != null){
            String data = currNode.data;
            System.out.println(data+"\t");
            currNode = currNode.next;
        }

        System.out.println();
    }

            public static void main(String[] args) {
                LinkedBaseStack stack = new LinkedBaseStack();
            stack.push("A");
            stack.push("B");
            stack.push("C");
            stack.pop();
            stack.push("D");
            stack.push("E");
            stack.pop();
            stack.push("F");
            stack.printAll();

        String data = stack.getTopData();
        System.out.println("Top data == " + data);
        }

    /**
     * 静态内部类
     */
    public static class Node{



        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        private String data;
        private Node next;

        Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
    }
}




