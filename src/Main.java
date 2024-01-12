import constants.Alphabet;

import constants.UserCommands;
import data.Directory;
import encryptions.Bruteforce;
import encryptions.Cipher;
import encryptions.Encryption;
import service.IOService;

import java.util.Scanner;

public class Main {

    private final static Cipher encrypter = ((key, iterator) -> iterator + key > Alphabet.ALPHABET.length - 1 ?
            Math.abs(Alphabet.ALPHABET.length - (iterator + key)) : iterator + key);

    private final static Cipher decrypter = ((key, iterator) -> iterator - key < 0 ?
            Math.abs(Alphabet.ALPHABET.length + (iterator - key)) : iterator - key);


    public static void main(String[] args) {

        do {
           switch (getUserCommand()){
               case EXIT: return;
               case ENCRYPT: doEncrypt();
                   break;
               case DECRYPT: doDecrypt();
                   break;
               case BRUTEFORCE: doBruteForce();
                   break;
               case UNKNOWN:
                   System.out.println("Неизвестная команда!");
           }
        } while (true);

    }

    private static UserCommands getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите цель и введите соответствующую цифру:");
        System.out.println("1. Шифрование");
        System.out.println("2. Дешифрование");
        System.out.println("3. Взлом методом Bruteforce");
        System.out.println("4. Завершить программу");
        if (!scanner.hasNextInt()) {
            return UserCommands.UNKNOWN;
        }
        switch (scanner.nextInt()){
            case 1: return UserCommands.ENCRYPT;
            case 2: return UserCommands.DECRYPT;
            case 3: return UserCommands.BRUTEFORCE;
            case 4: return UserCommands.EXIT;
            default: return UserCommands.UNKNOWN;
        }
    }

    private static void doEncrypt (){
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        int keyUser = getKeyFromUser();
        Encryption encrypt = new Encryption(directory);
        encrypt.encryption(keyUser, encrypter);
    }

    private static void doDecrypt (){
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        int keyUser = getKeyFromUser();
        Encryption encrypt = new Encryption(directory);
        encrypt.encryption(keyUser, decrypter);
    }

    private static void doBruteForce (){
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        Bruteforce bruteforce = new Bruteforce(directory);
        bruteforce.doBruteforce(decrypter);
    }

    private static int getKeyFromUser (){
        System.out.println("Введите ключ шифрования: ");
        int keyUser = new Scanner(System.in).nextInt();
        return keyUser;
    }
}