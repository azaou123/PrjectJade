import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import java.util.Random;

public class SellerAgent extends Agent {
    protected void setup() {
        System.out.println("SellerAgent démarré.");

        // Add a cyclic behaviour to listen for incoming CFP (Call for Proposal) messages
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.CFP) {
                        System.out.println("Message reçu de " + msg.getSender().getLocalName() + ": " + msg.getContent());

                        // Generate a random price between 1 and 100 EUR
                        Random rand = new Random();
                        double price = 1 + (99 * rand.nextDouble()); // Generate random price between 1 and 100 EUR

                        // Respond with a proposal (price offer)
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.PROPOSE);
                        reply.setContent("Offre de prix : " + String.format("%.2f", price) + " EUR"); // Ensure format consistency
                        send(reply);
                        System.out.println("Proposition envoyée : Offre de prix " + String.format("%.2f", price) + " EUR");
                    }
                } else {
                    block(); // Block until a message is received
                }
            }
        });
    }
}
