public class Retangulo {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,10);
        r1.print();
    }
}
class Rect {
    int x, y;
    int w, h;
    int area;
    Rect (int x, int y, int w, int h) {
        this.w = w;
        this.h = h;
	this.area = this.w*this.h;
    }
    void print () {
        System.out.format("Retangulo com tamanho igual a (%d,%d) e area igual a(%d) na posicao (%d,%d).\n",
            this.w, this.h, this.area,this.x, this.y);
    }
}
