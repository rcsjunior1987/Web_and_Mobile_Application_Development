using Azure.Storage.Blobs;
using AzureBlobProject.Models.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddRazorPages().AddRazorRuntimeCompilation();

// Add services to the container.
builder.Services.AddControllersWithViews();

//Injects the BlobConnect from appseetings.jason 
builder.Services.AddSingleton(u => new BlobServiceClient(
    builder.Configuration.GetValue<string>("BlobConnection")
    ));

// Goes to ContainerService
builder.Services.AddSingleton<IContainerService, ContainerService>();

// Goes to BlobService
builder.Services.AddSingleton<IBlobService, BlobService>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
