//package schalter1_e;

import desmoj.core.simulator.*;

// zur Darstellung von Kunden
// -> einfache Version, keine speziellen Attribute notwendig
public class KundeEntity extends Entity {
	
	/*
	 * 0 für Terminal 1
	 * 1 für Terminal 2
	 * 2 für Vermietstation und von Terminal 1 abfliegen
	 * 3 für Vermietstation und von Terminal 2 ablfliegen
	 */
	int ankunftplatz;
	
	

    // Konstruktor
    // Par 1: Modellzugehoerigkeit
    // Par 2: Name der Entitaet
    // Par 3: show inInTrace
    public KundeEntity(Model owner, String name, boolean showInTrace, int ankunftplatz) {
	   super(owner, name, showInTrace);
	   this.ankunftplatz=ankunftplatz;
	   
    }
}
