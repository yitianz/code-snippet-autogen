package kadanes;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KadanesAlgorithmSnippet {

//    static final String[] defaultVariableNames = {"localMax", "globalMax"};

    static final String kadanesInfoFilePath = "snippetlibrary/kadanes/KadanesAlgorithm.json";

    public static String kadanesSnippet(String[] vars, boolean inlineSnippet) throws IOException {
        // vars should contain the variable names for `localMax` and `globalMax`, in that order
        // null or empty string for default variable names
        String returnType = inlineSnippet ? "body" : "method";

//        System.out.println(System.getProperty("user.dir"));

//        BufferedReader jsonFile = new BufferedReader(new FileReader(kadanesInfoFilePath));
//        jsonFile.lines().forEach(System.out::println);

//        String jsonFile = Files.readString(Path.of(kadanesInfoFilePath));
        String jsonFile = new String(Files.readAllBytes(Paths.get(kadanesInfoFilePath)));

        JSONObject kadanesInfo = new JSONObject(jsonFile).getJSONObject("KadanesAlgorithm");
//        System.out.println(kadanesInfo.toString(4));

        JSONObject extractParams = kadanesInfo.getJSONObject("iterableType").getJSONObject("int[]").getJSONObject(returnType);
        int startLine = extractParams.getInt("startLine") - 1, length = extractParams.getInt("length");


        JSONObject replaceParams = kadanesInfo.getJSONObject("variables");

        Map<String, String> replaceVars = null;
        if (vars != null && !vars.isEmpty()) {
            replaceVars = new HashMap<>();
            Map<String, String> finalReplaceVars = replaceVars;
            vars.forEach((varName, repl) -> {
                try {
                    finalReplaceVars.put(replaceParams.getString(varName), repl);
                } catch (JSONException e) {
                    System.err.printf("Default variable name \"%s\" not found for this snippet; ignoring exception:%n", varName);
                    e.printStackTrace();
                }
            });
        }

        StringBuilder sb = new StringBuilder();
        int[] tabCount = new int[]{-1};
        Map<String, String> finalReplaceVars = replaceVars;
//        Files.lines(Path.of(kadanesInfoFilePath.substring(0, kadanesInfoFilePath.lastIndexOf('/') + 1) + kadanesInfo.getString("filename"))).skip(startLine).limit(length).forEach(s -> sb.append(replaceVariables(s.substring(tabCount[0] != -1 ? tabCount[0] : (tabCount[0] = s.length() - s.stripLeading().length())), finalReplaceVars)).append('\n'));

        Files.lines(Path.of(kadanesInfoFilePath.substring(0, kadanesInfoFilePath.lastIndexOf('/') + 1) + kadanesInfo.getString("filename"))).skip(startLine).limit(length).forEach(s -> sb.append(s.substring(tabCount[0] != -1 ? tabCount[0] : (tabCount[0] = s.length() - s.stripLeading().length()))).append('\n'));


        return replaceVariables_ahocorasick(sb, replaceVars);

    }

    public static String kadanesSnippet(boolean inlineSnippet, String[][] vars) throws IOException {
        Map<String, String> replaceVarNames = null;
        if (vars != null && vars.length > 0) replaceVarNames = Arrays.stream(vars).collect(Collectors.toMap(p -> p[0], p -> p[1]));
        return kadanesSnippet(inlineSnippet, replaceVarNames);
    }

    public static String kadanesSnippet(boolean inlineSnippet, String[] replacementVarNames) throws IOException {
        // replacementVarNames should contain the variable names for `globalMax` and `localMax`, in that order (alphabetically ascending)
        // null or empty string for default variable names
        Map<String, String> replaceVarNames = null;
        if (replacementVarNames != null && replacementVarNames.length > 0) {
//            String jsonFile = Files.readString(Path.of(kadanesInfoFilePath));
            String jsonFile = new String(Files.readAllBytes(Paths.get(kadanesInfoFilePath)));
            JSONObject replaceParams = new JSONObject(jsonFile).getJSONObject("KadanesAlgorithm").getJSONObject("variables");
            ArrayList<String> defaultVariableNames = new ArrayList<>(replaceParams.length());
            replaceParams.names().forEach(n -> defaultVariableNames.add(n.toString()));
            Collections.sort(defaultVariableNames);
            ArrayList<String> finalReplacementVarNames = new ArrayList<>(defaultVariableNames.size());
            Collections.addAll(finalReplacementVarNames, replacementVarNames);
            replaceVarNames = IntStream.range(0, defaultVariableNames.size()).boxed().collect(Collectors.toMap(defaultVariableNames::get, i -> finalReplacementVarNames.get(i) != null && !finalReplacementVarNames.get(i).equals("") ? finalReplacementVarNames.get(i) : defaultVariableNames.get(i)));
        }

        return kadanesSnippet(inlineSnippet, replaceVarNames);
    }

    public static String kadanesSnippet(boolean inlineSnippet) throws IOException {
        return kadanesSnippet(inlineSnippet, (Map<String, String>) null);
    }

    public static String kadanesSnippet() throws IOException {
        return kadanesSnippet(true);
    }

    private static String replaceVariables(String codeLine, Map<String, String> vars) {
        if (vars == null || vars.isEmpty()) return codeLine;
        StringBuilder sb = new StringBuilder();
        vars.forEach((orig, repl) -> {
            Matcher m = Pattern.compile(orig).matcher(sb);
            while (m.find()) m.appendReplacement(sb, repl);
        });
        codeLine = sb.toString();

//        Matcher m = Pattern.compile("loc").matcher(sb);
//        while (m.find()) m.appendReplacement(sb, )
//        vars.entrySet().stream().forEach(v -> codeLine = codeLine.replace(v.getKey(), v.getValue()));
//        for (Map.Entry<String, String> var : vars.entrySet()) {
//            codeLine = codeLine.replace(var.getKey(), var.getValue());
//        }

        return codeLine;
    }

    private static String replaceVariables(String codeLine) {
        return replaceVariables(codeLine, null);
    }

    private static String replaceVariables_ahocorasick(String codeBlock, Map<String, String> vars) {
        if (vars == null || vars.isEmpty()) return codeBlock;
//        final String text, final Map<String, String> definitions
        // Create a buffer sufficiently large that re-allocations are minimized.
        final StringBuilder sb = new StringBuilder(codeBlock.length() << 1);

        final Trie.TrieBuilder builder = Trie.builder();
//        builder.onlyWholeWordsWhiteSpaceSeparated();
        builder.onlyWholeWords();
        builder.ignoreOverlaps();
//        builder.removeOverlaps();  // deprecated, use ignoreOverlaps instead

//        final String[] keys = keys(definitions);
//
//        for (final String key : keys) {
//            builder.addKeyword(key);
//        }
        vars.keySet().forEach(builder::addKeyword);


        final Trie trie = builder.build();
        final Collection<Emit> emits = trie.parseText(codeBlock);

        int prevIndex = 0;

        for (final Emit emit : emits) {
            final int matchIndex = emit.getStart();

            sb.append(codeBlock, prevIndex, matchIndex);
            sb.append(vars.get(emit.getKeyword()));
            prevIndex = emit.getEnd() + 1;
        }

        // Add the remainder of the string (contains no more matches).
        sb.append(codeBlock.substring(prevIndex));

        return sb.toString();
    }

    private static String replaceVariables_ahocorasick(String codeBlock) {
        return replaceVariables_ahocorasick(codeBlock, null);
    }

    private static String replaceVariables_ahocorasick(StringBuilder codeBlock, Map<String, String> vars) {
        return replaceVariables_ahocorasick(codeBlock.toString(), vars);
    }

    private static String replaceVariables_ahocorasick(StringBuilder codeBlock) {
        return replaceVariables_ahocorasick(codeBlock, null);
    }

    public static void main(String[] args) {
        try {
            System.out.println(kadanesSnippet(new String[]{}, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
