import java.util.*;
public class buildingRoads {
    public static void getAllConnectedComponents(ArrayList<Integer> graph[],boolean vis[],int src,ArrayList<Integer> component) {
        Stack<Integer> st=new Stack<>();
        st.add(src);
        vis[src]=true;
        while(st.size()!=0) {
            int top=st.pop();
            for(int nbr:graph[top]) {
                if(vis[nbr]==false) {
                    component.add(nbr);
                    vis[nbr]=true;
                }
            } 
        }
    }
    public static void main(String args[]) {
         Scanner sc=new Scanner(System.in);
         int n=sc.nextInt();
         int m=sc.nextInt();
         int edges[][]=new int[m][2];
         for(int i=0;i<m;i++) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            edges[i][0]=a-1;
            edges[i][1]=b-1;
         }
         // construct the graph 
         int N=n;
         ArrayList<Integer> graph[]=new ArrayList[N];
         // initilise the arraylist
         for(int i=0;i<n;i++) {
            graph[i]=new ArrayList<>();
         }
         for(int edge[]:edges) {
            int u=edge[0];
            int v=edge[1];
            graph[u].add(v);
            graph[v].add(u);
         }
         ArrayList<ArrayList<Integer>> comp=new ArrayList<>();
         boolean vis[]=new boolean[N];
         for(int i=0;i<N;i++) {
            if(vis[i]==false) {
                ArrayList<Integer> component=new ArrayList<>();
                getAllConnectedComponents(graph,vis,i,component);
                comp.add(component);
            }
         }
         System.out.println(comp.size()-1);
         ArrayList<Integer> val=new ArrayList<>();
         for(ArrayList<Integer> al:comp) {
            val.add(al.get(0));
         }
         for(int i=0;i<val.size()-1;i++) {
             System.out.print(val.get(i)+" "+val.get(i+1));
         }
    }
}
