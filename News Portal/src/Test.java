<<<<<<< HEAD
public class Test {
    
    public static void main(String[] args) {
        
        Entertainment entertainment = new Entertainment("entertainment_and_arts");
        entertainment.printCategory();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printCategory(entertainment.getTitles().size());
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printTitles();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printLinks();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printImages();
    }
}
=======
public class Test {
    
    public static void main(String[] args) {
        
        Entertainment entertainment = new Entertainment("entertainment_and_arts");
        entertainment.printTitles();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printLinks();
        System.out.println("-------------------------------------------------------------------------------------------------------");
        entertainment.printImages();
    }
}
>>>>>>> e738f41c06b102a1fb09a402663a7d32c14cac64
