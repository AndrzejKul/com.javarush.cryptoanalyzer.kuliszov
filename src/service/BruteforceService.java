package service;

import constants.Alphabet;
import data.Directory;
import exceptions.TooManyAttemptsException;

public class BruteforceService {
    private final Directory directory;
    private final DirectoryService directoryService;
    private final IOService ioService;


    public BruteforceService(Directory directory, IOService ioService) {
        directoryService = new DirectoryService(ioService);
        this.directory = directory;
        this.ioService = ioService;
    }

    public void doBruteforce() {
        int key = -1;
        for (int i = 0; i < Alphabet.ALPHABET.length; i++) {
            String decryptText = doBruteforceIteration(key, directory);
            System.out.println(decryptText);
            System.out.println("Текст расшифрован? Введи Да или Нет");
            if (ioService.getText().equalsIgnoreCase("Да")) {
                directoryService.writeText(decryptText, directory.getOutputFile());
                return;
            } else {
                key -= 1;
            }
        }
        throw new TooManyAttemptsException();
    }

    public String doBruteforceIteration(int key, Directory directory) {
        CipherService cipherService = new CipherService(directory, ioService);
        return cipherService.applyCipher(key, true);
    }

}
