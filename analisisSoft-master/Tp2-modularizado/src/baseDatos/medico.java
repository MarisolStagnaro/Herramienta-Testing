package baseDatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class medico {
	String codigoMedico, nombreMedico, especialidad;
	List<paciente> pacientes = new ArrayList<paciente>();
	String codp = "", codpa = "", nomp = "", nompa = "", codm = "", codme = "", enfp = "", nomm = "", espm = "";// variables
	// usadas
	// en
	// la
	// lectura
	// de
	// datos

	public void AgregarInfoMedico() { // Ingresar datos del medico
		DataOutputStream datomed = null;
		String op = "";
		try {
			datomed = new DataOutputStream(new FileOutputStream("C:\\datomed.txt"));
			do {

				menu.ps("   ....................................................." + "\n");
				menu.ps("   :-:      - D A T O S   D E L   M E D I C O -      :-:" + "\n");
				menu.ps("   :-:...............................................:-:" + "\n");

				menu.ps("Digite el codigo del medico: ");
				codigoMedico = menu.LeerCadena();// leo codigo de medico ingresada
				datomed.writeUTF(codigoMedico);// escribo en el archivo txt el codigo

				menu.ps("Digite el nombre del medico: ");
				nombreMedico = menu.LeerCadena();// leo nombre de medico ingresada
				datomed.writeUTF(nombreMedico);// escribo en el archivo txt el nombre

				menu.ps("Digite la especializacion del medico: ");
				especialidad = menu.LeerCadena();// leo especialidad ingresada
				datomed.writeUTF(especialidad);// escribo en el archivo txt la especialidad ingresada

				menu.ps("Desea ingresar otro medico? S/N");// pregunta por pantalla
				menu.ps("\n");

				op = menu.LeerCadena();// leo la respuesta a la pregunta ingresada

			} while (op.equals("S") || op.equals("s"));// si no es S, salgo del while
			datomed.close();
		} catch (IOException ioe) {
		}
		;

	}

	public void informesMedicos(int opcion) throws IOException { // se realiza las consultas de informes de medicos
		String codtem = "";// variables auxiliares temporales
		int sw = 0, sw1 = 0; // variables auxiliares
		switch (opcion) {
		case 1: // listado de pacientes por medico
			consultaPacienteDelMedico(sw, sw1, codtem);// consulta de listado de pacientes del medico
			break;

		case 2: // enfermedades que atiende cada medico
			consultaEspecialidadMedico(sw, sw1, codtem);// consulta de especialidad de medico
			break;
		}

	}

	public void consultaPacienteDelMedico(int sw, int sw1, String codtem) {
		try {
			menu.ps("Digite el codigo del medico que desea consultar: ");// imprime por pantalla
			codtem = menu.LeerCadena();// leo el codigo de medico ingresado
			DataInputStream datomed = null;
			datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));// abro el archivo con los datos de
																					// los medicos
			sw = 1;
			while (sw != 0) {
				try {
					codm = datomed.readUTF();// leo del archivo codigo de medico
					nomm = datomed.readUTF();// leo del archivo el nombre del medico
					espm = datomed.readUTF();// leo del archivo la especialidad del medico

				} catch (EOFException e) {
					sw = 0;
				}

				if (codm.equals(codtem)) // compara el codigo extraido de la
											// tabla "datomed" con el codigo
											// digitado
				{
					menu.ps("::: El medico " + nomm + " atiende a los siguientes pacientes: " + "\n");// imprimo por
																										// pantalla el
																										// nombre del
																										// medico
					DataInputStream situpac = null;
					situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));// abro el archivo de
																							// situacion de pacientes

					sw = 1;
					while (sw != 0) {
						try {
							codp = situpac.readUTF();// leo del archivo codigo de paciente
							codme = situpac.readUTF();// leo del archivo del medico que atiende al paciente
							enfp = situpac.readUTF();// leo del archivo la situacion del paciente

							if (codme.equals(codtem)) // compara el codigo medico de la
														// tabla "datomed" con el de la
														// tabla "situpac"
							{
								DataInputStream datopac = null;
								datopac = new DataInputStream(new FileInputStream("C:\\datopac.txt"));// abro el archivo
																										// de datos del
																										// paciente

								sw1 = 1;
								while (sw1 != 0) {
									try {
										codpa = datopac.readUTF();// leo del archivo codigo de paciente
										nompa = datopac.readUTF();// leo del archivo el nombre del paciente

										if (codpa.equals(codp)) // compara el codigo del
																// paciente de la tabla "situpac"
																// con el codigo del paciente de
																// la tabla "datopac"
										{
											menu.ps("::: Paciente: " + nompa + "\n");// imprimo por pantalla el nombre
																						// del paciente atendido por el
																						// medico
										}
									} catch (EOFException e) {
										sw1 = 0;
									}
								}
							}
						} catch (EOFException e) {
							sw = 0;
						}
					}
				}
			}

		} catch (IOException ioe) {
		}

	}

	public void consultaEspecialidadMedico(int sw, int sw1, String codtem) {
		try {
			menu.ps("Digite el codigo del medico que desea consultar: ");//imprimo mensaje
			codtem = menu.LeerCadena();//leo el codigo de medico ingresado 

			DataInputStream datomed = null;
			datomed = new DataInputStream(new FileInputStream("C:\\datomed.txt"));//abro archivo de datos del medico

			sw1 = 1;
			while (sw1 != 0) {
				try {
					codm = datomed.readUTF();//leo del archivo el codigo del medico
					nomm = datomed.readUTF();//leo del archivo el nombre del medico
					espm = datomed.readUTF();//leo del archivo la especialidad del medico

					if (codm.equals(codtem)) // compara el codigo digitado
												// con el codigo del medico de la
												// tabla "datomed"
					{
						menu.ps("El medico " + nomm + " trata las siguientes enfermedades:" + "\n");//nombre del medico

						DataInputStream situpac = null;
						situpac = new DataInputStream(new FileInputStream("C:\\situpac.txt"));//abro el archivo de situacion de paciente

						sw = 1;
						while (sw != 0) {
							try {
								codp = situpac.readUTF();//leo del archivo el codigo del paciente
								codme = situpac.readUTF();//leo del archivo el codigo del medico que atiende al paciente
								enfp = situpac.readUTF();//leo del archivo la enfermedad que presenta el paciente

								if (codtem.equals(codme)) // compara el codigo del medico
															// de la tabla "datomed"
															// con el codigo del medico en la
															// tabla "situpac"

								{
									menu.ps(">>>> " + enfp + "\n");//especialidad que trata el medico 
								}
							} catch (EOFException e) {
								sw = 0;
							}
						}
					}
				} catch (EOFException e) {
					sw1 = 0;
				}
			}
		} catch (IOException ioe) {
		}

	}

}
