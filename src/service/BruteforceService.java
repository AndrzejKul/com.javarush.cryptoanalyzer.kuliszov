package service;

import data.Directory;

import java.util.Scanner;

public class BruteforceService {
    private final Directory directory;
    private final DirectoryService directoryService;

    public BruteforceService(Directory directory){
        directoryService = new DirectoryService();
        this.directory = directory;
    }

    public void doBruteforce() {
        int key = -1;
        do {
            String decrypyText = doBruteforceIteration(key, directory);
            System.out.println(decrypyText);
            System.out.println("Текст расшифрован? Введи Да или Нет");
            if (new Scanner(System.in).nextLine().equalsIgnoreCase("Да")){
                System.out.println("Все получилось");
                directoryService.writeText(decrypyText, directory.getOutputFile());
                return;
            } else {
                key -= 1;
            }

        } while (true);
    }

    public String doBruteforceIteration (int key, Directory directory){
        CipherService cipherService = new CipherService(directory);
        return cipherService.applyCipher(key, true);
    }

}
