import java.util.*;
import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;
public class NeuerKundeEvent extends ExternalEvent {

    private Autovermittlung meinModel;
    
    public NeuerKundeEvent(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        meinModel = (Autovermittlung) owner;
        
    }

    @Override
    public void eventRoutine() throws SuspendExecution {
        
        Random r = new Random();
        int ziel = r.nextInt(4);
        
        KundeEntity kunde = new KundeEntity(meinModel,"Kunde",true,ziel);
        
        if(ziel == 0 ) {
            
            KundenAnkunftEventT1 kundenAnkunft = new KundenAnkunftEventT1(meinModel,"Kunden Ankunft",true);
            kundenAnkunft.schedule(kunde,new TimeSpan(0.0));
            
        }else if(ziel == 1) {
            KundenAnkunftEventT2 kundenAnkunft = new KundenAnkunftEventT2(meinModel,"Kunden Ankunft",true);
            kundenAnkunft.schedule(kunde,new TimeSpan(0.0));
        }else if(ziel == 2 || ziel == 3) {
            KundenAnkunftEventAV kundenAnkunft = new KundenAnkunftEventAV(meinModel,"Kunden Ankunft",true);
            kundenAnkunft.schedule(kunde,new TimeSpan(0.0));
        }
        
        NeuerKundeEvent neuerKunde = new NeuerKundeEvent(meinModel,"kundekreation",true);
        neuerKunde.schedule(new TimeSpan(meinModel.getKundenAnkuftsZeit()));
        
        
        
        
    }

}
