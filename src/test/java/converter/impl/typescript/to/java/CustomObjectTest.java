package converter.impl.typescript.to.java;

import converter.type.Converter;
import converter.impl.typescript.TypeScriptConverterService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomObjectTest {
    private TypeScriptConverterService typeScriptConverterService = new TypeScriptConverterService();
    private Converter sut = typeScriptConverterService.convertToJava();

    @Test
    public void custom_object_converter_return_custom_dto() {
        //given
        String sutText = "testDTO:TestDTO";
        //when
        String convertedCode = sut.convert(sutText).toString();
        //then
        assertThat(convertedCode).contains("private TestDTO testDTO");
    }
}
