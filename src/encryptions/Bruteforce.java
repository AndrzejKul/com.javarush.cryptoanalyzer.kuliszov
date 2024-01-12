package encryptions;

import data.Directory;
import service.IOService;

import java.util.Scanner;

public class Bruteforce {
    private Directory directory;

    public Bruteforce(Directory directory){
        this.directory = directory;
    }

    public void doBruteforce(Cipher cipher) {
        int key = 1;
        do {
            String decryptedTemp = doBruteforceIteration(key, cipher, directory);
            System.out.println(decryptedTemp);
            System.out.println("Текст расшифрован? Введи Да или Нет");
            if (new Scanner(System.in).nextLine().equalsIgnoreCase("Да")){
                System.out.println("Все получилось");
                directory.writeText(decryptedTemp);
                return;
            } else {
                key +=1;
            }

        } while (true);
    }

    public String doBruteforceIteration (int key, Cipher cipher, Directory directory){
        Encryption encryption = new Encryption(directory);
        return encryption.encryption(key,cipher);
    }

}
