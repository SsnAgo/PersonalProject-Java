# README



## 一、缩进

**[强制]**使用tab缩进，不使用四个空格代替缩进

> 必须设置1个tab为4个空格的长度。

```java
// 正例
public static void countLines(List<String> list) {
    list.forEach(x -> {
        if (!x.trim().equals("")) {
            linesCount++;
        }
    });
}
```



## 二、变量命名

**[强制]**变量命名统一采用lowerCamelCase风格，必须遵从驼峰格式

```java
// 正例
private static int charactersCount = 0;
private static int linesCount = 0;
private static int wordsCount = 0;
```



## 三、每行最多字符数

**[强制]**每行最多118个字符，若超过则需换行，换行遵守下列规则

- 运算符与下文一起换行。
- 方法调用的点符号与下文一起换行。
- 在多个参数超长，逗号后进行换行。
- 在小括号前不要换行。
- 在第一个大括号后进行换行。

```java
// 正例
while ((c = fileReader.read()) != -1) {
    charactersCount++;
    if (!content) content = true;
}
```



## 四、函数最大行数

**[推荐]**函数最大行数为23行

> 若超过则应考虑分离部分功能为另一个函数



## 五、函数，类命名

**[强制]**函数采用lowerCamelCase风格

> 函数中第一个词为该函数所执行工作的动词描述，后面为操作内容描述。
>
> 带返回值的函数一般会使用get，generate等动词进行描述。

**[强制]**类统一采用首字母大写形式描述

> 类名应当使用能描述该类特性的名词

```java
// 正例
public static StringBuilder generateOutputString(Map<String,Integer> map){
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("characters: %s\nlines: %s\nwords: %s\n",charactersCount,linesCount,wordsCount));
    int i = 0;
    for (Map.Entry<String,Integer> entry :map.entrySet()){
        if (i++ < 10){
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }else break;
    }
    return sb;
}
```



## 六、常量

**[强制]** 不允许使用未定义的变量

**[强制]**常量必须经过初始化，Long类型常量赋值时使用大写的L、

```java
// 正例
Long val = 20L;
```

**[强制]**所有常量名必须全部大写，单词之间通过下划线`_`分隔

```java
// 正例
int GLOBAL_WORD = 10;
```



## 七、空行规则

**[推荐]**变量声明，执行语句之间不需要加空格，相同业务逻辑和语义之间不需要插入空行。

> 没有很大关系的函数方法之间可以按需要加入适当的空行以便区分



## 八、注释规则

**[强制]**方法，类上的注释必须使用`/*内容/`的形式，不能使用`//`

**[强制]**方法内部注释单行使用`\\xxx`，多行注释使用`/*xxx/`

**[推荐]**方法上的Javadoc中指定参数和返回值的意义

**[推荐]**能用英文注释就用英文注释，如果英文较难解释就使用中文注释，不能一部分英文一部分中文

**[推荐]**代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等的修改。

```java
/**
 * get a List contains every line in the input file
 *
 * @param filename
 * @return
 * @throws IOException
 */
public static List<String> getLineList(String filename) throws IOException {
    Path path = Path.of(filename);
    Stream<String> stringStream = Files.lines(path);
    return stringStream.collect(Collectors.toList());
}
```



## 九、操作符前后空格



**[强制]** 一元操作符前后可以不加空格。例如`++`,`--`等

```java
linesCount++;
```

**[强制]**二元，三元操作符前后必须添加一个空格

> 二元操作符例如：= ， + ，- ，< , > 等
>
> 三元操作符：例如 XX ? A : B

```java
i++ < 10
```

**[推荐]**逻辑运算符前后也应添加一个空格，便于分辨不同的表达式

> 例如 if (expr1 && expr2)



## 十、其他规则

**[推荐]**控制语句，判断语句，分支语句等带有代码块的执行流程中，第一个大括号不需要另起一行

> 这些流程的代码块第一个大括号与流程描述符处于同一行，见例子

```java
// 正例
if (i) {
    
}

// 反例
if (i)
{
    
}
```



**[强制]**所有打开的文件都必须关闭，以免造成内存泄漏，并且遵守正确的读取文件格式。

> 读取文件内容的操作一致放在try语句块中，并在finally语句块中释放文件的连接

```java
FileReader fileReader = null;
        try {
            fileReader = new FileReader(filename);
            .....
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
```

