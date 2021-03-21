## 寒假作业（2/2）
### 作业描述
>在大数据环境下，搜索引擎，电商系统，服务平台，社交软件等，都会根据用户的输入来判断最近搜索最多的词语，从而分析当前热点，优化自己的服务。首先当然是统计出哪些词语被搜索的频率最高啦，请设计一个程序，能够满足一些词频统计的需求。
### 项目地址
[Github地址](https://github.com/kepachirenguai/PersonalProject-Java/tree/main/221801418)
### 解题思路
>先把任务主要分成了读写文件、统计行数、统计字符数量、统计单词数量和统计频率这个几个部分。最开始想的是一行一行地读取文件同时记录行的数量，然后再把接收的每一行拼接成一个大的字符串去统计字符数量那些的。然后在实际做的过程中发现做出来的效果和题目的要求不相符合，如果要这样做恐怕会比较麻烦，于是把读入的全部加入到字符串中再去判断。在统计词频的过程中原本想的是用两个数组分别记录下单词和词频，写的过程中感觉很麻烦，后来在找资料的过程中发现之前学过的map可以很好地实现想法就直接用上了。

### 博客地址
[博客地址](https://www.cnblogs.com/lzkhw01/p/14483316.html)
### 代码规范
[我的代码规范](https://github.com/kepachirenguai/PersonalProject-Java/blob/main/221801418/codestyle.md)

### 实现过程
1.文件读写
读取文件代码：先判断文件是否存在如果不存在就报错，如果存在就打开输入流用BufferedReader来读取文件内容，用StringBuilder接收后转成String返回
```java
	public String readTextFile(String filePath) {			//文件的读取
		StringBuilder Text = new StringBuilder();
		try {
			File file=new File(filePath);
			if(file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),
						"UTF-8");
				BufferedReader bufferedReader = new BufferedReader(read);
				int x;
				while((x=bufferedReader.read()) != -1) {
					Text.append((char)x);
				}
				read.close();
			}else {
				System.out.println("文档不存在！");
			}
		}catch (Exception e) {
			System.out.println("打开文档出错！");
			e.printStackTrace();
		}
		return Text.toString();
	}
```
写文件的代码：这段是把统计结果合并成一个String后用输出流进行输出
```java
	public void writeTextFile(int chars,int words,int lines,
			String times,String filePath) {	//把所有统计的结果输出
		String str = "charcters:" + chars + "\nwords:" + 
			words + "\nlines"+lines + "\n" + times;
		FileOutputStream p;
		try {
			p = new FileOutputStream(filePath);
			p.write(str.getBytes());
			p.close();
		}catch (IOException e) {
			System.out.print("输出文件时出错！");
			e.printStackTrace();
		}
	}
```
2.统计行数
因为不会记录空白行，所以直接按\s把从intput读来的字符串进行分割放入String数组并统计这个数组长度，遍历String数组如果有空的就让刚刚统计的长度-1
```java
	public static int countLines(String str) {	//统计行数
		String[] LINE = str.split("\\s");	//把可能换行的地方分割出来
		int lines = LINE.length;
		for(int i = 0;i < LINE.length; i++ ) {	//减去空白行
			if(LINE[i].isEmpty()) lines-- ;
		}
		return lines;
	}
```
3.统计字符数量
这个直接把接收的字符串转成char数组，遍历数组统计能用ASCII码表示的字符数量
```java
	public static int countChars(String str) {	//统计字符数量
		int sum = 0;
		char[] cs = str.toCharArray();
		for(int i = 0;i < cs.length; i++ ) {
			if(cs[i] >= 0 && cs[i] < 128) sum++ ;
		}
		return sum;
	}
```
4.统计单词数量
这里也是用到了split来分割，因为单词内部不可能有除了字母和数字以外的字符，所以把读取的字符串按除了字母数字以外的符号分割放入String数组，再对这个数组里面的每个元素进行判断是否符合单词的要求
```java
public static int countWords(String str) {	//统计单词数量
		str=str.toLowerCase();
		String[] strArray=str.split("[^a-z0-9]+");	//把可能是单词的字符串切割出来
		
		int words = 0;
		for(int i = 0;i < strArray.length; i++ ) {
			if(strArray[i].length() < 4)continue;
			else {
				String temp=strArray[i].substring(0,4);	//检测是否符合规则
				if(temp.matches("[a-z]*")) {			
					words++;
				}
			}
		}
		return words;
	}
```
5.统计单词出现频率
这段代码先是做了和上面一段一样的事，不过不是符合单词规则就让计数器加1而是把符合规则的记录下在一个String数组中，之后遍历数组，用Map来记录单词以及它出现的频率，key是单词的字符串，value是出现的频率，之后再遍历Map找value最大的，如果value的值相同就比较key值，遍历一次就得到一个结果，把结果加入到要输出的StringBuilder里，并在Map中把那个结果删除，这样遍历10次或者直到Map中没有元素
```java
	public String countTimes(String str) {	//统计单词出现次数

		......  //这段和统计单词数量差不多不同的是用了一个List来存储所有符合条件的单词（相同的单词也会被重复apend）
		
		strArray=temp.toArray(new String[temp.size()]);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (int i = 0; i < strArray.length; i++ ) {	//把符合规则的单词存入map，key是单词，value是次数
			if(map.get(strArray[i]) == null) {	
    			map.put(strArray[i],1);
			}else{
				map.put(strArray[i],map.get(strArray[i]) + 1);
			}
		}
		
		Set<String> key = map.keySet();	//获取所有keyֵ
		StringBuilder finalstr=new StringBuilder();	//finalstr用于记录最后结果
		while(!map.isEmpty()) {	//循环比较最大value的单词记录到finalstr中
			int i = 0,maxvalue = 0;							
			String maxstr = " ";							
			for(String s:key) {
				int v = map.get(s);
				if(v > maxvalue) {
					maxvalue = v;
					maxstr =s ;
				}
				else if(v == maxvalue) {	//如果value的值相同就比较ASCII码
					if(maxstr.compareTo(s) > 0) {
						maxvalue = v;
						maxstr = s;
					}
				}
			}
			finalstr.append(maxstr + ":" + maxvalue + "\n");
			map.remove(maxstr);
			if(i == 9)break;	//如果finalstr中的单词数量已经有10个了就退出循环
		}
		return finalstr.toString();
	}
```

### 性能（文本数据包含不是单词的字符串以及空白行）
10000个字符串
![](https://img2020.cnblogs.com/blog/2286821/202103/2286821-20210304235120593-1249999379.png)

100000个字符串
![](https://img2020.cnblogs.com/blog/2286821/202103/2286821-20210304235023775-272486734.png)

不是很会分析。。。

### 单元测试
测试文本与结果
![](https://img2020.cnblogs.com/blog/2286821/202103/2286821-20210305142344828-1992063422.png)
测试文本中包含空白行、不符合单词规则的字符串、出现次数相同但是不完全相同的单词

覆盖率截图
![](https://img2020.cnblogs.com/blog/2286821/202103/2286821-20210304232800816-830128253.png)

### 异常处理
当要求读取的文件不存在时会报错
![](https://img2020.cnblogs.com/blog/2286821/202103/2286821-20210305142801349-1517399052.png)
