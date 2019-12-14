import java.util.ArrayList;

public class NodeDefault extends Node {

    NodeDefault(String data) {
        super(data);
    }

    @Override
    String toHTML() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Node> children = transformTokensIntoNodes(splitIntoTokens(data));
        if (children.size() == 0) return "";
        if (children.size() == 1) return children.get(0).data;
        for (Node child : children) {
            sb.append(child.toHTML());
        }
        return sb.toString();
    }
}