import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

public class KundenAnkunftEventAV extends Event<KundeEntity>{//AV -> autovermittlung
    
    private Autovermittlung meinModel;

    public KundenAnkunftEventAV(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        meinModel = (Autovermittlung) owner;
    }

    @Override
    public void eventRoutine(KundeEntity kunde) throws SuspendExecution {
        meinModel.vermietstationQueue.insert(kunde);
        
       if(!meinModel.busqueue.isEmpty()) {
           BusEntity bus = meinModel.busqueue.first();
           
      
        }
    }
 
}
