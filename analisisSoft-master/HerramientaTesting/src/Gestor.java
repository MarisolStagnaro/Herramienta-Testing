
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gestor {

    public static ArrayList<String> getMethodsNames(String path) throws IOException {
        Lector lector = new Lector();
        ArrayList<String> methodsNames = lector.getMethodsNames(path);
        return methodsNames;
    }

    public static Map<String, Object> getResults(String path, String methodName) throws IOException {
        Map<String, Object> resultadoEvaluacion = new HashMap();
        Herramienta herramienta = new Herramienta();
        Lector l = new Lector();
        l.grabarMetodoEnArchivo(methodName, path);
        
        
        int[] operacionDeLineas = herramienta.getTotalLines(methodName + ".java");
        int cc = herramienta.calcularCC(methodName + ".java");
        DecimalFormat formatoPorcentaje = new DecimalFormat("#.##");
        
        String porcComentarios = formatoPorcentaje.format(((float) operacionDeLineas[1] * 100) / (operacionDeLineas[0] - operacionDeLineas[3])) ;
        
        Map<String, Object> halstead = herramienta.getHalstead(methodName + ".java",getMethodsNames(path));
        
        resultadoEvaluacion.put("cc", cc);
        resultadoEvaluacion.put("totalLines", operacionDeLineas[0]);
        resultadoEvaluacion.put("comentarios", operacionDeLineas[1]);
        resultadoEvaluacion.put("codigo", operacionDeLineas[2]);
        resultadoEvaluacion.put("blancos", operacionDeLineas[3]);
        resultadoEvaluacion.put("porcComentarios", porcComentarios);
        resultadoEvaluacion.put("longitud",halstead.get("longitud"));
        resultadoEvaluacion.put("velocidad",formatoPorcentaje.format(halstead.get("volumen")));
        resultadoEvaluacion.put("esfuerzo",formatoPorcentaje.format(halstead.get("esfuerzo")));
        resultadoEvaluacion.put("fanin",herramienta.getFanIn(path, methodName));
        resultadoEvaluacion.put("fanout",herramienta.getFanOut(methodName + ".java", getMethodsNames(path)));
        

        File file = new File(methodName + ".java");
        file.delete();
        return resultadoEvaluacion;
    }
}
