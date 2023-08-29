public class QuickSort {
    public static void main(String[] args) {
        int[] a = {51,654,98,110,2,53,60,78};
        sort(a,0,a.length-1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }

    }
    static void sort(int[] a,int low,int high){
        if (low<high){
            /* pivotPos 基准点位置 */
            int pivotPos = partition(a, low, high);
            sort(a, low, pivotPos-1);
            sort(a, pivotPos+1, high);
        }
    }
    static int partition(int[] a,int low,int high){
        int pivot = a[low];
        while (low<high){
            while (low<high&&a[high]>=pivot){
                high--;
            }
            a[low] = a[high];
            while (low<high&&a[low]<=pivot){
                low++;
            }
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }
}
