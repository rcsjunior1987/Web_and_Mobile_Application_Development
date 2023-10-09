namespace AzureBlobProject.Models.Services
{
    public interface IContainerService
    {
        // get all containers and blobs
        Task<List<string>> GetAllContainerAndBlobs();

        //List of the containers name
        Task<List<string>> GetAllContainer();

        //Create the container by its name
        Task CreateContainer(string containerName);

        //Delete the container by its name
        Task DeleteContainer(string containerName);
    }
}
