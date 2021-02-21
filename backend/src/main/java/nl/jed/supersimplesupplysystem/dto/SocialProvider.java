package nl.jed.supersimplesupplysystem.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SocialProvider {

    FACEBOOK("facebook"), TWITTER("twitter"), LINKEDIN("linkedin"), GOOGLE("google"), GITHUB("github"), LOCAL("local");

    @Getter @NonNull
    private final String providerType;

}
