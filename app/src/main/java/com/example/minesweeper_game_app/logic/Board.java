package com.example.minesweeper_game_app.logic;

import java.util.Random;

public class Board {
    private Cell[] mCells;
    private int mSize;

    public Board(int mSize) {

        setmSize(mSize);
        this.mCells = new Cell[this.mSize * this.mSize];
        CreateNewBoard();
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public void CreateNewBoard() {
        //create cells
        for (int i = 0; i < this.mSize * this.mSize; i++)
            this.mCells[i] = new Cell();
        //put mines at random positions
        Random r = new Random();
        int minePos;

        for (int i = 0; i < 1 ; i++) {
            minePos = r.nextInt(this.mSize * this.mSize);

            if (mCells[minePos].isMined())
                i--;
            else
                mCells[minePos].setMined(true);
        }
    }

    public Boolean OnClick(int pos, boolean isFlagged) {
        int minesAround = CheckHowManyMinesAround(pos);

        Boolean isMined = mCells[pos].OnClick(isFlagged, minesAround);
        if(isMined==null)
            return null;
        if (!isMined && minesAround == 0) {
            OnClickAround(pos);
        }

        return isMined;
    }


    private void OnClickAround(int pos) {
        if (isCellValid(pos,pos + 1)) {
            OnClick(pos + 1, false);
        }
        if (isCellValid(pos,pos - 1)) {
            OnClick(pos - 1, false);
        }
        if (isCellValid(pos,pos + 1 + this.mSize)) {
            OnClick(pos + 1 + this.mSize, false);
        }
        if (isCellValid(pos,pos + this.mSize)) {
            OnClick(pos + this.mSize, false);
        }
        if (isCellValid(pos,pos - this.mSize)) {
            OnClick(pos - this.mSize, false);
        }
        if (isCellValid(pos,pos + 1 - this.mSize)) {
            OnClick(pos + 1 - this.mSize, false);
        }
        if (isCellValid(pos,pos - 1 - this.mSize)) {
            OnClick(pos - 1 - this.mSize, false);
        }
        if (isCellValid(pos,pos - 1 + this.mSize)) {
            OnClick(pos - 1 + this.mSize, false);
        }
    }

    public int getBoardSize() {
        return this.mCells.length;
    }

    public Cell getCell(int pos) {
        return mCells[pos];
    }

    private int CheckHowManyMinesAround(int pos) {
        int minesCount = 0;

        if (isCellValid(pos, pos + 1))
            if (mCells[pos + 1].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1))
            if (mCells[pos - 1].isMined())
                minesCount++;
        if (isCellValid(pos, pos + 1 + this.mSize))
            if (mCells[pos + 1 + this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos + this.mSize))
            if (mCells[pos + this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - this.mSize))
            if (mCells[pos - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos + 1 - this.mSize))
            if (mCells[pos + 1 - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1 - this.mSize))
            if (mCells[pos - 1 - this.mSize].isMined())
                minesCount++;
        if (isCellValid(pos, pos - 1 + this.mSize))
            if (mCells[pos - 1 + this.mSize].isMined())
                minesCount++;

        return minesCount;
    }

    public boolean isCellValid(int pos, int nextPos) {

        int row = pos / this.mSize;
        int col = pos % this.mSize;
        int newRow = nextPos / this.mSize;
        int newCol = nextPos % this.mSize;

        if (nextPos < 0 || nextPos >= this.mCells.length){
            return false;
        }
        if(newRow < 0 || newRow >= this.mSize){
            return false;
        }
        if (newCol < 0 || newCol >= this.mSize){
            return false;
        }
        if (col == this.mSize - 1 && newCol == 0){
            return false;
        }
        if (col == 0 && newCol == this.mSize - 1){
            return false;
        }
        return true;
    }
}
