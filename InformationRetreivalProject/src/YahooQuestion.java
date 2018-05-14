import java.util.List;

public class YahooQuestion {
	public String main_category;
	public String question;
	public List<String> nbestanswers;
	public int id;
	
	@Override
	public String toString(){
		return "qid = " + id + "\n" + question;
	}
}
