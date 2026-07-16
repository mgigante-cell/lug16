package secondo_esercizio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Edicola miaEdicola = new Edicola();
        boolean esegui = true;

        while (esegui) {
            mostraMenu();
            try {
                int sceltaInput = Integer.parseInt(scanner.nextLine());

                switch (sceltaInput) {
                    case 1 -> inserisciNuovaPubblicazione(scanner, miaEdicola);
                    case 2 -> miaEdicola.stampaReportChiusura();
                    case 3 -> modificaPubblicazione(scanner, miaEdicola);
                    case 4 -> rimuoviPubblicazione(scanner, miaEdicola);
                    case 0 -> {
                        System.out.println("\nChiusura del programma. Arrivederci!");
                        esegui = false;
                    }
                    default -> System.out.println("[ERRORE] Opzione non valida. Inserisci un numero da 0 a 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Inserisci un numero valido per selezionare l'opzione.");
            }
        }
        scanner.close();
    }

    private static void mostraMenu() {
        System.out.println(" 1. Inserisci una nuova pubblicazione");
        System.out.println(" 2. Visualizza report di chiusura giornaliera");
        System.out.println(" 3. Modifica una pubblicazione esistente");
        System.out.println(" 4. Rimuovi una pubblicazione");
        System.out.println(" 0. Esci dal programma");
        System.out.print("Scegli un'opzione: ");
    }

    private static void inserisciNuovaPubblicazione(Scanner scanner, Edicola edicola) {
        System.out.println("\nInserimento Nuova Pubblicazione");

        String nome = "";
        while (true) {
            System.out.print("Nome della pubblicazione (almeno 3 caratteri): ");
            nome = scanner.nextLine().trim();
            if (nome.length() >= 3)
                break;
            System.out.println("[ERRORE] Il nome deve contenere almeno 3 caratteri.");
        }

        int copieRicevute = 0;
        while (true) {
            try {
                System.out.print("Quantità copie ricevute: ");
                copieRicevute = Integer.parseInt(scanner.nextLine());
                if (copieRicevute >= 0)
                    break;
                System.out.println("[ERRORE] Le copie ricevute non possono essere negative.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Inserisci un numero intero valido.");
            }
        }

        double prezzoCopertina = 0.0;
        while (true) {
            try {
                System.out.print("Prezzo di copertina (€): ");
                prezzoCopertina = Double.parseDouble(scanner.nextLine());
                if (prezzoCopertina >= 0)
                    break;
                System.out.println("[ERRORE] Il prezzo non può essere negativo.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Inserisci un numero decimale valido.");
            }
        }

        double aggio = 0.0;
        while (true) {
            try {
                System.out.print("Aggio (percentuale di guadagno 0-100): ");
                aggio = Double.parseDouble(scanner.nextLine());
                if (aggio >= 0 && aggio <= 100)
                    break;
                System.out.println("[ERRORE] L'aggio deve essere compreso tra 0 e 100.");
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Inserisci un numero decimale valido.");
            }
        }

        int copieVendute = 0;
        while (true) {
            try {
                System.out.print("Quantità copie vendute: ");
                copieVendute = Integer.parseInt(scanner.nextLine());
                if (copieVendute < 0) {
                    System.out.println("[ERRORE] Le copie vendute non possono essere negative.");
                } else if (copieVendute > copieRicevute) {
                    System.out.println(
                            "[ERRORE] Non puoi aver venduto più copie di quelle ricevute (" + copieRicevute + ").");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Inserisci un numero intero valido.");
            }
        }

        Pubblicazione p = new Pubblicazione(nome, copieRicevute, prezzoCopertina, aggio, copieVendute);
        edicola.aggiungiPubblicazione(p);
        System.out.println("\n-> \"" + p.getNome() + "\" (ID: " + p.getId() + ") registrata con successo!");
    }

    private static void modificaPubblicazione(Scanner scanner, Edicola edicola) {
        System.out.println("\nModifica Pubblicazione");
        System.out.print("Inserisci il nome della pubblicazione da modificare: ");
        String nomeCerca = scanner.nextLine().trim();

        Pubblicazione p = edicola.cercaPubblicazione(nomeCerca);

        if (p == null) {
            System.out.println("[ERRORE] Pubblicazione \"" + nomeCerca + "\" non trovata in archivio.");
            return;
        }

        System.out.println("\nPubblicazione trovata: " + p.getNome() + " (ID: " + p.getId() + ")");
        System.out.println("Cosa desideri modificare?");
        System.out.println("1. Nome");
        System.out.println("2. Copie Ricevute (Attuali: " + p.getCopieRicevute() + ")");
        System.out.println("3. Prezzo Copertina (Attuale: " + p.getPrezzoCopertina() + " €)");
        System.out.println("4. Aggio (Attuale: " + p.getAggio() + "%)");
        System.out.println("5. Copie Vendute (Attuali: " + p.getCopieVendute() + ")");
        System.out.print("Scegli un'opzione (1-5): ");
        int sceltaModifica = Integer.parseInt(scanner.nextLine());

        try {
            switch (sceltaModifica) {
                case 1 -> {
                    System.out.print("Inserisci il nuovo nome: ");
                    String nuovoNome = scanner.nextLine().trim();
                    p.setNome(nuovoNome);
                    System.out.println("Nome modificato con successo!");
                }
                case 2 -> {
                    System.out.print("Inserisci nuove copie ricevute: ");
                    int nuoveRicevute = Integer.parseInt(scanner.nextLine());
                    p.setCopieRicevute(nuoveRicevute);
                    System.out.println("Copie ricevute aggiornate con successo!");
                }
                case 3 -> {
                    System.out.print("Inserisci nuovo prezzo: ");
                    double nuovoPrezzo = Double.parseDouble(scanner.nextLine());
                    p.setPrezzoCopertina(nuovoPrezzo);
                    System.out.println("Prezzo aggiornato con successo!");
                }
                case 4 -> {
                    System.out.print("Inserisci nuovo aggio: ");
                    double nuovoAggio = Double.parseDouble(scanner.nextLine());
                    p.setAggio(nuovoAggio);
                    System.out.println("Aggio aggiornato con successo!");
                }
                case 5 -> {
                    System.out.print("Inserisci nuove copie vendute: ");
                    int nuoveVendute = Integer.parseInt(scanner.nextLine());
                    p.setCopieVendute(nuoveVendute);
                    System.out.println("Copie vendute aggiornate con successo!");
                }
                default -> System.out.println("[ERRORE] Opzione di modifica non valida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRORE] Input non valido: devi inserire un numero coerente.");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRORE DI VALIDAZIONE] Impossibile applicare la modifica: " + e.getMessage());
        }
    }

    private static void rimuoviPubblicazione(Scanner scanner, Edicola edicola) {
        System.out.println("\nRimozione Pubblicazione");
        System.out.print("Inserisci il nome della pubblicazione da rimuovere: ");
        String nomeRimuovi = scanner.nextLine().trim();

        boolean rimosso = edicola.rimuoviPubblicazione(nomeRimuovi);
        if (rimosso)
            System.out.println("-> Pubblicazione \"" + nomeRimuovi + "\" rimossa con successo!");
        else
            System.out.println("[ERRORE] Impossibile rimuovere: pubblicazione non trovata.");
    }
}