public class Rotor {
    int num;
    int position;
    int notch;
    char[] encrypt;

    Rotor(int num, int p, int n, String encrypt) {
        this.num = num;
        this.position = p;
        this.notch = n;
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

    public void rotate(char[] encrypt) {
        char store = encrypt[25];
        char[] encStore = new char[26];
        for (int i = 0; i < 25; i++) {
            encStore[i + 1] = encrypt[i];
        }
        encStore[0] = store;
        this.encrypt = encStore;
    }

    public void startPos() {
        for (int i = 1; i < this.position; i++) {
            this.rotate(this.encrypt);
        }
    }

    public char rotor(char c, Rotor r) {
        for (int i = 0; i < 26; i++) {
            if (this.encrypt[i] == c) return r.encrypt[i];
        }
        return '/';
    }
}
