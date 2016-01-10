package rocnikovyprojekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DeterministicFiniteAutomaton<S, A> {
	
	private TransitionFunction<S, A> transitionFunction;
	private S startState;
	private Set<S> finalStates;
	private ArrayList<Configuration<S>> lastComputation;
	
	public DeterministicFiniteAutomaton(TransitionFunction<S, A> transitionFunction,
			S startState, Set<S> finalStates) {
		this.transitionFunction = transitionFunction;
		this.startState = startState;
		this.finalStates = finalStates;
	}
	
	public boolean accepts(Word<A> word) {
		S currentState = startState;
		lastComputation = new ArrayList<>();
		lastComputation.add(new Configuration<S>(startState, 0));
		
		for (int i = 0; i < word.length(); i++) {
			if (!transitionFunction.containsKey(currentState, word.get(i)))	{
				return false;
			}
			currentState = transitionFunction.get(currentState, word.get(i));
			lastComputation.add(new Configuration<S>(currentState, i+1));
		}
		return finalStates.contains(currentState);
	}
	
	public List<Configuration<S>> getLastComputation() {
		return lastComputation;
	}
	
	public static class Configuration<S> {
		private S state;
		private int position;
		
		public Configuration(S state, int position) {
			this.state = state;
			this.position = position;
		}
		
		public S getState() {
			return state;
		}
		
		public int getPosition() {
			return position;
		}
	}
	
	public static class TransitionFunction<S, A> {
		
		private HashMap<List<Object>, S> map = new HashMap<>();
		
		public void put(S state, A symbol, S newState) {
			map.put(createList(state, symbol), newState);
		}
		
		S get(S state, A symbol) {
			return map.get(createList(state, symbol));
		}
		
		public boolean containsKey(S state, A symbol) {
			return map.containsKey(createList(state, symbol));
		}
		
		private List<Object> createList(S state, A symbol) {
			ArrayList<Object> l = new ArrayList<Object>(2);
			l.add(state);
			l.add(symbol);
			return l;
		}
	}
	
}
