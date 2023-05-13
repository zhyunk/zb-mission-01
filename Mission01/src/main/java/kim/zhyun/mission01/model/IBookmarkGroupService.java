package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.BookmarkGroup;

import java.util.List;

public interface IBookmarkGroupService {

    void delete(int idx);

    void insert(BookmarkGroup b);

    void update(BookmarkGroup b);

    BookmarkGroup select(int idx);

    List<BookmarkGroup> selectAll();
}
