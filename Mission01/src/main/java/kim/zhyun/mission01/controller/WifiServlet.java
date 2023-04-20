package kim.zhyun.mission01.controller;

import kim.zhyun.mission01.model.BookmarkGroupService;
import kim.zhyun.mission01.model.WifiService;
import kim.zhyun.mission01.model.dto.WifiInfo;
import kim.zhyun.mission01.util.DB;
import kim.zhyun.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "wifiServlet", urlPatterns = "/detail/*", loadOnStartup = 1)
public class WifiServlet extends HttpServlet {
    WifiService service = WifiService.getInstance();

    @Override
    public void init() {
        DB.open();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String idx;
        double distance;

        String[] uri = req.getRequestURI().split("/");
        int maxIdxUri = uri.length - 1;
        if (maxIdxUri > 2) {
            idx = uri[maxIdxUri - 1];
            distance = Double.parseDouble(uri[maxIdxUri]);
        } else {
            idx = uri[maxIdxUri];
            distance = 0.0;
        }

        WifiInfo wi = service.select(idx);
        wi.setDistance(distance);

        req.setAttribute("wifiInfo", wi);
        req.setAttribute("bmgList", BookmarkGroupService.getInstance().selectAll());

        MyHttpServlet.forward(this, req, resp, "detail.jsp");
    }
}
