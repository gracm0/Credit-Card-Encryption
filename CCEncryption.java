/* Midland Park Girls Who Code Club
 * Deadline: 22 November 2022
 * Collects credit card (CC) number and the keyword, a single word.
 * Calculates the 'shift' based off of the keyword. Encrypts the CC
 * into a string of letters and shifts to the left by 'shift'.
 */
import java.math.BigInteger;
import javax.swing.JOptionPane; //import statement for GUI

class CCEncryption {
   
   public static void main(String[] args) {
      BigInteger CC = new BigInteger(JOptionPane.showInputDialog("Enter CREDIT CARD Number: "));
      String keyword = JOptionPane.showInputDialog("Enter KEYWORD: ");
      int shift = Cipher.calcShift(keyword);
      
      System.out.println("CC = " + CC); // test
      System.out.println("keyword = " + keyword); // test
      System.out.println("shift = " + shift); // test
      
      String encrypted = Cipher.vigenere(CC);
      System.out.println("encrypted CC = " + encrypted); // test
      encrypted = Cipher.caesar(encrypted, shift);
      
      //replaces all letters except for the last 2 with '*' to mask the keyword
      String hiddenKey = "";
      for(int i = 0; i < keyword.length()-2; i++) {
         hiddenKey += "*";
      }
      hiddenKey += keyword.substring(keyword.length()-2,keyword.length());
      
      JOptionPane.showMessageDialog(null, "Credit Card Number: xxxx xxxx xxxx " + CC.mod(new BigInteger("10000")) + 
                                          "\n" + "Keyword: " + hiddenKey +
                                          "\n" + "Encrypted Version: " + encrypted.toUpperCase());
   }
}