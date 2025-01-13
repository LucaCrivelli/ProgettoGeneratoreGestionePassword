import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeneratorePassword {
    private StringBuilder password = new StringBuilder();
    private String nomeAccount;
    private Path percorso;
    private String[] alfabetom = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private String[] alfabetoM = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] caratteriSpeciali = {"*","?","$","#","!","%","&"};
    public GeneratorePassword(String nomeAccount, Path percorso) {
        this.nomeAccount = nomeAccount;
        this.percorso = percorso;
    }

    public void generaPassword() {
        Random r = new Random();
        int n = 0;
        boolean lm = false;
        boolean lM = false;
        boolean num = false;
        boolean speciali = false;
        boolean corretta = false;
        while (corretta == false) {
            password = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                n = r.nextInt(1,5);
                if(n == 1){
                    for(int l1 = 0; l1 < 3; l1++){
                        n = r.nextInt(0, 26);
                        password.append(alfabetom[n]);
                    }
                    lm = true;
                }
                else if(n == 2){
                    for(int l2 = 0; l2 < 2; l2++) {
                        n = r.nextInt(0, 26);
                        password.append(alfabetoM[n]);
                    }
                    lM = true;
                }
                else if(n == 3){
                    n = r.nextInt(10,100);
                    password.append(n);
                    num = true;
                }
                else if(n == 4){
                    n = r.nextInt(0,7);
                    password.append(caratteriSpeciali[n]);
                    speciali = true;
                }
            }
            if(lm == true && lM == true && num == true && speciali == true){
                corretta = true;
            }
            else{
                lm = false;
                lM = false;
                num = false;
                speciali=false;
            }
        }
        List<String> lines = new ArrayList<>();
        lines.add(nomeAccount+ ","+ password.toString());
        try {
            Files.write(percorso, lines, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Errore scrittura!");
        }
        System.out.println("La password per il tuo account è: " + password);
    }
}
