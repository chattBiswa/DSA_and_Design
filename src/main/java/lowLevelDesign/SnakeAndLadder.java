package lowLevelDesign;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Game gameObj = new Game();
        gameObj.startGame();
    }
}

// Game
class Game {
    Board board;
    Dice dice;
    Queue<Player> players = new LinkedList<>();
    Player winner;

    Game(){
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }
    private void addPlayers(){
        Player p1 = new Player(1, 0);
        Player p2 = new Player(2, 0);
        players.add(p1);
        players.add(p2);
    }

    public void startGame(){
        while(winner == null){
            // find current player turn
            Player currentPlayer = findPlayerTurn();
            System.out.println("The current player turn is = " + currentPlayer.id);

            // roll the dice
            int rollDiceOutcome = dice.rollDice();

            // find player's new position
            System.out.println("Player = " + currentPlayer.id + " with current position = " + currentPlayer.currentPos);
            int newPos = currentPlayer.currentPos + rollDiceOutcome;
            newPos = jumpCheck(newPos);
            currentPlayer.currentPos = newPos;
            System.out.println("Player = " + currentPlayer.id + " with new position = " + currentPlayer.currentPos);

            // check winning condition
            if(newPos >= board.boardSize * board.boardSize -1)
                winner = currentPlayer;
        }

        System.out.println("The winner is Player = " + winner.id);
    }

    private int jumpCheck(int newPos){
        if(newPos >= board.boardSize * board.boardSize -1)
            return newPos;

        Cell jumpCell = board.getCell(newPos);
        if(jumpCell.jump != null && jumpCell.jump.start == newPos)
            return jumpCell.jump.end;

        return newPos;
    }

    private Player findPlayerTurn(){
        Player p = players.poll();
        players.offer(p);
        return p;
    }
}

// Board
class Board{
    Cell[][] cells;
    int boardSize;
    Board(int boardSize, int numOfSnake, int numOfLadder){
        this.boardSize = boardSize;
        initializeCells(boardSize);
        addSnakeAndLadder(numOfSnake, numOfLadder);
    }

    void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++)
            for(int j=0;j<boardSize;j++)
                cells[i][j]= new Cell();
    }

    void addSnakeAndLadder(int numOfSnake, int numOfLadder){
        while(numOfSnake > 0){
            int snakeStart = ThreadLocalRandom.current().nextInt(1, boardSize*boardSize -1);
            int snakeEnd = ThreadLocalRandom.current().nextInt(1, boardSize*boardSize -1);
            if(snakeStart < snakeEnd)
                continue;

            Jump jumpObj = new Jump();
            jumpObj.start = snakeStart;
            jumpObj.end = snakeEnd;

            Cell cell = getCell(snakeStart);
            cell.jump = jumpObj;

            numOfSnake--;
        }

        while(numOfLadder > 0){
            int LadderStart = ThreadLocalRandom.current().nextInt(1, boardSize*boardSize -1);
            int LadderEnd = ThreadLocalRandom.current().nextInt(1, boardSize*boardSize -1);
            if(LadderStart > LadderEnd)
                continue;

            Jump jumpObj = new Jump();
            jumpObj.start = LadderStart;
            jumpObj.end = LadderEnd;

            Cell cell = getCell(LadderStart);
            cell.jump = jumpObj;

            numOfLadder--;

        }
    }

    Cell getCell(int start){
        int cellRow = start / boardSize;
        int cellCol = start % boardSize;
        return cells[cellRow][cellCol];
    }
}

// Cell
class Cell{
    Jump jump;
}

// Jump
class Jump{
    int start;
    int end;
}

// Dice
class Dice{
    int diceCount;
    Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(){
        int diceCounter=0;
        int diceSum=0;
        while(diceCounter<diceCount){
            diceSum = ThreadLocalRandom.current().nextInt(1,6);
            diceCounter++;
        }
        return diceSum;
    }
}

// Player
class Player{
    int id;
    int currentPos;
    Player(int id, int currentPos){
        this.id = id;
        this.currentPos = currentPos;
    }
}
