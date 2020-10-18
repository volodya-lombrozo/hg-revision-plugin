package service;

import com.aragost.javahg.Repository;

import java.io.File;

public class RepositorySteward {

    private final String baseDir;

    public RepositorySteward(String baseDir) {
        this.baseDir = baseDir;
    }

    public Repository openRepository() {
        return Repository.open(new File(baseDir));
    }

}
