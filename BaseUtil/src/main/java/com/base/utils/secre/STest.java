package com.base.utils.secre;

public class STest {

    public static void main(String[] args) {
        String name = "uioruewior";

        String key = "8987";
        byte[] bytes = TYEncryption.encryptBytes(name.getBytes());
        System.out.println(new String(bytes));
        byte[] bytes1 = IPEncryption.encryptBytes(bytes);
        System.out.println(new String(bytes1));
    }
}
