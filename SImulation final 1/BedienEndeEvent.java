import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

public class BedienEndeEvent extends Event<KundeEntity> {

    // nuetzliche Referenz auf entsprechendes Modell
    private Autovermittlung meinModel;

    // Konstruktor
  	// Par 1: Modellzugehoerigkeit
	  // Par 2: Name des Ereignisses
	  // Par 3: show in trace?
    public BedienEndeEvent(Model owner, String name, boolean showInTrace) {
        super(owner, name, showInTrace);

        meinModel = (Autovermittlung) owner;
    }
    
	// Beschreibung der Aktionen, die den Kunden nach Beendigung der
	// Bedienung am Friseur betreffen
    
	@Override
	public void eventRoutine(KundeEntity kunde) throws SuspendExecution {
		
		// wartet ein weiterer Kunde auf Bedienung?
		if(kunde.getZiel()==0) {
		if (!meinModel.terminal1Queue.isEmpty()) {
			// Kunde vorhanden, aus Kundenreihe entfernen
			KundeEntity naechsterKunde = meinModel.terminal1Queue.first();
			meinModel.terminal1Queue.remove(naechsterKunde);
			// Bedienungsende Ereignis erzeugen
			BedienEndeEvent bedienEnde = new BedienEndeEvent(meinModel, "Bedienung Ende", true);
			// eintragen in Ereignisliste
			bedienEnde.schedule(kunde, new TimeSpan(meinModel.getBedienZeit()));
			}
		}
		
		if(kunde.getZiel()==1) {
		if (!meinModel.terminal2Queue.isEmpty()) {
			// Kunde vorhanden, aus Kundenreihe entfernen
			KundeEntity naechsterKunde = meinModel.terminal2Queue.first();
			meinModel.terminal2Queue.remove(naechsterKunde);
			// Bedienungsende Ereignis erzeugen
			BedienEndeEvent bedienEnde = new BedienEndeEvent(meinModel, "Bedienung Ende", true);
			// eintragen in Ereignisliste
			bedienEnde.schedule(kunde, new TimeSpan(meinModel.getBedienZeit()));
			}
		}
		
		if(kunde.getZiel()==2 || kunde.getZiel()==3) {
		if (!meinModel.vermietstationQueue.isEmpty()) {
			// Kunde vorhanden, aus Kundenreihe entfernen
			KundeEntity naechsterKunde = meinModel.vermietstationQueue.first();
			meinModel.vermietstationQueue.remove(naechsterKunde);
			// Bedienungsende Ereignis erzeugen
			BedienEndeEvent bedienEnde = new BedienEndeEvent(meinModel, "Bedienung Ende", true);
			// eintragen in Ereignisliste
			bedienEnde.schedule(kunde, new TimeSpan(meinModel.getBedienZeit()));
			}
		}

		
	}
	
}


//protected Queue<KundeEntity> vermietstationQueue;
//protected Queue<KundeEntity> terminal1Queue;
//protected Queue<KundeEntity> terminal2Queue;
//
//protected Queue<KundeEntity> vermietstationQueue;
//protected Queue<KundeEntity> terminal1Queue;
//protected Queue<KundeEntity> terminal2Queue;