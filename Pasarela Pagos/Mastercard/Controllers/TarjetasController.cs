using System;
using AutoMapper;
using Mastercard.Data;
using Mastercard.Dtos;
using Mastercard.Models;
using Microsoft.AspNetCore.Mvc;

namespace Mastercard.Controllers
{
    [Route("api/mastercard")]
    [ApiController]
    public class TarjetasController : ControllerBase
    {
        private readonly IMastercardRepo _repository;
        private IMapper _mapper;

        public TarjetasController(IMastercardRepo repository, IMapper mapper)
        {
            _repository = repository;
            _mapper = mapper;
        }

        [HttpPut("comprar")]
        public ActionResult RealizarCompra(TarjetaComprarDto tarjeta)
        {


            Tarjeta card = _repository.verificarTarjeta(tarjeta.Numero);

            //logica para verificar las tarjeta
            if (card == null)
                return NotFound();
            if (card.CVC == tarjeta.CVC
            && card.MesVencimiento == tarjeta.MesVencimiento
            && card.AnioVencimiento == tarjeta.AnioVencimiento
            )
            {
                var cupo = card.Cupo;
                var cobro = tarjeta.valorCompra;

                if ((cobro + card.Monto) <= cupo)
                {
                    card.Monto = card.Monto + cobro;
                    _repository.realizarCompra(card);
                    _repository.SaveChanges();
                    return Ok();
                }
                return NotFound();
            }
            return NotFound();
        }

        [HttpGet("validar")]
        public ActionResult<Tarjeta> verificarTarjeta(TarjetaVerificarDto tarjetaWrite)
        {

                                    Console.WriteLine("bien");

            //busca una tarjeta con el mismo número
            var tarjetaverificada = _repository.verificarTarjeta(tarjetaWrite.Numero);

            //logica para verificar las tarjeta
            if (tarjetaverificada == null)
                return NotFound();
            if (tarjetaverificada.CVC == tarjetaWrite.CVC
            && tarjetaverificada.MesVencimiento == tarjetaWrite.MesVencimiento
            && tarjetaverificada.AnioVencimiento == tarjetaWrite.AnioVencimiento
            ) return Ok(tarjetaverificada);

            return NotFound();
        }
    }
}