package nl.marijnploeg.kitereparatie.model.Enums;

public enum BucketName {
    PROFILE_IMAGE("kitereperatie_profile");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
