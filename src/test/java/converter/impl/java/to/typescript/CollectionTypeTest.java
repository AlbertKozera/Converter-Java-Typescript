package converter.impl.java.to.typescript;

import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CollectionTypeTest {
    private JavaConverterService javaConverterService = new JavaConverterService();
    private Converter sut = javaConverterService.convertToTypeScript();


    @ParameterizedTest
    @ValueSource(strings = {"private List<Integer> test", "  Collection<Integer> test", "ArrayList List<Double> test", "protected LinkedList<Float> test",})
    public void collection_type_converter_return_array(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:Array<number>");

    }

    @ParameterizedTest
    @ValueSource(strings = {"private Map<Integer,String> test", "  HashMap<Integer,String> test", "  TreeMap<Integer,String> test",
            "protected LinkedHashMap<Integer,String> test",
            "SortedMap <Integer,String>test"})
    public void collection_type_converter_return_map(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:Map<number,string>");

    }

    @Test
    public void collection_type_converter_return_indented_type_script_code() {
        //given
        String code = "private Map<Integer, Map<Integer, Map<Float,String>>> test";
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:Map<number,Map<number,Map<number,string>");
    }
}
