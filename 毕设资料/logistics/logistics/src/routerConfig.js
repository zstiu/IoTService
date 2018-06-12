// 以下文件格式为描述路由的协议格式
// 你可以调整 routerConfig 里的内容
// 变量名 routerConfig 为 iceworks 检测关键字，请不要修改名称

import BlankLayout from './layouts/BlankLayout';
import Home from './pages/Home';

import HeaderFooterLayout from './layouts/HeaderFooterLayout';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';

import HeaderAsideFooterLayout from './layouts/HeaderAsideFooterLayout';
import Cars from './pages/Cars';

import Orders from './pages/Orders';
import NotFound from './pages/NotFound';
import OrderItem from './pages/OrderItem';
import Datastream from './pages/Datastream';

import AlertPage from './pages/AlertPage';
import Device from './pages/Device';

const routerConfig = [
  {
    path: '/',
    layout: BlankLayout,
    component: Home,
  },
  {
    path: '/register',
    layout: BlankLayout,
    component: RegisterPage,
  },
  {
    path: '/login',
    layout: BlankLayout,
    component: LoginPage,
  },
  {
    path: '/cars',
    layout: HeaderAsideFooterLayout,
    component: Cars,
  },
  {
    path: '/orders',
    layout: HeaderAsideFooterLayout,
    component: Orders,
  },
  {
    path: '/orderItem',
    layout: HeaderAsideFooterLayout,
    component: OrderItem,
  },
  {
    path: '/device',
    layout: HeaderAsideFooterLayout,
    component: Device,
  },
  {
    path: '/datastream',
    layout: HeaderAsideFooterLayout,
    component: Datastream,
  },
  {
    path: '/alert',
    layout: HeaderAsideFooterLayout,
    component: AlertPage,
  },
  {
    path: '*',
    layout: BlankLayout,
    component: NotFound,
  },
];

export default routerConfig;
