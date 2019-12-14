import java.util.ArrayList;

public class NodeCODE extends Node {
    NodeCODE(String data) {
        super(data);
    }

    @Override
    String toHTML() {
        StringBuilder sb = new StringBuilder("<code>");
        ArrayList<Node> children = transformTokensIntoNodes(splitIntoTokens(data));
        if (children.size() == 0) return "";
        if (children.size() == 1) {
            sb.append(children.get(0).data);
        } else {
            for (Node child : children) {
                sb.append(child.toHTML());
            }
        }
        sb.append("</code>");
        return sb.toString();
    }
}