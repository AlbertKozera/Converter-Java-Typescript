package service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.extern.log4j.Log4j;
import config.UserConfiguration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Log4j
public class UserConfigService {

    private static String USER_DIR = System.getProperty("user.dir");

    public UserConfiguration loadConfigFile() {
        Gson gson = new Gson();
        try (JsonReader jsonReader = new JsonReader(new FileReader("config.json"))) {
            return gson.fromJson(jsonReader, UserConfiguration.class);
        } catch (IOException e) {
            return UserConfiguration.builder()
                    .build();
        }
    }

    public void createConfigFileIfNotExists() {
        File dataStorageDirectory = new File(USER_DIR + "\\converted-files");
        dataStorageDirectory.mkdir();
        File configFile = new File(USER_DIR + "\\config.json");
        if (!configFile.exists()) {
            log.info("Config file created");
            saveConfigOption(getOptionProperties(dataStorageDirectory));
        }
    }

    private UserConfiguration getOptionProperties(File dataStorageDirectory) {
        return UserConfiguration.builder()
                .saveTo(dataStorageDirectory.getAbsolutePath())
                .threadEnabled(true)
                .build();
    }

    public void saveConfigOption(UserConfiguration userConfiguration) {
        try (FileWriter fileWriter = new FileWriter("config.json")) {
            fileWriter.write(new Gson().toJson(userConfiguration));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

}
