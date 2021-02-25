import java.util.ArrayList;
import java.util.List;

public class Core {
	private WordReader wr;
	private TrieTree tt=new TrieTree();
	private List<String> list = new ArrayList<>();

	public Core(){

	}
	public Core(WordReader wr){
		this.wr=wr;
	}
	public void start(){
		String s;

		while ((s=wr.nextWord())!=null){
			list.add(s);
			tt.add(s);
		}
	}
	public String next(){
		String s = wr.nextWord();
		tt.add(s);
		return s;
	}

	public WordReader getWordReader() {
		return wr;
	}
	public void setWordReader(WordReader wr) {
		this.wr = wr;
	}
	public int getCharNumber(){
		return wr.getCharNumber();
	}
	public int getWordNumber(){
		return wr.getWordNumber();
	}
	public int getLineNumber(){
		return wr.getLineNumber();
	}
	public TrieTree getTrieTree() {
		return tt;
	}

	public void setTrieTree(TrieTree tt) {
		this.tt = tt;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("characters: " + wr.getCharNumber()+"\n" );
		res.append("words: " + wr.getWordNumber() +"\n" );
		res.append("lines: " + wr.getLineNumber()+"\n");
		return res.toString();
	}
}
