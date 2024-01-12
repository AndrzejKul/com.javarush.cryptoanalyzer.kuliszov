import constants.Alphabet;

import constants.UserCommands;
import data.Directory;
import exceptions.RuntimeIOException;
import exceptions.UserInputException;
import service.BruteforceService;
import service.CipherService;
import service.IOService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        do {
            try {
                switch (getUserCommand()) {
                    case EXIT:
                        return;
                    case ENCRYPT:
                        doEncrypt();
                        break;
                    case DECRYPT:
                        doDecrypt();
                        break;
                    case BRUTEFORCE:
                        doBruteForce();
                        break;
                    case UNKNOWN:
                        System.out.println("Неизвестная команда!");
                }
            } catch (RuntimeException exception){
                System.out.println("Произошла ошибка: " + exception.getMessage());
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
        switch (scanner.nextInt()) {
            case 1:
                return UserCommands.ENCRYPT;
            case 2:
                return UserCommands.DECRYPT;
            case 3:
                return UserCommands.BRUTEFORCE;
            case 4:
                return UserCommands.EXIT;
            default:
                return UserCommands.UNKNOWN;
        }
    }

    private static void doEncrypt() {
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        int keyUser = getKeyFromUser();
        CipherService encrypt = new CipherService(directory);
        encrypt.applyCipher(keyUser, false);
        System.out.println("Операция прошла успешно");
        System.out.println("-----------------------");
    }

    private static void doDecrypt() {
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        int keyUser = getKeyFromUser();
        CipherService encrypt = new CipherService(directory);
        encrypt.applyCipher(-keyUser, true);
        System.out.println("Операция прошла успешно");
        System.out.println("-----------------------");
    }

    private static void doBruteForce() {
        IOService ioService = new IOService();
        Directory directory = ioService.initDirectory();
        BruteforceService bruteforceService = new BruteforceService(directory);
        bruteforceService.doBruteforce();
    }

    private static int getKeyFromUser() {
        System.out.println("Введите ключ шифрования: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            throw new UserInputException("При вводе ключа вы ввели не число");
        }
        int keyUser = scanner.nextInt();
        return keyUser;
    }
}