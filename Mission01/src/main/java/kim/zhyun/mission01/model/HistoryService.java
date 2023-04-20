package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.History;

import java.util.List;

public class HistoryService implements IHistoryService {
    private static HistoryDao dao;
    public HistoryService() {
        dao = HistoryDao.getInstance();
    }

    private static HistoryService service;
    public static HistoryService getInstance() {
        if (service == null) {
            service = new HistoryService();
        }
        return service;
    }

    @Override
    public void insert(History h) {
        dao.insert(h);
    }

    @Override
    public void delete(String idx) {
        dao.delete(idx);
    }

    @Override
    public List<History> select() {
        return dao.deleteAll();
    }
}

interface IHistoryService {
    void insert(History h);
    List<History> select();
    void delete(String idx);
}