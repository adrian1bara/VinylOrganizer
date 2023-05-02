package org.example;

import org.example.comun.*;
import org.example.model.User;
import org.example.model.Vinil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VinilLogic {
    public static final Scanner scanner = new Scanner(System.in);

    public static Vinil salvareVinil(User user) throws ParseException, IOException {
        String gen;
        System.out.println("Introduceti genul vinilului dintre urmatoarele: \n" +
                "    CLASICA,\n" +
                "    POP,\n" +
                "    COUNTRY,\n" +
                "    ROCK,\n" +
                "    WORLD,\n" +
                "    ELECTRONICA,\n" +
                "    JAZZ,\n" +
                "    BLUES,\n" +
                "    REGGAE");
        gen = scanner.nextLine();

        String stare;
        System.out.println("Introduceti starea vinilului dintre urmatoarele:  \n" +
                "    NEW,\n" +
                "    MINT,\n" +
                "    NEAR_MINT,\n" +
                "    NOT_SO_CLEAN ");
        stare = scanner.nextLine();

        Date dataAparitiei;
        System.out.println("Introduceti data aparitiei vinilului dupa formatul dd/MM/yyyy: ");
        String dateFormat = "dd/MM/yyyy";
        dataAparitiei = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());

        String dimensiune;
        System.out.println("Introduceti dimensiunea vinilului: \n" +
                "    SEVEN_INCH,\n" +
                "    TEN_INCH,\n" +
                "    TWELVE_INCH ");
        dimensiune = scanner.nextLine();

        String tip;
        System.out.println("Introduceti tipul vinilului:   \n" +
                "    ORIGINAL,\n" +
                "    REEDITAT");
        tip = scanner.nextLine();

        int pret;
        System.out.println("Introduceti pretul vinilului: ");
        pret = scanner.nextInt();

        Vinil vinil = new Vinil();
        Random random = new Random();
        int max = 55;
        vinil.setId(random.nextInt(max));
        vinil.setGen(GenVinil.valueOf(gen));
        vinil.setTip(TipVinil.valueOf(tip));
        vinil.setPret(pret);
        vinil.setStare(StareVinil.valueOf(stare));
        vinil.setDataAparitie(dataAparitiei);
        vinil.setDimensiune(DimensiuneVinil.valueOf(dimensiune));

        salvareFisier(vinil, user);

        return vinil;
    }

    public static void salvareFisier(Vinil vinilNou, User user) throws IOException {
        FileWriter fWriter = new FileWriter(
                "/Users/Adrian/Projects/VinylOrganizer/src/main/resources/Data.txt", true);
        fWriter.write(System.lineSeparator());
        String linieFisier = user.getNume().toString()  + " " + vinilNou.toString();
        fWriter.write(linieFisier);
        fWriter.write(System.lineSeparator());
        fWriter.close();
    }


    public static double recomandarePret(Vinil vinil){
        double pretRecomandat = 0;
        double rezultatProcentajRecomandat;
        double pretInitial = vinil.getPret();
        Calendar before90s = Calendar.getInstance();
        before90s.set(Calendar.MONTH, 01);
        before90s.set(Calendar.DATE, 01);
        before90s.set(Calendar.YEAR, 1990);
        Date dataDeReferinta = before90s.getTime();

        before90s.set(Calendar.MONTH, 01);
        before90s.set(Calendar.DATE, 01);
        before90s.set(Calendar.YEAR, 2000);
        Date dataDeReferinta2 = before90s.getTime();

        boolean veryGoodCondition = vinil.getStare().equals(StareVinil.NEW) || vinil.getStare().equals(StareVinil.MINT);
        boolean goodCondition = vinil.getStare().equals(StareVinil.NEAR_MINT);
        boolean notSoGoodCondition = vinil.getStare().equals(StareVinil.NOT_SO_CLEAN);

        boolean isBefore90s = vinil.getDataAparitie().before(dataDeReferinta);
        boolean isAfter00s = vinil.getDataAparitie().after(dataDeReferinta2);

        // inainte de 1990
        if( isBefore90s ){
            if( vinil.getTip().equals(TipVinil.ORIGINAL)){
                if ( veryGoodCondition) {
                    // pretul creste cu 20%
                    rezultatProcentajRecomandat = pretInitial*(20.0f/100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if ( goodCondition) {
                    // pretul creste cu 10%
                    rezultatProcentajRecomandat = pretInitial*(10.0f/100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if ( notSoGoodCondition) {
                    // pretul scade cu 15%
                    rezultatProcentajRecomandat = pretInitial*(15.0f/100.0f);
                    pretRecomandat = pretInitial - rezultatProcentajRecomandat;
                }
            } else {
                // pretul scade cu 10%
                rezultatProcentajRecomandat = pretInitial*(10.0f/100.0f);
                pretRecomandat = pretInitial - rezultatProcentajRecomandat;
            }

        }
        // dupa 1990 dar inainte de 2000
        else if ( !isBefore90s && !isAfter00s ) {
            if( vinil.getTip().equals(TipVinil.ORIGINAL)) {
                if (veryGoodCondition) {
                    // pretul creste cu 15%
                    rezultatProcentajRecomandat = pretInitial * (15.0f / 100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if (goodCondition) {
                    // pretul creste cu 5%
                    rezultatProcentajRecomandat = pretInitial * (5.0f / 100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if (notSoGoodCondition) {
                    // pretul scade cu 10%
                    rezultatProcentajRecomandat = pretInitial * (10.0f / 100.0f);
                    pretRecomandat = pretInitial - rezultatProcentajRecomandat;
                }
            } else {
                // pretul scade cu 5%
                rezultatProcentajRecomandat = pretInitial*(5.0f/100.0f);
                pretRecomandat = pretInitial - rezultatProcentajRecomandat;
            }
        }
        // dupa 2000
        else if (isAfter00s) {
            if( vinil.getTip().equals(TipVinil.ORIGINAL)) {
                if (veryGoodCondition) {
                    // pretul creste cu 10%
                    rezultatProcentajRecomandat = pretInitial * (10.0f / 100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if (goodCondition) {
                    // pretul creste cu 5%
                    rezultatProcentajRecomandat = pretInitial * (5.0f / 100.0f);
                    pretRecomandat = pretInitial + rezultatProcentajRecomandat;
                } else if (notSoGoodCondition) {
                    // pretul scade cu 15%
                    rezultatProcentajRecomandat = pretInitial * (15.0f / 100.0f);
                    pretRecomandat = pretInitial - rezultatProcentajRecomandat;
                }
            } else {
                // pretul scade cu 10%
                rezultatProcentajRecomandat = pretInitial*(10.0f/100.0f);
                pretRecomandat = pretInitial - rezultatProcentajRecomandat;
            }
        }

        return pretRecomandat;
    }

    public static void salvarePretRecomandat (Vinil vinil, User user, float pretRecomandat) throws IOException {
        vinil.setPret(pretRecomandat);
        String linieNoua = user.getNume().toString() + " " + vinil.toString();

        Path path = Paths.get("C:\\Users\\Adrian\\Projects\\VinylOrganizer\\src\\main\\resources\\Data.txt");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(user.getNume().toString()) && fileContent.get(i).contains(String.valueOf(vinil.getId()))) {
                fileContent.set(i, linieNoua);
                break;
            }
        }
        Files.write(path, fileContent, StandardCharsets.UTF_8);
    }

    public static void afisareListaViniluri (User user) throws IOException {
        Path path = Paths.get("C:\\Users\\Adrian\\Projects\\VinylOrganizer\\src\\main\\resources\\Data.txt");
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        List<String> vinilurileUserului = new ArrayList<>();
        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).contains(user.getNume().toString())) {
                vinilurileUserului.add(fileContent.get(i));
            }
        }

        System.out.println("Vinilurile adaugate sunt: ");
        vinilurileUserului.stream().forEach(v -> System.out.println(v.toString()));
        System.out.println("\n");
    }
}
