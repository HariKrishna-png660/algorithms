import java.util.*;
class Edge {
    int u;
    int v;
    int w;
    public Edge(int u,int v,int w) {
        this.u=u;
        this.v=v;
        this.w=w;
    }
}
public class cons {
    public static void addEdge_directed(ArrayList<Edge> graph[],int u,int v,int w) {
        graph[u].add(new Edge(u,v,w));
    }
    public static void addEdge_undirected(ArrayList<Edge> graph[],int u,int v,int w) {
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }
    public static void main(String args[]) {
       // number of vertices
       int N=7;
       ArrayList<Edge> graph[]=new ArrayList[N];
       // initilasie arraylist
         for(int i=0;i<N;i++) {
            graph[i]=new ArrayList<>();
         }
      // construct the a directed graph
      addEdge_directed(graph,1,2,4);
      addEdge_directed(graph,1,3,4);
      addEdge_directed(graph,2,3,2);
      addEdge_directed(graph,3,4,1);
      addEdge_directed(graph,2,5,1);
      addEdge_directed(graph,4,6,2);
      addEdge_directed(graph,5,6,4);
           
       // display graph .........
       for(int i=0;i<N;i++) {
          for(Edge e:graph[i]) {
              System.out.println(e.u+","+e.v+","+e.w);
          }
       }
       System.out.println();
       ArrayList<Edge> graph1[]=new ArrayList[N];
       // intilaise arraylist
       for(int i=0;i<N;i++) {
           graph1[i]=new ArrayList<>();
       }
       // construct the undirected graph
      addEdge_undirected(graph1,1,2,4);
      addEdge_undirected(graph1,1,3,4);
      addEdge_undirected(graph1,2,3,2);
      addEdge_undirected(graph1,3,4,1);
      addEdge_undirected(graph1,2,5,1);
      addEdge_undirected(graph1,4,6,2);
      addEdge_undirected(graph1,5,6,4);
      
      // display graph .....
      for(int i=0;i<N;i++) {
        for(Edge e:graph1[i]) {
            System.out.println(e.u+","+e.v+","+e.w);
        }
      }

    }
}
