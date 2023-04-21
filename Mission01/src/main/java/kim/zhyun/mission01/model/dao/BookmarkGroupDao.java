package kim.zhyun.mission01.model.dao;

import kim.zhyun.mission01.model.dto.BookmarkGroup;
import kim.zhyun.mission01.util.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkGroupDao {
    private final String TABLE = "TB_BOOK_MARK_GROUP";
    private static BookmarkGroupDao dao;
    private BookmarkGroupDao() {}
    public static BookmarkGroupDao getInstance() {
        if (dao == null) {
            dao = new BookmarkGroupDao();
        }
        return dao;
    }

    public void delete(int idx) {
        String sql = "DELETE FROM " + TABLE + " WHERE IDX = ? ";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(BookmarkGroup b) {
        String sql = "UPDATE " + TABLE + " SET NAME = ?, MY_ORDER = ?, UPD_DATETIME = ? WHERE IDX = ?";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getMyOrder());
            pstmt.setString(3, b.getUpdDatetime());
            pstmt.setInt(4, b.getIdx());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(BookmarkGroup b) {
        String sql = "INSERT INTO " + TABLE + " (NAME, MY_ORDER, REG_DATETIME) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setString(1, b.getName());
            pstmt.setInt(2, b.getMyOrder());
            pstmt.setString(3, b.getRegDatetime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BookmarkGroup select(int idx) {
        String sql = "SELECT * FROM " + TABLE + " WHERE IDX = ?";

        BookmarkGroup bmg = null;
        try (PreparedStatement pstmt = DB.getConn().prepareStatement(sql)) {
            pstmt.setInt(1, idx);
            ResultSet rs = pstmt.executeQuery();

            bmg = getResultSetTo(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bmg;
    }
    public List<BookmarkGroup> selectAll() {
        List<BookmarkGroup> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE + " ORDER BY MY_ORDER, UPD_DATETIME, IDX";
        try (ResultSet rs = DB.getStmt().executeQuery(sql)) {
            while (rs.next()) {
                list.add(getResultSetTo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private BookmarkGroup getResultSetTo(ResultSet rs) {
        BookmarkGroup bmg = new BookmarkGroup();
        try {
            bmg.setIdx(rs.getInt("IDX"));
            bmg.setName(rs.getString("NAME"));
            bmg.setMyOrder(rs.getInt("MY_ORDER"));
            bmg.setRegDatetime(rs.getString("REG_DATETIME"));
            bmg.setUpdDatetime(rs.getString("UPD_DATETIME"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bmg;
    }
}