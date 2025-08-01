
package clasesGson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EjemploGson {
    public static void main(String[] args) {
        Persona persona = new Persona("Georgina", 22);
        Gson gson = new Gson();
        String json = gson.toJson(persona);
        System.out.println(json);
    }
}
class Persona {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}


