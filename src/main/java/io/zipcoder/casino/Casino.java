package io.zipcoder.casino;
import io.zipcoder.casino.utilities.Console;

public class Casino {

    // List<Players> All Players
    // List<Players> Active Players
    Game currentGame;
    Console console;

    public Casino() {
        this.console = new Console(System.in, System.out);
    }

    public void run(){


    }

    public Player createPlayer(String aName){
        return new Player(aName);
    }

    public void resetBalance(){

    }


    public Game selectGame() {

    }


    public void playGame() {

    }

    public String printCasinoMenu() {

    }

    public void playerLogin(String givenName) {

    }


    public void playerLogout(String givenName) {

    }


    public String printActivePlayers() {


    }

    public void parseMenuInput() {

    }

    public Boolean checkIfActivePlayers() {

    }

    public void quit() {

    }
}
