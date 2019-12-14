import java.util.ArrayList;
import java.util.Arrays;

public abstract class Node {
    public String data;

    Node(String data) {
        this.data = data;
    }

    ArrayList<String> splitIntoTokens(String data) {
        return new ArrayList<>(Arrays.asList(data.split("" +
                "(?<=\\[b])|(?=\\[b])|(?<=\\[/b])|(?=\\[/b])|" +
                "(?<=\\[u])|(?=\\[u])|(?<=\\[/u])|(?=\\[/u])|" +
                "(?<=\\[i])|(?=\\[i])|(?<=\\[/i])|(?=\\[/i])|" +
                "(?<=\\[s])|(?=\\[s])|(?<=\\[/s])|(?=\\[/s])|" +
                "(?<=\\[img])|(?=\\[img])|(?<=\\[/img])|(?=\\[/img])|" +
                "(?<=\\[code])|(?=\\[code])|(?<=\\[/code])|(?=\\[/code])"
        )));
    }

    ArrayList<Node> transformTokensIntoNodes(ArrayList<String> tokens) {
        boolean nodeDefault = true;
        String currentToken = "";
        ArrayList<Node> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            if (nodeDefault) {
                if (!Main.TAGS_MAP.keySet().contains(tokens.get(i))) sb.append(tokens.get(i));
                else {
                    result.add(new NodeDefault(sb.toString()));
                    sb.setLength(0);
                    nodeDefault = false;
                    currentToken = tokens.get(i);
                }
            } else {
                if (!Main.TAGS_MAP.get(currentToken).equals(tokens.get(i))) sb.append(tokens.get(i));
                else {
                    switch (currentToken) {
                        case "[b]":
                            result.add(new NodeB(sb.toString()));
                            break;
                        case "[i]":
                            result.add(new NodeI(sb.toString()));
                            break;
                        case "[s]":
                            result.add(new NodeS(sb.toString()));
                            break;
                        case "[u]":
                            result.add(new NodeU(sb.toString()));
                            break;
                        case "[img]":
                            result.add(new NodeIMG(sb.toString()));
                            break;
                        case "[code]":
                            result.add(new NodeCODE(sb.toString()));
                            break;
                        default:
                            break;
                    }
                    sb.setLength(0);
                    nodeDefault = true;
                    currentToken = "";
                }
            }
        }
        if (sb.length() > 0) {
            result.add(new NodeDefault(sb.toString()));
        }
        return result;
    }

    abstract String toHTML();
}