// Name: Xiaofeng Luo
// USC NetID: luoxiaof
// CSCI455 PA2
// Fall 2021


import java.util.ArrayList;

/**
 * Class TestAssert
 *
 * This class tests the assert statements in Bookshelf class.
 */
public class TestAssert {

   /**
    * Main method to test the assert statements
    *
    * @param args
    */
   public static void main(String[] args) {

      ArrayList<Integer> books = new ArrayList<Integer>();
      books.add(1);
      books.add(0);
      System.out.println("It will crash below if we run with the -ea flag.");
      Bookshelf bookshelf = new Bookshelf(books);
      System.out.println("Books on the shelf if we run without the flag [exp: [1, 0]]: " + bookshelf.toString());


   }
}
