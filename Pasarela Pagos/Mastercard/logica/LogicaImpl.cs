using Mastercard.Data;
using Mastercard.Dtos;

namespace Mastercard.logica
{
    public class LogicaImpl : ILogica
    {
        private readonly IMastercardRepo _repository;

        public LogicaImpl(IMastercardRepo repository)
        {
            _repository = repository;
        }

        public bool realizarCompra(TarjetaComprarDto tarjeta)
        {
            var card = _repository.findTarjetabyNum(tarjeta.Numero);

            if (card == null){
                return false;
            }
            if(card.CVC == tarjeta.CVC
            && card.MesVencimiento == tarjeta.MesVencimiento
            && card.AnioVencimiento == tarjeta.AnioVencimiento
            ) {
                var cupo = card.Cupo;
                var cobro = tarjeta.valorCompra;

                if ((cobro + card.Monto) <= cupo)
                {
                    card.Monto = card.Monto + cobro;
                    _repository.updateTarjeta(card);
                    _repository.SaveChanges();
                    return true;
                }
                return false;
            };
            return false;
        }
        public bool verificarTarjeta(TarjetaVerificarDto tarjetaWrite)
        {
           //busca una tarjeta con el mismo número
            var tarjetaverificada = _repository.findTarjetabyNum(tarjetaWrite.Numero);

            if (tarjetaverificada == null){
                return false;
            }
            if(tarjetaverificada.CVC == tarjetaWrite.CVC
            && tarjetaverificada.MesVencimiento == tarjetaWrite.MesVencimiento
            && tarjetaverificada.AnioVencimiento == tarjetaWrite.AnioVencimiento
            ) return true;
            return false;
        }
    }
}