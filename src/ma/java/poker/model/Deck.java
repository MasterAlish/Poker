package ma.java.poker.model;

import ma.java.poker.exceptions.EmptyDeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by master Alish on 3/10/17.
 */
public class Deck {
    private final List<Card> deck = new ArrayList<>();
    private Random randomizer = new Random();

    public Deck(){
        initDeck();
    }

    private void initDeck() {
        deck.clear();
        for(Card.Rank rank: Card.Rank.values()){
            for(Card.Suit suit: Card.Suit.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public Card dealCard() throws EmptyDeckException {
        if(deck.size() > 0) {
            int number = randomizer.nextInt(deck.size());
            return deck.remove(number);
        }
        throw new EmptyDeckException("Deck is empty!");
    }
}
