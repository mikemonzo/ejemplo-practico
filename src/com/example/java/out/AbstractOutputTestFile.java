package com.example.java.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.java.model.test.Test;

public abstract class AbstractOutputTestFile implements OutputTest {

    protected String testFilenamePrefix;
    protected Map<Test, String> pathForTests;
    protected String basePath;
    protected Path path;
    protected boolean folderCreated;
    protected String extension;
    protected boolean sameFile;

    public AbstractOutputTestFile(String extension){
        this.pathForTests = new HashMap<>();
        this.folderCreated = false;
        this.extension = extension;
        this.sameFile = false;
    }

    public AbstractOutputTestFile() {
        this(".out");
    }

    public String getTestFilenamePrefix() {
        return testFilenamePrefix;
    }

    public void setTestFilenamePrefix(String testFilenamePrefix){
        if (testFilenamePrefix != this.testFilenamePrefix){
            this.testFilenamePrefix = testFilenamePrefix;
            this.folderCreated = false;
        }
    }

    public boolean isSameFile() {
        return sameFile;
    }

    public void setSameFile(boolean sameFile) {
        this.sameFile = sameFile;
    }

    public void prepareFolder() throws IOException {
        if (testFilenamePrefix == null){
            throw new UnsupportedOperationException("No se ha especificado un prefijo para los archivos de test");
        }

        String datatime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        basePath = "./" + testFilenamePrefix + "-" + datatime;

        path = Paths.get(basePath);

        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
            Files.createDirectory(path);
            folderCreated = true;
        }
    }

    @Override
    public void outputTest(Test test) throws Exception {
        outputTest(test, true);
    }

    @Override
    public void outputCorreccion(Test test) throws Exception {
        if(!pathForTests.containsKey(test)){
            throw new UnsupportedOperationException("No se ha generado el archivo de test para el test " + test.getNombre());
        }

        String filename = testFilenamePrefix + "-" + test.getId() + "-solucion" + extension;

        List<String> result = formatCorreccion(test);
        Files.write(path.resolve(filename), result, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void outputTests(List<Test> testList) throws Exception {
        if (this.testFilenamePrefix == null){
            this.testFilenamePrefix = calculateTestFilenamePrefix(testList.get(0));
        }

        if (!folderCreated){
            prepareFolder();
        }

        if (sameFile){
            List<String> result =
                testList.stream()
                    .peek(test -> pathForTests.putIfAbsent(test, basePath))
                    .map(this::formatTest)
                    .flatMap(l -> l.stream())
                    .toList();

            String filename = testFilenamePrefix + "-unidos" + extension;
            Files.write(path.resolve(filename), 
                    result, 
                    StandardOpenOption.CREATE, 
                    StandardOpenOption.WRITE, 
                    StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            for (Test test : testList){
                String filename = testFilenamePrefix + "-" + test.getId() + extension;
                List<String> result = formatTest(test);
                Files.write(path.resolve(filename), 
                        result, 
                        StandardOpenOption.CREATE, 
                        StandardOpenOption.WRITE, 
                        StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }

    @Override
    public void outputCorrecciones(List<Test> testList) throws Exception {
        if(sameFile){
            List<String> result =
                testList.stream()
                    .peek(test -> pathForTests.putIfAbsent(test, basePath))
                    .map(this::formatCorreccion)
                    .flatMap(l -> l.stream())
                    .toList();

            String filename = testFilenamePrefix + "-soluciones" + extension;
            Files.write(path.resolve(filename), 
                    result, 
                    StandardOpenOption.CREATE, 
                    StandardOpenOption.WRITE, 
                    StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            for (Test test : testList){
                String filename = testFilenamePrefix + "-" + test.getId() + "-solucion" + extension;
                List<String> result = formatCorreccion(test);
                Files.write(path.resolve(filename), 
                        result, 
                        StandardOpenOption.CREATE, 
                        StandardOpenOption.WRITE, 
                        StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }

    private String calculateTestFilenamePrefix(Test test){
        String nombre = test.getNombre().toLowerCase().replaceAll("\\s", "");
        String curso = test.getCurso().toLowerCase().replaceAll("\\s", "");
        return nombre + curso;
    }

    private void outputTest(Test test, boolean doPrepareFolder) throws IOException {
        if (this.testFilenamePrefix == null){
            this.testFilenamePrefix = calculateTestFilenamePrefix(test);
        }

        if (doPrepareFolder && !folderCreated){
            prepareFolder();
        }

        pathForTests.putIfAbsent(test, basePath);

        String filename = testFilenamePrefix + "-" + test.getId() + extension;

        List<String> result = formatTest(test);

        Files.write(path.resolve(filename), result, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public abstract List<String> formatTest(Test test);
    public abstract List<String> formatCorreccion(Test test);

}
