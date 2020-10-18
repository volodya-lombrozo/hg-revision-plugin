package domain;

import com.aragost.javahg.Changeset;

import java.util.Properties;

public class Node implements Recordable {

    private final Changeset changeset;

    public Node(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.node", this.toString());
    }

    @Override
    public String toString() {
        return changeset.getNode();
    }
}
