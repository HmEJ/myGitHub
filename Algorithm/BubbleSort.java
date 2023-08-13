public class BubbleSort {  
    public static void main(String[] args) {  
        int[] a = {51,65,89,115,2,3,265,65};  
        System.out.println("排序前："+Arrays.toString(a));  
        for (int i = 1; i < a.length ; i++) {  
            for (int j = 0; j < a.length-1 ; j++) {  
                int temp=0;  
                if (a[j]>a[j+1]){  
                    temp=a[j];  
                    a[j]=a[j+1];  
                    a[j+1]=temp;  
                }  
            }  
        }  
        System.out.println("排序后："+Arrays.toString(a));  
    }  
}
