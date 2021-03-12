package me.mica.learn;

public class TestFormat {

    public static void main(String[] args) {
        String mat = "hello % s";
        System.out.println(String.format(mat, "kugou"));
    }
}
