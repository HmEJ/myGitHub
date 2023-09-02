## JavaScript的引入
在早期没有引入JS时，网页的一切数据校验都由浏览器发送到服务器，最后在服务器中进行校验。

**特点：**
1. JavaScript运行在浏览器端，它是阻断式解释执行的*脚本语言*
2. 弱类型语言：声明变量的时候不需要指定变量类型    `var a = 50;` 
3. 主要作用：实现前端的数据校验
4. 是一门*基于对象*和*事件驱动*的语言
5. 是一门动态语言：程序在运行期间仍然可以动态改变结构
6. *JavaScript = ECMAScript 语法 + BOM + DOM*

## JavaScript入门
1. JavaScript 可以写在head标签里，也可以写在body中
2. 一个方法可以被多个事件调用。一个事件可以调用多个方法
3. 一个script标签只能做一件事： 引入外部JS文件或者写在script标签中

## ECMA语法
### 输入和输出

输出：
	警示框 `alert("message here")`
	控制台输出：
		`console.log() //日志`
		`console.warn() //警告`
		`console.error() //错误`
		`console.info() //普通信息 `
输入：警示框 `prompt("message here")`
	使用`let`关键字接收输入信息： `let name = prompt("您的姓名：")`
### 变量

变量： 先声明再赋值 然后再使用
```js
const age = 20; // 创建一个常量
let age; age = 23; //创建一个局部变量
var age = 25; //创建一个成员变量
=======================================
function test04(){
	str = "今天是8月29号"
	alert(str)
}
function test05(){
	alert(str)
}
/* 👆 虽然str定义在function04中，但是funciton05仍可调用该变量。这就是存在一定的安全隐患。 */
```
一个变量在声明的时候，如果没有加任何声明关键字，那么一旦该变量被使用过一次，该变量就会由局部变量变为成员变量。从而造成安全隐患。
### JS的数据类型
###### 普通数据类型： 3个基本，2个特殊
`number`
`string`
`boolean`
`null`
`undefined`
###### 引用数据类型：
`String `
`Object`
`Array`

