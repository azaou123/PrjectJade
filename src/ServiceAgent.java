import jade.core.Agent;
import jade.domain.df.DFAgentDescription;
import jade.domain.df.ServiceDescription;
import jade.domain.df.DFService;

public class ServiceAgent extends Agent {
    protected void setup() {
        try {
            // Create a DFAgentDescription
            DFAgentDescription dfd = new DFAgentDescription();
            ServiceDescription service = new ServiceDescription();

            // Set the properties of the service
            service.setType("client-service");
            service.setName(getName());
            dfd.addServices(service);

            // Register the service with the DF
            DFService.register(this, dfd);

            System.out.println("Service registered successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
