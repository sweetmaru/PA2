import java.util.ArrayList;


public class BookshelfKeeperTester {

   public static void main(String[] args) {

      //test the two constructors and toString
      Bookshelf shelf1 = new Bookshelf();

      ArrayList<Integer> books = new ArrayList<Integer>();
      books.add(1);
      books.add(2);
      books.add(5);
      books.add(25);
      Bookshelf shelf2 = new Bookshelf(books);

      BookshelfKeeper keeper1 = new BookshelfKeeper();
      BookshelfKeeper keeper2 = new BookshelfKeeper(shelf2);

      System.out.println("Call the 1st constructor:");
      System.out.println("Books on the shelf and # of calls [exp: [] 0 0]: " + keeper1.toString());
      System.out.println();

      System.out.println("Call the 2nd constructor:");
      System.out.println("Books on the shelf and # of calls [exp: [1, 2, 5, 25] 0 0]: " + keeper2.toString());
      System.out.println();

      //test pickPos, putHeight and toString
      System.out.println("Test on the contained shelf with books [1, 2, 5, 25]:");
      System.out.println();

      System.out.println("Pick the book at position 2:"); //regular position
      keeper2.pickPos(2);
      System.out.println("Updated bookshelf and # of calls [exp: [1, 2, 25] 3 3]: " + keeper2.toString());
      System.out.println();

      System.out.println("Pick the book at position 0:"); //the front book
      keeper2.pickPos(0);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 25] 1 4]: " + keeper2.toString());
      System.out.println();

      System.out.println("Put the book with height 5:"); //regular position
      keeper2.putHeight(5);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 5, 25] 3 7]: " + keeper2.toString());
      System.out.println();

      System.out.println("Put the book with height 5:"); //duplicate height
      keeper2.putHeight(5);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 5, 5, 25] 3 10]: " + keeper2.toString());
      System.out.println();

      System.out.println("Put the book with height 2:"); //duplicate height
      keeper2.putHeight(2);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 2, 5, 5, 25] 1 11]: " + keeper2.toString());
      System.out.println();

      System.out.println("Put the book with height 5:"); //duplicate height
      keeper2.putHeight(5);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 2, 5, 5, 5, 25] 3 14]: " + keeper2.toString());
      System.out.println();

      System.out.println("Put the book with height 2:"); //duplicate height
      keeper2.putHeight(2);
      System.out.println("Updated bookshelf and # of calls [exp: [2, 2, 2, 5, 5, 5, 25] 1 15]: " + keeper2.toString());
      System.out.println();

      //test getTotalOperations and getNumBooks
      System.out.println("Toatl number of mutators calls made [exp: 15]: " + keeper2.getTotalOperations());
      System.out.println("Number of books on the shelf [exp: 7]: " + keeper2.getNumBooks());

      System.out.println("***************************************");

      keeper1.putHeight(2);
      keeper1.putHeight(5);
      System.out.println(keeper1.toString());
      keeper1.putHeight(8);
      System.out.println(keeper1.toString());
      keeper1.putHeight(1);
      System.out.println(keeper1.toString());
      keeper1.pickPos(3);
      System.out.println(keeper1.toString());
      keeper1.pickPos(0);
      System.out.println(keeper1.toString());
      keeper1.pickPos(0);
      System.out.println(keeper1.toString());
      keeper1.pickPos(0);
      System.out.println(keeper1.toString());
   }


}
