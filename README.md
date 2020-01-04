# Tetris (AI included)
this program is a Tetris UI it comes with a built in AI

## AI
```
press A to activate AI
```
### the AI uses four function to evaluate a given position
#
* NUMBER OF HOLES => the count of holes or on filled blocks that can't be filled in the current move
* NUMBER OF BUMPS => the difference of the block height in the y axises
* AGGREGATE HEIGHT => the product of total addition of all the indexes of the heights blocks (biggest y values)
* NUMBER OF LINES => the number of rows filled in the new game state
#
* the AI searches throught every possible gamestate and find the best gamestate thanks to these functions. 

## For what can use this?
* play a game of tetris
* watch a ai play tetris


## Built With

* [Processing](https://processing.org/) - The graphic interface

## Author

**OLIVER MORGAN**

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/atOliverParkerMorgan/Chess_AI/blob/master/LICENSE) file for details

## Acknowledgments
this is a very simple tetris (without T-spin implementation) the AI isn't perfect
