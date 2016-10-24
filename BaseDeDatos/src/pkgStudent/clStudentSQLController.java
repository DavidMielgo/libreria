package pkgStudent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pkgConexiones.clConexionSingleton;

public class clStudentSQLController {

    private ResultSet resultQuery = null;
    private boolean controlAnd = false;

    public ResultSet getAllStudent() throws SQLException {

        String sql = new String("select * from alumnos");
        resultQuery = clConexionSingleton.getInstance().executeQuery(sql);

        return resultQuery;
    }

    public void subscribe(clODTStudent student) throws SQLException {
        String sql = "insert into alumnos (dni,nombre,apellido1,apellido2) values ('" + student.getDni() + "','" + student.getName() + "','"
                + student.getSurname1() + "','" + student.getSurname2() + "');";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public void unsubscribe(clODTStudent student) throws SQLException {
        String sql = "delete from alumnos where registro = " + student.getRegist() + ";";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public void modify(clODTStudent student) throws SQLException {
        String sql = "update alumnos set dni = '" + student.getDni() + "', nombre = '" + student.getName() + "', apellido1 = '" + student.getSurname1() + "', apellido2 = '"
                + student.getSurname2() + "'where registro = '" + student.getRegist() + "';";
        clConexionSingleton.getInstance().executeSqlUpdate(sql);

    }

    public ResultSet getStudentSearch(clODTStudent student) throws SQLException {
        String sql = "select * from alumnos where ";
        if (!student.getRegist().equals("")) {
            sql = sql + "registro = " + student.getRegist() + " ";
            controlAnd = true;
        }
        if (!student.getDni().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "dni = '" + student.getDni() + "'";
            controlAnd = true;
        }
        if (!student.getName().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "nombre =  '" + student.getName() + "' ";
            controlAnd = true;
        }
        if (!student.getSurname1().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "apellido1 = '" + student.getSurname1() + "' ";
            controlAnd = true;
        }
        if (!student.getSurname2().equals("")) {
            if(controlAnd == true){
                sql = sql + "and ";
                controlAnd = false;
            }
            sql = sql + "apellido2 = '" + student.getSurname2() + "' ";
        }
        resultQuery = clConexionSingleton.getInstance().executeQuery(sql);
        return resultQuery;
    }

}
