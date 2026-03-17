package tp2025;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class ClientServer extends LC0_Algorithm {

    private int fatherDoor;
    private String[] neighbors_state;
    private int counter_server;
    private int counter_client;

    private final String SERVER_COUNT_PROPERTY = "count_sever";
    private final String CLIENT_COUNT_PROPERTY = "count_client";


    @Override
    public Object clone() {
        return new ClientServer();
    }

    @Override
    public String getDescription() {
        return "Calcul d'un arbre recouvrant 1.2";
    }


    @Override
    protected void beforeStart() {
        this.fatherDoor = -1;
        this.neighbors_state = new String[vertex.getDegree()];
        this.setLocalProperty("label", vertex.getLabel());
        if (vertex.getId() % 2 == 0) {
            this.counter_server = 1;
            this.counter_client = 0;
        } else {
            this.counter_server = 0;
            this.counter_client = 1;
        }
        this.setLocalProperty(SERVER_COUNT_PROPERTY, counter_server);
        this.setLocalProperty(CLIENT_COUNT_PROPERTY, counter_client);
        Arrays.fill(neighbors_state, "N");
    }


    @Override
    protected void onStarCenter() {
        this.counter_server = (int) getLocalProperty(SERVER_COUNT_PROPERTY);
        this.counter_client = (int) getLocalProperty(CLIENT_COUNT_PROPERTY);
        this.neighbors_state[neighborDoor] = (String) this.getNeighborProperty("label");
        if (this.getLocalProperty("label").equals("N") && this.getNeighborProperty("label").equals("A")) {
            this.setLocalProperty("label", "A");
            this.setNeighborProperty("label", "M");
            this.setDoorState(new MarkedState(true), neighborDoor);
            this.fatherDoor = neighborDoor;
        } else if (fatherDoor == neighborDoor && this.getLocalProperty("label").equals("A") && this.getNeighborProperty("label").equals("M")) {
            boolean canApply = true;
            for (String state : neighbors_state) {
                if (state.equals("N")) {
                    canApply = false;
                    break;
                }
            }
            if (canApply) {
                this.setLocalProperty("label", "F");
                this.setNeighborProperty("label", "A");
                int neighborClientCounter = (int) getNeighborProperty(CLIENT_COUNT_PROPERTY);
                int neighborServerCounter = (int) getNeighborProperty(SERVER_COUNT_PROPERTY);
                this.setNeighborProperty(CLIENT_COUNT_PROPERTY, this.counter_client + neighborClientCounter);
                this.setNeighborProperty(SERVER_COUNT_PROPERTY, this.counter_server + neighborServerCounter);
            }
        }
        this.putProperty("Affichage", this.display(this.counter_client, this.counter_server), SimulationConstants.PropertyStatus.DISPLAYED);
    }


    private String display(int counter_client, int counter_server) {
        return "S(" + counter_server + ")" + " " + "C(" + counter_client + ")";
    }
}
