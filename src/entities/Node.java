package entities;

public class Node {
	//1 - BLACK, 2 - RED
	private byte color;
	private int value;
	private int posto;
	private Node pai;
	private Node esq;
	private Node dir;
	
	
	public byte getColor() {
		return color;
	}
	public void setColor(byte color) {
		this.color = color;
	}
	public int getKey() {
		return value;
	}
	public void setKey(int key) {
		this.value = key;
	}
	public int getPosto() {
		return posto;
	}
	public void setPosto(int posto) {
		this.posto = posto;
	}
	public Node getPai() {
		return pai;
	}
	public void setPai(Node pai) {
		this.pai = pai;
	}
	public Node getEsq() {
		return esq;
	}
	public void setEsq(Node esq) {
		this.esq = esq;
	}
	public Node getDir() {
		return dir;
	}
	public void setDir(Node dir) {
		this.dir = dir;
	}
	
	public Node avo(Node node) {
		if((node != null) & (node.pai != null)) {
			return node.pai.pai;
		}else {
			return null;
		}
	}
	
	public Node tio(Node node) {
		Node aux = avo(node);
		if(aux == null) {
			return null;
		}if(node.pai == aux.esq) {
			return aux.dir;
		}else {
			return node.esq;
		}
	}
}
