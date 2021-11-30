package com.java.effective.item58;

import java.util.*;

public class Card {

    private final Suit suit;
    private final Rank rank;

    enum Suit {CLUB, DIAMOND, HEART, SPADE}
    enum Rank {ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING}

    Card (Suit suit, Rank rank ) {
        this.suit = suit;
        this.rank = rank;
    }

    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();

        for (Iterator<Suit> i = suits.iterator(); i.hasNext();) {
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext();) {
                deck.add(new Card(i.next(), j.next()));
            }
        }

        for (Iterator<Suit> i = suits.iterator(); i.hasNext();) {
            Suit suit = i.next();

            for (Iterator<Rank> j = ranks.iterator(); j.hasNext();) {
                deck.add(new Card(suit, j.next()));
            }
        }

    }
}
