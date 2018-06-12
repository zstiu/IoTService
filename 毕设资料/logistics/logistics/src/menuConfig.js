// 菜单配置
// headerMenuConfig：头部导航配置
// asideMenuConfig：侧边导航配置

const headerMenuConfig = [];

const asideMenuConfig = [
  {
    name: '注册',
    path: '/register',
    icon: 'home',
    type: [3],
  },
  {
    name: '登录',
    path: '/login',
    icon: 'home',
    type: [3],
  },
  {
    name: '车辆信息',
    path: '/cars',
    icon: 'home',
    type: [1],
  },
  {
    name: '订单信息',
    path: '/orders',
    icon: 'home',
    type: [0],
  },
  {
    name: '设备信息',
    path: '/device',
    icon: 'menu',
    type: [2,0],
  },{
    name: '报警信息',
    path: '/alert',
    icon: 'notice',
    type: [4],
  },
];

export { headerMenuConfig, asideMenuConfig };
