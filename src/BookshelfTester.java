// Name: Xiaofeng Luo
// USC NetID: luoxiaof
// CSCI455 PA2
// Fall 2021


import java.util.ArrayList;


/**
 * Class BookshelfTester
 *
 * This class tests the constructors and all the functions of  Bookshelf class.
 */
public class BookshelfTester {

   /**
    * Main method to test the constructors and all the functions of  Bookshelf class
    *
    * @param args
    */
   public static void main(String[] args) {

   //test the two constructors and toString
   Bookshelf bookshelf1 = new Bookshelf();

   ArrayList<Integer> books = new ArrayList<Integer>();
   books.add(1);
   books.add(2);
   books.add(5);
   books.add(25);
   Bookshelf bookshelf2 = new Bookshelf(books);

   System.out.println("Call the 1st constructor:");
   System.out.println("Books on the shelf [exp: []]: " + bookshelf1.toString());
   System.out.println();

   System.out.println("Call the 2nd constructor:");
   System.out.println("Books on the shelf [exp: [1, 2, 5, 25]]: " + bookshelf2.toString());
   System.out.println();

   //test addFront, addLast, removeFront, removeLast
   System.out.println("After adding a book with height 7 to the front of the empty shelf: ");
   bookshelf1.addFront(7);
   System.out.println("Books on the shelf [exp: [7]]: " + bookshelf1.toString());
   System.out.println();

   System.out.println("After adding a book with height 1 to the front of the empty shelf: ");
   bookshelf1.addFront(1);
   System.out.println("Books on the shelf [exp: [1, 7]]: " + bookshelf1.toString());
   System.out.println();

   System.out.println("After adding a book with height 27 to the last of the same shelf: ");
   bookshelf1.addLast(27);
   System.out.println("Books on the shelf [exp: [1, 7, 27]]: " + bookshelf1.toString());
   System.out.println();

   System.out.println("After removing the front book on the same shelf: ");
   bookshelf1.removeFront();
   System.out.println("Books on the shelf [exp: [7, 27]]: " + bookshelf1.toString());
   System.out.println();

   System.out.println("After removing the last book on the same shelf: ");
   bookshelf1.removeLast();
   System.out.println("Books on the shelf [exp: [7]]: " + bookshelf1.toString());
   System.out.println();

   //test getHeight, size, isSorted
   System.out.println("Tests on the shelf with books [1, 2, 5, 25]: ");
   System.out.println("Get the height of the book at position 1 [exp: 2]: " + bookshelf2.getHeight(1));
   System.out.println("Get number of the books on the shelf [exp: 4]: " + bookshelf2.size());
   System.out.print("Is this shelf sorted? [exp: Yes.]: ");
   if(bookshelf2.isSorted()) {
      System.out.println("Yes.");
   }


   }
}
