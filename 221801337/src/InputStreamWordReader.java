import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamWordReader implements WordReader {
	private BufferedReader br;
	private long wordNumber = 0L;
	private long charNumber = 0L;
	private long lineNumber = 0L;
	//用来判断当前的换行前是否为空，因为是按流读取，所以这么麻烦
	//值为1代表是在上一次next的单词结尾的分隔符为换行
	//值为0代表在这次next中遇到过换行
	//值为-1代表未遇到过换行
	private long hasEnter = -1L;
	//用来判断文件是否结束
	//因为文件结尾可能有换行，可能没有，但是最后一行无论有没有换行都是一行
	//所以在遇到EOF时不结束，而是将EOF(-1)换成换行、end置为true，在下次循环中跳出
	private boolean end = false;

	public InputStreamWordReader() {
	}

	public InputStreamWordReader(InputStream is) {
		setInputStream(is);
	}

	public void clear() {
		wordNumber = 0;
		charNumber = 0;
		lineNumber = 0;
		hasEnter = -1;
		end = false;
	}

	//获取下一个潜在的合法单词
	//即包含数字和字母的连续字符串
	public String getNextWord() {
		StringBuffer buffer = new StringBuffer();
		//单个字符
		int c;
		//出现过非分隔符
		boolean hasLetter = false;
		//按分隔符分出单词候选
		try {
			while (!end) {
				c = br.read();
				//如果不在文件结尾，正常记录字符
				if (c != -1) {
					charNumber++;
				} else if (c == -1) {//到文件结尾，不记录EOF，但是要判断换行，所以将EOF替换成换行，且记录文件结束
					c = '\n';
					end = true;
				}
				//非数字、字符为换行符号，进入分隔符处理
				if (!(this.isLetter(c) || Character.isDigit(c))) {
					//记录有效行，当前行有出现过非分隔符，为有效行
					if ((c == '\n' || c == '\r')) {
						//这个换行为单词后的第一个换行，要将这个换行记录到下次next中
						if (hasLetter == true) {
							lineNumber++;
							hasEnter = 1;
						} else if (hasEnter == -1) {//这个换行为遇到单词前的换行，直接记录这个换行，且不需要记录到下次next中
							lineNumber++;
							hasEnter = 0;
						}
					}
					//出现过字母且现在又出现了分隔符，跳出循环
					if (hasLetter) {
						break;
					}
				} else {
					hasLetter = true;
					//如果是单词后换行，在下次next中变为单词前换行(1->0)
					//如果是单词前换行，下次next并不被影响(0->-1)
					if (hasEnter > -1) {
						hasEnter -= 1;
					}
					buffer.append((char) c);
				}
			}
			if (end == true) br.close();
		} catch (IOException e) {
			System.out.println("IO异常，文件可能被删除");
			return null;
		}
		//没有出现过字符，即到文件尾，返回空
		if (hasLetter == false) return null;
		else return buffer.toString();
	}

	@Override
	public String nextWord() {
		//0代表当前行为空
		while (true) {
			//获取下一个单词
			String word = getNextWord();
			if (word == null) return null;
			//判断是否是单词，不是就继续读，是则返回
			if (isWord(word)) {
				wordNumber++;
				return word.toLowerCase();
			}
		}
	}

	//判断是否是单词，即开头字母>=4
	public boolean isWord(String s) {
		//判断结果
		boolean res = true;
		//出现数字
		boolean hasDigit = false;
		//出现数字前字符的长度
		int len = 0;

		char[] c = new char[s.length()];
		s.getChars(0, s.length(), c, 0);
		for (int i = 0; i < c.length; i++) {
			//出现字符
			if (this.isLetter(c[i])) {
				//如果现在未出现过数字，字符长度加一
				if (hasDigit == false) {
					len++;
				}
			} else {//出现数字
				hasDigit = true;
			}
		}
		if (len < 4) res = false;
		return res;
	}

	@Override
	public boolean setInputStream(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		clear();
		return true;
	}

	private boolean isLetter(int c) {
		if (c >= 65 && c <= 90 || c >= 97 && c <= 122) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long getLineNumber() {
		return lineNumber;
	}

	@Override
	public long getWordNumber() {
		return wordNumber;
	}

	@Override
	public long getCharNumber() {
		return charNumber;
	}
}
