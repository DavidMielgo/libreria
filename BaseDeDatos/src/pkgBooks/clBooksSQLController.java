package pkgBooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pkgConexiones.clConexionSingleton;

public class clBooksSQLController {


    private ResultSet resultQuery = null;
    private boolean controlAnd;

    public ResultSet getAllBooks() throws SQLException {

        String sql = new String("select * from libros");
        resultQuery = clConexionSingleton.getInstance().executeQuery(sql);

        return resultQuery;
    }

        public void subscribe(clODTBooks book) throws SQLException {
        String sql = "insert into libros (titulo, autor, editorial, asignatura, estado) values ('" + book.getTitulo() + "','" + book.getAutor() + "','"
                + book.getEditorial()+ "','" + book.getAsignatura()+ "','" + book.getEstado() + "');";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public void unsubscribe(clODTBooks book) throws SQLException {
        String sql = "delete from libros where codigo = " + book.getCodigo()+ ";";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public void modify(clODTBooks book) throws SQLException {
        String sql = "update libros set titulo = '" + book.getTitulo() + "', autor = '" + book.getAutor()+ "', editorial = '" + book.getEditorial() + "', asignatura = '"
                + book.getAsignatura() + "', estado = '" + book.getEstado() + "' where codigo = '" + book.getCodigo()+ "';";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public ResultSet getBookSearch(clODTBooks book) throws SQLException {
        String sql = "select * from libros where ";
        if (!book.getCodigo().equals("")) {
            sql = sql + "codigo = " + book.getCodigo()+ " ";
            controlAnd = true;
        }
        if (!book.getTitulo().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "titulo = '" + book.getTitulo()+ "'";
            controlAnd = true;
        }
        if (!book.getAutor().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "autor =  '" + book.getAutor()+ "' ";
            controlAnd = true;
        }
        if (!book.getEditorial().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "editorial = '" + book.getEditorial()+ "' ";
            controlAnd = true;
        }
        if (!book.getAsignatura().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "asignatura = '" + book.getAsignatura() + "' ";
            controlAnd = true;
        }
        if (!book.getEstado().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "estado = '" + book.getEstado()+ "' ";
        }
        resultQuery = clConexionSingleton.getInstance().executeQuery(sql);
        return resultQuery;
    } 
}
