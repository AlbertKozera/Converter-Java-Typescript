package converter.impl.typescript.to.java;

import converter.type.Converter;
import converter.impl.typescript.TypeScriptConverterService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class FileNameTypeTest {
    private TypeScriptConverterService typeScriptConverterService = new TypeScriptConverterService();
    private Converter sut = typeScriptConverterService.convertToJava();

    @Test
    public void file_name_type_typescript_java_converter_return_class() {
        //given
        String sutText = "interface Test";
        //when
        String convertedCode = sut.convert(sutText).toString();
        //then
        assertThat(convertedCode).contains("class");
    }
}
