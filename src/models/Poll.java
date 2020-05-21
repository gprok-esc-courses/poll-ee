package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Poll {
    private int id;
    private String title;
    private int votes;
    private String type;

    public Poll(int id, String title, int votes, String type) {
        this.id = id;
        this.title = title;
        this.votes = votes;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getVotes() {
        return votes;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Option> getOptions() {
        ArrayList<Option> options = Option.getAllForPoll(this.id);
        return options;
    }

    public void vote() {
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            int rows = stmt.executeUpdate("UPDATE polls SET votes=" + (votes+1) + " WHERE id=" + id);
            if(rows != 0) {
                votes++;
            }
        }
        catch (SQLException se) {
            System.err.println("Vote query failed");
        }
    }

    public static Poll find(int pollId) {
        Poll poll = null;
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM polls WHERE id=" + pollId);
            if(rs.next()) {
                poll = new Poll(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("votes"),
                        rs.getString("type")
                );
            }
        }
        catch (SQLException se) {
            System.err.println("Poll getAll query failed");
        }
        return poll;
    }

    public static ArrayList<Poll> getAll() {
        ArrayList<Poll> polls = new ArrayList<>();
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM polls");
            while(rs.next()) {
                Poll poll = new Poll(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("votes"),
                        rs.getString("type")
                        );
                polls.add(poll);
            }
        }
        catch (SQLException se) {
            System.err.println("Poll getAll query failed");
        }
        return polls;
    }

    public static void main(String[] args) {
        ArrayList<Poll> polls = Poll.getAll();
        for (Poll poll: polls) {
            System.out.println(poll.getTitle());
            ArrayList<Option> options = poll.getOptions();
            for (Option option : options) {
                System.out.println("\t" + option.getName());
            }
        }
    }
}
