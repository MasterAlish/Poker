package ma.java.poker.test;

import junit.framework.TestCase;
import ma.java.poker.model.Card;
import ma.java.poker.model.Combination;
import ma.java.poker.model.Hand;
import ma.java.poker.comparator.HandEvaluator;

/**
 * Created by master Alish on 3/10/17.
 */
public class HandEvaluatorTest extends TestCase {
    public void testRoyalFlush() {
        Hand hand = new Hand();
        hand.addCard(Card.parse("A♥"));
        hand.addCard(Card.parse("K♥"));
        hand.addCard(Card.parse("10♥"));
        hand.addCard(Card.parse("J♥"));
        hand.addCard(Card.parse("Q♥"));

        HandEvaluator evaluator = new HandEvaluator(hand);
        assertEquals(Combination.RoyalFlush, evaluator.getCombination());
    }

    public void testRoyalFlush2() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("A♣", "K♣", "Q♣", "J♣", "10♣"));
        assertEquals(Combination.RoyalFlush, evaluator.getCombination());
    }

    public void testStraightFlush() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("A♦", "2♦", "3♦", "4♦", "5♦"));
        assertEquals(Combination.StraightFlush, evaluator.getCombination());
    }

    public void testStraightFlush2() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "K♣", "Q♣", "J♣", "10♣"));
        assertEquals(Combination.StraightFlush, evaluator.getCombination());
    }

    public void testStraightFlush3() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "7♣", "8♣", "5♣", "6♣"));
        assertEquals(Combination.StraightFlush, evaluator.getCombination());
    }

    public void testFourOfAKind() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♦", "9♥", "9♣", "9♠", "6♣"));
        assertEquals(Combination.FourOfAKind, evaluator.getCombination());
    }

    public void testFourOfAKind2() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♦", "J♥", "9♣", "J♠", "J♣"));
        assertEquals(Combination.FourOfAKind, evaluator.getCombination());
    }

    public void testFullHouse() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♦", "J♥", "9♣", "9♠", "J♣"));
        assertEquals(Combination.FullHouse, evaluator.getCombination());
    }

    public void testFlush() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♦", "9♦", "10♦", "K♦", "2♦"));
        assertEquals(Combination.Flush, evaluator.getCombination());
    }

    public void testStraight() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♥", "9♦", "10♣", "K♦", "Q♣"));
        assertEquals(Combination.Straight, evaluator.getCombination());
    }

    public void testStraight2() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♣", "A♥", "10♣", "K♥", "Q♦"));
        assertEquals(Combination.Straight, evaluator.getCombination());
    }

    public void testThreeOfAKind() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("J♣", "J♥", "J♣", "K♥", "Q♦"));
        assertEquals(Combination.ThreeOfAKind, evaluator.getCombination());
    }

    public void testThreeOfAKind2() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "2♥", "2♣", "J♥", "2♦"));
        assertEquals(Combination.ThreeOfAKind, evaluator.getCombination());
    }

    public void testTwoPairs() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "2♥", "2♣", "J♥", "J♦"));
        assertEquals(Combination.TwoPairs, evaluator.getCombination());
    }

    public void testPair() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "2♥", "3♣", "J♥", "J♦"));
        assertEquals(Combination.Pair, evaluator.getCombination());
    }

    public void testHighCard() {
        HandEvaluator evaluator = new HandEvaluator(new Hand("9♣", "2♥", "3♣", "J♥", "8♦"));
        assertEquals(Combination.HighCard, evaluator.getCombination());
    }
}
