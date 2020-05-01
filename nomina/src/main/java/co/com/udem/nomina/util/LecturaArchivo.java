package co.com.udem.nomina.util;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivo {

	private  final  HashMap<String, EmpleadoDTO> empleados = new HashMap<String, EmpleadoDTO>();
	private static final Logger logger = LogManager.getLogger(LecturaArchivo.class);
	

	public  String leerArchivo() {
		 InputStream archivoNomina = null;
		archivoNomina = ClassLoader.class.getResourceAsStream("/nominaEmpleados.txt");
		Scanner scanner = null;
		String mensaje = "Exito";
		try {
			scanner = new Scanner(archivoNomina);
			while(scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				leerRegistro(registro);
				
			}
			
			imprimirHashMap();
			
		} catch (Exception e) {
			mensaje = "El archivo no está en la ruta especificada";
		}finally {
			if(scanner != null) {
				scanner.close();
			}
					
		}
		return mensaje;
	}
	
	public  void leerRegistro(String registro) {
		
		Scanner scanner = new Scanner(registro);
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			llenarDTO(scanner);			
		}
		scanner.close();
	}
	
	public  void llenarDTO(Scanner scanner) {
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNombres(scanner.next());
		empleadoDTO.setApellidos(scanner.next());
		String cedula = scanner.next();			
		empleadoDTO.setCedula(cedula);
		empleadoDTO.setDepartamento(scanner.next());
		empleadoDTO.setSalario(Double.parseDouble(scanner.next()));
		
		llenarHashMap(empleadoDTO);

	}
	
	public  void llenarHashMap(EmpleadoDTO empleadoDTO) {
		if (empleados.containsKey(empleadoDTO.getCedula())) {
			logger.info("El registro  ya existe, cedula: " + empleadoDTO.getCedula());
		}else {
			empleados.put(empleadoDTO.getCedula(), empleadoDTO);
		}
		 
		}

	public   void imprimirHashMap() {
		
		Collection<EmpleadoDTO> coleccionEmpleados = empleados.values();
		Iterator<EmpleadoDTO> iterator = coleccionEmpleados.iterator();
		StringBuilder empleado1 = new StringBuilder();

		while (iterator.hasNext()) {
			EmpleadoDTO empleadoDTO = iterator.next();
			empleado1.append(empleadoDTO.getNombres());
			empleado1.append(",");
			empleado1.append(empleadoDTO.getApellidos());
			empleado1.append(",");
			empleado1.append(empleadoDTO.getCedula());
			empleado1.append(",");
			empleado1.append(empleadoDTO.getDepartamento());
			empleado1.append(",");
			empleado1.append(empleadoDTO.getSalario());
			logger.info(empleado1.toString());
			empleado1.delete(0, empleado1.length());
		}

	}
	


	public  int tamanoHashMap() {
		return empleados.size();
	}
	     
	

	
}
