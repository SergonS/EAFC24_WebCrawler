package webcrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler 
{
	static String URL_MAIN = "\"https://www.fut.gg";
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
				for (Element player : row.select("a"))
				{
					String relHref = player.attr("href");
					String playerLink = URL_MAIN + relHref;
					
					System.out.println(playerLink);
				}
			}
		}
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