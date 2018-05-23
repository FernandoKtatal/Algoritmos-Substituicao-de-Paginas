import java.util.ArrayList;

public class Stack4LRU {
	private ArrayList<Integer> stack;
	private int top;
	private int bottom;
	
	public Stack4LRU() {
		this.stack = new ArrayList<>();
		this.top = -1;
		this.bottom = 0;
	}
	
	public boolean isEmpty() {
		if (top == -1)
			return true;
		else 
			return false;
	}
	
	public void att(Integer n) {
		if(this.stack.indexOf(n) == -1) {
			this.stack.add(n);
			this.top++;
			return;
		}
		
		this.stack.remove(n); //remove o menor elemento da lista
		this.stack.add(n);
	}
	
	public void remove(Integer n) {
		if(!(this.stack.indexOf(n) == -1))
			this.stack.remove(n);
	}
	
	public int getBase() {
		if(!isEmpty())
			return this.stack.get(0);
		return 0;
	}
	
}


