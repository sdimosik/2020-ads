package ru.mail.polis.ads.part1.sdimosik;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        int num = new Scanner(System.in).nextInt();
        System.out.println((num - num % 10) / 10 + " " + num % 10);
    }
}
