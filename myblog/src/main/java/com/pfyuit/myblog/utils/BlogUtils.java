package com.pfyuit.myblog.utils;

import org.jsoup.Jsoup;

public class BlogUtils {

	public static String getAbstract(String content) {
		String result = "";
		content = Jsoup.parse(content).text();
		if (content.length() > 200) {
			result = content.substring(0, 200) + "...";
		} else {
			result = content;
		}
		return result;
	}
}
