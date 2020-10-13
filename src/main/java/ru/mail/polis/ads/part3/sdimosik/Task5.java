package ru.mail.polis.ads.part3.sdimosik;

import java.io.*;
import java.util.*;

public class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static boolean isCorrect(int[] coords, int num, int numCows) {
        int cows = 1, last = coords[0];
        for (int value : coords) {
            if (value - last >= num) {
                cows++;
                last = value;
            }
        }
        return cows >= numCows;
    }

    private static void solve(final Scanner in, final PrintWriter out) throws IOException {
        int[] num = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] coords = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = coords[coords.length - 1] - coords[0] + 1;
        while (right - left > 1) {
            int middle = (left + right) / 2;
            if (isCorrect(coords, middle, num[1])) {
                left = middle;
            } else {
                right = middle;
            }
        }
        System.out.println(left);
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
