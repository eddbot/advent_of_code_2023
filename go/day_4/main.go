package main

import (
	"fmt"
	"io"
	"os"
	"regexp"
	"strconv"
	"strings"
)

type Game struct {
	id            int
	targetNumbers map[int]int
	totalPoints   int
}

func main() {
	input := input()
	games := createGames(input)
	fmt.Println("part 1 =", partOne(games))
	fmt.Println("part 2 =", partTwo(games))
}

func partOne(games []Game) int {
	winningPoints := 0
	for _, game := range games {

		if game.totalPoints == 0 {
			continue
		}

		winningPoints += 1 << (game.totalPoints - 1)
	}

	return winningPoints
}

var partTwoTotal = 0 // thanks I hate this :)
func partTwo(games []Game) int {
	recurser(games)

	return partTwoTotal
}

func recurser(games []Game) {

	if len(games) == 0 {
		return
	}
	for i, game := range games {
		partTwoTotal++
		recurser(games[i+1 : i+game.totalPoints+1])
	}
}

func createGames(input []string) []Game {

	games := []Game{}
	for gameNumber, line := range input {

		game := Game{id: gameNumber + 1, targetNumbers: map[int]int{}}
		line = strings.Split(line, ":")[1]
		gameLine := strings.Split(line, "|")
		re := regexp.MustCompile(`\d+`)

		targetNumbers := re.FindAllString(gameLine[0], -1)
		winningNumbers := re.FindAllString(gameLine[1], -1)

		for _, number := range targetNumbers {
			num, _ := strconv.Atoi(number)
			game.targetNumbers[num]++
		}

		for _, number := range winningNumbers {
			num, _ := strconv.Atoi(number)
			if _, ok := game.targetNumbers[num]; ok {
				game.totalPoints++
			}
		}
		games = append(games, game)
	}

	return games

}

func input() []string {

	file, err := os.Open("input.txt")

	if err != nil {
		panic(err)
	}

	stream, err := io.ReadAll(file)

	if err != nil {
		panic(err)
	}

	return strings.Split(string(stream), "\n")

}
