public class Plugboard {
    char[] input;
    char[] output;

    Plugboard(String input, String output) {
        this.input = input.toCharArray();
        this.output = output.toCharArray();
    }

    public char encrypt(char c) {
        for (int i = 0; i < 26; i++) {
            if (input[i] == c) return output[i];
        }
        return '/';
    }
}
