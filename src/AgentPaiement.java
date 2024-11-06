import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class AgentPaiement extends Agent {
    protected void setup() {
        System.out.println("Agent Paiement démarré : " + getAID().getName());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null && msg.getContent().equals("Procéder au paiement")) {
                    System.out.println("Paiement : Traitement du paiement pour la réservation.");

                    // Create a message to notify the notification agent
                    ACLMessage notificationMsg = new ACLMessage(ACLMessage.INFORM);
                    
                    // Dynamically resolve the AID of the notification agent
                    AID notificationAgent = new AID("notification", AID.ISLOCALNAME);
                    notificationMsg.addReceiver(notificationAgent);
                    
                    notificationMsg.setContent("Paiement effectué");
                    send(notificationMsg);
                    System.out.println("Paiement : Notification de paiement envoyée.");
                } else {
                    block();  // Block if no relevant message is received
                }
            }
        });
    }
}
