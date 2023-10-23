using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using AzureTangyFunction.Models;

namespace AzureTangyFunction
{
    public static class OnSalesUploadWriteToQueue
    {
        [FunctionName("OnSalesUploadWriteToQueue")]
        public static async Task<IActionResult> Run(
            [HttpTrigger(AuthorizationLevel.Function, "get", "post", Route = null)] HttpRequest req
           ,[Queue("SalesRequestInBound",Connection ="AzureWebJobsStorage")] IAsyncCollector<SalesRequest> salesRequestQueue
           ,ILogger log)
        {
            log.LogInformation("Sales Request received by OnSalesUploadWriteToQueue function.");

            //string name = req.Query["name"];

            string requestBody = await new StreamReader(req.Body).ReadToEndAsync();
            SalesRequest data = JsonConvert.DeserializeObject<SalesRequest>(requestBody);
            //name = name ?? data?.name;

            //Through into a queue
            await salesRequestQueue.AddAsync(data);

            string responseMessage = "Sales Request has been received for ." + data.Name;

            /*string responseMessage = string.IsNullOrEmpty(name)
                ? "This HTTP triggered function executed successfully. Pass a name in the query string or in the request body for a personalized response."
                : $"Hello, {name}. This HTTP triggered function executed successfully.";*/

            return new OkObjectResult(responseMessage);
        }
    }
}
