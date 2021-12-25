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
import com.jme3.audio.AudioNode;
import com.jme3.audio.Listener;
import com.jme3.audio.LowPassFilter;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;

import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.water.WaterFilter;
import java.util.ArrayList;

/**
 * WorldManagerState is app state that is responsible for
 * loading and setting up of assets
 *
 * @author  Yehor Hrushevyy
 * @version 1.0 2021-10-03 
 */
public class WorldManagerState extends BaseAppState {
    
    private SimpleApplication app;
    private Node rootNode;
    private AssetManager assetManager;
    private Listener listener;
    private Camera cam;
    private ViewPort viewPort;
    
    private final int soundSpeed;
    
    private Node reflectedScene; 
    private TerrainQuad terrain;
    
    private WaterFilter water;
    private AudioNode wavesAudio;
    private boolean wasUnderwater = false;
    
    public WorldManagerState(int soundSpeed) {
        this.soundSpeed = soundSpeed;
    }
    
    @Override
    public void update(float tpf) {
        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        if (water.isUnderWater() && !wasUnderwater ) {
            wasUnderwater = true;
            wavesAudio.setDryFilter(new LowPassFilter(0.5f, 0.1f));
        } else if (!water.isUnderWater() && wasUnderwater ) {
            wasUnderwater = false;
            wavesAudio.setDryFilter(new LowPassFilter(1f, 1f));
        }
    }
    
    @Override
    public void cleanup(Application app) {}
    
    /*
     * initializes world manager app state
     */
    @Override
    public void initialize(Application app) {
        this.app = (SimpleApplication) app;
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.viewPort = this.app.getViewPort();
        this.listener = this.app.getListener();
        this.cam = this.app.getCamera();
        
        loadTerrain();
        loadBackground();
        loadWater();
        loadLightning();
        loadAudio();
        
        reflectedScene = new Node("Scene");
        rootNode.attachChild(reflectedScene);
    }
    
    @Override
    public void onDisable() {}
    
    @Override
    public void onEnable() {}
    
    /* gets terrain,
     * @return TerrainQuad terrain
     */
    public TerrainQuad getTerrain() {
        return terrain;
    }
    
    /* loads terrain,
     * sets heightmap
     */
    private void loadTerrain() {
        Material matTerrain = new Material(assetManager,
                "Common/MatDefs/Light/Lighting.j3md");

        matTerrain.setTexture("DiffuseMap", assetManager.loadTexture(
            "Textures/Terrain/splat/dirt.jpg"));
        
        matTerrain.setBoolean("UseMaterialColors", true);
        matTerrain.setColor("Ambient", ColorRGBA.Gray );
        matTerrain.setColor("Diffuse", ColorRGBA.Brown );
        matTerrain.setColor("Specular", ColorRGBA.White );
        matTerrain.setFloat("Shininess", 8f);
        
        Texture heightMapImage = assetManager.loadTexture(
            "Textures/map.png");
        AbstractHeightMap heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
        
        String path = System.getProperty("user.dir");
        path += "\\assets\\ExcelTables\\heights.xlsx";
        ArrayList<HeightPoint> points = HeightsReader.getHeights(path, soundSpeed);
        float averageHeight = calculateAverageHeight(points);
        for (int i = 0; i < 128; i++){
            for (int j = 0; j < 128; j++){
                if (i == 0 || i == 127 || j == 0 || j == 127) {
                    heightmap.setHeightAtPoint(255, i, j);
                } else {
                    heightmap.setHeightAtPoint(averageHeight, i, j);
                }
            }
        }
        for (HeightPoint point : points) {
            if(point.isValid()) {
                for (int i = Math.max(1, point.getX()-3);
                         i < Math.min(126, point.getX() + 3);
                         i++) {
                    for (int j = Math.max(1, point.getZ()-3);
                             j < Math.min(126, point.getZ() + 3);
                             j++) {
                        heightmap.setHeightAtPoint((float)point.getHeight(), i, j);
                    }
                }
            }
        }
        
        terrain = new TerrainQuad("my terrain", 65, 129, heightmap.getHeightMap());

        terrain.setMaterial(matTerrain);
        terrain.setLocalTranslation(0, -100, 0);
        terrain.setLocalScale(2f, 1f, 2f);
        
        rootNode.attachChild(terrain);
    }
    
    /* calculates average height on heightmap
     * @param points height points of heightmap
     * @return float average height on heightmap 
     */
    private float calculateAverageHeight(ArrayList<HeightPoint> points) {
        double total = 0;
        for (HeightPoint point : points) {
            total += point.getHeight();
        }
        return (float) total/points.size();
    }
    
    /* loads background scene */
    private void loadBackground() {
        Node sceneNode = new Node("Scene");
        sceneNode.attachChild(SkyFactory.createSky(assetManager,
                "Textures/Sky/Bright/BrightSky.dds", false));
        rootNode.attachChild(sceneNode);
    }
    
    /* ladds water filter */
    private void loadWater() {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);
        water = new WaterFilter(reflectedScene, new Vector3f(-2.9f, -1.2f, -5.8f));
        water.setWaterHeight(153);
        water.setCenter(Vector3f.ZERO);
        water.setRadius(260);
        water.setSpeed(2f);
        water.setWaterTransparency(0.02f);
        fpp.addFilter(water);
    }
    
    /* sets lightning */
    private void loadLightning() {
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, 0, -2));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);
    }
    
    /* loads audio */
    private void loadAudio() {
        wavesAudio =  new AudioNode(assetManager,"Sounds/sea.wav");
        wavesAudio.setLooping(true);
        wavesAudio.setPositional(false);
        wavesAudio.setVolume(0.05f);
        wavesAudio.play();
        rootNode.attachChild(wavesAudio);
    }
}

