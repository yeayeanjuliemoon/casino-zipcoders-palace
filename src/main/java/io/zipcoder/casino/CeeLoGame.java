package io.zipcoder.casino;

import io.zipcoder.casino.card.utilities.CeeLoConstant;

import java.util.List;

public class CeeLoGame extends DiceGame {


    private Integer bet;
    private Integer pot;
    private GamblingPlayer player;
    private Integer playerScore = 0;
    private Console console = new Console(System.in, System.out);

    public CeeLoGame(GamblingPlayer player) {
        super(3, player);
        this.player = player;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Integer getBet() {
        return this.bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Integer getPot() {
        return this.pot;
    }

    public void setPot() {
        while (this.bet > player.getBalance()) {
            this.bet = this.console.getIntegerInput(CeeLoConstant.INVALID_BET_PROMPT);
        }

        this.pot = 2 * bet;
        this.console.println(CeeLoConstant.CURRENT_POT + this.getPot());
    }

    public List<Integer> getDiceRoll() {
        Dice dice = new Dice(3);
        dice.rollDice();
        return dice.getDieValues();
    }

    public String checkRoll(List<Integer> diceValues, String currentPlayer, String currentOpponent) {
        String winner = "";
        this.console.println(currentPlayer + " roll is: " +
                diceValues.get(0) + ", " +
                diceValues.get(1) + ", " +
                diceValues.get(2));

        StringBuilder builder = new StringBuilder();
        for(Integer value: diceValues) {
            builder.append(value);
        }

        String combination = builder.toString();
        if(checkForWin(combination)) {
            winner = currentPlayer;
        } else if(checkForLoss(combination)) {
            winner = currentOpponent;
        }

        return winner;
    }

    public boolean checkForWin(String combination) {
        for(WinningDiceCombo roll: WinningDiceCombo.values()) {
            if(roll.getCombo().equals(combination)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkForLoss(String combination) {
        for(LosingDiceCombo roll: LosingDiceCombo.values()) {
            if(roll.getCombo().equals(combination)) {
                return true;
            }
        }
        return false;
    }

    public void play() {
        startGame();
        boolean replay = true;
        while (replay) {
            this.console.getStringInput(CeeLoConstant.ROLL_DICE_PROMPT);
            String playerTurn = checkRoll(getDiceRoll(), CeeLoConstant.PLAYER, CeeLoConstant.HOUSE);
            String houseTurn = checkRoll(getDiceRoll(), CeeLoConstant.HOUSE, CeeLoConstant.PLAYER);

            if (!playerTurn.equals("") || !houseTurn.equals("")) {
                replay = false;
                if (playerTurn.equals(CeeLoConstant.PLAYER) || houseTurn.equals(CeeLoConstant.PLAYER)) {
                    payout();
                    printResults(CeeLoConstant.PLAYER_WINS);
                    replay = playAgain(replay);
                } else if (playerTurn.equals(CeeLoConstant.HOUSE) || houseTurn.equals(CeeLoConstant.HOUSE)) {
                    printResults(CeeLoConstant.HOUSE_WINS);
                    replay = playAgain(replay);
                }
            }
        }
    }

    public boolean playAgain(boolean replayOrNot) {
        String continueOrLeave = this.console.getStringInput(CeeLoConstant.ASK_TO_REPLAY);
        if (continueOrLeave.equals(CeeLoConstant.YES)) {
            replayOrNot = true;
            takeBet();
            setPot();
        } else
            exit();

        return replayOrNot;
    }

    public void printResults(String winnerString) {
        this.console.println(winnerString);
        this.console.println(CeeLoConstant.UPDATED_BALANCE + player.getBalance());
        this.console.println(CeeLoConstant.SCORE + printScore());
    }

    public void takeBet() {
        setBet(this.console.getIntegerInput(CeeLoConstant.BET_AMOUNT_PROMPT));
    }

    public void startGame() {
        String userString = this.console.getStringInput(CeeLoConstant.WELCOME_BET_PROMPT);
        while (!userString.toLowerCase().equals(CeeLoConstant.YES) && !userString.toLowerCase().equals(CeeLoConstant.NO)) {
            userString = this.console.getStringInput(CeeLoConstant.INVALID_YES_OR_NO_PROMPT);
        }

        if ((userString.toLowerCase().equals(CeeLoConstant.NO))) {
            exit();
            return;
        } else if (userString.toLowerCase().equals(CeeLoConstant.YES)) {
            takeBet();
            setPot();
            String playerString = this.console.getStringInput(CeeLoConstant.RULES_PROMPT);

            if (playerString.toLowerCase().equals(CeeLoConstant.YES)) {
                this.console.println(this.printGameRules());
            }

            setPot();
        }
    }

    public String printScore() {
        String result = "";
        result += this.playerScore;

        return result;
    }

    public Integer getScore() {
        return this.playerScore;
    }

    public String printGameRules() {
        return CeeLoConstant.CEELO_RULES;
    }

    public void exit() {
        this.console.println(CeeLoConstant.BYE_PROMPT);
    }

    public void payout() {
        player.deposit(getPot());
        playerScore++;
        this.pot = 0;
    }

    public void nextTurn() {
    }

    public Boolean checkGameState() {
        return null;
    }
}

