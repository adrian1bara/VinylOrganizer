package org.example;
import org.example.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner; // I use scanner because it's command line

public class UserLogic {

    public static final Scanner keyboard = new Scanner(System.in);
        public static User login() throws FileNotFoundException {
            // citeste din fisier
            Scanner scan = new Scanner (new File("C:\\Users\\Adrian\\Projects\\VinylOrganizer\\src\\main\\resources\\UserAndPassword.txt"));
            String userFisier = new String();
            String pass = new String();

            // citeste de la tastatura
            System.out.println("\n" + "Introduceti userul: ");
            final String inpUser = keyboard.nextLine();
            System.out.println("\n" + "Introduceti parola: ");
            String inpPass = "Parola pentru " + inpUser + " este " + keyboard.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if(line.equals(inpUser)) {
                    userFisier = line;
                } else if(line.equals(inpPass)){
                    pass = line;
                }
            }

            if (inpUser.equals(userFisier) && inpPass.equals(pass)) {
                System.out.print("\n" + "Bun venit in VinylOrganizer! " + "\n");
            } else {
                System.out.print("\n" + "Userul sau parola nu sunt corecte sau nu exista. Te rog incearca din nou sau creaza un user nou.");
            }
            return construiesteUser(inpUser);
        }

    public static User salvareUser() throws FileNotFoundException {
        String userIntrodus;
        String parola;
        User user = new User();
        try{
        FileWriter fWriter = new FileWriter(
                    "/Users/Adrian/Projects/VinylOrganizer/src/main/resources/UserAndPassword.txt", true);
        fWriter.write(System.lineSeparator());
        System.out.println("\n" + "Introduceti userul nou: ");
            userIntrodus = keyboard.nextLine();
        fWriter.write(userIntrodus);
        fWriter.write(System.lineSeparator());
        System.out.println("\n" + "Introduceti parola noua: ");
        parola = "Parola pentru " + userIntrodus + " este " + keyboard.nextLine();
        fWriter.write(parola);
        fWriter.close();

        if (userIntrodus.length() != 0 && parola.length() != 0 ) {
            Random random = new Random();
            int max = 55;
            user.setId(random.nextInt(max));
            user.setNume(userIntrodus);
            System.out.print("\n" + "Userul a fost salvat cu succes. Te rugam sa te autentifici.");
            login();
        }
        } catch (IOException e) {
            System.out.print("\n" + "A intervenit o eroare. Te rugam sa incerci din nou.");
        }
        return user;
    }

    private static User construiesteUser(String nume){
        User user = new User();
        user.setNume(nume);
        return user;
    }

}

