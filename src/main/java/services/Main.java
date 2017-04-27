package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pojos.Author;
import pojos.Publication;

public class Main {
    public static String filename = "sajs.ntt.edu.vn-04-20";

    public static String FILE_INPUT = "/Volumes/DATA/workspace/vnu/vci/git/dumpdatabase/input/20042017/" + filename + ".html";
    public static String FILE_DESTINATION = "/Volumes/DATA/workspace/vnu/vci/git/dumpdatabase/output/20042017/" + filename + ".json";

    public static String AUTHOR_REGEX_ELEMENT = ";";
    public static String AUTHOR_REGEX = "<br> ";
    List<Publication> publications = new ArrayList<Publication>();

    public static void main(String args[]) throws IOException {
        // Document doc = Jsoup.connect(LINK).get();
        Document doc = Jsoup.parse(new File(FILE_INPUT), "UTF-8");
        List<Publication> publications = new ArrayList<Publication>();
        Elements articles = doc.getElementsByTag("tr");
        for (Element articleDOM : articles) {
            Publication p = getPublicationFromAttrDOM(articleDOM);
            if (p != null) {
                publications.add(p);
            }
        }
        Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
        String jsons = gson.toJson(publications);
        String filePath = FILE_DESTINATION;
        writeJSONTextToFile(jsons, filePath);
    }

    private static void writeJSONTextToFile(String content, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < hex.length() - 1; i += 2) {
            String output = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
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
            String source = attrs.get(9).text();
            String keyword = attrs.get(7).text();
            String abstracts = attrs.get(6).text();
            String journalName = attrs.get(1).text();
            int year = -1;
            try {
                year = Integer.parseInt(attrs.get(4).text());
            } catch (NumberFormatException e) {
            }
            p = new Publication(title, abstracts, uri, authors, journalName, volume, number, year, source, usable, DOI,
                    citedList, keyword);
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
                            if (authorAttrs.length >= 3) {
                                a.setEmail(authorAttrs[2]);
                            }
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
