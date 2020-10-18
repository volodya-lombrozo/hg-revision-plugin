package domain;

import com.aragost.javahg.Changeset;

public class RepoInfo {

    private String hgBranch;
    private String hgRevision;
    private String hgRevisionHash;
    private String hgAuthor;
    private String hgDescription;
    private String hgDate;
    private String hgBookmarks;
    private Tags tags;


    public RepoInfo(Changeset changeset, String hgBookmarks) {
        this(changeset.getBranch(), String.valueOf(changeset.getRevision()), changeset.getNode(), new Tags(changeset), changeset.getUser(), changeset.getMessage(), new CommitDate(changeset).toString(), hgBookmarks);
    }

    RepoInfo(String hgBranch, String hgRevision, String hgRevisionHash, Tags tags, String hgAuthor, String hgDescription, String hgDate, String hgBookmarks) {
        this.hgBranch = hgBranch;
        this.hgRevision = hgRevision;
        this.hgRevisionHash = hgRevisionHash;
        this.tags = tags;
        this.hgAuthor = hgAuthor;
        this.hgDescription = hgDescription;
        this.hgDate = hgDate;
        this.hgBookmarks = hgBookmarks;
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

    public Tags getTags() {
        return tags;
    }


    @Override
    public String toString() {
        return "RepoInfo{" +
                "hgBranch='" + hgBranch + '\'' +
                ", hgRevision='" + hgRevision + '\'' +
                ", hgRevisionHash='" + hgRevisionHash + '\'' +
                ", hgAuthor='" + hgAuthor + '\'' +
                ", hgDescription='" + hgDescription + '\'' +
                ", hgDate='" + hgDate + '\'' +
                ", hgBookmarks='" + hgBookmarks + '\'' +
                ", tags=" + tags +
                '}';
    }
}
