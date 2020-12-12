package domain;


import domain.repo.Changeset;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tags implements RecordableProperty {

    private final List<String> tags;
    private final String postfix;

    public Tags(Changeset changeset) {
        this(changeset.tags());
    }

    public Tags(List<String> tags) {
        this(tags, "");
    }

    public Tags(List<String> tags, String postfix) {
        this.tags = tags;
        this.postfix = postfix;
    }

    @Override
    public void fillProperties(Properties properties) {
        fillAsList(properties);
        fillEach(properties);
        fillFirst(properties);
    }

    private String pointAndPostfix() {
        if (postfix == null || postfix.isEmpty())
            return "";
        else return "." + postfix;
    }

    private void fillFirst(Properties properties) {
        String key = "hg.tag" + pointAndPostfix();
        if (tags.size() > 0) {
            properties.put(key, tags.get(0));
        } else properties.put(key, "");
    }

    private void fillEach(Properties properties) {
        Map<String, String> tagsMap = toMap();
        tagsMap.forEach(properties::setProperty);
    }

    private void fillAsList(Properties properties) {
        properties.setProperty("hg.tags" + pointAndPostfix(), toString());
    }

    public Map<String, String> toMap() {
        return IntStream.range(0, tags.size()).boxed().collect(Collectors.toMap(this::tagKey, this::tagValue));
    }

    private String tagValue(int tagNumber) {
        return tags.get(tagNumber);
    }

    private String tagKey(int tagNumber) {
        return "hg.tags" + pointAndPostfix() + "[" + tagNumber + "]";
    }

    @Override
    public String toString() {
        return String.join(";", tags);
    }
}
