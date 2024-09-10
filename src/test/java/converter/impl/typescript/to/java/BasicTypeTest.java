package converter.impl.typescript.to.java;

import converter.type.Converter;
import converter.impl.typescript.TypeScriptConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BasicTypeTest {
    private TypeScriptConverterService typeScriptConverterService = new TypeScriptConverterService();
    private Converter sut = typeScriptConverterService.convertToJava();

    @Test
    public void basic_type_converter_return_int() {
        //given
        String code = "test:number";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private int test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test:boolean", "test:null", "test:undefined", "test:void", "test:never"})
    public void basic_type_converter_return_boolean(String code) {
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private boolean test");
    }

    @Test
    public void basic_type_converter_return_String() {
        //given
        String code = "test:string";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private String test");
    }

    @Test
    public void basic_type_converter_return_Date() {
        //given
        String code = "test:Date";
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private Date test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test:any", "test:unknown"})
    public void basic_type_converter_return_Object(String code) {
        //when
        String convertedCode = sut.convert(code).toString();
        //then
        assertThat(convertedCode).contains("private Object test");
    }
}
