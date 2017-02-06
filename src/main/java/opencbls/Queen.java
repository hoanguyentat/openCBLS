package opencbls;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Queen {
	
	private int x[];
	private int n;
	Random r;
	
	public Queen(int n) {
		// TODO Auto-generated constructor stub
		this.n = n;
		x = new int[n];
		r = new Random();
	}
	
	//Conflict totals
	private int conflict() {
		int c = 0;
		for (int i = 0; i < this.n; i++) {
			c+= this.conflict(i);		
		}
		return c/2;
	}
	
	// Conflict of a Queen
	private int conflict(int index) {
		int c = 0;
		for (int i = 0; i < this.n; i++) {
			if (i != index) {
				if (x[index] == x[i]) c++;
				if (x[index] - index  == x[i] - i) c++;
				if (x[index] + index  == x[i] + i) c++;
			}
		}
		return c;
	}
	
	private void init() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.n; i++) {
			x[i] = this.r.nextInt(this.n);
			
		}
	}
	
	private int move(int index) {
		int min = Integer.MAX_VALUE;
		//Save the old value
		int oldValues = x[index];
		ArrayList<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < this.n; i++) {
			x[index] = i;
			int conflicts = this.conflict();
			if (conflicts < min) {
				min = conflicts;
				ls.clear();
				ls.add(i);
			} else if (conflicts == min){
				ls.add(i);
			}
		}
		x[index] = ((Integer) ls.get(this.r.nextInt(ls.size()))).intValue();
//		System.out.println(x[index]);
		return x[index];
	}
	
	private void solve() {
//		init();
		// TODO Auto-generated method stub
		int iter = 0;
		while(iter < 10000){
			int totalConflicts = this.conflict();
			System.out.println("Step: " + iter + ",conflict: " + totalConflicts);;
			if(totalConflicts == 0){
				break;
			}
			ArrayList list = new ArrayList();
			int max = -1;
			
			//find a queen have the most errors
			for (int i = 0; i < this.n; i++) {
				int c = this.conflict(i);
				if(c > max){
					max = c;
					list.clear();
					list.add(i);
				} else if(c == max) {
					list.add(i);
				}
			}
			this.move(((Integer) list.get(this.r.nextInt(list.size()))).intValue());
			iter++;
		}
	}
	
	private void print_html() throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream("result.html");
		PrintWriter pw = new PrintWriter(fos);
		pw.write("<table border='1'>");
		for(int i = 0; i < n; i++){
			pw.write("<tr>");
			for(int j = 0; j < n; j++){
				if(x[i] == j) pw.write("<td width='5' height='5' bgColor='red'></td>\n");
				else pw.write("<td width='5' height='5' bgColor = 'green'></td>\n");
			}
			pw.write("</tr>\n");
		}
		pw.write("</table>");
		pw.close();
	}
	public static void main(String[] args) throws FileNotFoundException {
		Queen nQueens = new Queen(100);
		nQueens.solve();
		nQueens.print_html();
	}
}
