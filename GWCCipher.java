/* Midland Park Girls Who Code Club
 * Due 22 November 2022
 * user inputs cc number and keyword (k)
 * 3 arrays to hold 1 alphabet
 * Randomizer that spits out 0, 1, or 2 (r)
 * number (n) correlates with the letter at index n of array r
 * shift (s) = sum of letters in k
 * encrypted string is shifted by s spaces (caesar cipher)
 */
import java.util.Scanner;
import java.math.BigInteger;
class GWCCipher {
   // Vigenere alphabet table
   private static final String[][] V_ALPHABET = {{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"},
                                                {"k", "l", "m", "n", "o", "p", "q", "r", "s", "t"},
                                                {"u", "v", "w", "x", "y", "z", "a", "b", "c", "d"}};
   // Caesar alphabet list - alphabet is duplicated for easier shifting
   private static final String[] C_ALPHABET = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y"};
   private static final BigInteger TEN = BigInteger.TEN;
   
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      
      // collect user inputs (Credit card number and keyword)
      System.out.print("Enter CREDIT CARD Number: ");
      BigInteger CC = input.nextBigInteger();
      System.out.print("Enter KEYWORD: ");
      input.nextLine();
      String keyword = input.nextLine();
      
      //System.out.println("CC = " + CC); // test
      //System.out.println("keyword = " + keyword); // test
      
      String encrypted = vigenere(CC);
      //System.out.println(encrypted); // test
      int shift = calcShift(keyword);
      //System.out.println(shift); // test
      encrypted = caesar(encrypted, shift);
      System.out.println("Encrypted CC: " + encrypted); // end test
   }
   
   /* Takes in a string and returns the string with each number replaced
    * by the corresponding letter in the vigenere table. The row is determined
    * by a randomizer from 0-2, inclusive, and the column is equal to the number.
    * num {BigInteger} - credit card number
    * return {String} - ciphered string
    */
   public static String vigenere(BigInteger num) {
      int randomNum = 0;
      BigInteger temp;;
      String str = "";
      
      for(int j = 0; j < 16; j++) {
         randomNum = (int)(Math.random()*3);
         temp = num.mod(TEN);
         num = num.divide(TEN);
         str = V_ALPHABET[randomNum][temp.intValue()] + str;
      }
      return str;
   }

   /* Takes in a string and returns an int of the sum of the letters
    * in the keyword (A=1, B=2, C=3,...).
    * keyword {String} - word used to calculate the shift
    * return {int} - shift value
    */
   public static int calcShift(String keyword) {
      int total = 0;
      String temp = "";
      
      for(int i = 0; i < keyword.length(); i++) {
         temp = keyword.substring(i,i+1);
         for(int j = 0; j < 26; j++) {
            if(temp.equals(C_ALPHABET[j]))
               total += j + 1;
         }
      }
      total = total % 26;
      return total;
   }
   
   /* Takes in a string and returns the string with each letter replaced
    * by the letter offset indexes to the left. (shift1: A->Z, B->A,...)
    * message {String} - string being ciphered
    * offset {int} - number each letter is shifted by, between 0-26
    * return {String} - ciphered string
    */
   public static String caesar(String message, int offset) {
      String str = "";
      String temp = "";
      for(int i = 0; i < message.length(); i++) {
         temp = message.substring(i,i+1);
         for(int j = C_ALPHABET.length-1; j > 24; j--) {
            if(temp.equals(C_ALPHABET[j])) {
               str += C_ALPHABET[j - offset];
               j = 0;
            }
         }
         if(i != str.length() -1) {
            str += temp;
         }
      }
      return str;
   }
}