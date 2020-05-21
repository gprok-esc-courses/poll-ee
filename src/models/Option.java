package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Option {
    private int id;
    private String name;
    private int votes;
    private int pollId;

    public Option(int id, String name, int votes, int pollId) {
        this.id = id;
        this.name = name;
        this.votes = votes;
        this.pollId = pollId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public int getPollId() {
        return pollId;
    }

    public static Option find(int optionId) {
        Option option = null;
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM options WHERE id=" + optionId);
            if(rs.next()) {
                option = new Option(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("votes"),
                        rs.getInt("poll_id")
                );
            }
        }
        catch (SQLException se) {
            System.err.println("Poll getAll query failed");
        }
        return option;
    }

    public void vote() {
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            int rows = stmt.executeUpdate("UPDATE options SET votes=" + (votes+1) + " WHERE id=" + id);
            if(rows != 0) {
                votes++;
            }
        }
        catch (SQLException se) {
            System.err.println("Vote query failed");
        }
    }

    public static ArrayList<Option> getAllForPoll(int pollID) {
        ArrayList<Option> options = new ArrayList<>();
        try {
            Connection db = Database.getConnection();
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM options WHERE poll_id=" + pollID);
            while(rs.next()) {
                Option option = new Option(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("votes"),
                        rs.getInt("poll_id")
                );
                options.add(option);
            }
        }
        catch (SQLException se) {
            System.err.println("Option  getAll query failed");
        }
        return options;
    }
}
