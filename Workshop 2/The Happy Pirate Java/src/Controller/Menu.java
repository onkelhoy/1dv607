package Controller;

import Model.Member;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by henry on 2016-09-20.
 */
public class Menu {
    private Scanner scan = null;
    private YatchClub yatchclub;

    public Menu(YatchClub yatchclub, Scanner scan) {
        this.scan = scan;
        this.yatchclub = yatchclub;
        SplashScreen();
        AuthenticateMenu();
    }

    //************** Meny Fields ****************************************************************************************//
    private void AuthenticateMenu() {
        PreMenu();
        System.out.print("--- Authenticate Menu ---\n1). Login\n2). Register\n3). Anonymous\n0). Exit\n # ");
        String choise = scan.nextLine();
        switch (choise) {
            case "1":
                LoginMenu();
                break;
            case "2":
                RegistrationMenu();
                break;
            case "3":
                ContactsMenu("Anonymous", false);
                AuthenticateMenu();
                break;
            case "0":
                System.out.println("Thank you for visiting The Happy Pirate yacht club.");
                scan.close();
                break;
            default:
                System.out.println("That command in not valid.\nTry again!");
                AuthenticateMenu();
        }
    }
    private void RegistrationMenu() {
        PreMenu();

        System.out.println("--- Register Menu ---");
        System.out.print("Username: ");
        String userName = scan.nextLine();

        System.out.print("Email: ");
        String eMail = scan.nextLine();

        System.out.print("Identity nr: ");
        String id = scan.nextLine();

        System.out.print("Password: ");
        String password = scan.nextLine();
        System.out.print("Password again: ");
        String passwordRetype = scan.nextLine();

        if (password.equals(passwordRetype)) {
            if (yatchclub.register(userName, password, eMail, id)) {
                System.out.println("Successful registration");
                LoginMenu();
            } else {
                showError("\n\nThis user information is already in use");
                AuthenticateMenu();
            }
        } else {
            System.out.println("Passwords does not match!\nTry again!!!");
            RegistrationMenu();
        }
    }
    private void LoginMenu() {
        PreMenu();
        System.out.print("--- Login Menu ---\nUsername: ");
        String uName = scan.nextLine();
        System.out.print("Password: ");
        String pass = scan.nextLine();

        if (yatchclub.login(uName, pass)) {
            MSTmenu();
        } else {
            PreMenu();
            showError("That username and password is not valid");
            AuthenticateMenu();
        }

    }

    private void ContactsMenu(String Name, Boolean compact) {
        PreMenu();
        System.out.print(String.format("--- %s Menu ---\n1). List members\n2). Search member \n0). Back\n # ", Name));
        String choise = scan.nextLine();
        switch (choise) {
            case "1":
                PrintMembers("username = '*'", false);
                showError(""); // works as a continue message as well :)
                break;
            case "2":
                SearchField(compact);
                // when no more search it should continue to this..
                ContactsMenu(Name, compact);
                break;
            case "0":
                break;
        }
    }

    private void MSTmenu() {
        PreMenu();
        String type = yatchclub.getMember().getType();
        type = type.substring(0, 1).toUpperCase() + type.substring(1);

        System.out.println(String.format("--- %s Menu ---", type));
        System.out.print("0. Logout\n1. User info\n2. Boats\n3. Calendar\n4. Payments\n5. Contacts");
        switch (yatchclub.getMember().getType()) {
            case "secretary":
                System.out.print("\n6. Club Calendar\n7. Berth Registrations");
                break;
            case "treasurer":
                System.out.print("\n6. Club Payments"); //change to better name later
                break;
        }

        MSTprompt();
    }

