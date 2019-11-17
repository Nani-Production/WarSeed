package controls;

import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import gui.Launcher_Gui;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class MouseMoved implements EventHandler<MouseEvent> {
    private double frame1 = 0.1, frame2 = 0.05, frame3 = 0.025; //der prozentuale Rahmen, in dem man sich mit der Maus über die Map bewegen kann
    private double speed = 0;
    private Game_Gui gui;

    public MouseMoved(Game_Gui gui) {
        this.gui = gui;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();
        if (Gamestate.state == Gamestate_e.ingame){

            //TODO funkt noch nicht -> fixing
            if (!(x > gui.getWidth()*frame1 && x <= gui.getWidth()-(gui.getWidth()*frame1) && y > gui.getHeight()*frame1 && y <= gui.getHeight()-(gui.getHeight()*frame1)) && x > 0 && x <= gui.getWidth() && y > 0 && y <= gui.getHeight()){
                speed = 1; //langsam
                System.out.println("triggered1");
                if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight())*frame1), x, y)){
                    if (collisionRequest(new Rectangle(0, 0, gui.getWidth()*frame1, gui.getHeight()*frame1), x, y)){
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.moveUp(Math.sqrt((speed*speed)/2));
                        Camera.moveLeft(Math.sqrt((speed*speed)/2));
                    } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame1), 0, gui.getWidth()*frame1, gui.getHeight()*frame1), x, y)){
                        Camera.stopDown();
                        Camera.stopLeft();
                        Camera.moveUp(Math.sqrt((speed*speed)/2));
                        Camera.moveRight(Math.sqrt((speed*speed)/2));
                    } else {
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.stopLeft();
                        Camera.moveUp(speed);
                    }
                } else if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame1), gui.getWidth(), gui.getHeight()*frame1), x, y)){
                    if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame1), gui.getWidth()*frame1, gui.getHeight()*frame1), x, y)){
                        //querdown left
                        Camera.stopUp();
                        Camera.stopRight();
                        Camera.moveDown(Math.sqrt((speed*speed)/2));
                        Camera.moveLeft(Math.sqrt((speed*speed)/2));
                    } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame1), gui.getHeight()-(gui.getHeight()*frame1), gui.getWidth()*frame1, gui.getHeight()*frame1), x, y)){
                        //querdown right
                        Camera.stopUp();
                        Camera.stopLeft();
                        Camera.moveDown(Math.sqrt((speed*speed)/2));
                        Camera.moveRight(Math.sqrt((speed*speed)/2));
                    } else {
                        Camera.stopUp();
                        Camera.stopRight();
                        Camera.stopLeft();
                        Camera.moveDown(speed);
                    }
                } else if (collisionRequest(new Rectangle(0, gui.getHeight()*frame1, gui.getWidth()*frame1, gui.getHeight()-2*(gui.getHeight()*frame1)), x, y)){
                    Camera.stopUp();
                    Camera.stopDown();
                    Camera.stopRight();
                    Camera.moveLeft(speed);
                } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame1), gui.getHeight()*frame1, gui.getWidth()*frame1, gui.getHeight()-2*(gui.getHeight()*frame1)), x, y)){
                    Camera.stopUp();
                    Camera.stopDown();
                    Camera.stopLeft();
                    Camera.moveRight(speed);
                }


                if (!(x > gui.getWidth()*frame2 && x <= gui.getWidth()-(gui.getWidth()*frame2) && y > gui.getHeight()*frame2 && y <= gui.getHeight()-(gui.getHeight()*frame2)) && x > 0 && x <= gui.getWidth() && y > 0 && y <= gui.getHeight()){
                    speed = 2; //schneller
                    System.out.println("triggered2");
                    if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight())*frame2), x, y)){
                        if (collisionRequest(new Rectangle(0, 0, gui.getWidth()*frame2, gui.getHeight()*frame2), x, y)){
                            //querup left
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.moveUp(Math.sqrt((speed*speed)/2));
                            Camera.moveLeft(Math.sqrt((speed*speed)/2));
                        } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame2), 0, gui.getWidth()*frame2, gui.getHeight()*frame2), x, y)){
                            //querup right
                            Camera.stopDown();
                            Camera.stopLeft();
                            Camera.moveUp(Math.sqrt((speed*speed)/2));
                            Camera.moveRight(Math.sqrt((speed*speed)/2));
                        } else {
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.stopLeft();
                            Camera.moveUp(speed);
                        }
                    } else if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame2), gui.getWidth(), gui.getHeight()*frame2), x, y)){
                        if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame2), gui.getWidth()*frame2, gui.getHeight()*frame2), x, y)){
                            //querdown left
                            Camera.stopUp();
                            Camera.stopRight();
                            Camera.moveDown(Math.sqrt((speed*speed)/2));
                            Camera.moveLeft(Math.sqrt((speed*speed)/2));
                        } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame2), gui.getHeight()-(gui.getHeight()*frame2), gui.getWidth()*frame2, gui.getHeight()*frame2), x, y)){
                            //querdown right
                            Camera.stopUp();
                            Camera.stopLeft();
                            Camera.moveDown(Math.sqrt((speed*speed)/2));
                            Camera.moveRight(Math.sqrt((speed*speed)/2));
                        } else {
                            Camera.stopUp();
                            Camera.stopRight();
                            Camera.stopLeft();
                            Camera.moveDown(speed);
                        }
                    } else if (collisionRequest(new Rectangle(0, gui.getHeight()*frame2, gui.getWidth()*frame2, gui.getHeight()-2*(gui.getHeight()*frame2)), x, y)){
                        Camera.stopUp();
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.moveLeft(speed);
                    } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame2), gui.getHeight()*frame2, gui.getWidth()*frame2, gui.getHeight()-2*(gui.getHeight()*frame2)), x, y)){
                        Camera.stopUp();
                        Camera.stopDown();
                        Camera.stopLeft();
                        Camera.moveRight(speed);
                    }

                    if (!(x > gui.getWidth()*frame3 && x >= gui.getWidth()-(gui.getWidth()*frame3) && y < gui.getHeight()*frame3 && y >= gui.getHeight()-(gui.getHeight()*frame3)) && x > 0 && x <= gui.getWidth() && y > 0 && y <= gui.getHeight()){
                        speed = 3; //am schnellsten
                        System.out.println("triggered3");
                        if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight())*frame3), x, y)){
                            if (collisionRequest(new Rectangle(0, 0, gui.getWidth()*frame3, gui.getHeight()*frame3), x, y)){
                                //querup left
                                Camera.stopDown();
                                Camera.stopRight();
                                Camera.moveUp(Math.sqrt((speed*speed)/2));
                                Camera.moveLeft(Math.sqrt((speed*speed)/2));
                            } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame3), 0, gui.getWidth()*frame3, gui.getHeight()*frame3), x, y)){
                                //querup right
                                Camera.stopDown();
                                Camera.stopLeft();
                                Camera.moveUp(Math.sqrt((speed*speed)/2));
                                Camera.moveRight(Math.sqrt((speed*speed)/2));
                            } else {
                                Camera.stopDown();
                                Camera.stopRight();
                                Camera.stopLeft();
                                Camera.moveUp(speed);
                            }
                        } else if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame3), gui.getWidth(), gui.getHeight()*frame3), x, y)){
                            if (collisionRequest(new Rectangle(0, gui.getHeight()-(gui.getHeight()*frame3), gui.getWidth()*frame3, gui.getHeight()*frame3), x, y)){
                                //querdown left
                                Camera.stopUp();
                                Camera.stopRight();
                                Camera.moveDown(Math.sqrt((speed*speed)/2));
                                Camera.moveLeft(Math.sqrt((speed*speed)/2));
                            } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame3), gui.getHeight()-(gui.getHeight()*frame3), gui.getWidth()*frame3, gui.getHeight()*frame3), x, y)){
                                //querdown right
                                Camera.stopUp();
                                Camera.stopLeft();
                                Camera.moveDown(Math.sqrt((speed*speed)/2));
                                Camera.moveRight(Math.sqrt((speed*speed)/2));
                            } else {
                                Camera.stopUp();
                                Camera.stopRight();
                                Camera.stopLeft();
                                Camera.moveDown(speed);
                            }
                        } else if (collisionRequest(new Rectangle(0, gui.getHeight()*frame3, gui.getWidth()*frame3, gui.getHeight()-2*(gui.getHeight()*frame3)), x, y)){
                            Camera.stopUp();
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.moveLeft(speed);
                        } else if (collisionRequest(new Rectangle(gui.getWidth()-(gui.getWidth()*frame3), gui.getHeight()*frame3, gui.getWidth()*frame3, gui.getHeight()-2*(gui.getHeight()*frame3)), x, y)){
                            Camera.stopUp();
                            Camera.stopDown();
                            Camera.stopLeft();
                            Camera.moveRight(speed);
                        }
                    }
                }
            } else {
                speed = 0;
                Camera.stopUp();
                Camera.stopDown();
                Camera.stopRight();
                Camera.stopLeft();
            }
        }
    }
    private boolean collisionRequest (Rectangle rec, double x, double y){
        return x >= rec.getX() && x < rec.getX()+rec.getWidth() && y >= rec.getY() && y < rec.getY()+rec.getHeight();
    }
}