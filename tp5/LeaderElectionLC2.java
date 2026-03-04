package tp5;

import visidia.simulation.process.algorithm.LC2_Algorithm;

import java.util.ArrayList;

public class LeaderElectionLC2 extends LC2_Algorithm {


    private final String LABEL_KEY = "label";
    private final String NEIGHBORS_NUMBER_KEY = "neighbors";

    @Override
    public Object clone() {
        return new LeaderElectionLC2();
    }

    @Override
    public String getDescription() {
        return "Leader Election using closed star algorithm 1.1";
    }

    @Override
    protected void beforeStart() {
        this.setLocalProperty(LABEL_KEY, vertex.getLabel());
        this.setLocalProperty(NEIGHBORS_NUMBER_KEY, vertex.getDegree());

    }

    @Override
    protected void onStarCenter() {
        ArrayList<Integer> activesNodes = this.getActiveDoors();
        if(this.getLocalProperty(LABEL_KEY).equals("N")){
            if( (int) this.getLocalProperty(NEIGHBORS_NUMBER_KEY) == 0){
                this.setLocalProperty(LABEL_KEY, "E" );
            }
            else for (Integer node: activesNodes){
                if(this.getNeighborProperty(node, LABEL_KEY).equals("N")){
                    if ( (int) this.getNeighborProperty(node, NEIGHBORS_NUMBER_KEY) == 1){
                        this.setNeighborProperty(node,LABEL_KEY, "F" );
                        int currentNode = (int)this.getLocalProperty(NEIGHBORS_NUMBER_KEY);
                        this.setLocalProperty(NEIGHBORS_NUMBER_KEY, currentNode-1);
                    }
                }
            }
        }
        else if(this.getLocalProperty(LABEL_KEY).equals("F")){
            localTermination();
        }
        else if(this.getLocalProperty(LABEL_KEY).equals("E")) {
            localTermination();
        }
    }
}
