import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutionException;

public class HugeDataCore {
	private File inputFile;
	private File files[];
	private static final long MINSIZE = 1024 * 1024;
	private int divNum;
	private File cacheDir;
	private boolean div = false;
	private long topCount = 10;
	private List<Node> topKList = new ArrayList<>();
	private int eachFileSize;

	private long wordNumber = 0;
	private long charNumber = 0;
	private long lineNumber = 0;


	private PriorityQueue<Node> pq = new PriorityQueue<>();

	public HugeDataCore(File inputFile) {
		this.inputFile = inputFile;
		this.eachFileSize = 50;
		this.cacheDir = new File("./");
		if (inputFile.length() > MINSIZE * this.eachFileSize) {
			div = true;
		}
	}

	public HugeDataCore(File inputFile, int eachFileSize) {
		this.inputFile = inputFile;
		this.cacheDir = new File("./");
		this.eachFileSize = eachFileSize;
		if (inputFile.length() > MINSIZE * this.eachFileSize) {
			div = true;
		}
	}

	private void divFile() {
		try {
			if (div == false) {
				files = new File[1];
				files[0] = inputFile;
				divNum = 1;
			} else {
				divNum = Math.toIntExact(inputFile.length() / (MINSIZE * eachFileSize) + 1);
				FileWriter fw[] = new FileWriter[divNum];
				files = new File[divNum];
				for (int i = 0; i < divNum; i++) {
					cacheDir.mkdirs();
					files[i] = new File(cacheDir.getPath() + "/" + i + ".tmp");
					fw[i] = new FileWriter(files[i]);
				}

				WordReader wr = new InputStreamWordReader(new FileInputStream(inputFile));
				String s = new String();
				while ((s = wr.nextWord()) != null) {
					fw[Math.abs(s.hashCode()) % divNum].write(s + " ");
				}
				for (FileWriter writer : fw) {
					writer.close();
				}
				this.charNumber = wr.getCharNumber();
				this.wordNumber = wr.getWordNumber();
				this.lineNumber = wr.getLineNumber();
			}
		} catch (FileNotFoundException e) {
			System.out.println("分割文件时未找到文件");
		} catch (IOException e) {
			System.out.println("分割文件时IO错误");
		}
	}

	public void start() throws ExecutionException, InterruptedException {
		divFile();
		Core core = null;
		for (int i = 0; i < files.length; i++) {
			core = new Core();
			WordReader wr = new InputStreamWordReader();
			try {
				wr.setInputStream(new FileInputStream(files[i]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			core.setWordReader(wr);
			core.start();
			for (Node node : core.getTopK(topCount)) {
				pq.add(node);
				if (pq.size() > topCount) pq.poll();
			}
		}
		if (div == false) {
			this.charNumber = core.getCharNumber();
			this.wordNumber = core.getWordNumber();
			this.lineNumber = core.getLineNumber();
		}
		while (!pq.isEmpty()) {
			topKList.add(pq.poll());
		}
		Collections.reverse(topKList);
		if (div == true) {
			for (File file : files) {
				file.delete();
			}
		}
	}

	public File getInputFile() {
		return inputFile;
	}

	public File getCacheDir() {
		return cacheDir;
	}

	public void setCacheDir(String cacheDir) {
		this.cacheDir = new File(cacheDir);
	}

	public long getWordNumber() {
		return wordNumber;
	}

	public long getCharNumber() {
		return charNumber;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public long getTopCount() {
		return topCount;
	}

	public void setTopCount(int topCount) {
		this.topCount = topCount;
	}

	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("characters: " + this.getCharNumber() + "\n");
		res.append("words: " + this.getWordNumber() + "\n");
		res.append("lines: " + this.getLineNumber() + "\n");

		for (Node node : topKList) {
			res.append(node.string + ": " + node.value + "\n");
		}
		return res.toString();
	}
}
