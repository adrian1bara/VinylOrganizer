package org.example;

import org.example.model.User;
import org.example.model.Vinil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import static org.example.UserLogic.login;
import static org.example.UserLogic.salvareUser;
import static org.example.VinilLogic.recomandarePret;
import static org.example.VinilLogic.salvarePretRecomandat;
import static org.example.VinilLogic.afisareListaViniluri;
import static org.example.VinilLogic.salvareVinil;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("APLICATIA A PORNIT! "
                + "\n" + "Alegeti operatiunea dorita: "
                + "\n" + "    1. Autentificare."+ "\n"
                + "    2. Creeaza-ti user si parola.");
        User user = new User();
        int optiune = scanner.nextInt();
        if(optiune == 1){
            user = login();
        } else if(optiune == 2){
            user = salvareUser();
        }

        System.out.println("Va rugam alegeti operatiunea dorita: "
                + "\n" + "    1. Adaugare Vinil. "
                + "\n" + "    2. Afiseaza vinilurile adaugate pana acum."
                + "\n" + "    3. Inchide aplicatia. ");
         while(scanner.hasNext()){
             switch (scanner.nextInt()){
                 case 1: cazulUnu(user);
                     break;
                 case 2: cazulDoi(user);
                     break;
                 case 3: cazulTrei();
                 default: System.out.println("Alegere invalida!");
             }
         }


    }

    public static void cazulUnu(User user) throws IOException, ParseException {
        Vinil vinil = salvareVinil(user);
        System.out.println("Vinilul a fost salvat cu succes. Doriti o recomandare de pret?"
                + "\n" + "     1. DA. "
                + "\n" + "     2. NU. ");
        if (scanner.nextInt() == 1) {
            float pretRecomandat = (float) recomandarePret(vinil);
            System.out.println("Pretul recomandat este: "
                    + pretRecomandat
                    + ". Doriti sa-l salvati?"
                    + "\n" + "     1. DA. "
                    + "\n" + "     2. NU. ");
            if (scanner.nextInt() == 1) {
                salvarePretRecomandat(vinil, user, pretRecomandat);
            } else {
            }
        } else {
        }

        System.out.println("Va rugam alegeti operatiunea dorita: "
                + "\n" + "    1. Adaugare Vinil. "
                + "\n" + "    2. Afiseaza vinilurile adaugate pana acum."
                + "\n" + "    3. Inchide aplicatia. ");
    }

    public static void cazulDoi(User user) throws IOException {
        afisareListaViniluri(user);
        System.out.println("Va rugam alegeti operatiunea dorita: "
                + "\n" + "    1. Adaugare Vinil. "
                + "\n" + "    2. Afiseaza vinilurile adaugate pana acum."
                + "\n" + "    3. Inchide aplicatia. ");
    }

    public static void cazulTrei(){
        System.out.println("Aplicatia s-a oprit.");
        System.exit(0);
    }
}