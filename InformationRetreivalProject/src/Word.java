
public class Word {
	
	public String word;
	public int weight; 
	
	public Word(String _word, int _weight){
		word = _word;
		weight = _weight;
	}
	
	public String toString(){
		return "[" + word + ", " + weight + "]";
	}
}
