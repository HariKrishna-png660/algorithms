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
public class graph {
    public static boolean hasPath(ArrayList<Edge> graph[],int sr,int des,boolean vis[]) {
        if(sr==des) {
            return true;
        }
        boolean found=false;
        vis[sr]=true;
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
                 found=found||hasPath(graph, e.v, des,vis);
            }
        }
        return found;
    }
    public static boolean hasPath(ArrayList<Edge> graph[],int sr,int des,int N) {
        boolean vis[]=new boolean[N];
        return hasPath(graph,sr,des,vis);
    }
    public static void addEdge(ArrayList<Edge> graph[],int u,int v,int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v,u,w));
    }
    public static void display(ArrayList<Edge> graph[]) {
         for(ArrayList<Edge> currentArrayList:graph) {
            for(Edge e:currentArrayList) {
                System.out.println(e.u+","+e.v+"->"+e.w);
            }
         }
    }
    public static ArrayList<String> getAllPaths(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf) {
        if(sr==des) {
            ArrayList<String> baseAns=new ArrayList<>();
            baseAns.add(psf+sr+"");
            return baseAns;
        }
        vis[sr]=true;
        ArrayList<String> myAns=new ArrayList<>();
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
               ArrayList<String> al=getAllPaths(graph, e.v, des,vis,psf+sr+"");
               for(String s:al) {
                myAns.add(s);
               }
            }
        }
        vis[sr]=false;
        return myAns;
    }
    public static ArrayList<String> getAllPaths(ArrayList<Edge> graph[],int sr,int des,int N) {
        boolean vis[]=new boolean[N];
        return getAllPaths(graph,sr,des,vis,"");
    }
    public static void printAllPaths(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf) {
        if(sr==des) {
            System.out.println(psf+sr+"");
            return;
        }
        vis[sr]=true;
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
                printAllPaths(graph, e.v, des,vis,psf+sr+"");
            }
        }
        vis[sr]=false;
    }
    public static void printAllPaths(ArrayList<Edge> graph[],int sr,int des,int N) {
        boolean vis[]=new boolean[N];
         printAllPaths(graph, sr, des, vis,"");
    }
    static String shortestPath="";
    static int shortestPathWeight=Integer.MAX_VALUE;
    public static String getShortestPath(ArrayList<Edge> graph[],int sr,int des,int N) {
        boolean vis[]=new boolean[N];
      getShortestPath(graph, sr, des, vis,"",0);
      return shortestPath;
    }
    public static void getShortestPath(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf,int currentPathWeight) {
          if(sr==des) {
              if(currentPathWeight<shortestPathWeight) {
                 shortestPathWeight=currentPathWeight;
                 shortestPath=psf+sr+"";
              }
            //   return shortestPath;
          }
          vis[sr]=true;
          ArrayList<Edge> currentArrayList=graph[sr];
          for(Edge e:currentArrayList) {
             if(!vis[e.v]) {
                getShortestPath(graph, e.v, des, vis, psf+sr+"", currentPathWeight+e.w);
             }
          }
          vis[sr]=false;
    }
    static String longestpath="";
    static int largestPathWeight=Integer.MIN_VALUE;
    public static String getLongestPath(ArrayList<Edge> graph[],int sr,int des,int N) {
        boolean vis[]=new boolean[N];
        getLongestPath(graph, sr, des, vis,"",0);
        return longestpath;
    }
    public static void getLongestPath(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf,int currentPathWeight) {
        if(sr==des) {
            if(currentPathWeight>largestPathWeight) {
               largestPathWeight=currentPathWeight;
               longestpath=psf+sr+"";
            }
            return;
        }
        vis[sr]=true;
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
                getLongestPath(graph, e.v, des, vis, psf+sr+"", currentPathWeight+e.w);
            }
        }
        vis[sr]=false;
    }
    static String ceilPathString="";
    static int ceilPathWeight=Integer.MAX_VALUE;
    public static String getCeilPath(ArrayList<Edge> graph[],int sr,int des,int N,int targetWeight) {
        boolean vis[]=new boolean[N];
        getCeilPath(graph, sr, des, vis,"",0,targetWeight);
        return ceilPathString;
    }
    public static void getCeilPath(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf,int currentPathWeight,int targetWeight) {
        if(sr==des) {
            if(currentPathWeight>=targetWeight && currentPathWeight<ceilPathWeight) {
                ceilPathWeight=currentPathWeight;
                ceilPathString=psf+sr+"";
            }
            return;
        }
        vis[sr]=true;
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
                  getCeilPath(graph, e.v, des, vis, psf+sr+"", currentPathWeight+e.w, targetWeight);
            }
        }
        vis[sr]=false;
    }
    static String floorPathString="";
    static int floorPathWeight=Integer.MIN_VALUE;
    public static String getFloorPath(ArrayList<Edge> graph[],int sr,int des,int N,int targetWeight) {
        boolean vis[]=new boolean[N];
        getFloorPath(graph,sr,des,vis,"",0,targetWeight);
        return floorPathString;
    }
    public static void getFloorPath(ArrayList<Edge> graph[],int sr,int des,boolean vis[],String psf,int currentPathWeight,int targetWeight) {
        if(sr==des) {
            if(currentPathWeight<=targetWeight && currentPathWeight>floorPathWeight){
                floorPathString=psf+sr+"";
                floorPathWeight=currentPathWeight;
            }
            return;
        }
        vis[sr]=true;
        ArrayList<Edge> currentArrayList=graph[sr];
        for(Edge e:currentArrayList) {
            if(!vis[e.v]) {
                getFloorPath(graph, e.v, des, vis, psf+sr+"", currentPathWeight+e.w, targetWeight);
            }
        }
        vis[sr]=false;
    }
    
    // get connected components....
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge> graph[],int N) {
        ArrayList<ArrayList<Integer>> components=new ArrayList<>();
        boolean vis[]=new boolean[N];
        for(int vertex=0;vertex<N;vertex++) {
            if(vis[vertex]==false) {
                ArrayList<Integer> currentComponents=new ArrayList<>();
                dfs(graph,vertex,vis,currentComponents);
                components.add(currentComponents);
            }
        }
        return components;
    }
    public static void dfs(ArrayList<Edge> graph[],int vertex,boolean vis[],ArrayList<Integer> currentComponents) {
        vis[vertex]=true;
        currentComponents.add(vertex);
        for(Edge e:graph[vertex]) {
            if(vis[e.v]==false) {
                dfs(graph,e.v,vis,currentComponents);
            }
        }
    }
    // number of connected components
    public static int numberOfConnectedComponents(ArrayList<Edge> graph[],int N) {
        boolean vis[]=new boolean[N];
        int count=0;
        for(int i=0;i<N;i++) {
            if(vis[i]==false) {
                count++;
                dfs(graph,vis,i);
            }
        }
        return count;
    }
    public static void dfs(ArrayList<Edge> graph[],boolean vis[],int vertex) {
           vis[vertex]=true;
           for(Edge e:graph[vertex]) {
              if(vis[e.v]==false) {
                 dfs(graph,vis,e.v);
              }
           }
    }












        public static void main(String args[]) {
          // number of vertex 
          int N=8;
          // construction is being done with array of arraylist which are  of edge type.
          ArrayList<Edge> graph[]=new ArrayList[N];
          for(int i=0;i<N;i++) {
             graph[i]=new ArrayList<>();
          }
          addEdge(graph,0,1,10);
          addEdge(graph,1,2,11);
          addEdge(graph,1,4,2);
          addEdge(graph,1,3,7);
          addEdge(graph,4,2,8);
          addEdge(graph,2,5,13);
          addEdge(graph,5,6,6);
          addEdge(graph,5,7,5);
          addEdge(graph,6,7,4);
          display(graph);
        System.out.println(hasPath(graph, 4, 8, N));  
         System.out.println(hasPath(graph, 3, 5, N)); 
         System.out.println(getAllPaths(graph, 0, 6, N));
        printAllPaths(graph, 0, 6, N);
        System.out.println(getShortestPath(graph, 0, 6, N));
        System.out.println(getLongestPath(graph, 0, 6, N));
        System.out.println(getCeilPath(graph, 0, 6, N,39));
        System.out.println(getFloorPath(graph, 0, 6, N, 40));
    }
}
