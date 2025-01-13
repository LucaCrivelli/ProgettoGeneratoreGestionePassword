import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class InserirePassword {
    private String nomeAccount;
    private String password;
    private Path percorso;
    public InserirePassword(String nomeAccount, String password, Path percorso ) {
        this.nomeAccount = nomeAccount;
        this.password = password;
        this.percorso = percorso;
    }
    public void inserire() {
        List<String> lines = new ArrayList<>();
        lines.add(nomeAccount+ ","+ password);
        try {
            Files.write(percorso, lines, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Errore scrittura!");
        }
        System.out.println("La password del tuo account è stata inserita con successo");
    }
}
