import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/*
 *Il programma serve memorizzare account con password inserite o generate
 *
 * @author Luca Crivelli
 * @versions 16.01.2025
 */

public class Program {
    public static void main(String[] args) {
        boolean selezionato1 = false;
        boolean selezionato2 = false;
        boolean selezionato3 = false;
        boolean selezionato4 = false;
        boolean selezionato5 = false;
        boolean selezionato0 = true;
        boolean trovato = false;
        //con intellij bisodna aprire la cartella(del progetto) contenete la cartella src e password altrimenti questi percorsi non vanno
        Path pathD = Paths.get("password/discord.txt");
        Path pathG = Paths.get("password/gmail.txt");
        Path pathI = Paths.get("password/infomaniak.txt");
        Path pathMi = Paths.get("password/microsoft.txt");
        Path pathMo = Paths.get("password/moodle.txt");
        Path pathN = Paths.get("password/nvidia.txt");
        Path pathS = Paths.get("password/steam.txt");
        Path pathA = Paths.get("password/altro.txt");
        Path percorso = pathA;
        int option = 0;
        int option2 = 0;
        int option3 = 0;
        Scanner sc = new Scanner(System.in);

        while(selezionato0 == true) {
            while (selezionato1 == false) {
                System.out.print("seleziona: 1 per creare un account, 2 per creare un account con password generata, 3 per cercare un Account: ");
                try {
                    option = sc.nextInt();
                    if (option > 3 || option < 1) {
                        System.out.println("Errore: seleziona una delle tre opzioni");
                        sc.nextLine();
                    } else {
                        selezionato1 = true;
                        sc.nextLine();
                    }
                } catch (Exception e) {
                    System.out.println("Errore: " + e.getMessage());
                    sc.nextLine();
                }
            }
            System.out.println("seleziona tipo di account che vuoi gestire");
            while (selezionato2 == false) {
                System.out.print("seleziona: 1 Discord, 2 gmail, 3 infomaniak, 4 microsoft , 5 moodle, 6 nvidia, 7 steam, 8 altro: ");
                try {
                    option2 = sc.nextInt();
                    if (option2 > 8 || option2 < 1) {
                        System.out.println("Errore: seleziona una delle tre opzioni");
                        sc.nextLine();
                    } else {
                        selezionato2 = true;
                        sc.nextLine();
                    }
                } catch (Exception e) {
                    System.out.println("Errore: " + e.getMessage());
                    sc.nextLine();
                }
            }
            if(option2 == 1){
                percorso = pathD;
            }
            else if(option2 == 2){
                percorso = pathG;
            }
            else if(option2 == 3){
                percorso = pathI;
            }
            else if(option2 == 4){
                percorso = pathMi;
            }
            else if(option2 == 5){
                percorso = pathMo;
            }
            else if(option2 == 6){
                percorso = pathN;
            }
            else if(option2 == 7){
                percorso = pathS;
            }
            else if(option2 == 8){
                percorso = pathA;
            }
            if (option == 1) {
                while(selezionato4 == false) {
                    System.out.print("Inserisci il nome dell'account: ");
                    String nome = sc.nextLine();
                    System.out.print("Inserisci la password del tuo account: ");
                    String password = sc.nextLine();
                    ControlloAccount c1 = new ControlloAccount(nome, percorso);
                    InserirePassword i1 = new InserirePassword(nome, password, percorso);
                    int controllo1 = c1.controlloNome();
                    if (controllo1 == 0) {
                        int controllo2 = c1.controlloPassword(password);
                        if (controllo2 == 1) {
                            i1.inserire();
                            selezionato4 = true;
                        } else {
                            System.out.println("Sei sicuro di voler inserire questa password?");
                            while (selezionato5 == false) {
                                System.out.print("Selezionare 1 per continuare o 2 per reinserire o 3 per uscire: ");
                                try {
                                    option3 = sc.nextInt();
                                    if (option > 3 || option < 1) {
                                        System.out.println("Errore: seleziona una delle tre opzioni");
                                        sc.nextLine();
                                    } else {
                                        selezionato5 = true;
                                        sc.nextLine();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Errore: " + e.getMessage());
                                    sc.nextLine();
                                }
                            }
                            if (option3 == 1) {
                                i1.inserire();
                                selezionato4 = true;
                            }
                            if (option3 == 2) {
                                selezionato5 = false;
                            }
                            if (option3 == 3) {
                                selezionato4 = true;
                            }
                        }
                    }
                    else{
                        selezionato4 = true;
                    }
                }
            }

            else if (option == 2) {
                System.out.print("Inserisci il nome dell'account: ");
                String nome = sc.nextLine();
                ControlloAccount c1 = new ControlloAccount(nome, percorso);
                GeneratorePassword p1 = new GeneratorePassword(nome, percorso);
                int c = c1.controlloNome();
                if(c == 0) {
                    p1.generaPassword();
                }
            }

            else if (option == 3) {
                System.out.print("Inserisci il nome dell'account: ");
                String nome = sc.nextLine();
                try {
                    List<String> list = Files.readAllLines(percorso);
                    for(String line : list){
                        String[] parole = line.split(",");
                        if(parole[0].equals(nome)){
                            System.out.println("La password dell'account "+ parole[0] + " è: " + parole[1]);
                            trovato = true;
                            break;
                        }
                    }
                    if (trovato == false){
                        System.out.println("Errore: accaount non trovato");
                    }
                    else{
                        trovato = false;
                    }
                }catch (IOException e){
                    System.out.println("Errore: " + e.getMessage());
                }
            }

            while (selezionato3 == false) {
                System.out.print("Se vuoi continuare premere 1 se vuoi uscire premere 2: ");
                try {
                    option = sc.nextInt();
                    if (option > 2 || option < 1) {
                        System.out.println("Errore: seleziona una delle tre opzioni");
                        sc.nextLine();
                    } else {
                        selezionato3 = true;
                    }
                } catch (Exception e) {
                    System.out.println("Errore: " + e.getMessage());
                    sc.nextLine();
                }
            }

            if (option == 1) {
                option = 0;
                option2 = 0;
                option3 = 0;
                selezionato1 = false;
                selezionato2 = false;
                selezionato3 = false;
                selezionato4 = false;
                selezionato5 = false;
            }
            else if (option == 2) {
                selezionato0 =false;
                sc.close();
            }
        }
    }
}
