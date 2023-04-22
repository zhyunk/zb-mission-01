<%@ page import="kim.zhyun.mission01.model.dto.WifiInfo" %><%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ page import="java.util.List" %><%
    List<WifiInfo> list = (List<WifiInfo>) request.getAttribute("list");
    if (list == null || list.size() == 0) {
%>
<tr class="list-no-data">
    <% if (list.size() == 0){ %>
    <th colspan="17">Open API 와이파이 정보 가져오기를 눌러주세요</th>
    <% } %>
</tr>
<%
} else {
    for (WifiInfo i: list) {
%>
<tr>
    <td><%= String.format("%.6f", i.getDistance()) %></td>
    <td><%= i.getX_SWIFI_MGR_NO() %></td>
    <td><%= i.getX_SWIFI_WRDOFC() %></td>
    <td><a href="#" onclick="return go('<%= i.getX_SWIFI_MGR_NO() %>', '<%= i.getDistance() %>');"><%= i.getX_SWIFI_MAIN_NM() %></a></td>
    <td><%= i.getX_SWIFI_ADRES1() %></td>
    <td><%= i.getX_SWIFI_ADRES2() %></td>
    <td><%= i.getX_SWIFI_INSTL_FLOOR() %></td>
    <td><%= i.getX_SWIFI_INSTL_TY() %></td>
    <td><%= i.getX_SWIFI_INSTL_MBY() %></td>
    <td><%= i.getX_SWIFI_SVC_SE() %></td>
    <td><%= i.getX_SWIFI_CMCWR() %></td>
    <td><%= i.getX_SWIFI_CNSTC_YEAR() %></td>
    <td><%= i.getX_SWIFI_INOUT_DOOR() %></td>
    <td><%= i.getX_SWIFI_REMARS3() %></td>
    <td><%= i.getLAT() %></td>
    <td><%= i.getLNT() %></td>
    <td><%= i.getWORK_DTTM() %></td>
</tr>
<%      }
}
%>