import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class AgentReception extends Agent {
    protected void setup() {
        System.out.println("Agent Réception démarré : " + getAID().getName());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("Réception : Reçu message de " + msg.getSender().getLocalName());
                    if (msg.getContent().equals("Demande de réservation")) {
                        System.out.println("Réception : Chambre disponible.");

                        ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
                        reply.addReceiver(new AID("paiement", AID.ISLOCALNAME));
                        reply.setContent("Procéder au paiement");
                        send(reply);
                        System.out.println("Réception : Demande de paiement envoyée.");
                    }
                } else {
                    block();
                }
            }
        });
    }
}
