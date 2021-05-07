from .. import render_template
from ..controllers.ProductController import ProductController

class ShopRoute():

    def __init__(self, name, sortBy):
        self.name = name
        self.formatedName = '%{}%'.format(name)
        self.sortBy = sortBy

    def getRenderTemplate(self) :
        listProducts = ProductController.getListProductsByName(self.formatedName)
        return render_template('shop.html', products=listProducts, search=self.name)

    def sortProducts (self):
        if(self.sortBy == 'review'):    
            listSortedproducts = ProductController.sortByReview()            
        elif(self.sortBy == 'price'):
            listSortedproducts = ProductController.sortByPrice()
        else:
            listSortedproducts = ProductController.sortByNumReviews()
        return render_template('shop.html', products=listSortedproducts, sortby=self.sortBy)

