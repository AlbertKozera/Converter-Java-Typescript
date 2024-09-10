package service;


import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class GeneratorServiceTest {

    private GeneratorService sut = new GeneratorService();


    @Test
    public void return_empty_list() {
        //when
        Collection<File> result = sut.checkFile(null);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    public void return_one_element() throws IOException {
        //given
        Collection<File> list = new ArrayList<>();
        String test = "Hello";
        File testFile = File.createTempFile("file", ".txt");
        File emptyFile = File.createTempFile("emptyFile", ".txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write(test);
        writer.close();
        list.add(testFile);
        list.add(emptyFile);
        //when
        Collection<File> result = sut.checkFile(list);
        //then
        assertThat(result).size().isEqualTo(1);

    }

}
