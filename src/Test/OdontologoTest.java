package Test;

import dao.implementation.OdontologoDaoH2;
import model.Odontologo;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.assertEquals


public class OdontologoTest {
    @Test
    public void listarOdontologos() {

        //Class1 class1 = new Class1();
        Odontologo odontologo1 = new Odontologo(123, "Juan", "Perez");
        Odontologo odontologo2 = new Odontologo(456, "Pedro", "Rodriguez");


        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoIDao(new OdontologoDaoH2());

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);

        String respuestaEsperada = "Odontologo{id= 2, numeroMatricula= 456, nombre Pedro, apellido= Rodriguez}";

        //CUANDO
        String proceso = odontologo2.toString();

        //ENTONCES
        assertEquals(respuestaEsperada, proceso);

    }
}