<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    function queryList() {
        var pageNo=$("#pagenum").val();
        //正则表达式
        var reg= /^[1-9]\d*$/;
        if (!reg.test(pageNo)) {
            alert("请输入正确的页码");
            return;
        }else {}
        window.location.href="${requestURI}?pageNum="+pageNo+"${queryStr}";
    }
</script>
<tr align="right" bgcolor="#EEF4EA">
    <td height="36" colspan="12" align="center">
        <div>
            <a href="${requestURI}?pageNum=1${queryStr}">首页</a>
            <a href="${requestURI}?pageNum=${page.prePage}${queryStr}">上一页</a>
            <c:forEach items="${page.navigatepageNums}" var="pageNum">
                <c:if test="${pageNum == page.pageNum}">
                                <span style="color: black;font-weight: bold" >
                                    ${pageNum}页
                                </span>
                </c:if>
                <c:if test="${pageNum != page.pageNum}">
                    <a href="${requestURI}?pageNum=${pageNum}">${pageNum}页</a>
                </c:if>
            </c:forEach>
            <a href="${requestURI}?pageNum=${page.pageNum+1}${queryStr}">下一页</a>
            <a href="${requestURI}?pageNum=${page.pages}${queryStr}">末页</a>
            &nbsp;&nbsp;跳转到<input size="1" id="pagenum" onblur="queryList()">页
        </div>
    </td>
</tr>
