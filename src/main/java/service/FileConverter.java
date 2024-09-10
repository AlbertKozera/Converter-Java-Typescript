package service;

import config.Constants;
import converter.type.Converter;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FilenameUtils;
import config.UserConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Log4j
public class FileConverter implements Runnable {

    private File file;
    private String fileExtension;
    private Converter converter;
    private UserConfiguration userSettings;

    public FileConverter(File file, String fileExtension, Converter converter, UserConfiguration userSettings) {
        this.file = file;
        this.fileExtension = fileExtension;
        this.converter = converter;
        this.userSettings = userSettings;
    }

    @Override
    public void run() {
        BufferedWriter writer;
        StringBuilder convertedCode = new StringBuilder();
        try {
            writer = getWriter(file, fileExtension);
            String content = readFileAsOnceAndReplaceComments();
            String[] lines = content.split("\n");
            for (String line : lines) {
                String convertedLine = converter.convert(line).toString();
                convertedCode.append(convertedLine);
                convertedCode.append(System.getProperty("line.separator"));
            }
            if (!convertedCode.toString().contains(Constants.BRACKET_END)) {
                convertedCode.append(Constants.BRACKET_END);
            }
            writer.write(String.valueOf(convertedCode));
            writer.close();
        } catch (IOException e) {
            log.info("Error:" + e);
        }
    }

    private String readFileAsOnceAndReplaceComments() throws IOException {
        String content = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
        return ReplacementService.replaceComment(content);
    }

    private BufferedWriter getWriter(File file, String fileExtension) throws IOException {
        File convertedFile = new File(userSettings.getSaveTo() + "\\" + FilenameUtils.removeExtension(file.getName()) + fileExtension);
        return new BufferedWriter(new FileWriter(convertedFile));
    }

}
