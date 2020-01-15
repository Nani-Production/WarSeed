package draw;

import javafx.scene.image.Image;

public class ImageLoader {
    public static Image image = loadImage("/rsc/image.jpg"), map = loadImage("/rsc/map2.jpg"), image2 = loadImage("/rsc/map.png"),
            icon = loadImage("/rsc/client_icon.png"), menubg = loadImage("/rsc/war-art.jpg"), button = loadImage("/rsc/button.png"), textfield = loadImage("/rsc/textfield.png"),
            resinfobg = loadImage("/rsc/resinfo.png"), minimapbg = loadImage("/rsc/minimap.png"), unitInfobg = loadImage("/rsc/unit info.png"),
            ressource1 = loadImage("/rsc/gold.png"), ressource2 = loadImage("/rsc/iron.png"), ressource3 = loadImage("/rsc/bumm.png"),
            speeder = loadImage("/rsc/tank1.png"), damageDealer = loadImage("/rsc/tank2.png"), tank = loadImage("/rsc/tank3.png"), settler = loadImage("/rsc/siedler.png"),
            base = loadImage("/rsc/base.png"), village = loadImage("/rsc/village.png"),
            texForest = loadImage("/rsc/forest.png"), texgrass1 = loadImage("/rsc/grass1.png"), waterdark = loadImage("/rsc/water_dark.png"), waterlight = loadImage("/rsc/water_light.png");

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