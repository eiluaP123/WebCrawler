package com.mp.webcrawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Crawler {
	private static final int MAX_PAGES = 10;
	private List<String> pagesToVisit = new LinkedList<String>();
	private Set<String> pagesVisited = new HashSet<String>();
	private boolean found = false;
	public void crawl(String word, String url){
		while(this.pagesVisited.size() < MAX_PAGES){
			String curr_url;
			WebCrawler webCrawler = new WebCrawler();
			if(this.pagesToVisit.isEmpty()){
				pagesVisited.add(url);
				curr_url = url;
			}
			else {
				curr_url = nextURL();
			}
			webCrawler.crawl(curr_url);
			found = webCrawler.searchForWord(word);
			if(found){
				System.out.println("Word " + word + " found at " + curr_url);
				break;
			}
			this.pagesToVisit.addAll(webCrawler.getUrl_list());
		}
		System.out.println("Visted " + pagesVisited.size() + " webpages");		
	}

	public String nextURL(){
		String next;
		do{
			next = this.pagesToVisit.remove(0);
		}while(this.pagesVisited.contains(next));
		this.pagesVisited.add(next);
		return next;
	}
}
