package converter.impl.java.to.typescript;

import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FileNameTypeTest {

    private JavaConverterService javaConverterService = new JavaConverterService();
    private Converter sut = javaConverterService.convertToTypeScript();


    @Test
    public void file_name_type_java_type_script_converter_return_interface() {
        //given
        String sutText = "public class Test";
        //when
        StringBuilder stringBuilder = sut.convert(sutText);
        //then
        assertThat(stringBuilder.toString()).contains("interface");

    }
}
