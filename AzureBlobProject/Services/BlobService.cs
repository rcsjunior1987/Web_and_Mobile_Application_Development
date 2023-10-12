using Azure.Storage.Blobs;
using Azure.Storage.Blobs.Models;
using Azure.Storage.Blobs.Specialized;
using Azure.Storage.Sas;
using Microsoft.AspNetCore.Mvc.Rendering;

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

        public async Task<bool> UploadBlob(string blobName, IFormFile file, string containerName, Blob blob)
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

            // Declare the blob metadata as a collection of strings
            IDictionary<string, string> metaData = new Dictionary<string, string>();

            metaData.Add("title", blob.Title);
            metaData["comment"] = blob.Comment;

            //
            var result = await blobClient.UploadAsync(file.OpenReadStream(), httpHeader, metaData);

            // Remove all metadata title before insert the new ones
            metaData.Remove("title");

            // Remove all metadata before insert the new ones
            //IDictionary<string, string> removeMetaData = new Dictionary<string, string>();
            
            //Set Metadata
            await blobClient.SetMetadataAsync(metaData);

            if (result != null)
            {
                return true;
            }

            return false;
        }

        public async Task<List<Blob>> GetAllBlobsWithUri(string containerName)
        {
            // Declare the Blob containerClient by its name
            BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);

            //Get all Blobs
            var blobs = blobContainerClient.GetBlobsAsync();

            // Declare the list of blobs
            var blobList = new List<Blob>();

            string sasContainerSignature = "";
         
            // ******************** Generate SaA to the contaienr ********************
            // Can generate Sas?
            if (blobContainerClient.CanGenerateSasUri)
            {
                BlobSasBuilder sasBuilder = new()
                {
                    //Name of the contaienr
                    BlobContainerName = blobContainerClient.Name,
                    Resource = "b",
                    ExpiresOn = DateTimeOffset.UtcNow.AddHours(1)
                };
            
                // Set the read permission to the Sas
                sasBuilder.SetPermissions(BlobSasPermissions.Read);

                //
                sasContainerSignature = blobContainerClient.GenerateSasUri(sasBuilder).AbsoluteUri.Split('?')[1].ToString();
            }

            //Each blob in blobs
            await foreach (var blob in blobs)
            {
                //
                var blobClient = blobContainerClient.GetBlobClient(blob.Name);

                //Create a new Blob
                Blob blobIndividual = new()
                {
                    Uri = blobClient.Uri.AbsoluteUri + "?" + sasContainerSignature
                };

                //Create a new Blob
                //Blob blobIndividual = new()
                //{
                //    Uri = blobClient.Uri.AbsoluteUri
                //};
                //
                //// ******************** Generate SaA Blob by Blob ********************
                //// Can generate Sas?
                //if (blobClient.CanGenerateSasUri)
                //{
                //    BlobSasBuilder sasBuilder = new()
                //    {
                //        //Name of the contaienr
                //        BlobContainerName = blobClient.GetParentBlobContainerClient().Name,
                //        BlobName = blob.Name,
                //        Resource = "b",
                //        ExpiresOn = DateTimeOffset.UtcNow.AddHours(1)
                //    };
                //
                //    // Set the read permission to the Sas
                //    sasBuilder.SetPermissions(BlobSasPermissions.Read);
                //
                //    //
                //    blobIndividual.Uri = blobClient.GenerateSasUri(sasBuilder).AbsoluteUri;
                //}

                //Create a blob Property from blobClient
                BlobProperties blobProperties = await blobClient.GetPropertiesAsync();

                //Check if the blob property has a title
                if(blobProperties.Metadata.ContainsKey("title"))
                {
                    //If so assigns it
                    blobIndividual.Title = blobProperties.Metadata["title"];
                }

                //Check if the blob property has a comment
                if (blobProperties.Metadata.ContainsKey("comment"))
                {
                    //If so assigns it
                    blobIndividual.Comment = blobProperties.Metadata["comment"];
                }

                blobList.Add(blobIndividual);
            }

            return blobList;
        }
    }
}
