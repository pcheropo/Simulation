//package schalter1_e;
import java.util.Random;

import desmoj.core.simulator.*;

// stellt die Erscheinung eines neuen Kunden im System dar
// -> Beschreibung der Aktionen fuer eine neue Kundenankunft
public class NeuerKundeEvent extends ExternalEvent {

    // nuetzliche Referenz auf entsprechendes Modell
    private Autovermietung meinModel;

    // Konstruktor
	  // Par 1: Modellzugehoerigkeit
	  // Par 2: Name des Ereignisses
	  // Par 3: show in trace?

    public NeuerKundeEvent (Model owner, String name, boolean showInTrace) {
	   super(owner, name, showInTrace);

	   meinModel = (Autovermietung) owner;
    }
    
    // Aktionen, die bei Aktivierung dieses Ereignisses ausgefuehrt werden
    public void eventRoutine() {
	    Random r = new Random();
	    int intention = r.nextInt(2);
	    int ankunftplatz=r.nextInt(3);
        // neuen Kunden erzeugen
        KundeEntity kunde = new KundeEntity (meinModel, "Kunde", true, ankunftplatz,intention);

        // neues KundenAnkunfts-Ereignis erzeugen
        KundenAnkunftEvent kundenAnkunft =
            new KundenAnkunftEvent (meinModel, "Kundenankunft", true);

        // dieses aktivieren
        kundenAnkunft.schedule(kunde, new TimeSpan(0.0));

        // neues Ereignis fuer naechsten Kunden erzeugen eintragen
        NeuerKundeEvent neuerKunde = 
            new NeuerKundeEvent(meinModel, "Kundenkreation", true);
        neuerKunde.schedule (new TimeSpan(meinModel.getKundenAnkunftsZeit()));

    }
}
