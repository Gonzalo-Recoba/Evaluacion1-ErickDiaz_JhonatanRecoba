package dao.implementation;

import dao.BD;
import dao.IDao;
import model.Odontologo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.BD.logger;
//


public class OdontologoDaoH2 implements IDao<Odontologo> {



    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;
        try{
            connection = BD.getConnection();

            //Definimos los valores a insertar en la BD
            PreparedStatement psInsert = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            //Sustituimos variables dinamicas
            psInsert.setString(1, odontologo.getNombre());
            psInsert.setString(2, odontologo.getApellido());
            psInsert.setInt(3, odontologo.getNumeroMatricula());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();

            while (rs.next()){
                odontologo.setId(rs.getLong(1));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.info("Se guardaron los datos exitosamente");
        return odontologo;
    };

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        try{
            connection = BD.getConnection();

            //Definimos los valores a consultar de la BD
            PreparedStatement psDelete = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID = ?");

            //Sustituimos variables dinamicas
            psDelete.setLong(1, id);

            //Guardamos la respuesta de la BD en ps
            psDelete.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.info("Se eliminaron los datos exitosamente");
    }

    @Override
    public Odontologo buscar(Long id) {
        Connection connection = null;
        Odontologo odontologo = null;
        try{
            connection = BD.getConnection();

            //Definimos los valores a consultar de la BD
            PreparedStatement psQuery = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");

            //Sustituimos variables dinamicas
            psQuery.setLong(1, id);

            //Guardamos la respuesta de la BD en ps
            ResultSet rs = psQuery.executeQuery();



            //Recorremos ps imprimiendo por pantalla cada uno de sus atributos
            while(rs.next()){
                odontologo = new Odontologo();
                odontologo.setId(rs.getLong("ID"));
                odontologo.setNombre(rs.getString("NOMBRE"));
                odontologo.setApellido(rs.getString("APELLIDO"));
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            try{
                connection.close();
            } catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        logger.info("Odontolo encontrado: " + odontologo.getNombre() + " " + odontologo.getApellido() + " " + odontologo.getNumeroMatricula());
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        try{
            connection = BD.getConnection();

            //Definimos los valores a consultar de la BD
            PreparedStatement psQuery = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");


            //Guardamos la respuesta de la BD en ps
            ResultSet rs = psQuery.executeQuery();



            //Recorremos ps imprimiendo por pantalla cada uno de sus atributos
            while(rs.next()){
                Odontologo odontologo = new Odontologo();
                odontologo.setId(rs.getLong("ID"));
                odontologo.setNombre(rs.getString("NOMBRE"));
                odontologo.setApellido(rs.getString("APELLIDO"));
                odontologo.setNumeroMatricula(rs.getInt("MATRICULA"));

                listaOdontologos.add(odontologo);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        logger.error("Se encontraron " + listaOdontologos.size() + " odontologos registrados.");
        return listaOdontologos;
    }
}