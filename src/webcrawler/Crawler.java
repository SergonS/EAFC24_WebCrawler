package webcrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler 
{
	static String URL_MAIN = "https://www.fut.gg";
	static String URL_PLAYERS = "https://www.fut.gg/players/";
	
	public static void main(String[] args) 
	{
		getPlayers(URL_PLAYERS);
		
		/*
		try
		{
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if (con.response().statusCode() == 200)
			{
				for (Element row : doc.select("div.-my-1"))
				{
					System.out.println(row);
				}
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		*/
	}
	
	private static void getPlayers(String url)
	{
		Document doc = request(url);
		
		if (doc != null)
		{				
			for(Element row : doc.select("div.-my-1"))
			{
				for (Element card : row.select("a"))
				{
					String relHref = card.attr("href");
					String cardLink = URL_MAIN + relHref;
					
					Document cardDocument = request(cardLink);
					
					getCardInfo(cardDocument);
				}
			}
		}
	}
	
	private static void getCardInfo(Document card)
	{
		// Getting name in card
		String name = card.select("h1").first().text();
		System.out.println(name);
		
		// Getting player's full name
		String details = card.select("div.paper.mb-3").select("div.flex.justify-between.mt-2").text();
		System.out.println(details);
	}
	
	
	private static Document request(String url)
	{
		try
		{
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if (con.response().statusCode() == 200)
			{
				System.out.println("Visiting website: " + url);				
				return doc;
			}
			return null;
		}
		catch(IOException e)
		{
			return null;
		}
	}
}