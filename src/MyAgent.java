import jade.core.Agent;

public class MyAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " démarré.");
    }

    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " arrêté.");
    }
}
