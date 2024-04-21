package lib;

public class TaxFunction {

    /**
     * Menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        validateMonthWorking(numberOfMonthWorking);

        // Maksimum 3 anak yang diperhitungkan
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }

        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        // Hitung pajak
        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - nonTaxableIncome;
        int tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(0, tax);
    }

    // Validasi jumlah bulan kerja
    private static void validateMonthWorking(int numberOfMonthWorking) {
        if (numberOfMonthWorking > 12) {
            System.err.println("Error: More than 12 months working per year");
        }
    }

    // Menghitung penghasilan tidak kena pajak berdasarkan status perkawinan dan jumlah anak
    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = 54000000; // Standar penghasilan tidak kena pajak untuk yang belum menikah
        if (isMarried) {
            nonTaxableIncome += 4500000; // Tambahan penghasilan tidak kena pajak untuk yang sudah menikah
        }
        nonTaxableIncome += numberOfChildren * 1500000; // Tambahan per anak sampai anak ketiga
        return nonTaxableIncome;
    }
}
