package kadanes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class KadanesAlgorithmSnippetTest {

    Map<String, String> replaceVarsMap = null;
    ArrayList<String[]> replaceVars2DArray = null;
    ArrayList<String> replaceVarsArray = null;

    @BeforeEach
    void setUp() {
        replaceVarsMap = new HashMap<>();
        replaceVars2DArray = new ArrayList<>();
        replaceVarsArray = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        replaceVarsMap = null;
        replaceVars2DArray = null;
        replaceVarsArray = null;
    }

    @Test
    void testKadanesSnippetMethodListMap() {
        String expected = "" +
                "public static long kadanes(List<Integer> nums){\n" +
                "    long loki = 0, thor = Long.MIN_VALUE;\n" +
                "    for (Integer n : nums) {\n" +
                "        loki = Math.max(n, loki + n);\n" +
                "        thor = Math.max(loki, thor);\n" +
                "    }\n" +
                "    return thor;\n" +
                "}";
        replaceVarsMap.put("localMax", "loki");
        replaceVarsMap.put("globalMax", "thor");
        try {
            String actual;
            assertEquals(expected, actual = KadanesAlgorithmSnippet.kadanesSnippet(false, "List<Integer>", replaceVarsMap), String.format("%nExpected:%n%s%nActual:%n%s%n", expected, actual));
            assertEquals(expected, actual = KadanesAlgorithmSnippet.kadanesSnippet(false, replaceVarsMap), String.format("%nExpected:%n%s%nActual:%n%s%n", expected, actual));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testKadanesSnippetBodyIntArray1DArray() {
        String expected = "" +
                "long loki = 0, thor = Long.MIN_VALUE;\n" +
                "for (Integer n : nums) {\n" +
                "    loki = Math.max(n, loki + n);\n" +
                "    thor = Math.max(loki, thor);\n" +
                "}\n" +
                "return thor;";
        replaceVarsArray.add("thor");
        replaceVarsArray.add("loki");
        try {
            String actual;
            assertEquals(expected, actual = KadanesAlgorithmSnippet.kadanesSnippet(true, "int[]", replaceVarsArray.toArray(String[]::new)), String.format("%nExpected:%n%s%nActual:%n%s%n", expected, actual));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testKadanesSnippetMethodDefaultIterableTypeDefaultVariableNames() {
        String expected = "" +
                "public static long kadanes(List<Integer> nums){\n" +
                "    long localMax = 0, globalMax = Long.MIN_VALUE;\n" +
                "    for (Integer n : nums) {\n" +
                "        localMax = Math.max(n, localMax + n);\n" +
                "        globalMax = Math.max(localMax, globalMax);\n" +
                "    }\n" +
                "    return globalMax;\n" +
                "}";
        try {
            String actual;
            assertEquals(expected, actual = KadanesAlgorithmSnippet.kadanesSnippet(false), String.format("%nExpected:%n%s%nActual:%n%s%n", expected, actual));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testKadanesSnippet2() {
    }

    @Test
    void testKadanesSnippet3() {
    }

    @Test
    void testKadanesSnippet4() {
    }

    @Test
    void testKadanesSnippet5() {
    }

    @Test
    void testKadanesSnippet6() {
    }

    @Test
    void testKadanesSnippet7() {
    }
}