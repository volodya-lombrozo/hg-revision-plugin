package domain.repo;

public class JavaHgChangeset implements Changeset {

    private final com.aragost.javahg.Changeset delegate;

    public JavaHgChangeset(com.aragost.javahg.Changeset delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getUser() {
        return delegate.getUser();
    }

    @Override
    public String getBranch() {
        return delegate.getBranch();
    }
}
