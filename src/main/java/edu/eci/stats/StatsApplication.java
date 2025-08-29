package edu.eci.stats;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.eci.stats.collections.SinglyLinkedList;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StatsApplication {

    public static void main(String[] args) {
        InputStream is = StatsApplication.class.getResourceAsStream("/input.txt");
		Scanner sc = new Scanner(is);
		int casos = sc.nextInt(); 

		for (int c = 1; c <= casos; c++) {
		    int n = sc.nextInt(); 
		    List<Double> data = new SinglyLinkedList<>();

		    for (int i = 0; i < n; i++) {
		        data.add(sc.nextDouble());
		    }

		    double mean = Stats.mean(data);
		    double stddev = Stats.sampleStdDev(data);

		    System.out.printf("Caso %d:%n", c);
		    System.out.printf("Mean = %.2f%n", mean);
		    System.out.printf("StdDev = %.2f%n", stddev);
		}

		sc.close();
    }
}
