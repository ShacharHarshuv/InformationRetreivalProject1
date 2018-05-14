package lucene;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.lucene.document.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataXMLParser {
	
	public static Collection<Document> ParseXML(File inputFile) throws SAXException, IOException, ParserConfigurationException{
		 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         org.w3c.dom.Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         
         NodeList nList = doc.getElementsByTagName("DOC");
         
         for (int i = 0; i < nList.getLength(); i++){
        	 System.out.println(nList.item(i).getTextContent());
         }
         
         
         return null;
	}
}
