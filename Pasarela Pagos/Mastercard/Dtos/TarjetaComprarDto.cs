using System.ComponentModel.DataAnnotations;

namespace Mastercard.Dtos
{
    public class TarjetaComprarDto
    {
        public long Numero { get; set; }
        public int CVC { get; set; }
        public int MesVencimiento { get; set; }
        public int AnioVencimiento { get; set; }
        public float valorCompra { get; set; }
    }
}