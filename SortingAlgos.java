import java.util.*;
public class SortingAlgos {
    // =================== MERGE SORT =========================================================
    public static int[] mergeSortedArrays(int a[],int b[]) { // TIME COMPLEXITY OF mergeSortedArrays function is O(n1+n2) === O(n)
        int n1=a.length;                                     // SPACE COMPLEXITY OF mergeSortedArrays function is O(n1+n2) === O(n)
        int n2=b.length;
        int res[]=new int[n1+n2]; // space O(n)
        int i=0;
        int j=0;
        int k=0;
        while(i<n1 && j<n2) {
            int val1=a[i];
            int val2=b[j];
            if(val1<=val2) {
              res[k]=val1;
              i++;
            }
            else {
                res[k]=val2;
                j++;
            }
           k++;
        }
        while(i<n1) {
            res[k++]=a[i++];
        }
        while(j<n2) {
            res[k++]=b[j++];
        }
        return res;
    }
    public static int[] mergeSort(int arr[],int si,int ei) { // TIME COMPLEXITY OF mergeSort function is O(N LOG N)
        if(si==ei) {                                        // there are total of log n levels and in each level O(n) work is being done so total O(N LOG N)
            int ba[]=new int[1];                            // space = O(log n) [ recursive depth space] + O(n) merging array =O(N)
            ba[0]=arr[si];
            return ba;
        }
        int mid=(si+ei)/2;
        int left[]=mergeSort(arr,si,mid); 
        int right[]=mergeSort(arr,mid+1,ei);
        int res[]=mergeSortedArrays(left,right); // time is O(n)
        return res;
    }
    //===================== QUICK SORT=========================================
    public static void swap(int i,int j,int arr[]) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static int partition(int arr[],int si,int ei) { // TIME COMPLEXITY IS O(N)
        int pivot=arr[ei];                                 
        int i=si;int j=si;
        while(j<=ei-1) {
            if(arr[j]<=pivot) {
                swap(i,j,arr);
                i++;
            }
            j++;
        }
        swap(i,ei,arr);
        return i;
    }
    public static void quickSort(int arr[],int si,int ei) { // AVERAGE CASE TIME-> O(N LOG N) 
        if(si>ei) {                                         // WORST CASE TIME -> O(N^2) [WHEN THE ARRAY IS SORTED IN ASCENDING ORDER]
            return;
        }
        int pivotIndex=partition(arr,si,ei);
        quickSort(arr,si,pivotIndex-1);
        quickSort(arr,pivotIndex+1,ei);
    }
    public static void main(String args[]) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }
        //======MERGE SORT=============
        int res[]=mergeSort(arr,0,n-1);
        for(int i=0;i<n;i++) {
            System.out.print(res[i]+" ");
        }
        System.out.println();
        // ========== QUICK SORT========
        quickSort(arr,0,n-1);
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
