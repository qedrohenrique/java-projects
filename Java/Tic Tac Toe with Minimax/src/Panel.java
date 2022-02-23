import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
            private Thread thread;
            protected Graphics2D graphicsRender; 
            private Image img;
            private Color backgroundColor;
            
            public Panel(Color color) {
                        this.backgroundColor = color;
                        
                        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
                        setFocusable(false);
                        requestFocus();
            }
            
            @Override
            public void addNotify() {
                        super.addNotify();
                        
                        if(thread == null){
                                    thread = new Thread(this);
                                    thread.start();
                        }
            }
            
            @Override
            public void run() {
                        init(); 
                        long lastTime = System.nanoTime();
                        double nanoSecondPerUpdate = 1000000000D / 30;
                        float deltaTime = 0;
                        
                        while(true){
                                    long now = System.nanoTime();
                                    deltaTime += (now - lastTime) / nanoSecondPerUpdate;
                                    lastTime = now;
                                    
                                    if(deltaTime >= 1){
                                                update(deltaTime);
                                                render();
                                                deltaTime--;
                                    }
                                    
                                    try {
                                                Thread.sleep(1);
                                    } catch (InterruptedException e) {
                                                e.printStackTrace();
                                    }
                        }
            }
            
            public void init() {
                        img = createImage(Main.WIDTH, Main.HEIGHT);
                        graphicsRender = (Graphics2D) img.getGraphics();
            }
            
            public void update(float deltaTime) {
            }

            public void render() {
                        graphicsRender.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
                        graphicsRender.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
                        graphicsRender.setColor(backgroundColor);
                        graphicsRender.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
                        graphicsRender.setColor(Color.white);
            }
            
            public void clear(){
                        Graphics graphics = getGraphics();
                        
                        if(img != null) {
                                    graphics.drawImage(img, 0, 0, null);
                        }
                        
                        graphics.dispose();
            }
}