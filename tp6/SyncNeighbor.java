package tp6;

import visidia.simulation.process.algorithm.SensorSyncAlgorithm;
import visidia.simulation.process.messages.StringMessage;

public class SyncNeighbor extends SensorSyncAlgorithm {

    @Override
    public Object clone() {
        return new SyncNeighbor();
    }

    @Override
    public void init() {
        this.move(this.getId());

        for (int i =0; i<= 10; i++){
            if(getArity() != 0){
                this.sendAll(new StringMessage("Hello-WSN"));
                if(isIncomingDoor(0)) {
                    setLocalProperty("label", "A");
                }
            }
            else {
                setLocalProperty("label", "Z");
            }
            move(this.getId());
            try {
                Thread.sleep(600);
            } catch (Exception e) {
              System.out.println(e);
            }
        }
    }
    
}
