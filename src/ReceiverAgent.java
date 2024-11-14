import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;

// Classe ReceiverAgent qui étend Agent, représentant un agent récepteur
public class ReceiverAgent extends Agent {
    
    // Méthode setup exécutée au démarrage de l'agent
    protected void setup() {
        System.out.println("Receiver agent démarré."); // Affiche un message indiquant le démarrage de l'agent

        // Ajout d'un comportement cyclique qui vérifie constamment la réception de messages
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                // Vérifie la réception d'un message
                ACLMessage msg = receive();

                // Si un message est reçu, affiche son contenu
                if (msg != null) {
                    System.out.println("Message reçu : " + msg.getContent());
                } else {
                    // Si aucun message n'est reçu, bloque le comportement jusqu'à l'arrivée d'un nouveau message
                    block();
                }
            }
        });
    }
}
