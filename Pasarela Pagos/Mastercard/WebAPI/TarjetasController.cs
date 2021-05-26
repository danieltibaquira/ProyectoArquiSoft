using System;
using AutoMapper;
using Mastercard.Data;
using Mastercard.Dtos;
using Mastercard.logica;
using Mastercard.Models;
using Microsoft.AspNetCore.Mvc;

namespace Mastercard.Controllers
{
    [Route("api/mastercard")]
    [ApiController]
    public class TarjetasController : ControllerBase
    {
        private readonly ILogica _repository;
        private IMapper _mapper;

        public TarjetasController(ILogica repository, IMapper mapper)
        {
            _repository = repository;
            _mapper = mapper;
        }

        [HttpPut("comprar")]
        public ActionResult RealizarCompra(TarjetaComprarDto tarjeta)
        {

            Console.WriteLine("Comprar");

            bool respuesta = _repository.realizarCompra(tarjeta);

            if(respuesta) {
                return Ok("Compra realizada");
            }else{
                return Ok("Compra rechazada");
            }
        }

        [HttpPost("validar")]
        public ActionResult<Tarjeta> verificarTarjeta(TarjetaVerificarDto tarjetaWrite)
        {

            Console.WriteLine("Verificaar");

            bool respuesta = _repository.verificarTarjeta(tarjetaWrite);

            if(respuesta) {
                return Ok("Tarjeta verificada");
            }else{
                return Ok("Tarjeta rechazada");
            }
        }
    }
}