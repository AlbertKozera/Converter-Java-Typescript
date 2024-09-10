package converter.impl.java.to.typescript;

import config.Constants;
import converter.type.BasicType;
import converter.type.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicJavaTypeScript implements BasicType {
    private Converter converter;
    private Map<String, List<String>> typesMap = new HashMap<>();

    public BasicJavaTypeScript() {
        typesMap.put("number", Arrays.asList("int", "Integer", "float", "Float", "long", "Long", "BigDecimal", "BigInteger", "double", "Double", "byte", "Byte", "short", "Short"));
        typesMap.put("boolean", Arrays.asList("boolean", "Boolean"));
        typesMap.put("string", Arrays.asList("String", "char", "Character"));
        typesMap.put("Date", Arrays.asList("Date", "Timestamp", "LocalDate", "LocalDateTime"));
        typesMap.put("any", Arrays.asList("Object"));

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
                return getVariableName(code).equals(StringUtils.EMPTY) ? new StringBuilder(listEntry.getKey()) : new StringBuilder(getVariableName(code) + Constants.COLON + listEntry.getKey() + Constants.SEMICOLON);
            }
        }
        return this.converter.convert(code);

    }

    @Override
    public String getVariableName(String code) {
        int lastIndexOf = code.lastIndexOf(Constants.SPACE);
        return lastIndexOf == -1 ? StringUtils.EMPTY : code.substring(lastIndexOf).trim().replace(Constants.SEMICOLON, StringUtils.EMPTY);
    }
}
