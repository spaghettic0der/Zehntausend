package com.spaghettic0der.zehntausend;


public class Settings
{
    private String settingsName = "";
    //oracle bug workaround
    private int minWidth = 800;
    private int minHeight = 800;
    private int width = 800;
    private int height = 800;

    //general settings
    private int totalDiceNumber = 6;
    private int totalPlayers = 3;
    private boolean diceImageShown = false;
    private boolean gameOverAfterFirstPlayerWon = false;

    private boolean streetEnabled = true;
    private int scoreStreet = 1500;

    private int minScoreRequiredToSaveInRound = 300;
    private int minScoreRequiredToWin = 300;

    private boolean threeXTwoEnabled = true; //2x3 -> 2,2,4,4,6,6 --> 1000 points
    private int scoreThreeXTwo = 1000;

    private boolean sixDicesInARowEnabled = true;
    private int scoreSixDicesInARow = minScoreRequiredToWin; //can be ajusted

    public int getMinWidth()
    {
        return minWidth;
    }

    public int getMinHeight()
    {
        return minHeight;
    }

    public boolean isDiceImageShown()
    {
        return diceImageShown;
    }

    public void setDiceImageShown(boolean diceImageShown)
    {
        this.diceImageShown = diceImageShown;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getTotalDiceNumber()
    {
        return totalDiceNumber;
    }

    public void setTotalDiceNumber(int totalDiceNumber)
    {
        this.totalDiceNumber = totalDiceNumber;
    }

    public int getTotalPlayers()
    {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers)
    {
        this.totalPlayers = totalPlayers;
    }

    public boolean isStreetEnabled()
    {
        return streetEnabled;
    }

    public void setStreetEnabled(boolean streetEnabled)
    {
        this.streetEnabled = streetEnabled;
    }

    public int getScoreStreet()
    {
        return scoreStreet;
    }

    public void setScoreStreet(int scoreStreet)
    {
        this.scoreStreet = scoreStreet;
    }

    public int getMinScoreRequiredToSaveInRound()
    {
        return minScoreRequiredToSaveInRound;
    }

    public void setMinScoreRequiredToSaveInRound(int minScoreRequiredToSaveInRound)
    {
        this.minScoreRequiredToSaveInRound = minScoreRequiredToSaveInRound;
    }

    public int getMinScoreRequiredToWin()
    {
        return minScoreRequiredToWin;
    }

    public void setMinScoreRequiredToWin(int minScoreRequiredToWin)
    {
        this.minScoreRequiredToWin = minScoreRequiredToWin;
    }

    public boolean isThreeXTwoEnabled()
    {
        return threeXTwoEnabled;
    }

    public void setThreeXTwoEnabled(boolean threeXTwoEnabled)
    {
        this.threeXTwoEnabled = threeXTwoEnabled;
    }

    public int getScoreThreeXTwo()
    {
        return scoreThreeXTwo;
    }

    public void setScoreThreeXTwo(int scoreThreeXTwo)
    {
        this.scoreThreeXTwo = scoreThreeXTwo;
    }

    public boolean isSixDicesInARowEnabled()
    {
        return sixDicesInARowEnabled;
    }

    public void setSixDicesInARowEnabled(boolean sixDicesInARowEnabled)
    {
        this.sixDicesInARowEnabled = sixDicesInARowEnabled;
    }

    public int getScoreSixDicesInARow()
    {
        return scoreSixDicesInARow;
    }

    public void setScoreSixDicesInARow(int scoreSixDicesInARow)
    {
        this.scoreSixDicesInARow = scoreSixDicesInARow;
    }

    public boolean isGameOverAfterFirstPlayerWon()
    {
        return gameOverAfterFirstPlayerWon;
    }

    public void setGameOverAfterFirstPlayerWon(boolean gameOverAfterFirstPlayerWon)
    {
        this.gameOverAfterFirstPlayerWon = gameOverAfterFirstPlayerWon;
    }

    public String getSettingsName()
    {
        return settingsName;
    }

    public void setSettingsName(String settingsName)
    {
        this.settingsName = settingsName;
    }
}