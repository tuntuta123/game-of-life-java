package conway.graphics.menu;

import java.awt.Color;

public class ColorItem {
    private Color color;
    private String name;

    public ColorItem(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.name;  
    }
}

