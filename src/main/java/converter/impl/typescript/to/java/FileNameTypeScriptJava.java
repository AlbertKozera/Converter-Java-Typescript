package converter.impl.typescript.to.java;

import config.Constants;
import converter.type.Converter;
import converter.type.FileNameType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileNameTypeScriptJava implements FileNameType {
    private Converter converter;

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public StringBuilder convert(String code) {
        if (code.contains("interface")) {
            return new StringBuilder("class" + Constants.SPACE + findFileName(code) + Constants.SPACE + Constants.BRACKET_START);
        } else if (code.trim().equals(Constants.BRACKET_START) || code.trim().equals(Constants.BRACKET_END)) {
            return new StringBuilder(code.trim());
        } else {
            return this.converter.convert(code);
        }
    }

    @Override
    public String findFileName(String code) {
        String[] strings = code.split(Constants.SPACE);
        List<String> collect = Stream.of(strings).collect(Collectors.toList());
        int index = collect.indexOf("interface");
        return collect.get(index + 1).replace(Constants.BRACKET_START, StringUtils.EMPTY);
    }
}
