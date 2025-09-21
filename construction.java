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
    public String toString() {
        return "("+u+"->"+v+" ,"+w+")";
    }
}
public class construction {
    public static void addEdge(ArrayList<Edge> graph[],int u,int v,int w) {
        graph[u].add(new Edge(u,v,w));
    }
    public static void display(ArrayList<Edge> graph[]) {
        for(ArrayList<Edge> al:graph) {
            for(Edge e:al) {
                System.out.println(e);
            }
        } 
    }
    // visited is required for directed any ways in case of a undirected graph with cycle 
    // visted is not required for directed graph without cycles.
    public static boolean hasPath(int src,int des,ArrayList<Edge> graph[],int N,boolean vis[]) {
        if(src==des) {
            return true;
        }
        // boolean isPathExists=false;
        vis[src]=true;
        ArrayList<Edge> currentVertex=graph[src];
        for(Edge e:currentVertex) {
           if(!vis[e.v] && hasPath(e.v, des, graph, N,vis)) {
                return true;
           }
        }
        // vis[src]=false;
        return false;
    }
    public static void printAllPaths(int src,int des,ArrayList<Edge> graph[],boolean vis[],String psf) {
        if(src==des) {
            // String baseAns=psf+des+"";
            System.out.println(psf);
            return;
        }
        vis[src]=true;
        ArrayList<Edge> currentVertex=graph[src];
        for(Edge e:currentVertex) {
            if(!vis[e.v]) {
                printAllPaths(e.v, des, graph,vis,psf+e.v+"");
            }
        }
        vis[src]=false;
        return;
    }
    public static boolean hasPath(int src,int des,ArrayList<Edge> graph[],int N) {
       boolean vis[]=new boolean[N];
       return hasPath(src,des,graph,N,vis);
    }
    public static void printAllPaths(int src,int des,ArrayList<Edge> graph[],int N) {
        boolean vis[]=new boolean[N];
        printAllPaths(src, des, graph,vis,src+"");
    }
    public static void shortestPath(int sr,int des,ArrayList<Edge> graph[],int N,boolean vis[],String psf) {
        if(sr==des) {
           System.out.println(psf+des+"");
           return;
        }
        vis[sr]=true;
        ArrayList<Edge> current=graph[sr];
        int minWeight=current.get(0).w;
        int nextVertex=sr;
        for(Edge e:current) {
            if(!vis[e.v]&&e.w<=minWeight) {
              minWeight=Math.min(minWeight,e.w);
              nextVertex=e.v;
            }
        }
        shortestPath(nextVertex, des, graph,N,vis,psf+nextVertex+"");
        vis[sr]=false;
    }
    public static void shortestPath(int sr,int des,ArrayList<Edge> graph[],int N) {
       boolean vis[]=new boolean[N];
        shortestPath(sr, des, graph, N,vis,"");
    }
    public static boolean checkIfEdge(int a,int b,ArrayList<Edge> graph[]) {
        for(Edge e:graph[b]) {
             if(e.v==a) {
                return true;
             }
        }
        return false;
    }
    public static void dfs_hamiltonianPath(int currVtx,int osrc,int visitedvtxs,ArrayList<Edge> graph[],String path,boolean vis[],int N) {
        if(visitedvtxs==N-1) {
            if(checkIfEdge(currVtx,osrc,graph)) {
                System.out.println("Hamiltonian cycle :"+path);
            }
            else {
                System.out.println("Hamiltonian path :"+path);
            }
            return;
        }
        vis[currVtx]=true;
        for(Edge e:graph[currVtx]) {
            int nbr=e.v;
            if(!vis[nbr]) {
                dfs_hamiltonianPath(nbr, osrc, visitedvtxs+1, graph, path+"->"+nbr, vis, N);
            }
        }
       vis[currVtx]=false;
    }
    public static void hamiltonianPathandCycle(int src,ArrayList<Edge> graph[],boolean vis[],int N) {
        dfs_hamiltonianPath(src,src,0,graph,src+"",new boolean[N],N);
    }
   
    public static ArrayList<ArrayList<Integer>> allTopoOrders(ArrayList<Integer> graph[],int N) {
        boolean vis[]=new boolean[N];
        ArrayList<ArrayList<Integer>> topoOrders=new ArrayList<>();
       
                 ArrayList<Integer> topOrder=new ArrayList<>();
                 dfs(graph,vis,0,topOrder);
                 topoOrders.add(topOrder);
       

        return topoOrders;
    }
    public static void dfs(ArrayList<Integer> graph[],boolean vis[],int src,ArrayList<Integer> topoOrder) {
         vis[src]=true;
         for(int nbr:graph[src]) {
            if(!vis[nbr]) {
                dfs(graph,vis,nbr,topoOrder);
            }
         }
         topoOrder.add(src);
    }
    public static void BFS_01(ArrayList<Edge> graph[],int N,int src) {
        boolean vis[]=new boolean[N];
        LinkedList<Integer> queue=new LinkedList<>();
        queue.addLast(src);
        int level=0;
        while(queue.size()>0) {
            int size=queue.size();
            System.out.println("vertices at distance of"+level+"->");
            while(size-->0) {
                int vtx=queue.removeFirst();
                if(vis[vtx]==true) {
                    continue;
                }
                System.out.print(vtx+" ");
                vis[vtx]=true;
                for(Edge e:graph[vtx]) {
                    int nbr=e.v;
                    if(vis[nbr]==false) {
                        queue.addLast(nbr);
                    }
                }
            }
            System.out.println();
            level++;
        }

    }
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
    class Pair {
        int par;
        int vtx;
        int wt;
        public Pair(int par,int vtx,int wt) {
            this.par=par;
            this.vtx=vtx;
            this.wt=wt;
        }
    }
    public static void addEdge(ArrayList<Edge> graph[],int u,int v,int w) {
        graph[u].add(v);
    }
    public static ArrayList<Edge>[] prims(ArrayList<Edge> graph[],int N) {
        boolean vis[]=new boolean[N];
        PriorityQueue<Pair> pq=new PriorityQueue<>();
  
    } 




















    public static void main(String args[]) {
         int N=8;
         // create an array of of type arraylist of edge type.
         ArrayList<Edge> graph[]=new ArrayList[N];
          
         addEdge(graph,0,2,10);
         addEdge(graph,2,0,10);
         addEdge(graph,0,1,9);
         addEdge(graph,1,0,9);
         addEdge(graph,2,1,8);
         addEdge(graph,1,2,8);
         addEdge(graph,2,3,19);
         addEdge(graph,3,2,19);
         addEdge(graph,2,4,12);
         addEdge(graph,4,2,12);
         addEdge(graph,1,3,13);
         addEdge(graph,3,1,13);
         addEdge(graph,3,5,7);
         addEdge(graph,5,3,7);
         addEdge(graph,5,4,6);
         addEdge(graph,4,5,6);
        //  addEdge(graph,4,2,12);
        //  addEdge(graph,2,4,12);
         display(graph);
         System.out.println(hasPath(0,1,graph,N));
         printAllPaths(0,4,graph,N);
         shortestPath(0, 4, graph, N);
    }
}
