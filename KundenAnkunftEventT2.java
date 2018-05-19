import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

public class KundenAnkunftEventT2 extends Event<KundeEntity>{
    
    private Autovermittlung meinModel;
    
    public KundenAnkunftEventT2(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        meinModel = (Autovermittlung) owner;
    }


    
    @Override
    public void eventRoutine(KundeEntity kunde) throws SuspendExecution {
        meinModel.terminal2Queue.insert(kunde);
        
       if(!meinModel.busqueue.isEmpty()) {
            
        }
    }
}
