import java.util.ArrayList;

abstract class News{ // main purpose of making abstract class is because we do not want to create objects using this class directly.
    
    private WebScraper webScraper = new WebScraper();

    private String category;
    private ArrayList<String> titles = new ArrayList<>(); // Title of the news.
    private ArrayList<String> links = new ArrayList<>(); // Link to the news' page.
    private ArrayList<String> images = new ArrayList<>(); // Link to the images of the news.

    protected News(String category) {
        this.category = category;
        
        // Assign the fetched data to ArrayLists.
        this.assignTitlesAndLinks();
    }

    // Fetch the related category's news' titles and links.
    private void assignTitlesAndLinks() {

        this.webScraper.fetchNews(this.category);
        this.titles = this.webScraper.getTitles();
        this.links = this.webScraper.getLinks();
        this.images = this.webScraper.getImages();
    }

    protected String getDetailedText(String url) {

        return this.webScraper.fetchDetailedText(url);
    }

    protected ArrayList<String> getTitles() {

        return this.titles;
    }

    protected ArrayList<String> getLinks() {

        return this.links;
    }

    protected ArrayList<String> getImages() {

        return this.images;
    }

    // This function prints the news' titles.
    protected void printTitles() {

        for(int i = 0; i < this.titles.size(); i++) {
            System.out.println(i + 1 + " " + this.titles.get(i));
        }
    }

    // This function prints the news' links.
    protected void printLinks() {

        for(int i = 0; i < this.links.size(); i++) {
            System.out.println(i + 1 + " " + this.links.get(i));
        }
    }

    protected void printImages() {

        for(int i = 0; i < this.images.size(); i++) {
            System.out.println(i + 1 + " " + this.images.get(i));
        }
    }
}
