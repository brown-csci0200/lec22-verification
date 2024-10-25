package src;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Function;

public class TrafficLightSystem {
    TrafficState startState;

    HashSet<TrafficState> allStates;

    public TrafficLightSystem(TrafficState startState) {
        this.startState = startState;

        this.allStates = new HashSet<TrafficState>();
        this.allStates.add(startState);
    }

    public void addVertex(TrafficState state) {
        if (!allStates.contains(state)) {
            this.allStates.add(state);
        }
    }

    public void addEdge(String eventName, TrafficState from, TrafficState to) {
        if (this.allStates.contains(from) && this.allStates.contains(to)) {
            from.addEdge(eventName, to);
        }
        // Could throw exception otherwise
    }

    public boolean canReach(TrafficState dest) {
        TrafficState source = this.startState;
        HashSet<TrafficState> visited = new HashSet<TrafficState>();
        LinkedList<TrafficState> toCheck = new LinkedList<TrafficState>();

        visited.add(source);
        toCheck.add(source);

        while(!toCheck.isEmpty()) {
            // Remove a city from the list and check it
            TrafficState current = toCheck.removeFirst();
            if (current.equals(dest)) {
                return true;
            }
            // Otherwise, check all outgoing edges
            for (TrafficState nexts : current.nextStates.values()) {
                if (!visited.contains(nexts)) {
                    visited.add(nexts);
                    toCheck.addLast(nexts);
                }
            }
        }

        return false;
    }


    public boolean canReachFunc(Function<TrafficState, Boolean> badStateFunc) {
        TrafficState source = this.startState;
        // Keep track of a **list** of nodes we want to check
        HashSet<TrafficState> visited = new HashSet<TrafficState>();
        LinkedList<TrafficState> toCheck = new LinkedList<TrafficState>();

        visited.add(source);
        toCheck.add(source);

        while(!toCheck.isEmpty()) {
            // Remove a city from the list and check it
            TrafficState current = toCheck.removeFirst();
            if (badStateFunc.apply(current)) {
                return true; // BAD!!!!!
            }
            // Otherwise, check all outgoing edges
            for (TrafficState nexts : current.nextStates.values()) {
                if (!visited.contains(nexts)) {
                    visited.add(nexts);
                    toCheck.addLast(nexts);
                }
            }
        }

        return false;
    }
}
