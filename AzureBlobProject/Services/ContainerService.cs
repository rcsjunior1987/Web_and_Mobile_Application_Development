using Azure.Storage.Blobs;
using Azure.Storage.Blobs.Models;

namespace AzureBlobProject.Models.Services;

public class ContainerService : IContainerService
{
    private readonly BlobServiceClient _blobClient;

    //Contructor getting the container
    public ContainerService(BlobServiceClient blobClient)
    {
        _blobClient = blobClient;
    }

    //Creates a new container (if not exist) passing its Name
    public async Task CreateContainer(string containerName)
    {
        BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);
        await blobContainerClient.CreateIfNotExistsAsync(PublicAccessType.BlobContainer);
    }

    // Delete a container if it exists
    public async Task DeleteContainer(string containerName)
    {
        BlobContainerClient blobContainerClient = _blobClient.GetBlobContainerClient(containerName);
        await blobContainerClient.DeleteIfExistsAsync();
    }

    // Insert the containers name in a list of string
    public async Task<List<string>> GetAllContainer()
    {
        List<string> containerNameList = new();

        await foreach(BlobContainerItem blobContainerItem in _blobClient.GetBlobContainersAsync())
        {
            containerNameList.Add(blobContainerItem.Name);
        }

        return containerNameList;
    }

    public async Task<List<string>> GetAllContainerAndBlobs()
    {
        //List of string that will store the name of the blobs and containers
        List<string> containerAndBlobNames = new();

        // Print the Storage account name
        containerAndBlobNames.Add("Storage Account Name: " + _blobClient.AccountName);

        //Foreach containers
        await foreach(BlobContainerItem container in _blobClient.GetBlobContainersAsync())
        {
            //Container Name
            containerAndBlobNames.Add("____Container Name = " + container.Name);

            //Get the Container Name
            BlobContainerClient _blobContainer = _blobClient.GetBlobContainerClient(container.Name);

            //Foreach Blob inside the actual container
            await foreach (BlobItem blob in _blobContainer.GetBlobsAsync())
            {
                containerAndBlobNames.Add("________File Name (Blob) = " + blob.Name);
            }
        }

        return containerAndBlobNames;
    }
}