    private void UserInfoMenu() {
        PreMenu();
        Member m = yatchclub.getMember();
        System.out.print(String.format("--- User info menu ---\n0. Go back\n1: Save\n\nChangeable <number:change>\n  2. Username: %s\n  3. Email: %s\n  4. Name: %s\n  5. Change Password\n\nIdentity: %s\nMembership fee: %s\n\n?: ", m.getUsername(), m.getEmail(), m.getName(), m.getIdentity(), (m.hasPayedMembership() ? "has payed" : "has not payed")));

        String input = scan.nextLine();
        int number = -1;
        if (input.length() > 1) {
            String[] arr = input.split(":");
            try {
                number = Integer.parseInt(arr[0]);
            } catch (Exception e) {
            }
            input = arr[1];

            switch (number) {
                case -1://error
                    showError("Number : string - is only supported");
                    UserInfoMenu();
                    break;
                case 2: //usn
                    m.setUsername(input);
                    UserInfoMenu();
                    break;
                case 3: //email
                    m.setEmail(input);
                    UserInfoMenu();
                    break;
                case 4: //name
                    m.setName(input);
                    UserInfoMenu();
                    break;
                case 5: //password
                    m.setPassword(input);
                    UserInfoMenu();
                    break;
                default:
                    showError("Only option 2-5 can have 2 values");
                    UserInfoMenu();
            }
        } else {
            try {
                number = Integer.parseInt(input);
            } catch (Exception e) {}

            switch (number) {
                case -1://error
                    showError("only numbers!");
                    UserInfoMenu();
                    break;
                case 0: //go back
                    MSTmenu();
                    break;
                case 1: //save
                    yatchclub.setMember(false);
                    MSTmenu();
                    break;
                default:
                    showError("Only option 0-1 can have a number");
                    UserInfoMenu();
            }
        }
    }
    private void CalenderMenu(){

    }
    private void PaymentsMenu(){

    }

    private void BoatMenu(boolean listValue) {
        PreMenu();
        if (listValue) {
            // show boats
        }
        // \n4). register might be edited in the future.
        System.out.print("--- Boat Menu ---\n1). List boats.\n2). Remove boats\n3). Add new boat\n4). Register\n0). Exit\n # ");
        String input = scan.nextLine();


        switch (input) {
            case "1":
                BoatMenu(true);
                break;
            case "2":
                // remove boats
                System.out.print("\n(0 to quit)\nBoat ID: ");
                String boatID = scan.nextLine();
                if (boatID.equals("0")) BoatMenu(listValue);
                else {
                    // remove boat based on boatID.
                }
                break;
            case "3":
                // add new boat
                AddNewBoat();
                break;
            case "4":
                // register boat to a berth, might be updated
                break;

            // case 5: edit a specific boat functionality, to keep in mind

            case "0":
                MSTmenu();
                break;
            default:
                showError("Only number 0-4 are valid");
                BoatMenu(false);
        }
    }
    private void AddNewBoat() {
        System.out.print("--- Add new boat ---");

        //Boat name is not a property
        System.out.print("\nBoat Name: ");
        String bname = scan.nextLine();
        System.out.print(("\nBoat Type: "));
        String btype = scan.nextLine();
        System.out.print("Boat Length: ");
        String blength = scan.nextLine();

        yatchclub.saveBoat(bname, btype, blength);
        // save to xmlDB
        System.out.print("\nBoat has been saved");
        BoatMenu(false);
    }

    //************** Help methods ***************************************************************************************//

    private void showError(String error) {
        System.out.println(error);
        System.out.print("Press enter to continue.. ");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }
    private void SearchField(boolean compact){
        System.out.print("\n--- Search ----\n Fields that can be searched on: \n\tboats, boatlength, boattype \n\tname, username, id, identity\n\tage, month, email, gender\n\n Search: ");
        String query = scan.nextLine();

        PrintMembers(query, compact);

        System.out.print("\nDo another search (y/n): ");
        String ans = scan.nextLine();
        switch (ans.toLowerCase()){
            case "y":
            case "yes":
                SearchField(compact);
        }
        //no and then continue to the other method thats called after this!
    }
    private void PrintMembers(String query, boolean compact){
        NodeList nl = yatchclub.SearchDB(query, "member");
        if(nl == null) System.out.print("not a valid search query!");
        else if(nl.getLength() == 0) System.out.print("No match");
        else {
            for (int i = 0; i < nl.getLength(); i++) {
                Member m = new Member((Element) nl.item(i));
                System.out.print("\n" + (compact ? m.compactInfo() : m.verboseInfo()));
            }
        }
    }

