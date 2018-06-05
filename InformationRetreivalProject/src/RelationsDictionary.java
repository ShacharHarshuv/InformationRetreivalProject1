import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RelationsDictionary {
	
	private ArrayList<String> targetWords;
	private Map<String, HashMap<String, Word>> relations;
	
	public RelationsDictionary(){
		relations = new HashMap<String, HashMap<String, Word>>(); 
	}
	
	public HashMap<String, Word> GetRelatedWords(String word){
		try{
			return relations.get(word);
		}
		catch(Exception e){
			return new HashMap<String, Word>();
		}
	}
	
	public void setTargetWords(ArrayList<String> words){
		targetWords = words;
	}
	
	public void addRelation(String relatedWord){
		//IDEA - implement the relation with two direction - aRb iff bRa
		for (String word : targetWords){
			HashMap<String, Word> relatedWords;
			
			relatedWords = relations.get(word);
			
			if (relatedWords == null){
				//if the entry does not exist - add one
				relatedWords = new HashMap<String, Word>();
				relations.put(word, relatedWords);
			}
			
			//Add the relation to the word
			try{
				//if entry already exist, simply put more weight.
				relatedWords.get(relatedWord).weight++;
			}
			catch(Exception e){
				//if entry doesn't exist, initialize it with weight 1
				relatedWords.put(relatedWord, new Word(relatedWord, 1));
			}	
		}
	}
}
