package converter.impl.java;

import converter.type.Converter;

import java.util.function.Function;

public enum JavaConverterType {
    TypeScript(javaConverterService -> {
        fileExtension = ".ts";
        return javaConverterService.convertToTypeScript();
    });
    private static String fileExtension;

    public final Function<JavaConverterService, Converter> converterType;

    JavaConverterType(Function<JavaConverterService, Converter> converterType) {
        this.converterType = converterType;
    }

    public Function<JavaConverterService, Converter> getConverter() {
        return converterType;
    }

    public static String getFileExtension() {
        return fileExtension;
    }
}
