package com.github.prmiguel.selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;


import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScreenGridManager {

    private Dimension dimension;
    private Queue<Point> positions;

    public ScreenGridManager(int columns, int rows) {
        java.awt.Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
        positions = new LinkedList<>();
        switch (columns * rows) {
            case 1:
                dimension = new Dimension(a.width, a.height);
                positions.add(new Point(0, 0));
                break;
            case 2:
                dimension = new Dimension(a.width / 2, a.height);
                positions.add(new Point(0, 0));
                positions.add(new Point(a.width / 2, 0));
                break;
            case 4:
                dimension = new Dimension(a.width / 2, a.height / 2);
                positions.add(new Point(0, 0));
                positions.add(new Point(a.width / 2, 0));
                positions.add(new Point(0, a.height / 2));
                positions.add(new Point(a.width / 2, a.height / 2));
                break;
            default:
                throw new RuntimeException("not expected");
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Queue<Point> getPositions() {
        return positions;
    }
}
