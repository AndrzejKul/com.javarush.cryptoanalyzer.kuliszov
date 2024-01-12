package service;

import constants.Alphabet;
import exceptions.AlarmHackersException;

public class ShiftCharService {
    private final int ALPHABET_LENGTH = Alphabet.ALPHABET.length;

    public Character shiftChar (char ch, int shiftValue, boolean failOnUnknown){
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            if(ch == Alphabet.ALPHABET[i]){
                int newIndex = (((i+shiftValue) % ALPHABET_LENGTH) + ALPHABET_LENGTH) % ALPHABET_LENGTH;
                return Alphabet.ALPHABET[newIndex];
            }
        }
        if(ch == '\n'){
            return ch;
        }
        if (failOnUnknown){
            throw new AlarmHackersException ();
        }
        return null;
    }
}

