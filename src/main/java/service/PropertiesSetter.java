package service;

import domain.RepoInfo;

import java.util.Properties;

public class PropertiesSetter {

    private final Properties props;

    public PropertiesSetter(Properties props) {
        this.props = props;
    }

    public void setProperties(RepoInfo info) {
        props.setProperty("hg.author", info.getHgAuthor());//done
        props.setProperty("hg.branch", info.getHgBranch()); //done
        props.setProperty("hg.date", info.getHgDate()); //done
        props.setProperty("hg.desc", info.getHgDescription()); //done
        props.setProperty("hg.rev", info.getHgRevision()); //done
        props.setProperty("hg.node", info.getHgRevisionHash());
        props.setProperty("hg.bookmarks", info.getHgBookmarks()); //done
        info.getTags().fillProperties(props);
    }

}
