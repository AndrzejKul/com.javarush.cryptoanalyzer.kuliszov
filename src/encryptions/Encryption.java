package encryptions;

import constants.Alphabet;
import data.Directory;
import service.IOService;

public class Encryption {
    private final Directory directory;

    public Encryption(Directory directory){
        this.directory = directory;
    }

    public Directory getDirectory() {
        return directory;
    }

    public String encryption(int key, Cipher cipher) {
        String text = directory.getText().toLowerCase();
        char[] textOriginal = text.toCharArray();

        for (int i = 0; i < textOriginal.length; i++) {
            char textCharacter = textOriginal[i];
            for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
                char alphabetCharacter = Alphabet.ALPHABET[j];
                if (Character.toString(textCharacter).equals(Character.toString(alphabetCharacter))) {

                    int index = cipher.shift(key, j);
                    textOriginal[i] = Alphabet.ALPHABET[index];
                }
            }
        }
        String originCase = toOriginCase(directory.getText(), new String(textOriginal));
        directory.writeText(originCase);
        return originCase;
    }

    private String toOriginCase(String originText, String writeText) {
        for (int i = 0; i < writeText.length(); i++) {
            if (Character.isUpperCase(originText.charAt(i))) {
                writeText = writeText.replace(writeText.charAt(i),
                        Character.toUpperCase(writeText.charAt(i)));
            }
        }
        return writeText;
    }
}
