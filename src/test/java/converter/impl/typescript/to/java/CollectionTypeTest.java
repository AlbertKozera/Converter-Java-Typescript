package converter.impl.typescript.to.java;

import converter.type.Converter;
import converter.impl.typescript.TypeScriptConverterService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CollectionTypeTest {
    private TypeScriptConverterService typeScriptConverterService = new TypeScriptConverterService();
    private Converter sut = typeScriptConverterService.convertToJava();

    @Test
    public void collection_type_converter_return_array() {
        //given
        String code = "test:Array<string>";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private List<String> test");
    }

    @Test
    public void collection_type_converter_return_map() {
        //given
        String code = "test:Map<string,number>";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private Map<String,int> test");
    }

    @Test
    public void collection_type_converter_return_indented_java_code() {
        //given
        String code = "test:Map<string,Array<Map<number,string>>>";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private Map<String,List<Map<int,String>>> test");
    }
}
