
//package schalter1_e;

import desmoj.core.simulator.*;
import desmoj.core.dist.*;

/*
 main-Klasse vom einfachen Schalter-Modell (abgeleitet von 
 desmoj.core.simulator.Model) - stellt die notwendige Infrastruktur zur Verfuegung
*/

public class Autovermittlung extends Model {
    private static ContDistUniform kundenAnkuftsZeit;
    protected Queue<KundeEntity> vermietstationQueue;
    protected Queue<KundeEntity> terminal1Queue;
    protected Queue<KundeEntity> terminal2Queue;
    
    protected Queue<BusEntity> busqueue;
 
    public Autovermittlung(Model owner, String name, boolean showInReport, 
                            boolean showIntrace) {
        super(owner, name, showInReport, showIntrace);
    }



    /**
    * Initialisierung der benutzten DESMO-J Infrastruktur
     */
   
    public static void main(java.lang.String[] args) {

        // neues Experiment erzeugen
        // ATTENTION!
        // Use as experiment name a OS filename compatible string!!
        // Otherwise your simulation will crash!!
        Experiment schalterExperiment = 
            new Experiment("Schalter1-ereignis");
 
        // neues Modell erzeugen
        // Par 1: null markiert main model, sonst Mastermodell angeben
        Autovermittlung sch_e_model = 
            new Autovermittlung(null, "Schalter Modell", true, true);  

        // Modell mit Experiment verbinden
        sch_e_model.connectToExperiment(schalterExperiment);

        // Intervall fuer trace/debug
        schalterExperiment.tracePeriod(new TimeInstant(0.0), new TimeInstant(60));
        schalterExperiment.debugPeriod(new TimeInstant(0.0), new TimeInstant(60));

        // Ende der Simulation setzen
        // -> hier 4 Stunden (= 240 min)
        schalterExperiment.stop(new TimeInstant(250));

        // Experiment zur Zeit 0.0 starten
        schalterExperiment.start(); 

        // -> Simulation laeuft bis Abbruchkriterium erreicht ist
        // -> danach geht es hier weiter

        // Report generieren
        schalterExperiment.report();

        // Ausgabekanaele schliessen, allfaellige threads beenden
        schalterExperiment.finish();
        
    }



    @Override
    public String description() {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public void doInitialSchedules() {
    }



    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }
    
    
    public double getKundenAnkuftsZeit() {
        return kundenAnkuftsZeit.sample();
    }
}

