package kim.zhyun.mission01.model.service;

import kim.zhyun.mission01.model.IBookmarkGroupService;
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

