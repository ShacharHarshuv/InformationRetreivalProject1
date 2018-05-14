package lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneTest {
	
	public static void mainTest() throws IOException{
		Collection<Document> Corpus = LoadDocuments();
		
		Analyzer enAnalyzer = new EnglishAnalyzer();
		IndexWriter iw = CreateIndex(enAnalyzer);
		/*
		for (Document doc : Corpus){
			iw.addDocument(doc);
		}*/
		iw.commit();
		iw.close();
		
		
		String queryTxt = "son of a bitch";
		Query query = QueryParse(queryTxt, enAnalyzer);
		
		DirectoryReader dr = DirectoryReader.open(iw.getDirectory()); 
		IndexSearcher is = new IndexSearcher(dr);
		is.setSimilarity(new ClassicSimilarity());
		TopDocs result = is.search(query, 3);
		
		ScoreDoc[] docs = result.scoreDocs;
		IndexReader ir = is.getIndexReader();
		
		for (ScoreDoc document : docs){
			System.out.print("Result document: " + document.doc + ", ");
			System.out.println("Score: " + document.score);
			Document storedDoc = ir.document(document.doc);
			System.out.println("title: " + storedDoc.get("title"));
		}
		
		System.out.println("I finished");
		
	}
	

	private static Query QueryParse(String queryText, Analyzer anaylyzer){
		QueryParser parser = new QueryParser("body", anaylyzer);
		try{
		//escape special characters from query
		return parser.parse(QueryParser.escape(queryText));
		}
		catch(ParseException e){
			System.out.println("Exception occured!");
		}
		return null;
		
	}
	
	private static String ReadFileText(String path) throws IOException
	{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String str = new String(data, "UTF-8");
		
		return str;
	}
	private static String GetFileName(String path){
		File file = new File(path);
		return file.getName();
	}
	
	private static void ReadFile(Collection<Document> Corpus, File file){
		System.out.println("Hello");
	}
	
	private static Collection<Document> LoadDocuments() throws IOException{
		
		ArrayList<Document> Corpus = new ArrayList<Document>();
		
		String path = "C:\\Users\\Shachar\\Google Drive\\Education\\Information Retrieval\\TestCorpus1\\";
		List<Path> filesPaths = 
		Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
		
		for (Path p : filesPaths) {
			Document doc = new Document(); 
			doc.add(new StringField("title", GetFileName(p.toString()), Store.YES));
			doc.add(new TextField("body", ReadFileText(p.toString()), Store.YES));
			
			Corpus.add(doc);
		}
		
		return Corpus;
	}
	private static IndexWriter CreateIndex(Analyzer analyzer) throws IOException{
		
		Similarity tfidfSim = new ClassicSimilarity();
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		conf.setSimilarity(tfidfSim);
		Directory dir = FSDirectory.open(Paths.get("C:\\Users\\Shachar\\Google Drive\\Education\\Information Retrieval\\Index"));
		//incremental indexing
		conf.setOpenMode(OpenMode.CREATE_OR_APPEND);
		//create an index writer
		return new IndexWriter(dir, conf);
	}
}
