package ma.java.poker.test;

import junit.framework.TestCase;
import ma.java.poker.model.Hand;
import ma.java.poker.comparator.HandComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by master Alish on 3/10/17.
 */
public class TestComparator extends TestCase{
    public void testRoyalFlushsAreEqual(){
        Hand hand = new Hand("A♣", "K♣", "Q♣", "J♣", "10♣");
        Hand hand2 = new Hand("A♥", "K♥", "Q♥", "J♥", "10♥");
        assertEquals(0, new HandComparator().compare(hand, hand2));
    }

    public void testEqualStrightFlushs(){
        Hand hand = new Hand("9♣", "K♣", "Q♣", "J♣", "10♣");
        Hand hand2 = new Hand("9♥", "K♥", "Q♥", "J♥", "10♥");
        assertEquals(0, new HandComparator().compare(hand, hand2));

        hand = new Hand("A♣", "3♣", "2♣", "5♣", "4♣");
        hand2 = new Hand("A♥", "3♥", "2♥", "5♥", "4♥");
        assertEquals(0, new HandComparator().compare(hand, hand2));
    }

    public void testNotEqualStrightFlushs(){
        Hand hand = new Hand("9♣", "10♣", "J♣", "Q♣", "K♣");
        Hand hand2 = new Hand("8♥", "9♥", "10♥", "J♥", "Q♥");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);

