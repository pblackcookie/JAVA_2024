package edu.uob;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.sql.Types.NULL;

public class OXOController implements KeyListener {
    OXOModel gameModel;
    public boolean gameEnded = false;

    public OXOController(OXOModel model) {
        gameModel = model;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException {
        while (gameEnded && (!gameModel.isGameDrawn())) {
            KeyEvent e1 = null;
            keyReleased(e1);
            break;
        }
        // Parse the command and update the game state accordingly
        String itsRow = command.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String itsCol = command.replaceAll("[^0-9]", "");
        int row = itsRow.toLowerCase().charAt(0) - 'a'; // Convert letter to row index
        int col = Integer.parseInt(itsCol) - 1; // Convert number to column index
        int gamePlayer = gameModel.getCurrentPlayerNumber();
        OXOPlayer nowPlayer = gameModel.getPlayerByNumber(gamePlayer);
        // row and col error
//        int min_row = 3, max_row = 9;
//        int min_col = 3, max_col = 9;
//        if(row < min_row || row > max_row){
//            throw new OXOMoveException.InvalidIdentifierCharacterException(OXOMoveException.RowOrColumn.ROW,(char)row);
//        }
//        if(col < min_col || col > max_col){
//            throw new OXOMoveException.InvalidIdentifierCharacterException(OXOMoveException.RowOrColumn.COLUMN,(char)col);
//        }
        // o x -> board -> set is null -> can set cell
        if (gameModel.getCellOwner(row,col)==null) {
            gameModel.setCellOwner(row, col, nowPlayer);
            // switch -> o & x (only two players)
            if (gamePlayer == 0) {
                gameModel.setCurrentPlayerNumber(1);
            } else {
                gameModel.setCurrentPlayerNumber(0);
            }

        }else{
            throw new OXOMoveException.CellAlreadyTakenException(row,col);
        }

        // TASK5 - Winner check
        OXOPlayer winPlayer = gameModel.horizonWin();
        if (winPlayer != null) {
            gameEnded = true;
        } else {
            winPlayer = gameModel.verticalWin();
            if (winPlayer != null) {
                gameEnded = true;
            } else {
                winPlayer = gameModel.diagonalWin();
                if (winPlayer != null) {
                    gameEnded = true;
                }
            }
        }
        // winner check -- game drawn
        if(winPlayer == null){
            //gameEnded = true;
            gameModel.isGameDrawn();
        }

    }
    public void addRow() {
        if(gameModel.getNumberOfRows()<9){
            gameModel.addRow();
        }
    }
    public void removeRow() {
        if(gameModel.getNumberOfRows()>3){
            gameModel.removeRow();
        }
    }
    public void addColumn() {
        if(gameModel.getNumberOfColumns()<9){
            gameModel.addColumn();
        }
    }
    public void removeColumn() {
        if(gameModel.getNumberOfColumns()>3){
            gameModel.removeColumn();
        }
    }
    public void increaseWinThreshold() {}
    public void decreaseWinThreshold() {}
    public void reset() {
    // get row& null -> if not null set now
        int row_length = gameModel.getNumberOfRows();
        int col_length = gameModel.getNumberOfColumns();
        for (int row = 0; row < row_length; row++) {
            for (int col = 0; col < col_length; col++) {
                gameModel.setCellOwner(row,col,null);
            }
        }
        // set current player 0; winner null; game drawn false;
        gameModel.setCurrentPlayerNumber(0);
        gameModel.setWinThreshold(0);
        gameModel.setGameDrawn(false);
        gameEnded = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {reset();}
    }
}
