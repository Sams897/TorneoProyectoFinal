package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DataAnalysis {

    public static String equipoConMasPuntos(List<Torneo> data) {
        Map<String, Integer> puntos = new HashMap<>();

        for (Torneo item : data) {
            String equipoL = item.getNameEquipoL().trim();
            String equipoV = item.getNameEquipoV().trim();
            int puntosL = item.getPuntosEqL();
            int puntosV = item.getPuntosEqV();

            puntos.put(equipoL, puntos.getOrDefault(equipoL, 0) + puntosL);
            puntos.put(equipoV, puntos.getOrDefault(equipoV, 0) + puntosV);
        }

        return puntos.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No hay datos");
    }

    public static String[] top3EquiposConMasGoles(List<Torneo> data) {
        Map<String, Integer> goles = new HashMap<>();

        for (Torneo item : data) {
            String equipoL = item.getNameEquipoL().trim();
            String equipoV = item.getNameEquipoV().trim();
            int golesL = item.getGolEquipoL(); // 
            int golesV = item.getGolEquipoV(); //  

            goles.put(equipoL, goles.getOrDefault(equipoL, 0) + golesL);
            goles.put(equipoV, goles.getOrDefault(equipoV, 0) + golesV);
        }

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(goles.entrySet());

        String[] top3 = new String[3];
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) {
            top3[i] = maxHeap.poll().getKey();
        }

        return top3;
    }

    public static String[] top3MinutoConMasGoles(List<Torneo> data) {
        Map<Integer, Integer> minutos = new HashMap<>();

        for (Torneo item : data) {
            int minuto = item.getMingol();
            minutos.put(minuto, minutos.getOrDefault(minuto, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(minutos.entrySet());

        String[] top3 = new String[3];
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) {
            top3[i] = String.valueOf(maxHeap.poll().getKey());
        }

        return top3;
    }

}
