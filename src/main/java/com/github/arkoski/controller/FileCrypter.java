package com.github.arkoski.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class FileCrypter {
    private SecretKeySpec secretKey;
    private Cipher cipher;

    public FileCrypter(String secret, int length, String algorithm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] key = new byte[length];
        key = fixSecret(secret, length);
        this.secretKey = new SecretKeySpec(key, algorithm);
        this.cipher = Cipher.getInstance(algorithm);
    }

    private byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
        if (s.length() < length) {
            int missingLength = length - s.length();
            for (int i = 0; i < missingLength; i++) {
                s += " ";
            }
        }
        return s.substring(0, length).getBytes("UTF-8");
    }

    public void encryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        //System.out.println("Encrypting file: " + f.getName());
        this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    public void decryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        //System.out.println("Decrypting file: " + f.getName());
        this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    public void writeToFile(File f) throws IOException, IllegalBlockSizeException, BadPaddingException {
        FileInputStream in = new FileInputStream(f);
        byte[] input = new byte[(int) f.length()];
        in.read(input);

        FileOutputStream out = new FileOutputStream(f);
        byte[] output = this.cipher.doFinal(input);
        out.write(output);

        out.flush();
        out.close();
        in.close();
    }

    public static void encrypt() {
        File dir = new File("baza.txt");

        FileCrypter ske;
        try {
            ske = new FileCrypter("poiuytrewq", 16, "AES");

            //int choice = -2;
            //while (choice != -1) {
            //String[] options = {"Encrypt All", "Decrypt All", "Exit"};
            //choice = JOptionPane.showOptionDialog(null, "Select an option", "Options", 0,
            //JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            //switch (choice) {
            //case 0:
            try {
                ske.encryptFile(dir);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                System.err.println("Couldn't encrypt " + dir.getName() + ": " + e.getMessage());
            }
            //System.out.println("Files encrypted successfully");
            //break;
            //case 1:

            //break;
            //default:
            //choice = -1;
            //break;
            //}
            //}
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void decrypt() {
        File dir = new File("baza.txt");

        FileCrypter ske;
        try {
            ske = new FileCrypter("poiuytrewq", 16, "AES");

            //int choice = -2;
            //while (choice != -1) {
            //String[] options = {"Encrypt All", "Decrypt All", "Exit"};
            //choice = JOptionPane.showOptionDialog(null, "Select an option", "Options", 0,
            //JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            //switch (choice) {
            //case 0:
            try {
                ske.decryptFile(dir);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                System.err.println("Couldn't decrypt " + dir.getName() + ": " + e.getMessage());
            }
            //System.out.println("Files decrypted successfully");
            //break;
            //case 1:

            //break;
            //default:
            //choice = -1;
            //break;
            //}
            //}
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Couldn't create key: " + ex.getMessage());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e.getMessage());
        }
    }
}