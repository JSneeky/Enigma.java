public class Reflector {
    char[] input;
    char[] output;

    Reflector(String output) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.input = alpha.toCharArray();
        this.output = output.toCharArray();
    }

    public char encrypt(char c) {
        for (int i = 0; i < 26; i++) {
            if (input[i] == c) return output[i];
        }
        return '/';
    }
}
