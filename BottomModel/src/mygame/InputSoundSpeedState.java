/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import com.jme3.app.state.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.ColorRGBA;

/**
 * InputSoundSpeedState is app state that read user input for sound of speed
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class InputSoundSpeedState extends BaseAppState 
                                  implements ActionListener {
       
    private SimpleApplication app;
    private AssetManager assetManager;
    private AppStateManager stateManager;
    private InputManager inputManager;
    
    private BitmapText enterSpeedText;
    
    private final int screenWidth;
    private final int screenHeight;
    
        
    /** Constructs input sound speed state
     * @param width width of screen
     * @param height height of screen
     */
    public InputSoundSpeedState(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }
    
    /**
     * @param tpf time per frame value
     */
    @Override
    public void update(float tpf) {}
    
    @Override
    public void cleanup(Application app) {}
    
    /** Initializates iput sound speed state
     * @param app the application to which the state is attached
     */
    @Override
    public void initialize(Application app) {
        this.app          = (SimpleApplication) app;
        this.assetManager = this.app.getAssetManager();
        this.stateManager = this.app.getStateManager();
        this.inputManager = this.app.getInputManager();

        this.app.getViewPort().setBackgroundColor(ColorRGBA.Blue);
        BitmapFont guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        enterSpeedText = new BitmapText(guiFont, false);
        enterSpeedText.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        enterSpeedText.setText("Enter speed of sound: 350");
        enterSpeedText.setLocalTranslation(screenWidth / 2 - enterSpeedText.getLineWidth() / 2,
                               enterSpeedText.getLineHeight() / 2 + screenHeight / 2, 0);
        enterSpeedText.setColor(ColorRGBA.Black);
        this.app.getGuiNode().attachChild(enterSpeedText);
        
        setUpKeys();
    }
    
    /** Detaches text with sound of speed from scene */
    @Override
    public void onDisable() {
        this.app.getGuiNode().detachChild(enterSpeedText);
    }
    
    @Override
    public void onEnable() {}
    
    /** processes actions performed by user
     * @param binding action's name
     * @param value is action started or finished
     * @param tpf time per frame value
     */
    @Override
    public void onAction(String binding, boolean value, float tpf) {
        if (!value) {
            return;
        }
        if ("Enter".equals(binding)) {
            int soundSpeed = 1;
            String speedStr = enterSpeedText.getText().substring(22);
            if (!(speedStr.equals("0") || 
                    speedStr.equals("00") ||
                    speedStr.equals("000") ||
                    speedStr.length() == 0)) {
                soundSpeed = Integer.parseInt(speedStr);
            }
            WorldManagerState worldManagerState = new WorldManagerState(soundSpeed);
            stateManager.attach(worldManagerState);
            PhysicsState physicsState = new PhysicsState();
            stateManager.attach(physicsState);
            stateManager.detach(this);
            return;
        }
        if ("Delete".equals(binding)) {
            int length = enterSpeedText.getText().length();
            if (length > 22) {
                enterSpeedText.setText(enterSpeedText.getText().substring(0, length -1));
                return;
            }
        }
        String str = new String();
        switch (binding) {
        case "1":
            str = "1";
            break;
        case "2":
            str = "2";
            break;
        case "3":
            str = "3";
            break;
        case "4":
            str = "4";
            break;
        case "5":
            str = "5";
            break;
        case "6":
            str = "6";
            break;
        case "7":
            str = "7";
            break;
        case "8":
            str = "8";
            break;
        case "9":
            str = "9";
            break;
        case "0":
            str = "0";
            break;
        default:
            break;
        }
        if (enterSpeedText.getText().length() < 25) {
            enterSpeedText.setText(enterSpeedText.getText() + str);
        }
    }
    
    /** sets mappings for key triggers */
    private void setUpKeys() {
        inputManager.addMapping("1", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("2", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("3", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping("4", new KeyTrigger(KeyInput.KEY_4));
        inputManager.addMapping("5", new KeyTrigger(KeyInput.KEY_5));
        inputManager.addMapping("6", new KeyTrigger(KeyInput.KEY_6));
        inputManager.addMapping("7", new KeyTrigger(KeyInput.KEY_7));
        inputManager.addMapping("8", new KeyTrigger(KeyInput.KEY_8));
        inputManager.addMapping("9", new KeyTrigger(KeyInput.KEY_9));
        inputManager.addMapping("0", new KeyTrigger(KeyInput.KEY_0));
        inputManager.addMapping("Enter", new KeyTrigger(KeyInput.KEY_RETURN), 
                                         new KeyTrigger(KeyInput.KEY_NUMPADENTER));
        inputManager.addMapping("Delete", new KeyTrigger(KeyInput.KEY_BACK),
                                          new KeyTrigger(KeyInput.KEY_DELETE));
        inputManager.addListener(this, "1");
        inputManager.addListener(this, "2");
        inputManager.addListener(this, "3");
        inputManager.addListener(this, "4");
        inputManager.addListener(this, "5");
        inputManager.addListener(this, "6");
        inputManager.addListener(this, "7");
        inputManager.addListener(this, "8");
        inputManager.addListener(this, "9");
        inputManager.addListener(this, "0");
        inputManager.addListener(this, "Enter");
        inputManager.addListener(this, "Delete");
    }
}