/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgStudent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pkgConexiones.clConexionSingleton;

/**
 *
 * @author DavSosMie
 */
public class clStudentSQLController {

    private ResultSet resultQuery = null;

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


    public ResultSet search(String registro) throws SQLException {
        String sql = "select * from alumnos where registro = " + registro + ";";
        resultQuery = clConexionSingleton.getInstance().executeQuery(sql);
        return resultQuery;
    }
}
