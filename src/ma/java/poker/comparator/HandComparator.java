package ma.java.poker.comparator;

import ma.java.poker.model.Card;
import ma.java.poker.model.Combination;
import ma.java.poker.model.Hand;

import java.util.Comparator;
import java.util.List;

/**
 * Created by master Alish on 3/10/17.
 */
public class HandComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand hand1, Hand hand2) {
        HandEvaluator evaluator1 = new HandEvaluator(hand1);
        HandEvaluator evaluator2 = new HandEvaluator(hand2);

        Combination combination1 = evaluator1.getCombination();
        Combination combination2 = evaluator2.getCombination();
        if (combination1 == combination2) {
            if(combination1 == Combination.Straight || combination1 == Combination.StraightFlush){

                if(smallestStraight(hand1)){
                    return smallestStraight(hand2)?0:-1;
                }
                if(smallestStraight(hand2)){
                    return 1;
                }
            }

            List<Card.Rank> hand1Ranks = evaluator1.getHandRanksSortedByValue();
            List<Card.Rank> hand2Ranks = evaluator2.getHandRanksSortedByValue();

            for (int i = 0; i < hand1Ranks.size(); i++) {
                Card.Rank rank1 = hand1Ranks.get(i);
                Card.Rank rank2 = hand2Ranks.get(i);

                if (rank1 != rank2) {
                    return rank1.compareTo(rank2);
                }
            }
        }
        return combination1.compareTo(combination2);
    }

    private boolean smallestStraight(Hand hand) {
        return hand.getSortedCards().get(0).rank == Card.Rank.Two &&
                hand.getSortedCards().get(4).rank == Card.Rank.Ace;
    }
}
