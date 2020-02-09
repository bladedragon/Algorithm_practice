package dataStruct.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    /**
     * 顶点个数
     */
    private int v;
    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];  //邻接表

    public Graph(int v){

        this.v = v;
        adj = new LinkedList[v];
        for (int i=0 ;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边
     *
     * @param s 顶点
     * @param t 顶点
     */
    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);

        adj[t].add(s);

    }


    /**
     * 广度优先遍历
     * @param s
     * @param t
     */
    public void bfs(int s, int t){
        if(s == t){
            return ;
        }
        boolean[] visited = new boolean[v];
        visited[s] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        int[] prev = new int[v];
        for(int i = 0;i<v;i++){
            prev[i] = -1;
        }
        while(queue.size() != 0){
            int w = queue.poll();
            //遍历以w位节点的链表
            for(int i =0;i< adj[w].size();i++){
                int q = adj[w].get(i);
                if(!visited[q]){
                    prev[q] = w;
                    if(q == t){
                        printThis(prev,s,t);
                        return;
                    }
                    queue.offer(q);
                    visited[q] = true;
                }
            }
        }
    }

    public void dfs(int s,int t){
        boolean[] visited = new boolean[v];
        visited[s] = true;
        int[] prev = new int[v];
        for(int i=0; i< v; i++){
            prev[i] = -1;
        }
        recruitDfs(s,t,visited,prev);
        printThis(prev,s,t);
    }


    boolean found = false;

    private void recruitDfs(int w, int t, boolean[] visited, int[] prev) {
        if(found == false){
           return;
        }
        visited[w] = true;

        //递归结束条件
        if(w == t){
            found = true;
            return;
        }

        for(int i =0;i<adj[w].size();i++){
            int q = adj[w].get(i);
            if(!visited[q]){
                visited[q] =true;
                prev[q] = w;
                recruitDfs(q,t,visited,prev);
            }
        }


    }


    private void printThis(int[] prev, int s, int t) {
        if(prev[t] != -1 && s != t){
            printThis(prev,s,prev[t]);
        }
        System.out.println(t+" ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
//        dataStruct.graph.bfs(0,6);

        // 深度优先
        graph.dfs(0, 6);

    }

}
