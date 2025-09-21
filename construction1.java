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
        return this.u+"->"+this.v+","+this.w;
    }
}
 class Pair {
    int weight;
    String path;
    public Pair(int weight,String path) {
        this.weight=weight;
        this.path=path;
    }
  }
public class construction1 {
   public static void addEdge(ArrayList<Edge> graph[],int u,int v,int w) {
       graph[u].add(new Edge(u,v,w));
       graph[v].add(new Edge(v,u,w));

   }
   public static void display(ArrayList<Edge> graph[]) {
       int N=graph.length;
       for(int i=0;i<N;i++) {
          ArrayList<Edge> currList=graph[i];
          for(Edge e:currList) {
             System.out.println(e);
          }
       }
   }
   public static boolean hasPath(int src,int des,ArrayList<Edge> graph[],boolean vis[]) {
      if(src==des) {
        return true;
      }
      vis[src]=true;
      for(Edge e:graph[src]) {
         int nbr=e.v;
         if(!vis[nbr]) {
            if(hasPath(nbr,des,graph,vis)) {
                return true;
            }
         }
      }
      return false;
   }
   public static boolean hasPath(int src,int des,ArrayList<Edge> graph[],int N) {
      boolean vis[]=new boolean[N];
      return hasPath(src,des,graph,vis);
   }
   public static void printAllPaths(int src,int des,ArrayList<Edge> graph[],boolean vis[],String psf) {
      if(src==des) {
        String str=psf.substring(0,psf.length()-2);
        System.out.println(str);
        return;
      }
      vis[src]=true;
      for(Edge e:graph[src]) {
        int nbr=e.v;
        if(!vis[nbr]) {
            printAllPaths(nbr,des,graph,vis,psf+nbr+"->");
        }
      }
      vis[src]=false;
   }
   public static void printAllPaths(int src,int des,ArrayList<Edge> graph[],int N) {
      boolean vis[]=new boolean[N];
      printAllPaths(src, des, graph, vis,src+"->");
   }
  // smallest path and longest path
  static int smallestWeight=Integer.MAX_VALUE;
  static int largestWeight=Integer.MIN_VALUE;
  static String smallestPath="";
  static String largestPath="";
  static int floorPathWeight=Integer.MIN_VALUE;
  static int ceilPathWeight=Integer.MAX_VALUE;
  static String floorPath="";
  static String ceilPath="";
  public static void multisolver(int src,int des,ArrayList<Edge> graph[],boolean vis[],int currentPathWeight,String currPath,int targetWeight,PriorityQueue<Pair> pq,int k,PriorityQueue<Pair> pq1) {
    if(src==des) {
        if(currentPathWeight<smallestWeight) {
            smallestPath=currPath;
            smallestWeight=currentPathWeight;
           
        }
        if(currentPathWeight>largestWeight) {
            largestWeight=currentPathWeight;
            largestPath=currPath;
            
        }
        if(currentPathWeight>floorPathWeight && currentPathWeight<=targetWeight) {
           floorPathWeight=currentPathWeight;
           floorPath=currPath;
        }
        if(ceilPathWeight>currentPathWeight && currentPathWeight>=targetWeight) {
            ceilPath=currPath;
            ceilPathWeight=currentPathWeight;
        }
         pq.add(new Pair(currentPathWeight,currPath));
             if(pq.size()>k) {
                pq.remove();
             }
         pq1.add(new Pair(currentPathWeight,currPath));
          if(pq1.size()>k) {
             pq1.remove();
          }
          return;
    }
           vis[src]=true;
           for(Edge e:graph[src]) {
               int nbr=e.v;
               if(!vis[nbr]) {
                  multisolver(nbr, des, graph, vis,currentPathWeight+e.w,currPath+nbr+"->",targetWeight,pq,k,pq1);
               }
           }
           vis[src]=false;
  }
 
