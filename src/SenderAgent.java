import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

// Classe SenderAgent qui étend Agent, représentant un agent émetteur
public class SenderAgent extends Agent {
    
    // Méthode setup exécutée au démarrage de l'agent
    protected void setup() {
        System.out.println("Sender agent démarré."); // Affiche un message indiquant le démarrage de l'agent

        // Création d'un message de type INFORM, utilisé pour envoyer une information
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

        // Ajout du destinataire "receiver" au message
        msg.addReceiver(new AID("receiver", AID.ISLOCALNAME));

        // Définition du contenu du message
        msg.setContent("Bonjour depuis l'agent sender");

        // Envoi du message
        send(msg);
        System.out.println("Message envoyé à receiver."); // Confirmation de l'envoi du message
    }
}
