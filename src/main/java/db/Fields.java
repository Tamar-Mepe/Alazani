package db;

public class Fields {
    public static final String INT = "int";
    public static final String DOUBLE = "double";
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String AUTO_INCREMENT = "AUTO_INCREMENT";
    public static final String TIMESTAMP = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP";

    public static final String[] ID = new String[]{INT, PRIMARY_KEY, AUTO_INCREMENT};
    public static final Object DATE = new String[]{TIMESTAMP, "DEFAULT CURRENT_TIMESTAMP"};

    public static String varchar(int num) {
        return "varchar(" + num + ")";
    }
}
