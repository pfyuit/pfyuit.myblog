package com.pfyuit.myblog.utils;

import org.jsoup.Jsoup;

public class BlogUtils {

	public static String getAbstract(String content) {
		String result = "";
		content = Jsoup.parse(content).text();
		if (content.length() > 500) {
			result = content.substring(0, 500) + "...";
		} else {
			result = content;
		}
		return result;
	}
}
