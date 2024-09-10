package converter.strategy;

import converter.Language;
import converter.type.Converter;
import converter.impl.java.JavaConverterService;
import converter.impl.java.JavaConverterType;
import lombok.extern.log4j.Log4j;
import service.ExecutorThreadService;

import java.io.File;
import java.util.Collection;

@Log4j
public class JavaConversionStrategy implements ConversionStrategy {
    private final ExecutorThreadService executorThreadService = new ExecutorThreadService();

    @Override
    public void convert(Collection<File> files, Language convertTo) {
        JavaConverterService javaConverterService = new JavaConverterService();
        JavaConverterType javaConverterType = JavaConverterType.valueOf(String.valueOf(convertTo));
        Converter converter = javaConverterService.doConvert(javaConverterType);
        log.info("Conversion from Java to " + convertTo);
        executorThreadService.convert(files, converter, JavaConverterType.getFileExtension());
    }
}
