import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;
public class KundenAnkunftEventT1 extends Event<KundeEntity>{
    private Autovermittlung meinModel;
    
    public KundenAnkunftEventT1(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);
        meinModel = (Autovermittlung) owner;
    }


    
    @Override
    public void eventRoutine(KundeEntity kunde) throws SuspendExecution {
        meinModel.terminal1Queue.insert(kunde);
        if(!meinModel.busqueue.isEmpty()) {
            
        }
    }

}
