package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.History;

import java.util.List;

public interface IHistoryService {
    void insert(History h);

    List<History> select();

    void delete(String idx);
}
