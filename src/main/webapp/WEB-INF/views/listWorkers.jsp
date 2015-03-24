<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="springTags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>

<html>
  <head>
    <title>listWorkers.jsp</title>
<c:url value="/resources/css/Random.css" var="random" />
  <link href="${random }" type="text/css" rel="stylesheet" />
  
        
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
      <h3>Welcome to the listWorkers.jsp page</h3>
         <c:url value="/worker/" var="back_home" />
         <a href="${back_home }">go to the home page for workers</a> <br/>
         
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