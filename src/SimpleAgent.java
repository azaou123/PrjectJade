import jade.core.Agent;

// Classe SimpleAgent qui étend Agent, représentant un agent simple
public class SimpleAgent extends Agent {

    // Méthode setup exécutée au démarrage de l'agent
    @Override
    protected void setup() {
        // Affiche un message de bienvenue avec le nom local de l'agent
        System.out.println("Bonjour, je suis " + getLocalName());
    }
    
    @Override
    protected void takeDown() {
        System.out.println("Agent " + getLocalName() + " arrêté.");
    }
}
