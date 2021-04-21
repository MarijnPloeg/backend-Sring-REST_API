package nl.marijnploeg.kitereparatie.model;

public enum BoardType {
    TWINTIP("Twintip"),
    DIRECTIONAL("Directional");

    private final String type;

    BoardType(String type) {
        this.type = type;
    }
}
