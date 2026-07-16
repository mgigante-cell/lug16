package secondo_esercizio;

import java.util.ArrayList;

public class Edicola {
    private ArrayList<Pubblicazione> catalogo = new ArrayList<>();

    public void aggiungiPubblicazione(Pubblicazione p) {
        if (p == null)
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

    public Pubblicazione cercaPubblicazione(String nome) {
        if (nome == null)
            return null;
        return catalogo.stream()
                .filter(pb -> pb.getNome().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }

    public boolean rimuoviPubblicazione(String nome) {
        Pubblicazione p = cercaPubblicazione(nome);
        if (p == null)
            return false;
        return catalogo.remove(p);
    }
}
