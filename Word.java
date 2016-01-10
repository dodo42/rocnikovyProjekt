package rocnikovyprojekt;

import java.util.ArrayList;
import java.util.List;

public class Word<A> {
	
	private List<A> symbols = new ArrayList<>();
	
	public Word() {
		super();
	}
	
	public Word(List<A> symbols) {
		this.symbols = symbols;
	}
	
	public A get(int index) {
		return symbols.get(index);
	}
	
	public void add(A symbol) {
		symbols.add(symbol);
	}
	
	public int length() {
		return symbols.size();
	}
}
