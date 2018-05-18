//package schalter1_e;

import desmoj.core.simulator.*;

// zur Darstellung von Kunden
// -> einfache Version, keine speziellen Attribute notwendig
public class KundeEntity extends Entity {
	
	/*
	 * 0 für Vermietstation
	 * 1 für Terminal 1
	 * 2 für Terminal 2
	 */
	int ankunftplatz;
	
	/*
	 * 0 wenn abfliegen
	 * 1 wenn ankommen
	 */
	int intention;

    // Konstruktor
    // Par 1: Modellzugehoerigkeit
    // Par 2: Name der Entitaet
    // Par 3: show inInTrace
    public KundeEntity(Model owner, String name, boolean showInTrace, int ankunftplatz,int intention) {
	   super(owner, name, showInTrace);
	   this.ankunftplatz=ankunftplatz;
	   this.intention=intention;
    }
}
