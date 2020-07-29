package io.zipcoder.casino;

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
        return null;
    }


    public void playGame() {

    }

    public String printCasinoMenu() {
        return null;
    }

    public void playerLogin(String givenName) {

    }


    public void playerLogout(String givenName) {

    }


    public String printActivePlayers() {
        return null;
    }

    public void parseMenuInput() {

    }

    public Boolean checkIfActivePlayers() {
        return null;
    }

    public void quit() {

    }
}
