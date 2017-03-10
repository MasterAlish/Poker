package ma.java.poker.model;

import ma.java.poker.exceptions.CannotParseCardException;

/**
 * Created by master Alish on 3/10/17.
 */

public class Card {
    public final Suit suit;
    public final Rank rank;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", rank.name, suit.symbol);
    }

    public static Card parse(String cardStr){
        try{
            Suit resultSuit = null;
            Rank resultRank = null;
            char suitSymbol = cardStr.charAt(cardStr.length()-1);
            for(Suit suit: Suit.values()){
                if (suit.symbol == suitSymbol){
                    resultSuit = suit;
                    break;
                }
            }
            String rankStr = cardStr.substring(0, cardStr.length()-1);
            for(Rank rank: Rank.values()){
                if (rank.name.equals(rankStr)){
                    resultRank = rank;
                    break;
                }
            }
            if(resultRank != null && resultSuit != null) {
                return new Card(resultRank, resultSuit);
            }
        }catch (Exception e){
            throw new CannotParseCardException();
        }
        throw new CannotParseCardException();
    }

    public enum Rank {
        Two("2", 2), Three("3", 3), Four("4", 4), Five("5", 5), Six("6", 6), Seven("7", 7), Eight("8", 8),
        Nine("9", 9), Ten("10", 10), Jack("J", 11), Queen("Q", 12), King("K", 13), Ace("A", 14);

        public final String name;
        public final int value;

        Rank(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

    public enum Suit {
        Hearts('♥'), Clubs('♣'), Spades('♠'), Diamonds('♦');

        public final char symbol;

        Suit(char symbol){
            this.symbol = symbol;
        }
    }
}
