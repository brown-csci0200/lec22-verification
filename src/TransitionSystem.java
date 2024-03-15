package src;

import java.util.*;
import java.util.function.Function;

public class TransitionSystem {
    TrafficState startState;
    HashMap<TrafficState, Vertex> allVertices;

    public TransitionSystem(TrafficState start) {
        this.allVertices = new HashMap<>();
        this.addVertex(start);
        this.startState = start;
    }

    public void addVertex(TrafficState label) {
        if (!allVertices.containsKey(label)) {
            this.allVertices.put(label, new Vertex(label));
        }
    }

    public void addTransition(String transitionLabel, TrafficState fromLabel, TrafficState toLabel) {
        this.addVertex(fromLabel);
        this.addVertex(toLabel);
        Vertex startV = this.allVertices.get(fromLabel);
        Vertex endV = this.allVertices.get(toLabel);
        startV.nextStates.put(transitionLabel, endV);
    }

    // public ? runSystem

    public boolean canBeBad(Function<TrafficState, Boolean> badProperty) {
        Vertex start = this.allVertices.get(this.startState);
        return start.canVisitBad(badProperty);
    }

    public boolean canLoop() {
        Vertex start = this.allVertices.get(this.startState);
        return start.canLoop();
    }
}
