import constants.UserCommands;
import data.Directory;
import exceptions.AlarmHackersException;
import exceptions.ExitException;
import exceptions.TooManyAttemptsException;
import service.BruteforceService;
import service.CipherService;
import service.DirectoryService;
import service.IOService;

public class Main {
    public static final IOService ioService = new IOService();

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
            } catch (TooManyAttemptsException tooManyAttemptsException) {
                System.out.println("Превышено количество попыток ввода.");
            } catch (ExitException exitException) {
                System.out.println("Спасибо. Программа завершена.");
                return;
            } catch (AlarmHackersException alarmHackersException) {
                System.out.println("Нелегальные символы в тексте. Возможна атака хакеров!");
            } catch (RuntimeException exception) {
                System.out.println("Произошла ошибка: " + exception.getMessage());
            }
        } while (true);

    }

    private static UserCommands getUserCommand() {
        System.out.println("Выберите цель и введите соответствующую цифру:");
        System.out.println("1. Шифрование");
        System.out.println("2. Дешифрование");
        System.out.println("3. Взлом методом Bruteforce");
        System.out.println("4. Завершить программу");
        Integer inputUserNumber = ioService.getInt();
        if (inputUserNumber == null) {
            return UserCommands.UNKNOWN;
        }
        switch (inputUserNumber) {
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

    private static void printSuccess() {
        System.out.println("Операция прошла успешно");
        System.out.println("-----------------------");
    }

    private static void doEncrypt() {
        CipherService encrypt = new CipherService(getDirectory(), ioService);
        int keyUser = getKeyFromUser();
        encrypt.applyCipher(keyUser, false);
        printSuccess();
    }

    private static void doDecrypt() {
        CipherService encrypt = new CipherService(getDirectory(), ioService);
        int keyUser = getKeyFromUser();
        encrypt.applyCipher(-keyUser, true);
        printSuccess();
    }

    private static void doBruteForce() {
        BruteforceService bruteforceService = new BruteforceService(getDirectory(), ioService);
        bruteforceService.doBruteforce();
        printSuccess();
    }

    private static int getKeyFromUser() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Введите ключ шифрования: ");
            Integer inputUserNumber = ioService.getInt();
            if (inputUserNumber == null) {
                System.out.println("При вводе ключа вы ввели не число");
                continue;
            }
            if (inputUserNumber <= 0) {
                System.out.println("Ключ должен быть больше 0");
                continue;
            }
            return inputUserNumber;
        }
        throw new TooManyAttemptsException();
    }

    private static Directory getDirectory() {
        DirectoryService directoryService = new DirectoryService(ioService);
        return directoryService.initDirectory();
    }
}