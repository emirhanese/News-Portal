public class Science extends News{

    private String category;

    protected Science(String category) {
        super(category);
        this.category = category;
    }
    
    @Override
    protected void printCategory() {
        
        System.out.println("Category: " + this.category);
    }
    
    // Overloading the printCategory function of the News class.
    protected void printCategory(int newsCount) {

        System.out.println("There are " + newsCount + " news in " + this.category + " category");
    }
}