    //************ Prompt methods ***************************************************************************************//

    private void MSTprompt() {

        System.out.print("\n?: ");

        String input = scan.nextLine();
        String type = yatchclub.getMember().getType();

        switch (input) {
            case "0":
                yatchclub.setMember(true); //logout
                AuthenticateMenu();
                break;
            case "1":
                // show user info menu
                UserInfoMenu();
                break;
            case "2":
                // show boats menu
                BoatMenu(false);
                break;
            case "3":
                // show calendar menu
                CalenderMenu();
                break;
            case "4":
                // show payments menu
                PaymentsMenu();
                break;
            case "5":
                if (type.equals("secretary") || type.equals("treasurer")) {
                    ContactsMenu("Contacts", true);
                    MSTmenu();
                } //show contacts meny (more info)
                else {
                    ContactsMenu("Contacts", false);
                    MSTmenu();
                } //show contacts meny
                break;
            case "6":
                if (type.equals("secretary")) {
                } else if (type.equals("treasurer")) {
                } else showError("only values 0 - 5 are accepted");

                break;
            case "7":
                if (type.equals("secretary")) {
                } else if (type.equals("treasurer")) showError("only values 0 - 6 are accepted");
                else showError("only values 0 - 5 are accepted");

                break;
            default:
                if (type.equals("secretary")) showError("only values 0 - 7 are accepted");
                else if (type.equals("treasurer")) showError("only values 0 - 6 are accepted");
                else showError("only values 0 - 5 are accepted");
        }
    }
    private void SplashScreen() {
        System.out.print("                                                  Welcome To:                                                  \n" +
                "     __________________________________________________________________________________________________________\n" +
                "       ______                   _     _                                   ____                                 \n" +
                "         /      /               /    /                                    /    )   ,                           \n" +
                "     ---/------/__----__-------/___ /-----__------__------__-------------/____/-------)__----__--_/_----__-----\n" +
                "       /      /   ) /___)     /    /    /   )   /   )   /   ) /   /     /        /   /   ) /   ) /    /___)    \n" +
                "     _/______/___/_(___ _____/____/____(___(___/___/___/___/_(___/_____/________/___/_____(___(_(_ __(___ _____\n" +
                "                                              /       /         /                                              \n" +
                "                                             /       /      (_ /                                               \n" +
                "               ____________________________________________________________________________________            \n" +
                "                   __                                                                                          \n" +
                "                   / |        /          ,         ,                              ,                            \n" +
                "               ---/__|----__-/---_--_--------__-------__--_/_---)__----__--_/_--------__----__-----            \n" +
                "                 /   |  /   /   / /  ) /   /   ) /   (_ ` /    /   ) /   ) /    /   /   ) /   )                \n" +
                "               _/____|_(___/___/_/__/_/___/___/_/___(__)_(_ __/_____(___(_(_ __/___(___/_/___/_____            \n" +
                "                                                                                                               \n" +
                "                                                                                                               \n" +
                "                                   _______________________________________                                     \n" +
                "                                                                                                               \n" +
                "                                                                                                               \n" +
                "                                   ---__---------__--_/_----__---_--_-----                                     \n" +
                "                                     (_ ` /   / (_ ` /    /___) / /  )                                         \n" +
                "                                   _(__)_(___/_(__)_(_ __(___ _/_/__/_____                                     \n" +
                "                                            /                                                                  \n" +
                "                                        (_ /                                                                   \n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    private void PreMenu() {
        System.out.println("\n\n\n===============================================================================================================\n===============================================================================================================\n");
    }

}
