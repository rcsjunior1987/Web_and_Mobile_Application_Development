using Azure.Storage.Blobs;
using Azure.Storage.Blobs.Models;
using AzureFunctionTangyWeb.Models;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Diagnostics;

namespace AzureFunctionTangyWeb.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    static readonly HttpClient client = new HttpClient();
    private readonly BlobServiceClient _blobServiceClient;

    public HomeController(ILogger<HomeController> logger, BlobServiceClient blobServiceClient)
    {
        _blobServiceClient = blobServiceClient;
        _logger     = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    public IActionResult Privacy()
    {
        return View();
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }

    [HttpPost]
    public async Task<IActionResult> Index(SalesRequest salesRequest, IFormFile file)
    {
        salesRequest.Id = Guid.NewGuid().ToString();

        using (var content = new StringContent(JsonConvert.SerializeObject(salesRequest)
            , System.Text.Encoding.UTF8
            , "application/json"))
        {
            HttpResponseMessage response = await client.PostAsync("http://localhost:7113/api/OnSalesUploadWriteToQueue", content);

            string returnValue = response.Content.ReadAsStringAsync().Result;
        }

        //Check any image is uploaded 
        if(file != null)
        {   
            // Change the name of the image received
            var fileName = salesRequest.Id + Path.GetExtension(file.FileName);

            //Instance of the Container to be used in this project, named functionsalesrep
            BlobContainerClient blobContainerClient = _blobServiceClient.GetBlobContainerClient("functionsalesrep");

            //Creates a blobClient to retrieve the file name from the blob functionsalesrep
            var blobClient = blobContainerClient.GetBlobClient(fileName);

            // 
            var httpHeaders = new BlobHttpHeaders
            {
                ContentType = file.ContentType
            };

            //Upload the fileName from the Blob
            await blobClient.UploadAsync(file.OpenReadStream(), httpHeaders);

            return View();
        }

        return RedirectToAction(nameof(Index));
    }



}