package com.spaghettic0der;


import java.util.ArrayList;

public class Player
{
    private ArrayList<Dice> remainingDices;
    private ArrayList<Dice> drawnDices;
    private int playerNumber;
    private final int TOTAL_DICE_NUMBER = 6;
    private int score = 0;
    private String playerName = null;

    public Player(int playerNumber)
    {
        remainingDices = new ArrayList<>();
        drawnDices = new ArrayList<>();
        initDice();
        this.playerNumber = playerNumber;
    }

    private void initDice()
    {
        for(int i=0; i < TOTAL_DICE_NUMBER; i++)
        {
            Dice dice = new Dice(i);
            dice.roll();
            remainingDices.add(dice);
        }
    }

    public void clearDices()
    {
        drawnDices.clear();
        remainingDices.clear();
        initDice();
    }

    public void rollDice()
    {
        for(int i=0; i < remainingDices.size(); i++)
        {
            remainingDices.get(i).roll();
        }

        for(int i=0; i < drawnDices.size(); i++)
        {
            drawnDices.get(i).setDiceDrawnThisRound(false);
        }
    }

    public ArrayList<Dice> getRemainingDices()
    {
        return remainingDices;
    }

    public ArrayList<Dice> getDrawnDices()
    {
        return drawnDices;
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public int getScore()
    {
        return score;
    }

    public void addToScore(int number)
    {
        score += number;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public String getPlayerName()
    {
        return playerName;
    }
}
