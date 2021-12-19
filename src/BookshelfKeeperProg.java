// Name: Xiaofeng Luo
// USC NetID: luoxiaof
// CSCI455 PA2
// Fall 2021


import java.util.ArrayList;
import java.util.Scanner;


/**
 * Class BookshelfKeeperProg
 *
 * This class does error checking for the inputs and allows the user to do a series of put and pick operations on the
 * given bookshelf.
 *
 */
public class BookshelfKeeperProg {

   /**
    * Main method to do the error checking for inputs and the put/pick operations.
    * It uses several private helper methods in this class to fulfill the functions above.
    *
    * @param args
    */
   public static void main(String[] args) {

      //Get the data
      Scanner in = new Scanner(System.in);

      ArrayList<Integer> books = new ArrayList<Integer>(getbooks(in));

      ArrayList<String> commands = new ArrayList<String>(getcommands(in));
      ArrayList<String> eachCommand = new ArrayList<String>(getEachCommand(commands));
      ArrayList<Integer> eachCommandNum = new ArrayList<Integer>(getEachCommandNum(commands));

      System.out.println("Please enter initial arrangement of books followed by newline:");

      //Error-checking for the books array entered
      if(!checkHeight(books)){
         System.out.println("ERROR: Height of a book must be positive.");
      }

      if(!checkOrder(books)){
         System.out.println("ERROR: Heights must be specified in non-decreasing order.");
      }

      if(!checkHeight(books) || !checkOrder(books)){
         exit();
      }

      Bookshelf bookshelf = new Bookshelf(books);
      BookshelfKeeper keeper = new BookshelfKeeper(bookshelf);
      System.out.println(keeper.toString());
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");

      //Error-checking for the commands entered
      if(!checkCommands(eachCommand)){
         System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
      }

      if(!checkHeightGiven(eachCommand, eachCommandNum)){
         System.out.println("ERROR: Height of a book must be positive.");
      }

      if(!checkPosGiven(eachCommand, eachCommandNum, keeper)){
         System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
      }

      if(!checkCommands(eachCommand) || !checkHeightGiven(eachCommand, eachCommandNum) || !checkPosGiven(eachCommand, eachCommandNum, keeper)){
         exit();
      }

      //Do pick and put operations
      operations(eachCommand, eachCommandNum, keeper);

   }


   // my private helper methods

   /**
    * This method gets the first line of data for the bookshelf, puts it into a string
    * and converts it into an arraylist.
    *
    * @param in the input data
    *
    * @return an arraylist containing the heights of a pile of books
    */
   private static ArrayList<Integer> getbooks(Scanner in) {

      String pileOfBooks = in.nextLine();
      Scanner lineScanner = new Scanner(pileOfBooks);

      ArrayList<Integer> books = new ArrayList<Integer>();

      while(lineScanner.hasNextInt()) {
         books.add(lineScanner.nextInt());
      }

      return books;
   }


   /**
    * This method gets all the commands from the user and puts them into an arraylist.
    *
    * @param in the input data
    *
    * @return an arraylist containing all the commands
    */
   private static ArrayList<String> getcommands(Scanner in) {

      ArrayList<String> commands = new ArrayList<String>();

      while(in.hasNextLine()){
         commands.add(in.nextLine());
      }

      return commands;
   }


   /**
    * This method gets all the words in the command arraylist and puts them into a new arraylist.
    *
    * @param commands the arraylist containing all the commands
    *
    * @return an arraylist containing all the words in the commands
    */
   private static ArrayList<String> getEachCommand(ArrayList<String> commands) {

      ArrayList<String> words = new ArrayList<String>();

      for(int i = 0; i < commands.size(); i++){
         Scanner lineScanner = new Scanner(commands.get(i));
         words.add(lineScanner.next());
      }

      return words;
   }


   /**
    * This method gets all the numbers in the command arraylist and puts them into a new arraylist.
    *
    * @param commands the arraylist containing all the commands
    *
    * @return an arraylist containing all the numbers in the commands
    */
   private static ArrayList<Integer> getEachCommandNum(ArrayList<String> commands) {

      ArrayList<Integer> nums = new ArrayList<Integer>();

      for(int i = 0; i < commands.size() - 1; i++){
         Scanner lineScanner = new Scanner(commands.get(i));
         lineScanner.next();
         nums.add(lineScanner.nextInt());
      }

      return nums;
   }


