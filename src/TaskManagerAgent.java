import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.util.leap.List;
import jade.util.leap.ArrayList;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class TaskManagerAgent extends Agent {
    private List taskList;

    @Override
    protected void setup() {
        System.out.println(getLocalName() + ": Agent started.");
        
        // Initialize the task list
        taskList = new ArrayList();
        
        // Add tasks to the list
        taskList.add("Task 1: Buy groceries");
        taskList.add("Task 2: Complete assignment");

        // Add a behavior that simulates managing tasks
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                if (!taskList.isEmpty()) {
                    System.out.println(getLocalName() + ": Processing " + taskList.get(0));
                    taskList.remove(0);
                } else {
                    System.out.println(getLocalName() + ": No tasks left.");
                    block();
                }
            }
        });

        // Clone the agent after setup
        cloneAgent();
    }

    @Override
    protected void takeDown() {
        System.out.println(getLocalName() + ": Terminating...");
    }

    // Method to clone the agent
    public void cloneAgent() {
        try {
            // Get the container where the agent is currently running
            AgentContainer container = getContainerController();
            
            // Create a name for the cloned agent
            String clonedAgentName = getLocalName() + "-Clone";

            // Create the cloned agent using the container
            AgentController clonedAgentController = container.createNewAgent(clonedAgentName, this.getClass().getName(), null);

            // Start the cloned agent
            clonedAgentController.start();
            System.out.println(getLocalName() + ": Cloning agent to " + clonedAgentName);
        } catch (Exception e) {
            System.err.println(getLocalName() + ": Error during cloning.");
            e.printStackTrace();
        }
    }

    // Method to suspend the agent
    public void suspendAgent() {
        try {
            doSuspend();
            System.out.println(getLocalName() + ": Agent suspended.");
        } catch (Exception e) {
            System.err.println(getLocalName() + ": Error during suspension.");
        }
    }

    // Method to restore the agent state (stub method for demonstration)
    public void restoreState() {
        // In a real-world scenario, this is where you would load saved state
        // For example, you could restore the task list from a file or database
        System.out.println(getLocalName() + ": Restoring state...");
        taskList.add("Task 3: Call client");
    }
}
