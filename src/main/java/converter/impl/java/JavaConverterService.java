package converter.impl.java;

import converter.type.Converter;
import converter.impl.java.to.typescript.CollectionJavaTypeScript;
import converter.impl.java.to.typescript.FileNameJavaTypeScript;
import converter.impl.java.to.typescript.ObjectJavaTypeScript;
import converter.impl.java.to.typescript.BasicJavaTypeScript;

public class JavaConverterService {
    public Converter convertToTypeScript() {
        Converter fileNameJavaTypeScriptConverter = new FileNameJavaTypeScript();
        Converter basicJavaTypeScriptConverter = new BasicJavaTypeScript();
        Converter objectTypeJavaTypeScriptConverter = new ObjectJavaTypeScript();
        Converter arrayJavaTypeScriptConverter = new CollectionJavaTypeScript();

        fileNameJavaTypeScriptConverter.nextConverter(arrayJavaTypeScriptConverter);
        arrayJavaTypeScriptConverter.nextConverter(basicJavaTypeScriptConverter);
        basicJavaTypeScriptConverter.nextConverter(objectTypeJavaTypeScriptConverter);
        objectTypeJavaTypeScriptConverter.nextConverter(null);
        return fileNameJavaTypeScriptConverter;
    }

    public Converter doConvert(JavaConverterType converterType) {
        return converterType.getConverter().apply(this);
    }

}
