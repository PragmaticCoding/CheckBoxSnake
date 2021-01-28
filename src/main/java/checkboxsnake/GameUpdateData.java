package checkboxsnake;


import checkboxsnake.Direction;

/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * GameUpdateData.java
 * created on 06.04.2018 - 14:55:23
 * edited by dnolte 06.04.2018 - 14:55:23
 */
/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class GameUpdateData {
    
    private Direction direction;

    public GameUpdateData(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
    
    

}
