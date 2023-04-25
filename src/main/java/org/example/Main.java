package org.example;

import org.example.model.User;
import org.example.model.Vinil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println("APLICATIA A PORNIT");
        // TODO user log in
        User user = new User();

        //TODO trebuie adaptat ca inputul sa fie conform ENUMs
        Scanner scanner = new Scanner(System.in);
        VinilLogic vinilLogic = new VinilLogic();
        System.out.println("Va rugam alegeti operatiunea dorita: 1 = Adaugare Vinil  2 = Afiseaza vinilurile adaugate");
        if(scanner.nextInt() == 1) {
            Scanner scanner1 = new Scanner(System.in);

            String gen;
            System.out.println("Introduceti genul vinilului: ");
            gen = scanner1.nextLine();

            String stare;
            System.out.println("Introduceti stare vinilului: ");
            stare = scanner1.nextLine();

            Date dataAparitiei;
            System.out.println("Introduceti data aparitiei vinilului: ");
            String dateFormat = "dd/MM/yyyy";
            dataAparitiei = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());


            String dimensiune;
            System.out.println("Introduceti dimensiunea vinilului: ");
            dimensiune = scanner1.nextLine();

            String culoare;
            System.out.println("Introduceti culoarea vinilului: ");
            culoare = scanner1.nextLine();

            String tip;
            System.out.println("Introduceti tipul vinilului: ");
            tip = scanner1.nextLine();

            int pret;
            System.out.println("Introduceti pretul vinilului: ");
            pret = scanner.nextInt();

            Vinil vinil;
            vinil = vinilLogic.construiesteVinil(gen, tip, pret, stare, dataAparitiei, culoare, dimensiune);
            vinilLogic.adaugareVinil(vinil, user);
            System.out.println("Vinilul a fost salvat cu succes. Doriti o recomandare de pret? DA/NU");
            if (scanner1.nextLine() == "DA") {
                int pretRecomandat = vinilLogic.recomandarePret(vinil);
                System.out.println("Pretul recomandat este: " + pretRecomandat + ". Doriti sa-l salvati? DA/NU");
                if (scanner1.nextLine() == "DA") {
                    vinilLogic.salvarePretRecomandat(vinil, user, pretRecomandat);
                } else {
                    // ce facem?
                }
            } else {
                // ce facem?
            }



        } else if (scanner.nextInt() == 2) {
           List<Vinil> listaViniluri =  vinilLogic.afisareListaViniluri(user);
        }

    }
}