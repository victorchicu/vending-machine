package challenge_1.domain;

public enum OAuth2Provider {
    LOCAL("local");

    private final String value;

    OAuth2Provider(String value) {
        this.value = value;
    }

    public static OAuth2Provider fromString(String provider) {
        for (OAuth2Provider oAuth2Provider : OAuth2Provider.values()) {
            if (oAuth2Provider.toString().equalsIgnoreCase(provider)) {
                return oAuth2Provider;
            }
        }
        throw new IllegalArgumentException("No enum constant " + provider);
    }

    @Override
    public String toString() {
        return value;
    }
}
