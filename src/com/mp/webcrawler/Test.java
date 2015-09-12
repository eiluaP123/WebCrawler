package com.mp.webcrawler;

public class Test {

	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		if (args.length > 0){
			String word = args[0];
			String url = args[1];
			crawler.crawl(word, url);
		}
	}
}
