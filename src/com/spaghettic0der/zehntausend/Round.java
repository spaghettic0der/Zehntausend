package com.spaghettic0der.zehntausend;

import java.util.ArrayList;

public class Round
{
    private ArrayList<Roll> rollArrayList;
    private boolean isConfirmingRound = false;

    public Round()
    {
        rollArrayList = new ArrayList<>();
        nextRoll();
    }

    public ArrayList<Roll> getRollArrayList()
    {
        return rollArrayList;
    }

    public boolean isValid()
    {
        for (Roll currentRoll : rollArrayList)
        {
            if (currentRoll.getDrawnDices().isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public Roll getCurrentRoll()
    {
        return rollArrayList.get(rollArrayList.size() - 1);
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

    public void nextRoll()
    {
        Roll roll = new Roll();
        rollArrayList.add(roll);
    }

    public boolean isConfirmingRound()
    {
        return isConfirmingRound;
    }

    public void setConfirmingRound(boolean confirmingRound)
    {
        isConfirmingRound = confirmingRound;
    }
}