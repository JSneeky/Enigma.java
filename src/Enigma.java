public class Enigma {
    Rotor rotor1;
    Rotor rotor2;
    Rotor rotor3;
    Reflector reflector;
    Plugboard plugboard;
    char[] encrypt;

    Enigma(Rotor a, Rotor b, Rotor c, Reflector r, Plugboard p, String encrypt) {
        this.rotor1 = a;
        this.rotor2 = b;
        this.rotor3 = c;
        this.reflector = r;
        this.plugboard = p;
        this.encrypt = encrypt.toCharArray();
    }

    public void update(Rotor a, Rotor b, Rotor c) {
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

    public String encrypt() {
        this.rotor1.startPos();
        this.rotor2.startPos();
        this.rotor3.startPos();

        int length = this.encrypt.length;
        char[] output = new char[length];

        for (int i = 0; i < length; i++) {
            if (this.encrypt[i] == ' ') output[i] = ' ';
            else {
                char pb = this.plugboard.encrypt(this.encrypt[i]);
                char r1 = this.rotor1.rotor(pb, rotor1);
                char r2 = this.rotor1.rotor(r1, rotor2);
                char r3 = this.rotor2.rotor(r2, rotor3);
                char rf = this.reflector.encrypt(r3);
                r3 = this.rotor3.rotor(rf, rotor3);
                r2 = this.rotor3.rotor(r3, rotor2);
                r1 = this.rotor2.rotor(r2, rotor1);
                pb = this.plugboard.encrypt(r1);
                output[i] = pb;
                this.update(this.rotor1, this.rotor2, this.rotor3);
            }
        }
        String s = new String(output);
        return s;
    }
}
