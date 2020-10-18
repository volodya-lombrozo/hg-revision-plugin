package domain;

import com.aragost.javahg.Changeset;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class CommitDate implements RecordableProperty {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private final Changeset changeset;

    public CommitDate(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.date", this.toString());
    }

    @Override
    public String toString() {
        Date date = changeset.getTimestamp().getDate();
        if (date == null) return "";
        return formatter.format(date.toInstant().atZone(ZoneId.systemDefault()));
    }
}
