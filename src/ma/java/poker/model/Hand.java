package ma.java.poker.model;

import ma.java.poker.comparator.HandComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by master Alish on 3/10/17.
 */
public class Hand {
    public static final int DEFAULT_SIZE = 5;

    private final List<Card> cards = new ArrayList<>();

    public Hand(){}

    public Hand(String ... strCards){
        for(String cardStr: strCards){
            addCard(Card.parse(cardStr));
        }
    }

    public void addCard(Card card){
        cards.add(card);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Card card: cards){
            result.append(card).append(" ");
        }
        return result.toString();
    }

    public void sort(){
        Collections.sort(cards, (card1, card2) -> card1.rank.compareTo(card2.rank));
    }

    public List<Card> getSortedCards() {
        sort();
        return cards.subList(0, cards.size());
    }

    public boolean isBiggerThan(Hand other){
        return new HandComparator().compare(this, other) > 0;
    }

    public boolean isLessThan(Hand other){
        return new HandComparator().compare(this, other) < 0;
    }

    public boolean isEqualTo(Hand other){
        return new HandComparator().compare(this, other) == 0;
    }
}
