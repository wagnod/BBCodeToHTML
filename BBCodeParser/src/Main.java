import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static final Map<String, String> TAGS_MAP = initTagsMap();

    static Map<String, String> initTagsMap() {
        Map<String, String> map = new HashMap<>();
        map.put("[b]", "[/b]");
        map.put("[u]", "[/u]");
        map.put("[i]", "[/i]");
        map.put("[s]", "[/s]");
        map.put("[img]", "[/img]");
        map.put("[code]", "[/code]");
        return map;
    }

    public static void main(String[] args) {
        try {
            handleFile(args[0], args[1]);
        } catch (IOException e) {
            System.err.println("IO?");
        }
    }

    private static void handleFile(String inputFileName, String outputFileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(inputFileName)), StandardCharsets.UTF_8);
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8);
        String s = new NodeDefault(content).toHTML();
        out.append(s);
        out.close();
    }
}