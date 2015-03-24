<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="springTags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>

<html>
  <head>
    <title>personForm.jsp</title>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
  <c:url value="${contextPath}/resources/css/Random.css" var="random" />
  <link href="${contextPath}/resources/css/Random.css" type="text/css" rel="stylesheet" />
  
        
      <c:url value="/resources/themes/le-frog/jquery-ui.css"  var="leFrog" />
      <c:url value="/resources/js/jquery.js" var="jquery" />
      <c:url value="/resources/js/jquery-ui.js" var="jqueryUI" />
      <c:url value="/resources/css/Main.css" var="Main" />
      
      <script type="text/javascript" src="${jquery }"></script>
      <script type="text/javascript" src="${jqueryUI}"></script>
  
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
    ${pageContext.request.contextPath} <br/>
  
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
      
      
      <c:url value="/worker/" var="go_worker"/>
      <a href="${go_worker}">go to worker</a>
       <br/>
      
     <h3>Welcome to the personForm.jsp page</h3>
      <c:url value="/listPersons" var="list" />
      <a href="${list }">list all persons</a>
      <br/>
     
      <springForm:form action="addPerson" method="POST" commandName="person">
       <table class="table_home">
       <tr>
         <td><springTags:message code="person.age" /> </td>
         <td><springForm:input path="age"/> 
         <springForm:errors path="age" cssClass="errors" />
         </td>
       </tr>
           <tr>
         <td><springTags:message code="person.fname" /></td>
         <td><springForm:input path="fname"/> 
         <springForm:errors path="fname" cssClass="errors" />
         </td>
       </tr>
           <tr>
         <td><springTags:message code="person.lname" /></td>
         <td><springForm:input path="lname"/> 
         <springForm:errors path="lname" cssClass="errors" />
         </td>
       </tr>
           <tr>
         <td><springTags:message code="person.email" /></td>
         <td><springForm:input path="email"/> 
         <springForm:errors path="email" cssClass="errors" />
         </td>
       </tr>
           <tr>
       <td colspan="2" align="right">
         <input type="submit" value="Add new Person" />
       </td>
       </tr>
       </table>
      </springForm:form>
      <br/>
      <h3>list all persons from db</h3>
      <c:choose>
        <c:when test="${not empty listPersons }">
         <table class="table_home">
           <tr>
           <th>age</th>
            <th>fname</th>
            <th>lname</th>
            <th>email</th>
           </tr>
          <c:forEach items="${listPersons }" var="person">
            <tr>
              <td>${person.age }</td>
              <td>${person.fname }</td>
              <td>${person.lname}</td>
              <td>${person.email}</td>
            </tr>
        </c:forEach>
         </table>
        
   
        </c:when>
        
        <c:otherwise>
          <h3>There is not any person in db</h3>
        </c:otherwise>
      </c:choose>
  </body>
</html>