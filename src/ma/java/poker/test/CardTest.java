package ma.java.poker.test;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import ma.java.poker.model.Card;

/**
 * Created by master Alish on 3/11/17.
 */
public class CardTest extends TestCase{
    public void assertCardRankAndSuit(Card.Rank expectedRank, Card.Suit expectedSuit, Card card){
        if(expectedRank != card.rank || expectedSuit != card.suit) {
            fail(String.format("Expected %s%s, but given %s", expectedRank.name, expectedSuit.symbol, card));
        }
    }

    public void testAssertCardRankAndSuitMethodSucceeds(){
        assertCardRankAndSuit(Card.Rank.Ace, Card.Suit.Hearts, new Card(Card.Rank.Ace, Card.Suit.Hearts));
        assertCardRankAndSuit(Card.Rank.Ten, Card.Suit.Spades, new Card(Card.Rank.Ten, Card.Suit.Spades));
    }

    public void testAssertCardRankAndSuitMethodFailsForWrongCard(){
        try {
            assertCardRankAndSuit(Card.Rank.Ace, Card.Suit.Hearts, new Card(Card.Rank.Ten, Card.Suit.Hearts));
            fail();
        }catch (AssertionFailedError ignored){}
        try {
            assertCardRankAndSuit(Card.Rank.Ten, Card.Suit.Spades, new Card(Card.Rank.Jack, Card.Suit.Clubs));
            fail();
        }catch (AssertionFailedError ignored){}
    }

    public void testCardParse(){
        assertCardRankAndSuit(Card.Rank.Ace, Card.Suit.Hearts, Card.parse("A♥"));
        assertCardRankAndSuit(Card.Rank.King, Card.Suit.Spades, Card.parse("K♠"));
        assertCardRankAndSuit(Card.Rank.Queen, Card.Suit.Clubs, Card.parse("Q♣"));
        assertCardRankAndSuit(Card.Rank.Jack, Card.Suit.Diamonds, Card.parse("J♦"));
        assertCardRankAndSuit(Card.Rank.Ten, Card.Suit.Hearts, Card.parse("10♥"));
        assertCardRankAndSuit(Card.Rank.Nine, Card.Suit.Spades, Card.parse("9♠"));
        assertCardRankAndSuit(Card.Rank.Eight, Card.Suit.Clubs, Card.parse("8♣"));
        assertCardRankAndSuit(Card.Rank.Seven, Card.Suit.Diamonds, Card.parse("7♦"));
        assertCardRankAndSuit(Card.Rank.Six, Card.Suit.Hearts, Card.parse("6♥"));
        assertCardRankAndSuit(Card.Rank.Five, Card.Suit.Spades, Card.parse("5♠"));
        assertCardRankAndSuit(Card.Rank.Four, Card.Suit.Clubs, Card.parse("4♣"));
        assertCardRankAndSuit(Card.Rank.Three, Card.Suit.Diamonds, Card.parse("3♦"));
        assertCardRankAndSuit(Card.Rank.Two, Card.Suit.Hearts, Card.parse("2♥"));
    }
}