   /**
    * This method does error checking for the heights of books and makes sure the heights are all positive.
    *
    * @param books an arraylist containing the heights of a pile of books
    *
    * @return true if the bookshelf is empty or the heights are all positive; false if any book has a negative height
    */
   private static boolean checkHeight(ArrayList<Integer> books) {

      for(int i = 0; i < books.size(); i++){
         if(books.get(i) == null){
            return true;
         }
         else if(books.get(i) <= 0){
            return false;
         }
      }

      return true;
   }


   /**
    * This method does error checking for the order of books and makes sure the books are arranged in
    * non-decreasing order.
    *
    * @param books an arraylist containing the heights of a pile of books
    *
    * @return true if the bookshelf is empty or arranged in non-decreasing order;
    * false if not
    */
   private static boolean checkOrder(ArrayList<Integer> books) {

      for(int i = 0; i < books.size() - 1; i++){
         if(books.get(i) == null){
            return true;
         }
         else if(books.get(i) > books.get(i + 1)){
            return false;
         }
      }

      return true;
   }


   /**
    * This method does error checking for the words in commands and makes sure they are one of the three
    * valid words, i.e. pick, put and end.
    *
    * @param eachCommand the arraylist containing all the words in the commands
    *
    * @return true if the words are all valid; false if not
    */
   private static boolean checkCommands(ArrayList<String> eachCommand) {

      for(int i = 0; i < eachCommand.size(); i++) {
         if(!(eachCommand.get(i).equals("pick")) && !(eachCommand.get(i).equals("put")) && !(eachCommand.get(i).equals("end"))){
            return false;
         }
      }

      return true;
   }


   /**
    * This method does error checking for the numbers of "put" commands and makes sure
    * the numbers given are all positive.
    *
    * @param eachCommand the arraylist containing all the words in the commands
    * @param eachCommandNum the arraylist containing all the numbers in the commands
    *
    * @return true if the heights for the put commands are all positive; false if not
    */
   private static boolean checkHeightGiven(ArrayList<String> eachCommand, ArrayList<Integer> eachCommandNum) {

      ArrayList<Integer> heightGiven = new ArrayList<Integer>();

      for(int i = 0; i < eachCommand.size(); i++){
         if(eachCommand.get(i).equals("put")) {
            heightGiven.add(eachCommandNum.get(i));
         }
      }

      for(int i = 0; i < heightGiven.size(); i++){
         if(heightGiven.get(i) <= 0){
            return false;
         }
      }

      return true;
   }


   /**
    * This method does error checking for the numbers of "pick" commands and makes sure the
    * positions given are in valid bounds for the bookshelf.
    *
    * @param eachCommand the arraylist containing all the words in the commands
    * @param eachCommandNum the arraylist containing all the numbers in the commands
    * @param keeper the bookshelf keeper object
    *
    * @return true if the positions given are in valid bounds for the bookshelf; false if not
    */
   private static boolean checkPosGiven(ArrayList<String> eachCommand, ArrayList<Integer> eachCommandNum, BookshelfKeeper keeper) {

      ArrayList<Integer> posGiven = new ArrayList<Integer>();

      for(int i = 0; i < eachCommand.size(); i++){
         if(eachCommand.get(i).equals("pick")) {
            posGiven.add(eachCommandNum.get(i));
         }
      }

      ArrayList<Integer> numBooks = new ArrayList<Integer>();
      int currNum = keeper.getNumBooks();

      for(int i = 0; i < eachCommand.size(); i++){
         if(eachCommand.get(i).equals("put")){
            currNum++;
         }
         if(eachCommand.get(i).equals("pick")){
            numBooks.add(currNum);
            currNum--;
         }
      }

      for(int i = 0; i < posGiven.size(); i++){
         if((posGiven.get(i) < 0) || (posGiven.get(i) > numBooks.get(i) - 1)){
            return false;
         }
      }

      return true;
   }


   /**
    * This method terminates the program when errors occur.
    */
   private static void exit() {

      System.out.println("Exiting Program.");
      System.exit(0);

   }


   /**
    * This method does put and pick operations.
    *
    * @param eachCommand the arraylist containing all the words in the commands
    * @param eachCommandNum the arraylist containing all the numbers in the commands
    * @param keeper the bookshelf keeper object
    */
   private static void operations(ArrayList<String> eachCommand, ArrayList<Integer> eachCommandNum, BookshelfKeeper keeper) {

      for(int i = 0; i < eachCommandNum.size(); i++){
         if(eachCommand.get(i).equals("put")){
            keeper.putHeight(eachCommandNum.get(i));
         }
         else {
            keeper.pickPos(eachCommandNum.get(i));
         }
         System.out.println(keeper.toString());
      }

      System.out.println("Exiting Program.");
      System.exit(0);

   }

}
