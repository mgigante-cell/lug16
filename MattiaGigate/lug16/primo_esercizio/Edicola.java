package primo_esercizio;

import java.util.ArrayList;

public class Edicola {
    private ArrayList<Pubblicazione> catalogo = new ArrayList<>();

    public void aggiungiPubblicazione(Pubblicazione p) {
        if(p == null)
            throw new IllegalArgumentException("Errore: la Pubblicazione non può essere nulla");
        catalogo.add(p);
    }

    public void stampaReportChiusura() {
        double guadagnoTotale = 0;
        for (Pubblicazione p : catalogo) {
            System.out.println("Pubblicazione: " + p.getNome());
            System.out.println("  - Copie Ricevute: " + p.getCopieRicevute());
            System.out.println("  - Copie Vendute:  " + p.getCopieVendute());
            System.out.println("  - Copie Rese:     " + p.calcolaReso());

            guadagnoTotale += p.calcoloGuadagnoNetto();
        }
        System.out.printf("Guadagno totale: %.2f €\n", guadagnoTotale);
    }
}
