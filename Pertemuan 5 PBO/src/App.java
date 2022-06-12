import java.util.ArrayList; //untuk Array List
import java.util.Scanner; //untuk menginput

public class App {
    public static void main(String[] args) throws Exception {
       
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>(); //untuk membuat sebuah ruang inputan
        ArrayList<String> logMutasi = new ArrayList<String>();
        //app banking
        //1. tambah nasabah
        //2. setor uang
        //3. cetak mutasi
        //4. transfer uang
        //5. cetak nasabah
        //5. keluar
        Scanner input = new Scanner(System.in); //untuk menginput
        init(nasabah); 
        String yn ="y";
        do{
            //cetak menu
            menu();

            int pilihan =0;
            pilihan = input.nextInt(); //untuk menyimpan imputan dari kita

            if(pilihan==1){ //tambah data nasabah
                //scanner untuk terima inputan
                //nasabah.add inputan yg diterima dalam string, 
                String NoRek;
                String Namansb;
                long Saldo=0;

                System.out.print("Nama Nasabah \t:\t");
                Namansb =input.next();

                System.out.print("Nomor Rekening \t:\t");
                NoRek = input.next();

                System.out.print("Saldo Awal \t:\t");
                Saldo = input.nextLong();

                nasabah.add(new Nasabah(NoRek, Namansb, Saldo)); //untuk menyimpan data dalam ArrayList
            }
            else if(pilihan==2){ //setor uang
                String NoRek;
                long setoran=0;

                System.out.print("Nomor Rekening \t:\t");
                NoRek= input.next();

                System.out.print("Nominal setoran (IDR) \t:\t");
                setoran= input.nextLong();

                //cek apakah nomor rek terdaftar? jika iya tambahakn saldonya
                //jika tidak "nomor rekening tidak ditemukan"
                // if(nasabah.contains(new Nasabah(NoRek)))
                //    System.out.println("Nomor rekening ditemukan");
                // else 
                //    System.out.println("Nomor rekening tidak ditemukan");
                int i=0; //index
                for (Nasabah nasabah2 : nasabah) {
                    if(nasabah2.getNoRek().equals(NoRek)){
                        System.out.println("Nomor rekening ditemukan");
                        System.out.println(nasabah2);
                        Nasabah tmpnsb = nasabah2;
                        tmpnsb.setSaldo(tmpnsb.getSaldo() + setoran);
                        nasabah.set(i, tmpnsb); //untuk menambah saldo dalam rek

                        System.out.println("Setoran berhasil");
                        logMutasi.add("Setor uang " + setoran + " ke Rekening " + tmpnsb.getNoRek() + " " + tmpnsb.getNamaNsb());
                    }
                    i++;
                }
            }
            else if(pilihan==3){ //cetak mutasi
                for (String string : logMutasi) {
                    System.out.println(string);
                }

            }else if(pilihan==4){ //transfer uang
                String NoRek3;
                String NoRek4;
                long tf=0;
                System.out.print ("Masukkan Nomor Rekening \t:\t");
                NoRek3 = input.next();
                int i=0;
                for (Nasabah nasabah3 : nasabah) 
                {i+=1;
                    if(nasabah3.getNoRek().equals(NoRek3))
                    {
                        System.out.println("Rekening Terdaftar di data "+i);
                        System.out.println("Nomor rekening " + nasabah3.getNamaNsb());
                        System.out.print ("Masukkan Nomor Rekening yang dituju \t:\t");
                        
                        NoRek4 = input.next();
                        
                        for (Nasabah nasabah4 : nasabah) 
                        {
                            
                            if(nasabah4.getNoRek().equals(NoRek4))
                            {
                                System.out.println("Rekening  "+ nasabah4.getNamaNsb());
                                System.out.print ("Nominal Transfer (IDR)\t:\t");
                                tf = input.nextLong();

                                if(tf<nasabah3.getSaldo() & (nasabah3.getSaldo())>=0)
                                {
                                    Nasabah tmpnsb2 = nasabah3;
                                    Nasabah tmpnsb3 = nasabah4;
                                    tmpnsb2.setSaldo(tmpnsb2.getSaldo()- tf);
                                    nasabah.set(i, tmpnsb2);
                                    tmpnsb3.setSaldo(tmpnsb3.getSaldo() + tf);
                                    nasabah.set(i, tmpnsb3);
                                    logMutasi.add("Transfer " + tf + " ke rekening " + tmpnsb3.getNoRek() + " AN " + tmpnsb3.getNamaNsb());
                                    System.out.println("\n<Transaksi Berhasil>");
                                    System.out.println("Saldo Tersisah " + tmpnsb2.getSaldo());
                                    break;
                                }
                                else
                                {
                                    System.out.println("\n<Transaksi gagal>");
                                    System.out.println("Saldo tidak mencukupi");  
                                }
                            } i++;
                        }
                       
                        break;
                    }
                    else if (!nasabah3.getNoRek().equals(NoRek3))
                    { System.out.println("Rekening Tidak Terdaftar di data "+i);}
                    
                }
                
            }else if(pilihan==5){ //cetak nasabah 
                cetakNamaNasabah(nasabah);
            }
            else if(pilihan==6) { break;}
            else { continue;}

            System.out.print("Apakah anda ingin kembali ke menu utama (y/n) :");
            yn = input.next();
        }while(yn.equalsIgnoreCase("y"));
        input.close();

        //scanner untuk terima inputan
        //nasabah.add inputan yg diterima dalam string, 

        // cetakNamaNasabah(nasabah);

        // nsb3.setNamansb("Kimberly");
        // nasabah.set(2,nsb3);
        // cetakNamaNasabah(nasabah);

        // hapusNasabah(nasabah,1);
        // cetakNamaNasabah(nasabah);

        // hapusNasabah(nasabah,nsb1);
        // cetakNamaNasabah(nasabah);
    }

    public static ArrayList<Nasabah> init(ArrayList<Nasabah> nasabah){
        Nasabah nsb1 =  new Nasabah("0123789","Mike",500000);
        nasabah.add(nsb1);
        Nasabah nsb2 =  new Nasabah("0456789","Jian",500000);
        nasabah.add(nsb2);
        Nasabah nsb3 =  new Nasabah("0456123","Jason",500000);
        nasabah.add(nsb3);
        nasabah.add( new Nasabah("0789456","Januar",500000));
        return nasabah;
    }
    public static void menu(){
                //app banking
        //1. tambah nasabah
        //2. setor uang
        //3. cetak mutasi
        //4. transfer uang
        //5. keluar
        System.out.println("Aplikasi Banking oleh Christopher Alexander Chai");
        System.out.println("1. Tambah Nasabah Baru");
        System.out.println("2. Setor Uang");
        System.out.println("3. Cetak Mutasi");
        System.out.println("4. Transfer Uang");
        System.out.println("5. Cetak Data Nasabah");
        System.out.println("6. Keluar");
        System.out.print("Masukkan Pilihan Anda : ");
    }

    public static void hapusNasabah(ArrayList<Nasabah> nasabah,int idx){
        nasabah.remove(idx);
    }
    public static void hapusNasabah(ArrayList<Nasabah> nasabah,Nasabah nsb){
        nasabah.remove(nsb);
    }

    public static void cetakNamaNasabah(ArrayList<Nasabah> nasabah){
        System.out.println("No.Rekening\tNama\t\tSaldo\tNo.kartu\tPIN\tTgl. Daftar");
        System.out.println("-------------------------------------------------------------------------");
        for (Nasabah nsbh : nasabah) {
            System.out.println(nsbh);
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}