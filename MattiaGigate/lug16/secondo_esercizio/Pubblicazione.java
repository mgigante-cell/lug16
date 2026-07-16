package secondo_esercizio;

public class Pubblicazione {
    private static int idProgressivo = 0;
    private final int id;
    private String nome;
    private int copieRicevute;
    private int copieVendute;
    private double prezzoCopertina;
    private double aggio;

    public static final byte LUNGHEZZA_NOME_MIN = 3;
    public static final double PREZZO_MIN = 1.50;
    public static final byte AGGIO_MIN = 5;
    public static final byte AGGIO_MAX = 20;

    public Pubblicazione(String nome, int copieRicevute, double prezzoCopertina, double aggio, int copieVendute) {
        setNome(nome);
        setCopieRicevute(copieRicevute);
        setPrezzoCopertina(prezzoCopertina);
        setAggio(aggio);
        setCopieVendute(copieVendute);
        id = ++idProgressivo;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getCopieRicevute() { return copieRicevute;}
    public double getPrezzoCopertina() { return prezzoCopertina; }
    public double getAggio() { return aggio; }
    public int getCopieVendute() { return copieVendute; }
    
    public int calcolaReso() { return copieRicevute - copieVendute; }
    public double calcoloGuadagnoNetto() { return copieVendute * prezzoCopertina * (aggio / 100); }

    public void setNome(String nome) {
        if (nome.length() < LUNGHEZZA_NOME_MIN)
            throw new IllegalArgumentException("Errore: il nome deve essere >= " + LUNGHEZZA_NOME_MIN);
        this.nome = nome;
    }

    public void setCopieRicevute(int copieRicevute) {
        if (copieRicevute <= 0)
            throw new IllegalArgumentException("Errore: le copie ricevute devono essere > 0");
        this.copieRicevute = copieRicevute;
    }

    public void setPrezzoCopertina(double prezzoCopertina) {
        if (prezzoCopertina < PREZZO_MIN)
            throw new IllegalArgumentException("Errore: il prezzo copertina deve essere >= " + PREZZO_MIN);
        this.prezzoCopertina = prezzoCopertina;
    }

    public void setAggio(double aggio) {
        if (aggio < AGGIO_MIN || aggio > AGGIO_MAX)
            throw new IllegalArgumentException("Errore: l'aggio deve essere compreso tra " + AGGIO_MIN + " e " + AGGIO_MAX);
        this.aggio = aggio;
    }

    public void setCopieVendute(int copieVendute) {
        if (copieVendute < 0)
            throw new IllegalArgumentException("Errore: le copie vendute non possono essere < 0");
        if (copieVendute > getCopieRicevute())
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
