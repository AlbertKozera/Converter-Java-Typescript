package service;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ReplacementServiceTest {

    @ParameterizedTest
    @ValueSource(strings = {"//comment", "/*comment*/"})
    public void return_empty_string_when_comments_are_given(String line) {
        //when
        String result = ReplacementService.replaceComment(line);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void return_string_without_regex_staff() {
        //given
        String givenString = getGivenString();
        String exceptedString = getExceptedString();
        //when
        String result = ReplacementService.replaceComment(givenString);
        //then
        assertThat(result).isEqualTo(exceptedString);
    }

    private String getExceptedString() {
        return "public class OptionProperties {\n" +
                "    private HashMap<Integer, String> integerStringHashMap;\n" +
                "    private List<Integer> integerList;\n" +
                "    private Map<Integer, Map<Integer, Map<Float,String>>> test;\n" +
                "    private String saveTo;\n" +
                "    private boolean threadEnabled;\n" +
                "}\n";
    }

    private String getGivenString() {
        return "package properties;\n" +
                "\n" +
                "\n" +
                "import lombok.Builder;\n" +
                "import lombok.Data;\n" +
                "import org.apache.commons.lang3.time.FastDateFormat;\n" +
                "\n" +
                "import java.util.Collection;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "@Data\n" +
                "@Builder\n" +
                "public class OptionProperties {\n" +
                "    private HashMap<Integer, String> integerStringHashMap;\n" +
                "    private List<Integer> integerList;\n" +
                " /*   private Collection<Integer> integerCollection;*/\n" +
                "\n" +
                "/*\n" +
                "    FastDateFormat\n" +
                "\n" +
                "    fastDateFormat\n" +
                "*/\n" +
                "\n" +
                "//    sfge\n" +
                "\n" +
                "\n" +
                "    private Map<Integer, Map<Integer, Map<Float,String>>> test;\n" +
                "    private String saveTo;\n" +
                "    private boolean threadEnabled;\n" +
                "}\n";
    }

}
