package tp4;

import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

import java.util.ArrayList;

public class SpanningTreeLC1 extends LC1_Algorithm {

    @Override
    public Object clone() {
        return new SpanningTreeLC1();
    }

    @Override
    public String getDescription() {
        return "Recover Tree using open star algorithm.";
    }

    @Override
    protected void beforeStart() {
        this.setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        ArrayList<Integer> connectedNodes = this.getActiveDoors();
        if(this.getLocalProperty("label").equals("N")){
            for(Integer node : connectedNodes) {
                if(this.getNeighborProperty(node, "label").equals("A")){
                    setDoorState(new MarkedState(true), node);
                    this.setLocalProperty("label", "A");
                    break;
                }
            }
        }
    }
}
