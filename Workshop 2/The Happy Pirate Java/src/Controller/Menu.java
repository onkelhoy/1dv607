package Controller;

import Helper.FileHandler;

import java.lang.reflect.Member;
import java.util.Scanner;

/**
 * Created by henry on 2016-09-20.
 */
public class Menu {
    private Scanner scan = null;
    public Menu(Scanner scan){
        this.scan = scan;
        SplashScreen();
        AuthenticateMenu();
    }


    private void AuthenticateMenu(){

        System.out.print("1). login.\n2). register\n0). Exit\n # ");
        String choise = scan.nextLine();
        switch (choise){
            case "1":
                LoginMenu();
                break;
            case "2":
                RegistrationMenu();
                break;
            case "0":
                System.out.println("Have a nice day sir, bye bye!!!");
                scan.close();
                break;
            default:
                System.out.println("Wrong input...\nTry again!!!");
                AuthenticateMenu();
        }

    }
    private void LoginMenu(){

        System.out.print("Username : ");
        String uName = scan.nextLine();
        System.out.print("Password : ");
        String pass = scan.nextLine();

        if(login(uName, pass)){
            MSTmenu();
        }

    }
    private void RegistrationMenu(){

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


    private void PreMenu(){
        System.out.println("\n\n----------------------------------------------------------------------------------------------------------------");
    }
    private void MSTmenu(String type){
        PreMenu();
        type = type.toLowerCase();
        System.out.print("0. Logout\n1. User info\n2. Boats\n3. Calendar\4. Payments\n 5. Show Members");
        switch (type){
            case "secretary":
                System.out.print("\n6. Club Calendar\n7. Berth Registrations");
                break;
            case "treasurer":
                System.out.print("\n6. Club Payments"); //change to better name later
                break;
        }

        MSTprompt(type);
    }
    private void MSTprompt(String type){

        System.out.print("\n?: ");
        int input = -1;
        try { input = scan.nextInt(); }
        catch (Exception e) {
            System.out.println("Wrong input (only numbers), press any key to proceed");
            scan.next();
            MSTmenu(type);
        }



        switch (input){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }

    private void SeldinTrashMethod(){

    }
}
