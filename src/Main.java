import lib.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Pembesaran citra dengan interpolasi bicubic");
        System.out.println("8. Keluar");
        System.out.printf("Pilihan: ");
        int choice = sc.nextInt();
        while(choice < 1 || choice > 8){
            System.out.printf("Masukan tidak valid! Silakan ulangi...\nPilihan: ");
            choice = sc.nextInt();
        }
        if(choice == 1){
            SPL.DriverSPL();
        }
        else if(choice == 2){
            Determinant.DriverDeterminan();
        }
        else if(choice == 3){
            Balikan.DriverBalikan();
        }
        else if(choice == 4){
            Interpolate.SolveInterpolate();
        }
        else if(choice == 5){
            Bicubic.SolveBicubic();
        }
        else if(choice == 6){
            Regresi.SolveRegression();
        }
        else if (choice == 7){
            ImageProcessing.ImageProcessingDriver();
        }
        else{
            System.out.println("Terima kasih telah menggunakan program ini!");
        }
    }
}
