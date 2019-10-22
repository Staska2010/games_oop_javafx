package ru.job4j;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;

public class BishopBlackTest {
    @Test
    public void ifPositionValueIsTheSameThenTrue() {
        Figure bBishop = new BishopBlack(Cell.C1);
        Cell expectedPosition = Cell.C1;
        Cell realPosition = bBishop.position();
        Assert.assertThat(expectedPosition, is(realPosition));
    }

    @Test
    public void ifPositionValueIsNotTheSameThenFalse() {
        Figure bBishop = new BishopBlack(Cell.C1);
        Cell expectedPosition = Cell.C4;
        Cell realPosition = bBishop.position();
        Assert.assertNotEquals(expectedPosition, realPosition);
    }

    @Test
    public void ifCopyBishopThanCreateNewFigureWithCellCords() {
        Figure bBishopOld = new BishopBlack(Cell.C1);
        Cell newPosition = Cell.C4;
        Figure bBiShopCopy = bBishopOld.copy(newPosition);
        Assert.assertThat(bBiShopCopy, instanceOf(Figure.class));
        Assert.assertEquals(bBiShopCopy.position(), newPosition);
    }

    @Test
    public void whenChooseWayC1G5ThenWayIsD2E3F4G5() {
        Figure bBishop = new BishopBlack(Cell.C1);
        Cell[] way = bBishop.way(Cell.C1, Cell.G5);
        Cell[] expectedWay = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Assert.assertArrayEquals(expectedWay, way);
    }

    @Test
    public void whenChooseWayB6G1ThenWayIsC5D4E3F2G1() {
        Figure bBishop = new BishopBlack(Cell.B6);
        Cell[] way = bBishop.way(Cell.B6, Cell.G1);
        Cell[] expectedWay = {Cell.C5, Cell.D4, Cell.E3, Cell.F2, Cell.G1};
        Assert.assertArrayEquals(expectedWay, way);
    }

    @Test
    public void whenChooseWayC3A1ThenWayIsB2A1() {
        Figure bBishop = new BishopBlack(Cell.C3);
        Cell[] way = bBishop.way(Cell.C3, Cell.A1);
        Cell[] expectedWay = {Cell.B2, Cell.A1};
        Assert.assertArrayEquals(expectedWay, way);
    }

    @Test
    public void whenChoosenNonDiagonalWayThenException() throws IllegalStateException{
        Figure bBishop = new BishopBlack(Cell.A1);
        try {
            Cell[] way = bBishop.way(Cell.A1, Cell.A2);
            Assert.fail("Exception catched");
        } catch (IllegalStateException ise) {
            Assert.assertThat(ise.getMessage(), not(isEmptyString()));
        }
    }

    @Test
    public void whenChosenRightCellsThenIsDiagonal() {
        BishopBlack bBishop = new BishopBlack(Cell.B1);
        // case: B1 and F5 cells
        Cell source = Cell.B1;
        Cell dest = Cell.F5;
        boolean isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(true));
        // case: C7 and G3 cells
        source = Cell.C7;
        dest = Cell.G3;
        isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(true));
        // case: B4 and C5 cells
        source = Cell.B4;
        dest = Cell.C5;
        isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(true));
    }

    @Test
    public void whenChosenWrongCellsThenNotDiagonal() {
        BishopBlack bBishop = new BishopBlack(Cell.C7);
        Cell source = Cell.C7;
        Cell dest = Cell.G4;
        boolean isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(false));
    }
}
