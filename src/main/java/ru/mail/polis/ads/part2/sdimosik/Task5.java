package ru.mail.polis.ads.part2.sdimosik;

import java.io.*;
import java.util.*;

public class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static class MyPair {
        int main, helper;

        public MyPair(int main, int helper) {
            this.main = main;
            this.helper = helper;
        }

        public int getMain() {
            return main;
        }

        public int getHelper() {
            return helper;
        }
    }

    private static void mergeSort(MyPair[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        MyPair[] l = new MyPair[mid];
        MyPair[] r = new MyPair[array.length - mid];

        System.arraycopy(array, 0, l, 0, mid);
        if (array.length - mid >= 0) System.arraycopy(array, mid, r, 0, array.length - mid);
        mergeSort(l);
        mergeSort(r);
        merge(array, l, r, mid, array.length - mid);
    }

    private static void merge(MyPair[] array, MyPair[] l, MyPair[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getMain() <= r[j].getMain()) {
                array[k++] = l[i++];
            } else {
                array[k++] = r[j++];
            }
        }
        while (i < left) {
            array[k++] = l[i++];
        }
        while (j < right) {
            array[k++] = r[j++];
        }
    }

    private static void solve(final Scanner in, final PrintWriter out) throws IOException {
        int temp = Integer.parseInt(in.nextLine());
        MyPair[] array = new MyPair[temp];
        for (int i = 0; i < temp; i++) {
            String[] nums = in.nextLine().split(" ");
            array[i] = new MyPair(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
        }
        mergeSort(array);
        for (MyPair pair : array) {
            out.write(pair.main + " " + pair.helper + "\n");
        }
        out.close();
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
