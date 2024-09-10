package converter.impl.typescript.to.java;

import config.Constants;
import converter.type.Converter;
import converter.type.BasicType;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicTypeScriptJava implements BasicType {
    private Converter converter;
    private Map<String, List<String>> typesMap = new HashMap<>();

    public BasicTypeScriptJava() {
        typesMap.put("int", Arrays.asList("number"));
        typesMap.put("boolean",  Arrays.asList("boolean", "null", "undefined", "void", "never"));
        typesMap.put("String", Arrays.asList("string"));
        typesMap.put("Date", Arrays.asList("Date"));
        typesMap.put("Object", Arrays.asList("any", "unknown"));
    }

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public StringBuilder convert(String code) {
        for (Map.Entry<String, List<String>> listEntry : typesMap.entrySet()) {
            List<String> typesList = listEntry.getValue();
            if (typesList.stream().anyMatch(code::contains)) {
                return new StringBuilder(Constants.PRIVATE + Constants.SPACE + listEntry.getKey() + Constants.SPACE + getVariableName(code) + Constants.SEMICOLON);
            }
        }
        return this.converter.convert(code);
    }

    @Override
     public String getVariableName(String code) {
        return Stream.of(code.split(Constants.COLON)).collect(Collectors.toList()).get(0)
                .replace(Constants.BRACKET_START, StringUtils.EMPTY);
    }
}
