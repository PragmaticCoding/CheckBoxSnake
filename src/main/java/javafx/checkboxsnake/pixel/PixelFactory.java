package javafx.checkboxsnake.pixel;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class PixelFactory {
    
    public Pixel getPixel(){
//        return new RadioButtonPixel(); // alternative java node implementation
        return new CheckBoxPixel();
    }
}
