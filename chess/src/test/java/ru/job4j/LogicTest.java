package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

public class LogicTest {
    @Test
    public void whenMoveInRightDirectionFreeWayThenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new BishopBlack(Cell.C4));
        logic.add(new BishopBlack(Cell.G1));
        boolean moveResult = logic.move(Cell.A1, Cell.D4);
        Assert.assertThat(moveResult, is(true));
    }

    @Test
    public void whenMoveInRightDirectionDestinationPointBusyThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new BishopBlack(Cell.G7));
        logic.add(new BishopBlack(Cell.G1));
        boolean moveResult = logic.move(Cell.A1, Cell.G7);
        Assert.assertThat(moveResult, is(false));
    }

    @Test
    public void whenMoveInRightDirectionIntermediatePointBusyThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new BishopBlack(Cell.D4));
        logic.add(new BishopBlack(Cell.G1));
        boolean moveResult = logic.move(Cell.A1, Cell.G7);
        Assert.assertThat(moveResult, is(false));
    }

    @Test
    public void whenMoveInWrongtDirectionThenThrownException() throws IllegalStateException {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.A1));
        try {
            boolean moveResult = logic.move(Cell.A1, Cell.E3);
            Assert.fail("Exception catched");
        } catch (IllegalStateException ise) {
            Assert.assertThat(ise.getMessage(), not(isEmptyString()));
        }
    }
}
