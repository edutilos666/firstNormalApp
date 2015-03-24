<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="springTags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>

<html>
  <head>
    <title>workerForm.jsp</title>
<%--    <c:url value="/resources/css/Random.css" var="random" />
  <link href="${random }" type="text/css" rel="stylesheet" /> --%>
  
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <link href="${contextPath}/resources/css/Random.css" type="text/css" rel="stylesheet" />
  
        <script src="${contextPath}/resources/js/jquery.js"></script>
      <script type="text/javascript" src="${contextPath}/resources/js/jquery-ui.js"></script>
  
  <link rel="stylesheet" href="<springTags:theme code="css"/>" type="text/css" />
  
  <script type="text/javascript">
         $(function(){
             $("button,input[type=submit], input[type=button], a").button(); 
             
             $(".accordion").accordion({
            	 collapsible: true, 
            	 active: false
             });  
        	 
         }); 
   
       </script>
   </head>
   <body>
   <table>

        <tr><td>Change Theme:</td><td><form name="themeChangeForm" method="get">
            <select name="theme" onchange="submitform()">
                <option selected="selected">--</option>
                <option value="default">Default</option>
                <option value="blue">Blue</option>
                <option value="black">Black</option>
            </select>
         </form></td></tr>

</table>

<script type="text/javascript">
function submitform()
{
document.themeChangeForm.submit();
}
</script>
   
   
      <h3>Welcome to the workerForm.jsp page</h3>
       <c:url value="?locale=en" var="en" />
    <c:url value="?locale=de" var="de" />
    <a href="${en }">english</a>
    <a href="${de }">german</a> <br/>
    
          <h3>select a theme</h3>
      <c:url value="?theme=default" var="defaultTheme" />
      <c:url value="?theme=black" var="blackTheme" />
      <c:url value="?theme=blue" var="blueTheme" />
      <a href="${defaultTheme }">default</a> <br/>
      <a href="${blackTheme }">black</a> <br/>
      <a href="${blueTheme }">blue</a> <br/>
      
    
      <h4>add new worker</h4>
      <springForm:form action="/SpringMVCAnnotated3/worker/add" method="POST" commandName="worker">
       <table>
         <tr>
           <td><springTags:message code="worker.fname" /></td>
           <td><springForm:input path="fname"/> <springForm:errors path="fname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="worker.lname" /></td>
           <td><springForm:input path="lname"/> <springForm:errors path="lname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="worker.email" /></td>
           <td><springForm:input path="email"/> <springForm:errors path="email" cssClass="errors" /></td>
         </tr>
         
         <tr>
          <td colspan="2" align="right">
            <button type="submit"><springTags:message code="worker.add" /></button>
          </td>
         </tr>
       </table>
      </springForm:form>
      
      <br/>
      
       <h4>delete worker by id</h4>
      <springForm:form action="/SpringMVCAnnotated3/worker/delete" method="POST" commandName="worker">
       <table>
           <tr>
           <td><springTags:message code="worker.id" /></td>
           <td><springForm:input path="id"/> <springForm:errors path="id" cssClass="errors" /></td>
         </tr>
      <%--    <tr>
           <td><springTags:message code="fname" /></td>
           <td><springForm:input path="fname" readonly="true"/> <springForm:errors path="fname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="lname" /></td>
           <td><springForm:input path="lname"  readonly="true"/> <springForm:errors path="lname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="email" /></td>
           <td><springForm:input path="email"  readonly="true"/> <springForm:errors path="email" cssClass="errors" /></td>
         </tr> --%>
         
         <tr>
          <td colspan="2" align="right">
            <button type="submit"><springTags:message code="worker.delete" /></button>
          </td>
         </tr>
       </table>
      </springForm:form>
      
      
      <br/>
      
             <h4>list all workers </h4>
      <springForm:form action="/SpringMVCAnnotated3/worker/list" method="POST" commandName="worker">
       <table>
<%--            <tr>
           <td><springTags:message code="id" /></td>
           <td><springForm:input path="id"/> <springForm:errors path="id" cssClass="errors" /></td>
         </tr>
         <tr>
           <td><springTags:message code="fname" /></td>
           <td><springForm:input path="fname" readonly="true"/> <springForm:errors path="fname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="lname" /></td>
           <td><springForm:input path="lname"  readonly="true"/> <springForm:errors path="lname" cssClass="errors" /></td>
         </tr>
                  <tr>
           <td><springTags:message code="email" /></td>
           <td><springForm:input path="email"  readonly="true"/> <springForm:errors path="email" cssClass="errors" /></td>
         </tr> --%>
         
         <tr>
          <td colspan="2" align="right">
            <button type="submit"><springTags:message code="worker.list" /></button>
          </td>
         </tr>
       </table>
      </springForm:form>
      
      
      <br/>
      
      <h3>listing all workers...</h3>
     
        <table class="table_home">
         <tr>
          <th><springTags:message code="worker.id" /></th>
          <th><springTags:message code="worker.fname" /></th>
          <th><springTags:message code="worker.lname" /></th>
           <th><springTags:message code="worker.email" /></th>
         </tr>
         <c:forEach items="${listWorkers }" var="worker">
          <tr>
          <td>${worker.id }</td>
          <td>${worker.fname }</td>
          <td>${worker.lname }</td>
          <td>${worker.email }</td>
          </tr>
           </c:forEach>
        </table>
     
   </body>
</html>