package secondo_esercizio;

public class Pubblicazione {
    private static int idProgressivo = 0;
    private final int id;
    private String nome;
    private int copieRicevute;
    private int copieVendute;
    private double prezzoCopertina;
    private double aggio;

    public Pubblicazione(String nome, int copieRicevute, double prezzoCopertina, double aggio, int copieVendute) {
        setNome(nome);
        setCopieRicevute(copieRicevute);
        setPrezzoCopertina(prezzoCopertina);
        setAggio(aggio);
        setCopieVendute(copieVendute);
        id = ++idProgressivo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCopieRicevute() {
        return copieRicevute;
    }

    public double getPrezzoCopertina() {
        return prezzoCopertina;
    }

    public double getAggio() {
        return aggio;
    }

    public int getCopieVendute() {
        return copieVendute;
    }

    public int calcolaReso() {
        return copieRicevute - copieVendute;
    }

    public double calcoloGuadagnoNetto() {
        return copieVendute * prezzoCopertina * (aggio / 100);
    }

    public void setNome(String nome) {
        if (nome.length() < 3)
            throw new IllegalArgumentException("Errore: il nome deve essere >= 3");
        this.nome = nome;
    }

    public void setCopieRicevute(int copieRicevute) {
        if (copieRicevute < 0)
            throw new IllegalArgumentException("Errore: le copie ricevute devono essere >= 0");
         if (copieRicevute < this.copieVendute) //controllo di sicurezza
            throw new IllegalArgumentException(
                    "Errore: le copie ricevute non possono essere inferiori alle copie vendute (" + this.copieVendute
                            + ")");
        this.copieRicevute = copieRicevute;
    }

    public void setPrezzoCopertina(double prezzoCopertina) {
        if (prezzoCopertina < 0)
            throw new IllegalArgumentException("Errore: il prezzo copertina deve essere >= 0");
        this.prezzoCopertina = prezzoCopertina;
    }

    public void setAggio(double aggio) {
        if (aggio < 0 || aggio > 100)
            throw new IllegalArgumentException("Errore: l'aggio deve essere compreso tra 0 e 100");
        this.aggio = aggio;
    }

    public void setCopieVendute(int copieVendute) {
        if (copieVendute < 0)
            throw new IllegalArgumentException("Errore: le copie vendute non possono essere < 0");
        if (copieVendute > this.copieRicevute)
            throw new IllegalArgumentException("Errore: le copie vendute non possono essere > delle copie ricevute");
        this.copieVendute = copieVendute;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pubblicazione other = (Pubblicazione) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
