package config;


public class UserConfiguration {
    private String saveTo;
    private boolean threadEnabled;

    public static UserConfigurationBuilder builder() {
        return new UserConfigurationBuilder();
    }

    public static final class UserConfigurationBuilder {
        private String saveTo;
        private boolean threadEnabled;

        private UserConfigurationBuilder() {
        }

        public UserConfigurationBuilder saveTo(String saveTo) {
            this.saveTo = saveTo;
            return this;
        }

        public UserConfigurationBuilder threadEnabled(boolean threadEnabled) {
            this.threadEnabled = threadEnabled;
            return this;
        }

        public UserConfiguration build() {
            UserConfiguration userConfiguration = new UserConfiguration();
            userConfiguration.setSaveTo(saveTo);
            userConfiguration.setThreadEnabled(threadEnabled);
            return userConfiguration;
        }
    }

    public String getSaveTo() {
        return saveTo;
    }

    public void setSaveTo(String saveTo) {
        this.saveTo = saveTo;
    }

    public boolean isThreadEnabled() {
        return threadEnabled;
    }

    public void setThreadEnabled(boolean threadEnabled) {
        this.threadEnabled = threadEnabled;
    }


}
