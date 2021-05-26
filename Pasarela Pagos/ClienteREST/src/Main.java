import java.util.Scanner;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		RestClient client = new RestClient();

		// variables
		long numero;
		int cvc;
		int mes;
		int anio;
		float monto;
		//response
		Response response;
		//tarjeta
		VerificarTarjeta verificar;
		//switch
		boolean bandera = true;
		int opcion;
		
		while (bandera) {
			System.out.println("1.Verificar tarjeta");
			System.out.println("2.Realizar compra");
			System.out.println("3.Salir");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				verificar = new VerificarTarjeta();
				System.out.print("Numero de tarjeta:");
				numero = sc.nextLong();
				System.out.print("CVC:");
				cvc = sc.nextInt();
				System.out.print("Mes vencimiento:");
				mes = sc.nextInt();
				System.out.print("Anio vencimiento:");
				anio = sc.nextInt();
				
				verificar.setAnioVencimiento(anio);
				verificar.setCVC(cvc);
				verificar.setMesVencimiento(mes);
				verificar.setNumero(numero);

				response = client.verificarTarjeta(verificar);
				System.out.println(response.readEntity(String.class));
				break;
			case 2:
				verificar = new VerificarTarjeta();
				System.out.print("Numero de tarjeta:");
				numero = sc.nextLong();
				System.out.print("CVC:");
				cvc = sc.nextInt();
				System.out.print("Mes vencimiento:");
				mes = sc.nextInt();
				System.out.print("Anio vencimiento:");
				anio = sc.nextInt();
				System.out.print("Valor compra:");
				monto = sc.nextFloat();
				
				verificar.setAnioVencimiento(anio);
				verificar.setCVC(cvc);
				verificar.setMesVencimiento(mes);
				verificar.setNumero(numero);
				verificar.setValorCompra(monto);
				
				response = client.comprar(verificar);
				System.out.println(response.readEntity(String.class));
				break;
			case 3:
				bandera = false;
			default:
				break;
			}
		}
		sc.close();

	}

}
