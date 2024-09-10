package converter.impl.java.to.typescript;

import config.Constants;
import converter.type.Converter;
import converter.type.CollectionType;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionJavaTypeScript implements CollectionType {
    private Converter converter;
    private Map<String, List<String>> collectionTypes = new HashMap<>();
    private static StringBuilder result = new StringBuilder();

    public CollectionJavaTypeScript() {
        collectionTypes.put("Array", Arrays.asList("Collection", "ArrayList", "Set", "HashSet", "List"));
        collectionTypes.put("Map", Arrays.asList("Map", "HashMap", "TreeMap", "LinkedHashMap", "SortedMap"));
    }

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public synchronized StringBuilder convert(String code) {
        result = new StringBuilder();
        return convertCode(code);
    }

    private StringBuilder convertCode(String code) {
        for (Map.Entry<String, List<String>> listEntry : collectionTypes.entrySet()) {
            List<String> collectionTypesList = listEntry.getValue();
            if (canBeConverted(code, collectionTypesList)) {
                appendVariableNameIfExists(code, listEntry);
                String[] concatenatedCode = getConcatenatedCode(code);
                for (int counter = 0; counter < concatenatedCode.length; counter++) {
                    String codeToConvert = concatenatedCode[counter].trim();
                    if (splitPartContainsCollection(codeToConvert)) {
                        callThisConverter(concatenatedCode, counter, codeToConvert);
                    } else {
                        callBasicTypeConverter(concatenatedCode, counter, codeToConvert);
                    }
                }
                return result;
            }

        }
        return this.converter.convert(code);
    }

    private void callBasicTypeConverter(String[] concat, int i, String codeToConvert) {
        result.append(converter.convert(codeToConvert));
        if (i == concat.length - 1) {
            result.append(Constants.DIAMOND_OPERATOR_END);
        } else {
            result.append(Constants.COMMA);
        }
    }

    private void callThisConverter(String[] concat, int i, String codeToConvert) {
        this.convertCode(codeToConvert);
        if ((i < concat.length - 1) && !splitPartContainsCollection(concat[i + 1])) {
            result.append(Constants.COMMA);
        } else if ((i < concat.length - 1) && splitPartContainsCollection(concat[i]) && splitPartContainsCollection(concat[i + 1])) {
            result.append(Constants.COMMA);
        } else {
            result.append(Constants.DIAMOND_OPERATOR_END);
        }
    }

    private String[] getConcatenatedCode(String code) {
        String[] split = code.substring(code.indexOf(Constants.DIAMOND_OPERATOR_START) + 1, code.lastIndexOf(Constants.DIAMOND_OPERATOR_END)).split(Constants.COMMA);
        return concat(split);
    }

    private void appendVariableNameIfExists(String code, Map.Entry<String, List<String>> listEntry) {
        result.append(getVariableName(code))
                .append(listEntry.getKey())
                .append(Constants.DIAMOND_OPERATOR_START);
    }

    private boolean canBeConverted(String code, List<String> typesList) {
        return typesList.stream().anyMatch(code::contains) && typesList.stream().anyMatch(type -> code.substring(0, code.indexOf(Constants.DIAMOND_OPERATOR_START)).contains(type));
    }

    private boolean splitPartContainsCollection(String codeToConvert) {
        return collectionTypes.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .anyMatch(codeToConvert::contains);
    }

    private String[] concat(String[] split) {
        List<String> stringList = new ArrayList<>();
        boolean noCollection = true;
        StringBuilder concatStart = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(Constants.DIAMOND_OPERATOR_START) && split[i].contains(Constants.DIAMOND_OPERATOR_END)) {
                stringList.add(split[i]);
            } else if (split[i].contains(Constants.DIAMOND_OPERATOR_START) && StringUtils.isBlank(concatStart)) {
                noCollection = false;
                concatStart.append(split[i]);
            } else if (split[i].contains(Constants.DIAMOND_OPERATOR_END)) {
                noCollection = false;
                concatStart.append(Constants.COMMA);
                concatStart.append(split[i]);
                stringList.add(String.valueOf(concatStart));
                concatStart = new StringBuilder();
            } else {
                if (StringUtils.isBlank(concatStart)) {
                    stringList.add(split[i]);
                }
                concatStart.append(Constants.COMMA);
                concatStart.append(split[i]);
            }
        }
        return (stringList.isEmpty() || noCollection) ? split : stringList.stream()
                .toArray(String[]::new);

    }

    @Override
    public String getVariableName(String code) {
        String variable = code.substring(code.lastIndexOf(Constants.DIAMOND_OPERATOR_END) + 1).trim().replace(Constants.SEMICOLON, StringUtils.EMPTY);
        return StringUtils.isBlank(variable) ? StringUtils.EMPTY : variable + Constants.COLON;
    }
}
