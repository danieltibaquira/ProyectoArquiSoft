using Mastercard.Models;
using Microsoft.EntityFrameworkCore;

namespace Mastercard.Data
{
    public class MastercardContext : DbContext
    {
        public MastercardContext(DbContextOptions<MastercardContext> opt) : base(opt)
        {

        }
        public DbSet<Tarjeta> Tarjetas {get; set;}
        public DbSet<Usuario> Usuarios {get; set;}
    }
}