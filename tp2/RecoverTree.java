package tp2;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.Arrays;

public class RecoverTree extends LC0_Algorithm {

    private int fatherDoor;
    private String[] neighbors_state;
    private int counter;





    @Override
    public Object clone() {
        return new RecoverTree();
    }

    @Override
    public String getDescription() {
        return "Calcul d'un arbre recouvrant 1.2";
    }


    @Override
    protected void beforeStart() {
        this.counter = 1;
        this.fatherDoor = -1;
        this.setLocalProperty("label", vertex.getLabel());
        this.setLocalProperty("count", counter);
        this.neighbors_state = new String[vertex.getDegree()];
        Arrays.fill(neighbors_state, "N");
    }


    @Override
    protected void onStarCenter() {
        this.counter = (int) getLocalProperty("count");
        this.neighbors_state[neighborDoor] = (String) this.getNeighborProperty("label");
        if(this.getLocalProperty("label").equals("N") && this.getNeighborProperty("label").equals("A")){
            this.setLocalProperty("label", "A");
            this.setNeighborProperty("label", "M");
            this.setDoorState(new MarkedState(true), neighborDoor);
            this.fatherDoor = neighborDoor;
        }

        else if(fatherDoor == neighborDoor && this.getLocalProperty("label").equals("A") && this.getNeighborProperty("label").equals("M")){
            boolean canApply = true;
            for(String state: neighbors_state){
                if(state.equals("N")){
                    canApply = false;
                    break;
                }
            }
            if(canApply){
                this.setLocalProperty("label", "F");
                this.setNeighborProperty("label", "A");
                int currentCounter = (int)getLocalProperty("count");
                int neighborCounter = (int)getNeighborProperty("count");
                this.setNeighborProperty("count", currentCounter + neighborCounter);
            }
        }



        this.putProperty("Affichage", this.displayNeighborStateList(this.neighbors_state, this.counter), SimulationConstants.PropertyStatus.DISPLAYED);
    }


    private String displayNeighborStateList(String[] list, int counter){
        StringBuilder sb = new StringBuilder("Voisins: ");
        for (String j : list) {
            sb.append(j);
        }
        sb.append(" ").append("Order: ").append(counter);
        return sb.toString();
    }
}
