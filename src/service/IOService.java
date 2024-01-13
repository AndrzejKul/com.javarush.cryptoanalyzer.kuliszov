package service;

import exceptions.ExitException;

import java.util.Scanner;

public class IOService {

    public String getText() {
        return checkForExit(new Scanner(System.in).nextLine());
    }

    public Integer getInt() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        checkForExit(scanner.nextLine());
        return null;
    }

    public String checkForExit(String userInput) {
        if (userInput.equalsIgnoreCase("exit")) {
            throw new ExitException();
        }
        return userInput;
    }


}
