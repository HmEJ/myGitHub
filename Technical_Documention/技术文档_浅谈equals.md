### 浅谈equals
`==` 比较的是运行时常量池中两个元素的值。对于基本数据类型是，值就是内容本身，因此只要内容一样，`==`的结果就是true。对于引用数据类型，运行时常量池中存储的是其地址值，因此，即使是两个元素内容一样，但是地址不一样的话，`==`的值就会是false.
1. **Object中的equals**
    ```
    public boolean equals(Object obj) {  
    return (this == obj);  
    }
    ```
    Object中的equals很简单，使用`==`进行比较，比较的是地址。
2. **String中的equals**
    ```
    public boolean equals(Object anObject) {  
        if (this == anObject) {  
            return true;    //地址值相等，一定为true
        }  
        return (anObject instanceof String aString)  //判断是否是同一类型
                && (!COMPACT_STRINGS || this.coder == aString.coder)  
                && StringLatin1.equals(value, aString.value);  
    }
    ```
    代码使用`instanceof`操作符来判断`anObject`是否是`String`类型的实例，即判断`anObject`是否也是一个字符串对象。如果`anObject`不为`String`类型的实例，或者为`null`，那么直接返回`false`，因为一个字符串对象不可能与非字符串对象相等。代码调用`StringLatin1.equals`方法，该方法是一个静态方法，用于比较两个字符串的内容是否相等。如果内容相同，则返回`true`，否则返回`false`。

   `StringLatin1.equals`方法：
    ```
    public static boolean equals(byte[] value, byte[] other) {  
        if (value.length == other.length) {  
            for (int i = 0; i < value.length; i++) {  
                if (value[i] != other[i]) {  
                    return false;  
                }  
            }  
            return true;  
        }  
        return false;  
    }
    ```
    可以看到该代码是直接遍历字符串，将其中的值取出来比较。比较的是内容。
3. **Arrays中的equals**
    ```
    public static boolean equals(long[] a, long[] a2) {  
        if (a==a2)  
            return true;  
        if (a==null || a2==null)  
            return false;  
      
        int length = a.length;  
        if (a2.length != length)  
            return false;  
      
        return ArraysSupport.mismatch(a, a2, length) < 0;   
    }
    ```
4. **重写equals**

   ***为什么要重写`equals`方法？***
   
   前面已经提到，`Object`中的`equals`是直接使用`==`来进行比较，对于引用数据类型，这是比较的地址值。当我们使用`String`或者`Arrays`时，这些类已经为我们提前重写了`equals`方法使得我们可以比较对象的内容。可是对于我们自定义的类呢？如果我们不重写`equals`方法，在使用`equals`时则是默认调用`Object`中的`equals`方法 ( ~~毕竟`Object`是所有类的祖宗~~ ) ，而这个`equals`并不是我们想要的。因此，我们需要重写`equals`方法。
   ```
   public class Person {  
       private String name;  
       private int age;  
     
       //无参构造  
       public Person() {  
     
       }  
     
       public Person(String name, int age) {  
           this.name = name;  
           this.age = age;  
       }  
     
       public String getName() {  
           return name;  
       }  
     
       public int getAge() {  
           return age;  
       }  
     
       @Override  
       public String toString() {  
           return "Person{" +  
                   "name='" + name + '\'' +  
                   ", age=" + age +  
                   '}';  
       }  
     
       //重写equals方法  
       @Override  
       public boolean equals(Object obj) {  
           //判断内存地址  
           if (obj == this) {  
               return true;  
           }  
           if (obj == null) {  
               return false;  
           }  
           //判断是否是同一类型的对象  
           if (obj instanceof Person) {  
               //强制转换成Person类型  
               Person per = (Person) obj;  
               //判断他们的属性值    
               if (this.name.equals(per.name) && this.age == (per.age)) {  
                   return true;  
               }  
           }  
           return false;  
       }  
   }
   ```
   在以上例子中，创建了一个`Person`类，该类有两个属性，分别是`String`类型的`name`和`int`类型的`age`。如果要比较两个人信息是否相等，就需要重写`equals`。在重写方法中，首先判断两个对象的内存地址相同，如果连地址都相同，就代表他们是同一个对象，那么内容一定相同，因此返回`true`。接着判断比较的对象是否为空，如果为空，则比较无意义，返回`false`，二者不相等。接着，如果两个对象内存地址不同，并且都不为空，那么开始判断二者是否是同一类型，如果不是同一类型，也是没有比较的必要了。直接返回`false`。如果是同一类型，为了让`Object`能够调用`Person`的成员变量和成员方法，这样就可以进行内容比较，所以向下转型。之后就开始判断他们的内容咯，内容就是姓名和年龄嘛。这里因为姓名是`String`类型的，我们可以使用`JAVA`开发者们已经提前帮我们写好了的String类下的equals来比较内容。又因为`age`是`int`类型，这是一个基本数据类型，所以直接用`==`来比较内容。当二者内容都相等时，就代表我们的目的达成了，这两个对象内容相同，返回true。至此，我们重写了自定义`Person`类下的`equals`方法，使其能完成比较两个`Person`类对象是否内容相同的功能。