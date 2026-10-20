package dataflow.zeroanalysis;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import seminar.dataflow.Launcher;
import seminar.dataflow.zeroanalysis.DivisionByZeroAnalysis;
import seminar.dataflow.zeroanalysis.ZeroAbstractState;
import seminar.dataflow.zeroanalysis.ZeroAbstractValue;

public class DivisionByZeroTest {
    @Test
    public void testTarget1() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis1");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(7));
        assertEquals("j = m / k", divisionsByZero.get(7));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(8);
        assertEquals(ZeroAbstractValue.ZERO, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.ZERO, inSet.getValue("k"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("m"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("n"));
        assertEquals(ZeroAbstractValue.BOTTOM, inSet.getValue("j"));
    }

    @Test
    public void testTarget2() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis2");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(7));
        assertEquals("j = m / x", divisionsByZero.get(7));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(8);
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("m"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("n"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("i"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("j"));
    }

    @Test
    public void testTarget3() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis3");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(6));
        assertEquals("j = m / n", divisionsByZero.get(6));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(7);
        assertEquals(ZeroAbstractValue.ZERO, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("m"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("n"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("j"));
    }

    @Test
    public void testTarget4() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis4");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(12));
        assertEquals("j = n / y", divisionsByZero.get(12));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(13);
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("m"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("n"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("j"));
    }

    @Test
    public void testTarget5() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis5");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(0, divisionsByZero.size());

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(11);
        assertEquals(ZeroAbstractValue.NON_POSITIVE, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("y"));
    }

    @Test
    public void testTarget6() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis6");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(0, divisionsByZero.size());

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(12);
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("r"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("y"));
    }

    @Test
    public void testTarget7() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis7");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(7));
        assertEquals("d = j / i", divisionsByZero.get(7));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(13);
        assertEquals(ZeroAbstractValue.ZERO, inSet.getValue("i"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("j"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("d"));
    }

    @Test
    public void testTarget8() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis8");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(11));
        assertEquals("r = n / i", divisionsByZero.get(11));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(12);
        assertEquals(ZeroAbstractValue.NON_NEGATIVE, inSet.getValue("i"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("k"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("j"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("r"));
    }

    @Test
    public void testTarget9() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis9");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(0, divisionsByZero.size());

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(13);
        assertEquals(ZeroAbstractValue.NOT_ZERO, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.NOT_ZERO, inSet.getValue("y"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("z"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("j"));
        assertEquals(ZeroAbstractValue.TOP, inSet.getValue("k"));
    }

    @Test
    public void testTarget10() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis10");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(11));
        assertEquals("k = n / x", divisionsByZero.get(11));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(12);
        assertEquals(ZeroAbstractValue.NON_POSITIVE, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.NEGATIVE, inSet.getValue("y"));
    }

    @Test
    public void testTarget11() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis11");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(1, divisionsByZero.size());
        assertTrue(divisionsByZero.containsKey(8));
        assertEquals("r = n / q", divisionsByZero.get(8));

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(11);
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("a"));
        assertEquals(ZeroAbstractValue.NON_NEGATIVE, inSet.getValue("q"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("s"));
    }

    @Test
    public void testTarget12() {
        Launcher.analyzeClass("seminar.dataflow.targets.ZeroAnalysis12");
        DivisionByZeroAnalysis zeroAnalysis = Launcher.getLastDivisionByZeroAnalysis();

        Map<Integer, String> divisionsByZero = zeroAnalysis.getPossibleDivisionByZeroExpressions();
        assertEquals(0, divisionsByZero.size());

        ZeroAbstractState inSet = zeroAnalysis.getINSetForLineNumber(16);
        assertEquals(ZeroAbstractValue.NEGATIVE, inSet.getValue("x"));
        assertEquals(ZeroAbstractValue.NON_NEGATIVE, inSet.getValue("i"));
        assertEquals(ZeroAbstractValue.POSITIVE, inSet.getValue("y"));
        assertEquals(ZeroAbstractValue.NON_NEGATIVE, inSet.getValue("z"));
        assertEquals(ZeroAbstractValue.NON_POSITIVE, inSet.getValue("w"));
        assertEquals(ZeroAbstractValue.NEGATIVE, inSet.getValue("v"));
    }
}
