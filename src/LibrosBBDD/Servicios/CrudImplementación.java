package LibrosBBDD.Servicios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import LibrosBBDD.Dtos.Libros;
import LibrosBBDD.Util.ADto;

/* SENTENCIAS PARAMETRIZADAS
 * QUE LLEGUE A LA QUERY MEDIANTE UN PARAMETRO Y NO COMO UN STRING
 * String query="SELECT empleado FROM Empleados WHERE total_productos>? AND mes=?";
 * PreparedStatement sentencia =conexion.prepareStatement(query);
 * sentencia.setInt(1,20); el primer valor es la primera variable y el segundo es el valor que se le asigna.
 * sentencia.setInt(2,1);
 * ResultSet resultado=sentencia.executeQuery();
 * */
public class CrudImplementación implements CrudInterfaz {

	@Override
	public ArrayList<Libros> SelectLibros(Connection conexion){

		ArrayList<Libros> listaLibrosSelect = new ArrayList<>();
		Statement declaracion = null;
		ResultSet resultado = null;
		Scanner scan=new Scanner(System.in);
		ADto aDto=new ADto();
		try {
			declaracion = conexion.createStatement();
			resultado = declaracion.executeQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros\r\n" + "ORDER BY id_libro ASC ");
			listaLibrosSelect=aDto.resultadosLibros(resultado);
			declaracion.close();
			resultado.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("[ERROR-CrudImplementación-SelectLibros()]-No se ha podidio acceder a la base de datos.");
			return listaLibrosSelect;
		}
		return listaLibrosSelect;
	}
	public void UpdateLibros(Connection conexion) {
		ArrayList<Libros> listaLibrosUpdate = new ArrayList<>();
		Statement declaracion = null;
		ResultSet resultado = null;
		ADto aDto=new ADto();
		Scanner scan =new Scanner(System.in);
		long id_libro;
		int edicion;
		String titulo,autor,isbn;
		try {
			declaracion = conexion.createStatement();
			resultado = declaracion.executeQuery("SELECT * FROM gbp_almacen.gbp_alm_cat_libros\r\n" + "ORDER BY id_libro ASC ");
			listaLibrosUpdate=aDto.resultadosLibros(resultado);
			for (int i = 0; i < listaLibrosUpdate.size(); i++) {
				System.out.println(listaLibrosUpdate.get(i).getId_libro()+"."+listaLibrosUpdate.get(i).getTitulo());
			}
			System.out.print("Introduzca el id del libro del que quiera editar sus datos: ");
			id_libro=scan.nextLong();
			System.out.print("Actualiza el titulo-->");
			
			declaracion.close();
			resultado.close();
			conexion.close();
		} catch (SQLException sqle) {
			System.out.println("[ERROR-CrudImplementación-SelectLibros()]-No se ha podidio acceder a la base de datos.");
		}
		
	}
}
