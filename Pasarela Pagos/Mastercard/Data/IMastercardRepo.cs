using System.Collections.Generic;
using Mastercard.Dtos;
using Mastercard.Models;

namespace Mastercard.Data
{
    public interface IMastercardRepo
    {
        bool SaveChanges();

        Tarjeta verificarTarjeta(long numero);

        void realizarCompra(Tarjeta tarjeta);
    }
}