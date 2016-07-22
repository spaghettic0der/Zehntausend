package com.spaghettic0der.zehntausend;


import java.util.ArrayList;

//saves all drawn dices
public class Roll
{

    private ArrayList<Dice> drawnDices;
    private boolean isConfirmingRoll = false;

    public Roll()
    {
        drawnDices = new ArrayList<>();
    }

    public ArrayList<Dice> getDrawnDices()
    {
        return drawnDices;
    }

    public void removeDiceWithNumber(int number)
    {
        for (Dice toRemove : getDrawnDices())
        {
            if (toRemove.getDiceNumber() == number)
            {
                getDrawnDices().remove(toRemove);
                break;
            }
        }
    }

    public void removeDice(Dice dice)
    {
        drawnDices.remove(dice);
    }

    public boolean isConfirmingRoll()
    {
        return isConfirmingRoll;
    }

    public void setConfirmingRoll(boolean confirmingRoll)
    {
        isConfirmingRoll = confirmingRoll;
    }
}
