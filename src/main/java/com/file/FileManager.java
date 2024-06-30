package com.file;

import com.file.constants.Constant;
import com.file.operator.FileOperator;

/**
 * Main class initiates execution of {@link FileOperator#reverseFileContent(java.lang.String, java.lang.String)}
 *
 */
public class FileManager {
    public static void main(String args[]) {
        FileOperator.reverseFileContent(Constant.INPUT_FILE_NAME,Constant.OUTPUT_FILE_NAME);
    }
}
