### 代码规范制定
***
* 缩进
缩进采用Tab键，相当于四个空格。
```
public static void main(String args[]){
    int flag=1;
    ……
}
```
* 变量命名
	* 变量均以字母开头，不能使用下划线开头。当表示连接时，变量中可穿插下划线，例如new_sno来表示新的学生学号。变量结尾只能是字母或者数字。变量名需要使用能代表其含义的英文名称或普遍被接受的惯例命名。如cnt表示计数器，i表示循环体内的次数。
	* 对于类的实例采用驼峰命名法，例如
```
LineNumberReader lineNumberReader=new LineNumberReader(new FileReader(file));
```
* 每行最多字符数
单行字符数限制不超过 **120**个，超出换行。换行时带换行的点符号或者箭头符号一起换行。换行相对第一行缩进四个空格，后面与第二行缩进保持一致。
```
public function sendEmail($email)
    {
        return Yii::$app->mailer->compose()
            ->setTo($email)
            ->setFrom([$this->email => $this->name])
            ->setSubject($this->subject)
            ->setTextBody($this->body)
            ->send();
    }
```
* 函数最大行数
单个函数不超过50行，同时里面不能包含对于另一个函数的嵌套定义。
* 函数、类命名
普通函数采用小驼峰命名法，构造函数和类命名采用大驼峰命名法。同时在普通函数命名时，最好体现函数的作用，包含get，set，delete等动作含义的单词。
* 常量
常量命名时使用全大写字母，单词之间使用下划线分隔，表达完成常量意思。
* 空行规则
函数与函数之间用空行隔开；代码块之间变量的定义和使用之间也使用空行隔开。
* 注释规则
单行文本采用//进行注释，多行则采用/** …… **/进行注释。对于函数和代码功能的解释通常在最开头。
```
/**
 * 对书籍的查询
 * @author Bobbbby
 *
 */
public class BooksDao {

	public ResultSet list(Connection con,Books book)throws Exception{
		StringBuffer sb=new StringBuffer("select * from Books");
		……
}
```
* 操作符前后空格
个人习惯操作符前后都不加空格。
```
method("a","b","c");
```
* 其他规则
	*将左大括号放在行尾，而将右大括号放在行首。
	```
	函数返回类型 function(参数表) {
函数体；
}
```