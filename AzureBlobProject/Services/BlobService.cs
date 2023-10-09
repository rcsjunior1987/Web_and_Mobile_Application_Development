using Azure.Storage.Blobs;
using Azure.Storage.Blobs.Models;

namespace AzureBlobProject.Models.Services
{
    public class BlobService : IBlobService
    {
        private readonly BlobServiceClient _blobClient;

        public BlobService(BlobServiceClient blobClient)
        {
            _blobClient = blobClient;
        }

        public async Task<bool> DeleteBlob(string blobName, string containerName)
        {
            // Declare the Blob containerClient
            BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);

            // Get the blob from its name
            var blobClient = blobContainerClient.GetBlobClient(blobName);

            return await blobClient.DeleteIfExistsAsync();
        }

        public async Task<string> GetBlob(string blobName, string containerName)
        {
            // Declare the Blob containerClient
            BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);

            // Get the blob from its name
            var blobClient = blobContainerClient.GetBlobClient(blobName);

            return blobClient.Uri.AbsoluteUri;
        }

        public async Task<List<string>> GetAllBlobs(string containerName)
        {
            BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);

            // Declare the list of blobs names
            var blobsString = new List<string>();

            var blobs = blobContainerClient.GetBlobsAsync();

            await foreach (var item in blobs)
            {
                blobsString.Add(item.Name);
            }

            return blobsString;
        }

        public async Task<bool> UploadBlob(string blobName, IFormFile file, string containerName)
        {
            // Declare the Blob containerClient
            BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);

            // Get the blob from its name
            var blobClient = blobContainerClient.GetBlobClient(blobName);

            //
            var httpHeader = new BlobHttpHeaders()
            {
                ContentType = file.ContentType
            };

            //
            var result = await blobClient.UploadAsync(file.OpenReadStream(), httpHeader);

            if (result != null)
            {
                return true;
            }

            return false;
        }
    }
}
