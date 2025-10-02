import java.util.*;
public class SORTINGALGOS {
    // merge sort 
    public static int[] mergeSortedArrays(int a[],int b[]) {
        int n1=a.length;
        int n2=b.length;
        int res[]=new int[n1+n2];
        int i=0; // pointer for array a
        int j=0; // pointer for array b
        int k=0; // pointer for array res
        while(i<n1 && j<n2) {
            if(a[i]<=b[j]) {
                res[k]=a[i];
                i++;
            }
            else {
                res[k]=b[j];
                j++;
            }
            k++;
        }
        while(i<n1) {
           res[k]=a[i];
           i++;
           k++;
        }
        while(j<n2) {
            res[k]=b[j];
            j++;
            k++;
        }
        return res;
    }
    public static int[] mergeSort(int arr[],int si,int ei) {
        if(si==ei) {
            int ba[]=new int[1];
            ba[0]=arr[si];
            return ba;
        }
        int mid=(si+ei)/2;
        int left[]=mergeSort(arr,si,mid);
        int right[]=mergeSort(arr,mid+1,ei);
        return mergeSortedArrays(left, right);
    }
    // ===================MERGE SORT BASED QUESTIONS===========================================================================
    // https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
    class Solution {
    static int invCount;
    // merge sort 
    public static int[] mergeSortedArrays(int a[],int b[]) {
        int n1=a.length;
        int n2=b.length;
        int res[]=new int[n1+n2];
        int i=0; // pointer for array a
        int j=0; // pointer for array b
        int k=0; // pointer for array res
        while(i<n1 && j<n2) {
            if(a[i]<=b[j]) {
                res[k]=a[i];
                i++;
            }
            else {
                res[k]=b[j];
                invCount += (n1-i);
                j++;
            }
            k++;
        }
        while(i<n1) {
           res[k]=a[i];
           i++;
           k++;
        }
        while(j<n2) {
            res[k]=b[j];
            // even though we try to add invCount += (n1 -i) we will be adding zeros here .....
            j++;
            k++;
        }
        return res;
    }
    public static int[] mergeSort(int arr[],int si,int ei) {
        if(si==ei) {
            int ba[]=new int[1];
            ba[0]=arr[si];
            return ba;
        }
        int mid=(si+ei)/2;
        int left[]=mergeSort(arr,si,mid);
        int right[]=mergeSort(arr,mid+1,ei);
        return mergeSortedArrays(left, right);
    }
       static int inversionCount(int arr[]) {
        // Code Here
        invCount=0;
        int n=arr.length;
        mergeSort(arr,0,n-1);
        return invCount;
       }
   }
   //=====================================
   // Leetcode 493 (HARD)
   class Solution {
    static int invCount;
    // merge sort 
    public static int[] mergeSortedArrays(int a[],int b[]) {
        int n1=a.length;
        int n2=b.length;
        int res[]=new int[n1+n2];
        int i=0; // pointer for array a
        int j=0; // pointer for array b
        int k=0; // pointer for array res
        while(i<n1 && j<n2) {
            if(a[i]<=b[j]) {
                res[k]=a[i];
                i++;
            }
            else {
                res[k]=b[j];
                j++;
            }
            k++;
        }
        while(i<n1) {
           res[k]=a[i];
           i++;
           k++;
        }
        while(j<n2) {
            res[k]=b[j];
            // even though we try to add invCount += (n1 -i) we will be adding zeros here .....
            j++;
            k++;
        }
        return res;
    }
    public static int[] mergeSort(int arr[],int si,int ei) {
        if(si==ei) {
            int ba[]=new int[1];
            ba[0]=arr[si];
            return ba;
        }
        int mid=(si+ei)/2;
        int left[]=mergeSort(arr,si,mid);
        int right[]=mergeSort(arr,mid+1,ei);
        int j = 0;
        for (int i = 0; i < left.length; i++) {
           while (j < right.length && (long) left[i] > 2L* right[j]) {
                  j++;
              }
             invCount += j;
          }
        return mergeSortedArrays(left, right);
    }
    public int reversePairs(int[] nums) {
        invCount=0;
        int n=nums.length;
        mergeSort(nums,0,n-1);
        return invCount;
    }
}
//==================================================
// leetcode 315 (HARD)
class Solution {
    class Pair {
        int val;
        int originalIndex;
        int count;
        public Pair(int val, int originalIndex) {
            this.val = val;
            this.originalIndex = originalIndex;
            this.count = 0; 
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        mergeSort(arr, 0, n - 1);

        // Prepare result in original order
        int[] resArr = new int[n];
        for (Pair p : arr) {
            resArr[p.originalIndex] = p.count;
        }

        List<Integer> result = new ArrayList<>();
        for (int c : resArr) result.add(c);
        return result;
    }

    private Pair[] mergeSort(Pair[] arr, int si, int ei) {
        if (si == ei) {
            return new Pair[]{arr[si]};
        }

        int mid = (si + ei) / 2;
        Pair[] left = mergeSort(arr, si, mid);
        Pair[] right = mergeSort(arr, mid + 1, ei);

        return merge(left, right);
    }

    private Pair[] merge(Pair[] left, Pair[] right) {
        int n1 = left.length, n2 = right.length;
        Pair[] merged = new Pair[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (left[i].val <= right[j].val) {
                // All elements from right[0..j-1] are smaller than left[i]
                left[i].count += j;
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < n1) {
            left[i].count += j; // remaining left elements see all counted right elements
            merged[k++] = left[i++];
        }

        while (j < n2) {
            merged[k++] = right[j++];
        }

        return merged;
    }
}

    public static void main(String args[]) {
         int a[]={1,3,7,10,23,2,5,8,14};
         int res[]=mergeSort(a,0,a.length-1);
         for(int i=0;i<res.length;i++) {
            System.out.print(res[i]+" ");
         }
    }
}
