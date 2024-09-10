package converter.impl.typescript.to.java;

import config.Constants;
import converter.type.CollectionType;
import converter.type.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionTypeScriptJava implements CollectionType {
    private Converter converter;
    private Map<String, List<String>> typesMap = new HashMap<>();

    public CollectionTypeScriptJava() {
        typesMap.put("List", Arrays.asList("Array"));
        typesMap.put("Map", Arrays.asList("Map"));
        typesMap.put("int", Arrays.asList("number"));
        typesMap.put("boolean", Arrays.asList("boolean", "null", "undefined", "void", "never"));
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
        if (code.contains("Array") || code.contains("Map")) {
            String collectionName = getVariableName(code);
            String collectionType = getVariableType(code);
            for (Map.Entry<String, List<String>> listEntry : typesMap.entrySet()) {
                List<String> typesList = listEntry.getValue();
                String regex = getRegex(typesList);
                collectionType = collectionType.replaceAll(regex, listEntry.getKey());
            }
            return new StringBuilder(Constants.PRIVATE + Constants.SPACE + collectionType + Constants.SPACE + collectionName + Constants.SEMICOLON);
        }
        return this.converter.convert(code);
    }

    private String getRegex(List<String> typesList) {
        return typesList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(Constants.COMMA))
                .replaceAll(Constants.COMMA, "|");
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
