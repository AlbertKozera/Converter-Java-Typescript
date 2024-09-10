package service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class EnumInstanceService {

    public Collection<String> getListOfPossibleConversionType(String nameOfLanguage) {
        try {
            Class<?> instanceOfEnum = Class.forName(getInstanceOfEnum(nameOfLanguage));
            Object[] enumConstants = instanceOfEnum.getEnumConstants();
            return getEnumValueList(enumConstants);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private Collection<String> getEnumValueList(Object[] enumConstants) {
        return Arrays.stream(enumConstants)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private String getInstanceOfEnum(String nameOfLanguage) { // example ---> converter.impl.java.JavaConverterType
        return "converter.impl." +
                nameOfLanguage.toLowerCase() +
                "." +
                nameOfLanguage +
                "ConverterType";
    }

}
