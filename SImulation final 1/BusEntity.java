import desmoj.core.simulator.*;

public class BusEntity extends Entity {
	int capacity;
	int freiePlaetze = 20;// count
	int ziel;
	KundeEntity kunde;

	public BusEntity(Model owner, String name, boolean showInTrace, int ziel, KundeEntity kunde) {
		super(owner, name, showInTrace);
		this.capacity = freiePlaetze;
		this.ziel = ziel;
		this.kunde = kunde;
	}

	public void setFreiePlaetze(int freiePlaetze) {
		this.freiePlaetze = freiePlaetze;
	}

	public int dec() {// einsteigen
		return freiePlaetze--;
	}

	public int inc() {// austeigen
		return freiePlaetze++;
	}

	public int getBesetzePlaetze() {
		if (freiePlaetze < capacity) {
			return capacity - freiePlaetze;
		} else {
			return 0;
		}
	}

	public void einsteigen() {
		if (kunde != null) {// ist einstieg moeglich??
			if (getBesetzePlaetze() < capacity) {
				freiePlaetze = dec();
			}
		}
	}
}
