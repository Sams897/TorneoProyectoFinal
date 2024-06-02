package com.api.test.data;

import com.api.test.model.Torneo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class TorneoData {


    private static final String URL = "jdbc:mysql://localhost:3306/torneopartido";
    private static final String USER = "root";
    private static final String PASSWORD = "1234highonlife";

    public List<Torneo> GetData() {
        List<Torneo> data = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
             System.out.println("Connected to the database.");
            String query = "SELECT id, NameEquipoL, NameEquipoV, GolEquipoL, GolEquipoV, MejorJugador, Mingol, Estadio, PuntosEqL, PuntosEqV FROM torneo";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String nameequipoL = resultSet.getString("NameEquipoL");
                        String nameequipoV = resultSet.getString("NameEquipoV");
                        String golequipoL = resultSet.getString("GolEquipoL");
                        String golequipoV = resultSet.getString("GolEquipoV");
                        String mejorjugador = resultSet.getString("MejorJugador");
                        String mingol = resultSet.getString("Mingol");
                        String estadio = resultSet.getString("Estadio");
                        int PuntoseqL = resultSet.getInt("PuntosEqL");
                        int PuntoseqV = resultSet.getInt("PuntosEqV");

                    data.add(new Torneo( nameequipoL, nameequipoV, golequipoL, golequipoV, mejorjugador, mingol, estadio, PuntoseqL, PuntoseqV));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return data;
    }
}