  public static void multiSolver(int src,int des,ArrayList<Edge> graph[],int N,int k) {
    boolean vis[]=new boolean[N];
    PriorityQueue<Pair> pq=new PriorityQueue<>((Pair p1,Pair p2)-> {
        return p2.weight-p1.weight;
    });
    PriorityQueue<Pair> pq1=new PriorityQueue<>((Pair p1,Pair p2)-> {
        return p1.weight-p2.weight;
    });
    multisolver(src, des, graph, vis,0,src+"->",40,pq,k,pq1);
  }
  public static void dfs(int vtx,ArrayList<Edge> graph[],boolean vis[],ArrayList<Integer> components) {
      components.add(vtx);
      vis[vtx]=true;
      for(Edge e:graph[vtx]) {
          int nbr=e.v;
          if(!vis[nbr]) {
            dfs(nbr,graph,vis,components);
          }
      }

  }
  public static int connectedComponents(ArrayList<Edge> graph[],boolean vis[],ArrayList<ArrayList<Integer>> components,int N) {
    int number_of_components=0;
     for(int i=0;i<N;i++) {
        if(!vis[i]) {
            ArrayList<Integer> comp=new ArrayList<>();
            dfs(i,graph,vis,comp);
            components.add(comp);
           number_of_components++;
        }
     }
     return number_of_components;
  }
  public static int connectedComponents(ArrayList<Edge> graph[],int N) {
    boolean vis[]=new boolean [N];
    ArrayList<ArrayList<Integer>> components=new ArrayList<>();
    return connectedComponents(graph, vis,components,N);
  }
  public static boolean checkEdge(int currsrc,int osrc,ArrayList<Edge> graph[]) {
      for(Edge e:graph[currsrc]) {
        int nbr=e.v;
         if(nbr==osrc) {
            return true;
         }
      }
     return false;
  }
  public static void isHamiltonianPathOrCycle(ArrayList<Edge> graph[],int N,int cellsVisited,boolean vis[],int currsrc,int osrc,String path) {
       if(N==cellsVisited) {
          if(checkEdge(currsrc,osrc,graph)) {
            System.out.println("THIS FORM A HAMILTONIAN CYCLE");
            System.out.println(path);
          }
          else {
            System.out.println("THIS FROM A HAMILTONIAN PATH");
            System.out.println(path);
          }
          return;
       }
       vis[currsrc]=true;
       for(Edge e:graph[currsrc]) {
          int nbr=e.v;
          if(!vis[nbr]) {
             isHamiltonianPathOrCycle(graph, N, cellsVisited+1,vis,nbr,osrc,path+nbr+"->");
          }
       }
       vis[currsrc]=false;
  }
  public static void isHamiltonianPathOrCycle(ArrayList<Edge> graph[],int N,int src) {
     boolean vis[]=new boolean[N];
     isHamiltonianPathOrCycle(graph, N,0,vis,src,src,src+"->");
  }
  // topological sort for a directed acyclic graph
  // approach 1 using a arraylist
  public static ArrayList<Integer> topologicalOrder(ArrayList<Edge> graph[],int n) {
    boolean vis[]=new boolean[n];
    ArrayList<Integer> order=new ArrayList<>();
    for(int i=0;i<n;i++) {
        if(!vis[i]) {
            dfs(i,graph,vis,order);
        }
    }
    Collections.reverse(order);
     return order;
  }
  public static void dfs(int vtx,ArrayList<Edge> graph[],boolean vis[],ArrayList<Integer> order) {
     vis[vtx]=true;
     for(Edge e:graph[vtx]) {
        int nbr=e.v;
        if(!vis[nbr]) {
            dfs(nbr,graph,vis,order);
        }
     }
     order.add(vtx);
  }
  // topological sort for a directed cyclic graph
  public static boolean dfs(int vtx,ArrayList<Edge> graph[],int vis[]) {
    vis[vtx]=1;
    for(Edge e:graph[vtx]) {
        int nbr=e.v;
        if(vis[nbr]==1) {
            return false;
        }
        else if(vis[nbr]==0) {
            return dfs(nbr,graph,vis);
        }
    }
    vis[vtx]=2;
    return true;
  }
  public static boolean topologicalSort_genric(ArrayList<Edge> graph[],int N) {
      int vis[]=new int[N];
      for(int i=0;i<N;i++) {
         if(vis[i]==0) {
              if(!dfs(i,graph,vis)) {
                 return false;
              }
         }
      }
      return true;
  }
 // BFS TRAVERSAL==========================
 public static void BFS_01(ArrayList<Edge> graph[],int N,int src) {
   LinkedList<Integer> list=new LinkedList();
   boolean vis[]=new boolean[N];
   int level=0;
   list.addLast(src);
   while(list.size()>0) {
     int size=list.size();
     while(size-->0) {
        int front=list.removeFirst();
        if(vis[front]) {
            continue;
        }
        vis[front]=true;
        for(Edge e:graph[front]) {
            int nbr=e.v;
            if(!vis[nbr]) {
               list.addLast(nbr);
            }
        }
     }
     level++;
   }
 }
 // topological sort using bfs (kahn's algorithm)
 public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge> graph[],int N) {
   // calculate the indegree of each vertex
   int indegree[]=new int[N];
   LinkedList<Integer> queue=new LinkedList<>();
   ArrayList<Integer> order=new ArrayList<>();
    for(ArrayList<Edge> edge:graph) {
       for(Edge e:edge) {
          int nbr=e.v;
          indegree[nbr]++;
       }
    }
    for(int i=0;i<N;i++) {
      if(indegree[i]==0) {
         queue.addLast(i);
         order.add(i);
      }
    }
    while(queue.size()>0) {
      int size=queue.size();
      while(size-->0) {
         int front=queue.removeFirst();
         for(Edge e:graph[front]) {
            int nbr=e.v;
            indegree[nbr]--;
            if(indegree[nbr]==0) {
               queue.addLast(nbr);
               order.add(nbr);
            }
         }
      }
    }
    if(order.size()!=N) {
      return new ArrayList<>();
    }
    return order;
 }
    public static void main(String args[]) {
         int N=8;
         ArrayList<Edge> graph[]=new ArrayList[N];
         for(int i=0;i<N;i++) {
            graph[i]=new ArrayList<>();
         }
         addEdge(graph,0,1,10);
         addEdge(graph,1,3,7);
         addEdge(graph,1,2,11);
         addEdge(graph,1,4,12);
         addEdge(graph,5,6,6);
         addEdge(graph,5,7,5);
         addEdge(graph,6,7,4);
         addEdge(graph,2,4,9);
         display(graph);
         System.out.println(hasPath(0, 4, graph, N));
         printAllPaths(0, 5, graph, N);
        //  multiSolver(0, 4, graph, N);
         System.out.println(smallestPath);
         System.out.println(largestPath);
        //  multisolver(0, N, graph, null, N, floorPath, N);
    }
}
