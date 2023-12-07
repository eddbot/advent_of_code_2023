package main

import (
	"fmt"
	"os"
	"slices"
	"sort"
	"strconv"
	"strings"
)

type PokerHand struct {
	cards      []string
	tracker    map[string]int
	bid        int
	handType   int
	tieBreaker int
}

func (ph *PokerHand) assignRankWithWildCards() {

	jMask := []bool{false, false, false, false, false}
	perms := map[string]bool{}

	for i, card := range ph.cards {
		if card == "J" {
			jMask[i] = true
		}
	}
	// how to generate all sequences?

	str := []string{}

	for _, s := range ph.cards {
		str = append(str, s)

	}

	for a := range cardConv {
		for b := range cardConv {
			for c := range cardConv {
				for d := range cardConv {
					for e := range cardConv {

						if jMask[0] {
							str[0] = a
						}
						if jMask[1] {

							str[1] = b
						}
						if jMask[2] {

							str[2] = c
						}
						if jMask[3] {

							str[3] = d
						}
						if jMask[4] {

							str[4] = e
						}

						x := strings.Join(str, "")

						perms[x] = true

					}
				}
			}
		}
	}

	bestPerm := 0

	for permutation := range perms {
		p := getRank(strings.Split(permutation, ""))

		if p > bestPerm {
			bestPerm = p
		}
	}

	ph.handType = bestPerm

}

func (ph *PokerHand) assignRank() {
	ph.handType = getRank(ph.cards)
}

func getRank(cards []string) int {

	tracker := map[string]int{}

	for _, card := range cards {
		tracker[card]++
	}

	for _, v := range tracker {

		if len(tracker) == 1 {
			return 7 // five in a row
		}
		if len(tracker) == 5 {
			return 1 // high card
		}
		if v == 4 {
			return 6 // 4 of a kind
		}
		if v == 3 && len(tracker) == 2 {
			return 5 // full house
		}
		if v == 3 && len(tracker) > 2 {
			return 4 // 3 of a kind
		}
		if v == 2 && len(tracker) == 3 {
			return 3 // 2 pair
		}
		if v == 1 && len(tracker) == 4 {
			return 2 // 1 pair
		}
	}

	return 0

}

func main() {

	input := input()
	hands := []PokerHand{}

	for _, hand := range input {

		h := strings.Fields(hand)
		bid, err := strconv.Atoi(h[1])
		if err != nil {
			panic(err)
		}
		cards := strings.Split(h[0], "")

		ph := PokerHand{cards: cards, bid: bid}
		// ph.assignRank()
		ph.assignRankWithWildCards()
		hands = append(hands, ph)
	}

	sorter := func([]PokerHand) {
		sort.Slice(hands, func(i, j int) bool {
			if hands[i].handType == hands[j].handType {
				for x := 0; x < 5; x++ {

					cardA := cardConvPartTwo[hands[i].cards[x]]
					cardB := cardConvPartTwo[hands[j].cards[x]]

					if cardA < cardB {
						return false
					}

					if cardA > cardB {
						return true
					}

				}
			}
			return hands[i].handType > hands[j].handType
		})

	}

	sorter(hands)

	slices.Reverse(hands)
	total := 0

	for i, hand := range hands {
		total += hand.bid * (i + 1)
	}

	fmt.Println(total)

}
func input() []string {

	file, err := os.ReadFile("inputs/input.txt")

	if err != nil {
		panic(err)
	}

	return strings.Split(string(file), "\n")
}

var cardConv = map[string]int{
	"A": 14,
	"K": 13,
	"Q": 12,
	"J": 11,
	"T": 10,
	"9": 9,
	"8": 8,
	"7": 7,
	"6": 6,
	"5": 5,
	"4": 4,
	"3": 3,
	"2": 2,
}

var cardConvPartTwo = map[string]int{
	"A": 14,
	"K": 13,
	"Q": 12,
	"T": 10,
	"9": 9,
	"8": 8,
	"7": 7,
	"6": 6,
	"5": 5,
	"4": 4,
	"3": 3,
	"2": 2,
	"J": 1,
}