        hand = new Hand("A♣", "2♣", "3♣", "4♣", "5♣");
        hand2 = new Hand("2♥", "3♥", "4♥", "5♥", "6♥");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("A♣", "2♣", "3♣", "4♣", "5♣");
        hand2 = new Hand("9♥", "K♥", "Q♥", "J♥", "10♥");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("A♣", "2♣", "3♣", "4♣", "5♣");
        hand2 = new Hand("2♥", "3♥", "4♥", "5♥", "6♥");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);
    }

    public void testNotEqualFourOfAKind(){
        Hand hand = new Hand("10♣", "10♥", "10♠", "10♦", "K♣");
        Hand hand2 = new Hand("8♥", "9♣", "9♥", "9♠", "9♦");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);

        hand = new Hand("10♣", "10♥", "10♠", "10♦", "K♣");
        hand2 = new Hand("8♥", "A♣", "A♥", "A♠", "A♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("10♣", "10♥", "10♠", "10♦", "K♣");
        hand2 = new Hand("10♣", "10♥", "10♠", "10♦", "9♣");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);
    }

    public void testNotEqualFullHouse(){
        Hand hand = new Hand("10♣", "10♥", "10♠", "K♦", "K♣");
        Hand hand2 = new Hand("10♣", "10♥", "10♠", "9♦", "9♣");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);

        hand = new Hand("9♣", "9♥", "9♠", "4♦", "4♣");
        hand2 = new Hand("5♣", "5♥", "5♠", "K♦", "K♣");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);
        assertTrue(new HandComparator().compare(hand2, hand) < 0);
    }

    public void testEqualFlushes(){
        Hand hand = new Hand("9♥", "10♥", "3♥", "6♥", "2♥");
        Hand hand2 = new Hand("9♠", "10♠", "3♠", "6♠", "2♠");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);

        hand = new Hand("A♥", "K♥", "4♥", "3♥", "2♥");
        hand2 = new Hand("4♠", "2♠", "3♠", "K♠", "A♠");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);
    }

    public void testNotEqualFlushes(){
        Hand hand = new Hand("9♥", "10♥", "3♥", "6♥", "2♥");
        Hand hand2 = new Hand("9♠", "K♠", "3♠", "6♠", "2♠");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("A♥", "K♥", "4♥", "3♥", "5♥");
        hand2 = new Hand("4♠", "2♠", "3♠", "K♠", "A♠");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);
    }

    public void testEqualStraights(){
        Hand hand = new Hand("9♥", "10♥", "K♠", "J♠", "Q♦");
        Hand hand2 = new Hand("9♣", "10♠", "K♣", "J♣", "Q♠");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);
    }

    public void testNotEqualStraights(){
        Hand hand = new Hand("9♥", "10♥", "K♠", "J♠", "Q♦");
        Hand hand2 = new Hand("9♣", "10♠", "8♣", "J♣", "Q♠");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);
    }

    public void testNotEqualThreeOfAKind(){
        Hand hand = new Hand("10♣", "10♥", "10♠", "Q♦", "K♣");
        Hand hand2 = new Hand("8♥", "7♣", "9♥", "9♠", "9♦");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);

        hand = new Hand("10♣", "10♥", "6♠", "10♦", "K♣");
        hand2 = new Hand("8♥", "A♣", "K♥", "A♠", "A♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("10♣", "10♥", "6♠", "10♦", "K♣");
        hand2 = new Hand("8♥", "10♣", "K♥", "10♠", "10♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);
    }

    public void testEqualTwoPairs(){
        Hand hand = new Hand("10♣", "10♥", "Q♠", "Q♦", "K♣");
        Hand hand2 = new Hand("10♥", "10♣", "Q♥", "Q♠", "K♦");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);
    }

    public void testNotEqualTwoPairs(){
        Hand hand = new Hand("10♣", "10♥", "Q♠", "Q♦", "K♣");
        Hand hand2 = new Hand("8♥", "7♣", "7♥", "9♠", "9♦");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);

        hand = new Hand("10♣", "10♥", "6♠", "K♦", "K♣");
        hand2 = new Hand("8♥", "8♣", "K♥", "A♠", "A♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("10♣", "10♥", "6♠", "6♦", "K♣");
        hand2 = new Hand("8♥", "5♣", "8♥", "10♠", "10♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);
    }

    public void testEqualPairs() {
        Hand hand = new Hand("10♣", "10♥", "Q♠", "J♦", "K♣");
        Hand hand2 = new Hand("10♥", "10♣", "Q♥", "J♠", "K♦");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);
    }

    public void testNotEqualPairs(){
        Hand hand = new Hand("10♣", "10♥", "6♠", "9♦", "K♣");
        Hand hand2 = new Hand("8♥", "3♣", "K♥", "A♠", "A♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);

        hand = new Hand("10♣", "9♥", "6♠", "6♦", "K♣");
        hand2 = new Hand("8♥", "5♣", "A♥", "10♠", "10♦");
        assertTrue(new HandComparator().compare(hand, hand2) < 0);
    }

    public void testEqualHighCard(){
        Hand hand = new Hand("10♣", "A♥", "6♠", "9♦", "K♣");
        Hand hand2 = new Hand("10♥", "A♣", "6♥", "9♠", "K♦");
        assertTrue(new HandComparator().compare(hand, hand2) == 0);
    }

    public void testNotEqualHighCard(){
        Hand hand = new Hand("10♣", "A♥", "7♠", "9♦", "K♣");
        Hand hand2 = new Hand("10♥", "A♣", "6♥", "9♠", "K♦");
        assertTrue(new HandComparator().compare(hand, hand2) > 0);
    }

    public void testSortingWithComparator(){
        List<Hand> hands = new ArrayList<>();
        Hand highCardSmall = new Hand("10♥", "A♣", "6♥", "9♠", "K♦");
        Hand highCard = new Hand("10♣", "A♥", "7♠", "9♦", "K♣");
        Hand twoPairs = new Hand("10♣", "10♥", "Q♠", "Q♦", "K♣");
        Hand threeOfAKind = new Hand("10♣", "10♥", "10♠", "Q♦", "K♣");
        Hand straight = new Hand("9♣", "10♠", "8♣", "J♣", "Q♠");
        Hand fullHouseSmall = new Hand("10♣", "10♥", "10♠", "9♦", "9♣");
        Hand fullHouse = new Hand("10♣", "10♥", "10♠", "K♦", "K♣");
        Hand fourOfAKind = new Hand("10♣", "10♥", "10♠", "10♦", "K♣");
        Hand straightFlush = new Hand("9♣", "10♣", "J♣", "Q♣", "K♣");
        Hand royalFlush = new Hand("A♥", "K♥", "Q♥", "J♥", "10♥");

        hands.add(highCardSmall);
        hands.add(royalFlush);
        hands.add(straight);
        hands.add(highCard);
        hands.add(twoPairs);
        hands.add(threeOfAKind);
        hands.add(straightFlush);
        hands.add(fourOfAKind);
        hands.add(fullHouseSmall);
        hands.add(fullHouse);

        Collections.sort(hands, new HandComparator());

        assertEquals(highCardSmall, hands.get(0));
        assertEquals(highCard, hands.get(1));
        assertEquals(twoPairs, hands.get(2));
        assertEquals(threeOfAKind, hands.get(3));
        assertEquals(straight, hands.get(4));
        assertEquals(fullHouseSmall, hands.get(5));
        assertEquals(fullHouse, hands.get(6));
        assertEquals(fourOfAKind, hands.get(7));
        assertEquals(straightFlush, hands.get(8));
        assertEquals(royalFlush, hands.get(9));
    }
}
