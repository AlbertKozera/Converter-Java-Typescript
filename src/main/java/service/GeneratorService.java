package service;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class GeneratorService {

    public Collection<File> checkFile(Collection<File> selectedFiles) {
        return (Objects.isNull(selectedFiles) || selectedFiles.isEmpty()) ? Collections.emptyList() : checkSize(selectedFiles);

    }

    private Collection<File> checkSize(Collection<File> selectedFiles) {
        return selectedFiles.stream()
                .filter(file -> file.length() != 0)
                .collect(Collectors.toSet());
    }

}
