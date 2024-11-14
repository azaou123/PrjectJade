import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import java.util.ArrayList;
import java.util.List;

public class BuyerAgent extends Agent {
    private List<AID> sellers = new ArrayList<>();
    private double bestOffer = Double.MAX_VALUE;
    private AID bestSeller;

    protected void setup() {
        System.out.println("BuyerAgent démarré.");

        // Add sellers to the list
        sellers.add(new AID("seller1", AID.ISLOCALNAME));
        sellers.add(new AID("seller2", AID.ISLOCALNAME));

        // Create a CFP message
        ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
        for (AID seller : sellers) {
            cfp.addReceiver(seller);
        }
        cfp.setContent("Demande d'offre pour un produit");
        cfp.setConversationId("contract-negotiation");

        // Send the CFP message to the sellers
        send(cfp);
        System.out.println("Demande envoyée aux vendeurs.");

        // Wait for the responses (PROPOSE messages) from sellers
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage reply = blockingReceive(); // Block until a message is received
                if (reply != null) {
                    System.out.println("Réponse reçue de " + reply.getSender().getLocalName() + ": " + reply.getContent());

                    // After receiving responses, select the best offer (in this case, just the first one)
                    if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        double price = extractPrice(reply.getContent());
                        if (price != -1) {
                            System.out.println("Proposition de " + reply.getSender().getLocalName() + ": " + price + " EUR");

                            // Select the best offer (lowest price)
                            if (price < bestOffer) {
                                bestOffer = price;
                                bestSeller = reply.getSender();
                            }

                            // End the negotiation after all offers are received
                            if (bestSeller != null) {
                                ACLMessage contract = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                                contract.addReceiver(bestSeller);
                                contract.setContent("Contrat accepté pour " + bestOffer + " EUR");
                                send(contract);
                                System.out.println("Contrat envoyé à " + bestSeller.getLocalName() + " : Contrat accepté pour " + bestOffer + " EUR");

                                // End the agent's process
                                doDelete();
                            }
                        } else {
                            System.out.println("Erreur lors de l'extraction du prix.");
                        }
                    }
                }
            }
        });
    }

    private double extractPrice(String content) {
        // Assuming the proposal content format is "Offre de prix : <price> EUR"
        try {
            // Split the message by ":" to get the price part
            String[] parts = content.split(":");
            if (parts.length == 2) {
                String pricePart = parts[1].trim(); // Extract the price part after "Offre de prix"
                // Replace the comma with a dot to handle the decimal correctly (19,70 -> 19.70)
                pricePart = pricePart.replace(',', '.');
                String[] priceParts = pricePart.split(" "); // Split by space to isolate price and currency
                if (priceParts.length == 2 && priceParts[1].equals("EUR")) {
                    return Double.parseDouble(priceParts[0]);
                }
            }
            System.err.println("Format de proposition invalide.");
            return -1; // Return -1 if the format is not as expected
        } catch (Exception e) {
            System.err.println("Erreur lors de l'extraction du prix.");
            return -1;
        }
    }
}
