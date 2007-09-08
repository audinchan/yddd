<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="pager" uri="http://nestframework.org/tags/pager" %>
<%@ taglib prefix="nestfn" uri="http://nestframework.org/tags/function" %>
<%@ taglib prefix="nest" uri="http://nestframework.org/tags-validate" %>
<%@ taglib prefix="un" uri="http://jakarta.apache.org/taglibs/unstandard-1.0" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<un:bind var="nestframeworkLocale" type="org.nestframework.core.Constant" field="LOCALE_KEY"/>
<fmt:setLocale value="${sessionScope[nestframeworkLocale]}"/>
<fmt:setBundle basename="messages"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<un:bind var="userKey" type="com.yodoo.rent.commons.Constant" field="USER_KEY"/>
