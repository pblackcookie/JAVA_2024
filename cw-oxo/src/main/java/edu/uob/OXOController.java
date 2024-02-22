package edu.uob;

public class OXOController {
    OXOModel gameModel;

    public OXOController(OXOModel model) {
        gameModel = model;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException {
        // Parse the command and update the game state accordingly
        int row = command.toLowerCase().charAt(0) - 'a'; // Convert letter to row index
        int col = Character.getNumericValue(command.charAt(1)) - 1; // Convert number to column index
        int gamePlayer = gameModel.getCurrentPlayerNumber();
        OXOPlayer nowPlayer = gameModel.getPlayerByNumber(gamePlayer);
        // o x -> board -> set
        gameModel.setCellOwner(row,col,nowPlayer);
        // switch -> o & x (only 两个玩家)
        if (gamePlayer == 0){
            gameModel.setCurrentPlayerNumber(1);
        }else{
            gameModel.setCurrentPlayerNumber(0);
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
    }
}
