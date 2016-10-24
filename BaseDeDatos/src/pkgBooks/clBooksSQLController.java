package pkgBooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pkgConexiones.clConexionSingleton;

/**
 *
 * @author DavSosMie
 */
public class clBooksSQLController {


    private ResultSet resultQuery = null;

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
        System.out.println(sql);
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public void modify(clODTBooks book) throws SQLException {
        String sql = "update libros set titulo = '" + book.getTitulo() + "', autor = '" + book.getAutor()+ "', editorial = '" + book.getEditorial() + "', asignatura = '"
                + book.getAsignatura() + "', estado = '" + book.getEstado() + "' where codigo = '" + book.getCodigo()+ "';";
        System.out.println(sql);
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    
    
}
