package converter.impl.java.to.typescript;

import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomObjectTest {
    private JavaConverterService javaConverterService = new JavaConverterService();
    private Converter sut = javaConverterService.convertToTypeScript();


    @Test
    public void custom_object_converter_return_custom_dto() {
        //given
        String sutText = "protected SomeDTO someDTO";
        //when
        StringBuilder stringBuilder = sut.convert(sutText);
        //then
        assertThat(stringBuilder.toString()).contains("someDTO:SomeDTO");

    }

}
