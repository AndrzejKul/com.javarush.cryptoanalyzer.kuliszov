package service;

import data.Directory;

import java.io.File;
import java.util.Scanner;

public class IOService {

    public Directory initDirectory() {
        File inputFile;
        File outputFile;
        boolean firstTry = true;
        Scanner scanner = new Scanner(System.in);
        do {
            if(firstTry){
                System.out.println("Укажите путь к файлу: ");
            } else {
                System.out.println("Файл не найден, введите верный адрес: ");
            }
            String fileName = scanner.nextLine();
            inputFile = new File(fileName);
            firstTry = false;
        } while ((!inputFile.isFile()));
        System.out.println("Укажите адрес создания нового файла: ");
        outputFile = new File(scanner.nextLine());
        return new Directory(inputFile, outputFile);
    }

}
