package converter.impl.java.to.typescript;

import config.Constants;
import converter.type.Converter;
import converter.type.FileNameType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNameJavaTypeScript implements FileNameType {
    private Converter converter;

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public StringBuilder convert(String code) {
        if (code.toLowerCase().contains("class")) {
            return new StringBuilder("interface " + findFileName(code) + Constants.SPACE + Constants.BRACKET_START);
        } else if (code.toLowerCase().trim().equals(Constants.BRACKET_START) || code.toLowerCase().trim().equals(Constants.BRACKET_END)) {
            return new StringBuilder(code.trim());
        } else {
            return this.converter.convert(code);
        }
    }

    @Override
    public String findFileName(String code) {
        String[] splitBySpace = code.split(Constants.SPACE);
        List<String> collect = Stream.of(splitBySpace).collect(Collectors.toList());
        int index = collect.indexOf("class");
        return collect.get(index + 1).replace(Constants.BRACKET_START, StringUtils.EMPTY);
    }
}
