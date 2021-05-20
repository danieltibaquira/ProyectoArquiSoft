using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Mastercard.Models
{
    public class Usuario
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public string Nombres { get; set; }
        [Required]
        public string Apellidos { get; set; }
        [Required]
        public DateTime FechaNacimiento { get; set; }
        [Required]
        public char Genero { get; set; }
        [Required]
        public string Pais { get; set; }
        [Required]
        public string Ciudad { get; set; }
        [Required]
        public string Direccion { get; set; }
        public long Celular { get; set; }
        [Required]
        public long Cedula { get; set; }
        public ICollection<Tarjeta> Tarjetas { get; set; }
    }
}