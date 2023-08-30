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
同Java

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

### JS中的继承
原型 Prototype


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