package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class Vertex<StateType> {
    StateType label;
    HashMap<String, Vertex<StateType>> nextStates;

    public Vertex(StateType l) {
        this.label = l;
        this.nextStates = new HashMap<>();
    }

    public Vertex<StateType> getNextState(String transition) {
        return this.nextStates.get(transition);
    }
}