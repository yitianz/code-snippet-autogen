package kadanes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class KadanesAlgorithmSnippet {

    static final String kadanesInfoFilePath = "snippetlibrary/kadanes/KadanesAlgorithm.json";

    public static String kadanesSnippet(String[] vars, boolean inlineSnippet) throws IOException {
        // vars should contain the variable names for `localMax` and `globalMax`, in that order
        // null or empty string for default variable names
        String returnType = inlineSnippet ? "body" : "method";

//        System.out.println(System.getProperty("user.dir"));

//        BufferedReader jsonFile = new BufferedReader(new FileReader(kadanesInfoFilePath));
//        jsonFile.lines().forEach(System.out::println);

        String jsonFile = Files.readString(Path.of(kadanesInfoFilePath));

        JSONObject kadanesInfo = new JSONObject(jsonFile).getJSONObject("KadanesAlgorithm");
//        System.out.println(kadanesInfo.toString(4));

        JSONObject extractParams = kadanesInfo.getJSONObject("iterableType").getJSONObject("List<Integer>").getJSONObject(returnType);

        int startLine = extractParams.getInt("startLine") - 1, length = extractParams.getInt("length");
        StringBuilder sb = new StringBuilder();
        int[] tabCount = new int[]{-1};
        Files.lines(Path.of(kadanesInfoFilePath.substring(0, kadanesInfoFilePath.lastIndexOf('/') + 1) + kadanesInfo.getString("filename"))).skip(startLine).limit(length).forEach(s -> sb.append(s.substring(tabCount[0] != -1 ? tabCount[0] : (tabCount[0] = s.length() - s.stripLeading().length()))).append('\n'));

        return sb.toString();

    }

    public static void main(String[] args) {
        try {
            System.out.println(kadanesSnippet(new String[]{}, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
