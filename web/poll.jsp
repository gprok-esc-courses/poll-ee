<%@ page import="models.Poll" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Option" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int pollId = Integer.parseInt(request.getParameter("poll_id"));
    Poll poll = Poll.find(pollId);
    ArrayList<Option> options = poll.getOptions();

%>
<html>
<head>
    <title>Polls Central</title>
</head>
<body>
    <h1><% out.println(poll.getTitle()); %></h1>

    <form method="post" action="/vote.do">
        <input type="text" name="poll_id" hidden value='<% out.println(poll.getId()); %>'>
    <%
        for(Option option : options) {
            out.println("<input type='" + poll.getType() + "' name='options' value='" + option.getId() + "'>");
            out.println("<label>" + option.getName() + "</label><br>");
        }
    %>
        <button type="submit">Vote</button>
    </form>
</body>
</html>
