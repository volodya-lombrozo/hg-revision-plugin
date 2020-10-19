package domain;

import com.aragost.javahg.Changeset;
import util.ChangesetTags;
import util.ChangesetTime;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PreviousTags implements RecordableProperty {

    private final Changeset changeset;

    public PreviousTags(Changeset changeset) {
        this.changeset = changeset;
    }

    private Tags toTags() {
        return new Tags(findPreviousTags(), "previous");
    }

    private List<String> findPreviousTags() {
        Changeset current = changeset.getParent1();
        List<String> res = new ArrayList<>();
        while (res.isEmpty() && current != null) {
            if (current.tags() != null && !current.tags().isEmpty())
                res.addAll(current.tags());
            current = current.getParent1();
        }
        return res;
    }

    private TimedTags findPreviousTags(Changeset changeset) {
        TimedTags timedTags = new TimedTags(changeset);
        if (timedTags.hasTags())
            return timedTags;
        else return TimedTags.later(findPreviousTags(changeset.getParent1()), findPreviousTags(changeset.getParent2()));
    }

    @Override
    public void fillProperties(Properties properties) {
        toTags().fillProperties(properties);
    }

    @Override
    public String toString() {
        return "PreviousTags{" +
                "changeset=" + changeset +
                ", previous tags=" + findPreviousTags() +
                '}';
    }


    private static class TimedTags {

        private final Instant time;
        private final List<String> tags;

        TimedTags(Changeset changeset) {
            this(new ChangesetTime(changeset).toInstant(), new ChangesetTags(changeset).toTags());
        }

        TimedTags(Instant time, List<String> tags) {
            this.time = time;
            this.tags = tags;
        }

        boolean hasTags() {
            return tags != null && !tags.isEmpty();
        }

        public List<String> getTags() {
            return tags;
        }

        static TimedTags later(TimedTags first, TimedTags second) {
            if (first.time.isAfter(second.time))
                return first;
            else return second;
        }
    }
}
