package io.zipcoder.casino;

import io.zipcoder.casino.card.games.Blackjack;
import io.zipcoder.casino.card.games.GoFish;

public class GameFactory {

    public static Game getGame(String gameType, Player aPlayer){
        if(gameType == null || aPlayer == null){
            return null;
        }
        if(gameType.equals("GOFISH")){
            return new GoFish(5,aPlayer);
        } else if(gameType.equals("BLACKJACK") && aPlayer instanceof GamblingPlayer){
            return new Blackjack(aPlayer);
        } else if(gameType.equals("CRAPS") && aPlayer instanceof GamblingPlayer){
            return new CrapsGame((GamblingPlayer) aPlayer);
        } else if(gameType.equals("CEELO") && aPlayer instanceof GamblingPlayer){
            return new CeeLoGame((GamblingPlayer) aPlayer);
        }

        return null;
    }

}
