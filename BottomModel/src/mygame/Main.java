/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import com.jme3.app.SimpleApplication;

/**
 * Main is entry point class
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class Main extends SimpleApplication {
    
    /** application entry point
     * @param args arguments from command prompt
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    /** initializes application */
    @Override
    public void simpleInitApp() {    
        setDisplayFps(false);
        setDisplayStatView(false);
        
        InputSoundSpeedState inputState = new InputSoundSpeedState(settings.getWidth(),
                                                                   settings.getHeight());
        stateManager.attach(inputState);
    }

    /**
     * @param tpf time per frame value
     */
    @Override
    public void simpleUpdate(float tpf) {}
}