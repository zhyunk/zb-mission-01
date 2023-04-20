<%@ page import="kim.zhyun.mission01.model.dto.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@include file="../common/navigation.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button onclick="location.replace('<%= ROOT %>bookmarkgroup/add')">북마크 그룹 이름 추가</button>
<table>
  <tr>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>순서</th>
    <th>등록일자</th>
    <th>수정일자</th>
    <th>비고</th>
  </tr>
  <%
    List<BookmarkGroup> list = request.getAttribute("list") == null ? null : (List<BookmarkGroup>) request.getAttribute("list") ;
    if (list == null || list.size() == 0) {
  %>
  <tr class="list-no-data">
    <th colspan="6">등록된 북마크 그룹이 없습니다.</th>
  </tr>
  <%
  } else {
    for (BookmarkGroup b: list) {
  %>
  <tr>
    <td><%= b.getIdx() %></td>
    <td><%= b.getName() %></td>
    <td><%= b.getMyOrder() %></td>
    <td><%= b.getRegDatetime()%></td>
    <td><%= b.getUpdDatetime() == null ? "" : b.getUpdDatetime() %></td>
    <td>
      <a href="<%= ROOT %>bookmarkgroup/update?idx=<%= b.getIdx() %>">수정</a>
      <a href="<%= ROOT %>bookmarkgroup/delete?idx=<%= b.getIdx() %>">삭제</a>
    </td>
  </tr>
  <%
    }
  }
  %>
</table>
</body>
</html>