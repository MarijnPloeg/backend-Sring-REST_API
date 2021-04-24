package nl.marijnploeg.kitereparatie.model;

public enum RepairType {
    KITE("Kite"),
    BOARD("Board"),
    BAR("Bar"),
    WETSUIT("Wetsuit");

    private final String type;

    RepairType(String type) {
        this.type = type;
    }

    public static RepairType fromType(String type) {
        if (type.equals("Kite") || type.equals("kite")) {
            return KITE;
        }
        if (type.equals("Board") || type.equals("board")) {
            return BOARD;
        }
        if (type.equals("Bar") || type.equals("bar")) {
            return KITE;
        }
        if (type.equals("Wetsuit") || type.equals("wetsuit")) {
            return KITE;
        }
        throw new UnsupportedOperationException(
                "The type " + type + " is not supported!"
        );
    }

    public String getType() {
        return type;
    }


}
