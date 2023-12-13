package main

import (
	"fmt"
	"os"
	"strings"
)

var reflectionPoints = map[int]int{}

func main() {
	in := input()

	total := 0

	for _, line := range in {
		y := reflectionPoint(non_flipped(line))
		x := reflectionPoint(flipped(line))

		if y > 0 {
			total += (y * 100)
		}
		if x > 0 {
			total += x
		}
	}

	fmt.Println(total)

	for _, line := range in {

		toEdit := []rune(line)
		for i := 0; i < len(toEdit); i++ {

			switch toEdit[i] {
			case '#':
				toEdit[i] = '.'
			case '.':
				toEdit[i] = '#'
			case '\n':
				continue
			}

			toEdit = []rune(line)
		}

	}

}

func reflectionPoint(board []string) int {

	for i := 1; i < len(board); i++ {

		if board[i] == board[i-1] {

			up := i - 1
			down := i

			for {
				str1 := board[up]
				str2 := board[down]

				if str1 != str2 {
					break
				}
				up--
				down++
				if up < 0 || down >= len(board) {
					return i
				}
			}
		}
	}
	return 0
}

func non_flipped(board string) []string {
	return strings.Split(board, "\n")
}

func flipped(board string) []string {
	splitBoard := strings.Split(board, "\n")

	flipped := []string{}

	for i := 0; i < len(splitBoard[0]); i++ {
		str := ""
		for j := 0; j < len(splitBoard); j++ {
			str += string(splitBoard[j][i])
		}
		flipped = append(flipped, str)
	}
	return flipped
}

func input() []string {

	file, err := os.ReadFile("inputs/input.txt")

	if err != nil {
		panic(err)
	}

	return strings.Split(string(file), "\n\n")
}
