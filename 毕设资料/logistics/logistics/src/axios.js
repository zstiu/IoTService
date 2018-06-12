import axios from 'axios';
axios.defaults.timeout = 10000;
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://115.159.26.94:8080';


export {axios}  