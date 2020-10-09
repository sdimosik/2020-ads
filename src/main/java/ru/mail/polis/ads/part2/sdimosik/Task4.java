package ru.mail.polis.ads.part2.sdimosik;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static BigInteger findOrderStatistic(ArrayList<BigInteger> array, int k) {
        int left = 0, right = array.size() - 1;
        while (true) {
            int mid = partition(array, left, right);
            if (mid == k) {
                return array.get(mid);
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int partition(ArrayList<BigInteger> array, int left, int right) {
        BigInteger v = array.get((left + right) / 2);
        int i = left;
        int j = right;
        while (i <= j) {
            while (array.get(i).compareTo(v) < 0) {
                i++;
            }
            while (array.get(j).compareTo(v) > 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            BigInteger temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
        return j;
    }

    private static void solve(final Scanner in, final PrintWriter out) throws IOException {
        int k = Integer.parseInt(in.nextLine());
        String[] nums = in.nextLine().split(" ");
        ArrayList<BigInteger> arr = new ArrayList<>(nums.length);
        for (String num : nums) {
            arr.add(new BigInteger(num));
        }
        out.write(String.valueOf(findOrderStatistic(arr, arr.size() - k)));
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
