package nl.marijnploeg.kitereparatie.model;

public enum BrandType {

    KITE("Kitesurfing brand"),
    BOARD("Board brand"),
    WETSUIT("Wetsuit brand");

    private final String type;

    BrandType(String type) {
        this.type = type;
    }
}
