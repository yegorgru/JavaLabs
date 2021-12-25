/*
 * Yehor Hrushevyy
 *
 * Copyright (c) All Rights Reserved.
 */

package mygame;

import com.jme3.app.state.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import java.util.ArrayList;
import java.util.List;

/**
 * PhysicsState is app state that is responsible for
 * movement, falling and collision of player
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class PhysicsState extends BaseAppState implements ActionListener {
    
    private SimpleApplication app;
    private Camera cam;
    private InputManager inputManager;
    
    private CharacterControl player;
    private TerrainQuad terrain;
    
    private final Vector3f walkDirection = new Vector3f();
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    
    BulletAppState bulletAppState;
    
    /** updates position of player
     * @param tpf time per frame value
     */
    @Override
    public void update(float tpf) {
        player.setWalkDirection(updateWalkDirection(left, right, up, down,
                                        cam.getDirection().clone().multLocal(0.6f),
                                        cam.getLeft().clone().multLocal(0.4f)));
        cam.setLocation(player.getPhysicsLocation());
    }
    
    public Vector3f updateWalkDirection(boolean left, boolean right, 
                                        boolean up, boolean down, 
                                        Vector3f camDir, Vector3f camLeft) {
        walkDirection.set(0, 0, 0);
        if (left) { 
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (up) {
            walkDirection.addLocal(camDir);
        }
        if (down) {
            walkDirection.addLocal(camDir.negate());
        }
        return walkDirection;
    }
    
    @Override
    public void cleanup(Application app) {}
    
    /** Initializates world manager state
     * @param app the application to which the state is attached
     */
    @Override
    public void initialize(Application app) {    
        this.app          = (SimpleApplication) app;
        this.cam          = this.app.getCamera();
        this.inputManager = this.app.getInputManager();
        AppStateManager stateManager = this.app.getStateManager();
        WorldManagerState worldManager = stateManager.getState(WorldManagerState.class);
        this.terrain = worldManager.getTerrain();
        
        this.left = false;
        this.right = false;
        this.down = false;
        this.up = false;
        
        setUpKeys();
        
        bulletAppState = new BulletAppState();
        app.getStateManager().attach(bulletAppState);

        addTerrainPhysics();
        addPlayerPhysics();
    }
    
    @Override
    public void onDisable() {}
    
    @Override
    public void onEnable() {}
        
    /** processes actions performed by user
     * @param binding action's name
     * @param value is action started or finished
     * @param tpf time per frame value
     */
    @Override
    public void onAction(String binding, boolean value, float tpf) {
        switch (binding) {
        case "Left":
            left = value;
            break;
        case "Right":
            right = value;
            break;
        case "Up":
            up = value;
            break;
        case "Down":
            down = value;
            break;
        case "Jump":
            player.jump(new Vector3f(0,20f,0));
            break;
        default:
            break;
        }
    }
    
    /** sets mappings for key triggers */
    private void setUpKeys() {
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, "Left");
        inputManager.addListener(this, "Right");
        inputManager.addListener(this, "Up");
        inputManager.addListener(this, "Down");
        inputManager.addListener(this, "Jump");
    }
    
    /** sets collision model for terrain */
    private void addTerrainPhysics() {
        List<Camera> cameras = new ArrayList<>();
        cameras.add(this.cam);
        
        TerrainLodControl control = new TerrainLodControl(terrain, cameras);
        terrain.addControl(control);

        terrain.addControl(new RigidBodyControl(0));
        
        bulletAppState.getPhysicsSpace().add(terrain);
    }
    
    /** sets collision model for player */
    private void addPlayerPhysics() {
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);

        player.setPhysicsLocation(new Vector3f(-10, 200, 10));
        player.setGravity(new Vector3f(0,-30f,0));

        bulletAppState.getPhysicsSpace().add(player);
    }
}
