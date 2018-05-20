
//package schalter1_e;

import java.util.Random;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.simulator.*;

// stellt das Ereignis einer Kundenankunft dar
public class KundenAnkunftEvent extends Event<KundeEntity> {

	private Autovermittlung meinModel;
	// private TerminalEntity T1;
	// private TerminalEntity T2;
	// int ziel;

	public KundenAnkunftEvent(Model owner, String name, boolean showInTrace, int ziel) {
		super(owner, name, showInTrace);
		meinModel = (Autovermittlung) owner;
	}

	@Override
	public void eventRoutine(KundeEntity kunde) throws SuspendExecution {

		// Kunde in Warteschlange
		
		if (kunde.getZiel() ==0) {
		meinModel.terminal1Queue.insert(kunde);
		sendTraceNote("Laenge der Warteschlange: " + meinModel.terminal1Queue.length());
		}
		
		if (kunde.getZiel() ==1) {
		meinModel.terminal2Queue.insert(kunde);
		sendTraceNote("Laenge der Warteschlange: " + meinModel.terminal2Queue.length());
		}
		
		if (kunde.getZiel() == 2 || (kunde.getZiel() ==3)) {
		meinModel.vermietstationQueue.insert(kunde);
		sendTraceNote("Laenge der Warteschlange: " + meinModel.vermietstationQueue.length());
		}


		if ((kunde.getZiel() == 2)) { // NACH T1
			if (!meinModel.busqueueAV.isEmpty()) {
				// Schalter frei, von entsprechender WS holen
				BusEntity bus = meinModel.busqueueAV.first();

				// extra Entfernen von WS notwendig
				meinModel.busqueueAV.remove(bus);

				// Schalter in entsprechende WS um Referenz nicht zu verlieren
				meinModel.busqueueAV.insert(bus);

				// Kunden aus Kundenreihe um am Schalter bedient zu werden
				// -> Referenz auf Kunden bereits vorhanden - kein first() n�tig!
				meinModel.busqueueAV.remove(bus);
			}
		} else if ((kunde.getZiel() == 3)) {// NACH T2
			if (!meinModel.busqueueAV.isEmpty()) {
				// Schalter frei, von entsprechender WS holen
				BusEntity bus = meinModel.busqueueAV.first();

				// extra Entfernen von WS notwendig
				meinModel.busqueueAV.remove(bus);

				// Schalter in entsprechende WS um Referenz nicht zu verlieren
				meinModel.busqueueAV.insert(bus);

				// Kunden aus Kundenreihe um am Schalter bedient zu werden
				// -> Referenz auf Kunden bereits vorhanden - kein first() n�tig!
				meinModel.busqueueAV.remove(bus);
			}
		} else if ((kunde.getZiel() == 0)) {// NACH AV
			if (!meinModel.busqueueT1.isEmpty()) {
				// Schalter frei, von entsprechender WS holen
				BusEntity bus = meinModel.busqueueT1.first();

				// extra Entfernen von WS notwendig
				meinModel.busqueueT1.remove(bus);

				// Schalter in entsprechende WS um Referenz nicht zu verlieren
				meinModel.busqueueT1.insert(bus);

				// Kunden aus Kundenreihe um am Schalter bedient zu werden
				// -> Referenz auf Kunden bereits vorhanden - kein first() n�tig!
				meinModel.busqueueT1.remove(bus);
			}
		} else if ((kunde.getZiel() == 1)) {// NACH AV
			if (!meinModel.busqueueT2.isEmpty()) {
				// Schalter frei, von entsprechender WS holen
				BusEntity bus = meinModel.busqueueT2.first();

				// extra Entfernen von WS notwendig
				meinModel.busqueueT2.remove(bus);

				// Schalter in entsprechende WS um Referenz nicht zu verlieren
				meinModel.busqueueT2.insert(bus);

				// Kunden aus Kundenreihe um am Schalter bedient zu werden
				// -> Referenz auf Kunden bereits vorhanden - kein first() n�tig!
				meinModel.busqueueT2.remove(bus);
			}
		}
	}
}