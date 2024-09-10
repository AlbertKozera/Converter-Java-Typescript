package converter.strategy;

import converter.Language;

import java.io.File;
import java.util.Collection;

public interface ConversionStrategy {
    void convert(Collection<File> files, Language convertTo);
}
