package domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class RepoInfoBuilder {
    private String hgBranch;
    private String hgRevision;
    private String hgRevisionHash;
    private String hgAuthor;
    private String hgDescription;
    private String hgDate;
    private String hgBookmarks;
    private Tags tags;

    public RepoInfoBuilder setHgBranch(String hgBranch) {
        this.hgBranch = hgBranch;
        return this;
    }

    public RepoInfoBuilder setHgRevision(String hgRevision) {
        this.hgRevision = hgRevision;
        return this;
    }

    public RepoInfoBuilder setHgRevisionHash(String hgRevisionHash) {
        this.hgRevisionHash = hgRevisionHash;
        return this;
    }

    public RepoInfoBuilder setHgTags(Tags tags) {
        this.tags = tags;
        return this;
    }

    public RepoInfoBuilder setHgTags(Collection<String> tags) {
        return setHgTags(new Tags(tags));
    }

    public RepoInfoBuilder setBookmarks(String bookmarks) {
        this.hgBookmarks = bookmarks;
        return this;
    }

    public RepoInfoBuilder setBookmarks(Collection<String> bookmarks) {
        return setBookmarks(String.join(";", bookmarks));
    }

    public RepoInfoBuilder setHgAuthor(String hgAuthor) {
        this.hgAuthor = hgAuthor;
        return this;
    }

    public RepoInfoBuilder setHgDescription(String hgDescription) {
        this.hgDescription = hgDescription;
        return this;
    }

    public RepoInfoBuilder setHgDate(String hgDate) {
        this.hgDate = hgDate;
        return this;
    }

    public RepoInfoBuilder setHgDate(Date hgDate) {
        if (hgDate == null) return setHgDate("");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return setHgDate(formatter.format(hgDate.toInstant().atZone(ZoneId.systemDefault())));
    }

    public RepoInfo create() {
        return new RepoInfo(hgBranch, hgRevision, hgRevisionHash, tags, hgAuthor, hgDescription, hgDate, hgBookmarks);
    }
}