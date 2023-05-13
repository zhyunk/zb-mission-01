package kim.zhyun.mission01.model.service;

import kim.zhyun.mission01.model.IWifiService;
import kim.zhyun.mission01.model.dao.WifiDao;
import kim.zhyun.mission01.model.dto.WifiInfo;

import java.util.List;

public class WifiService implements IWifiService {
    private final WifiDao dao;
    private WifiService() {
        this.dao = WifiDao.getInstance();
    }

    static class WifiServiceHolder {
        private static final WifiService INSTANCE = new WifiService();
    }
    public static WifiService getInstance() {
        return WifiServiceHolder.INSTANCE;
    }

    @Override
    public int insert(List<WifiInfo> list) {
        if (list == null || list.size() == 0) return 0;
        int insertCnt = 0;

        for (int i = 0; i < list.size(); i += 1000) {
            insertCnt += dao.insertAll(list.subList(i,  Math.min(1000 + i, list.size())));
        }

        return insertCnt;
    }

    @Override
    public WifiInfo select(String idx) {
        return dao.select(idx);
    }

    @Override
    public List<WifiInfo> select(double lat, double lnt) {
        return dao.selectAll(lat, lnt);
    }
}

