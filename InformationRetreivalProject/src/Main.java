import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		List<YahooQuestion> list = DataSetParser.ParseJson();
		
		for (String an : list.get(0).nbestanswers){
			
			System.out.println(an);
		}

	}

}
