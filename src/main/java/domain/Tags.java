package domain;

import com.aragost.javahg.Changeset;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tags implements RecordableProperty {

    private final List<String> tags;

    public Tags(Changeset changeset) {
        this(changeset.tags());
    }

    public Tags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public void fillProperties(Properties properties) {
        fillAsList(properties);
        fillEach(properties);
    }

    private void fillEach(Properties properties) {
        Map<String, String> tagsMap = toMap();
        tagsMap.forEach(properties::setProperty);
    }

    private void fillAsList(Properties properties) {
        properties.setProperty("hg.tags", toString());
    }

    public Map<String, String> toMap() {
        return IntStream.range(0, tags.size()).boxed().collect(Collectors.toMap(this::tagKey, this::tagValue));
    }

    private String tagValue(int tagNumber) {
        return tags.get(tagNumber);
    }

    private String tagKey(int tagNumber) {
        return "hg.tags[" + tagNumber + "]";
    }

    @Override
    public String toString() {
        return String.join(";", tags);
    }
}