### 流程控制 
同[[3-Java SE 入门#^64eb84|Java流程控制]]

### 运算符

算术运算符 : `+ - * /`
逻辑运算符 : `&& || ! `
赋值运算符 : `= += -=`
关系运算符 : `> < >= <= == != ===` 
三元运算符 : `X ? Y : Z`

#### JS中相等性运算

JavaScript 提供三种不同的值比较运算：
- [`===`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Strict_equality)——严格相等（三个等号）
- [`==`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Equality)——宽松相等（两个等号）
- [`Object.is()`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/is)

 选择哪个运算取决于你需要什么样的比较。简单来说：

- 在比较两个操作数时，双等号（`==`）将执行类型转换，并且会按照 IEEE 754 标准对 `NaN`、`-0` 和 `+0` 进行特殊处理（故 `NaN != NaN`，且 `-0 == +0`）；
- 三等号（`===`）做的比较与双等号相同（包括对 `NaN`、`-0` 和 `+0` 的特殊处理）但不进行类型转换；如果类型不同，则返回 `false`；
- `Object.is()` 既不进行类型转换，也不对 `NaN`、`-0` 和 `+0` 进行特殊处理（这使它和 `===` 在除了那些特殊数字值之外的情况具有相同的表现）。

#### 普通变量默认转换方法：
`Number() `  
- 可将布尔类型的`true`转为1，`false`转为0
- 可将内容为数字的字符串转为`number`类型数字。如果字符串内容不是一个正确的数字格式，则返回`NaN`
`parseInt()/parseFloat()`
- 可将字符串转为`number`类型，从左到右，有一点就转一点。
### 方法

JavaScript中定义方法的三种格式如下：
```js
//声明式
function 方法名(参数列表){
	//方法体
}
-----------------------------------
//赋值式
let 方法名 = function(参数列表){
	//方法体
}
-----------------------------------
//new对象式
let 方法名 = new Function('方法体');  //注意Function要大写
```

##### JS中的方法返回值
JavaScript中的返回值，想要返回值就写 `return 返回值;`  不想要就不写。
返回值的类型没有限制，想返回什么都可以。
##### JS中方法名的命名规则
同Java 遵循小驼峰原则。
### JS中的参数
**JavaScript中没有重载机制：**
JavaScript只有一个全局作用区域，后面声明的同名的方法或变量会覆盖之前的方法或变量 。

>例如，你先声明了一个`test01()`的无参方法，之后又声明了一个`test01(a)`的有参方法。在之后调用此方法时，JS将总是调用后一个有参的`test01(a)`方法。 
### JS中的数组
创建数组的四种方式：
```js
//创建一个空数组
let arr = new Array();
arr[0]=1;

//创建定长数组
let arr2 = new Array(5);
arr2[0] = 'x';
arr2[1] = true;

//直接指定元素
let arr3 = new Array('asd',10,1,true);

//直接指定元素方式的简写
let arr4 = ['asd',10,1,true];
```
> `[]`就相当于`new Array()` . 因此，考虑如下问题：
> `let arr = [];`
> `console.log([]==[])`  
> 以上控制台打印的结果为`false`。
> 因为 一个`[]`代表一个数组对象，`[]==[]` 就相当于两个数组对象进行比较，他们的地址值不一样，因此最终结果为`false`。

### JS中的对象
考虑如下代码：
```js
/*1.*/
function test02(){
	let obj = new Object();
	console.log(obj);
	obj.empno = 7521;
	obj.ename = '张三';
	obj.show = function(){
		console.log(obj.empno+";"+obj.ename);
	}
	obj.show(); 
}
/*2.以上代码可写为如下格式*/
function test02(){
	let obj = {};
	console.log(obj);
	obj.empno = 7521;
	obj.ename = '张三';
	obj.show = function(){
		console.log(obj.empno+";"+obj.ename);
	}
	obj.show(); 
}
/*3.亦可写为如下格式*/
function test02(){
	let obj = {empno:7521,ename:'张三'};
	console.log(obj);
	obj.show = function(){
		console.log(obj.empno+";"+obj.ename);
	}
	obj.show(); 
}
```
>👆实际上第三种格式的`obj`就是一种类似`JSON`格式的对象
>`JSON`是 `JavaScript Object Notation `的缩写。中文名为`JS对象表示`
>`JSON`是轻量级的文本数据交换格式
>标准的`JSON`格式如下( 属性名 和 值 都要双引 )：
>	JSON对象：
>	`let obj = { "stuName":"张三" };`
>	JSON集合：
>	`let arr = [{"stuName":"张三","stuAge":"21"}]`

### JS中的封装
封装一个Student类，考虑如下代码：
```js
/*创建学生类*/
function Student(stuno,stuname,age,gender){
	this.stuno = stuno;
	this.stuname = stuname;
	this.age = age;
	this.gender = gender;
	
	this.show = function(){
		console.log(this.stuname+"的学生编号是"+this.stuno);
	}
	this.study = function(){
		console.log(this.stuname+"正在背SE面试题")
	}
}
function main(){
	let stu = new Student(511,'莫航',21,'男');
	let stu01 = new Student(510,'戴耀',21,'女')
	stu.show();
	stu01.study();
}
main();
```
>`this.属性` 表示当前对象的属性值。 

### prototype 原型
在JavaScript中，每个对象都有一个与之相关联的原型对象（prototype object）。原型对象是对象的属性和方法的集合，它可以被其他对象继承，从而实现对象之间的共享属性和方法。
1. 原型链(prototype chain)
	JavaScript中的对象之间通过原型链连接在一起。每个对象都有一个指向其原型对象的链接。如果某个属性或方法在当前对象上找不到，JavaScript会沿着原型链向上搜索，直到找到匹配的属性或方法，或者达到原型链的顶端（通常是`Object.prototype`）
2. 构造函数(constructor)
	构造函数是用于创建对象的函数。构造函数可以具有一个原型对象，该原型对象定义了该构造函数创建的对象的共享属性和方法。构造函数通常以大写字母开头，以便区分普通函数
3. 原型属性(prototype property)
	构造函数可以通过将属性或方法添加到它们的原型对象上，使所有由该构造函数创建的对象都共享这些属性和方法。这样可以节省内存，因为所有实例共享同一个原型对象，而不是每个实例都复制一份
考虑如下示例：
```js
// 定义一个构造函数
function Person(name, age) {
  this.name = name;
  this.age = age;
}

// 在构造函数的原型对象上添加一个方法
Person.prototype.sayHello = function() {
  console.log(`Hello, my name is ${this.name} and I am ${this.age} years old.`);
};

// 使用构造函数创建对象
const person1 = new Person("Alice", 30);
const person2 = new Person("Bob", 25);

// 调用共享的方法
person1.sayHello(); // 输出：Hello, my name is Alice and I am 30 years old.
person2.sayHello(); // 输出：Hello, my name is Bob and I am 25 years old.

```

>1. 所有的内建对象都遵循相同的模式（pattern）：
>>a. 方法都存储在 prototype 中（`Array.prototype`、`Object.prototype`、`Date.prototype` 等）
>>b. 对象本身只存储数据（数组元素、对象属性、日期）
>2. 原始数据类型也将方法存储在包装器对象的 prototype 中：`Number.prototype`、`String.prototype` 和 `Boolean.prototype`。只有 `undefined` 和 `null` 没有包装器对象
>3. 内建原型可以被修改或被用新的方法填充。但是不建议更改它们。唯一允许的情况可能是，当我们添加一个还没有被 JavaScript 引擎支持，但已经被加入 JavaScript 规范的新标准时，才可能允许这样做

### JS中的继承
JS中有三种方式实现继承：
1. 原型继承
- 原型继承是最常见的继承方式。它使用原型链连接对象
- 每个对象都有一个原型对象。通过`prototype`属性访问
- 子对象可以通过将其原型设置为父对象来继承父对象的属性和方法
```js
//创建父类构造器
function Parent(name){
	this.name = name;
}
//使用原型为父类添加方法
Parent.prototype.greet = function(){
	console.info("hello iam "+this.name);
}
//创建子类构造器
function Child(name,age){
	this.age = age;
	//调用父类构造器
	Parent.call(this,name);
}
//让子类原型指向父类原型，完成继承
Child.prototype = Object.create(Parent.prototype);
//创建子类对象
const child = new Child("mh",21);
//调用继承而来的方法。
child.greet();
```
>以上继承代码中出现了一个方法 `call( thisArg, agr1, arg2,... )`
>`call()`方法是JS中的一个内置方法。它是用于调用函数的方法，它允许你在调用函数时***指定函数内部的`this`值及传递函数所需要的参数***。
>其基本语法如下： `function.call( thisArg, agr1, arg2,... )`
>	`function` 是要调用的函数
>	`thisArg` 是**要调用的函数内部的`this`值**，即函数执行时的上下文。你可以传递任何对象作为`thisArg`，该对象将成为函数执行时的`this`
>	`arg1`, `arg2`, ... 是要传递给函数的参数

2. 类继承(ES6之后的方法)
- ES6引入了类和`extends`关键字，提供了清晰的面向对象编程语法
- 使用`class`定义类，使用`extends`实现继承，可以继承父类的属性和方法
```js
//创建父类
class Parent{
	//定义父类构造器
	constructor(name){
		this.name = name;
	}
	//生成父类的方法
	greet(){
		console.info("hello,my nmae is "+this.name);
	}
}
//创建子类 通过extends关键字继承父类
class Son extends Parent{
	//定义子类构造器
	constructor(name,age){
		super(name);
		this.age = age;
	}
}
//生成子类对象
const child = new Son("ss",5);
//调用继承而来的方法
child.greet(); //控制台输出 hello,my nmae is ss
```

3. `Object.create()`
- `Object.create()`方法可以创建一个新对象，将指定对象作为新对象的原型
- 这种方式**不涉及构造函数**，但可以实现对象之间的继承
```js
// 父对象
const parent = {
  name: "Alice",
  greet: function() {
    console.log(`Hello, my name is ${this.name}`);
  }
};

// 子对象继承父对象
const child = Object.create(parent);
child.age = 5;

// 子对象可以调用父对象的方法
child.greet(); // 输出：Hello, my name is Alice

```

> 以上三种继承的方式，可以根据项目需求和个人偏好选择适合的方法。**类继承通常是最现代和推荐的方式**，但原型继承和`Object.create()`也是有效的

### JS中的事件
事件的定义：
	可以被浏览器侦测到的人或者浏览器本身的行为,人对浏览器或者浏览器对网页做了什么事,JS可以根据不同行为.绑定一些不同的响应代码处理,让浏览器和人的行为之间有一个交互
#### 常用事件类型：
##### 1.鼠标事件
- `onclick`  当用户点击某个对象时调用的事件句柄
- `oncontextmenu` 在用户点击鼠标右键打开上下文菜单时触发
- `ondbclick` 当用户双击某个对象时调用的事件句柄
- `onmousedown` 鼠标按钮被按下
- `onmouseenter` ***当鼠标指针移动到元素上时触发***
- `onmouseleave` ***当鼠标指针移出元素时触发***
- `onmouseomve` 鼠标被移动
- `onmouseover` *鼠标移到某元素之上* （支持冒泡）
- `onmouseout` *鼠标从某元素移开*  （支持冒泡）
- `onmouseup` 鼠标按键被松开

这四个事件处理器属性都与鼠标的进入和离开元素有关，但它们在触发时机和事件冒泡方面有所不同。以下是它们之间的区别：
1. **`onmouseover`**：
    
    - 当鼠标指针进入元素（包括子元素）时触发。
    - 事件冒泡：当鼠标从子元素移动到父元素时，会触发子元素的 `onmouseover` 事件，然后再触发父元素的 `onmouseover` 事件。
    - 常用于在鼠标进入元素时触发某些效果。
2. **`onmouseenter`**：
    
    - 当鼠标指针进入元素的边界时触发。
    - 不冒泡：不会触发其他子元素的 `onmouseenter` 事件。
    - 常用于在鼠标进入元素时触发效果，且不希望冒泡的情况。
3. **`onmouseout`**：
    
    - 当鼠标指针离开元素（包括子元素）时触发。
    - 事件冒泡：当鼠标从子元素移动到父元素时，会触发子元素的 `onmouseout` 事件，然后再触发父元素的 `onmouseout` 事件。
    - 常用于在鼠标离开元素时触发某些效果。
4. **`onmouseleave`**：
    
    - 当鼠标指针离开元素的边界时触发。
    - 不冒泡：不会触发其他子元素的 `onmouseleave` 事件。
    - 常用于在鼠标离开元素时触发效果，且不希望冒泡的情况。

总的来说，`onmouseenter` 和 `onmouseleave` 更适合在需要精确控制鼠标进入和离开元素时使用，而 `onmouseover` 和 `onmouseout` 更适合需要考虑事件冒泡的情况。选择适合的事件取决于你的需求以及你希望如何处理鼠标与元素的交互。
##### 2.键盘事件
- `onkeydown`  某个键盘按键被按下
- `onkeypress`  某个键盘按键被按下并松开
- `onkeyup`  某个键盘按键被松开
##### 3.表单事件
- `onblur` 元素失焦时触发
- `onchange`  表单元素内容改变时触发
- `onfocus`  元素获取焦点时触发
- `onfocusin`  元素即将获取焦点时触发 （支持冒泡）
- `onfocusout`  元素即将失去焦点时触发 （支持冒泡）
- `oninput`  元素获取用户输入时触发
- `onreset`  表单重置时触发
- `onsearch`  向搜索域输入文本时触发 (`<input="search">`)
- `onselect`  用户选取文本时触发  (`<input>` 和 `<textarea>`)
- `onsubmit` 表单提交时触发

### JS中事件的阻止
`事件=return false`

## BOM 
`Browser Object Model` 浏览器对象模型 
顶级对象是 `windows`
`window`对象属性

![[sfsfs.png]]
`window`对象方法

![[Snipaste_2023-08-30_17-43-19.png]]
### 定时器

考虑以下代码，演示两种定时器：
```js
/*该代码每隔1s 将在控制台输出一个倒计时。倒计时5次后会关闭当前页面。*/
function windowClose() {
	var countdown = 5;
	countdownInterval = window.setInterval(function() {
		if (countdown > 0) {
			console.info("倒计时：" + countdown);
			countdown--;
		} else {
			window.close();
			clearInterval(countdownInterval);
		}
	}, 1000);
}
```
```js
/*以下代码设置在控制台输出closing字样并在5秒后关闭本页面*/
function closee(){
	console.info("closing。。。")
	window.setTimeout(function(){
		window.close();
	},1000*5)
}
```

### location对象

location对象,是window对象的一个属性,代表浏览器上URL地址栏,使用location对象可以操作地址栏

考虑如下代码：
```js
function getInfo(){
	console.info(location.host); //域名＋端口
	console.info(location.hostname); //域名
	console.info(location.href); //地址栏中的信息
	console.info(location.port); //端口
	location.href="https://www.baidu.com/?wd=今日天气"
}
```

👆以上代码会在控制台输出域名，端口，链接信息。通过调用`location`的`href`属性还可以直接改变链接的值，实现跳转。

### history对象

考虑如下代码：
```js
function test02(){
	window.history.go(-1);  //向后跳转 history.back()
}
function test03(){
	window.history.go(1);  //向前跳转 history.forward()
}
```

### screen和navigator对象
screen代表屏幕,navigator代表浏览器软件本身,通过这两个对象可以获得屏幕和浏览器软件的一些信息
```js
function ttt(){
	console.info(window.screen.width)
	console.info(window.screen.height)
	console.info(navigator.userAgent)
	console.info(navigator.appName)  //该方法已废弃
}
```

👆 以上代码 获取的是屏幕的宽度和高度信息，以及显示当前浏览器用户代理字符串（包含有关浏览器、操作系统和设备的信息）和 显示浏览器的名称

## DOM
### 获取节点对象
直接：
```js
//通过id 获取元素
document.getElementById("aid").href="https://www.baidu.com"
//通过name 获取元素
document.getElementsByName("sname")[0].innerText ="teacherName";
//通过class属性 获取元素
document.getElementsByClassName("acla")[0].style.color="deepskyblue";
```

间接：
### 操作属性
系统属性可以在获取元素后直接获得
```js
let obj = document.getElementsByTagName("input")[2];
console.info(obj.name);
console.info(obj.id);
console.info(obj.type);
console.info(obj.value);  
```
自定义属性通过`setAttribute() / getAttribute() `方法来设置和获取
```js
let val = obj.getAttribute("sdfsdf");   
console.log(val); 
obj.setAttribute("niub","牛逼");   //K-V形式存储属性名和属性值
console.log(obj.getAttribute("niub"));
```
### 操纵外观属性
```js
document.getElementById("tttt").style.overflow="";
```

### 操纵文档结构
```js
//创建文本框对象
let txt = document.createElement("input");
//设置对象的value属性值
txt.value =parseInt(Math.random()*20) ;
//创建按钮
let del_btn = document.createElement("button");
//设置按钮双标签间的文本内容
del_btn.innerText="删除";
============================================
//添加子元素
div.appendChild(txt);
============================================
//删除子元素
div.removeChild(txt);
```

### 表单
表单是非常特殊的一个HTML组件。在JS中表单不同于其他组件。
#### 获取form表单
```js
//获取form对象1
let form = document.getElementsByTagName("form")[0];
//获取form对象2
let form = document.myform;   // 最简便
//获取form对象3
let form = document.getElementById("myform")
```
#### 获取表单元素
```js
//获取表单元素1
let val = document.getElementsByName("wd")[0];
//获取表单元素2
let val = document.myform.wd;   //最简便 
//获取表单元素3
let val = document.getElementById("wd");
```

## JQuery ：write less, do more
	JQuery是JavaScript的类库
`JQuery`的主要目的是为了简化`Js`的`DOM`模块, 支持独立开发，防止硬编码。

`JQuery`**语法格式**: 
`$("选择器").方法(参数)`
`$("DOM对象").方法(参数)`
`$("HTML语句").方法(参数)`


窗体加载事件：
`$(document).ready( ... )`
`$("body").ready( ... )`

### JQuery选择器
同[[2-CSS#^61de39|CSS选择器]]。
考虑如下代码：
```js
$(document).ready(function(){
	$("td").css("background-color","aquamarine");
	$("#td01").css("background-color","indianred");
	$("td#td01").css("color","whitesmoke");
	$("td[id]").css("font-size","30px"); //选择td标签下所有具有id属性的元素
	$("td[id = 'td01']").css("background-color","black"); //属性选择器
	$("td:eq(5)").css("background-color","yellow");  //索引为5 的  equals
	$("td:gt(5)").css("background-color","yellow"); //索引5之后的  不带5 great than
	$("td:lt(5)").css("background-color","pink"); //索引5之前的  不带5 less than
	$("td:even").css("background-color","cyan"); //even偶数   odd奇数
	/*为元素添加/移除现成的CSS样式*/
	$("td").addClass("color")   // 使用addClass()方法。参数为CSS选择器名
	$("td").removeClass("color") //使用removeClass()方法移除CSS样式
});
```

### JQuery 获取属性
考虑如下代码：
```js
alert($("table").css("height")); //获取CSS属性：高度
$("input").val(333);  //给input标签的value赋值
alert( $("input").val()); //获取标签的value值
/*获得文本属性*/
alert($("table").text());
alert($("table").html());
/*获得其他属性(自定义属性)*/
alert($("img").attr("src"));
```

### JQuery修改标签结构
考虑如下代码：
```js
$(document).ready(function(){
	//1.设置按钮单击事件
	let money = 0;
	$("button").click(function(){
		let val = parseInt(Math.random()*100+1);
		//创建标签对象
		$(
			"<input disabled='disabled' type='text' value='"+val+"'/>"+
			"<input type='button' value='删除' />"+
			"<br>"
		).appendTo( $("div") );
		
		money = val+money;
		$("span").text("总价:"+money+"元");
		
	});
	//2.为即将显示的删除按钮添加单击事件: on(事件名称,标签对象,方法)
	$(document).on("click","input[value='删除']",function(){
		//$(this)代指当前标签对象
		let a = $(this).prev().val();  //获取删除标签前一个标签的value值
		money = money-a;
		$("span").text("总价:"+money+"元");
		$(this).prev().remove();
		$(this).next().remove();
		$(this).remove();  //我删我自己	
	});
});
```