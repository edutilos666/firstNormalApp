<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="springTags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>

<html>
  <head>
    <title>listPersons.jsp</title>
<c:url value="/resources/css/Random.css" var="random" />
<%--   <link href="${random }" type="text/css" rel="stylesheet" /> --%>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
     <h3>Welcome to the listPersons.jsp page</h3>
   <c:url value="/" var="back_home" />
   <a href="${back_home }">go to the homepage</a>
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