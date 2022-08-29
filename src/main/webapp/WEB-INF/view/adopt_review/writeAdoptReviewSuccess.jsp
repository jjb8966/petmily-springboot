<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>
    alert("성공적으로 등록 되었습니다.");

    <c:if test="${authUser.grade == '관리자'}">
        location.href = "/admin/board?kindOfBoard=${param.kindOfBoard}";
    </c:if>
    <c:if test="${authUser.grade == '일반'}">
        location.href = "/adopt_review/list?kindOfBoard=${param.kindOfBoard}";
    </c:if>
</script>