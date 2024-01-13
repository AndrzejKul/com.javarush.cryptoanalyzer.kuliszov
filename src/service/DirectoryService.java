package service;

import data.Directory;
import exceptions.RuntimeIOException;

import java.io.*;

public class DirectoryService {
    private final IOService ioService;

    public DirectoryService(IOService ioService) {
        this.ioService = ioService;
    }

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

    public Directory initDirectory() {
        File inputFile;
        File outputFile;
        boolean firstTry = true;
        do {
            if (firstTry) {
                System.out.println("Укажите путь к файлу: ");
            } else {
                System.out.println("Файл не найден, введите верный адрес: ");
            }
            String fileName = ioService.getText();
            inputFile = new File(fileName);
            firstTry = false;
        } while ((!inputFile.isFile()));
        System.out.println("Укажите адрес создания нового файла: ");
        outputFile = new File(ioService.getText());
        return new Directory(inputFile, outputFile);
    }

}
