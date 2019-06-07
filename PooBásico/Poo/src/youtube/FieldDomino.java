package youtube;

import youtube.Domineering.Domino;

public class FieldDomino implements Domino {
	
	private int left;
	private int right;
	
	public FieldDomino(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public void flip() {
		int swap = left;
		left = right;
		right = swap;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}
	
	public String toString() {
		return left + "-" + right;
	}

}
