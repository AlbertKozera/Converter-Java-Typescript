package service;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacementService {

    private static List<String> patterns;

    static {
        patterns = new ArrayList<>();
        patterns.add("(?sm)(^(?:\\s*)?((?:/\\*(?:\\*)?).*?(?<=\\*/))|(?://).*?(?<=$))");
        patterns.add(".*import.*\\r?\\n");
        patterns.add(".*package.*\\r?\\n");
        patterns.add(".*@.*\\r?\\n");
        patterns.add("\\b\\w*\\s*\\w*\\(.*?\\)\\s*\\{[\\x21-\\x7E\\s]*\\}");
        patterns.add("(?m)^[ \\t]*\\r?\\n");
    }

    public static String replaceComment(String content) {
        for (String pattern : patterns) {
            Pattern ptn = Pattern.compile(pattern);
            Matcher match = ptn.matcher(content);
            content = match.replaceAll(StringUtils.EMPTY);
        }
        return content;
    }
}
