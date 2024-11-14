import jade.core.Agent;
import jade.core.behaviours.*;

// Classe BehaviorAgent qui étend Agent, représentant un agent avec plusieurs types de comportements
public class BehaviorAgent extends Agent {
    private int counter = 0;  // Compteur pour suivre les itérations
    
    // Méthode setup exécutée au démarrage de l'agent
    protected void setup() {

        // Ajout d'un OneShotBehaviour, exécuté une seule fois
        addBehaviour(new OneShotBehaviour() {
            public void action() {
                System.out.println("OneShotBehaviour exécuté une seule fois");
            }
        });

        // Ajout d'un CyclicBehaviour, exécuté en boucle
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                // Affichage de l'itération actuelle
                System.out.println("CyclicBehaviour exécuté en boucle, itération: " + counter);
                
                if (counter < 5) {
                    counter++;  // Incrémentation du compteur
                } else {
                    // Arrêt du comportement après 5 itérations
                    System.out.println("CyclicBehaviour terminé après " + counter + " itérations.");
                    myAgent.removeBehaviour(this);  // Suppression du comportement
                    return;  // Sortie de la boucle une fois que le comportement est supprimé
                }
            }
        });

        // Ajout d'un TickerBehaviour, exécuté toutes les secondes
        // addBehaviour(new TickerBehaviour(this, 1000) {
        //     protected void onTick() {
        //         System.out.println("TickerBehaviour toutes les secondes");
        //     }
        // });
    }
}
