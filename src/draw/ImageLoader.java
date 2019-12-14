package draw;

import javafx.scene.image.Image;

public class ImageLoader {
    public static Image menubg = loadImage("/rsc/war-art.jpg"), image = loadImage("/rsc/image.jpg"), map = loadImage("/rsc/map2.jpg"),
            map2 = loadImage("/rsc/map.jpg"), image2 = loadImage("/rsc/map.png"), button = loadImage("/rsc/button.png"),
    tank1 = loadImage("/rsc/tank1.png"), tank2 = loadImage("/rsc/tank2.png"), tank3 = loadImage("/rsc/tank3.png"), tank4 = loadImage("/rsc/siedler.png"),
    base = loadImage("/rsc/base.png"), village = loadImage("/rsc/village.png"), textfield = loadImage("/rsc/textfield.png");

    private static Image loadImage(String path){
        try {
            //TODO Bilder funktionieren nicht als Artifact
            //Image img = new Image(String.valueOf(Toolkit.getDefaultToolkit().getClass().getResource(path)));
            Image img = new Image(String.valueOf(ImageLoader.class.getResource(path)));
            return img;
        } catch (IllegalArgumentException e){
            System.out.println(path);
            return null;
        }
    }
}