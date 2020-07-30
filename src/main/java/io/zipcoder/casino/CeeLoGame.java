package io.zipcoder.casino;

import java.util.Arrays;
import java.util.List;

public class CeeLoGame extends DiceGame {

    private Integer pot;
    private GamblingPlayer player;
    private Integer playerScore = 0;

    public CeeLoGame(GamblingPlayer player) {
        this.player = player;
    }

    public Integer getPot() {
        return pot;
    }

    public void takeBet(Integer bet) {
        this.pot = 2 * bet;
    }

    public Integer[] getDiceRoll() {
        Dice dice = new Dice(3);
        dice.rollDice();
        return dice.getDiceValues();
    }

    public String checkPlayer(Integer[] diceValues) {
        String winner = "";
        List<Integer> combination = Arrays.asList(diceValues);

        if(combination.contains(4) && combination.contains(5) && combination.contains(6)) {
            winner = "Player";
        } else if(combination.get(0).equals(combination.get(1)) && combination.get(1).equals(combination.get(2))) {
            winner = "Player";
        } else if(combination.get(0).equals(combination.get(1)) && combination.contains(6)) {
            winner = "Player";
        } else if(combination.get(1).equals(combination.get(2)) && combination.contains(6)) {
            winner = "Player";
        } else if(combination.get(0).equals(combination.get(2)) && combination.contains(6)) {
            winner = "Player";
        } else if(combination.contains(1) == combination.contains(2) == combination.contains(3)) {
            winner = "House";
        } else if(combination.get(0).equals(combination.get(1)) && combination.contains(1)) {
            winner = "House";
        } else if(combination.get(1).equals(combination.get(2)) && combination.contains(1)) {
            winner = "House";
        } else if(combination.get(0).equals(combination.get(2)) && combination.contains(1)) {
            winner = "House";
        }

        return winner;
    }

    public String checkHouse(Integer[] diceValues) {
        String winner = "";
        List<Integer> combination = Arrays.asList(diceValues);

        if(combination.contains(4) && combination.contains(5) && combination.contains(6)) {
            winner = "House";
        } else if(combination.get(0) == combination.get(1) && combination.get(1) == combination.get(2)) {
            winner = "House";
        } else if(combination.get(0) == combination.get(1) && combination.contains(6)) {
            winner = "House";
        } else if(combination.get(1) == combination.get(2) && combination.contains(6)) {
            winner = "House";
        } else if(combination.get(0) == combination.get(2) && combination.contains(6)) {
            winner = "House";
        } else if(combination.contains(1) == combination.contains(2) == combination.contains(3)) {
            winner = "Player";
        } else if(combination.get(0) == combination.get(1) && combination.contains(1)) {
            winner = "Player";
        } else if(combination.get(1) == combination.get(2) && combination.contains(1)) {
            winner = "Player";
        } else if(combination.get(0) == combination.get(2) && combination.contains(1)) {
            winner = "Player";
        }

        return winner;
    }

    public void play() {
        Boolean replay = true;
        while(replay) {
            String playerTurn = checkPlayer(getDiceRoll());
            String houseTurn = checkHouse(getDiceRoll());

            if(!playerTurn.equals("") && !houseTurn.equals("")) {
                replay = false;
                if(playerTurn.equals("Player") || houseTurn.equals("Player")) {
                    payout();
                }

            }
        }

    }

    public void nextTurn() {
    }

    public Boolean checkGameState() {
        return null;
    }

    public String printScore() {
        String result = "";
        result = "The player's score is " + this.playerScore;

        return result;
    }

    public String printGameRules() {
        String rules = "Automatic Win: Player wins automatically if one of the following combinations are rolled: \n" +
                " 4-5-6, triples of same number, doubles with same number with a third showing a 6\n" +
                "Automatic Loss: Player loses automatically if one of the following combinations are rolled: \n" +
                "1-2-3, doubles with same number with third showing a 1\n" +
                "Re-roll: When none of the above combinations are rolled, player needs to re-roll until oen of the above combinations are rolled.";

        return rules;
    }

    public void exit() {

    }

    public void payout() {
        player.deposit(getPot());
        player.getBalance();
        playerScore++;
        this.pot = 0;
    }


    }

