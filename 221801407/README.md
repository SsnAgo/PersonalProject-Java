[作业链接](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11740)
[Github项目地址](https://github.com/Poisson7/PersonalProject-Java)
刚开始拿到题目后，题目中命令行参数，统计文件中的字符数、单词总数、有效行数、单词出现次数、[字典序](https://blog.csdn.net/qq_37050329/article/details/86637183)、文件输入输出等。
使用的语言是java，命令行参数可以通过Main函数传入的参数args[]来获取，文件的输入输出通过InputStreamReader和OutputStreamWriter来读写，通过BufferedReader来一个个读取文件中的字符，最后统计的单词次数通过HashMap来存储，之后转化为ArrayList来[排序](https://www.imooc.com/article/42746)获取前十单词。
### 计算模块接口的设计与实现过程，关键代码，解释思路，独到之处
```java
lib();
void fileCount(String inputFile);//读取文件，通过BufferedReader读取字符，通过读取的字符来判断计数
void CountChar(int x )；//charCount++
void CountLine(int x)；//遇到换行符如果该行包括非空白字符则+1
void CountWord(int x)；//分隔符后若无4个英文字母则不计入单词数中，反之调用addWordMap(word)且wordCount+1
void addWordMap(String word)；//将单词转化为小写，已存在value+1，不存在word为key，value=1；
void getWordTopTen()；//转化为list，重写排序顺序
void writeFile(String outputFile)；
```
判断单词部分由于单词必须以4个英文字母开头，非字母数字符号做分隔符
```java
void CountWord(int x){
        if(((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount!=-1){
            word += (char)x;
            letterCount++;;
        }
        //前4个包含数字
        else if (((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount==-1){
            letterCount=-1;
        }
        else if(x<='9'&&x>='0'&&letterCount<4){
            letterCount=-1;
            word = "";
        }
        //前4个全是英文
        else if (x<='9'&&x>='0'&&letterCount>=4){
            word += (char)x;
        }
        //遇到分隔符
        else{
            if(letterCount>=4){
                wordCount++;
                addWordMap(word);
            }
            letterCount = 0;
            word = "";
        }
    }
```

排序代码
```java
        list = new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue().equals(o2.getValue())) {return o1.getKey().compareTo(o2.getKey());}
                else {return o2.getValue().compareTo(o1.getValue());}
            }
        });
```
写入文件代码
```java
            out = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");
            StringBuilder str = new StringBuilder();
            str.append("characters: "+charCount+"\n"
                    + "words: "+wordCount+"\n"
                    +"lines: "+lineCount+"\n");
            for(int i = 0;i<(list.size()<10 ? list.size():10);i++){
                str.append(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
            }
            out.write(str.toString());
```
### 计算模块接口部分的性能改进
### 计算模块部分单元测试展示,单元测试得到的测试覆盖率截图
#### 计算模块正确性部分
1. 测试统计字符功能
> 测试数据是ASCII码0-127
ASCII字符都能被统计出来，汉字不在测试文档中不做测试
2. 测试统计行数用例
> 测试数据是" "+"\n"+"\t"+"\n"+"\r"+"\n"
仅存在空白字符的行数不会被统计
3. 测试单词数
> 测试用例 file123 1file fil1e
以上都不为单词
4. 测试单词前10
> 测试用例
测试数据"aaaa2,aaaa20,aaaa30,aaaa19\n"*循环次数5次，末尾append("aaaa9\n")
```java
    for(int i=0;i<str.length();i++){
        lib.CountWord(str.charAt(i));
    }
    lib.getWordTopTen();
    String str1 = "";
    String str2 = "aaaa19:5\naaaa2:5\naaaa20:5\naaaa30:5\naaaa9:1\n";
    for(int i=0;i<lib.list.size();i++){
        str1+= lib.list.get(i).getKey()+":"+lib.list.get(i).getValue()+"\n";
    }
    Assert.assertEquals(str2,str1);
```
