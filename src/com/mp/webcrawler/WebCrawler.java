package com.mp.webcrawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	private List<String> url_list = new LinkedList<String>();

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private Document htmlDoc;
	
	public boolean crawl(String url){
		try {
			Connection conn = Jsoup.connect(url).userAgent(USER_AGENT);
			htmlDoc = conn.get();
			if(conn.response().statusCode() == 200){
				System.out.println("Received web page at " +url);
			}
			if(!(conn.response().contentType().contains("text/html"))){
				System.out.println("Retrieved something other than HTML");
				return false;
			}
			Elements linksOnPage = htmlDoc.select("a[href]");
			System.out.println("Found " +linksOnPage.size() + " links");
			for (Element link : linksOnPage){
				url_list.add(link.absUrl("href"));
			}
			return true;
			}
		catch(IOException e){
			System.out.println("Error in the HTTP request " + e);
			return false;
		}
	}
	
	public boolean searchForWord(String word){
		if(this.htmlDoc == null){
			System.out.println("Call the crawl function first!");
			return false;
		}
		System.out.println("Searching for " + word);
		String docText = htmlDoc.body().text();
		return docText.toLowerCase().contains(word.toLowerCase());
	}
	
	public List<String> getUrl_list() {
		return url_list;
	}
	
}
