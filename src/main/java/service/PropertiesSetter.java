package service;

import domain.RepoInfo;

public class PropertiesSetter {
    public void setProperties(RepoInfo info) {
        System.setProperty("hg.author", info.getHgAuthor());
        System.setProperty("hg.tags", info.getHgTags());
        System.setProperty("hg.branch", info.getHgBranch());
        System.setProperty("hg.date", info.getHgDate());
        System.setProperty("hg.desc", info.getHgDescription());
        System.setProperty("hg.rev", info.getHgRevision());
        System.setProperty("hg.node", info.getHgRevisionHash());
        System.setProperty("hg.bookmarks", info.getHgBookmarks());
    }

}
