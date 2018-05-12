import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class DataSetParser {
	
	public static List<YahooQuestion> ParseJson() 
	{
		//List<YahooQuestion> yq = new List<YahooQuestion>();
		
		BufferedReader br = null;
		try{
			Gson gson = new GsonBuilder().create();
			JsonReader jr = new JsonReader(new FileReader("C:/Users/Shachar/Google Drive/Education/Information Retrieval/WebScope6Yahoo/nfL6.json/nfL6.json"));
			YahooQuestion[] qs = gson.fromJson(jr, YahooQuestion[].class);
			return Arrays.asList(qs);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		
		return null;
	}
}
