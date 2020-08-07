import java.util.ArrayList;

public class AI {
    private Connect4 game;
    private int computerPlayer, playerTurn;

    public AI(Connect4 game, int move) {
        this.game = game;
        this.computerPlayer = move;
        this.playerTurn = game.toggle(move);
    }

    public boolean computerMove() {
        Board g = new Board(0, 0, computerPlayer, game.getGrid(), false);
        int w = getBestMove(g, 0, 6, true);
        if (w == -1 || w >= game.grid[0].length) {
            return false;
        }
        int d = game.findLowestEmptyRow(w);
        if (d == -1) {
            return false;
        }
        game.getGrid()[d][w] = computerPlayer;
        game.setGameOver(Board.checkForWinner(game.getGrid(), computerPlayer));
        if (game.isGameOver()) {
            game.setWinner(computerPlayer);
        }
        game.setCurrentPlayerTurn(game.toggle(game.getCurrentPlayerTurn()));
        return true;
    }

    public int getBestMove(Board boardstate, int depth, int maxDepth, boolean maximizingPlayer) {
        if (depth == maxDepth) {
            return (1 + maxDepth - depth) * findHeuristic(boardstate);
        }
        int bestValueSoFar, value;
        int bestMove = -1;
        Board nextBoardState;
        if (maximizingPlayer) {
            bestValueSoFar = -11 * maxDepth;
            ArrayList<Integer> validMoves = findAllValidMoves(boardstate);
            for (int i = 0; i < validMoves.size(); i++) {
                nextBoardState = new Board(boardstate.findLowestEmptyRow(validMoves.get(i)), validMoves.get(i), computerPlayer,
                        boardstate.getBoard(), true);
                if ((1 + maxDepth - depth) * findHeuristic(nextBoardState) >= 10) {
                    bestValueSoFar = (1 + maxDepth - depth) * 10;
                    bestMove = validMoves.get(i);
                    if (depth == 0) {
                        return bestMove;
                    } else {
                        return bestValueSoFar;
                    }
                }
                value = getBestMove(nextBoardState, depth + 1, maxDepth, false);                 //
                if (value > bestValueSoFar) {
                    bestValueSoFar = value;
                    bestMove = validMoves.get(i);
                }
                if (value == bestValueSoFar) {
                    if (Math.abs(bestMove - 3) > Math.abs(validMoves.get(i) - 3)) {
                        if (boardstate.findLowestEmptyRow(validMoves.get(i)) >= game.getCols() / 2) {
                            bestMove = validMoves.get(i);
                        }
                    }
                }
            }
            if (depth == 0) {
                return bestMove;
            } else {
                return bestValueSoFar;
            }
        } else {
            bestValueSoFar = 11 * maxDepth;
            ArrayList<Integer> validMoves = findAllValidMoves(boardstate);
            for (int i = 0; i < validMoves.size(); i++) {
                nextBoardState = new Board(boardstate.findLowestEmptyRow(validMoves.get(i)), validMoves.get(i), playerTurn,
                        boardstate.getBoard(), true);
                if ((1 + maxDepth - depth) * findHeuristic(nextBoardState) <= -10) {
                    bestValueSoFar = (1 + maxDepth - depth) * -10;
                    bestMove = validMoves.get(i);
                    if (depth == 0) {
                        return bestMove;
                    } else {
                        return bestValueSoFar;
                    }
                }
                value = getBestMove(nextBoardState, depth + 1, maxDepth, true);
                if (value < bestValueSoFar) {

                    bestValueSoFar = value;
                    bestMove = validMoves.get(i);
                }
                if (value == bestValueSoFar) {
                    if (Math.abs(bestMove - 3) > Math.abs(validMoves.get(i) - 3)) {
                        if (boardstate.findLowestEmptyRow(validMoves.get(i)) >= game.getCols() / 2) {
                            bestMove = validMoves.get(i);
                        }
                    }
                }
            }
            if (depth == 0) {
                return bestMove;
            } else {
                return bestValueSoFar;
            }
        }
    }

    public int findHeuristic(Board boardstate) {
        if (boardstate.checkForWinner(playerTurn)) return -10;
        if (boardstate.checkForWinner(computerPlayer)) return 10;
        if (boardstate.checkForOneAwayFromWin(playerTurn)) return -1;
        if (boardstate.checkForOneAwayFromWin(computerPlayer)) return 1;
        return 0;
    }

    public ArrayList<Integer> findAllValidMoves(Board boardstate) {
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int i = 0; i < game.getGrid()[0].length; i++) {
            int t = boardstate.findLowestEmptyRow(i);
            if (t != -1) {
                validMoves.add(i);
            }
        }
        return validMoves;
    }

    public boolean computerMove2() {
        Board g = new Board(0, 0, playerTurn, game.getGrid(), false);
        int w = getBestMove(g, 0, 4, true);
        if (w == -1 || w >= game.grid[0].length) {
            return false;
        }
        int d = game.findLowestEmptyRow(w);
        if (d == -1) {
            return false;
        }
        game.getGrid()[d][w] = playerTurn;
        game.setGameOver(Board.checkForWinner(game.getGrid(), playerTurn));
        if (game.isGameOver()) {
            game.setWinner(playerTurn);
        }
        game.setCurrentPlayerTurn(game.toggle(game.getCurrentPlayerTurn()));
        return true;
    }
}
