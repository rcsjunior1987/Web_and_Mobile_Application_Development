using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AzureBlobProject.Models.Services
{
    public interface IBlobService
    {
        Task<string> GetBlob(string blobName, string containerName);

        //List of the Blobs inside a container
        Task<List<string>> GetAllBlobs(string containerName);

        Task<List<Blob>> GetAllBlobsWithUri(string containerName);

        //
        Task<bool> UploadBlob(string blobName, IFormFile file, string containerName, Blob blob);

        //
        Task<bool> DeleteBlob(string blobName, string containerName);
    }
}
