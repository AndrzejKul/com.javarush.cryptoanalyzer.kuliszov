package data;

import java.io.*;

public final class Directory {

    private final File inputFile;
    private final File outputFile;

    public Directory(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

}
