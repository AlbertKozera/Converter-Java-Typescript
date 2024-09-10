package converter.strategy;

import converter.Language;
import converter.type.Converter;
import converter.impl.typescript.TypeScriptConverterService;
import converter.impl.typescript.TypeScriptConverterType;
import lombok.extern.log4j.Log4j;
import service.ExecutorThreadService;

import java.io.File;
import java.util.Collection;

@Log4j
public class TypeScriptConversionStrategy implements ConversionStrategy {
    private final ExecutorThreadService executorThreadService = new ExecutorThreadService();

    @Override
    public void convert(Collection<File> files, Language convertTo) {
        TypeScriptConverterService typeScriptConverterService = new TypeScriptConverterService();
        TypeScriptConverterType typeScriptConverterType = TypeScriptConverterType.valueOf(String.valueOf(convertTo));
        Converter converter = typeScriptConverterService.doConvert(typeScriptConverterType);
        log.info("Conversion from TypeScript to " + convertTo);
        executorThreadService.convert(files, converter, TypeScriptConverterType.getFileExtension());
    }
}
