package rocnikovyprojekt;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		DeterministicFiniteAutomaton.TransitionFunction<Integer, Character> function = 
				new DeterministicFiniteAutomaton.TransitionFunction<>();
		function.put(0, 'h', 1);
		function.put(1, 'o', 0);
		HashSet<Integer> finalStates = new HashSet<>();
		finalStates.add(0);
		DeterministicFiniteAutomaton<Integer, Character> machine = new DeterministicFiniteAutomaton<>(
				function, 0, finalStates);
		Word<Character> w = new Word<>();
		w.add('h');
		w.add('o');
		w.add('h');
		w.add('o');
		
		if (machine.accepts(w)) {
			System.out.println("akceptujem");
		} else {
			System.out.println("neakceptujem");
		}
		
		for (DeterministicFiniteAutomaton.Configuration<Integer> conf : machine.getLastComputation()) {
			System.out.println(conf.getPosition() + ", " + conf.getState());
		}
	}
}
