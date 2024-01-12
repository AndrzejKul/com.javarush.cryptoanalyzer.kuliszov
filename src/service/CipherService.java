package service;

import constants.Alphabet;
import data.Directory;

import java.util.List;

public class CipherService {
    private final Directory directory;
    private final DirectoryService directoryService;

    public CipherService(Directory directory){
        this.directory = directory;
        this.directoryService = new DirectoryService();
    }

    public Directory getDirectory() {
        return directory;
    }

    public String applyCipher(int key, boolean failOnUnknown) {
        ShiftCharService shiftCharService = new ShiftCharService();
        String originalText = directoryService.readText(directory.getInputFile());
        StringBuilder stringBuilder = new StringBuilder();
        char[] cipherArray = originalText.toLowerCase().toCharArray();

        for (int i = 0; i < cipherArray.length; i++) {
            char textCharacter = cipherArray[i];
            Character shiftChar = shiftCharService.shiftChar(textCharacter, key, failOnUnknown);
            if (shiftChar != null) {
                if (Character.isUpperCase(originalText.charAt(i))) {
                    shiftChar = Character.toUpperCase(shiftChar);
                }
                stringBuilder.append(shiftChar);
            }
        }
        String finalText = stringBuilder.toString();
        directoryService.writeText(stringBuilder.toString(), directory.getOutputFile());
        return finalText;
    }

}
