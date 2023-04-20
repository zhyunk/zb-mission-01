package kim.zhyun.mission01.controller;

import kim.zhyun.mission01.model.BookmarkGroupService;
import kim.zhyun.mission01.model.dto.BookmarkGroup;
import kim.zhyun.mission01.util.MyDateTime;
import kim.zhyun.mission01.util.MyHttpServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="bookmarkgroupServlet", urlPatterns = "/bookmarkgroup/*")
public class BookmarkGroupServlet extends HttpServlet {
    private final BookmarkGroupService service = BookmarkGroupService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String[] uris = req.getRequestURI().split("/");
        int maxIdxUris = uris.length - 1;
        String title = "";
        String uri = "";
        String cmd = "";

        if (uris[maxIdxUris].equals("add")) {
            title = " 추가";
            uri = "detail.jsp";
            cmd = "add";

        } else if (uris[maxIdxUris].equals("update")) {
            int idx = Integer.parseInt(req.getParameter("idx"));

            title = " 수정";
            uri = "detail.jsp";
            cmd = "update";

            BookmarkGroup bmg = service.select(idx);
            req.setAttribute("bmg", bmg);

        } else if (uris[maxIdxUris].equals("delete")) {
            int idx = Integer.parseInt(req.getParameter("idx"));
            service.delete(idx);
            req.setAttribute("list", service.selectAll());

            title = "";
            uri = "";
            cmd = "";

        } else {
            req.setAttribute("list", service.selectAll());
        }

        req.setAttribute("pageTitle", "북마크 그룹" + title);
        req.setAttribute("cmd", cmd);

        MyHttpServlet.forward(this, req, resp, "/_bookmarkgroup/" + uri);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        MyHttpServlet.encoding(req, resp);

        String[] uris = req.getRequestURI().split("/");
        int uriMaxIdx = uris.length - 1;


        BookmarkGroup b = new BookmarkGroup();
        b.setName(req.getParameter("name"));
        b.setMyOrder(Integer.parseInt(req.getParameter("order")));

        List<BookmarkGroup> list = null;

        if (uris[uriMaxIdx].equals("add")) {
            b.setRegDatetime(MyDateTime.getNow());

            service.insert(b);
            list = service.selectAll();

            req.setAttribute("list", list);

        } else if (uris[uriMaxIdx].equals("update")) {
            b.setIdx(Integer.parseInt(req.getParameter("idx")));
            b.setUpdDatetime(MyDateTime.getNow());

            service.update(b);

            req.setAttribute("list", service.selectAll());
        }

        req.setAttribute("pageTitle", "북마크 그룹");

        MyHttpServlet.forward(this, req, resp, "/_bookmarkgroup/");
    }
}