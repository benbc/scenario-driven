<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://pebble.sourceforge.net/pebble" prefix="pebble" %>
<%@ taglib uri="/WEB-INF/url.tld" prefix="url" %>

<%--
  Displays month-by-month archive links.
--%>
<c:if test="${blogType == 'singleblog'}">
<div class="sidebarItem">
  <div class="sidebarItemTitle"><span><fmt:message key="sidebar.archives"/></span></div>
  <div class="sidebarItemBody">
    <table width="99%" cellpadding="0" cellspacing="0">
      <c:forEach var="year" items="${archives}" varStatus="status">
        <c:if test="${status.count % 2 == 1}">
        <tr>
        </c:if>
        <td valign="top">
          <b><fmt:formatDate value="${year.date}" pattern="yyyy"/></b><br />
          <c:forEach var="month" items="${year.archives}">
          <a href="${url:rewrite(month.permalink)}"><fmt:formatDate value="${month.date}" pattern="MMMM"/></a> (<fmt:formatNumber value="${month.numberOfBlogEntries}" type="number" />)
          <br />
          </c:forEach>
          <br />
        </td>
        <c:if test="${status.count % 2 == 0 or status.last}">
        </tr>
        </c:if>
      </c:forEach>
    </table>
  </div>
</div>
</c:if>