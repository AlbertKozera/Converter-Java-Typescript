package converter.impl.java.to.typescript;

import config.Constants;
import converter.type.Converter;
import converter.type.ObjectType;
import org.apache.commons.lang3.StringUtils;

public class ObjectJavaTypeScript implements ObjectType {
    private Converter converter;

    @Override
    public void nextConverter(Converter nextConverter) {
        this.converter = nextConverter;
    }

    @Override
    public StringBuilder convert(String code) {
        code = removeAccessModifierAndStatic(code).trim();
        StringBuilder result = new StringBuilder();
        String variableName = getVariableName(code);
        String convertedCode = code.replace(variableName.replace(Constants.COLON, StringUtils.EMPTY), StringUtils.EMPTY).trim();
        return result.append(variableName)
                .append(convertedCode);
    }

    @Override
    public String getVariableName(String code) {
        int lastIndexOf = code.lastIndexOf(Constants.SPACE);
        return lastIndexOf == -1 ? StringUtils.EMPTY : code.substring(lastIndexOf).trim().replace(Constants.SEMICOLON, StringUtils.EMPTY) + Constants.COLON;
    }

    private String removeAccessModifierAndStatic(String code) {
        return code.replace("private", StringUtils.EMPTY)
                .replace("public", StringUtils.EMPTY)
                .replace("protected", StringUtils.EMPTY)
                .replace("static", StringUtils.EMPTY);
    }
}
