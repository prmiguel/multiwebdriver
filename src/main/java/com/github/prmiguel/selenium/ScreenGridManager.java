package com.github.prmiguel.selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class ScreenGridManager {

    private final Dimension dimension;
    private final Queue<Point> positions;
    private final Point[][] grid;

    public ScreenGridManager(int rows, int columns) {
        grid = new Point[rows][columns];
        java.awt.Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
        positions = new LinkedList<>();
        dimension = new Dimension(a.width / columns, a.height / rows);

        int y = 0;
        int x = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Point(x, y);
                positions.add(new Point(x, y));
                x += dimension.getWidth();
            }
            x = 0;
            y += dimension.getHeight();
        }
    }

    public ScreenGridManager(ScreenGrid screenGrid) {
        this(screenGrid.getRows(), screenGrid.getColumns());
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Queue<Point> getPositions() {
        return positions;
    }
}
