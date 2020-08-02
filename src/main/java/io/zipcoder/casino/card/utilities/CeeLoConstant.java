package io.zipcoder.casino.card.utilities;

public class CeeLoConstant {

    public static final String INVALID_BET_PROMPT = "Amount is greater than your current balance. Please enter in a valid amount";
    public static final String INVALID_YES_OR_NO_PROMPT = "This input is invalid. Please enter a yes/no.";
    public static final String WELCOME_BET_PROMPT = "Welcome to CeeLo! Would you like to bet? yes/no";
    public static final String BET_AMOUNT_PROMPT = "How much would you like to bet?";
    public static final String CURRENT_POT = "The current pot is this amount: ";
    public static final String RULES_PROMPT = "Would you like to see the rules?";
    public static final String ROLL_DICE_PROMPT = "Press enter to roll the dice.";
    public static final String DEPOSIT_POT = "This amount will be deposited to your account: ";
    public static final String UPDATED_BALANCE= "Your new balance is:";
    public static final String SCORE = "Your score is: ";
    public static final String BYE_PROMPT = "Thank you for playing CeeLo!";
    public static final String ASK_TO_REPLAY = "Would you like to play again? yes/no";
    public static final String YES = "yes";
    public static final String NO = "no";
    public static final String PLAYER = "Player";
    public static final String HOUSE = "House";

    public static final String PLAYER_WINS = "The Player wins";
    public static final String HOUSE_WINS = "The House wins";



    public static final String CEELO_RULES = "======================================================================================= \n" +
            "Automatic Win: Player wins automatically if one of the following combinations are rolled: \n" +
            " 4-5-6, triples of same number, doubles with same number with a third showing a 6\n" +
            "Automatic Loss: Player loses automatically if one of the following combinations are rolled: \n" +
            "1-2-3, doubles with same number with third showing a 1\n" +
            "Re-roll: When none of the above combinations are rolled, player needs to re-roll until one of the above combinations are rolled.\n" +
            "=======================================================================================";

}
