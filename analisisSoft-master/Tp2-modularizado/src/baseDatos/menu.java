package baseDatos;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class menu {
	
	public static void ps(String x) {
		System.out.print(x);//imprime en pantalla
	}

	public static int LeerEntero() {//lee la opcion elegida en el menu
		String línea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//para leer la entrada por teclado
			línea = br.readLine();////leo linea ingresada
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ne = 0;
		try {
			ne = Integer.parseInt(línea);//paso el sting a entero
		} catch (Exception e) {
		}
		;
		return (ne);//retorno el entero
	}

	public static String LeerCadena() {//lee la informacion ingresada en el ingreso de datos o consultas de informes
		String línea = new String("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//para leer la entrada por teclado
			línea = br.readLine();//leo linea ingresada
		} catch (Exception e) {
			e.printStackTrace();
		}
		double ne = 0;
		return (línea);//retorno la cadena de texto
	}
	
	
	public static int MostrarMenuInicial() {//muestra menu principal del centro asistencial los pinares
		int op1=0;//entero para elegir la opcion 
		do{
			//Lineas impresas por pantalla 
			menu.ps("   .............................................." + "\n");
			menu.ps("   :-:         CENTRO ASISTENCIAL             :-:" + "\n");
			menu.ps("   :-:   >>>> L O S  P I N A R E S   <<<<     :-:" + "\n");
			menu.ps("   :-:  C O N T R O L  D E  P A C I E N T E S :-:" + "\n");
			menu.ps("   :-:........................................:-:" + "\n");
			menu.ps("   :-: 1.  Ingreso de datos                   :-:" + "\n");
			menu.ps("   :-: 2.  Informes                           :-:" + "\n");
			menu.ps("   :-: 3.  Salir                              :-:" + "\n");
			menu.ps("   .............................................." + "\n");
			menu.ps("   ....Elija la opcion deseada: ");
			// ps("\n");
			op1 = menu.LeerEntero();//leo la opcion elegida
			if (op1 < 1 || op1 > 3) {//si no es correcta la opcion muestro mensaje para que vuelva ingresar una opcion
				ps("Debe digitar una opcion del menu" + "\n");//mensaje
				}
		} while (op1 > 3 && op1 < 1);//si es una opcion valida, salgo sino vuelve a elegirla
		return op1;//retorno la opcion valida elegida
	}
	
	
	public static int MostrarMenuIngresoDatos() {//muestra el menu para el ingreso de datos
		int op2=0;//entero para elegir la opcion
		do {
			//Lineas impresas por pantalla 
			ps("   ..............................................." + "\n");
			ps("   :-: -I N G R E S O  D E  P A C I E N T E S- :-:" + "\n");
			ps("   :-:.........................................:-:" + "\n");
			ps("   :-: 1.  Datos del paciente                  :-:" + "\n");
			ps("   :-: 2.  Situacion del paciente              :-:" + "\n");
			ps("   :-: 3.  Datos del medico                    :-:" + "\n");
			ps("   :-: 4.  vOLVER                              :-:" + "\n");
			ps("   ..............................................." + "\n");
			ps("   ....Elija la opcion deseada: ");

			op2 = LeerEntero();//leo la opcion puesta

			if (op2 < 1 || op2 > 4) {//si no es correcta la opcion muestro mensaje para que vuelva ingresar una opcion
				ps("Debe digitar una opcion del menu" + "\n");//mensaje
			}
		} while (op2 < 1 || op2 > 4);//si es una opcion valida, salgo sino vuelve a elegirla
		return op2;//retorno la opcion valida elegida
	}
	
	
	public static int MostrarMenuInformes() {// muestra menu para la consulta de informes
		int op2=0;//entero para elegir la opcion
		do {
			//Lineas impresas por pantalla 
			ps("   ..............................................." + "\n");
			ps("   :-:  C O N T R O L  D E  P A C I E N T E S  :-:" + "\n");
			ps("   ..............................................." + "\n");
			ps("   :-:           - I N F O R M E S -           :-:" + "\n");
			ps("   :-:.........................................:-:" + "\n");
			ps("   :-: 1. Listado de pacientes por medico      :-:" + "\n");
			ps("   :-: 2. Enfermedades que atiende cada medico :-:" + "\n");
			ps("   :-: 3. Anterior                             :-:" + "\n");
			ps("   ..............................................." + "\n");
			ps("   ....Elija la opcion deseada: ");
			op2 = LeerEntero();//leo la opcion elegida
			if (op2 < 1 || op2 > 3) {//si no es correcta la opcion muestro mensaje para que vuelva ingresar una opcion
				ps("Seleccione una de las opciones del menu" + "\n");//mensaje
			}
		} while (op2 < 1 || op2 > 3);//si es valida la opcion salgo sino sigo
		return op2;//retorno la opcion valida elegida
	}
	
	
}

