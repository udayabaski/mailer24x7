/**
 * 
 */
package com.nervytech.mailer24x7.mail.util;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author bsikkaya
 * 
 */
public class JSoupParser {
	
	public static String updateExternalReferencesWithS3Link(String htmlContent, Map<String,String> referencesMap) {
		
		Document doc = null;
		try {

			doc = Jsoup.parse(htmlContent);
			Elements media = doc.select("[src]");
			Elements links = doc.select("[href]");

			for (Element med : media) {
				String val = med.attr("src");
				
				if (!"#".equals(val)  && !val.startsWith("http://") && !val.startsWith("https://")) {
					//referencesMap.put(val.toLowerCase(),null);   // Will set the link later in the process
					System.out.println("Value is ===>>> "+val+" REFMAP VAL "+referencesMap.get(val.toLowerCase()));
					
					med.attr("src", referencesMap.get(val.toLowerCase()));
				}

			}

			for (Element med : links) {
				String val = med.attr("href");
				
				if (!"#".equals(val)  && !val.startsWith("http://") && !val.startsWith("https://")) {
					//referencesMap.put(val.toLowerCase(),null);  // Will set the link later in the process
					System.out.println("Value is ===>>> "+val+" REFMAP VAL "+referencesMap.get(val.toLowerCase()));
					med.attr("href", referencesMap.get(val.toLowerCase()));
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doc.html();
	}

	public static Map<String,String> getExternalReferences(String htmlContent) {

		Map<String,String> referencesMap = null;
		try {

			referencesMap = new HashMap<String,String>();

			Document doc = Jsoup.parse(htmlContent);
			Elements media = doc.select("[src]");
			Elements links = doc.select("[href]");

			for (Element med : media) {
				String val = med.attr("src");
				if (!"#".equals(val) && !val.startsWith("http://") && !val.startsWith("https://")) {
					referencesMap.put(val.toLowerCase(),"");   // Will set the link later in the process
					// med.attr("src", "arun&baskar");
				}

			}

			for (Element med : links) {
				String val = med.attr("href");
				if (!"#".equals(val)  && !val.startsWith("http://") && !val.startsWith("https://")) {
					referencesMap.put(val.toLowerCase(),"");  // Will set the link later in the process
					// med.attr("src", "arun&baskar");
				}

			}

			/*
			 * // below code is to write the modified html to the same file
			 * 
			 * PrintWriter writer = new PrintWriter(input,"UTF-8"); //
			 * System.out.println(" the total html is ====>"+doc.html());
			 * writer.write(doc.html() ) ; writer.flush(); writer.close();
			 * 
			 * // doc.appendElement(media);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("REFFFFFFFFFFFFFFFFFFF "+referencesMap);
		
		return referencesMap;
	}

	public static void main(String args[]) {
		// getExternalReferences();
	}
}
