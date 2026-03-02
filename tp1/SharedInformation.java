package tp1;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import java.util.Arrays;


public  class SharedInformation extends LC0_Algorithm {

    private int[] ngh_id;


    @Override
    public Object clone() {
        return new SharedInformation();
    }

    @Override
    public String getDescription() {
        return "Shared information 1.1";
    }

    @Override
    protected void beforeStart() {
        this.setLocalProperty("id", vertex.getId());
        this.setLocalProperty("label", vertex.getLabel());
        this.ngh_id = new int[vertex.degree()];
        Arrays.fill(this.ngh_id, -1);
    }

    @Override
    protected void onStarCenter() {
        int neighborNumber = vertex.degree();
        int neighborId = (int)getNeighborProperty("id");
        this.ngh_id[neighborDoor] = neighborId;
        int currentId = (int)this.getLocalProperty("id");
        int count = 0;

        for (int i = 0; i < neighborNumber; i++) {
            if(this.ngh_id[i] <= currentId) {
                count++;
            }
        }

        if(count >= neighborNumber){
            this.setLocalProperty("label", "K");
        }
        else if(count <= (neighborNumber*0.25) ){
            this.setLocalProperty("label", "C");
        }
        else if(count > neighborNumber*0.25 && count <= neighborNumber*0.5){
            this.setLocalProperty("label", "M");
        }
        else if(count > (neighborNumber*0.5) && count <= neighborNumber*0.75 ){
            this.setLocalProperty("label", "J");
        }
        else if(count > neighborNumber*0.75 ){
            this.setLocalProperty("label", "A");
        }
        this.putProperty("Affichage", this.displayNeighborList(), SimulationConstants.PropertyStatus.DISPLAYED);
    }


    private String displayNeighborList(){
        StringBuilder sb = new StringBuilder("Voisins: ");
        for (int j : this.ngh_id) {
            sb.append(j);
        }
        return sb.toString();
    }
}
