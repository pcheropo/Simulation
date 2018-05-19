//package schalter1_e;

import java.util.Random;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

// stellt das Ereignis einer Kundenankunft dar
public class KundenAnkunftEvent extends Event<KundeEntity> {

    private Autovermittlung meinModel1;
    private TerminalEntity T1;
    private TerminalEntity T2;
    int ziel;
    
    public KundenAnkunftEvent(Model owner, String name, boolean showInTrace,int ziel) {
        super(owner, name, showInTrace);
        this.ziel = ziel;

    }

    @Override
    public void eventRoutine(KundeEntity kunde) throws SuspendExecution {
        if(!meinModel1.vermietstationQueue.isEmpty()) {
            Random r = new Random();
            int ziel = r.nextInt(3) + 2;//random zwische option 2 oder 3 dabei handelt es sich um Terminal1 oder 2
            BusEntity bus = new BusEntity(meinModel1,"Kunde steigt in den bus ein",true,ziel,meinModel1.vermietstationQueue.first());
            bus.einsteigen();
            meinModel1.vermietstationQueue.remove(kunde);
            
        }else if(!meinModel1.terminal1Queue.isEmpty()) {
            int ziel = 4;
            BusEntity bus = new BusEntity(meinModel1,"Kunde steigt in den bus ein",true,ziel,meinModel1.terminal1Queue.first());
            bus.einsteigen();
            meinModel1.terminal1Queue.remove(kunde);
        }else if(!meinModel1.terminal2Queue.isEmpty()) {
            int ziel = 4;
            BusEntity bus = new BusEntity(meinModel1,"Kunde steigt in den bus ein",true,ziel,meinModel1.terminal2Queue.first());
            bus.einsteigen();
            meinModel1.terminal2Queue.remove(kunde);
        }
       }
    }


