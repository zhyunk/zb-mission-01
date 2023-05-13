package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.Bookmark;

import java.util.List;

public interface IBookmarkService {

    void deleteBookmarkGroup(int idxBookmarkGroup);

    void delete(int idx);

    void insert(Bookmark b);

    List<Bookmark> selectAll();

    Bookmark select(int idx);
}
