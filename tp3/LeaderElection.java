package tp3;

import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;

import java.util.Arrays;



public class LeaderElection extends LC0_Algorithm {


    /*
    * Règles de réecriture pour l'élection d'un leader :
    * R1: N(1) --- N(x)  ----> F(0) --- N(x-1)   avec x>1
    * R2: N(1) --- N(1)  ----> E(0) --- F(0)
    *
    * */

    private final String NEIGHBORS_KEY = "neighbors";
    private final String LABEL_KEY = "label";
    private String[] neighborsList;


    @Override
    public Object clone() {
        return new LeaderElection();
    }

    @Override
    public String getDescription() {
        return "Election d'un Leader";
    }

    @Override
    protected void beforeStart() {
        this.setLocalProperty(LABEL_KEY, vertex.getLabel());
        this.setLocalProperty(NEIGHBORS_KEY, vertex.getDegree());
        this.neighborsList = new String[vertex.getDegree()];
        Arrays.fill(this.neighborsList, "N");
    }


    @Override
    protected void onStarCenter() {
        int neighborDegree = (int) this.getNeighborProperty(NEIGHBORS_KEY);
        int currentDegree = (int) this.getLocalProperty(NEIGHBORS_KEY);
        if(currentDegree == 1 && neighborDegree >1) {
            this.setLocalProperty(LABEL_KEY, "F");
            this.setNeighborProperty(NEIGHBORS_KEY, neighborDegree -1);
            this.setLocalProperty(NEIGHBORS_KEY, 0);
        }

        else if (currentDegree == 1 && neighborDegree == 1) {
            this.setLocalProperty(LABEL_KEY, "E");
            this.setNeighborProperty(LABEL_KEY, "F");
            this.setLocalProperty(NEIGHBORS_KEY, 0);
            this.setNeighborProperty(NEIGHBORS_KEY, 0);
        }
        this.neighborsList[neighborDoor] =  this.getNeighborProperty("label").toString();

        putProperty("affichage", this.displayNeighborStateList(), SimulationConstants.PropertyStatus.DISPLAYED);
    }


    private String displayNeighborStateList(){
        StringBuilder sb = new StringBuilder();
        sb.append(vertex.getLabel());
        sb.append("(");
        int count = 0;
        for (String j : neighborsList) {
            if(j.equals("N")){
                count++;
            }
        }
        sb.append(count).append(")");
        return sb.toString();
    }
}
