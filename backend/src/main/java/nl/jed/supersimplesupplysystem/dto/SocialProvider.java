package nl.jed.supersimplesupplysystem.dto;

public enum SocialProvider {

    FACEBOOK("facebook"), TWITTER("twitter"), LINKEDIN("linkedin"), GOOGLE("google"), GITHUB("github"), LOCAL("local");

    @Getter
    private String providerType;

    SocialProvider(final String providerType) {
        this.providerType = providerType;
    }
}
