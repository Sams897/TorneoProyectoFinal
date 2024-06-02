package model;

import java.io.IOException;
import java.io.Writer;

public class BinaryTree {

    private Node root;

    private class Node {
        private int key;
        private Torneo value;
        private Node left;
        private Node right;

        public Node(int key, Torneo value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(Torneo value) {
        int key = generateKey(value);
        System.out.println("Insertando partido con clave única: " + key); // Imprimir la clave generada
        root = insertRecursive(root, key, value);
    }

    private Node insertRecursive(Node root, int key, Torneo value) {
        if (root == null) {
            return new Node(key, value);
        }

        if (key < root.key) {
            root.left = insertRecursive(root.left, key, value);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right, key, value);
        }

        return root;
    }

    public Torneo search(int key) {
        return searchRecursive(root, key);
    }

    private Torneo searchRecursive(Node root, int key) {
        if (root == null || root.key == key) {
            return (root != null) ? root.value : null;
        }

        if (key < root.key) {
            return searchRecursive(root.left, key);
        }

        return searchRecursive(root.right, key);
    }

    public void inOrder(Writer writer) throws IOException {
        inOrderRecursive(root, writer);
    }

    private void inOrderRecursive(Node root, Writer writer) throws IOException {
        if (root != null) {
            inOrderRecursive(root.left, writer);
            writer.write(root.value.toString() + "\n");
            inOrderRecursive(root.right, writer);
        }
    }

    public void preOrder(Writer writer) throws IOException {
        preOrderRecursive(root, writer);
    }

    private void preOrderRecursive(Node root, Writer writer) throws IOException {
        if (root != null) {
            writer.write(root.value.toString() + "\n");
            preOrderRecursive(root.left, writer);
            preOrderRecursive(root.right, writer);
        }
    }

    public void postOrder(Writer writer) throws IOException {
        postOrderRecursive(root, writer);
    }

    private void postOrderRecursive(Node root, Writer writer) throws IOException {
        if (root != null) {
            postOrderRecursive(root.left, writer);
            postOrderRecursive(root.right, writer);
            writer.write(root.value.toString() + "\n");
        }
    }

    private int generateKey(Torneo torneo) {
        int sumaValoresASCII = 0;

        // Convertir las cadenas a valores ASCII
        sumaValoresASCII += getAsciiValue(torneo.getNameEquipoL());
        sumaValoresASCII += getAsciiValue(torneo.getNameEquipoV());
        sumaValoresASCII += getAsciiValue(torneo.getMejorJugador());
        sumaValoresASCII += getAsciiValue(torneo.getEstadio());

        // Sumar los valores numéricos directamente
        sumaValoresASCII += torneo.getPuntosEqL();
        sumaValoresASCII += torneo.getPuntosEqV();
        sumaValoresASCII += torneo.getGolEquipoL();
        sumaValoresASCII += torneo.getGolEquipoV();
        sumaValoresASCII += torneo.getMingol();

        return sumaValoresASCII;
    }

    private int getAsciiValue(String value) {
        int suma = 0;
        for (int i = 0; i < value.length(); i++) {
            suma += (int) value.charAt(i);
        }
        return suma;
    }

}
