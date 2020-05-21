<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Poll" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ArrayList<Poll> polls = Poll.getAll();
%>
<html>
  <head>
    <title>Polls Central</title>
  </head>
  <body>
  <h1>Polls</h1>
  <ul>
    <%
      for(Poll poll : polls) {
        out.println("<li><a href='poll.jsp?poll_id=" + poll.getId() + "'>" + poll.getTitle() + "</a></li>");
      }
    %>
  </ul>
  </body>
</html>
