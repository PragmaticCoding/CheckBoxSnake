package com.arm.checkboxsnake.data;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class Position {

    private IntegerProperty xPositionProperty = new SimpleIntegerProperty();
    private IntegerProperty yPositionProperty = new SimpleIntegerProperty();

    public Position(int xPosition, int yPosition) {
        this.xPositionProperty.set(xPosition);
        this.yPositionProperty.set(yPosition);
    }

    public IntegerProperty xPositionProperty() {
        return xPositionProperty;
    }

    public IntegerProperty yPositionProperty() {
        return yPositionProperty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.xPositionProperty.get());
        hash = 67 * hash + Objects.hashCode(this.yPositionProperty.get());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (!Objects.equals(this.xPositionProperty.get(), other.xPositionProperty.get())) {
            return false;
        }
        if (!Objects.equals(this.yPositionProperty.get(), other.yPositionProperty.get())) {
            return false;
        }
        return true;
    }
}
