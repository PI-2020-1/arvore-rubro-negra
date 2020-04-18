package entities;

public class AVL {
	private Node raiz;

	public Node getRaiz() {
		return raiz;
	}

	public void setRaiz(Node raiz) {
		this.raiz = raiz;
	}
	
	public void criaArvore(int valor) {
		raiz = new Node();
		raiz.setPai(null);
		raiz.setEsq(null);
		raiz.setDir(null);
		raiz.setColor((byte) 1);
		raiz.setKey(valor);
	}
	
	public void emOrdem(Node node) {
		if(!(node == null)) {
			emOrdem(node.getEsq());
			System.out.println(node.getKey());
			emOrdem(node.getDir());
		}else {
			return;
		}
	}
	
	public void testa(Node node) {
		if(!(node != null)) {
			testa(node.getEsq());
			System.out.println(node.getKey() + " " + node.getColor());
			testa(node.getDir());
		}
	}
	
	public void posOrdem(Node node) {
		if(node != null) {
			posOrdem(node.getEsq());
			posOrdem(node.getDir());
			System.out.println(node.getKey());
		}
	}
	
	public int posto(Node node) {
		int p1, p2, p;
		if(node != null) {
			p1 = posto(node.getEsq());
			p2 = posto(node.getDir());
			p = p2;
			if(node.getColor() == 1) {
				p = p + 1;
				System.out.println(node.getKey() + "/" + (p-1));
			}else {
				System.out.println(node.getKey() + "/" + p);
			}
			return p;
		}else {
			return 1;
		}
	}
	
	public void insert(int valor, Node node, Node pai) {
		if(node != null) {
			if(valor < node.getKey()) {
				insert(valor, node.getEsq(), node);
			}else {
				insert(valor, node.getDir(), node);
			}
		}else {
			Node novo = new Node();
			novo.setPai(pai);
			novo.setEsq(null);
			novo.setDir(null);
			novo.setColor((byte) 2);
			novo.setKey(valor);
			
			if(valor < pai.getKey()) {
				pai.setEsq(novo);
			}else {
				pai.setDir(novo);
			}
			corrige1(novo);
			raiz.setColor((byte) 1);
		}
	}
	
	public void corrige1(Node node) {
		if(node.getPai() == null) {
			node.setColor((byte) 1);
		}else {
			corrige2(node);
		}
	}
	
	public void corrige2(Node node) {
		if(node.getPai().getColor() == 1) {
			return;
		}else {
			corrige3(node);
		}
	}
	
	public void corrige3(Node node) {
		Node aux, aux2;
		aux = new Node();
		aux = aux.tio(node);
		aux2 = new Node();
		
		if((aux != null) & (aux.getColor() == 2)) {
			node.getPai().setColor((byte) 1);
			aux.setColor((byte) 1);
			aux2 = aux2.avo(node);
			aux2.setColor((byte) 2);
			corrige1(aux2);
		}else {
			corrige4(node);
		}
	}
	
	public void corrige4(Node node) {
		Node aux = new Node();
		aux = aux.avo(node);
		
		if((node == node.getPai().getDir()) & (node.getPai() == aux.getEsq())) {
			rotEsq(node.getPai());
			node = node.getEsq();
		}else if((node == node.getPai().getEsq()) & (node.getPai() == aux.getDir())) {
			rotDir(node.getPai());
			node = node.getDir();
		}
		corrige5(node);
	}
	
	public void corrige5(Node node) {
		Node aux = new Node();
		aux = aux.avo(node);
		node.getPai().setColor((byte) 1);
		aux.setColor((byte) 2);
		if((node == node.getPai().getEsq()) & (node.getPai() == aux.getEsq())) {
			rotDir(aux);
		}else {
			rotEsq(aux);
		}
	}
	
	public void rotEsq(Node node) {
		Node aux = node.getDir();
		if(node.getPai() != null) {
			if(node.getPai().getDir() == node) {node.getPai().setDir(aux);}
			if(node.getPai().getEsq()==node) {node.getPai().setEsq(aux);}
		}else {
			raiz = aux;
		}
		
		node.setDir(aux.getEsq());
		aux.setEsq(node);
	}
	
	public void rotDir(Node node) {
		Node aux = node.getEsq();
		if(node.getPai() != null) {
			if(node.getPai().getDir() == node) {node.getPai().setDir(aux);}
			if(node.getPai().getEsq() == node) {node.getPai().setEsq(aux);}
		}else {
			raiz = aux;
		}
		
		node.setEsq(aux.getDir());
		aux.setDir(node);
	}
}
