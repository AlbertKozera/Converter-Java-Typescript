package service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import config.UserConfiguration;

import java.io.*;
import java.util.Map;

public class LoaderService {
    public static Map loadExtension() {
        Gson gson = new Gson();
        InputStream inputStream = LoaderService.class.getResourceAsStream("/option/files_extension.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(bufferedReader, Map.class);
    }

    public static Map<String, String> loadIcon() {
        Gson gson = new Gson();
        InputStream inputStream = LoaderService.class.getResourceAsStream("/option/image.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(bufferedReader, Map.class);
    }

    public static UserConfiguration loadConfigFile() {
        Gson gson = new Gson();
        try (JsonReader jsonReader = new JsonReader(new FileReader("config.json"))) {
            return gson.fromJson(jsonReader, UserConfiguration.class);
        } catch (IOException e) {
            return UserConfiguration.builder()
                    .build();
        }
    }
}
