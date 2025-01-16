import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class InserirePassword {
    private String nomeAccount;
    private String password;
    private Path percorso;
    public InserirePassword(String nomeAccount, String password, Path percorso) {
        this.nomeAccount = nomeAccount;
        this.password = password;
        this.percorso = percorso;
    }
    public void inserire(int tipo) {
        int quantita = 0;
        int n = 0;
        boolean vuota = false;
        if(tipo == 8) {
            try {
                List<String> list = Files.readAllLines(percorso);
                if(list.isEmpty()){
                    vuota = true;
                }
                else {
                    for (String line : list) {
                        String[] parole = line.split(",");
                        if (parole[0].equals(nomeAccount)) {
                            quantita += 1;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Errore: " + e.getMessage());
            }
            if(vuota == false) {
                Random r = new Random();
                n = r.nextInt(0, 100);
                String codiceId = String.valueOf(quantita) + String.valueOf(n);
                List<String> lines = new ArrayList<>();
                lines.add(nomeAccount + "," + password + "," + codiceId);
                try {
                    Files.write(percorso, lines, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    System.out.println("Errore scrittura!");
                }
                System.out.println("La password del tuo account è stata inserita con successo");
                System.out.println("Attenzione: per ritrovare il tuo account usare il codice: " + codiceId);
            }
        }
        if(vuota == true || tipo != 8){
            List<String> lines = new ArrayList<>();
            lines.add(nomeAccount + "," + password);
            try {
                Files.write(percorso, lines, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("Errore scrittura!");
            }
            System.out.println("La password del tuo account è stata inserita con successo");
        }
    }
}
