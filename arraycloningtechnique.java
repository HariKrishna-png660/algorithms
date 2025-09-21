import java.util.*;
public class arraycloningtechnique {
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0) {
            int n=sc.nextInt();
            int arr[]=new int[n];
            HashMap<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<n;i++) {
                arr[i]=sc.nextInt();
                if(map.containsKey(arr[i])) {
                    map.put(arr[i],map.get(arr[i])+1);
                }
                else {
                    map.put(arr[i],1);
                }
            }
            int maxFreq=Integer.MIN_VALUE;
            for(Integer value:map.values()) {
                  maxFreq=Math.max(value,maxFreq);
            }
            // System.out.println(maxFreq);
            int op=0;
            while(maxFreq<n) {
              op++;
              if(n-maxFreq>=maxFreq) {
                 op += maxFreq;
                 maxFreq*=2;
              }
              else {
                 op +=(n-maxFreq);
                 maxFreq=n;
              }
              
            }
            System.out.println(op);

        }
    }
}
