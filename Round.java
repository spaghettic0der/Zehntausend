package com.spaghettic0der;

import java.util.ArrayList;

public class Round
{
    private ArrayList<Roll> rollArrayList;

    public Round()
    {
        rollArrayList = new ArrayList<>();
    }

    public ArrayList<Roll> getRollArrayList()
    {
        return rollArrayList;
    }

    public void addToRound(Roll roll)
    {
        rollArrayList.add(roll);
    }

    public Roll getLastRoll()
    {
        Roll roll;
        if (rollArrayList.size() > 0)
        {
            roll = rollArrayList.get(rollArrayList.size() - 1);
        }
        else
        {
            roll = new Roll();
            rollArrayList.add(roll);
        }
        return roll;
    }

    public ArrayList<Dice> getDrawnDices()
    {
        ArrayList<Dice> dices = new ArrayList<>();

        for (Roll currentRoll : getRollArrayList())
        {
            for (Dice currentDice : currentRoll.getDrawnDices())
            {
                dices.add(currentDice);
            }
        }

        return dices;
    }

}
