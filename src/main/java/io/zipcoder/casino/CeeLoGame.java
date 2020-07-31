package io.zipcoder.casino;

import java.util.Arrays;
import java.util.List;

public class CeeLoGame extends DiceGame {


    private Integer pot;
    private GamblingPlayer player;
    private Integer playerScore = 0;
    private Console console = new Console(System.in, System.out);

    public CeeLoGame(GamblingPlayer player) {
        super(3, player);
        this.player = player;
    }

    public Integer getPot() {
        return this.pot;
    }

    public void takeBet(Integer bet) {
        while(bet > player.getBalance()) {
            bet = this.console.getIntegerInput("Amount is greater than your current balance. Please enter in a valid amount");
        }

        this.pot = 2 * bet;
    }

    public List<Integer> getDiceRoll() {
        Dice dice = new Dice(3);
        dice.rollDice();
        return dice.getDiceValues();
    }

    public String checkPlayer(List<Integer> diceValues) {
        String winner = "";
        this.console.println("You roll is: " + diceValues.get(0) + ", " + diceValues.get(1) + ", " + diceValues.get(2));

        if(diceValues.contains(4) && diceValues.contains(5) && diceValues.contains(6)) {
            winner = "Player";
        } else if(diceValues.get(0).equals(diceValues.get(1)) && diceValues.get(1).equals(diceValues.get(2))) {
            winner = "Player";
        } else if(diceValues.get(0).equals(diceValues.get(1)) && diceValues.contains(6)) {
            winner = "Player";
        } else if(diceValues.get(1).equals(diceValues.get(2)) && diceValues.contains(6)) {
            winner = "Player";
        } else if(diceValues.get(0).equals(diceValues.get(2)) && diceValues.contains(6)) {
            winner = "Player";
        } else if(diceValues.contains(1) == diceValues.contains(2) == diceValues.contains(3)) {
            winner = "House";
        } else if(diceValues.get(0).equals(diceValues.get(1)) && diceValues.contains(1)) {
            winner = "House";
        } else if(diceValues.get(1).equals(diceValues.get(2)) && diceValues.contains(1)) {
            winner = "House";
        } else if(diceValues.get(0).equals(diceValues.get(2)) && diceValues.contains(1)) {
            winner = "House";
        }

        return winner;
    }

    public String checkHouse(List<Integer> diceValues) {
        String winner = "";
        this.console.println("House rolls: " + diceValues.get(0) + ", " + diceValues.get(1) + ", " + diceValues.get(2));

        if(diceValues.contains(4) && diceValues.contains(5) && diceValues.contains(6)) {
            winner = "House";
        } else if(diceValues.get(0) == diceValues.get(1) && diceValues.get(1) == diceValues.get(2)) {
            winner = "House";
        } else if(diceValues.get(0) == diceValues.get(1) && diceValues.contains(6)) {
            winner = "House";
        } else if(diceValues.get(1) == diceValues.get(2) && diceValues.contains(6)) {
            winner = "House";
        } else if(diceValues.get(0) == diceValues.get(2) && diceValues.contains(6)) {
            winner = "House";
        } else if(diceValues.contains(1) == diceValues.contains(2) == diceValues.contains(3)) {
            winner = "Player";
        } else if(diceValues.get(0) == diceValues.get(1) && diceValues.contains(1)) {
            winner = "Player";
        } else if(diceValues.get(1) == diceValues.get(2) && diceValues.contains(1)) {
            winner = "Player";
        } else if(diceValues.get(0) == diceValues.get(2) && diceValues.contains(1)) {
            winner = "Player";
        }

        return winner;
    }

    public void play() {
        String userString = this.console.getStringInput("Welcome to CeeLo! Would you like to bet? yes/no");
        while(!userString.toLowerCase().equals("yes") && !userString.toLowerCase().equals("no")) {
            userString = this.console.getStringInput("This input is invalid. Please enter a yes/no.");
        }

        if((userString.toLowerCase().equals("no"))){
            exit();
            return;
        } else if(userString.toLowerCase().equals("yes")) {
            takeBet(this.console.getIntegerInput("How much would you like to bet?"));
            this.console.println("The current pot is this amount: " + this.getPot());
            String playerString = this.console.getStringInput("Would you like to see the rules?");
            if (playerString.toLowerCase().equals("yes")) {
                this.console.println(this.printGameRules());
            }
        }


            Boolean replay = true;
            while (replay) {
                this.console.getStringInput("Press enter to roll the dice.");
                String playerTurn = checkPlayer(getDiceRoll());
                String houseTurn = checkHouse(getDiceRoll());

                if (!playerTurn.equals("") && !houseTurn.equals("")) {
                    replay = false;
                    if (playerTurn.equals("Player") || houseTurn.equals("Player")) {
                        this.console.println("The Player wins");
                        this.console.println("This amount will be deposited to your account: " + this.getPot());
                        payout();
                        this.console.println("Your new balance is:" + player.getBalance());
                        this.console.println("Your score is: " + printScore());
                        String continueOrLeave = this.console.getStringInput("Would you like to play again? yes/no");
                        if(continueOrLeave.equals("yes")) {
                            replay = true;
                            takeBet(this.console.getIntegerInput("How much would you like to bet?"));
                            this.console.println("The current pot is this amount: " + this.getPot());
                        }
                        exit();
                    } else if (playerTurn.equals("House") || houseTurn.equals("House")) {
                        this.console.println("The House wins");
                        this.console.println("Your score is " + printScore());
                        String continueOrLeave = this.console.getStringInput("Would you like to play again? yes/no");
                        if(continueOrLeave.equals("yes")) {
                            replay = true;
                            takeBet(this.console.getIntegerInput("How much would you like to bet?"));
                            this.console.println("The current pot is this amount: " + this.getPot());
                        }
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
        String rules =
                "======================================================================================= \n" +
                "Automatic Win: Player wins automatically if one of the following combinations are rolled: \n" +
                " 4-5-6, triples of same number, doubles with same number with a third showing a 6\n" +
                "Automatic Loss: Player loses automatically if one of the following combinations are rolled: \n" +
                "1-2-3, doubles with same number with third showing a 1\n" +
                "Re-roll: When none of the above combinations are rolled, player needs to re-roll until one of the above combinations are rolled.\n" +
                "=======================================================================================";

        return rules;
    }

    public void exit() {
        this.console.println("Thank you for playing CeeLo!");

    }

    @Override
    public void takeBet() {

    }

    public void payout() {
        player.deposit(getPot());
        playerScore++;
        this.pot = 0;
    }
}

