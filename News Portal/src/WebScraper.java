import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

final class WebScraper {
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private String detailedText;

    // This function fetches the news' titles, images and links.
    public void fetchNews(String category) {

        try {
            String url = "https://www.bbc.com/news/" + category;
            Document doc = Jsoup.connect(url).get();

            Elements container = doc.select("li.lx-stream__post-container:has(div.lx-stream-related-story--index-image-wrapper)");

            Elements newsTitles = container.select("a.qa-heading-link > span.lx-stream-post__header-text");
            Elements newsLinks = container.select("h3.lx-stream-post__header-title > a.qa-heading-link");
            Elements imageLinks = container.select("div.lx-stream-related-story--index-image-wrapper > img");
    
            for(Element title:newsTitles) {
                this.titles.add(title.text().replaceAll("[‘’]" , ""));
            }

            for(Element link:newsLinks) {
                if(!link.attr("href").startsWith("https://www.bbc.com") && !link.attr("href").startsWith("https://www.bbc.co.uk")) {
                    String new_link = "https://www.bbc.com" + link.attr("href");
                    this.links.add(new_link);
                }
    
                else {
                    this.links.add(link.attr("href"));
                }
            }

            for(Element image: imageLinks) {
                this.images.add(image.attr("src"));
            }
            
        }
    
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // This function fetches the full text of the news.
    public String fetchDetailedText(String url) {

        try {
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("article p.ssrcss-1q0x1qg-Paragraph:not(:has(b))");

            if(paragraphs.text().contains("This video can not be played")) {
                this.detailedText = paragraphs.text().replace("This video can not be played", "");
            }

            else {
                this.detailedText = paragraphs.text();
            }
        }

        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return this.detailedText;
    }

    public ArrayList<String> getTitles() {

        return this.titles;
    }

    public ArrayList<String> getImages() {

        return this.images;
    }

    public ArrayList<String> getLinks() {

        return this.links;
    }

}
