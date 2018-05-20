
//package schalter1_e;

import desmoj.core.simulator.*;
import desmoj.core.dist.*;

/*
 main-Klasse vom einfachen Schalter-Modell (abgeleitet von 
 desmoj.core.simulator.Model) - stellt die notwendige Infrastruktur zur Verfuegung
*/

public class Autovermittlung extends Model {

	/**
	 * Zufallszahlengenerator fuer 
	 * Kundenankuenfte,bedienZeit
	 */
	private static ContDistUniform kundenAnkunftsZeit;
	private static ContDistNormal bedienZeit;
	
	/**
	 * Warteschlangen fuer freie Bus
	 * -> elementare Statistik erhueltlich
	*/
    protected Queue<KundeEntity> vermietstationQueue;
    protected Queue<KundeEntity> terminal1Queue;
    protected Queue<KundeEntity> terminal2Queue;
    
    
	/**
     * Warteschlange fuer wartende Kunden
     * jeder Kunde kommt zuerst hier hinein
     */
    protected Queue<BusEntity> busqueueAV;
    protected Queue<BusEntity> busqueueT1;
    protected Queue<BusEntity> busqueueT2;

 
    public Autovermittlung(Model owner, String name, boolean showInReport, 
                            boolean showIntrace) {
        super(owner, name, showInReport, showIntrace);
    }
    
	/**
	 * liefert eine Zufallszahl fuer Kundenankunftszeit
	 */
	public double getKundenAnkunftsZeit() {
		return kundenAnkunftsZeit.sample();
	}
	
	/**
	 * 
	 * @returns bedienZeit
	 */
	public double getBedienZeit() {
		return bedienZeit.sample();
	}
	
	
	
	/**
	 * Kurzbeschreibung des Modells
	 */
	public String description() {
		return "AUtovermitlung (Ereignis orientiert):" +
				"simuliert einen Busfahrt, wo ankommende Kunden zuerst in einer "
				+ "Warteschlange eingereiht werden. Wenn im Bus frei platz gibt, "
				+ "werden sie nach Wunsch bedient.";
	}
	
	
	/**
	 * ersten Kunden erzeugen und in Ereignisliste eintragen -> erste
	 * Kundenankunft
	 */
	public void doInitialSchedules() {
		KundeEntity kunde = new KundeEntity(this, "Test", true, 0);
		
		NeuerKundeEvent ersterKunde = new NeuerKundeEvent(this, "Kundenkreation", true);
		ersterKunde.schedule(new TimeSpan(getKundenAnkunftsZeit()));
	}
	
	/**
	 * Initialisierung der benutzten DESMO-J Infrastruktur
	 */
	public void init() {
		// Generator fuer Ankunftszeiten initialisieren
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name des Generators
		// Par 3: minimale Bedienzeit in Minuten
		// Par 4: maximale Bedienzeit in Minuten
		// Par 5: show in report?
		// Par 6: show in trace?
		kundenAnkunftsZeit = new ContDistUniform(this, "Ankunftszeitintervall", 5.0, 15.0, true, true);

		// negative Ankunftszeitintervalle sind nicht moeglich,
		kundenAnkunftsZeit.setNonNegative(true);

		// Generator fuer Bedienzeiten initialisieren
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name des Generators
		// Par 3: mittlere Zeitdauer in Minuten zwischen Bedienszeiten
		// Par 4: Standardabweichung
		// Par 5: show in report?
		// Par 6: show in trace?
		bedienZeit = new ContDistNormal(this, "Bedienzeiten", 10.0, 5.0, true, true);

		// negative Bedienszeitintervalle sind nicht moeglich,
		bedienZeit.setNonNegative(true);

		// Warteschlange fuer Kunden initialisieren
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name der Warteschlange
		// Par 3: show in report?
		// Par 4: show in trace?
		vermietstationQueue = new Queue<KundeEntity>(this, "Kunden-Warteschlange in Vermittlung", true, true);
		terminal1Queue = new Queue<KundeEntity>(this, "Kunden-Warteschlange in Terminal 1", true, true);
		terminal2Queue = new Queue<KundeEntity>(this, "Kunden-Warteschlange in Terminal 2", true, true);

		// Unser Frisuer Queues
		// Warteschlange fuer freie Friseur initialisieren
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name der Warteschlange
		// Par 3: show in report?
		// Par 4: show in trace?
		busqueueAV = new Queue<BusEntity>(this, "AV", true, true);
		busqueueT1 = new Queue<BusEntity>(this, "T1", true, true);
		busqueueT2 = new Queue<BusEntity>(this, "T2", true, true);

		// den Friseur in freieFriseurQueue einfuegen
		// Hinweis: dies geschieht nicht in doInitialSchedules(), da keine
		// Ereignisse erzeugt werden
		BusEntity bus;
		KundeEntity kunde = new KundeEntity(this, "Test", true, 0);
		
		// Bus erzeugen
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name der Entitaet
		// Par 3: show in trace?
		// Par 4: Preference
		// Par 5: Bedientanzahl wird mit 0-3 Initialisiert
		
		if (kunde.getZiel()==0) {
		bus = new BusEntity(this, "AV", true, 0, kunde);
		busqueueT1.insert(bus);
		}
		
		if (kunde.getZiel()==1) {
		bus = new BusEntity(this, "AV", true, 0, kunde);
		busqueueT2.insert(bus);
		}
		
		if (kunde.getZiel()==2 || kunde.getZiel()==3) {
		bus = new BusEntity(this, "AV", true, 0, kunde);
		busqueueAV.insert(bus);
		}
		


		// Warteschlange fuer besetzte Friseur initialisieren
		// Par 1: Modellzugehoerigkeit
		// Par 2: Name der Warteschlange
		// Par 3: show in report?
		// Par 4: show in trace?
//		besetzterFriseurQueueA = new Queue<FriseurEntity>(this, "besetzte Friseur WS A", true, true);
//		besetzterFriseurQueueB = new Queue<FriseurEntity>(this, "besetzte Friseur WS B", true, true);
//		besetzterFriseurQueueC = new Queue<FriseurEntity>(this, "besetzte Friseur WS c", true, true);
	}
   
    public static void main(java.lang.String[] args) {

        // neues Experiment erzeugen
        // ATTENTION!
        // Use as experiment name a OS filename compatible string!!
        // Otherwise your simulation will crash!!
        
    	Experiment schalterExperiment = 
            new Experiment("Bus-ereignis");
 
        // neues Modell erzeugen
        // Par 1: null markiert main model, sonst Mastermodell angeben
        Autovermittlung sch_e_model = 
            new Autovermittlung(null, "Bus-Auto Modell", true, true);  

        // Modell mit Experiment verbinden
        sch_e_model.connectToExperiment(schalterExperiment);

        // Intervall fuer trace/debug
        schalterExperiment.tracePeriod(new TimeInstant(0.0), new TimeInstant(60));
        schalterExperiment.debugPeriod(new TimeInstant(0.0), new TimeInstant(60));

        // Ende der Simulation setzen
        // -> hier 4 Stunden (= 240 min)
        schalterExperiment.stop(new TimeInstant(10000));

        // Experiment zur Zeit 0.0 starten
        schalterExperiment.start(); 

        // -> Simulation laeuft bis Abbruchkriterium erreicht ist
        // -> danach geht es hier weiter

        // Report generieren
        schalterExperiment.report();

        // Ausgabekanaele schliessen, allfaellige threads beenden
        schalterExperiment.finish();
        
    }

    
}