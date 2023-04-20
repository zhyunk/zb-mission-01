package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.Bookmark;

import java.util.List;

public class BookmarkService implements IBookmarkService {
    private final BookmarkDao dao;
    private BookmarkService() {
        this.dao = BookmarkDao.getInstance();
    }

    private static BookmarkService service;
    public static BookmarkService getInstance() {
        if (service == null) {
            service = new BookmarkService();
        }
        return service;
    }

    @Override
    public void deleteBookmarkGroup(int idxBookmarkGroup) {
        dao.deleteBookmarkGroup(idxBookmarkGroup);
    }

    @Override
    public void delete(int idx) {
        dao.delete(idx);
    }

    @Override
    public void insert(Bookmark b) {
        dao.insert(b);
    }

    @Override
    public List<Bookmark> selectAll() {
        return dao.selectAll();
    }

    @Override
    public Bookmark select(int idx) {
        return dao.select(idx);
    }
}

interface IBookmarkService {

    void deleteBookmarkGroup(int idxBookmarkGroup);
    void delete(int idx);
    void insert(Bookmark b);
    List<Bookmark> selectAll();
    Bookmark select(int idx);
}