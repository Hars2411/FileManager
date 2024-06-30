package com.file.operator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class to verify
 * {@link FileOperator#reverseFileContent(java.lang.String, java.lang.String)}
 *
 */
public class FileOperatorTest {

    private File inputFile = null;
    private File outputFile = null;
    private Path path = null;
    private List<String> lines = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        inputFile = File.createTempFile("testInput", ".txt");
        outputFile = File.createTempFile("result", ".txt");

        // write content to input file
        path = Paths.get(inputFile.getAbsolutePath());
        String input = "ABC";
        lines.add(input);

        Files.write(path, lines);
    }

    @Test
    public void testReverseFileContent() throws IOException {
        FileOperator.reverseFileContent(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        Stream<String> line = Files.lines(Paths.get(outputFile.getAbsolutePath()));
        List<String> result = line.parallel().map(data -> new StringBuilder(data).reverse().toString()).collect(Collectors.toList());

        Assertions.assertTrue(lines.equals(result));
    }

    @Test
    public void testReverseFileContent_multiLines() throws IOException {
        lines.add("Multiple");
        lines.add("lines");
        lines.add(" Added .");
        Files.write(path, lines);

        FileOperator.reverseFileContent(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        Stream<String> streamOfString = Files.lines(Paths.get(outputFile.getAbsolutePath()));
        List<String> result = streamOfString.parallel().map(data -> new StringBuilder(data).reverse().toString()).collect(Collectors.toList());
        Assertions.assertTrue(lines.equals(result));
    }

    @Test
    public void testReverseFileContent_emptyFile() throws IOException {
        new RandomAccessFile(inputFile.getAbsolutePath(), "rw").setLength(0);
        FileOperator.reverseFileContent(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());

        Stream<String> streamOfString = Files.lines(Paths.get(outputFile.getAbsolutePath()));
        Assertions.assertNotNull(streamOfString);
        Assertions.assertTrue(streamOfString.count() == 0);
    }
}
