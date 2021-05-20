using System.ComponentModel.DataAnnotations;

namespace Mastercard.Models
{
    public class Tarjeta
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public long Numero { get; set; }
        [Required]
        public int CVC { get; set; }
        [Required]
        public int MesVencimiento { get; set; }
        [Required]
        public int AnioVencimiento { get; set; }
        [Required]
        public float Cupo { get; set; }
        [Required]
        public float Monto { get; set; }
        [Required]
        public Usuario Propietario { get; set; }
    }
}