package co.com.udem.nomina.util;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivoTest1 {
	
	private EmpleadoDTO empleadoDTO;
	
	
	@Before
    public void setup() {

		empleadoDTO = new EmpleadoDTO("Alex","Gomez", "6405636","Tecnologia",600000.00);
		
 }

	@Test
	public void test_llenarHashMap() {
	
		HashMap<String, EmpleadoDTO> empleadosMap = new HashMap<String, EmpleadoDTO>();
		empleadosMap.put("6405636", empleadoDTO);
        assertThat(empleadosMap.get("6405636"), is(empleadoDTO));
		
	}

	@Test
	public void test_llenarDTO() {
		
		HashMap<String, EmpleadoDTO> empleadosMap = new HashMap<String, EmpleadoDTO>();
		String registro="Alex,Gomez,6405636,Tecnologia,600000";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(registro);
		scanner.useDelimiter(",");
		EmpleadoDTO empleado = new EmpleadoDTO();
		
		empleado.setNombres(scanner.next());
		empleado.setApellidos(scanner.next());
		String cedula = scanner.next();			
		empleado.setCedula(cedula);
		empleado.setDepartamento(scanner.next());
		empleado.setSalario(Double.parseDouble(scanner.next()));
		
		empleadosMap.put(empleado.getCedula(), empleado);
		
		assertThat(empleadosMap.get(cedula), is(empleado));
		 	
	}
	
	@Test
	public void test_leerRegistro() {
		
	    final Logger logger = LogManager.getLogger(LecturaArchivoTest1.class);
		LecturaArchivo lecturaArchivo1 = new LecturaArchivo();
		String mensaje=lecturaArchivo1.leerArchivo();
		int cantidadRegistros = lecturaArchivo1.tamanoHashMap();
		
		logger.info(mensaje);		
		assertEquals("Exito",mensaje);
		assertEquals(3,cantidadRegistros);
	}
	
	
	@Test
	public void test_NumeroRegistroArchivo() {
		
	   LecturaArchivo lecturaArchivo1 = new LecturaArchivo();
		String mensaje=lecturaArchivo1.leerArchivo();
		int cantidadRegistros = lecturaArchivo1.tamanoHashMap();
		assertEquals("Exito",mensaje);
		assertEquals(3,cantidadRegistros);
	}
	
}
