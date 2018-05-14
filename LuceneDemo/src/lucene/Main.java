package lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;



public class Main {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		
		File inputFile = new File("C:/Users/Shachar/Google Drive/Education/Information Retrieval/xml parse test/dataxml.xml");
		
		DataXMLParser.ParseXML(inputFile);

		
		
		
	}

	
	

}
