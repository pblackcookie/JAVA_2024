package edu.uob;

import java.util.ArrayList;

public class OXOModel {

    private ArrayList<ArrayList<OXOPlayer>> cells;
    private OXOPlayer[] players;
    private int currentPlayerNumber;
    private OXOPlayer winner;
    private boolean gameDrawn;
    private int winThreshold;

    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh) {
        winThreshold = winThresh;
//        cells = new OXOPlayer[numberOfRows][numberOfColumns];
        cells = new ArrayList<>(numberOfRows);
        for (int i = 0; i < numberOfColumns; i++){
            ArrayList<OXOPlayer> row = new ArrayList<>(numberOfColumns);
            for(int j = 0; j < numberOfColumns; j++){
                row.add(null);
            }
            cells.add(row);
        }
        players = new OXOPlayer[2];
    }

    public int getNumberOfPlayers() {
        return players.length;
    }

    public void addPlayer(OXOPlayer player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public OXOPlayer getPlayerByNumber(int number) {
        return players[number];
    }

    public OXOPlayer getWinner() {
        return winner;
    }

    public void setWinner(OXOPlayer player) {
        winner = player;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayerNumber;
    }

    public void setCurrentPlayerNumber(int playerNumber) {
        currentPlayerNumber = playerNumber;
    }

    public int getNumberOfRows() {
        return cells.size();
    }

    public int getNumberOfColumns() {
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber) {
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player) {
        cells.get(rowNumber).set(colNumber, player);
    }

    public void setWinThreshold(int winThresh) {
        winThreshold = winThresh;
    }

    public int getWinThreshold() {
        return winThreshold;
    }

    public void setGameDrawn(boolean isDrawn) {
        gameDrawn = isDrawn;
    }

    public boolean isGameDrawn() {
        return gameDrawn;
    }

    public void addRow(){
        int numColumns = getNumberOfColumns();
        ArrayList<OXOPlayer> newRow = new ArrayList<>(numColumns);
        for (int j = 0; j < numColumns; j++) {
            newRow.add(null); // 初始化新行的单元格为null
        }
        cells.add(newRow);
    }

    public void addColumn(){
        for (ArrayList<OXOPlayer> row : cells) {
            row.add(null); // 添加新列，初始化为null
        }
    }

    public void removeRow(){
//        if (!cells.isEmpty()) {
//            cells.remove(cells.size() - 1); // 从末尾开始移除行
//        }
        if (!cells.isEmpty()) {
            ArrayList<OXOPlayer> lastRow = cells.get(cells.size() - 1);
            boolean isEmpty = true;
            for (OXOPlayer cell : lastRow) {
                if (cell != null) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                cells.remove(cells.size() - 1); // 从末尾开始移除行
            }
        }
    }

    public void removeColumn() {
        if (!cells.isEmpty()) {
            boolean isEmpty = true;
            for (ArrayList<OXOPlayer> row : cells) {
                if (!row.isEmpty()) {
                    OXOPlayer lastCell = row.get(row.size() - 1);
                    if (lastCell != null) {
                        isEmpty = false;
                        break;
                    }
                }
            }
            if (isEmpty) {
                for (ArrayList<OXOPlayer> row : cells) {
                    if (!row.isEmpty()) {
                        row.remove(row.size() - 1); // 从末尾开始移除列
                    }
                }
            }
        }
    }
    public OXOPlayer horizonWin() {
        int tot_row = getNumberOfRows();
        int tot_col = getNumberOfColumns();
        // 以短边作为赢的条件 -- min函数
        setWinThreshold(Math.min(tot_col, tot_row));
        // For循环 - 在一row里连续六个-胜利阈值是每一列都有连续的
        for (int i = 0; i < tot_row; i++) {
            for (int j = 0; j < tot_col; j++) {
                if (getCellOwner(i, j) != null) {
                    int win_cnt = 1;
                    for (int k = 1; k < tot_col - j; k++) {
                        int nxrgt = j + k; // 他右边的应该+k 而不是+1
                        if (nxrgt > tot_col || (getCellOwner(i, j) != getCellOwner(i, nxrgt))) {
                            break;
                        }
                        win_cnt++;
                    }
                    if (win_cnt >= getWinThreshold()) {
                        setWinner(getCellOwner(i, j));
                        return getWinner();
                    }
                }
            }
        }
        return null;
    }
    public OXOPlayer verticalWin() {
        int tot_row = getNumberOfRows();
        int tot_col = getNumberOfColumns();
        // 以短边作为赢的条件 -- min函数
        setWinThreshold(Math.min(tot_col, tot_row));
        // For循环 - 在一row里连续六个-胜利阈值是每一行都有连续的
        for (int i = 0; i < tot_col; i++) {
            for (int j = 0; j < tot_row; j++) {
                if (getCellOwner(j, i) != null) {
                    int win_cnt = 1;
                    for (int k = 1; k < tot_row - j; k++) {
                        int nxblw = j + k; // 他右边的应该+k 而不是+1
                        if (nxblw > tot_row || (getCellOwner(i, j) != getCellOwner(i, nxblw))) {
                            break;
                        }
                        win_cnt++;
                    }
                    if (win_cnt >= getWinThreshold()) {
                        setWinner(getCellOwner(j, i));
                        return getWinner();
                    }
                }
            }
        }
        return null;
    }
    public OXOPlayer diagonalWin() {
        return null;
    }
}
