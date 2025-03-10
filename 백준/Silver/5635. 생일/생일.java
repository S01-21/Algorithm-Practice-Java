import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static Student[] list;
	static class Student implements Comparable<Student>{
		String name;
		int day, month, year;
		public Student(String name, int day, int month, int year) {
			this.name = name;
			this.day = day;
			this.month = month;
			this.year = year;
		}
		public int compareTo(Student o) {	// 나이적은순 
			if (o.year != this.year) {
				return o.year - this.year;
			} else {
				if (o.month != this.month) {
					return o.month - this.month;
				} else {
					return o.day - this.day;
				}
			}
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new Student[N];
		for (int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());
			list[i] = new Student(name, day, month, year);
		}
		Arrays.sort(list);
		sb.append(list[0].name).append("\n");
		sb.append(list[N-1].name);
		System.out.println(sb);
		br.close();
	}
}
