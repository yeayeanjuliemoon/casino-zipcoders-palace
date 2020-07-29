package io.zipcoder.casino;

import java.util.ArrayList;

public class Casino {

    Player activePlayer;
    //List<Player> Active Players
    public Game currentGame;
    public Console console;
    private boolean opFlag;

    public Casino() {
        this.console = new Console(System.in, System.out);
        this.opFlag = true;
    }

    public void run(){
        while(opFlag) {
            console.println(printCasinoMenu());
            //parseMenuInput();
        }
    }

    public Player createPlayer(String aName){
        return new Player(aName);
    }

    public void resetBalance(){

    }


    public Game selectGame() {
        return null;

    }


    public void playGame() {

    }

    public String printCasinoMenu() {
        return "A casino menu";
    }

    public void playerLogin(String givenName) {

    }


    public void playerLogout(String givenName) {

    }


    public String printActivePlayers() {
        return null;
    }
    /*
    public void parseMenuInput() {
        String input = console.getStringInput("Please enter selection >");
        switch (input){
            case "Create User":
                //Player player = createPlayer();
                // activePlayer = player;
                break;
            case "GoFish":
                //something
                break;
            case "BlackJack":
                if(checkIfActivePlayers()){
                    //this.currentGame = BlackJack;
                    //playGame(this.currentGame);
                } else {
                    //Error message?
                }
                break;
            case "Craps":
                //something
                break;
            case "Ceelo":
                //something
                break;
            default:
                console.println("Please enter a valid input");
                break;


        }

    }
     */

    public Boolean checkIfActivePlayers() {
        return null;
    }

    public void quit() {
        System.exit(0);
    }
}
