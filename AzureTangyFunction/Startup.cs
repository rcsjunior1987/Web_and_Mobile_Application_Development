using AzureTangyFunc.Data;
using AzureTangyFunction;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

[assembly: WebJobsStartup(typeof(Startup))]
namespace AzureTangyFunction;

public class Startup : IWebJobsStartup
{
    public void Configure(IWebJobsBuilder builder)
    {
        // AzureSqlDataBase from local.settings
        string connectionString = Environment.GetEnvironmentVariable("AzureSqlDatabase");

        //
        builder.Services.AddDbContext<AzureTangyDbContext>(
            options => options.UseSqlServer(connectionString));

        //
        builder.Services.BuildServiceProvider();
    }
}
