import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.List;

public class ControlloAccount {
    private String nomeAccount;
    private Path percorso;
    private String[] alfabetom = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private String[] alfabetoM = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] numeri = {"0","1","2","3","4","5","6","7","8","9"};
    private String[] caratteriSpeciali = {"*","?","$","#","!","%","&","+","-",":",";","@","^","_"};
    private String spazio = " ";

    public ControlloAccount(String nomeAccount, Path percorso) {
        this.nomeAccount = nomeAccount;
        this.percorso = percorso;
    }

    public int controlloNome(){
        int c = 0;
        boolean cSpazi = false;
        for(int i = 0; i < nomeAccount.length(); i ++){
            if(nomeAccount.charAt(i) == spazio.charAt(0)){
                System.out.println("Attenzione: il nome non può avere spazi");
                c = 1;
                cSpazi = true;
            }
        }
        if(cSpazi == false) {
            try {
                List<String> list = Files.readAllLines(percorso);
                if (!list.isEmpty()) {
                    for (String line : list) {
                        String[] parole = line.split(",");
                        if (parole[0].equals(nomeAccount)) {
                            System.out.println("Account " + parole[0] + " è già esistente ");
                            c = 1;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        return c;
    }
    public int controlloPassword(String password){
        int c = 0;
        boolean lm = false;
        boolean lM = false;
        boolean num = false;
        boolean speciali = false;
        boolean lunghezza = false;
        for(int i = 0; i < password.length(); i++){
            for(int j = 0; j < alfabetom.length; j++){
                if(password.charAt(i) == alfabetom[j].charAt(0)){
                    lm = true;
                    break;
                }
            }
            for(int j = 0; j < alfabetoM.length; j++){
                if(password.charAt(i) == alfabetoM[j].charAt(0)){
                    lM = true;
                    break;
                }
            }
            for(int j = 0; j < numeri.length; j++){
                if(password.charAt(i) == numeri[j].charAt(0)){
                    num = true;
                    break;
                }
            }
            for(int j = 0; j < caratteriSpeciali.length; j++){
                if(password.charAt(i) == caratteriSpeciali[j].charAt(0)){
                    speciali = true;
                    break;
                }
            }
        }
        if(password.length()>=8){
            lunghezza = true;
        }
        if(lm == true && lM == true && num == true && speciali == true && lunghezza== true){
            c = 1;
            System.out.println("Password sicura");
        }
        else{
            System.out.println("Password non sicura");
        }
        return c;
    }
}
