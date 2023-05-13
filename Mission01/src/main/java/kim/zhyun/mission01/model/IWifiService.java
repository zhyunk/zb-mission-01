package kim.zhyun.mission01.model;

import kim.zhyun.mission01.model.dto.WifiInfo;

import java.util.List;

public interface IWifiService {
    WifiInfo select(String idx);

    List<WifiInfo> select(double lat, double lnt);

    int insert(List<WifiInfo> list);
}
