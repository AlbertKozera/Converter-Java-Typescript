package converter.impl.java.to.typescript;

import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SyntaxTest {
    private JavaConverterService javaConverterService = new JavaConverterService();
    private Converter sut = javaConverterService.convertToTypeScript();


    @ParameterizedTest
    @ValueSource(strings = {"public", "protected", "private"})
    public void class_java_converter_return_empty_string(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).isEqualTo(StringUtils.EMPTY);

    }

}
