package io.zipcoder.casino;

import io.zipcoder.casino.card.games.Blackjack;
import io.zipcoder.casino.card.games.GoFish;


public class Casino {

    public Player activePlayer;
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
            parseMenuInput();
        }
    }

    public Player createPlayer(String aName){
        return new Player(aName);
    }

    public void resetBalance(){

    }


    public void selectGame(Game selectedGame) {
        this.currentGame = selectedGame;
        playGame();
    }


    private void playGame() {
        currentGame.play();
    }

    public String printCasinoMenu() {
        String menu = "******* ♖ ZipCoder's Palace ♖  *******\n" +
                      "******* Please Enter A Number: *******\n"+
                      "* 1: Login ~~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                      "* 2: Play GoFish ~~~~~~~~~~~~~~~~~ *\n"+
                      "* 3: Play BlackJack ~~~~~~~~~~~~~~ *\n"+
                      "* 4: Play Craps ~~~~~~~~~~~~~~~~~~ *\n"+
                      "* 5: Play CeeLo ~~~~~~~~~~~~~~~~~~ *\n"+
                      "* 6: Logout ~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                      "* 7: Leave ZipCoder's Palace ~~~~~ *\n\n"+
                      "**** You must be logged in to play a game! ****\n\n";
        return menu;
    }

    public void playerLogin(String givenName) {

    }


    public void playerLogout(String givenName) {

    }


    public String printActivePlayers() {
        return null;
    }

    public void parseMenuInput() {
        Integer input = console.getIntegerInput("Selection >");
        switch (input){
            case 1: //Login
                //Player player = createPlayer();
                // activePlayer = player;
                break;
            case 2: //GoFish
                if(checkIfActivePlayers()){
                    selectGame(new GoFish());
                } else {
                    console.println("No player currently logged in");
                }
                break;
            case 3: //BlackJack
                if(checkIfActivePlayers()){
                    selectGame(new Blackjack());
                } else {
                    console.println("No player currently logged in");
                }
                break;
            case 4: // Craps
                if(checkIfActivePlayers()){
//                    selectGame(new CrapsGame());
                } else {
                    console.println("No player currently logged in");
                }
                break;
            case 5: // CeeLo
                if(checkIfActivePlayers()){
//                    selectGame(new CeeLoGame());
                } else {
                    console.println("No player currently logged in");
                }
                break;
            case 6: // Logout
                if(checkIfActivePlayers()){
                    String aPlayer = this.activePlayer.toString();
                    playerLogout(aPlayer); // Needs to be re-looked at
                } else {
                    console.println("No player currently logged in");
                }
                break;
            case 7: // Quit
                quit();
                break;
            default:
                    console.println("Please enter a valid selection (1 -> 9)");
                break;
        }
    }

    public Boolean checkIfActivePlayers() {
        return true;
    }

    public void quit() {
        console.println(" Thank you for playing at ZipCoder's Casino!");
        System.exit(0);
    }
}
