package service;

import domain.RepoInfo;

import java.util.Properties;

public class PropertiesSetter {

    private final Properties props;

    public PropertiesSetter(Properties props) {
        this.props = props;
    }

    public void setProperties(RepoInfo info) {
        props.setProperty("hg.author", info.getHgAuthor());
        props.setProperty("hg.tags", info.getHgTags());
        props.setProperty("hg.branch", info.getHgBranch());
        props.setProperty("hg.date", info.getHgDate());
        props.setProperty("hg.desc", info.getHgDescription());
        props.setProperty("hg.rev", info.getHgRevision());
        props.setProperty("hg.node", info.getHgRevisionHash());
        props.setProperty("hg.bookmarks", info.getHgBookmarks());
    }

}
