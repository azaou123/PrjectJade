import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class AgentClient extends Agent {
    protected void setup() {
        System.out.println("Agent Client démarré : " + getAID().getName());

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("Client : Je veux réserver une chambre.");

                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("reception", AID.ISLOCALNAME));
                msg.setContent("Demande de réservation");
                send(msg);
                System.out.println("Client : Demande de réservation envoyée.");
            }
        });
    }
}
