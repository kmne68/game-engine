/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.core;

import com.base.engine.rendering.RenderingEngine;
import com.base.game.TestGame;
import com.base.engine.rendering.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmne68
 *
 * To run this program, click run on this class.
 *
 * Use this link for library setup:
 * http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_NetBeans.html
 */
public class CoreEngine {

  private boolean isRunning;

  private TestGame game;
  private RenderingEngine renderingEngine;
  private int width;
  private int height;
  private double frameTime;

  public CoreEngine(int width, int height, int framerate, TestGame game) {
    this.isRunning = false;
    this.width = width;
    this.height = height;
    this.game = game;
    this.frameTime = 1.0 / framerate;
  }


  public void createWindow(String title) {

    Window.createWindow(width, height, title);    
    this.renderingEngine = new RenderingEngine();
  }

  public void start() {
    if (isRunning) {
      return;
    }
    run();
  }

  public void stop() {
    if (!isRunning) {
      return;
    }
    isRunning = false;
  }

  private void run() {
    isRunning = true;

    int frames = 0;
    long frameCounter = 0;

    game.init();

    double lastTime = Time.getTime();
    double unprocessedTime = 0;

    while (isRunning) {
      boolean doRender = false;

      double startTime = Time.getTime();
      double passedTime = startTime - lastTime;
      lastTime = startTime;

      unprocessedTime += passedTime;
      frameCounter += passedTime;

      while (unprocessedTime > frameTime) {
        doRender = true;

        unprocessedTime -= frameTime;

        if (Window.isCloseRequested()) {
          stop();
        }

        game.input((float) frameTime);
        Input.update();

        game.update((float) frameTime);

        if (frameCounter >= 1.0) {
          System.out.println("frames = " + frames);
          frames = 0;
          frameCounter = 0;
        }
      }
      if (doRender) {
        game.render(renderingEngine);
        // renderingEngine.render(game.getRootObject());
        Window.render();
        frames++;
      } else {
        try {
          Thread.sleep(1);
        } catch (InterruptedException ex) {
          Logger.getLogger(CoreEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }

    cleanUp();
  }

  private void cleanUp() {
    Window.dispose();
  }

}
