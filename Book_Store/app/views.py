from . import Blueprint, request
from .routes.HomeRoute import HomeRoute
from .routes.ProductRoute import ProductRoute
from .routes.ShopRoute import ShopRoute
from .routes.SupportRoute import SupportRoute
from .routes.OrderRoute import OrderRoute
from .routes.DeleteOrderRoute import DeleteOrderRoute
from .routes.DeleteOrderItemRoute import DeleteOrderItemRoute
from .routes.CheckOutRoute import CheckOutRoute
from .routes.SupportRoute import SupportRoute

bp = Blueprint('main', __name__)

@bp.route('/')
def index():
    return HomeRoute().getRenderTemplate()

@bp.route('/home')
def home():
    return HomeRoute().getRenderTemplate()

@bp.route('/support', methods=['POST', 'GET'], strict_slashes=False)
def support():
    return SupportRoute.getRenderTemplate()

@bp.route('/product/<int:productId>/')
def product(productId):
    return ProductRoute.getProductById(productId)

@bp.route('/shop/')
def search():    
    return ShopRoute(request.args.get('search'), '').getRenderTemplate()

@bp.route('/shop/<string:sortby>')
def shop(sortby):
     return ShopRoute('', sortby).sortProducts()

@bp.route('/order', methods=['POST', 'GET'])
def order():
    return OrderRoute(request.values.get('order'), request.values.get('productId')).getRenderTemplate()

@bp.route('/deleteOrderItem', methods=['POST'])
def deleteOrderItem():
    return DeleteOrderItemRoute(request.values.get('order'), request.form['id']).getRenderTemplate()

@bp.route('/deleteOrder')
def deleteOrder():
    return DeleteOrderRoute().getRenderTemplate()

@bp.route('/checkOut', methods=['POST', 'GET'], strict_slashes=False)
def checkOut():
    return CheckOutRoute().getRenderTemplate()