package com.keaplogik.examples.main.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.String;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class that houses the dealer.com challenges and runnable main method.
 */
public class DealerDotComChallenge {

    private final PrintStream p;

    private static final String[] phrases;
    private static final String[] words;
    private static final Integer[] numbers;

    static {
        // initialize static test data.
        phrases = new String[]{"Eva, can I stab bats in a cave?", "Mr. Owl ate my metal worm",
                "Was it a car or a cat I saw?", "A nut for a jar of tuna", "Do geese see God?",
                "Ma is as selfless as I am", "Dammit, I'm mad!", "A Toyota's a Toyota",
                "Go hang a salami, I'm a lasagna hog", "A Santa lived as a devil at NASA", "How are you today?",
                "I am doing fine thanks.", "Let's go fly a kite"};
        words = new String[]{"civic", "radar", "level", "rotor", "noon", "kayak", "reviver", "racecar", "redder",
                "madam", "Malayalam", "refer", "hello", "test", "bamboo", "laughter", "just", "another", "example"};

        numbers = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99, 101, 111, 121, 131,
                141, 151, 161, 171, 181, 191, 202, 339933, 606, 599, 995, 4004, 50011, 10111};
    }

    public DealerDotComChallenge(PrintStream printStream) {
        this.p = printStream;
    }

    /**
     * Run Challenges:
     * <ol>
     * <li>
     * Reads in a key file in format "key,count". Where key is a string and count is an integer.
     * Rolls up matching keys and outputs results.
     * </li>
     * <li>
     * Function that determines if a given string is a palindrome.
     * </li>
     * </ol>
     *
     * @param args - unused argument list.
     */
    public static void main(String[] args) {

        final PrintStream p = System.out;
        // Initialize challenges.
        DealerDotComChallenge challenge = new DealerDotComChallenge(p);

        // Challenge 1
        p.println("______________CHALLENGE 1: KEY COUNTS CHALLENGE_____________");
        challenge.keyFileChallenge("key_file1.txt");

        p.println();
        p.println();

        // Challenge 2
        p.println("______________CHALLENGE 2: PALINDROME CHALLENGE_____________");
        challenge.palindromeChallenge();
    }

    /**
     * Reads in line item file and totals the key counts occurring within the file for each distinct value.
     *
     * @param fileName - name of file at {project-base}/resources/key_files/<code>fileName</code>
     */
    public void keyFileChallenge(final String fileName) {
        // Declare a hash to store keys and their counts.
        final Map<String, Integer> keyCountMap = new HashMap<>();

        // Read file into buffer reader. Read and process each line into hash.
        Path path = FileSystems.getDefault().getPath("resources", "key_files", fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // Pull value and count from line using splice.
                String[] keyValues = line.split(",");
                String value = keyValues[0];
                Integer count = new Integer(keyValues[1]);

                // Add Key to map if not already there. Increment or set count.
                Integer existingCount = keyCountMap.get(value);
                keyCountMap.put(value, existingCount == null ? count : existingCount + count);
            }
        } catch (IOException x) {
            x.printStackTrace();
        }


        // Start of results presentation.
        p.println("Keys and their total counts within " + fileName + "\n");

        p.printf("%10s | %3s\n", "Key", "Total Count");
        p.println("--------------------------");

        // Iterate over each entry and output result.
        for (Map.Entry<String, Integer> entry : keyCountMap.entrySet()) {
            p.printf("%10s | %3s\n", entry.getKey(), entry.getValue());
        }
        p.println();
    }

    /**
     * Tests various phrases and words against a Palindrome class validator.
     */
    public void palindromeChallenge() {
        p.println("Determine if each phrase, word or number is a valid palindrome.\n");

        p.printf("%35s | %5s\n", "Phrase", "Palindrome?");
        p.println("----------------------------------------------------");

        //Validate each phrase and print results.
        for (String phrase : phrases) {
            p.printf("%35s | %5s\n", phrase, Palindrome.validate(phrase).isValid());
        }

        p.println("\n");
        p.printf("%15s | %5s\n", "Word", "Palindrome?");
        p.println("----------------------------------------------------");

        //Validate each word and print results.
        for (String word : words) {
            p.printf("%15s | %5s\n", word, Palindrome.validate(word).isValid());
        }


        p.println("\n");
        p.printf("%8s | %5s\n", "Number", "Palindrome?");
        p.println("----------------------------------------------------");

        //Validate each number and print results.
        for (Integer number : numbers) {
            p.printf("%8s | %5s\n", number, Palindrome.validate(number.toString()).isValid());
        }
    }

}

/**
 * Palindrome class used to validate word or phrase against it's validation function.
 */
class Palindrome {

    /**
     * Validates a word or phrase is a palindrome.
     *
     * @param wordOrPhrase - Word or Phrase to validate
     * @return Validation - Validation result.
     */
    static Validation validate(String wordOrPhrase) {
        if (wordOrPhrase == null || wordOrPhrase.isEmpty()) return new Validation(false);

        Palindrome palindrome = new Palindrome(wordOrPhrase);
        // Remove casing and strip out any non alphanumeric characters.
        String cleanedWordOrPhrase = palindrome.wordOrPhrase.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        //Simply use string builder to reverse the string and return whether the result is equal to the original.
        String reverseCleanedWordOrPhrase = new StringBuilder(cleanedWordOrPhrase).reverse().toString();
        return new Validation(reverseCleanedWordOrPhrase.equals(cleanedWordOrPhrase));
    }

    private final String wordOrPhrase;

    private Palindrome(String wordOrPhrase) {
        this.wordOrPhrase = wordOrPhrase;
    }

    static class Validation {
        private final Boolean valid;

        Validation(boolean valid) {
            this.valid = valid;
        }

        public Boolean isValid() {
            return valid;
        }
    }
}