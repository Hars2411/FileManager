package com.file.operator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class responsible for performing below operations:
 * <ul>
 * <li>Reading ASCII input from text file</li>
 * <li>Reversing read content</li>
 * <li>Writing reverse content to output file</li>
 * </ul>
 *
 *
 */
public class FileOperator {
    
    private static final Logger LOGGER = Logger.getLogger(FileOperator.class.getName());

    /**
     * Reverse file content.
     * <ul>
     * <li>Input file is present inside resources folder.</li>
     * <li>Output file will be created inside resources folder</li>
     * </ul>
     * 
     * @param inputFile input file containing ASCII content
     * @param outputFile output file to be created to contain reverse ASCII characters
     */
    public static void reverseFileContent(String inputFile, String outputFile) {
        LOGGER.info("Reverse file content flow started");

        // Read entire file content in stream
        try (Stream<String> line = Files.lines(Paths.get(inputFile))) {
            List<String> result = line.parallel().map(data -> new StringBuilder(data).reverse().toString()).collect(Collectors.toList());
            
            if (null != result && !result.isEmpty()) {
                Path path = Paths.get(outputFile);
                // Write reverse string to output file
                Files.write(path, result);
            } else {
                LOGGER.warning("No content to reverse");
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error during processing file", ex);
        }
        LOGGER.info("Reverse file content flow completed");
    }
}
