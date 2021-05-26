using System.Collections.Generic;
using Mastercard.Dtos;
using Mastercard.Models;

namespace Mastercard.Data
{
    public interface IMastercardRepo
    {
        bool SaveChanges();

        Tarjeta findTarjetabyNum(long numero);

        void updateTarjeta(Tarjeta tarjeta);
    }
}