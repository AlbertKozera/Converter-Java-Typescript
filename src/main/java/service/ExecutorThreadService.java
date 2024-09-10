package service;

import converter.type.Converter;
import lombok.extern.log4j.Log4j;
import config.UserConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j
public class ExecutorThreadService {

    public Collection<File> convert(Collection<File> files, Converter converter, String fileExtension) {
        UserConfiguration userSettings = LoaderService.loadConfigFile();
        log.info("Conversion started");
        log.info("Number of files:" + files.size());
        ExecutorService executor = Executors.newFixedThreadPool(userSettings.isThreadEnabled() ? Runtime.getRuntime().availableProcessors() : 1);
        List<Callable<Object>> callables = new ArrayList<>(files.size());
        long start = System.currentTimeMillis();

        for (File file : files) {
            callables.add(Executors.callable(new FileConverter(file, fileExtension, converter, userSettings)));
        }

        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            log.info(e);
        }
        long end = System.currentTimeMillis();
        log.info("Conversion end successfully");
        log.info("Conversion duration:" + (end - start) + "ms");
        return Collections.emptyList();
    }


}
