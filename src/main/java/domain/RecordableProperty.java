package domain;

import java.util.Properties;

public interface RecordableProperty {

    void fillProperties(Properties properties);


    class Fake implements RecordableProperty {
        @Override
        public void fillProperties(Properties ignore) {
        }
    }
}
