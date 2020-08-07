public class Board {
    private int[][] board;

    public Board(int row, int col, int playerNum, int[][] g, boolean addPiece) {
        this.board = new int[6][7];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                this.board[r][c] = g[r][c];
            }
        }
        if (addPiece) {
            board[row][col] = playerNum;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public int findLowestEmptyRow(int c) {
        for (int r = board.length - 1; r >= 0; r--) {
            if (board[r][c] == 0) {
                return r;
            }
        }
        return -1;
    }

    public boolean checkForOneAwayFromWin(int f) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (row + 3 <= board.length - 1) {
                    int count = 0;
                    int empty = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[row + i][col] == f) {
                            count++;
                        } else if (board[row + i][col] == 0) {
                            empty++;
                        } else {
                            break;
                        }
                    }
                    if (count == 3 && empty == 1) return true;
                }
                if (col + 3 <= board[0].length - 1) {
                    int count = 0;
                    int empty = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[row][col + i] == f) {
                            count++;
                        } else if (board[row][col + i] == 0) {
                            empty++;
                        } else {
                            break;
                        }
                    }
                    if (count == 3 && empty == 1) return true;
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    int count = 0;
                    int empty = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[row - i][col - i] == f) {
                            count++;
                        } else if (board[row - i][col - i] == 0) {
                            empty++;
                        } else {
                            break;
                        }
                    }
                    if (count == 3 && empty == 1) return true;
                }
                if (col + 3 <= board[0].length - 1 && row - 3 >= 0) {
                    int count = 0;
                    int empty = 0;
                    for (int i = 0; i < 4; i++) {
                        if (board[row - i][col + i] == f) {
                            count++;
                        } else if (board[row - i][col + i] == 0) {
                            empty++;
                        } else {
                            break;
                        }
                    }
                    if (count == 3 && empty == 1) return true;
                }
            }
        }
        return false;
    }

    public boolean checkForWinner(int f) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (row + 3 <= board.length - 1) {
                    if (board[row][col] == f && board[row + 1][col] == f && board[row + 2][col] == f
                            && board[row + 3][col] == f) {
                        return true;
                    }
                }
                if (col + 3 <= board[0].length - 1) {
                    if (board[row][col] == f && board[row][col + 1] == f && board[row][col + 2] == f
                            && board[row][col + 3] == f) {
                        return true;
                    }
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    if (board[row][col] == f && board[row - 1][col - 1] == f && board[row - 2][col - 2] == f
                            && board[row - 3][col - 3] == f) {
                        return true;
                    }
                }
                if (col + 3 <= board[0].length - 1 && row - 3 >= 0) {
                    if (board[row][col] == f && board[row - 1][col + 1] == f && board[row - 2][col + 2] == f
                            && board[row - 3][col + 3] == f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkForWinner(int[][] grid, int f) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (row + 3 <= grid.length - 1) {
                    if (grid[row][col] == f && grid[row + 1][col] == f && grid[row + 2][col] == f
                            && grid[row + 3][col] == f) {
                        return true;
                    }
                }
                if (col + 3 <= grid[0].length - 1) {
                    if (grid[row][col] == f && grid[row][col + 1] == f && grid[row][col + 2] == f
                            && grid[row][col + 3] == f) {
                        return true;
                    }
                }
                if (col - 3 >= 0 && row - 3 >= 0) {
                    if (grid[row][col] == f && grid[row - 1][col - 1] == f && grid[row - 2][col - 2] == f
                            && grid[row - 3][col - 3] == f) {
                        return true;
                    }
                }
                if (col + 3 <= grid[0].length - 1 && row - 3 >= 0) {
                    if (grid[row][col] == f && grid[row - 1][col + 1] == f && grid[row - 2][col + 2] == f
                            && grid[row - 3][col + 3] == f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
