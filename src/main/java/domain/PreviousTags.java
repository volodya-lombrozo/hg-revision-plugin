package domain;

import com.aragost.javahg.Changeset;
import util.ChangesetTags;
import util.ChangesetTime;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class PreviousTags implements RecordableProperty {

    private final Changeset changeset;
    private final HashSet<TimedTags> alreadyVisited = new HashSet<>();

    public PreviousTags(Changeset changeset) {
        this.changeset = changeset;
    }

    private Tags toTags() {
        return new Tags(findPreviousTags(), "previous");
    }

    private List<String> findPreviousTags() {
        TimedTags previousTags = TimedTags.later(findPreviousTags(changeset.getParent1()), findPreviousTags(changeset.getParent2()));
        return previousTags.getTags();
    }

    private TimedTags findPreviousTags(Changeset changeset) {
        TimedTags timedTags = new TimedTags(changeset);
        if(alreadyVisited.contains(timedTags))
            return timedTags;
        else alreadyVisited.add(timedTags);
        if (timedTags.hasTags() || changeset == null)
            return timedTags;
        else {
            TimedTags firstParent = findPreviousTags(changeset.getParent1());
            TimedTags secondParent = findPreviousTags(changeset.getParent2());
            return TimedTags.later(firstParent, secondParent);
        }
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


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TimedTags timedTags = (TimedTags) o;
            return Objects.equals(time, timedTags.time) &&
                    Objects.equals(tags, timedTags.tags);
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, tags);
        }
    }
}
