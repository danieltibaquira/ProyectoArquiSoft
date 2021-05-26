using Mastercard.Models;
using System.Linq;
using System.Collections.Generic;
using System;
using Mastercard.Dtos;

namespace Mastercard.Data
{
    public class sqlMastercardRepo : IMastercardRepo
    {
        private readonly MastercardContext _context;

        public sqlMastercardRepo(MastercardContext context)
        {
            _context = context;
        }

        public void updateTarjeta(Tarjeta tarjeta)
        {
            //nothing
        }

        public bool SaveChanges()
        {
            return (_context.SaveChanges() >= 0);
        }

        public Tarjeta findTarjetabyNum(long numerotajeta)
        {
            Tarjeta card = _context.Tarjetas.FirstOrDefault(p => p.Numero == numerotajeta);

            return card;
        }


    }
}