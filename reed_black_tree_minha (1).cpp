#include<stdlib.h>
#include<stdio.h>
#include<math.h>
#include<conio.h>
#include<locale.h>
#define RUBRO 1
#define NEGRO 2

typedef struct arvore{
	int num;
	int cor;
	struct arvore * pai;
	struct arvore * esquerda;
	struct arvore * direita;
}arvore;

arvore * aloca_arvore();
arvore * ptraiz;

void menu(arvore * a);
void insere_arvore(arvore * a,int x);
void mostrar_arvore(arvore * a);
void corrige(arvore * a);
void corrige2(arvore * a);
void corrige3(arvore * a);
void corrige4(arvore * a);
void rotEsq(arvore * a);
void rotDir(arvore * a);

int main(){
	
	arvore * a;
	a = aloca_arvore();
	
	menu(a);
	
	getch();
}

arvore * aloca_arvore(){
	arvore * novo;
	novo =(arvore*)malloc(sizeof(arvore));
	novo->num = 0;
	novo->cor = 0;
	novo->pai = NULL;
	novo->esquerda = NULL;
	novo->direita = NULL;
	return novo;
}

arvore * avo(arvore *a){
	if((a != NULL) && (a->pai != NULL)){
		return a->pai->pai;
	}
	else{
		return NULL;
	}
}

arvore* tio(arvore *a)
{
	arvore *n = avo(a);
	if (n == NULL){
		return NULL;
	}
	if (a->pai == n->esquerda){
		return n->direita;
	}
	else{
		return n->esquerda;
	}
}

void insere_arvore(arvore * a,int x){
	arvore * novo;
	novo = aloca_arvore();
	novo->num = x;
	novo->cor = RUBRO;
	
	if (a->num==0){
		a->num=x;
		a->cor=NEGRO;
		printf("chegou.1");	
	}else{
		printf("chegou.2");
		if(x>a->num){
			if(a->direita==NULL){
				printf("chegou.3");
				novo->pai=a;
				a->direita=novo;
				corrige(a->direita);
			}else{
				insere_arvore(a->direita, x);
			}
		}else if(x<a->num){
			printf("chegou.4");
			if(a->esquerda==NULL){
				novo->pai=a;
				a->esquerda=novo;
				corrige(a->esquerda);
			}else{
				insere_arvore(a->esquerda, x);
			}
		}else{
			printf("Número já inserido");
		}
		
	}
}

void corrige(arvore * a){
	if (a->pai->cor == NEGRO)
		return;
	else
		corrige2(a);
}

void corrige2(arvore * a){
	arvore *u = tio(a), *g;
	
	if ((u != NULL) && (u->cor == RUBRO)) {
		a->pai->cor = NEGRO;
		u->cor = NEGRO;
		g = avo(a);
		g->cor = RUBRO;
		if (g->pai == NULL){
			g->cor = NEGRO;
		}
	} else {
		corrige3(a);
	}
}

void corrige3(arvore * a)
{
	arvore *g = avo(a);
	
	if ((a == a->pai->direita) && (a->pai == g->esquerda)) {
		rotEsq(a->pai);
		a = a->esquerda;
	} else if ((a == a->pai->esquerda) && (a->pai == g->direita)) {
		rotDir(a->pai);
		a = a->direita;
	}
	corrige4(a);
}

void corrige4(arvore * a)
{
	arvore *g = avo(a);
	if(g==NULL){
		return;
	}
	a->pai->cor = NEGRO;
	g->cor = RUBRO;
	if ((a == a->pai->esquerda) && (a->pai == g->esquerda)){
		rotDir(g);
	} else {
		rotEsq(g);
	}
}


void mostrar_arvore(arvore * a){
	printf ("numero: %d \n", a->num);
	printf ("cor: %d \n", a->cor);
	printf ("pai: %d \n", a->pai);
	printf ("esquerda: %d \n", a->esquerda);
	printf ("direita: %d \n", a->direita);	
	if (a->esquerda!=NULL) {      
		mostrar_arvore(a->esquerda);       
		//printf (" %d ", a->num);
    }
	if(a->direita!=NULL){
		mostrar_arvore(a->direita);
		//printf (" %d ", a->num);
	}
}

void rotEsq(arvore * a){
	arvore * Q = a->direita;
	if(a->pai != NULL){
		if(a->pai->direita==a) {
			a->pai->direita=Q;
		}
		if(a->pai->esquerda==a) {
			a->pai->esquerda=Q;
		}
	}
	else{
	ptraiz=Q;
	}
	
	a->direita = Q->esquerda;
	Q->esquerda = a;
}

void rotDir(arvore * a){
	arvore * Q = a->esquerda;
	if(a->pai != NULL){
		if(a->pai->direita==a){
			a->pai->direita=Q;
		}
		if(a->pai->esquerda==a) {
		a->pai->esquerda=Q;
		}
	}
	else ptraiz=Q;
	
	a->esquerda = Q->direita;
	Q->direita = a;
}

void menu(arvore * a){
	setlocale(LC_ALL, "Portuguese");
	int op, num;
	
	do{
		system("cls");
		printf("1 - Inserir\n");
		printf("2 - Remover\n");
		printf("3 - Mostrar\n");
		printf("4 - Sair\n");
		scanf("%d", &op);
		
		switch(op){
			case 1:
				printf("Qual número deseja incluir na árvore?\n");
				scanf("%d", &num);
				
				insere_arvore(a,num);
				
				getch();
			break;
			case 2:
			break;
			case 3:
				
				mostrar_arvore(a);
				
				getch();
			break;
			case 4:
				printf("Saindo...");
			break;
			default:
				printf("Opção inválida!");
				getch();
			break;
		}
		
	}while(op!=4);
	
	
	getch();
}
