package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import pojos.Author;
import pojos.Publication;

public class Main {
	public static String FILE_INPUT = "/Users/sonnguyen/Desktop/jed.edu.vn.html";
	public static String FILE_DESTINATION = "/Users/sonnguyen/Desktop/aticles.json";

	public static String AUTHOR_REGEX_ELEMENT = ";";
	public static String AUTHOR_REGEX = "<br> ";
	List<Publication> publications = new ArrayList<Publication>();

	public static void main(String args[]) throws IOException {
//		Document doc = Jsoup.connect(LINK).get();
		Document doc = Jsoup.parse(new File(FILE_INPUT), "UTF-8");
		List<Publication> publications = new ArrayList<Publication>();
		Elements articles = doc.getElementsByTag("tr");
		for (Element articleDOM : articles) {
			Publication p = getPublicationFromAttrDOM(articleDOM);
			if (p != null) {
				publications.add(p);
			}
		}
		String jsons = new Gson().toJson(publications);
		String filePath = FILE_DESTINATION;
		writeJSONTextToFile(jsons, filePath);
	}

	private static void writeJSONTextToFile(String content, String filePath) {
		File file = new File(filePath);
		try (FileOutputStream fop = new FileOutputStream(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = content.getBytes("UTF-8");
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Publication getPublicationFromAttrDOM(Element articleDOM) {
		Publication p = null;
		Elements attrs = articleDOM.getElementsByTag("td");
		if (attrs.size() == 12) {
			String volume = attrs.get(2).text();
			String uri = attrs.get(9).text();
			boolean usable = false;
			String number = attrs.get(3).text();
			String citedList = getReferencesByText(attrs.get(8).toString());
			List<Author> authors = getAuthorsByText(attrs.get(11).toString());
			String DOI = attrs.get(10).text();
			String title = attrs.get(0).text();
			String source = attrs.get(2).text();
			String abstracts = attrs.get(6).text();
			String journalName = attrs.get(1).text();
			int year = -1;
			try{
				year = Integer.parseInt(attrs.get(4).text());
			}catch(NumberFormatException e){}
			p = new Publication(title, abstracts, uri, authors, journalName, volume, number, year, source, usable, DOI,
					citedList);
		}
		return p;
	}

	private static List<Author> getAuthorsByText(String text) {
		List<Author> auths = new ArrayList<Author>();
		if (!text.isEmpty()) {
			String authorsText = text.replaceAll("<td>|</td>", "");
			String[] authors = authorsText.split(AUTHOR_REGEX);
			if (authors.length > 0) {
				for (String authorTex : authors) {
					if (!authorTex.isEmpty()) {
						String[] authorAttrs = authorTex.split(AUTHOR_REGEX_ELEMENT);
						if (authorAttrs.length >= 2) {
							Author a = new Author(authorAttrs[0]);
							a.setAffiliation(authorAttrs[1]);
							auths.add(a);
						}
					}
				}
			}
		}
		return auths;
	}

	private static String getReferencesByText(String text) {
		String pubs = "";
		if (!text.isEmpty()) {
			pubs = text.replaceAll("<td>|</td>", "").replaceAll(AUTHOR_REGEX, "\n");
		}
		return pubs;
	}

}
