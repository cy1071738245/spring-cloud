axios.defaults.baseURL='http://localhost:9527'

axios.defaults.timeout = 3000

axios.defaults.headers['Content-Type'] = 'application/json'

// 设置前置拦截器->以后所有的AJAX都会自动添加上 Authorization:token 的协议头
axios.interceptors.request.use(function (config) {
    // 判断如果用户登录了就把 token 配置上 axios 的协议头中
    let token = localStorage.getItem('token')
    if (token) {
        config.headers['Authorization'] = token
    }
    // 处理请求前代码
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

function getProductList(){
    return axios.get("/joy-product/goods/list");
}

function getGoodsTypeList(){
    return axios.get("/joy-product/goodsType/list");
}

function login(params){
    return axios.post("/joy-user/wxCallBack", params);
}

function mergerOfCarts(params){
	return axios.post("/joy-cart/cart/mergerOfCarts", params);
}

function addCart(params){
	return axios.post("/joy-cart/cart/addCart", params);
}

function getOrder(userId){
	return axios.post("/joy-order/order/get", {"userId":userId});
}

function setOrder(params){
	return axios.post("/joy-order/order/set", params);
}

function pay(params){
	return axios.post("/joy-order/order/pay", params);
}

function getDetail(orderId){
	return axios.post("/joy-order/order/getDetail", {"orderId":orderId});
}