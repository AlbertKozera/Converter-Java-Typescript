package converter.impl.typescript;

import converter.type.Converter;
import converter.impl.typescript.to.java.CollectionTypeScriptJava;
import converter.impl.typescript.to.java.FileNameTypeScriptJava;
import converter.impl.typescript.to.java.ObjectTypeScriptJava;
import converter.impl.typescript.to.java.BasicTypeScriptJava;

public class TypeScriptConverterService {
    public Converter convertToJava() {
        Converter fileNameTypeScriptJavaConverter = new FileNameTypeScriptJava();
        Converter basicTypeScriptJavaConverter = new BasicTypeScriptJava();
        Converter objectTypeScriptJavaConverter = new ObjectTypeScriptJava();
        Converter collectionTypeScriptJavaConverter = new CollectionTypeScriptJava();

        fileNameTypeScriptJavaConverter.nextConverter(collectionTypeScriptJavaConverter);
        collectionTypeScriptJavaConverter.nextConverter(basicTypeScriptJavaConverter);
        basicTypeScriptJavaConverter.nextConverter(objectTypeScriptJavaConverter);
        objectTypeScriptJavaConverter.nextConverter(null);
        return fileNameTypeScriptJavaConverter;
    }

    public Converter doConvert(TypeScriptConverterType converterType) {
        return converterType.getConverter().apply(this);
    }

}
