axios.defaults.baseURL='http://localhost:9527'

axios.defaults.timeout = 3000

axios.defaults.headers['Content-Type'] = 'application/json'

function getProductList(){
    return axios.get("/joy-product/goods/list");
}

function getGoodsTypeList(){
    return axios.get("/joy-product/goodsType/list");
}

function login(params){
    return axios.post("/joy-user/wxCallBack", params);
}

function addCart(params){
	return axios.post("/joy-cart/cart/addCart", params);
}
