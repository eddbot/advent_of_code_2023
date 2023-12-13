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
		total += reflectionPoint(line)
	}

	fmt.Println(total)

}

func reflectionPoint(board string) int {

	repeats := []int{}

	columns := isReflected(non_flipped(board))

	if columns != 0 {
		repeats = append(repeats, columns*100)
		return columns * 100
	}

	rows := isReflected(flipped(board))

	if rows != 0 {
		repeats = append(repeats, rows)
		return rows
	}

	return 0
}

func isReflected(board []string) int {
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

	file, err := os.ReadFile("inputs/test_input.txt")

	if err != nil {
		panic(err)
	}

	return strings.Split(string(file), "\n\n")
}
