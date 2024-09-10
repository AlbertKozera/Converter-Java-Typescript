package service;

import org.junit.Test;
import java.util.Collection;
import java.util.Arrays;
import converter.impl.java.JavaConverterType;
import converter.impl.typescript.TypeScriptConverterType;
import java.util.stream.Collectors;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class EnumInstanceServiceTest {

    private EnumInstanceService sut = new EnumInstanceService();


    @Test
    public void return_enum_list_for_Java() {
        //given
        String language = "Java";
        Enum[] testArrayJava = JavaConverterType.values();
        Collection<String> testListJava = getListOfEnumValues(testArrayJava);
        //when
        Collection<String> result = sut.getListOfPossibleConversionType(language);
        //then
        assertThat(result).containsAll(testListJava);
    }

    @Test
    public void return_enum_list_for_TypeScript() {
        //given
        String language = "TypeScript";
        Enum[] testArrayTypeScript = TypeScriptConverterType.values();
        Collection<String> testListTypeScript = getListOfEnumValues(testArrayTypeScript);
        //when
        Collection<String> result = sut.getListOfPossibleConversionType(language);
        //then
        assertThat(result).containsAll(testListTypeScript);
    }

    @Test
    public void return_empty_list() {
        //when
        Collection<String> result = sut.getListOfPossibleConversionType(null);
        //then
        assertThat(result).isEmpty();
    }

    private Collection<String> getListOfEnumValues(Enum[] language) {
        return  Arrays.stream(language)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

}