package ma.java.poker;

import ma.java.poker.comparator.HandComparator;
import ma.java.poker.comparator.HandEvaluator;
import ma.java.poker.model.Deck;
import ma.java.poker.model.Hand;

/**
 * Created by master Alish on 3/10/17.
 */
public class Poker {
    public static void main(String[] args) {
        Deck deck = new Deck();

        Hand myHand = new Hand();
        Hand otherHand = new Hand();

        for (int lap = 0; lap < Hand.DEFAULT_SIZE; lap++) {
            myHand.addCard(deck.dealCard());
            otherHand.addCard(deck.dealCard());
        }

        System.out.println("1. " + myHand.toString() + " - " + new HandEvaluator(myHand).getCombination());
        System.out.println("2. " + otherHand.toString() + " - " + new HandEvaluator(otherHand).getCombination());

        HandComparator handComparator = new HandComparator();
        int compareResult = handComparator.compare(myHand, otherHand);

        if (compareResult == 0) {
            System.out.println("Hands are equal");
        } else if (compareResult > 0) {
            System.out.println("First hand is bigger");
        } else {
            System.out.println("Second hand is bigger");
        }
    }
}
