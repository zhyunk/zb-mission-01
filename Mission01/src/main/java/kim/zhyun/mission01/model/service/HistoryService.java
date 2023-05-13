package kim.zhyun.mission01.model.service;

import kim.zhyun.mission01.model.IHistoryService;
import kim.zhyun.mission01.model.dao.HistoryDao;
import kim.zhyun.mission01.model.dto.History;

import java.util.List;

public class HistoryService implements IHistoryService {
    private static HistoryDao dao;
    public HistoryService() {
        dao = HistoryDao.getInstance();
    }

    static class HistoryServiceHolder {
        private static final HistoryService INSTANCE = new HistoryService();
    }
    public static HistoryService getInstance() {
        return HistoryServiceHolder.INSTANCE;
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

