public class Retangulo {
    public static void main (String[] args) {
        Rect r1 = new Rect(15,25, 30,50);
        r1.print();
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void print () {
        System.out.format("Retangulo com tamanho igual a (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }
}
