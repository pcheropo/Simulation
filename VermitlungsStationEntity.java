import desmoj.core.simulator.*;

public class VermitlungsStationEntity extends Entity{
    int ziel;
    
    public VermitlungsStationEntity(Model owner, String name, boolean showInTrace,int ziel) {
        super(owner, name, showInTrace);
        this.ziel=ziel;
 }
       
    public int getZiel() {
        return ziel;
    }
   
}
