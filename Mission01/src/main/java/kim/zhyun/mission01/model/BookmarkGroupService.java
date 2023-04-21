package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dao.BookmarkGroupDao;
import kim.zhyun.mission01.model.dto.BookmarkGroup;

import java.util.List;

public class BookmarkGroupService implements IBookmarkGroupService {
    private final BookmarkGroupDao dao;
    private BookmarkGroupService() {
        this.dao = BookmarkGroupDao.getInstance();
    }

    private static BookmarkGroupService service;
    public static BookmarkGroupService getInstance() {
        if (service == null) {
            service = new BookmarkGroupService();
        }
        return service;
    }

    @Override
    public void delete(int idx) {
        dao.delete(idx);
        BookmarkService.getInstance().deleteBookmarkGroup(idx);
    }

    @Override
    public void insert(BookmarkGroup b) {
        dao.insert(b);
    }

    @Override
    public void update(BookmarkGroup b) {
        dao.update(b);
    }

    @Override
    public BookmarkGroup select(int idx) {
        return dao.select(idx);
    }

    @Override
    public List<BookmarkGroup> selectAll() {
        return dao.selectAll();
    }
}

interface IBookmarkGroupService {

    void delete(int idx);
    void insert(BookmarkGroup b);
    void update(BookmarkGroup b);
    BookmarkGroup select(int idx);
    List<BookmarkGroup> selectAll();
}