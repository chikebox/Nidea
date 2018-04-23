package com.ipartek.formacion.nidea.ejemplos.graficos;

import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParserJsoup {

	public static void main(String[] args) throws IOException {
		Document doc= Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println("Titulo:" + title);
		doc.getElementsByTag("a");

	}

}
