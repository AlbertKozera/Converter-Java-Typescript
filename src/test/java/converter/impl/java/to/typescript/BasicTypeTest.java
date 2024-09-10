package converter.impl.java.to.typescript;

import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BasicTypeTest {
    private JavaConverterService javaConverterService = new JavaConverterService();
    private Converter sut = javaConverterService.convertToTypeScript();

    @ParameterizedTest
    @ValueSource(strings = {"private int test", "private short test", "private double test", "private float test", "private byte test",
            "private long test", "private Integer test", "private Double test", "private Float test", "private Long test"})
    public void basic_type_converter_return_number(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:number");

    }


    @ParameterizedTest
    @ValueSource(strings = {"private Date test", "private LocalDate test", "private LocalDateTime test", "private Timestamp test"})
    public void basic_type_converter_return_date(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:Date");

    }

    @ParameterizedTest
    @ValueSource(strings = {"private String test", "private char test", "private Character test"})
    public void basic_type_converter_return_string(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:string");

    }

    @ParameterizedTest
    @ValueSource(strings = {"Object test"})
    public void basic_type_converter_return_any(String code) {
        //when
        StringBuilder stringBuilder = sut.convert(code);
        //then
        assertThat(stringBuilder.toString()).contains("test:any");

    }
}
