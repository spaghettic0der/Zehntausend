package com.spaghettic0der.zehntausend.AI;

public enum AIType
{
    EASY,
    NORMAL,
    HARD;

    private static AIType[] aiTypes = values();

    public AIType getNext()
    {
        return aiTypes[(this.ordinal() + 1) % aiTypes.length];
    }
}

