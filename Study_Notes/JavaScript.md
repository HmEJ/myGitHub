## JavaScript的引入
在早期没有引入JS时，网页的一切数据校验都由浏览器发送到服务器，最后在服务器中进行校验。

**特点：**
1. JavaScript运行在浏览器端，它是阻断式解释执行的*脚本语言*
2. 弱类型语言：声明变量的时候不需要指定变量类型    `var a = 50;` 
3. 主要作用：实现前端的数据校验
4. 是一门*基于对象*和*事件驱动*的语言
5. 是一门动态语言：程序在运行期间仍然可以动态改变结构
6. *JavaScript = ECMAScript 语法 + BOM + DOM**

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
number
string
boolean
null
undefined
###### 引用数据类型：
String 
Object
Array

### 流程控制 
同Java

