package ma.java.poker.comparator;

import ma.java.poker.exceptions.UnacceptableHandSizeException;
import ma.java.poker.model.Card;
import ma.java.poker.model.Combination;
import ma.java.poker.model.Hand;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by master Alish on 3/10/17.
 */
public class HandEvaluator {
    private final List<Card> cards;
    private Map<Card.Rank, Integer> ranksMap;

    public HandEvaluator(Hand hand) {
        this.cards = hand.getSortedCards();
        if (cards.size() != 5) {
            throw new UnacceptableHandSizeException();
        }
        ranksMap = getAlikeRanksCountsMap(cards);
    }

    public Combination getCombination() {
        if(isFlush(cards)){
            if(isStraight(cards)){
                if(cards.get(0).rank == Card.Rank.Ten){
                    return Combination.RoyalFlush;
                }
                return Combination.StraightFlush;
            }
            return Combination.Flush;
        }
        if(isStraight(cards)){
            return Combination.Straight;
        }
        if (isHighCard()) return Combination.HighCard;
        if (isPair()) return Combination.Pair;
        if (isTwoPairs()) return Combination.TwoPairs;
        if (isThreeOfAKind()) return Combination.ThreeOfAKind;
        if (isFourOfAKind()) return Combination.FourOfAKind;
        if (isFullHouse()) return Combination.FullHouse;
        return Combination.Flush;
    }

    private boolean isHighCard() {
        return ranksMap.values().size() == 5;
    }

    private boolean isPair() {
        return ranksMap.values().size() == 4;
    }

    private boolean isTwoPairs() {
        return ranksMap.containsValue(2) && ranksMap.values().size() == 3;
    }

    private boolean isFullHouse() {
        return ranksMap.containsValue(3) && ranksMap.containsValue(2);
    }

    private boolean isThreeOfAKind() {
        return ranksMap.containsValue(3) && ranksMap.containsValue(1);
    }

    private boolean isFourOfAKind() {
        return ranksMap.containsValue(4);
    }

    private boolean isStraight(List<Card> cards) {
        return (cards.get(0).rank.value == cards.get(1).rank.value - 1 &&
                cards.get(1).rank.value == cards.get(2).rank.value - 1 &&
                cards.get(2).rank.value == cards.get(3).rank.value - 1 &&
                cards.get(3).rank.value == cards.get(4).rank.value - 1) ||

                (cards.get(0).rank == Card.Rank.Two &&
                        cards.get(1).rank == Card.Rank.Three &&
                        cards.get(2).rank == Card.Rank.Four &&
                        cards.get(3).rank == Card.Rank.Five &&
                        cards.get(4).rank == Card.Rank.Ace);
    }

    private boolean isFlush(List<Card> cards) {
        Card.Suit suit = cards.get(0).suit;

        for (Card card : cards) {
            if (card.suit != suit) {
                return false;
            }
        }

        return true;
    }

    private Map<Card.Rank, Integer> getAlikeRanksCountsMap(List<Card> cards) {
        Map<Card.Rank, Integer> rankMap = new HashMap<>();
        for (Card card : cards) {
            if (rankMap.keySet().contains(card.rank)) {
                rankMap.put(card.rank, rankMap.get(card.rank) + 1);
            } else {
                rankMap.put(card.rank, 1);
            }
        }
        return rankMap;
    }

    public List<Card.Rank> getHandRanksSortedByValue() {
        List<RankCount> rankCountList = new ArrayList<>();
        for(Card.Rank rank: ranksMap.keySet()){
            int count = ranksMap.get(rank);
            rankCountList.add(new RankCount(rank, count));
        }
        Collections.sort(rankCountList, (rankCount1, rankCount2) -> {
            if(rankCount1.count == rankCount2.count){
                return rankCount1.rank.compareTo(rankCount2.rank);
            }
            return Integer.compare(rankCount1.count, rankCount2.count);
        });
        Collections.reverse(rankCountList);
        return rankCountList.stream().map(rankCount -> rankCount.rank).collect(Collectors.toList());
    }

    class RankCount{
        public Card.Rank rank;
        public int count;

        public RankCount(Card.Rank rank, int count) {
            this.count = count;
            this.rank = rank;
        }
    }
}
