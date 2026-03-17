package tp6;



import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class LocalTerminationDetector extends LC1_Algorithm{


    @Override
    protected void beforeStart() {
       this.setLocalProperty("label", vertex.getLabel());
       this.setLocalProperty("p", false);
       this.setLocalProperty("a", -1);
    }

    @Override
    public String getDescription() {
        return "Terminaison 5.0";
    }

    @Override
    protected void onStarCenter() {
        if (this.getLocalProperty("label").equals("W")){
            if((int) this.getLocalProperty("a") < 3){
                int min = (int) this.getLocalProperty("a");
                for (Integer node : this.getActiveDoors()){
                    if (min > (int)this.getNeighborProperty(node, "a")) {
                        min = (int)this.getNeighborProperty(node, "a");
                    }
                }
                this.setLocalProperty("a", min+1);
            }
        }
         else if(this.getLocalProperty("label").equals("N")){
            for (Integer node: this.getActiveDoors()){
                if(this.getNeighborProperty(node, "label").equals("A")){
                    this.setLocalProperty("label", "A");
                    this.setDoorState(new MarkedState(true), node);
                    break;
                }
            }
        }
        else if (this.getLocalProperty("label").equals("A")){
            int n_count = 0;
            for (Integer node: this.getActiveDoors()){
                if(this.getNeighborProperty(node, "label").equals("N")){
                    n_count++;
                }
            }
            if(n_count == 0 ){
                this.setLocalProperty("p", true);
                this.setLocalProperty("a", 0);
                this.setLocalProperty("label", "W");
            }
        }
        putProperty("affichage", display(), SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    public Object clone() {
        return new LocalTerminationDetector();
    }


    private String display()
    {
        return "a" +
                "(" +
                this.getLocalProperty("a") +
                ")";
    }
    

    
}
