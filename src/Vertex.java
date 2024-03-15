package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class Vertex {
    TrafficState label;
    // map from transition labels to the vertex that transition goes to
    HashMap<String, Vertex> nextStates;

    public Vertex(TrafficState l) {
        this.label = l;
        this.nextStates = new HashMap<>();
    }

    public Vertex getNextState(String transition) {
        return this.nextStates.get(transition);
    }

    public boolean canVisitBad(Function<TrafficState, Boolean> badProperty) {
        return this.canVisitBad_helper(badProperty, new HashSet<>());
    }

    private boolean canVisitBad_helper(
            Function<TrafficState, Boolean> badProperty, HashSet<Vertex> visited) {
        // Will fill in during lecture
        return false;
    }

    public boolean canLoop() {
        return this.canLoop_helper(new HashSet<>());
    }

    private boolean canLoop_helper(HashSet<TrafficState> visited) {
        // Will fill in during lecture
        return false;
    }
}