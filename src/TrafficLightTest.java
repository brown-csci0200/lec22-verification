package src;

import org.junit.Assert;
import org.junit.Test;

import static src.TrafficLight.*;

public class TrafficLightTest {

    TrafficState sGG = new TrafficState(GREEN, GREEN);
    TrafficState sGR = new TrafficState(GREEN, YELLOW);

    @Test
    public void testExample() {
        Assert.assertEquals(2, 1 + 1);
    }


    @Test
    public void testMakeGraph() {
        TrafficState sRG = new TrafficState(RED, GREEN);
        TrafficState sGY = new TrafficState(GREEN, YELLOW);
        TrafficState sYG = new TrafficState(YELLOW, GREEN);
        TrafficState sRY = new TrafficState(RED, YELLOW);
        TrafficState sYY = new TrafficState(YELLOW, YELLOW);
        TrafficState sGG = new TrafficState(GREEN, GREEN);
        TrafficState sGR = new TrafficState(GREEN, RED);
        TrafficState sYR = new TrafficState(YELLOW, RED);
        TrafficState sRR = new TrafficState(RED, RED);

        TrafficLightSystem ts = new TrafficLightSystem(sRG);
        TrafficState[] states = {sGY, sYG, sRY, sYY,
                sGG, sGR, sYR, sRR};
        for(TrafficState s : states) {
            ts.addVertex(s);
        }

        ts.addEdge("timer", sRG, sRY);
        ts.addEdge("timer", sRY, sGR);
        ts.addEdge("timer", sGR, sYR);
        ts.addEdge("timer", sYR, sRG);
        ts.addEdge("timer", sRG, sGG);

        ts.addEdge("timer", sGY, sYY);
        ts.addEdge("timer", sYY, sRR);
        ts.addEdge("timer", sYG, sYY);
        ts.addEdge("timer", sGG, sYY);
        ts.addEdge("timer", sRR, sGG);

        //Assert.assertFalse(ts.canReach(sGG));
        Assert.assertFalse(ts.canReachFunc((s) -> {
            return (s.hope == GREEN && s.waterman == GREEN);
        }));
    }
}
