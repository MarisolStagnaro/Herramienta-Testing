package baseDatos;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class paciente {
	String codigoPaciente,nombrePaciente,enfermedad,observaciones;
	
	
	public void ingresoDeDatos () {//Ingreso de datos del paciente
		String op = "";
		DataOutputStream datopac = null;
		try {
			datopac = new DataOutputStream(new FileOutputStream("C:\\datopac.txt"));//abrimos el archivo para agregar pacientes
			try {
				do {
					menu.ps("   ..............................................." + "\n");
					menu.ps("   :-:  - D A T O S  D E L  P A C I E N T E -  :-:" + "\n");
					menu.ps("   :-:.........................................:-:" + "\n");

					menu.ps("Digite el codigo del paciente: ");
					codigoPaciente = menu.LeerCadena(); //se ingresa el codigo del paciente 
					datopac.writeUTF(codigoPaciente);
					menu.ps("Digite el nombre del paciente: ");
					nombrePaciente = menu.LeerCadena(); // se ingresa Nombre y apellido del paciente

					datopac.writeUTF(nombrePaciente);// se escribe en el archivo datopac

					menu.ps("Desea ingresar otro paciente? S/N" + "\n");//pregunta en pantalla

					op = menu.LeerCadena();//leemos esa opcion que ingreso por pantalla

				} while (op.equals("S") || op.equals("s"));// si es no , salimos del while
				datopac.close();//cerramos el archivo de texto de pacientes

			} catch (IOException ioe) {
			}
			;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void ingresoDeSituacionDelPaciente () {// ingreso de situacion del paciente
		
		DataOutputStream situpac = null;
		try {
			situpac = new DataOutputStream(new FileOutputStream("C:\\situpac.txt"));//abrimos archivo de texto de situacion de paciente
			String op = "";
			do {
				String codMedico="";
				menu.ps("   ....................................................." + "\n");
				menu.ps("   :-: - S I T U A C I O N  D E L  P A C I E N T E - :-:" + "\n");
				menu.ps("   :-:...............................................:-:" + "\n");

				menu.ps("Digite el codigo del paciente: ");
				codigoPaciente = menu.LeerCadena();//ingreso codigo del paciente ya registrado
				situpac.writeUTF(codigoPaciente);//lo escribo en el archivo
				menu.ps("Digite el codigo del medico: ");
				codMedico = menu.LeerCadena();//ingreso el codigo del medico que atiende al paciente
				situpac.writeUTF(codMedico);
				menu.ps("Digite el diagnostico del medico: ");
				enfermedad = menu.LeerCadena();//escribo diagnostico del paciente
				situpac.writeUTF (enfermedad);

				menu.ps("Desea ingresar otro registro al historial? S/N");//pregunta por pantalla
				menu.ps("\n");
				op = menu.LeerCadena();//leo la respuesta a la pregunta

			} while (op.equals("S") || op.equals("s"));//si es N, salgo del while
			situpac.close();
		} catch (IOException ioe) {
		}
		;
	}
	
}
