package kim.zhyun.mission01.model.dao;

import kim.zhyun.mission01.model.dto.History;
import kim.zhyun.mission01.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    private final String TABLE = "TB_HISTORY";

    private static HistoryDao dao;
    private HistoryDao() {}
    public static HistoryDao getInstance() {
        if (dao == null) {
            dao = new HistoryDao();
        }
        return dao;
    }

    public void insert(History h) {
        String sql = "INSERT INTO " + TABLE + " (LAT, LNT, REG_DATETIME) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setDouble(1, h.getLat());
            pstmt.setDouble(2, h.getLnt());
            pstmt.setString(3, h.getRegDateTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<History> deleteAll() {
        List<History> list = new ArrayList<>();

        String sb = " SELECT " +
                        " IDX, " +
                        " LNT, " +
                        " LAT, " +
                        " REG_DATETIME " +
                    " FROM " + TABLE +
                    " ORDER BY IDX DESC";

        try (ResultSet resultSet = DB.getStmt().executeQuery(sb)) {
            while (resultSet.next()) {
                History history = new History();
                history.setIdx(resultSet.getInt("IDX"));
                history.setLat(resultSet.getDouble("LAT"));
                history.setLnt(resultSet.getDouble("LNT"));
                history.setRegDateTime(resultSet.getString("REG_DATETIME"));
                list.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void delete(String idx) {
        String sql = "DELETE FROM " + TABLE + " WHERE idx = " + idx;

        try {
            DB.getStmt().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}