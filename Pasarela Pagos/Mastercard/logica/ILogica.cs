using Mastercard.Dtos;

namespace Mastercard.logica
{
    public interface ILogica
    {
        bool verificarTarjeta(TarjetaVerificarDto tarjetaWrite);

        bool realizarCompra(TarjetaComprarDto tarjeta);
    }
}