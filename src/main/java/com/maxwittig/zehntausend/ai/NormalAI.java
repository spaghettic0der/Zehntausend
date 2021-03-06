package com.maxwittig.zehntausend.ai;

import com.maxwittig.zehntausend.Main;
import com.maxwittig.zehntausend.extras.Settings;
import com.maxwittig.zehntausend.gamelogic.Game;
import com.maxwittig.zehntausend.gamelogic.Scoring;
import com.maxwittig.zehntausend.Main;
import com.maxwittig.zehntausend.extras.Settings;
import com.maxwittig.zehntausend.gamelogic.Game;
import com.maxwittig.zehntausend.gamelogic.Scoring;

public class NormalAI extends AI {

    public NormalAI(int playerNumber, Settings settings, Game game) {
        super(playerNumber, settings, game);
        //noRisk();
    }

    @Override
    protected AIType getAiType() {
        return AIType.NORMAL;
    }

    @Override
    public String getPlayerName() {
        return Main.language.getAI() + " " + Main.language.getNormal() + " " + (playerNumber + 1);
    }

    @Override
    boolean drawIsPossible() {
        return (Scoring.containsMultiple(remainingDices) || containsOneOrFive(remainingDices));
    }


    @Override
    protected void drawDices() {
        drawStreet();
        drawMultiple(rollAfterYouDrawnMultiple, diceNumberWhereItMakesSenseToRiskRerolling);
        draw5And1(drawOnlyOne);

    }


}
