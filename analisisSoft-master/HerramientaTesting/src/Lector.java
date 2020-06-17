
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lector {

    public void eliminarComentario(String string) {
        int indiceComentario = string.indexOf("//");
        if (indiceComentario != -1) {
            string = string.substring(0, indiceComentario);
        }
    }

    public ArrayList<String> getMethodsNames(String path) throws FileNotFoundException, IOException {
        ArrayList<String> reservedNames;
        reservedNames = new ArrayList<String>(Arrays.asList("if", "while", "for", "do", "else", "switch"));

        ArrayList<String> methodsNames = new ArrayList<String>();

        String bigLine = "";
        FileReader fileReader = new FileReader(path);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\n", "");
                bigLine = bigLine.concat(line);
            }

            String patternString = "[^\\)\\(\\{\\}\\t]+\\s([a-zA-Z][a-zA-Z0-9]*)\\s*\\(\\s*([^\\(\\)]*)\\)\\s*([^\\(\\)\\{\\};]*)\\s*\\{";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(bigLine);

            while (matcher.find()) {
                if (!reservedNames.contains(matcher.group(1))) {
                    methodsNames.add(matcher.group(1));
                }
            }
        }

        return methodsNames;

    }

    public void grabarMetodoEnArchivo(String nombreMetodo, String path) {

        String codigoMetodo = buscarCodigoMetodo(nombreMetodo, path);
        try {
            PrintWriter salida = new PrintWriter(nombreMetodo + ".java");
            salida.print(codigoMetodo);
            salida.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String buscarCodigoMetodo(String nombreMetodo, String path) {
        boolean empieza = false, termina = false;
        int cantLlaves = 0;
        boolean primeraLLave = false;
        String metodo = "";
        //String patternString = "[^\\)\\(\\{\\}\\t]+\\s(" + nombreMetodo + ")\\s*\\(\\s*\\)\\s\\{";
        String patternString = "[^\\)\\(\\{\\}\\t]+\\s("+nombreMetodo+")\\s*\\(\\s*([^\\(\\)]*)\\)\\s*([^\\(\\)\\{\\};]*)\\s*\\{";

        Pattern pattern = Pattern.compile(patternString);

        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNextLine() && !termina) {
                String linea = sc.nextLine();
                eliminarComentario(linea);
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    empieza = true;
                }
                if (empieza) {
                    if (linea.contains("{")) {
                        cantLlaves++;
                        primeraLLave = true;
                    }
                    if (linea.contains("}")) {
                        cantLlaves--;
                    }
                    metodo += linea + '\n';
                    if (primeraLLave && cantLlaves == 0) {
                        termina = true;
                    }

                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return metodo;
    }
}
