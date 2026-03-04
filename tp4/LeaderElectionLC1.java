package tp4;

import visidia.simulation.process.algorithm.LC1_Algorithm;

import java.util.ArrayList;

public  class LeaderElectionLC1 extends LC1_Algorithm {

    /*
    * Règles d'élection d'un leader utilisant une synchronisation Etoile Ouverte
    *
    *  - Si le centre est à l'etat N et n'a que 1 voisin a 'etat N alors ce centre passe à l'etat F
    *  - Si plutot il n'a aucun voisin N, il passe à l'état E.
    *
    * */

    @Override
    public Object clone() {
        return new LeaderElectionLC1();
    }

    @Override
    public String getDescription() {
        return "Leader Election using opened start synchronization 1.1";
    }

    @Override
    protected void beforeStart() {
        this.setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        ArrayList<Integer> synchronizedNodes = this.getActiveDoors();
        if(this.getLocalProperty("label").equals("N")) {
            int count = 0;
            for(Integer node : synchronizedNodes) {
                if(this.getNeighborProperty(node, "label").equals("N")) {
                    count++;
                }
            }
            if(count ==1){
                this.setLocalProperty("label", "F");
            }
            else if(count ==0){
                this.setLocalProperty("label", "E");
            }
        }
    }


}


