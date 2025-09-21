import java.util.*;
public class basicQuestions {
    // check wheather there exists a path b/w a source and a destination 
    public static boolean isPathExists(int sr,int des,ArrayList<Integer> graph[],int N,boolean vis[]) {
        if(sr==des) {
            return true;
        }
        vis[sr]=true;
        for(int nbr:graph[sr]) {
            if(!vis[nbr] && isPathExists(nbr, des, graph,N,vis)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPathExists(int sr,int des,ArrayList<Integer> graph[],int N) {
        boolean vis[]=new boolean[N];
        return isPathExists(sr, des, graph, N,vis);
    }

    // print all paths b/w a source and a destination by recursion on the way up 
    public static void allPaths(int sr,int des,ArrayList<Integer> graph[],int N,boolean vis[],String psf) {
        if(sr==des) {
            System.out.println(psf);
            return;
        }
        vis[sr]=true;
        for(int nbr:graph[sr]) {
            if(vis[nbr]==false) {
                allPaths(nbr,des,graph,N,vis,psf+nbr+"->");
            }
        }
        vis[sr]=false;
    }
    public static void allPaths(int sr,int des,ArrayList<Integer> graph[],int N) {
        boolean vis[]=new boolean[N];
        allPaths(sr,des,graph,N,vis,sr+"->");
    }
    // print all paths b/w a source and a destination by recursion on the way down 
    public static void allPaths(int sr,int des,ArrayList<Integer> graph[],int N,String psf,ArrayList<String> paths,boolean vis[]) {
        if(sr==des) {
            paths.add(psf);
            return;
        }
        vis[sr]=true;
        for(int nbr:graph[sr]) {
            if(vis[nbr]==false) {
                allPaths(nbr,des,graph,N,psf+nbr,paths,vis);
            }
        }
        vis[sr]=false;
    }
    public static void allPaths1(int sr,int des,ArrayList<Integer> graph[],int N) {
       boolean vis[]=new boolean[N];
       ArrayList<String> paths=new Arraylist<>();
       allPaths(sr,des,graph,N,"",paths,vis);
    }
    // path with minimumweight from source to destination 
    static String minimumPath;
    static int minimumPathWeight;
    public static void pathWithMinimumWeight(int sr,int des,ArrayList<int[]> graph[],int N,boolean vis[],String psf,int currPathWeight) {
        if(sr==des) {
            if(currPathWeight<minimumPathWeight) {
                minimumPath=psf;
                minimumPathWeight=currPathWeight;
            }
            return;
        }
        vis[sr]=true;
        for(int arr[]:graph[sr]) {
            int nbr=arr[0];
            if(vis[nbr]==false) {
                pathWithMinimumWeight(nbr, des, graph, N,vis,psf+nbr+"",currPathWeight+arr[1]);
            }
        }
        vis[sr]=false;
    }
    public static String pathWithMinimumWeight(int sr,int des,ArrayList<Integer> graph[],int N) {
        minimumPath="";
        minimumPathWeight=Integer.MAX_VALUE;
        boolean vis[]=new boolean[N];
        pathWithMinimumWeight(sr,des,graph,N,vis,"",0);
        return minimumPath;
    }
    // path with maximum weight from source to destination 
     static int maximumWeight;
     static String maximumString;
     public static String pathWithMaximumWeight(int sr,int des,ArrayList<int[]> graph[],int N) {
        boolean vis[]=new boolean[N];
        maximumStr
     }

    public static void main(String args[]) {

    }
}
