import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        translator.addWord("exit", "вихід");

        System.out.print("The dictionary is pre-filled. Do you want to add new words? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes")) {
            while (true) {
                System.out.print("Enter the word in English (or 'exit' to complete): ");
                String englishWord = scanner.nextLine().trim();
                if (englishWord.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter the translation into Ukrainian: ");
                String ukrainianWord = scanner.nextLine().trim();
                if (translator.addWord(englishWord, ukrainianWord)) {
                    System.out.println("Word added!");
                } else {
                    System.out.println("Error: the word must contain only valid characters (English word - Latin, Ukrainian word - Cyrillic).");
                }
            }
        }

        System.out.print("Enter a phrase in English to translate: ");
        String phrase = scanner.nextLine();

        String translatedPhrase = translator.translate(phrase);
        System.out.println("Translation: " + translatedPhrase);

        scanner.close();
    }
}