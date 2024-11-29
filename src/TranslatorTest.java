import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {

    private Translator translator;

    @BeforeEach
    void setUp() {
        translator = new Translator();
    }

    @Test
    void testAddWord_ValidWords() {
        assertTrue(translator.addWord("hello", "привіт"));
        assertTrue(translator.addWord("world", "світ"));
    }

    @Test
    void testAddWord_InvalidEnglishWord() {
        assertFalse(translator.addWord("h3llo", "привіт"));
        assertFalse(translator.addWord("hello!", "привіт"));
    }

    @Test
    void testAddWord_InvalidUkrainianWord() {
        assertFalse(translator.addWord("hello", "привіт!"));
        assertFalse(translator.addWord("hello", "123"));
    }

    @Test
    void testTranslate_SingleWord() {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        assertEquals("привіт", translator.translate("hello"));
        assertEquals("світ", translator.translate("world"));
    }

    @Test
    void testTranslate_CaseInsensitivity() {
        translator.addWord("hello", "привіт");

        assertEquals("Привіт", translator.translate("Hello"));
        assertEquals("Привіт", translator.translate("HELLO"));
    }

    @Test
    void testTranslate_Capitalization() {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        assertEquals("Привіт", translator.translate("Hello"));
        assertEquals("Світ", translator.translate("World"));
    }

    @Test
    void testTranslate_MultipleWordsWithPunctuation() {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        String input = "Hello, world!";
        String expected = "Привіт, світ!";

        assertEquals(expected, translator.translate(input));
    }

    @Test
    void testTranslate_NoTranslation() {
        translator.addWord("hello", "привіт");

        assertEquals("world", translator.translate("world"));
        assertEquals("unknown", translator.translate("unknown"));
    }

    @Test
    void testTranslate_MixedContent() {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");

        String input = "Hello, unknown world!";
        String expected = "Привіт, unknown світ!";

        assertEquals(expected, translator.translate(input));
    }
}
