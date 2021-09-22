package com.arm.checkboxsnake.pixel;

import com.arm.checkboxsnake.pixel.CheckBoxPixel;
import com.arm.checkboxsnake.pixel.Pixel;

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
