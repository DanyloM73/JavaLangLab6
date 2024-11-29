import java.util.HashMap;

public class Translator {
    private final HashMap<String, String> dictionary;

    public Translator() {
        dictionary = new HashMap<String, String>();
    }

    public boolean addWord(String english, String ukrainian) {
        if (isEnglishWordValid(english) && isUkrainianWordValid(ukrainian)) {
            dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
            return true;
        }
        return false;
    }

    public String translate(String phrase) {
        StringBuilder translation = new StringBuilder();

        String[] tokens = phrase.split("(?=[\\p{Punct}\\s])|(?<=[\\p{Punct}\\s])");

        for (String token : tokens) {
            if (token.matches("[a-zA-Z]+")) {
                String lowerCaseWord = token.toLowerCase();
                String translatedWord = dictionary.getOrDefault(lowerCaseWord, token);

                if (Character.isUpperCase(token.charAt(0))) {
                    translatedWord = capitalize(translatedWord);
                }

                translation.append(translatedWord);
            } else {
                translation.append(token);
            }
        }

        return translation.toString().trim();
    }

    private boolean isEnglishWordValid(String word) {
        return word.matches("[a-zA-Z]+");
    }

    private boolean isUkrainianWordValid(String word) {
        return word.matches("[а-щА-ЩЬьЮюЯяЇїІіЄєҐґ']+");
    }

    private String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
