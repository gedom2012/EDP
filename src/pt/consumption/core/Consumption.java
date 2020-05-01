package pt.consumption.core;

public class Consumption {

	private Integer vazioOld, pontaOld, cheiaOld, dias, consumoGasOld;
	private double edpPackage;
	private double dgecg = 0.07, iec = 0.001, fatorPotencia = 0.3964, kwhFactor = 0.1511, value, valueIva, iva = 1.23,
			descontoEDP = 0.94, audioVisual = 2.85, valueGas, valueGasIva, fcv = 0.96759000, pcs = 11.89924900,
			iecGnc = 0.00472425, tosFixo, tosVariavel = 0.0058932, coefGnc = 0.0621;

	public Consumption(Integer vazioOld, Integer pontaOld, Integer cheiaOld, Integer dias, Integer consumoGasOld,
			double edpPackage) {
		super();
		this.vazioOld = vazioOld;
		this.pontaOld = pontaOld;
		this.cheiaOld = cheiaOld;
		this.dias = dias;
		this.consumoGasOld = consumoGasOld;
		this.edpPackage = edpPackage;
	}

	public void lightValue(Integer vazio, Integer ponta, Integer cheia) {
		Integer consumoKw;
		consumoKw = ((vazio + ponta + cheia) - (vazioOld + pontaOld + cheiaOld));
		value = (consumoKw * kwhFactor) + (consumoKw * iec) + (dias * fatorPotencia) + dgecg;
		value = (value * descontoEDP) + edpPackage;
		valueIva = (value + audioVisual + edpPackage) * iva;

		System.out.println("Seu consumo total em KwH foi de...: " + consumoKw);
		System.out.printf("Seu custo de energia sem taxas é... " + "%.2f", value);
		System.out.println();
		System.out.printf("Seu custo de energia com taxas é... " + "%.2f", valueIva);

	}

	public void gasValue(Integer consumoGas) {
		double kwh = (consumoGas - consumoGasOld);
		kwh = kwh * fcv * pcs;
		tosFixo = dias * iecGnc;
		valueGas = (kwh * coefGnc) + (kwh * tosVariavel) + tosFixo;
		valueGasIva = valueGas * iva;

		System.out.println();
		System.out.printf("Seu consumo de gas em KwH foi de...: " + "%.0f", kwh);
		System.out.println();
		System.out.printf("Seu custo de gas sem taxas é... " + "%.2f", valueGas);
		System.out.println();
		System.out.printf("Seu custo de gas com taxas é... " + "%.2f", valueGasIva);

	}

}
