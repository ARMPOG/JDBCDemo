package manager;

import db.DBConnectionProvider;
import model.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

   private Connection connection;

    public BookManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    // CREATE
    public Books save(Books book) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement
                    ("Insert into number(name ,author,pages) Values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPages());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    public boolean saveAll(List<Books> books) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement
                    ("Insert into Books(name ,author,pages) Values(?,?,?)");
            for (Books book : books) {
                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthor());
                statement.setInt(3, book.getPages());
                statement.addBatch();
            }
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //READ
    public List<Books> getAllBooks() {
        List<Books> result = new ArrayList<>();
        String query = "SELECT * FROM Books";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(Books.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .author(resultSet.getString("author"))
                        .pages(resultSet.getInt("pages"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public Books getABookByName(String bookName){
        Books result = new Books();
        String query = "SELECT * FROM Books WHERE name=?";

        try {
           PreparedStatement statement = connection.prepareStatement(query);
           statement.setString(1,bookName);
           ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = Books.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .author(resultSet.getString("author"))
                        .pages(resultSet.getInt("pages"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //UPDATE
    public void updateByBookName(String bookName){
        String query = "UPDATE Books SET name = 'Pnin', pages = 143 Where name=?";
        PreparedStatement statement=null;
        try {
           statement = connection.prepareStatement(query);
            statement.setString(1,bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int updated =0;
        try {
            updated=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (updated>0) System.out.println("Updated ");
    }

    //DELETE
    public void deleteAllBooks() {
        String query = "DELETE FROM Books";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int allDeleted = 0;
        try {
            allDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (allDeleted > 0) {
            System.out.println("All rows  deleted successfully!");
        }
    }

    public void deleteABook(String bookname) {
        String query = "DELETE FROM Books Where name=? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookname);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

