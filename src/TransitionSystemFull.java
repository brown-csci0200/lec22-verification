package src;

import java.util.*;
import java.util.function.Function;

public class TransitionSystemFull<StateType> {
    StateType startState;
    HashMap<StateType, Vertex<StateType>> allVertices;

    public TransitionSystemFull(StateType start) {
        this.allVertices = new HashMap<>();
        this.addVertex(start);
        this.startState = start;
    }

    public void addVertex(StateType label) {
        if (!allVertices.containsKey(label)) {
            this.allVertices.put(label, new Vertex<StateType>(label));
        }
    }

    public void addTransition(String transitionLabel, StateType fromLabel, StateType toLabel) {
        this.addVertex(fromLabel);
        this.addVertex(toLabel);
        Vertex<StateType> startV = this.allVertices.get(fromLabel);
        Vertex<StateType> endV = this.allVertices.get(toLabel);
        startV.nextStates.put(transitionLabel, endV);
    }

    public List<StateType> runSystem(ArrayList<String> events) {
        Vertex<StateType> currentState = this.allVertices.get(this.startState);
        ArrayList<StateType> result = new ArrayList<>();
        result.add(this.startState);

        for (String e : events) {
            currentState = currentState.getNextState(e);
            result.add(currentState.label);
        }

        return result;
    }

    // public ? runSystem

//    public boolean canBeBad(Function<TrafficState, Boolean> badProperty) {
//        Vertex<StateType> start = this.allVertices.get(this.startState);
//        return start.canVisitBad(badProperty);
//    }
//
//    public boolean canLoop() {
//        Vertex<StateType> start = this.allVertices.get(this.startState);
//        return start.canLoop();
//    }
}
