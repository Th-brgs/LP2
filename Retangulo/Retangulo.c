#include <stdio.h>

typedef struct {
  int w, h;
  int x, y;
} retangulo;

void print (retangulo* r) {
  printf("Retangulo de tamanho  (%d,%d) na posicao (%d,%d). \n",r->w, r-> h, r->x, r->y);
}

void main (void) {
    retangulo r1 = { 2,5, 20,50};
    print(&r1);
}
