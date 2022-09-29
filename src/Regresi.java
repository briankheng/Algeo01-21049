import lib.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Regresi {

    public static void SolveRegression(){
        // Menerima input dari user
        Scanner sc = new Scanner(System.in);
        BufferedReader scFile = new BufferedReader(new InputStreamReader(System.in));

        int n = 0;
        int m=0;
        double x;
        Matrix M= new Matrix(100,100);

        System.out.printf("1. Masukan dari keyboard\n2. Masukan dari file\n");
        int choice = sc.nextInt();
        while(choice != 1 && choice != 2){
            System.out.printf("Masukan tidak valid! Silakan ulangi...\n");
            choice = sc.nextInt();
        }
        
        if(choice == 1){
            System.out.printf("Masukkan jumlah variabel: ");
            n = sc.nextInt();
            System.out.printf("Masukkan jumlah data: ");
            m = sc.nextInt();
            Matrix MData = new Matrix(n,m);
            
            System.out.printf("Masukkan titik-titik x dan y:\n");
            for(int j = 0; j < m; j++){

                for(int i=0;i<n;i++){
                    x=sc.nextDouble();
                    MData.setElmt(i, j, x);
                }
            }
            M=MData;
        }
        else{
            Boolean found = false;
            while(!found){
                found = true;
                String fileName = "";
                System.out.printf("Masukkan nama file: ");
                try{
                    fileName = scFile.readLine();
                }
                catch(IOException err){
                    err.printStackTrace();
                }
                try{
                    Scanner file = new Scanner(new File("../test/"+fileName));
                    n=file.nextInt();
                    m=file.nextInt();
                    Matrix MData = new Matrix(n,m);

                    for(int i = 0; i < n; i++){
                        for(int j=0;j<m;j++){
                            x=file.nextDouble();
                            MData.setElmt(i, j, x);
                        }
                    }
                    M=MData;
                }
                catch(FileNotFoundException err){
                    err.printStackTrace();
                    found = false;
                }
            }
            
        }

        //Fungsi Regresi
        int i,j,k;
        int pass;
        double temp,sum;
        double res;
        int nRow=M.getRowEff();
        int nCol=M.getColEff();

        Matrix MUse = new Matrix(nRow, nRow+1);
        i=0;
        for(j=0;j<nRow+1;j++){
            if(j==0)MUse.setElmt(i, j, nCol);
            else{
                sum=0;
                for(k=0;k<nCol;k++){
                    temp=M.getElmt(j-1, k);
                    sum+=temp;
                }
                MUse.setElmt(i, j, sum);
            }
        }

        for(i=1;i<nRow;i++){
            for(j=0;j<nRow+1;j++){
                if(j==0){
                    sum=0;
                    for(k=0;k<nCol;k++){
                        temp=M.getElmt(i-1, k);
                        sum+=temp;
                    }
                    MUse.setElmt(i, j, sum);
                }
                else{
                    sum=0;
                    for(k=0;k<nCol;k++){
                        temp=M.getElmt(i-1, k) * M.getElmt(j-1, k);
                        sum+=temp;
                    }
                    MUse.setElmt(i, j, sum);
                }

            }

        }
        MUse.printMatrix();
        SPL solution = new SPL();
        solution.GaussJordan(MUse);
        double[] a = solution.x;
        String save;
        System.out.printf("Persamaannya adalah \n ");
        System.out.printf("y =");
        for(i=0;i<solution.nEff;i++){
            save=" ";
            if(i!=0&&solution.x[i]>0)save+="+";
            save+=Double.toString(solution.x[i]);
            save+=" x" + Integer.toString(i+1);
            System.out.printf(save);
        }
        System.out.printf("\n");
        System.out.printf("Apakah jawaban ingin disimpan dalam file?\n1. Ya\n2. Tidak\n");
        choice = sc.nextInt();
        while(choice != 1 && choice != 2){
            System.out.printf("Masukan tidak valid! Silakan ulangi...\n");
            choice = sc.nextInt();
        }
        if(choice == 1){
            String fileName = "";
            System.out.printf("Masukkan nama file: ");
            try{
                fileName = scFile.readLine();
            }
            catch(IOException err){
                err.printStackTrace();
            }
            try{
                FileWriter file = new FileWriter("../test/"+fileName);
                file.write("Luaran untuk Regresi adalah y = ");

                for(i=0;i<solution.nEff;i++){
                    save=" ";
                    if(i!=0&&solution.x[i]>0)save+="+";
                    save+=Double.toString(solution.x[i]);
                    save+=" x" + Integer.toString(i+1);
                    file.write(save);
                }
                
                file.write("\n");
                file.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }

    }
}
