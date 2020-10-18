package domain;

import com.aragost.javahg.Changeset;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tags implements Recordable {

    private final Collection<String> tags;

    public Tags(Changeset changeset) {
        this(changeset.tags());
    }

    public Tags(Collection<String> tags) {
        this.tags = tags;
    }

    @Override
    public void fillProperties(Properties properties) {
        Map<String, String> tagsMap = toMap();
        tagsMap.forEach(properties::setProperty);
        properties.setProperty("hg.tags", toString());
    }

    public Map<String, String> toMap() {
        return IntStream.range(0, tags.size()).boxed().collect(Collectors.toMap(this::tagKey, this::tagValue));
    }

    private String tagValue(int tagNumber) {
        return "";
    }

    private String tagKey(int tagNumber) {
        return "ht.tags[" + tagNumber + "]";
    }

    @Override
    public String toString() {
        return String.join(";", tags);
    }
}
