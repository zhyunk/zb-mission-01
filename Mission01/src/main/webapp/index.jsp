<%@ include file="_common/navigation.jsp" %>
<%@ page import="kim.zhyun.mission01.util.MyHttpServlet" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

        <label for="lat">LAT: </label> <input type="text" id="lat" name="lat" placeholder="0.0" value=""> ,
        <label for="lnt">LNT: </label> <input type="text" id="lnt" name="lnt" placeholder="0.0" value="">
        <button type="button" onclick="getUserLocation()">내 위치 가져오기</button>
        <button type="button" onclick="return geoSubmit()">근처 WIFI 정보 보기</button>

        <br/>
        <br/>

        <table>
            <thead>
                <tr>
                    <th>거리(KM)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>wifi접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </tr>
            </thead>
            <tbody id="tbl_wifi_list">
                <tr class="list-no-data">
                    <th colspan="17">위치 정보를 입력한 후에 조회해주세요</th>
                </tr>
            </tbody>
        </table>

        <br/>
        <br/>

        <script>
            function go(v, d) {
                location.href = "<%= ROOT %>detail/" + encodeURI(v) + "/" + d;
            }
            function success({ coords, timestamp }) {
                const latitude = coords.latitude;   // 위도
                const longitude = coords.longitude; // 경도

                document.getElementById("lat").value = latitude;
                document.getElementById("lnt").value = longitude;
            }

            function getUserLocation() {
                if (!navigator.geolocation) {
                    throw "위치 정보가 지원되지 않습니다.";
                }
                navigator.geolocation.getCurrentPosition(success);
            }

            function geoSubmit() {
                var lat = document.getElementById("lat").value;
                var lnt = document.getElementById("lnt").value;

                if (!lat || !lnt) {
                    alert("내 위치를 입력해 주세요")
                    return false;
                }

                if (!isFloat(lat)) {
                    alert("LAT에 올바른 실수를 입력해 주세요")
                    document.getElementById("lat").focus();
                    return false;
                }
                if (!isFloat(lnt)) {
                    alert("LNT에 올바른 실수를 입력해 주세요")
                    document.getElementById("lnt").focus();
                    return false;
                }

                if (lat && lnt) {
                    var data = {
                        lat: lat,
                        lnt: lnt
                    };

                    ajaxRequest("<%= MyHttpServlet.getUrl(request) + ROOT %>history", data, () => {});
                    ajaxRequest("<%= MyHttpServlet.getUrl(request) + ROOT %>getAround", data, (response) => {
                        document.getElementById("tbl_wifi_list").innerHTML = response;
                    });
                    return true;
                }
            }
            function isFloat(value) {
                floatRegex = /^[-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?$/;

                return floatRegex.test(value);
            }

            function ajaxRequest(url, data, callback) {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        callback(xhr.responseText);
                    }
                };
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                xhr.send('data=' + encodeURIComponent(JSON.stringify(data)));
            }
        </script>
    </body>
</html>