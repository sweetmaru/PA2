// Name: Xiaofeng Luo
// USC NetID: luoxiaof
// CSCI455 PA2
// Fall 2021


import java.util.ArrayList;

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
      Representation invariant:

      --The height of all the books should be a postive integer.
      --The position number should be a non-negative integer and less than the number of books on the shelf.

   */
   

   private Bookshelf bookshelf;
   private int lastCalls; //the number of bookshelf mutator calls made to perform the last pick or put operation
   private int totalCalls; //the total number of mutator calls made since we created this BookshelfKeeper
   private int numtaken; //number of books needed to be taken from the contained bookshelf to the temporary bookshelf
   private int pickposition; //position of the book which will be picked from the contained bookshelf


   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {

      bookshelf = new Bookshelf();

      assert isValidBookshelfKeeper();

   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {

      bookshelf = sortedBookshelf;

      assert isValidBookshelfKeeper();

   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {

      pickposition = position;

      int endposition = minPickEnd(position);

      if(endposition == 0){
         pickFromFront();
      }
      else pickFromLast();

      lastCalls = count(); //count number of mutator calls for this one pick operation
      totalCalls += lastCalls; //count the total mutator calls

      assert isValidBookshelfKeeper();

      return lastCalls;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {

      int endposition = minPutEnd(height);

      if(endposition == 0){
         putFromFront(height);
      }
      else putFromLast(height);

      lastCalls = count(); //count number of mutator calls for this one pick operation
      totalCalls += lastCalls; //count the total mutator calls

      assert isValidBookshelfKeeper();

      return lastCalls;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {

      assert isValidBookshelfKeeper();

      return totalCalls;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {

      assert isValidBookshelfKeeper();

      return bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {

      assert isValidBookshelfKeeper();

      return bookshelf.toString() + " " + lastCalls + " " + totalCalls;
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      for(int i = 0; i < bookshelf.size(); i++){
         //the size of bookshelf has reduced by 1 after picking, so pickposition should larger than
         //bookshelf.size() in case that we picked the book of the last index
         if(bookshelf.getHeight(i) <= 0 || pickposition < 0 || pickposition > bookshelf.size()){
            return false;
         }
      }
      return true;

   }

   // my private helper methods

   /**
    * Counts for the number of mutator calls made by one pick or put operation.
    *
    * @return the number of mutator calls made by one pick or put operation
    */
   private int count(){

      return numtaken * 2 + 1;

   }


   /**
    * Chooses the end leading to a minimum number of mutator calls for pick operations.
    *
    * @param position position of the book which will be picked from the contained bookshelf
    *
    * @return the position of the end leading to a minimum number of mutator calls
    */
   private int minPickEnd(int position){

      if(position < bookshelf.size() - 1 - position){
         numtaken = position;
         return 0;
      }
      else{
         numtaken = bookshelf.size() - 1 - position;
         return bookshelf.size() - 1;
      }

   }


   /**
    * Chooses the end leading to a minimum number of mutator calls for put operations.
    *
    * @param height height of the book which will be put into the contained bookshelf
    *
    * @return the position of the end leading to a minimum number of mutator calls
    */
   private int minPutEnd(int height){

      int putPosition1 = bookshelf.size(); //possible position to put certain book from the front
      int putPosition2 = bookshelf.size(); //possible position to put certain book from the last

      //check the possible put positions from each side considering the multiple copies
      for(int i = 0; i <= bookshelf.size() - 1; i++){
         if(bookshelf.getHeight(i) >= height){
            putPosition1 = i;
            break;
         }
      }

      for(int j = bookshelf.size() - 1; j >= 0; j--){
         if(bookshelf.getHeight(j) <= height){
            putPosition2 = j + 1;
            break;
         }
      }

      //compare the two position and choose the one leading to the minimum mutator calls
      if(putPosition1 < (bookshelf.size() - putPosition2) ||
            putPosition1 == 0){
         numtaken = putPosition1;
         return 0;
      }
      else{
         numtaken = bookshelf.size() - putPosition2;
         return -1;
      }
   }


   /**
    * Picks the certain book from the front of the contained bookshelf.
    */
   private void pickFromFront() {

      ArrayList<Integer> temp = new ArrayList<Integer>(); //temporary shelf to store the books taken

      //remove and store the books taken to the temporary shelf
      for (int i = 0; i < numtaken; i++) {
         temp.add(bookshelf.removeFront());
      }

      //pick the book
      bookshelf.removeFront();

      //put back the books taken after picking
      for(int i = 0; i < numtaken; i++){
         bookshelf.addFront(temp.get(i));
      }

   }


   /**
    * Picks the certain book from the last of the contained bookshelf.
    */
   private void pickFromLast(){

      ArrayList<Integer> temp = new ArrayList<Integer>(); //temporary shelf to store the books taken

      //remove and store the books taken to the temporary shelf
      for(int i = 0; i < numtaken; i++){
         temp.add(bookshelf.removeLast());
      }

      //pick the book
      bookshelf.removeLast();

      //put back the books taken after picking
      for(int i = numtaken - 1; i >= 0; i--){
         bookshelf.addLast(temp.get(i));
      }

   }


   /**
    * Puts the certain book from the front of the contained bookshelf.
    *
    * @param height height of the book which will be put into the contained bookshelf
    */
   private void putFromFront(int height){

      ArrayList<Integer> temp = new ArrayList<Integer>(); //temporary shelf to store the books taken

      //remove and store the books taken to the temporary shelf
      for (int i = 0; i < numtaken; i++) {
         temp.add(bookshelf.removeFront());
      }

      //put the book
      bookshelf.addFront(height);

      //put back the books taken after picking
      for(int i = 0; i < numtaken; i++){
         bookshelf.addFront(temp.get(i));
      }

   }


   /**
    * Puts the certain book from the last of the contained bookshelf.
    *
    * @param height height of the book which will be put into the contained bookshelf
    */
   private void putFromLast(int height){

      ArrayList<Integer> temp = new ArrayList<Integer>(); //temporary shelf to store the books taken

      //remove and store the books taken to the temporary shelf
      for(int i = 0; i < numtaken; i++){
         temp.add(bookshelf.removeLast());
      }

      //put the book
      bookshelf.addLast(height);

      //put back the books taken after picking
      for(int i = numtaken - 1; i >= 0; i--){
         bookshelf.addLast(temp.get(i));
      }

   }


}
