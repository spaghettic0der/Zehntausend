package com.maxwittig.zehntausend.ui;

import com.maxwittig.zehntausend.ai.AIType;
import javafx.scene.control.Button;

public class AIButton extends Button {
    private AIType currentAIType;

    public AIButton(AIType currentAIType) {
        this.currentAIType = currentAIType;
        setText(currentAIType.toString());
        setId(currentAIType.toString());
    }

    public void nextType() {
        currentAIType = currentAIType.getNext();
        setId(currentAIType.toString());
        setText(currentAIType.toString());
    }

}
