package io.zipcoder.casino;
import io.zipcoder.casino.utilities.Console;

public class Casino {

    Game currentGame;
    Console console;

    public Casino() {
        this.console = new Console;
    }

    public Player createPlayer(){
        return new Player;
    }

    public void resetBalance(){

    }

    public Game selectGame() {
        return new Game;
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
