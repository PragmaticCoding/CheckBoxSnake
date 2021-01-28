/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * Pixel.java
 * created on 06.04.2018 - 14:39:53
 * edited by dnolte 06.04.2018 - 14:39:53
 */
package checkboxsnake;

import javafx.scene.control.CheckBox;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class Food {

    private CheckBox node;

    public Food() {
        node = buildCheckBox();
    }

    public CheckBox getNode() {
        return node;
    }
    
    public void mark(){
        node.setSelected(true);
    }
    
    public void unmark(){
        node.setSelected(false);
    }

    private CheckBox buildCheckBox() {
        CheckBox checkBox = new CheckBox();
        checkBox.getStyleClass().add("check-box-food");

        return checkBox;
    }

}
