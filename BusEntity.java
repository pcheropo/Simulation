
//package schalter1_e;

import desmoj.core.simulator.*;

//zur Darstellung eines Schalters
//-> einfache Version, keine speziellen Attribute notwendig
public class BusEntity extends Entity {
	final int capacity;

	// Konstruktor
	// Par 1: Modellzugehoerigkeit
	// Par 2: Name der Entitaet
	// Par 3: show inInTrace
	public BusEntity(Model owner, String name, boolean showInTrace) {
		super(owner, name, showInTrace);
		this.capacity=20;
		
	}
}
