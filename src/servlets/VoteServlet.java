package servlets;

import models.Option;
import models.Poll;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "VoteServlet")
public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String [] optionsList = request.getParameterValues("options");
        int pollId = Integer.parseInt(request.getParameter("poll_id"));

        for(String s : optionsList) {
            // out.println(s);
            int id = Integer.parseInt(s);
            Option option = Option.find(id);
            option.vote();
        }

        Poll poll = Poll.find(pollId);
        poll.vote();
        out.println("<h1>" + poll.getTitle() + "</h1>");
        out.println("<h2>Results</h2>");

        ArrayList<Option> options = poll.getOptions();
        for (Option option : options) {
            out.println(option.getName() + " (" + option.getVotes() + ")<br>");
        }

        out.println("<div><a href='index.jsp'>Polls</a></div>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
