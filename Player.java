package com.spaghettic0der;


import java.util.ArrayList;


public class Player
{
    private ArrayList<Dice> remainingDices;
    private int playerNumber;
    private int score = 0;
    private String playerName = null;
    private ArrayList<Turn> turnArrayList;
    private Settings settings;

    public Player(int playerNumber, Settings settings)
    {
        playerName = "Player " + (playerNumber + 1);
        turnArrayList = new ArrayList<>();
        remainingDices = new ArrayList<>();
        this.settings = settings;
        initDice();
        this.playerNumber = playerNumber;
        nextTurn();
    }

    public void initDice()
    {
        remainingDices.clear();
        for (int i = 0; i < settings.getTotalDiceNumber(); i++)
        {
            Dice dice = new Dice();
            dice.roll();
            remainingDices.add(dice);
        }
    }


    public Turn getCurrentTurn()
    {
        return turnArrayList.get(turnArrayList.size() - 1);
    }

    public void rollDice()
    {
        if (!remainingDices.isEmpty())
        {
            for (int i = 0; i < remainingDices.size(); i++)
            {
                remainingDices.get(i).roll();
            }
            getCurrentTurn().getCurrentRound().nextRoll();
        }
        else
        {
            getCurrentTurn().nextRound();
            initDice();
            //getCurrentTurn().getCurrentRound().addToRound(roll);
            //clears dices on board and re_init them again
            //incase you finish the roll
        }
    }

    public ArrayList<Dice> getRemainingDices()
    {
        return remainingDices;
    }


    public void nextTurn()
    {
        Turn turn = new Turn();
        turnArrayList.add(turn);
    }

    public int getPlayerNumber()
    {
        return playerNumber;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void addToScore(int number)
    {
        score += number;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public ArrayList<Turn> getTurnArrayList()
    {
        return turnArrayList;
    }
}
