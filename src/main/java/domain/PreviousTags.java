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

    private Tags toTags(TimedTags timedTags) {
        return new Tags(timedTags.getTags(), "previous");
    }

    private TimedTags findPreviousTags() {
        int step = 1;
        return TimedTags.later(findPreviousTags(step, changeset.getParent1()), findPreviousTags(step, changeset.getParent2()));
    }

    private TimedTags findPreviousTags(int step, Changeset changeset) {
        TimedTags timedTags = new TimedTags(step, changeset);
        if (alreadyVisited(timedTags))
            return timedTags;
        else savedAsVisited(timedTags);
        if (timedTags.hasTags() || changeset == null)
            return timedTags;
        else {
            TimedTags firstParent = findPreviousTags(step + 1, changeset.getParent1());
            TimedTags secondParent = findPreviousTags(step + 1, changeset.getParent2());
            return TimedTags.later(firstParent, secondParent);
        }
    }

    private boolean alreadyVisited(TimedTags timedTags) {
        return alreadyVisited.contains(timedTags);
    }

    private void savedAsVisited(TimedTags timedTags) {
        alreadyVisited.add(timedTags);
    }

    @Override
    public void fillProperties(Properties properties) {
        TimedTags previousTags = findPreviousTags();
        toTags(previousTags).fillProperties(properties);
        properties.setProperty("hg.commit.number.from.previous.tag", String.valueOf(previousTags.getStep()));
    }

    @Override
    public String toString() {
        return "PreviousTags{" +
                "changeset=" + changeset +
                ", previous tags=" + findPreviousTags() +
                '}';
    }


    private static class TimedTags {

        private final int step;
        private final Instant time;
        private final List<String> tags;

        TimedTags(int step, Changeset changeset) {
            this(step, new ChangesetTime(changeset).toInstant(), new ChangesetTags(changeset).toTags());
        }

        TimedTags(int step, Instant time, List<String> tags) {
            this.step = step;
            this.time = time;
            this.tags = tags;
        }

        boolean hasTags() {
            return tags != null && !tags.isEmpty();
        }

        public List<String> getTags() {
            return tags;
        }

        public int getStep() {
            return step;
        }

        static TimedTags later(TimedTags first, TimedTags second) {
            if (first.hasTags() && second.hasTags()) {
                if (first.time.isAfter(second.time))
                    return first;
                else return second;
            } else if (first.hasTags()) {
                return first;
            } else return second;
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
