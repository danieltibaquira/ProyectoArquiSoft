using AutoMapper;
using Mastercard.Dtos;
using Mastercard.Models;

namespace Mastercard.Profiles
{
    public class MastercardProfile : Profile
    {
        public MastercardProfile()
        {
            CreateMap<Tarjeta, TarjetaVerificarDto>();
            CreateMap<TarjetaVerificarDto, Tarjeta>();

        }
    }
}