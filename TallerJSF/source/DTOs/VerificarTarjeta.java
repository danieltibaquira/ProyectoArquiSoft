package DTOs;

public class VerificarTarjeta {
	
	private long Numero;
	private int CVC;
	private int MesVencimiento;
	private int AnioVencimiento;
	private float valorCompra;
	
	
	
	public float getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}
	public long getNumero() {
		return Numero;
	}
	public void setNumero(long numero) {
		Numero = numero;
	}
	public int getCVC() {
		return CVC;
	}
	public void setCVC(int cVC) {
		CVC = cVC;
	}

	public int getMesVencimiento() {
		return MesVencimiento;
	}
	public void setMesVencimiento(int mesVencimiento) {
		MesVencimiento = mesVencimiento;
	}
	public int getAnioVencimiento() {
		return AnioVencimiento;
	}
	public void setAnioVencimiento(int anioVencimiento) {
		AnioVencimiento = anioVencimiento;
	}
	
	
}
