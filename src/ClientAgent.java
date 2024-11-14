import jade.core.Agent;
import jade.domain.df.DFAgentDescription;
import jade.domain.df.ServiceDescription;
import jade.domain.df.DFService;

public class ClientAgent extends Agent {
    protected void setup() {
        try {
            // Create a DFAgentDescription template
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription serviceTemplate = new ServiceDescription();

            // Set the properties of the service template
            serviceTemplate.setType("client-service");
            template.addServices(serviceTemplate);

            // Search the DF for agents that provide the service
            DFAgentDescription[] result = DFService.search(this, template);

            // Process the results
            if (result.length > 0) {
                System.out.println("Found a matching agent: " + result[0].getName());
            } else {
                System.out.println("No matching agents found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
