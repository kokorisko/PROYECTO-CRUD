package com.miweb.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miweb.model.Usuario;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    private static final String FILE_PATH = "webapp/WEB-INF/data/usuarios.json";

    public static List<Usuario> leerUsuarios() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("El archivo usuarios.json no existe.");
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void escribirUsuarios(List<Usuario> usuarios) {
        
        File file = new File("webapp/WEB-INF/data/usuarios.json");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("Directorio creado: " + file.getParent());
        }

        try (Writer writer = new FileWriter(file)) {
            new Gson().toJson(usuarios, writer);
            System.out.println("Usuarios escritos correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al escribir el archivo.");
        }
    }
}
