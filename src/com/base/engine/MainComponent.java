/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmne68
 */
public class MainComponent {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D Engine";
    public static final double FRAME_CAP = 5000.0;
    
    private boolean isRunning;
    
    private Game game;
    
    public MainComponent()
    {
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();
    }
    
    
    public void start()
    {
        if(isRunning)
            return;
        run();
    }
    
    
    public void stop()
    {
        if(!isRunning)
            return;
        isRunning = false; 
    }
    
    private void run()
    {
        isRunning = true;
        
        int frames = 0;
        long frameCounter = 0;
        
        final double frameTime = 1.0 / FRAME_CAP;   // This could be the constant (instead of FRAME_CAP
        
        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        
        while(isRunning)
        {
            boolean doRender = false;
            
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;
            
            unprocessedTime += passedTime / (double)Time.SECOND; 
            frameCounter += passedTime;
            
            while(unprocessedTime > frameTime)
            {
                doRender = true;
                
                unprocessedTime -= frameTime;
            
                if(Window.isCloseRequested())
                    stop(); 
                
                Time.setDelta(frameTime);                
            
                game.input();
                Input.update();
                
                game.update();
                
                if(frameCounter >= Time.SECOND)
                {
                    System.out.println("frames = " + frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if(doRender)
            {
                render();
                frames++;
            }
            else
            {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        cleanUp();
    }
    
    
    private void render()
    {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }
    
    private void cleanUp()
    {
        Window.dispose();
    }
    
    
    public static void main(String[] args) {
        
        Window.createWindow(WIDTH, HEIGHT, TITLE);
        
        MainComponent game = new MainComponent();
        
        game.start();
    }
}
