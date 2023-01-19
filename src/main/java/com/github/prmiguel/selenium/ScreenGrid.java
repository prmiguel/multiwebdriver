package com.github.prmiguel.selenium;

public enum ScreenGrid {
    _1x1(1, 1),
    _1X2(1, 2),
    _2x1(2, 1),
    _1x3(1, 3),
    _3x1(3, 1),
    _2x2(2, 2),
    _1x4(1, 4),
    _4x1(4, 1),
    ;

    private final int columns;
    private final int rows;

    ScreenGrid(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public int getRows() {
        return this.rows;
    }
}
