import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class AgentNotification extends Agent {
    protected void setup() {
        System.out.println("Agent Notification démarré : " + getAID().getName());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getContent().equals("Paiement effectué")) {
                        System.out.println("Notification : Reçu message 'Paiement effectué'. Envoi de la confirmation au client.");

                        // Send confirmation to client
                        ACLMessage confirmationMsg = new ACLMessage(ACLMessage.CONFIRM);
                        
                        // Dynamically resolve the AID of the client agent
                        AID clientAgent = new AID("client", AID.ISLOCALNAME);
                        confirmationMsg.addReceiver(clientAgent);
                        
                        confirmationMsg.setContent("Réservation confirmée");
                        send(confirmationMsg);
                        System.out.println("Notification : Confirmation envoyée au client.");
                    } else {
                        System.out.println("Notification : Message reçu mais contenu non reconnu: " + msg.getContent());
                    }
                } else {
                    block();  // Block if no messages received
                }
            }
        });
    }
}
