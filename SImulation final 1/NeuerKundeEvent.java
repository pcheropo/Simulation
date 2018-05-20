import java.util.*;
import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

public class NeuerKundeEvent extends ExternalEvent {

	private Autovermittlung meinModel;
	private int ziel;

	public NeuerKundeEvent(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		meinModel = (Autovermittlung) owner;

	}
	public NeuerKundeEvent(Model owner, String name, boolean showInTrace, int ziel) {
		super(owner, name, showInTrace);
		this.ziel = ziel;
		meinModel = (Autovermittlung) owner;

	}

	@Override
	public void eventRoutine() throws SuspendExecution {
		Random r = new Random();
		int ziel = r.nextInt(4);

		KundeEntity kunde = new KundeEntity(meinModel, "Kunde", true, ziel);

		// neues KundenAnkunfts-Ereignis erzeugen
		KundenAnkunftEvent kundenAnkunft = new KundenAnkunftEvent(meinModel, "Kundenankunft", true, ziel);

		// dieses aktivieren
		kundenAnkunft.schedule(kunde, new TimeSpan(0.0));

		// neues Ereignis fuer naechsten Kunden erzeugen eintragen
		NeuerKundeEvent neuerKunde = new NeuerKundeEvent(meinModel, "Kundenkreation", true,ziel);
		neuerKunde.schedule(new TimeSpan(meinModel.getKundenAnkunftsZeit()));
	}

}
