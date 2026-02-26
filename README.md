# Tetris (with Heuristic AI)

## Project Overview
This project is a Java + Processing implementation of Tetris with an optional built-in AI player.

You can play manually with keyboard controls or toggle AI mode to watch the game evaluate and execute moves automatically. The AI scores candidate placements using common board-quality heuristics and picks the highest-scoring option each turn.

## Features
- Playable Tetris game loop with standard tetrominoes and line clearing.
- Real-time score and level display.
- Next-piece preview queue.
- Keyboard controls for movement and rotation.
- Toggleable AI mode (`A`) that computes and applies best-found moves.
- Heuristic evaluation based on:
	- aggregate height
	- completed lines
	- holes
	- surface bumpiness

## Architecture / Structure
```text
src/main/java/
	proc/sketches/
		MAIN.java               # Processing sketch entry point and game loop
		AI/
			AI.java               # Move evaluation and best-move selection
			Bitmap.java           # Grid simulation for candidate move search
		Grid/
			Spot.java             # Grid state, line clearing, score/level progression
		Blocks/
			Block.java            # Basic block coordinate model
		Shapes/
			Shape.java            # Base shape behavior (movement/rotation helpers)
			*.java                # Concrete tetromino implementations
	util/
		ImageLoad.java          # Utility image loader (currently not used by MAIN)
```

### Runtime flow (high level)
1. `MAIN` initializes the Processing canvas, shape queue, timer, and assets.
2. A timer advances the active piece downward at fixed intervals.
3. Input (or AI) moves/rotates the active piece.
4. On collision, the piece locks, lines are cleared, score/level are updated, and the next piece spawns.

## Build & Run Instructions
### Prerequisites
- JDK 8+ (project currently targets Java 8 in `build.gradle`)
- A Java IDE (IntelliJ IDEA recommended for easiest run experience)

### Build
```bash
bash ./gradlew clean build
```

### Run
This repository currently does not define a Gradle `run` task. Run the app from your IDE by executing:
- Main class: `proc.sketches.MAIN`

### Controls
- `←` / `→`: move piece left/right
- `↑`: rotate piece
- `↓`: soft drop
- `A`: toggle AI mode on/off

## Testing
Run the existing test task:

```bash
bash ./gradlew test
```

At the moment, the repository does not include unit test sources under `src/test`, so this task mainly verifies that the test pipeline is configured.

## Project Context
- This is a compact Tetris + AI project focused on core gameplay and heuristic decision-making rather than full competitive-rule completeness.
- Current implementation notes:
	- no T-spin-specific logic
	- heuristic AI is deterministic by fixed weights (not self-training at runtime)
	- sprite loading in `MAIN` currently uses an absolute local path and may require adjustment for other machines

## License
This project is licensed under the MIT License. See [LICENSE.md](LICENSE.md). 
