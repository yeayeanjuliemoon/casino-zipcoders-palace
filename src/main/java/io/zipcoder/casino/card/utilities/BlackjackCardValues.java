package io.zipcoder.casino.card.utilities;

public enum BlackjackCardValues {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    public final Integer value;

    BlackjackCardValues(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }

    public static Integer valueOfRank(String rank){
        for (BlackjackCardValues name: BlackjackCardValues.values()){
            if(rank.equals(name.toString())){
                return name.getValue();
            }

        }
        return null;
    }
}
