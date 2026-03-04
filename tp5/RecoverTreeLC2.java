package tp5;

import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.ArrayList;

public class RecoverTreeLC2 extends LC2_Algorithm {

    @Override
    public Object clone() {
        return new RecoverTreeLC2();
    }

    @Override
    public String getDescription() {
        return "Recover Tree using closed star algorithm.";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("count", 0);
    }

    @Override
    protected void onStarCenter() {
        int count = (int) this.getLocalProperty("count");
        ArrayList<Integer> activesNodes = this.getActiveDoors();
        if(count == activesNodes.size()){
            localTermination();
        }
        else {
            if(this.getLocalProperty("label").equals("N")){
                for (Integer node : activesNodes) {
                    if(this.getNeighborProperty(node, "label").equals("A")){
                        this.setLocalProperty("label", "A");
                        this.setDoorState(new MarkedState(true), node);
                        break;
                    }
                }
            }
            else if (this.getLocalProperty("label").equals("A")){
                for (Integer node : activesNodes) {
                    if(this.getNeighborProperty(node, "label").equals("N")){
                        this.setNeighborProperty(node, "label", "A");
                        this.setDoorState(new MarkedState(true), node);
                    }
                    else if (this.getNeighborProperty(node, "label").equals("A")){
                        setLocalProperty("count", count+1);
                    }
                }
            }
        }
    }
}
