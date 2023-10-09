using Azure.Storage.Blobs;
using AzureBlobProject.Models.Services;
using Microsoft.AspNetCore.Mvc;
using static System.Net.Mime.MediaTypeNames;

namespace AzureBlobProject.Controllers;

public class BlobController : Controller
{
    //
    private readonly IBlobService _blobService;

    public BlobController(IBlobService blobService)
    {
        _blobService = blobService;
    }

    // Create the action MANAGE
    [HttpGet]
    public async Task<IActionResult> Manage(string containerName)
    {
        var blobObj = await _blobService.GetAllBlobs(containerName);
        return View(blobObj);
    }

    [HttpGet]
    public IActionResult AddFile(string containerName)
    {
        return View();
    }

    [HttpPost]
    public async Task<IActionResult> AddFile(string containerName, IFormFile file)
    {
        if (file == null || file.Length < 1)
            return View();

        // To avoid a file to be overwritten, it is changed to FILE NAME + _ + GUID, For instance, rcsjunior.jpg will turn rcsjunior_0001
        var fileName = Path.GetFileNameWithoutExtension(file.Name) + "_"  + Guid.NewGuid() + Path.GetExtension(file.FileName);

        var result = await _blobService.UploadBlob(fileName, file, containerName);

        if (result)
            return RedirectToAction("Index", "Container");

        return View();
    }

    [HttpGet]
    public async Task<IActionResult> ViewFile(string blobName, string containerName)
    {
        return Redirect(await _blobService.GetBlob(blobName, containerName));
    }

    [HttpGet]
    public async Task<IActionResult> DeleteFile(string blobName, string containerName)
    {
        await _blobService.DeleteBlob(blobName, containerName);


        
        return View();
    }

}
