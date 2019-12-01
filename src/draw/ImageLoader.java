package draw;

import javafx.scene.image.Image;

public class ImageLoader {
    public static Image image = loadImage("/rsc/image.jpg"), map = loadImage("/rsc/map.png"); //= loadImage("/resources/playermodel.png")

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