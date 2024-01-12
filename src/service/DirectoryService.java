package service;

import exceptions.RuntimeIOException;

import java.io.*;

public class DirectoryService {
    public String readText(File inputFile) {

        String resultText = "";
        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             BufferedInputStream bufferedReader = new BufferedInputStream(fileInputStream)) {
            byte[] readAllBytes = bufferedReader.readAllBytes();
            resultText = new String(readAllBytes);
        } catch (IOException exception) {
            throw new RuntimeIOException("Ошибка при считывании файла");
        }
        return resultText;
    }

    public void writeText(String text, File outputFile) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] resultByteArray = text.getBytes();
            bufferedOutputStream.write(resultByteArray);
        } catch (IOException exception) {
            throw new RuntimeIOException("Ошибка записи файла");
        }
    }

}
