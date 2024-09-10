package converter.impl.typescript.to.java;

import config.Constants;
import converter.type.Converter;
import converter.type.ObjectType;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectTypeScriptJava implements ObjectType {
    private Converter converter;

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public StringBuilder convert(String code) {
        return new StringBuilder(Constants.PRIVATE + Constants.SPACE + getVariableType(code) + Constants.SPACE + getVariableName(code) + Constants.SEMICOLON);
    }

    @Override
    public String getVariableName(String code) {
        return Stream.of(code.split(Constants.COLON)).collect(Collectors.toList()).get(0)
                .replace(Constants.BRACKET_START, StringUtils.EMPTY);
    }

    public String getVariableType(String code) {
        return Stream.of(code.split(Constants.COLON)).collect(Collectors.toList()).get(1)
                .replace(Constants.BRACKET_END, StringUtils.EMPTY)
                .replace(Constants.SEMICOLON, StringUtils.EMPTY);
    }
}
