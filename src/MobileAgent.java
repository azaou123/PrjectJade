import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.ContainerID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MobileAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + ": Ready to move!");

        // Add behaviour to perform actions before and after moving
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                // Simulate some action before moving
                System.out.println(getLocalName() + ": Preparing to move...");

                // Set up the target container where the agent will move
                ContainerID containerID = new ContainerID();
                containerID.setName("localhost:1100");  // Specify the target container here (e.g., "localhost:1100")

                // Perform the move
                doMove(containerID);

                // Simulate an action after the move
                System.out.println(getLocalName() + ": Moved to another container!");

                // End the behaviour
                block();
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println(getLocalName() + ": Terminating...");
    }
}
