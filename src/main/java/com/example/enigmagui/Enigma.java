package com.example.enigmagui;

/// A software implementation of an Enigma Machine
public class Enigma {
    Rotor rotor1;
    Rotor rotor2;
    Rotor rotor3;
    Reflector reflector;
    Plugboard plugboard;
    char[] encrypt;

    Enigma(Rotor a, Rotor b, Rotor c, Reflector r, Plugboard p, String encrypt) {
        rotor1 = a;
        rotor2 = b;
        rotor3 = c;
        reflector = r;
        plugboard = p;
        this.encrypt = encrypt.toCharArray();
    }

    /// Rotates the rotors (Rotor 1 every key press, Rotor 2 every 26 key presses and Rotor 3 every 676)
    private void update(Rotor a, Rotor b, Rotor c) {
        a.rotate(a.encrypt);
        a.notch = a.notch + 1;
        if (a.notch % 26 == 0) {
            b.rotate(b.encrypt);
            b.notch = b.notch + 1;
        }
        if (b.notch % 26 == 0) {
            c.rotate(c.encrypt);
            c.notch = c.notch + 1;
        }
    }

    /// Encrypts the input string
    String encrypt() {
        rotor1.startPos();
        rotor2.startPos();
        rotor3.startPos();

        int length = encrypt.length;
        char[] output = new char[length];

        for (int i = 0; i < length; i++) {
            if (encrypt[i] == ' ') output[i] = ' ';
            else {
                char pb = plugboard.encrypt(encrypt[i]);
                char r1 = rotor1.encrypt(pb);
                char r2 = rotor1.encrypt(r1);
                char r3 = rotor2.encrypt(r2);
                char rf = reflector.encrypt(r3);
                r3 = rotor3.encrypt(rf);
                r2 = rotor3.encrypt(r3);
                r1 = rotor2.encrypt(r2);
                pb = plugboard.encrypt(r1);
                output[i] = pb;
                update(rotor1, rotor2, rotor3);
            }
        }
        String s = new String(output);
        return s;
    }

    /// Represents the Plugboard of an Enigma Machine
    public static class Plugboard {
        private final char[] input;
        private  final char[] output;

        Plugboard(String input, String output) {
            this.input = input.toCharArray();
            this.output = output.toCharArray();
        }

        /// Encrypts a given character
        private char encrypt(char c) {
            for (int i = 0; i < 26; i++) {
                if (input[i] == c) return output[i];
            }
            return '/';
        }
    }

    /// Represents a reflector in an Enigma Machine
    public static class Reflector {
        private final char[] input;
        private final char[] output;

        Reflector(String output) {
            String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            input = alpha.toCharArray();
            this.output = output.toCharArray();
        }

        /// Encrypts a given character
        private char encrypt(char c) {
            for (int i = 0; i < 26; i++) {
                if (input[i] == c) return output[i];
            }
            return '/';
        }
    }

    /// Represents a rotor of an Enigma Machine
    public static class Rotor {
        private final int position;
        private int notch;
        private char[] encrypt;

        Rotor(int p, int n, String encrypt) {
            position = p;
            notch = n;
            this.encrypt = encrypt.toCharArray();
        }

        /// Rotates the rotor
        private void rotate(char[] encrypt) {
            char store = encrypt[25];
            char[] encStore = new char[26];
            for (int i = 0; i < 25; i++) {
                encStore[i + 1] = encrypt[i];
            }
            encStore[0] = store;
            this.encrypt = encStore;
        }

        /// Rotates the rotor by a given number of positions
        private void startPos() {
            for (int i = 1; i < position; i++) {
                rotate(encrypt);
            }
        }

        /// Encrypts a given character
        private char encrypt(char c) {
            for (int i = 0; i < 26; i++) {
                if (encrypt[i] == c) return this.encrypt[i];
            }
            return '/';
        }
    }
}
