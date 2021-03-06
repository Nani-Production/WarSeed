package controls;

import draw.Draw_Main;
import gamestate.Gamestate;
import gamestate.Gamestate_e;
import gui.Game_Gui;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Rectangle;

public class MouseMoved implements EventHandler<MouseEvent> {
    private double frame1 = 0.1, frame2 = 0.05, frame3 = 0.02; //der prozentuale Rahmen, in dem man sich mit der Maus über die Map bewegen kann
    private double speed = 0;
    private Game_Gui gui;
    private Robot robot;

    public MouseMoved(Game_Gui gui, Robot robot){
        this.gui = gui;
        this.robot = robot;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();

        //lockMouseOnScreen(x, y);

        if (Gamestate.state == Gamestate_e.menu) {
            gui.getConnect().checkHover(x, y);
            gui.getReady().checkHover(x, y);
        } else if (Gamestate.state == Gamestate_e.ingame) {
            //test
            Draw_Main.frame1 = frame1;
            Draw_Main.frame2 = frame2;
            Draw_Main.frame3 = frame3;
            //test

            if (x > gui.getWidth() * frame1 && x <= gui.getWidth() - (gui.getWidth() * frame1) && y > gui.getHeight() * frame1 && y <= gui.getHeight() - (gui.getHeight() * frame1)) {
                //stop level
                speed = 0;
                Camera.stopUp();
                Camera.stopDown();
                Camera.stopRight();
                Camera.stopLeft();
            } else if (!(x > gui.getWidth() * frame1 && x <= gui.getWidth() - (gui.getWidth() * frame1) && y > gui.getHeight() * frame1 && y <= gui.getHeight() - (gui.getHeight() * frame1))) {
                speed = 8; //first level
                if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight()) * frame1), x, y)) {
                    if (collisionRequest(new Rectangle(0, 0, gui.getWidth() * frame1, gui.getHeight() * frame1), x, y)) {
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.moveUp(Math.sqrt((speed * speed) / 2));
                        Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                    } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame1), 0, gui.getWidth() * frame1, gui.getHeight() * frame1), x, y)) {
                        Camera.stopDown();
                        Camera.stopLeft();
                        Camera.moveUp(Math.sqrt((speed * speed) / 2));
                        Camera.moveRight(Math.sqrt((speed * speed) / 2));
                    } else {
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.stopLeft();
                        Camera.moveUp(speed);
                    }
                } else if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame1), gui.getWidth(), gui.getHeight() * frame1), x, y)) {
                    if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame1), gui.getWidth() * frame1, gui.getHeight() * frame1), x, y)) {
                        //querdown left
                        Camera.stopUp();
                        Camera.stopRight();
                        Camera.moveDown(Math.sqrt((speed * speed) / 2));
                        Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                    } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame1), gui.getHeight() - (gui.getHeight() * frame1), gui.getWidth() * frame1, gui.getHeight() * frame1), x, y)) {
                        //querdown right
                        Camera.stopUp();
                        Camera.stopLeft();
                        Camera.moveDown(Math.sqrt((speed * speed) / 2));
                        Camera.moveRight(Math.sqrt((speed * speed) / 2));
                    } else {
                        Camera.stopUp();
                        Camera.stopRight();
                        Camera.stopLeft();
                        Camera.moveDown(speed);
                    }
                } else if (collisionRequest(new Rectangle(0, gui.getHeight() * frame1, gui.getWidth() * frame1, gui.getHeight() - 2 * (gui.getHeight() * frame1)), x, y)) {
                    Camera.stopUp();
                    Camera.stopDown();
                    Camera.stopRight();
                    Camera.moveLeft(speed);
                } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame1), gui.getHeight() * frame1, gui.getWidth() * frame1, gui.getHeight() - 2 * (gui.getHeight() * frame1)), x, y)) {
                    Camera.stopUp();
                    Camera.stopDown();
                    Camera.stopLeft();
                    Camera.moveRight(speed);
                }
                if (!(x > gui.getWidth() * frame2 && x <= gui.getWidth() - (gui.getWidth() * frame2) && y > gui.getHeight() * frame2 && y <= gui.getHeight() - (gui.getHeight() * frame2))) {
                    speed = 16; //second level
                    if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight()) * frame2), x, y)) {
                        if (collisionRequest(new Rectangle(0, 0, gui.getWidth() * frame2, gui.getHeight() * frame2), x, y)) {
                            //querup left
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.moveUp(Math.sqrt((speed * speed) / 2));
                            Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                        } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame2), 0, gui.getWidth() * frame2, gui.getHeight() * frame2), x, y)) {
                            //querup right
                            Camera.stopDown();
                            Camera.stopLeft();
                            Camera.moveUp(Math.sqrt((speed * speed) / 2));
                            Camera.moveRight(Math.sqrt((speed * speed) / 2));
                        } else {
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.stopLeft();
                            Camera.moveUp(speed);
                        }
                    } else if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame2), gui.getWidth(), gui.getHeight() * frame2), x, y)) {
                        if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame2), gui.getWidth() * frame2, gui.getHeight() * frame2), x, y)) {
                            //querdown left
                            Camera.stopUp();
                            Camera.stopRight();
                            Camera.moveDown(Math.sqrt((speed * speed) / 2));
                            Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                        } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame2), gui.getHeight() - (gui.getHeight() * frame2), gui.getWidth() * frame2, gui.getHeight() * frame2), x, y)) {
                            //querdown right
                            Camera.stopUp();
                            Camera.stopLeft();
                            Camera.moveDown(Math.sqrt((speed * speed) / 2));
                            Camera.moveRight(Math.sqrt((speed * speed) / 2));
                        } else {
                            Camera.stopUp();
                            Camera.stopRight();
                            Camera.stopLeft();
                            Camera.moveDown(speed);
                        }
                    } else if (collisionRequest(new Rectangle(0, gui.getHeight() * frame2, gui.getWidth() * frame2, gui.getHeight() - 2 * (gui.getHeight() * frame2)), x, y)) {
                        Camera.stopUp();
                        Camera.stopDown();
                        Camera.stopRight();
                        Camera.moveLeft(speed);
                    } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame2), gui.getHeight() * frame2, gui.getWidth() * frame2, gui.getHeight() - 2 * (gui.getHeight() * frame2)), x, y)) {
                        Camera.stopUp();
                        Camera.stopDown();
                        Camera.stopLeft();
                        Camera.moveRight(speed);
                    }
                    if (!(x > gui.getWidth() * frame3 && x >= gui.getWidth() - (gui.getWidth() * frame3) && y < gui.getHeight() * frame3 && y >= gui.getHeight() - (gui.getHeight() * frame3))) {
                        speed = 32; //third level
                        if (collisionRequest(new Rectangle(0, 0, gui.getWidth(), (gui.getHeight()) * frame3), x, y)) {
                            if (collisionRequest(new Rectangle(0, 0, gui.getWidth() * frame3, gui.getHeight() * frame3), x, y)) {
                                //querup left
                                Camera.stopDown();
                                Camera.stopRight();
                                Camera.moveUp(Math.sqrt((speed * speed) / 2));
                                Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                            } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame3), 0, gui.getWidth() * frame3, gui.getHeight() * frame3), x, y)) {
                                //querup right
                                Camera.stopDown();
                                Camera.stopLeft();
                                Camera.moveUp(Math.sqrt((speed * speed) / 2));
                                Camera.moveRight(Math.sqrt((speed * speed) / 2));
                            } else {
                                Camera.stopDown();
                                Camera.stopRight();
                                Camera.stopLeft();
                                Camera.moveUp(speed);
                            }
                        } else if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame3), gui.getWidth(), gui.getHeight() * frame3), x, y)) {
                            if (collisionRequest(new Rectangle(0, gui.getHeight() - (gui.getHeight() * frame3), gui.getWidth() * frame3, gui.getHeight() * frame3), x, y)) {
                                //querdown left
                                Camera.stopUp();
                                Camera.stopRight();
                                Camera.moveDown(Math.sqrt((speed * speed) / 2));
                                Camera.moveLeft(Math.sqrt((speed * speed) / 2));
                            } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame3), gui.getHeight() - (gui.getHeight() * frame3), gui.getWidth() * frame3, gui.getHeight() * frame3), x, y)) {
                                //querdown right
                                Camera.stopUp();
                                Camera.stopLeft();
                                Camera.moveDown(Math.sqrt((speed * speed) / 2));
                                Camera.moveRight(Math.sqrt((speed * speed) / 2));
                            } else {
                                Camera.stopUp();
                                Camera.stopRight();
                                Camera.stopLeft();
                                Camera.moveDown(speed);
                            }
                        } else if (collisionRequest(new Rectangle(0, gui.getHeight() * frame3, gui.getWidth() * frame3, gui.getHeight() - 2 * (gui.getHeight() * frame3)), x, y)) {
                            Camera.stopUp();
                            Camera.stopDown();
                            Camera.stopRight();
                            Camera.moveLeft(speed);
                        } else if (collisionRequest(new Rectangle(gui.getWidth() - (gui.getWidth() * frame3), gui.getHeight() * frame3, gui.getWidth() * frame3, gui.getHeight() - 2 * (gui.getHeight() * frame3)), x, y)) {
                            Camera.stopUp();
                            Camera.stopDown();
                            Camera.stopLeft();
                            Camera.moveRight(speed);
                        }
                    }
                }
            }
            /*
                else {
                    System.out.println("stop level");
                    speed = 0;
                    Camera.stopUp();
                    Camera.stopDown();
                    Camera.stopRight();
                    Camera.stopLeft();
                }
                 */
        }
    }

    private void lockMouseOnScreen(double x, double y) {
        double[] cordinations = gui.getStageInfo();
        double newX = x+cordinations[0], newY = y+cordinations[1];

        if (x <= 1) {
            newX = cordinations[0]+2;
            System.out.println("links "+newX);
        } else if (x >= gui.getWidth()-1){
            newX = cordinations[0]+cordinations[2]-2;
            System.out.println("rechts "+newX);
        }

        System.out.println(cordinations[1]+"   "+cordinations[3]);
        if (y <= 1){
            newY = cordinations[1]+2;
            System.out.println("oben "+newY);
        } else if (y >= gui.getHeight()-1){
            newY = cordinations[1]+cordinations[3]-2;
            System.out.println("unten "+newY);
        }
        System.out.println("mouse moved");
        robot.mouseMove(newX, newY);
    }

    private boolean collisionRequest(Rectangle rec, double x, double y) {
        return x >= rec.getX() && x < rec.getX() + rec.getWidth() && y >= rec.getY() && y < rec.getY() + rec.getHeight();
    }
}