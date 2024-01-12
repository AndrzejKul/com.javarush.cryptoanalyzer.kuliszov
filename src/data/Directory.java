package data;

import java.io.*;
import java.util.Scanner;

public final class Directory {

    private final File inputFile;

    private final File outputFile;

    private String text;

    public Directory(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        text = readText();
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public String getText() {
        return text;
    }

/*    if (!inputFile.isFile()) {
        System.out.println("Файл не найден. Введите новый адрес:");
    } else {*/




public String readText() {

    String resultText = "";
    try (FileInputStream fileInputStream = new FileInputStream(inputFile);
         BufferedInputStream bufferedReader = new BufferedInputStream(fileInputStream)) {
        byte[] readAllBytes = bufferedReader.readAllBytes();
        resultText = new String(readAllBytes);
    } catch (IOException exception) {
        exception.getMessage();
        //throw new NotFoundFileException("Некоректный адрес исходного файла. Введите новый: ");

    }
    return resultText;
}

public void writeText(String text) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
        byte[] resultByteArray = text.getBytes();
        bufferedOutputStream.write(resultByteArray);
    } catch (IOException exception) {
        exception.getMessage();
        //throw new NotFoundFileException("Некоректный адрес результатирующего файла. Введите новый: ");
    }
}


}
