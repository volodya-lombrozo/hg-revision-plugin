package domain;

public class RepoInfo {

    String hgBranch;
    String hgRevision;
    String hgRevisionHash;
    String hgTags;
    String hgAuthor;
    String hgDescription;
    String hgDate;
    String hgBookmarks;

    RepoInfo(String hgBranch, String hgRevision, String hgRevisionHash, String hgTags, String hgAuthor, String hgDescription, String hgDate, String hgBookmarks) {
        this.hgBranch = hgBranch;
        this.hgRevision = hgRevision;
        this.hgRevisionHash = hgRevisionHash;
        this.hgTags = hgTags;
        this.hgAuthor = hgAuthor;
        this.hgDescription = hgDescription;
        this.hgDate = hgDate;
        this.hgBookmarks = hgBookmarks;
    }

    public static RepoInfo create(String hgBranch, String hgRevision, String hgRevisionHash, String hgTags, String hgAuthor, String hgDescription, String hgDate, String hgBookmarks) {
        return new RepoInfoBuilder().setHgBranch(hgBranch).setHgRevision(hgRevision).setHgRevisionHash(hgRevisionHash).setHgTags(hgTags).setHgAuthor(hgAuthor).setHgDescription(hgDescription)
                .setBookmarks(hgBookmarks)
                .setHgDate(hgDate).create();
    }

    public String getHgBranch() {
        return hgBranch;
    }

    public String getHgRevision() {
        return hgRevision;
    }

    public String getHgRevisionHash() {
        return hgRevisionHash;
    }

    public String getHgTags() {
        return hgTags;
    }

    public String getHgAuthor() {
        return hgAuthor;
    }

    public String getHgDescription() {
        return hgDescription;
    }

    public String getHgDate() {
        return hgDate;
    }

    public String getHgBookmarks() {
        return hgBookmarks;
    }
}
