package main

import (
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

func main() {

	input := input()
	totals := []int{}
	ratios := []int{}

	// iterate over the grid (input)
	for y := 0; y < len(input); y++ {
		for x := 0; x < len(input[y]); x++ {

			digit := string(input[y][x])

			// if the digit is a number or a dot, we outta here
			if aNumber(digit) || digit == "." {
				continue
			}

			// lets keep track of all the possible adjacent numbers for this
			// symbol, but no duplicates plz!
			num := map[int]bool{}

			// looping around the symbol
			for i := -1; i <= 1; i++ {
				for j := -1; j <= 1; j++ {
					// guard against oob
					if y+i > len(input)-1 || y+i < 0 {
						continue
					}
					// guard against oob
					if x+j > len(input[y])-1 || x+j < 0 {
						continue
					}

					idx := x + j
					adj := string(input[y+i][idx])
					str := ""

					// we found a chunk of a number, now we need to find the start of it
					if aNumber(adj) {
						for {
							if !aNumber(string(input[y+i][idx])) {
								idx++
								break
							}
							if idx == 0 {
								break
							}
							idx--
						}

						// now the idx pointer is at the start of the num, we can move fwd
						for {
							if idx > len(input[0])-1 {
								break
							}
							if !aNumber(string(input[y+i][idx])) {
								break
							}
							str += string(input[y+i][idx])
							idx++
						}
						// finally lets store the number into the set
						n, _ := strconv.Atoi(str)
						// this gives us all of the adjacent numbers for this symbol
						num[n] = true
					}
				}
			}

			// part 1
			// we just need to append the number so we can total it up
			for k := range num {
				totals = append(totals, k)
			}

			// part 2
			// if our num set has 2 entries, and the digit is '*'
			// we have found a valid gear ratio.
			if len(num) == 2 && digit == "*" {
				ratio := 1

				for k := range num {
					ratio *= k
				}

				ratios = append(ratios, ratio)
			}

		}
	}

	// reporting the results
	total := 0
	totalRatio := 0

	for _, num := range totals {
		total += num
	}

	fmt.Println("part 1", total)

	for _, num := range ratios {
		totalRatio += num
	}
	fmt.Println("part 2", totalRatio)
}

func aNumber(num string) bool {

	_, err := strconv.Atoi(num)

	return err == nil

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
