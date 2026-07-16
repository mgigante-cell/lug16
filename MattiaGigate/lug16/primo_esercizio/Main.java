package primo_esercizio;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Edicola miaEdicola = new Edicola();
        
        System.out.println("GESTIONALE EDICOLA INIZIATO");
        boolean inserimentoAttivo = true;

        while (inserimentoAttivo) {
            System.out.println("\nInserimento Nuova Pubblicazione");
            
            // CONTROLLO NOME
            String nome = "";
            while (true) {
                System.out.print("Nome della pubblicazione (almeno " + Pubblicazione.LUNGHEZZA_NOME_MIN + " caratteri): ");
                nome = scanner.nextLine().trim();
                if (nome.length() >= Pubblicazione.LUNGHEZZA_NOME_MIN) break;
                System.out.println("[ERRORE] Il nome deve contenere almeno " + Pubblicazione.LUNGHEZZA_NOME_MIN + " caratteri. Riprova.");
            }

            // CONTROLLO COPIE RICEVUTE
            int copieRicevute = 0;
            while (true) {
                try {
                    System.out.print("Quantità copie ricevute: ");
                    copieRicevute = scanner.nextInt();
                    if(scanner.hasNextLine()) scanner.nextLine();
                    if (copieRicevute > 0) break;
                    System.out.println("[ERRORE] Le copie ricevute non possono essere negative o uguali a zero. Riprova.");
                } catch (InputMismatchException e) {
                    System.out.println("[ERRORE] Inserisci un numero intero valido.");
                }
            }

            // CONTROLLO PREZZO DI COPERTINA
            double prezzoCopertina = 0.0;
            while (true) {
                try {
                    System.out.print("Prezzo di copertina (€): ");
                    prezzoCopertina = scanner.nextDouble();
                    if(scanner.hasNextLine()) scanner.nextLine();
                    if (prezzoCopertina >= Pubblicazione.PREZZO_MIN) break; 
                    System.out.println("[ERRORE] Il prezzo non può essere inferiore a " + Pubblicazione.PREZZO_MIN + ". Riprova.");
                } catch (InputMismatchException e) {
                    System.out.println("[ERRORE] Inserisci un numero decimale valido (usa il punto per i decimali, es: 1.50).");
                }
            }

            // CONTROLLO AGGIO
            double aggio = 0.0;
            while (true) {
                try {
                    System.out.print("Aggio (percentuale di guadagno tra " + Pubblicazione.AGGIO_MIN + " e " + Pubblicazione.AGGIO_MAX + "): ");
                    aggio = scanner.nextDouble();
                    if(scanner.hasNextLine()) scanner.nextLine();
                    if (aggio >= Pubblicazione.AGGIO_MIN && aggio <= Pubblicazione.AGGIO_MAX) break;
                    System.out.println("[ERRORE] L'aggio deve essere una percentuale compresa tra " + Pubblicazione.AGGIO_MIN + " e " + Pubblicazione.AGGIO_MAX + ". Riprova.");
                } catch (InputMismatchException e) {
                    System.out.println("[ERRORE] Inserisci un numero decimale valido.");
                }
            }

            // CONTROLLO COPIE VENDUTE 
            int copieVendute = 0;
            while (true) {
                try {
                    System.out.print("Quantità copie vendute: ");
                    copieVendute = scanner.nextInt();
                    if(scanner.hasNextLine()) scanner.nextLine();
                    
                    if (copieVendute < 0)
                        System.out.println("[ERRORE] Le copie vendute non possono essere negative. Riprova.");
                    else if (copieVendute > copieRicevute)
                        System.out.println("[ERRORE] Non puoi aver venduto più copie di quelle ricevute (" + copieRicevute + "). Riprova.");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("[ERRORE] Inserisci un numero intero valido.");
                }
            }

          
            Pubblicazione p = new Pubblicazione(nome, copieRicevute, prezzoCopertina, aggio, copieVendute);
            miaEdicola.aggiungiPubblicazione(p);
            System.out.println("\n-> \"" + p.getNome() + "\" registrata con successo");

            System.out.print("\nVuoi inserire un'altra pubblicazione? (S/N): ");
            String risposta = scanner.nextLine().trim().toUpperCase();
            if (risposta.equals("N")) inserimentoAttivo = false;
        }

        scanner.close();
        miaEdicola.stampaReportChiusura();
    }
}