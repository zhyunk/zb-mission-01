package kim.zhyun.mission01.util;

public class PrivateInfo {
    // api 키
    public static final String API_KEY = "597076746e67696d38375672767670";

    // DB 경로 + DB 이름
    private String DB_INFO = "zhSQLite.db";
    public String getDbInfo() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.startsWith("windows"))
            return this.getClass().getClassLoader().getResource(DB_INFO).getPath().replaceFirst("/", "");
        else
            return this.getClass().getClassLoader().getResource(DB_INFO).getPath();
    }
}
