//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EnigmaMachine {
    public static void main(String[] args) {
        String encrypt = "ENTER STRING";

        Rotor rotor1 = new Rotor(1, 0, 1, "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        Rotor rotor2 = new Rotor(2, 0, 1, "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        Rotor rotor3 = new Rotor(3, 0, 1, "BDFHJLCPRTXVZNYEIWGAKMUSQO");
        Reflector reflector = new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        Plugboard plugboard = new Plugboard("AOBGQFSTJXZEHPILMNCVDKRUWY", "OAGBFQTSXJEZPHLINMVCDKRUWY");
        Enigma enigma = new Enigma(rotor1, rotor2, rotor3, reflector, plugboard, encrypt);

        System.out.println("Encryption:" + enigma.encrypt());
    }
}
