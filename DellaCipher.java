// This is file we will work from just pull n push whenver you need. REMEMBER "Pull before eamCharh time you start working on it."
import java.util.Scanner;

public class DellaCipher {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a message: ");
        String message = input.nextLine().toUpperCase();
        System.out.print("Enter a key: ");
        String key = input.nextLine().toUpperCase();
        System.out.println();
        
        int keyIndex = 0;
        // Print key above the message for only alphabetic characters
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            if (Character.isLetter(messageChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                System.out.print(keyChar);
                keyIndex++;
            } else {
                System.out.print(" "); // Print spaces for non-alphabetic characters
            }
        }
        System.out.println();
        System.out.println(message);
        
        String encryptedMessage = processCipher(message, key);
        System.out.println(encryptedMessage);
    }

    // Encrypts the message using the Della Porta cipher method
    public static String processCipher(String text, String key) {
        String result = "";
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            char messageChar = text.charAt(i);

            // Only process alphabetic characters
            if (Character.isLetter(messageChar)) {
                // 1. Determine which key letter to use
                char keyChar = key.charAt(keyIndex % key.length());

                // 2. Find the alphabet index (0-12) based on key pairs (AB=0, CD=1... YZ=12)
                int alphabetIdx = (keyChar - 'A') / 2;

                // 3. Perform the substitution
                if (messageChar >= 'A' && messageChar <= 'M') {
                    // Character is in the top row, move to the bottom row
                    int indexInBottomRow = (messageChar - 'A' + alphabetIdx) % 13;
                    result += (char) ('N' + indexInBottomRow);
                } else if (messageChar >= 'N' && messageChar <= 'Z') {
                    // Character is in the bottom row, move to the top row
                    int indexInTopRow = (messageChar - 'N' - alphabetIdx + 13) % 13;
                    result += (char) ('A' + indexInTopRow);
                }
                keyIndex++;
            } else {
                // Append non-alphabetic characters unchanged
                result += messageChar;
            }
        }
        return result;
    }
}