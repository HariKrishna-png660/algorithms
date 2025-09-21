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
public class TOTALGRAPHS {
    public static void construct(int edges[][],ArrayList<Integer> graph[]) {
         for(int edge[]:edges) {
            int u=edge[0];
            int v=edge[1];
            graph[u].add(v);
            graph[v].add(u); // in case of a undirected graph 
         }
    }
    public static void display(ArrayList<Integer> graph[]) {
        int N=graph.length;
        for(int i=0;i<N;i++) {
            for(int nbr:graph[i]) {
                System.out.print(nbr+" ");
            }
            System.out.println();
        }
    }
    // Q1 : FIND WHEATHER THERE EXISTS A PATH BETWEEN A SOURCE AND DESTINATION IN A GRAPH ?
    // https://media.geeksforgeeks.org/wp-content/uploads/graph-11-300x236.png
    // above image is a test case 
    // IMPORTANT =============
    // TIME COMPLEXITY 
    /*
     *  “For each vertex, I’m doing two things:
              1) Visit the vertex once.
              2) Scan through its adjacency list once.
              If I add this up across all vertices, I end up visiting all vertices = O(V), and scanning all adjacency lists = O(E).”
              In an undirected graph, adjacency list has 2E entries (because each edge is stored twice).
              So the loop executes 2E times, which is O(E).
     */
    // SPACE COMPLEXITY 
    // O(V)....
    // IN THIS QUESTION WE CANNOT IMPROVE TIME AND SPACE BUT WE CAN PREFER THE DIFFERENT APPROACHES ...........................
    /*
     * 1) RECURSIVE DFS 
     * 2) ITERATIVE DFS 
     * 3) BFS 
     * 
     * 
     * | Method       Safe Node Limit (Java)                        | Best For                                         | Weakness                      |
| ----------------- | --------------------------------------------- | ------------------------------------------------ | ----------------------------- |
| **Recursive DFS** | Up to \~10^4 (depth limited by stack)         | Clean/simple graph/tree traversal                | Stack overflow on deep graphs |
| **Iterative DFS** | Up to 10^6+                                   | Large graphs, cycle detection, generic traversal | Code slightly longer          |
| **BFS**           | Up to 10^6 (but watch memory for wide graphs) | Shortest path (unweighted), level order          | High memory in wide graphs    |

     */ 

     /*
      * ITERATIVE DFS.....
      */
      public static boolean isPathExists_itDfs(ArrayList<Integer> graph[],int src,int des,boolean vis[]) {
         Stack<Integer> st=new Stack<>();
         st.push(src);
         vis[src]=true;
         while(st.size()!=0) {
            int top=st.pop();
            if(top==des) {
                return true;
            }
            for(int nbr:graph[top]) {
                if(vis[nbr]==false) {
                    vis[nbr]=true;
                    st.push(nbr);
                }
            }
         }
         return false;
      }
      /*
     * RECURSIVE DFS ......
     */
    public static boolean isPathExists(ArrayList<Integer> graph[],int sr,int des) {
        int N=graph.length;
        boolean vis[]=new boolean[N];
        return isPathExists_itDfs(graph, sr, des,vis);
    }
    
    public static boolean isPathExists_recDfs(ArrayList<Integer> graph[],int src,int des,boolean vis[]) {
        if(src==des) {
            return true;
        }
        vis[src]=true;
        for(int nbr:graph[src]) {
            if(!vis[nbr]) {
                boolean isFound=isPathExists_recDfs(graph,nbr,des,vis);
                if(isFound) {
                    return true;
                }
            }
        }
        return false;
    }
    // Q2: FIND ALL PATHS FROM A SOURCE TO DESTINATION ?
    public static void allPaths_dfs(ArrayList<Integer> graph[],int sr,int des,boolean vis[],String psf) {
        if(sr==des) {
            System.out.println(psf+"->"+des);
            return;
        }
        vis[sr]=true;
        for(int nbr:graph[sr]) {
             if(vis[nbr]==false) {
                allPaths_dfs(graph,nbr,des,vis,psf+nbr+"->");
             }
        }
        vis[sr]=false;
    }
    public static void allPaths(ArrayList<Integer> graph[],int src,int des) {
        int N=graph.length;
        boolean vis[]=new boolean[N];
        allPaths_dfs(graph,src,des,vis,src+"->");
    } 
    public static void main(String args[]) {
       Scanner sc=new Scanner(System.in);
       // Number of vertices 
       int N=15;
       // number of edges
       int q=sc.nextInt();
       ArrayList<Integer> graph[]=new ArrayList[N];
       // initilise the arraylist
       for(int i=0;i<N;i++) {
           graph[i]=new ArrayList<>();
       }
       // take edges as input 
       int edges[][]=new int[q][2];
       for(int i=0;i<q;i++) {
           int a=sc.nextInt();
           int b=sc.nextInt();
           edges[i]=new int[]{a-1,b-1};
       }
       construct(edges,graph);
       // display graph as a adjacency list
       display(graph);


       // Q1 
       System.out.println(isPathExists(graph,14,3));
       allPaths(graph,3,6);
    }
}
