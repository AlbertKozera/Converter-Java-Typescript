package converter.impl.typescript;

import converter.type.Converter;

import java.util.function.Function;

public enum TypeScriptConverterType {
    Java(typeScriptConverterService -> {
        fileExtension = ".java";
        return typeScriptConverterService.convertToJava();
    });

    private static String fileExtension;

    public final Function<TypeScriptConverterService, Converter> converterType;

    TypeScriptConverterType(Function<TypeScriptConverterService, Converter> converterType) {
        this.converterType = converterType;
    }

    public Function<TypeScriptConverterService, Converter> getConverter() {
        return converterType;
    }

    public static String getFileExtension() {
        return fileExtension;
    }
}
