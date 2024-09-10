package converter;


import converter.strategy.JavaConversionStrategy;
import converter.strategy.TypeScriptConversionStrategy;
import java.io.File;
import java.util.Collection;

public enum Language {
    Java {
        @Override
        public void convert(Collection<File> files, Language convertTo) {
            new JavaConversionStrategy().convert(files, convertTo);

        }
    },
    TypeScript {
        @Override
        public void convert(Collection<File> files, Language convertTo) {
            new TypeScriptConversionStrategy().convert(files, convertTo);
        }
    };

    public abstract void convert(Collection<File> files, Language convertTo);
}
