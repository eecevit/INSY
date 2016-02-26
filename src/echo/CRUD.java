package echo;

import java.sql.*;
import java.time.Clock;

import org.postgresql.ds.PGSimpleDataSource;

public class CRUD {
    private Connection con;
    private PreparedStatement prep;
    private ResultSet res;
    // create read update delete.
    PGSimpleDataSource ds = new PGSimpleDataSource();

    public void insert(String num, String vname, String nname){
        try {
            String sql = "INSERT INTO Person (nummer, vorname, nachname) VALUES (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, num);
            statement.setString(2, vname);
            statement.setString(3, nname);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("a new person was inserted");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public ResultSet select() {
        try {
            String sql = "SELECT * FROM Person";

            //Statement statement = con.createStatement();
            ResultSet result = prep.executeQuery(sql);

            int count = 0;

            while (result.next()) {
                String nummer = result.getString(1);
                String nname = result.getString(3);

                String output = "Person #%d: %s - %s";
                System.out.println(String.format(output, ++count, nummer, nname));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String vname, String nname, String num){
        try {
            String sql = "UPDATE Person SET vorname=?, nachname=? WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, vname);
            statement.setString(2, nname);
            statement.setString(3, num);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("an existing person was updated");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String num){
        try {
            String sql = "DELETE FROM Person WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, num);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("a person was deleted");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
