package service;
import dao.IDao;
import dao.implementation.OdontologoDaoH2;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public IDao<Odontologo> getOdontologoIDao(){
        return odontologoIDao;
    }

    //Constructor
    public OdontologoService(){
        this.odontologoIDao = (IDao<Odontologo>) new OdontologoDaoH2();
    }

    //Setter
    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao){
        this.odontologoIDao = odontologoIDao;
    }


    //Metodos
    public Odontologo guardar(Odontologo odontologo){
        //Se delega la responsabilidad de guardar al DAO
        return odontologoIDao.guardar(odontologo);
    }
    public void eliminar(Long id){
        //Se delega la responsabilidad de eliminar al DAO
        odontologoIDao.eliminar(id);
    }

    public Odontologo buscar(Long id){
        //Se delega la responsabilidad de buscar al DAO
        return odontologoIDao.buscar(id);
    }

    public List<Odontologo> buscarTodos(){
        //Se delega la responsabilidad de buscar al DAO
        return odontologoIDao.buscarTodos();
    }


}
