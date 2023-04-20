package kim.zhyun.mission01.controller;

import kim.zhyun.mission01.model.WifiService;
import kim.zhyun.mission01.model.dto.WifiInfo;
import kim.zhyun.mission01.model.dto.Result;
import kim.zhyun.mission01.util.MyHttpServlet;
import kim.zhyun.mission01.util.MyOkHttp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@WebServlet(name = "apiServlet", urlPatterns = "/wifiListUpdate", loadOnStartup = 2)
public class ApiServlet extends HttpServlet {
    private final WifiService service = WifiService.getInstance();

    public void init() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        MyHttpServlet.encoding(request, response);

        MyOkHttp ok = new MyOkHttp();

        // api로 받을수 있는 list 개수
        int cnt = ok.getPossibleCnt();

        List<WifiInfo> list = new ArrayList<>();
        List<CompletableFuture<Result>> listCf = new ArrayList<>();
        for (int i = 0; i < cnt + 1; i += 1000) {
            listCf.add(ok.getApiResult(1 + i, Math.min(1000 + i, cnt)));
        }

        for (CompletableFuture<Result> cf : listCf) {
            cf.thenAccept(result -> {
                list.addAll(result.getWifiListInfo().getRow());
                if (list.size() == cnt) {
                    service.insert(list);
                }
            });
        }

        // db에 저장 된 전체 개수
        request.setAttribute("listCnt", cnt);

        MyHttpServlet.forward(this, request, response, "/load-wifi.jsp");
    }

    // 나와 가까운 거리의 wifi 정보 20개 가져옴
    public List<WifiInfo> getAroundList(String lat, String lnt) {
        return service.select(Double.parseDouble(lat), Double.parseDouble(lnt));
    }
}