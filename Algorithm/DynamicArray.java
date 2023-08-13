public class DynamicArray {  
    public static void main(String[] args) {  
        int a = 10;     //数组长度
        int[] array = new int[a];   //定义一个初始数组，数据被初始化为0  
        Scanner in = new Scanner(System.in);    
        System.out.println("输入第1个元素(输入0结束):");  
        int count = 0;  //计数器 记录数据个数
        int inPut = in.nextInt();  
        while (inPut!=0){     //循环输入数据，输入0结束
            if (array[array.length-1]!=0){ //数组扩容条件：旧数组最后一个数不为0
                a*=2;   
                int[] newArray = new int[a]; //创建一个新数组，长度为旧数组两倍
                newArray = Arrays.copyOf(array, a); //将旧数组数据填入新数组
                array = newArray; //消除旧数组 将新数组地址引入旧数组
            }else {  //不满足扩容条件，继续往旧数组填入数据
                array[count] = inPut;  
                count++;  
            }  
            System.out.println("输入第"+(count+1)+"个元素(输入0结束)：");  
            inPut = in.nextInt();  
        }  
        String result = Arrays.toString(array);    //将数组转为字符串
        System.out.print("[");  
        //截取有数据的数组部分(没数据的部分被初始化为0)
        for (int i = 0;array[i]!=0; i++) { 
            if (i!=count-1){    //如果指针不在有数据数组最后一位
                System.out.print(array[i]+",");  
            }else {  
                System.out.print(array[i]);  
            //上述步骤为了去除输出内容的最后一个,
            }  
        }  
        System.out.print("]");  
    }  
}